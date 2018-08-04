package kv.myapplication.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import kv.myapplication.R;
import kv.myapplication.list.NewsArticleListActivity;
import kv.myapplication.object.NewsArticle;
import kv.myapplication.system.ImageLoading;

/**
 * An activity representing a single newsArticle detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link NewsArticleListActivity}.
 */
public class NewsArticleDetailActivity extends AppCompatActivity {

    private ImageView mImage;
    private NewsArticle mNewsArticle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsarticle_detail);
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            mNewsArticle=getIntent().getParcelableExtra(NewsArticleDetailFragment.ARG_ITEM_ID);
            Bundle arguments = new Bundle();
            arguments.putParcelable(NewsArticleDetailFragment.ARG_ITEM_ID,mNewsArticle);
            NewsArticleDetailFragment fragment = new NewsArticleDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.newsarticle_detail_container, fragment)
                    .commit();
            mImage = findViewById(R.id.main_image);
            ImageLoading.getInstance().loadImage(mNewsArticle.getBigImageUrl(),mImage,getApplicationContext(),false);
        }
    }
}
