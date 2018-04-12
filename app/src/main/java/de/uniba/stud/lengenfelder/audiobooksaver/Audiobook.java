package de.uniba.stud.lengenfelder.audiobooksaver;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Audiobook {
    @PrimaryKey
    private int id;
    @ColumnInfo
    private String title;
    @ColumnInfo
    private String desc;
    @ColumnInfo
    private String uri;

    public Audiobook(int id, String title, String desc, String uri) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.uri = uri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
