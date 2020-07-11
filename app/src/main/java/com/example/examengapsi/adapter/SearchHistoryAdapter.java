package com.example.examengapsi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examengapsi.R;
import com.example.examengapsi.model.SearchHistory;

import java.util.List;

public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.SearchHistoryHolder> {

    Context context;
    List<SearchHistory> searchHistory;

    public SearchHistoryAdapter(Context context, List<SearchHistory> searchHistory) {
        this.context = context;
        this.searchHistory = searchHistory;
    }


    public interface OnClickListener {
        void selectItem(String text);
    }

    public OnClickListener onClickListener;

    @NonNull
    @Override
    public SearchHistoryAdapter.SearchHistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchHistoryHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_history, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHistoryAdapter.SearchHistoryHolder holder, final int position) {
        holder.text.setText(searchHistory.get(position).getText());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.selectItem(searchHistory.get(position).getText());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchHistory.size();
    }

    public class SearchHistoryHolder extends RecyclerView.ViewHolder {

        TextView text;

        public SearchHistoryHolder(@NonNull View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.idTv);
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
