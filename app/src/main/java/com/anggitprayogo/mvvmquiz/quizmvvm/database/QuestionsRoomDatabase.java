package com.anggitprayogo.mvvmquiz.quizmvvm.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(entities = {Questions.class}, version = 1)
public abstract class QuestionsRoomDatabase extends RoomDatabase{

    public abstract QuestionsDao wordDao();

    private static QuestionsRoomDatabase INSTANCE;

    static QuestionsRoomDatabase getDatabase(Context context){

        if (INSTANCE == null){
            synchronized (QuestionsRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            QuestionsRoomDatabase.class,
                            "questions"
                            )
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }

        return INSTANCE;
    }


    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
        }
    };

}
