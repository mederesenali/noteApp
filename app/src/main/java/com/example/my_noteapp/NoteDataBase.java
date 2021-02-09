package com.example.my_noteapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDataBase extends RoomDatabase {
    private static NoteDataBase noteDataBase;

    public abstract NoteDao noteDao();

    public static synchronized NoteDataBase getInstance(Context context) {
        if (noteDataBase == null) {
            noteDataBase = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDataBase.class, "note_database").
                    fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return noteDataBase;
    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(noteDataBase).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;
        private PopulateDbAsyncTask(NoteDataBase db) {
            noteDao = db.noteDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title 1", "Description 1"));
            noteDao.insert(new Note("Title 2", "Description 2"));
            noteDao.insert(new Note("Title 3", "Description 3"));
            return null;
        }
    }
}
