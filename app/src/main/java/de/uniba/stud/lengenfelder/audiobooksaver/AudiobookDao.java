package de.uniba.stud.lengenfelder.audiobooksaver;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface AudiobookDao {
    @Query("SELECT * FROM audiobook")
    List<Audiobook> getAll();

    @Query("SELECT * FROM audiobook WHERE id=:id")
    Audiobook findById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAudiobook(Audiobook audiobook);

    @Update
    void updateUri(Audiobook audiobook);

    @Delete
    void deleteAudiobook(Audiobook audiobook);
}
