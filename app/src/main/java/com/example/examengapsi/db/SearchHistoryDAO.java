package com.example.examengapsi.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.examengapsi.model.SearchHistory;

import java.util.List;


@Dao
public interface SearchHistoryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSearchHistory(SearchHistory ... searchHistory);

    @Query("SELECT * FROM search_history")
    List<SearchHistory> findAllHistory();

}
