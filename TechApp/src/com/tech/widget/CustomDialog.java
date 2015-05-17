package com.tech.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.tech.R;

public class CustomDialog extends Dialog {

	private TextView mText;
	private boolean mbBackKeyDown = false;
	private OnCustomDlgCancelListener mOnCustomDlgCancelListener;

	public CustomDialog(Context context, String text, OnCustomDlgCancelListener listener) {

		super(context, R.style.CustomDialog);
		setContentView(LayoutInflater.from(context).inflate(R.layout.widget_custom_dialog, null));
		mText = (TextView) findViewById(R.id.dlg_text);
		if (!TextUtils.isEmpty(text)) {
			mText.setText(text);
		}
		TextView tvCancel = (TextView) findViewById(R.id.dlg_cancel);
		tvCancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (mOnCustomDlgCancelListener != null) {
					mOnCustomDlgCancelListener.onCancel();
				}
				dismiss();
			}
		});
		mOnCustomDlgCancelListener = listener;
	}

	// 按键的处理
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			mbBackKeyDown = true;
			return true;
		}
		mbBackKeyDown = false;
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (mbBackKeyDown) {
				if (mOnCustomDlgCancelListener != null) {
					mOnCustomDlgCancelListener.onCancel();
				}
				dismiss();
			}
			mbBackKeyDown = false;
			return true;
		}
		mbBackKeyDown = false;
		return super.onKeyUp(keyCode, event);
	}

	public interface OnCustomDlgCancelListener {

		/**
		 * 
		 * 通过取消按钮或返回键取消对话框里回调
		 */
		public void onCancel();
	}
}
