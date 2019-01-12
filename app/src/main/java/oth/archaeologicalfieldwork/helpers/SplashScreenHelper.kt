package oth.archaeologicalfieldwork.helpers

import android.app.Activity
import android.app.Application
import android.os.Bundle
import oth.archaeologicalfieldwork.R

/**
 * Splash Screen with Default Theme approach
 *
 *
 * Reference:
 * https://blog.davidmedenjak.com/android/2017/09/02/splash-screens.html
 * https://github.com/bleeding182/samples/tree/master/SplashScreen
 *
 */
class SplashScreenHelper : Application.ActivityLifecycleCallbacks {
    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        activity?.setTheme(R.style.AppTheme)
    }

    override fun onActivityPaused(activity: Activity?) {}

    override fun onActivityResumed(activity: Activity?) {}

    override fun onActivityStarted(activity: Activity?) {}

    override fun onActivityDestroyed(activity: Activity?) {}

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {}

    override fun onActivityStopped(activity: Activity?) {}
}