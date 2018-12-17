package oth.archaeologicalfieldwork.main;

import android.app.Application;
import oth.archaeologicalfieldwork.helpers.SplashScreenHelper;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // register the util to remove splash screen after loading
        registerActivityLifecycleCallbacks(new SplashScreenHelper());
    }
}