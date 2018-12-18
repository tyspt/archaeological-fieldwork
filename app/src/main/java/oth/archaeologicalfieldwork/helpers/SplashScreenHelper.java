package oth.archaeologicalfieldwork.helpers;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import oth.archaeologicalfieldwork.R;

/**
 * Splash Screen with Default Theme approach
 * <p>
 * Reference:
 * https://blog.davidmedenjak.com/android/2017/09/02/splash-screens.html
 * https://github.com/bleeding182/samples/tree/master/SplashScreen
 */
public class SplashScreenHelper implements Application.ActivityLifecycleCallbacks {

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        // apply the actual theme
        activity.setTheme(R.style.AppTheme);

    }

    @Override
    public void onActivityStarted(Activity activity) {
        // do nothing
    }

    @Override
    public void onActivityResumed(Activity activity) {
        // do nothing
    }

    @Override
    public void onActivityPaused(Activity activity) {
        // do nothing
    }

    @Override
    public void onActivityStopped(Activity activity) {
        // do nothing
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        // do nothing
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        // do nothing
    }
}