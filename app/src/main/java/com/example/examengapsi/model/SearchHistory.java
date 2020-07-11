package com.example.examengapsi.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "search_history")
public class SearchHistory {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name="text")
    private String text;

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    public void setText(@NonNull String text) {
        this.text = text;
    }

    @NonNull
    public String getText() {
        return text;
    }
}
