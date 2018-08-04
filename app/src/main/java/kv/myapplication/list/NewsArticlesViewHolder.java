package kv.myapplication.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kv.myapplication.R;
import kv.myapplication.object.NewsArticle;
import kv.myapplication.system.ImageLoading;

public class NewsArticlesViewHolder extends  RecyclerView.ViewHolder{

    private View mParentView;
    NewsArticlesViewHolder(View view) {
        super(view);
        mParentView=view;
    }

    public void bind(NewsArticle newsArticle) {
        mParentView.setTag(newsArticle);
        ((TextView)mParentView.findViewById(R.id.title)).setText(newsArticle.getTitle());
        ((TextView)mParentView.findViewById(R.id.reporter)).setText(newsArticle.getByLine());
        ((TextView) mParentView.findViewById(R.id.calendar_text)).setText(newsArticle.getPublishDate());
        ImageLoading.getInstance().loadImage(newsArticle.getImageUrl(),(ImageView) mParentView.findViewById(R.id.thumbImage),mParentView.getContext().getApplicationContext(),true);
    }
}
