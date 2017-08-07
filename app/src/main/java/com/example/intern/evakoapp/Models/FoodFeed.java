package com.example.intern.Evakoapp.Models;

/**
 * Created by intern on 24/01/2017.
 */
public class FoodFeed {

    public FoodFeed(){

    }
    String feedLogo;
    String feedName;
    String feedImage;
    String feedTime;
    String feedDescArabic;
    String feedDescEnglish;
    String feedLikes;
    String feedShares;
    String feedOrders;

    public String getFeedImage() {
        return feedImage;
    }

    public void setFeedImage(String feedImage) {
        this.feedImage = feedImage;
    }

    public String getFeedLogo() {
        return feedLogo;
    }

    public void setFeedLogo(String feedLogo) {
        this.feedLogo = feedLogo;
    }

    public String getFeedName() {
        return feedName;
    }

    public void setFeedName(String feedName) {
        this.feedName = feedName;
    }

    public String getFeedTime() {
        return feedTime;
    }

    public void setFeedTime(String feedTime) {
        this.feedTime = feedTime;
    }

    public String getFeedDescArabic() {
        return feedDescArabic;
    }

    public void setFeedDescArabic(String feedDescArabic) {
        this.feedDescArabic = feedDescArabic;
    }

    public String getFeedDescEnglish() {
        return feedDescEnglish;
    }

    public void setFeedDescEnglish(String feedDescEnglish) {
        this.feedDescEnglish = feedDescEnglish;
    }

    public String getFeedLikes() {
        return feedLikes;
    }

    public void setFeedLikes(String feedLikes) {
        this.feedLikes = feedLikes;
    }

    public String getFeedShares() {
        return feedShares;
    }

    public void setFeedShares(String feedShares) {
        this.feedShares = feedShares;
    }

    public String getFeedOrders() {
        return feedOrders;
    }

    public void setFeedOrders(String feedOrders) {
        this.feedOrders = feedOrders;
    }


}
