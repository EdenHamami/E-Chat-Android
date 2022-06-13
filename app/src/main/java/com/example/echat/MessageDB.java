package com.example.echat;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities ={Message.class} ,version = 1)
public abstract class MessageDB extends RoomDatabase {
    private static MessageDB messageDB;

    public static MessageDB getInstance(Context context) {
        if (null == messageDB) {
            messageDB= Room.databaseBuilder(context,
                            MessageDB.class,
                            "MessageDB")
                    .allowMainThreadQueries().build();
        }
        return messageDB;
    }

    public abstract MessageDao messageDao();
}
