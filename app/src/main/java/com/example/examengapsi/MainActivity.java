package com.example.examengapsi;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.examengapsi.adapter.ProductsListAdapter;
import com.example.examengapsi.adapter.SearchHistoryAdapter;
import com.example.examengapsi.db.AppDataBase;
import com.example.examengapsi.model.Producto;
import com.example.examengapsi.model.SearchHistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainInterface.MainView {

    private MainPresenter mainPresenter;
    RecyclerView recyclerView, recyclerViewHistory;
    ProductsListAdapter productsListAdapter;
    SearchHistoryAdapter searchHistoryAdapter;
    SearchHistory searchHistory;
    InserBDTask inserBDTask;
    SearchHistoryDBTask searchHistoryDBTask;
    private List<SearchHistory> listHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainPresenter = new MainPresenter(this, this);

        searchHistory = new SearchHistory();

        searchHistoryDBTask = new SearchHistoryDBTask();
        searchHistoryDBTask.execute();

    }

    private void createRecyclerView(ArrayList<Producto> products) {
        recyclerView = findViewById(R.id.idRvProducts);
        productsListAdapter = new ProductsListAdapter(this, products);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(productsListAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.isNestedScrollingEnabled();
        productsListAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Busca producto");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Log.wtf("onQueryTextSubmit MainActivity", "query; " + query);
                inserBDTask = new InserBDTask();
                searchHistory.setText(query);
                inserBDTask.execute(searchHistory);
                mainPresenter.searchProduct(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.wtf("MainActivity", "onQueryTextChange | newText; " + newText);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void resultSearchjProduct(ArrayList<Producto> products) {
        createRecyclerView(products);
    }

    private class InserBDTask extends AsyncTask<SearchHistory, Void, Void> {

        @Override
        protected Void doInBackground(SearchHistory... searchHistories) {
            Log.wtf("doinBack", "" + searchHistories[0]);
            AppDataBase.getAppDB(getApplicationContext()).searchHistoryDAO().insertSearchHistory(searchHistories[0]);
            return null;
        }
    }

    private class SearchHistoryDBTask extends AsyncTask<Void,Void, List<SearchHistory>>{

        @Override
        protected List<SearchHistory> doInBackground(Void... voids) {
            listHistory = AppDataBase.getAppDB(getApplicationContext()).searchHistoryDAO().findAllHistory();
            return listHistory;
        }
        @Override
        public void  onPostExecute(List<SearchHistory> searchHistory){
            showHistory(searchHistory);
        }

        private void showHistory(List<SearchHistory> searchHistory) {
           Log.wtf("MainActivityt", "showHistory | searchlist: " + searchHistory);
           createRecyclerHistory(searchHistory);
        }
    }


    private void createRecyclerHistory(List<SearchHistory> searchHistory) {
        recyclerViewHistory = findViewById(R.id.idRvProducts);
        searchHistoryAdapter = new SearchHistoryAdapter(this, searchHistory);
        recyclerViewHistory.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerViewHistory.setAdapter(searchHistoryAdapter);
        recyclerViewHistory.setHasFixedSize(true);
        recyclerViewHistory.isNestedScrollingEnabled();
        searchHistoryAdapter.notifyDataSetChanged();
    }
}
