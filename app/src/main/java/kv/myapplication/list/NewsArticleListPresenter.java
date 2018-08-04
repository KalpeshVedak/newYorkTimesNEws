package kv.myapplication.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import kv.myapplication.R;
import kv.myapplication.details.NewsArticleDetailActivity;
import kv.myapplication.details.NewsArticleDetailFragment;
import kv.myapplication.object.NewsArticle;

class NewsArticleListPresenter {

    private NewsArticleListActivity mNewsArticleListActivity;
    private NewsArticleListFetch mNewsArticleListFetch;
    private Disposable mNewsListDisposable;
    private boolean mTwoPane;

    NewsArticleListPresenter(NewsArticleListActivity newsArticleListActivity, NewsArticleListFetch newsArticleListFetch) {
        mNewsArticleListActivity=newsArticleListActivity;
        mNewsArticleListFetch=newsArticleListFetch;
    }

    public void setNewsArticleList(final RecyclerView newsArticleListView, final boolean mTwoPane) {
        this.mTwoPane=mTwoPane;
        if(mNewsListDisposable==null || mNewsListDisposable.isDisposed()) {
            mNewsListDisposable = mNewsArticleListFetch.getNewsArticleList().subscribe(new Consumer<ArrayList<NewsArticle>>() {
                @Override
                public void accept(ArrayList<NewsArticle> list) {
                    if (list.size() == 1) {
                        if (!list.get(0).getException().isEmpty()) {
                            mNewsArticleListActivity.showErrorMessage();
                            return;
                        }

                    }
                    NewsArticlesAdapter newsArticlesAdapter = new NewsArticlesAdapter(mNewsArticleListActivity,list);
                    newsArticleListView.setAdapter(newsArticlesAdapter);
                    mNewsArticleListActivity.hideProgress();
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) {
                    throwable.printStackTrace();
                }
            });
        }
       fetchNewsArticles();
    }

    private void fetchNewsArticles()
    {
        mNewsArticleListActivity.showProgress();
        mNewsArticleListFetch.fetchNewsArticles();
    }


    public void cleanUp() {
        mNewsListDisposable.dispose();
    }

    public void launchNextScreen(View view) {
                   if (mTwoPane) {
                Bundle arguments = new Bundle();
                arguments.putParcelable(NewsArticleDetailFragment.ARG_ITEM_ID, (Parcelable) view.getTag());
                NewsArticleDetailFragment fragment = new NewsArticleDetailFragment();
                fragment.setArguments(arguments);
               mNewsArticleListActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.newsarticle_detail_container, fragment)
                .commit();
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, NewsArticleDetailActivity.class);
                intent.putExtra(NewsArticleDetailFragment.ARG_ITEM_ID, (Parcelable) view.getTag());
                context.startActivity(intent);
            }
    }
}
