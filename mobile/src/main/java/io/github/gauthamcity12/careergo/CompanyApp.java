package io.github.gauthamcity12.careergo;


import android.app.Application;
import android.content.Context;

public class CompanyApp extends Application {
    private static Context context = null;

    public void onCreate(){
        super.onCreate();
        CompanyApp.context = getApplicationContext();
    }

    public void setContext(Context c){
        CompanyApp.context = c;
    }

    public static Context getAppContext(){
        return CompanyApp.context;
    }
}
