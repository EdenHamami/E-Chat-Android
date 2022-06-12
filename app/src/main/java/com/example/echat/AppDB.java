package com.example.echat;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities ={Contact.class} ,version = 1)
public abstract class AppDB extends RoomDatabase {
    public abstract ContactDao contactDao();
    private static AppDB appDB;

    public static AppDB getInstance(Context context) {
        if (null == appDB) {
            appDB= Room.databaseBuilder(context,
                            AppDB.class,
                            "ContactDB")
                    .allowMainThreadQueries().build();
        }
        return appDB;
    }

}
