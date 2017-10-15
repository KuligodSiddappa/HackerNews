package com.example.hackernews.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hackernews.R;
import com.example.hackernews.newsmodel.NewsDataModel;

import java.util.ArrayList;

public class HackerNewsAdapter extends RecyclerView.Adapter<HackerNewsAdapter.HackerNewsViewHolder> {

    private ItemClickEvent mItemClickEvent;
    private ArrayList<NewsDataModel> mDataModels = new ArrayList<>();
    private Context mContext;

    public HackerNewsAdapter(Context context) {
        mContext = context;
    }

    public void updateData(ArrayList<NewsDataModel> dataModels) {
        if (mDataModels != null && dataModels != null) {
            mDataModels.clear();
            mDataModels.addAll(dataModels);
            notifyDataSetChanged();
        }
    }

    @Override
    public HackerNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_list_item, parent, false);

        return new HackerNewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HackerNewsViewHolder holder, int position) {
        NewsDataModel dataModel = mDataModels.get(position);
        holder.newsTitle.setText(dataModel.getTitle());
    }

    @Override
    public int getItemCount() {
        return mDataModels.size();
    }

    public void registerForItemClick(ItemClickEvent event) {
        mItemClickEvent = event;
    }

    public class HackerNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView newsTitle;
        LinearLayout itemParent;

        public HackerNewsViewHolder(View view) {
            super(view);
            newsTitle = (TextView) view.findViewById(R.id.news_item);
            itemParent = (LinearLayout) view.findViewById(R.id.recycler_item_parent);
            itemParent.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            int clickPostion = getAdapterPosition();
            String browserUrl = mDataModels.get(clickPostion).getUrl();
            if (browserUrl != null) {
                Uri uri = Uri.parse(browserUrl);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);
                mContext.startActivity(browserIntent);
            }
        }
    }
}
