package com.example.examengapsi.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.examengapsi.model.SearchHistory;

@Database(entities = {SearchHistory.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase INSTANCE;
    public abstract SearchHistoryDAO searchHistoryDAO();

    public static  AppDataBase getAppDB(Context context){
        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "examen_gapsi_db")
                    .build();
        }
        return INSTANCE;
    }

    public  static  void  destroyBD(){
        INSTANCE=null;
    }
}
