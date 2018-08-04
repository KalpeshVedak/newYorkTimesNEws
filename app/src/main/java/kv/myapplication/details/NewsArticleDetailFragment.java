package kv.myapplication.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kv.myapplication.R;
import kv.myapplication.list.NewsArticleListActivity;
import kv.myapplication.object.NewsArticle;

/**
 * A fragment representing a single mNewsArticle detail screen.
 * This fragment is either contained in a {@link NewsArticleListActivity}
 * in two-pane mode (on tablets) or a {@link NewsArticleDetailActivity}
 * on handsets.
 */
public class NewsArticleDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    private TextView mTextView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewsArticleDetailFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.newsarticle_detail, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NewsArticle newsArticle=getArguments().getParcelable(NewsArticleDetailFragment.ARG_ITEM_ID);
        String linkText = newsArticle.getTitle()+"<br/><br/><br/>"+ newsArticle.getAnAbstract()+"<br/><br/> <a href='"+newsArticle.getUrl()+"'>Click for more.</a>.";
        mTextView = view.findViewById(R.id.newsarticle_detail);
        mTextView.setText(Html.fromHtml(linkText));
        mTextView.setMovementMethod(LinkMovementMethod.getInstance());



    }
}
