package de.uniba.stud.lengenfelder.audiobooksaver;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

@Entity
public class Audiobook implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(desc);
        dest.writeString(uri);
    }

    public Audiobook(Parcel in) {
        id = in.readInt();
        title = in.readString();
        desc = in.readString();
        uri = in.readString();
    }

    public static final Parcelable.Creator<Audiobook> CREATOR = new Parcelable.Creator<Audiobook>() {
        public Audiobook createFromParcel(Parcel in) {
            return new Audiobook(in);
        }

        public Audiobook[] newArray(int size) {
            return new Audiobook[size];
        }
    };
}
