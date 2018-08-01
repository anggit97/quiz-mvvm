package com.anggitprayogo.mvvmquiz.quizmvvm.network;

import android.arch.lifecycle.LiveData;

import com.anggitprayogo.mvvmquiz.quizmvvm.model.QuestionsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET("json/questions.json")
    Call<List<QuestionsModel>> getQuestions();

}
