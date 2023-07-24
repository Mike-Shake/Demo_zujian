package com.example.room_demo2.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.room_demo2.model.Person;

@Database(entities = {Person.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "personList";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d("gao", "getInstance: ");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                                AppDatabase.class, AppDatabase.DATABASE_NAME)
                        .build();
            }
        }
        Log.d("gao", "getInstance: finish");
        return sInstance;
    }

    public abstract PersonDao personDao();
}
