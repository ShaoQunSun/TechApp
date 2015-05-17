package com.tech.activity.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.tech.widget.CustomDialog;
import com.tech.widget.CustomDialog.OnCustomDlgCancelListener;

/**
 * 同步课堂页面基类
 * 
 * @author Sam
 * @date 2015-5-17 下午4:18:59
 */
public class ActivityTechBase extends ActivityBase {

	protected ActionBar mActionBar; // 导航栏
	private CustomDialog mCustomDialog; // 自定义对话框

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		mActionBar = getSupportActionBar();

		if (null != mActionBar) {

			mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
			mActionBar.setDisplayShowHomeEnabled(true);
			mActionBar.setDisplayHomeAsUpEnabled(true);
			mActionBar.setDisplayShowTitleEnabled(true);
			mActionBar.setDisplayShowCustomEnabled(true);
			mActionBar.setDisplayUseLogoEnabled(true);
		}
	}

	@Override
	protected void onResume() {

		super.onResume();

		setOrientation();
	}

	protected void setOrientation() {

		/**
		 * 设置屏幕为竖屏
		 */
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

	@Override
	protected void requestFeature() {

		// requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	@Override
	public void refreshContent() {

		// TODO Auto-generated method stub
	}

	/**
	 * 显示自定义对话框
	 * 
	 * @param context
	 * @param text
	 * @param listener
	 */
	public void showCustomDialog(Context context, String text, OnCustomDlgCancelListener listener) {

		if (null != mCustomDialog) {

			mCustomDialog.dismiss();
			mCustomDialog = null;
		}

		mCustomDialog = new CustomDialog(context, text, listener);
		mCustomDialog.show();
	}

	/**
	 * 关闭自定义对话框
	 */
	public void closeCustomDialog() {

		if (null != mCustomDialog) {

			mCustomDialog.dismiss();
			mCustomDialog = null;
		}
	}
}
