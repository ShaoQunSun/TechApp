package com.tech.activity;

import android.os.Bundle;

import com.tech.R;
import com.tech.activity.base.ActivityTechBase;

/**
 * 父母首页
 * 
 * @author Sam
 * @date 2015-5-16 下午5:45:40
 */
public class ParentsMainActivity extends ActivityTechBase {

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		initComponents();// 初始化界面元素
	}

	/**
	 * 初始化界面元素
	 */
	private void initComponents() {

		setContentView(R.layout.activity_parents_main);
	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
	}

}
