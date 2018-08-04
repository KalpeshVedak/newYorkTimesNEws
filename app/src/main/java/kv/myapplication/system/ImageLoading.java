package kv.myapplication.system;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class ImageLoading {
    private static final ImageLoading ourInstance = new ImageLoading();

    public static ImageLoading getInstance() {
        return ourInstance;
    }

    private ImageLoading() {
    }

    public void loadImage(String url, ImageView imageView, Context context, boolean center_crop) {
        if (!TextUtils.isEmpty(url)) {
            try {

                RequestBuilder<Bitmap> bitmapRequestBuilder;


                bitmapRequestBuilder =
                        Glide.with(context)
                                .asBitmap()
                                .load(Uri.parse(url));
                if(center_crop) {
                    bitmapRequestBuilder.apply(RequestOptions.centerCropTransform());
                    bitmapRequestBuilder.apply(RequestOptions.circleCropTransform());
                }else{
                    //bitmapRequestBuilder.apply(RequestOptions.fitCenterTransform());
                }

                bitmapRequestBuilder.apply(RequestOptions.skipMemoryCacheOf(true));
                bitmapRequestBuilder.apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL));
                bitmapRequestBuilder.into(imageView);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
