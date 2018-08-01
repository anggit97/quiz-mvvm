package com.anggitprayogo.mvvmquiz.quizmvvm;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anggitprayogo.mvvmquiz.quizmvvm.database.Questions;
import com.anggitprayogo.mvvmquiz.quizmvvm.model.QuestionsModel;
import com.anggitprayogo.mvvmquiz.quizmvvm.model.QuestionsViewModel;
import com.anggitprayogo.mvvmquiz.quizmvvm.network.DataServiceGenerator;
import com.anggitprayogo.mvvmquiz.quizmvvm.network.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizActivity extends AppCompatActivity {

    List<Questions> mQuestions;
    int score = 0;
    int qid = 0;
    Questions currentQ;
    TextView txtQuestions;
    RadioButton rda, rdb, rdc;
    Button btnNext;
    private QuestionsViewModel questionsViewModel;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        relativeLayout = (RelativeLayout)findViewById(R.id.profileLoadingScreen);

        fetchQuestions();

        questionsViewModel = ViewModelProviders.of(QuizActivity.this).get(QuestionsViewModel.class);

        questionsViewModel.getAllQuestions().observe(this, new Observer<List<Questions>>() {
            @Override
            public void onChanged(@Nullable final List<Questions> words) {
                // Update the cached copy of the words in the adapter.
                mQuestions = words;
                Collections.shuffle(mQuestions);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_quiz, menu);
        return true;
    }

    private void fetchQuestions() {
        relativeLayout.setVisibility(View.VISIBLE);

        DataServiceGenerator dataServiceGenerator = new DataServiceGenerator();

        Service service = DataServiceGenerator.createService(Service.class);

        Call<List<QuestionsModel>> call = service.getQuestions();

        call.enqueue(new Callback<List<QuestionsModel>>() {
            @Override
            public void onResponse(Call<List<QuestionsModel>> call, Response<List<QuestionsModel>> response) {
                if (response.isSuccessful()){

                    if (response != null){
                        List<QuestionsModel> questionsModelList = response.body();

                        for (int i = 0; i < questionsModelList.size(); i++){
                            String question = questionsModelList.get(i).getQuestion();
                            String answer = questionsModelList.get(i).getAnswer();
                            String opta = questionsModelList.get(i).getOpta();
                            String optb = questionsModelList.get(i).getOptb();
                            String optc = questionsModelList.get(i).getOptc();

                            Questions questions = new Questions(question, answer, opta, optb, optc);

                            questionsViewModel.insert(questions);
                        }

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                takeAction();

                            }
                        }, 3000);

                    }

                }else{

                }
            }

            @Override
            public void onFailure(Call<List<QuestionsModel>> call, Throwable t) {

            }
        });

    }

    private void setQuestionView()
    {
        txtQuestions.setText(currentQ.getQuestion());
        rda.setText(currentQ.getOptA());
        rdb.setText(currentQ.getOptB());
        rdc.setText(currentQ.getOptC());
        qid++;
    }

    private void takeAction() {
        relativeLayout.setVisibility(View.INVISIBLE);
        currentQ = mQuestions.get(qid);
        txtQuestions = (TextView)findViewById(R.id.textView1);
        rda=(RadioButton)findViewById(R.id.radio0);
        rdb=(RadioButton)findViewById(R.id.radio1);
        rdc=(RadioButton)findViewById(R.id.radio2);
        btnNext=(Button)findViewById(R.id.button1);
        setQuestionView();
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp=(RadioGroup)findViewById(R.id.radioGroup1);

                if (grp.getCheckedRadioButtonId() == -1){
                    return;
                }

                RadioButton answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());

                grp.clearCheck();
                //Log.d("yourans", currentQ.getANSWER()+" "+answer.getText());

                if(currentQ.getAnswer().equals(answer.getText()))
                {
                    score++;
                    Log.d("score", "Your score"+score);
                }
                if(qid<5){
                    currentQ=mQuestions.get(qid);
                    setQuestionView();
                }else{
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("score", score); //Your score
                    intent.putExtras(b); //Put your score to your next Intent
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
