package kv.myapplication.object;

import android.os.Parcel;
import android.os.Parcelable;

public class NewsArticle implements Parcelable {

    private String key;
    private String url;
    private String section;
    private String type;
    private String title;
    private String anAbstract;
    private String byLine;
    private String publishDate;
    private String source;
    private String desFacet;
    private String orgFacet;
    private String perFacet;
    private String geoFacet;
    private String imageUrl;
    private String bigImageUrl;

    public NewsArticle(){ }
    protected NewsArticle(Parcel in) {
        key = in.readString();
        url = in.readString();
        section = in.readString();
        type = in.readString();
        title = in.readString();
        anAbstract = in.readString();
        byLine = in.readString();
        publishDate = in.readString();
        source = in.readString();
        desFacet = in.readString();
        orgFacet = in.readString();
        perFacet = in.readString();
        geoFacet = in.readString();
        imageUrl = in.readString();
        bigImageUrl = in.readString();
    }

    public static final Creator<NewsArticle> CREATOR = new Creator<NewsArticle>() {
        @Override
        public NewsArticle createFromParcel(Parcel in) {
            return new NewsArticle(in);
        }

        @Override
        public NewsArticle[] newArray(int size) {
            return new NewsArticle[size];
        }
    };

    public String getKey() {
        return key;
    }

    public String getUrl() {
        return url;
    }

    public String getSection() {
        return section;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getByLine() {
        return byLine;
    }

    public String getAnAbstract() {
        return anAbstract;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public String getSource() {
        return source;
    }

    public String getDesFacet() {
        return desFacet;
    }

    public String getOrgFacet() {
        return orgFacet;
    }

    public String getPerFacet() {
        return perFacet;
    }

    public String getGeoFacet() {
        return geoFacet;
    }

    public String getBigImageUrl() {
        return bigImageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAbstract(String anAbstract) {
        this.anAbstract = anAbstract;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDesFacet(String desFacet) {
        this.desFacet = desFacet;
    }

    public void setOrgFacet(String orgFacet) {
        this.orgFacet = orgFacet;
    }

    public void setPerFacet(String perFacet) {
        this.perFacet = perFacet;
    }

    public void setGeoFacet(String geoFacet) {
        this.geoFacet = geoFacet;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setByLine(String byLine) {
        this.byLine = byLine;
    }

    public void setBigImageUrl(String bigImageUrl) {
        this.bigImageUrl = bigImageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(url);
        dest.writeString(section);
        dest.writeString(type);
        dest.writeString(title);
        dest.writeString(anAbstract);
        dest.writeString(byLine);
        dest.writeString(publishDate);
        dest.writeString(source);
        dest.writeString(desFacet);
        dest.writeString(orgFacet);
        dest.writeString(perFacet);
        dest.writeString(geoFacet);
        dest.writeString(imageUrl);
        dest.writeString(bigImageUrl);
    }


}
