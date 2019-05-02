package com.room.saksham.kamre;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by MUZ0007 on 1/8/2016.
 */
public class RoomsApp extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        ParseObject.registerSubclass(Room.class);
        Parse.initialize(new Parse.Configuration.Builder(this).applicationId("CR16tBxcveR106nQ3xcGeKedg64VyAKMNo7qRmAW")
            .clientKey("CkcA70dAW7MhaL4nuwocDiPF7cD9OdRjyXu5L52m")
            .server("https://parseapi.back4app.com/")
            .build());
    }
}
