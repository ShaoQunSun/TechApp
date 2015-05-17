package com.tech;

import android.app.Application;
import android.os.Handler;
import android.os.Process;
import android.util.Log;

import com.tech.activity.base.FrameworkActivityManager;

/**
 * 同步课堂Application类
 * 
 * @author Sam
 * @date 2015-5-17 下午3:11:53
 */
public class TechApp extends Application {

	private final String TAG = "TechApp";

	private static TechApp mApp;

	static public TechApp getAppInstance() {

		if (mApp == null)
			throw new NullPointerException("app not create or be terminated!");
		return mApp;
	}

	@Override
	public void onCreate() {

		super.onCreate();

		// Thread.setDefaultUncaughtExceptionHandler(new WbUncaughtExceptionHandler());
		mApp = this;
		Log.i(TAG, "onCreate");
	}

	@Override
	public void onTerminate() {

		Log.i(TAG, "onTerminate");
		super.onTerminate();
	}

	public void finishApp() {

		FrameworkActivityManager.getInstance().FinishAllActivity();

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {

				KillApplication.killApplicationByPackageName(TechApp.this, getPackageName());
			}
		}, 1000);

	}

	public class WbUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

		@Override
		public void uncaughtException(Thread thread, Throwable ex) {

			Log.i("WbUncaughtExceptionHandler", "WbUncaughtExceptionHandler");
			ex.printStackTrace();

			finishApp();

			Process.killProcess(Process.myPid());
		}
	}
}
