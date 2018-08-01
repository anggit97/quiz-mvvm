package com.anggitprayogo.mvvmquiz.quizmvvm.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.anggitprayogo.mvvmquiz.quizmvvm.database.Questions;
import com.anggitprayogo.mvvmquiz.quizmvvm.database.QuestionsRepository;

import java.util.List;

public class QuestionsViewModel extends AndroidViewModel{

    private QuestionsRepository mRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private LiveData<List<Questions>> mAllQuestions;

    public QuestionsViewModel (Application application) {
        super(application);
        mRepository = new QuestionsRepository(application);
        mAllQuestions = mRepository.getmAllQuestions();
    }

    public LiveData<List<Questions>> getAllQuestions() { return mAllQuestions; }

    public void insert(Questions word) { mRepository.insert(word); }
}
