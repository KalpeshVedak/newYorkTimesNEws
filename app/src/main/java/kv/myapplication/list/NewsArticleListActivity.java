package kv.myapplication.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import kv.myapplication.R;
import kv.myapplication.details.NewsArticleDetailActivity;

/**
 * An activity representing a list of newsArticles. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link NewsArticleDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class NewsArticleListActivity extends AppCompatActivity implements INewsArticleListInterface, View.OnClickListener{

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private NewsArticleListPresenter mArticleListPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsarticle_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        if (findViewById(R.id.newsarticle_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        RecyclerView recyclerView = findViewById(R.id.newsarticle_list);
        DividerItemDecoration itemDecor = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecor);
        mArticleListPresenter = new NewsArticleListPresenter(this,new NewsArticleListFetch());
        mArticleListPresenter.setNewsArticleList(recyclerView,mTwoPane);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mArticleListPresenter.cleanUp();
    }

    @Override
    public void onClick(View v) {
        mArticleListPresenter.launchNextScreen(v);
    }
}
