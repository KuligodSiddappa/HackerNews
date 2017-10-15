package com.example.hackernews.ui.adapter;

import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hackernews.R;

public class HackerNewsAdapter extends RecyclerView.Adapter<HackerNewsAdapter.HackerNewsViewHolder> {

    private ItemClickEvent mItemClickEvent;

    @Override
    public HackerNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_list_item, parent, false);

        return new HackerNewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HackerNewsViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class HackerNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView newsTitle;
        LinearLayout itemParent;

        public HackerNewsViewHolder(View view) {
            super(view);
            newsTitle = (TextView) view.findViewById(R.id.news_item);
            itemParent = (LinearLayout)view.findViewById(R.id.recycler_item_parent);
            itemParent.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickPostion = getAdapterPosition();
        }
    }


    public void registerForItemClick(ItemClickEvent event){
        mItemClickEvent = event;
    }
}
