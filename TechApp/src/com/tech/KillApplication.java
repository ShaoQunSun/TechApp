package com.tech;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

/**
 * 负责退出整个应用程序
 * 
 * @author LiuMM
 * @date 2011-10-9
 */
public class KillApplication {

	static public boolean killApplicationByPackageName(Context context, String packageName) {

		if (TextUtils.isEmpty(packageName))
			return false;
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

		Method method = null;

		boolean ret = false;
		try {
			int sdkV = Build.VERSION.SDK_INT;
			if (sdkV >= 8) {
				method = am.getClass().getMethod("killBackgroundProcesses", String.class);
				Log.i("KillApplication", "SDK=[" + sdkV + "] Method:killBackgroundProcesses");
			} else {
				method = am.getClass().getMethod("restartPackage", String.class);
				Log.i("KillApplication", "SDK=[" + sdkV + "] Method:restartPackage");
			}

		} catch (SecurityException e) {

			e.printStackTrace();
		} catch (NoSuchMethodException e) {

			e.printStackTrace();
		}

		if (method != null) {
			try {
				method.invoke(am, packageName);
				ret = true;
			} catch (IllegalArgumentException e) {

				e.printStackTrace();
			} catch (IllegalAccessException e) {

				e.printStackTrace();
			} catch (InvocationTargetException e) {

				e.printStackTrace();
			}

		}

		// android.os.Process.killProcess(android.os.Process.myPid());
		return ret;
	}
}
