package com.anggitprayogo.mvvmquiz.quizmvvm.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

@Entity(tableName = "questions")
public class Questions {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "question")
    private String question;

    @Nullable
    @ColumnInfo(name = "answer")
    private String answer;

    @Nullable
    @ColumnInfo(name = "optA")
    private String optA;

    @Nullable
    @ColumnInfo(name = "optB")
    private String optB;

    @Nullable
    @ColumnInfo(name = "optC")
    private String optC;

    public Questions(@NonNull String question, String answer, String optA, String optB, String optC) {
        this.question = question;
        this.answer = answer;
        this.optA = optA;
        this.optB = optB;
        this.optC = optC;
    }

    public Questions() {
        id = 0;
        question = "";
        optA = "";
        optB = "";
        optC = "";
        answer = "";
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    @NonNull
    public String getQuestion() {
        return question;
    }

    public void setQuestion(@NonNull String question) {
        this.question = question;
    }

    @Nullable
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(@Nullable String answer) {
        this.answer = answer;
    }

    @Nullable
    public String getOptA() {
        return optA;
    }

    public void setOptA(@Nullable String optA) {
        this.optA = optA;
    }

    @Nullable
    public String getOptB() {
        return optB;
    }

    public void setOptB(@Nullable String optB) {
        this.optB = optB;
    }

    @Nullable
    public String getOptC() {
        return optC;
    }

    public void setOptC(@Nullable String optC) {
        this.optC = optC;
    }
}
