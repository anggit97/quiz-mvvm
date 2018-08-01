package com.anggitprayogo.mvvmquiz.quizmvvm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestionsModel {

    @Expose
    @SerializedName("answer")
    private String answer;
    @Expose
    @SerializedName("optC")
    private String optc;
    @Expose
    @SerializedName("optB")
    private String optb;
    @Expose
    @SerializedName("optA")
    private String opta;
    @Expose
    @SerializedName("question")
    private String question;

    public String getAnswer() {
        return answer;
    }

    public String getOptc() {
        return optc;
    }

    public String getOptb() {
        return optb;
    }

    public String getOpta() {
        return opta;
    }

    public String getQuestion() {
        return question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setOptc(String optc) {
        this.optc = optc;
    }

    public void setOptb(String optb) {
        this.optb = optb;
    }

    public void setOpta(String opta) {
        this.opta = opta;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
