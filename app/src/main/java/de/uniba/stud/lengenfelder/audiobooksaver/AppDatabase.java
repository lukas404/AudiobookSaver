package de.uniba.stud.lengenfelder.audiobooksaver;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Audiobook.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AudiobookDao audiobookDao();
    private static AppDatabase instance = null;

    protected AppDatabase() {
        // Filler
    }

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            // temporary solution: main thread queries
            instance = Room.databaseBuilder(context,
                    AppDatabase.class, "database-name").allowMainThreadQueries().build();
        }
        return instance;
    }

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}
