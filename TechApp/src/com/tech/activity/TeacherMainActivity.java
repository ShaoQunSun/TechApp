package com.tech.activity;

import android.os.Bundle;

import com.tech.R;
import com.tech.activity.base.ActivityTechBase;

/**
 * 老师首页
 * 
 * @author Sam
 * @date 2015-5-16 下午5:45:27
 */
public class TeacherMainActivity extends ActivityTechBase {

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		initComponents();// 初始化界面元素
	}

	/**
	 * 初始化界面元素
	 */
	private void initComponents() {

		setContentView(R.layout.activity_teacher_main);
	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
	}

}
