package com.room.saksham.kamre;

/**
 * Created by MUZ0007 on 6/10/2016.
 */
public class PreferencesAmenity {
    public int getPrefImageView() {
        return prefImageView;
    }

    public String getPrefTextView() {
        return prefTextView;
    }

    public PreferencesAmenity(int imageView, String textView){
        this.prefImageView = imageView;
        this.prefTextView = textView;

    }

    public void setPrefImageView(int featuresImageView) {
        this.prefImageView = featuresImageView;
    }

    public void setPrefTextView(String featuresTextView) {
        this.prefTextView = featuresTextView;
    }

    private int prefImageView;
    private String prefTextView;
}
