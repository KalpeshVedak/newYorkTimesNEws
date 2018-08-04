package kv.myapplication.list;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.reactivex.subjects.BehaviorSubject;
import kv.myapplication.object.NewsArticle;
import kv.myapplication.system.NetworkCalls;

public class NewsArticleListFetch {


    private BehaviorSubject<ArrayList<NewsArticle>> mArticleListHander;
    private NewsFetchTask mNewsFetchTask;
    public BehaviorSubject<ArrayList<NewsArticle>> getNewsArticleList() {
        return mArticleListHander;
    }

    public NewsArticleListFetch()
    {
        mArticleListHander=BehaviorSubject.create();
    }

    public void fetchNewsArticles() {

        mNewsFetchTask=null;
        mNewsFetchTask = new NewsFetchTask();
        mNewsFetchTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    class NewsFetchTask extends AsyncTask<String, String, ArrayList> {

        @Override
        protected ArrayList doInBackground(String... objects) {
            ArrayList newsArticleList = new ArrayList();
            try {
                String url = "http://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=d1677cf1f6584f0383536da7315cdce2";
                JSONObject jsonObject = NetworkCalls.getInstance().performNetworkCall(url);
                newsArticleList = parseNewsArticleResponse(jsonObject);
            } catch (Exception e) {
                NewsArticle newsArticle = new NewsArticle();
                newsArticle.setException(e);
                newsArticleList.add(newsArticle);
            }
            return newsArticleList;
        }

        @Override
        protected void onPostExecute(ArrayList list) {
            super.onPostExecute(list);
            mArticleListHander.onNext(list);
        }
    }

    private ArrayList<NewsArticle> parseNewsArticleResponse(JSONObject jsonObject) {
        ArrayList<NewsArticle> newsArticles = new ArrayList<>();
        try {
            JSONArray newsArticleArray = jsonObject.getJSONArray("results");
            for( int articleIndex = 0; articleIndex<newsArticleArray.length();articleIndex++) {
                newsArticles.add(returnNewsObject(newsArticleArray.getJSONObject(articleIndex)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsArticles;
    }

    private NewsArticle returnNewsObject(JSONObject newsObject) throws JSONException {
        NewsArticle newsArticle = new NewsArticle();
        newsArticle.setKey(newsObject.optString("id"));
        newsArticle.setUrl(newsObject.optString("url"));
        newsArticle.setSection(newsObject.optString("section"));
        newsArticle.setType(newsObject.optString("type"));
        newsArticle.setTitle(newsObject.optString("title"));
        newsArticle.setByLine(newsObject.optString("byline"));
        newsArticle.setAbstract(newsObject.optString("abstract"));
        newsArticle.setPublishDate(newsObject.optString("published_date"));
        newsArticle.setSource(newsObject.optString("source"));
        newsArticle.setDesFacet(newsObject.optString("des_facet"));
        newsArticle.setOrgFacet(newsObject.optString("org_facet"));
        newsArticle.setPerFacet(newsObject.optString("per_facet"));
        newsArticle.setGeoFacet(newsObject.optString("geo_facet"));
        extractPhotThumb(newsObject,newsArticle);
        return newsArticle;
    }

    private void extractPhotThumb(JSONObject newsObject, NewsArticle newsArticle) throws JSONException {
        JSONArray mediaArray = newsObject.getJSONArray("media");
        JSONObject mediaMetaData = mediaArray.getJSONObject(0);
        JSONArray mediaMetaArray = mediaMetaData.getJSONArray("media-metadata");
        newsArticle.setImageUrl(mediaMetaArray.getJSONObject(0).optString("url"));
        newsArticle.setBigImageUrl(mediaMetaArray.getJSONObject(2).optString("url"));
    }


}
