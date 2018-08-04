package kv.myapplication.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import kv.myapplication.R;
import kv.myapplication.object.NewsArticle;

public class NewsArticlesAdapter extends RecyclerView.Adapter<NewsArticlesViewHolder> {
    private final NewsArticleListActivity mParentActivity;
    private final List<NewsArticle> mValues;

    NewsArticlesAdapter(NewsArticleListActivity parent,
                        List<NewsArticle> items) {
        mValues = items;
        mParentActivity = parent;
    }

    @NonNull
    @Override
    public NewsArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.newsarticle_list_content, parent, false);
        view.setOnClickListener(mParentActivity);
        return new NewsArticlesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsArticlesViewHolder holder, int position) {
       holder.bind(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}
