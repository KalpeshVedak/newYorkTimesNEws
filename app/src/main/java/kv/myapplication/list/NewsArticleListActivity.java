package kv.myapplication.list;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

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
    private ProgressDialog progress;
    private NewsArticleListPresenter mArticleListPresenter;
    private Button mTryAgain;
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
        final RecyclerView recyclerView = findViewById(R.id.newsarticle_list);
        DividerItemDecoration itemDecor = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecor);
        mArticleListPresenter = new NewsArticleListPresenter(this,new NewsArticleListFetch());
        mArticleListPresenter.setNewsArticleList(recyclerView,mTwoPane);
        mTryAgain = findViewById(R.id.tryAgain);
        mTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTryAgain.setVisibility(View.INVISIBLE);
                mArticleListPresenter.setNewsArticleList(recyclerView,mTwoPane);
            }
        });
    }

    @Override
    public void showProgress() {
        progress=new ProgressDialog(this);
        progress.setMessage(getString(R.string.progres_bar_title));
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();
    }

    @Override
    public void hideProgress() {
        progress.dismiss();
        progress.hide();
    }

    @Override
    public void showErrorMessage() {
       progress.dismiss();
       progress.hide();
       mTryAgain.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mArticleListPresenter.cleanUp();
        progress=null;
    }

    @Override
    public void onClick(View v) {
        mArticleListPresenter.launchNextScreen(v);
    }


}
