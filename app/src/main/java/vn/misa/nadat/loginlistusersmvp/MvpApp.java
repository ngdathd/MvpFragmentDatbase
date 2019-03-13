package vn.misa.nadat.loginlistusersmvp;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MvpApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}
