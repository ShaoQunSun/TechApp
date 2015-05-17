package com.tech.utils;

import java.io.IOException;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.TextUtils;

/**
 * 媒体播放工具类
 * 
 * @author Sam
 * @date 2015-5-17 下午2:52:26
 */
public class MediaPlayerUtils {

	private MediaPlayer mMediaPlayer; // MediaPlayer实例
	private Context mContext; // 上下文
	private static MediaPlayerUtils mInstance = null; // 媒体播放工具类实例

	private MediaPlayerUtils(Context cxt) {

		this.mContext = cxt;
		this.mMediaPlayer = new MediaPlayer();
	}

	public static MediaPlayerUtils newInstance(Context cxt) {

		if (null == mInstance) {

			mInstance = new MediaPlayerUtils(cxt);
		}

		return mInstance;
	}

	/**
	 * 播放资源音频文件
	 * 
	 * @param sound
	 */
	public void playSound(int sound) {

		if (sound == 0 || sound == -1)
			return;

		MediaPlayer player = MediaPlayer.create(mContext, sound);

		player.start();
	}

	/**
	 * 播放文件系统音频文件
	 * 
	 * @param path
	 */
	public void playSound(String path) {

		if (TextUtils.isEmpty(path))
			return;

		try {
			mMediaPlayer.setDataSource(path);
			mMediaPlayer.prepare();
			mMediaPlayer.start();
		} catch (IllegalArgumentException e) {

			e.printStackTrace();
		} catch (SecurityException e) {

			e.printStackTrace();
		} catch (IllegalStateException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * 播放网络音频
	 * 
	 * @param url
	 */
	public void playSoundWithUrl(String url) {

		if (TextUtils.isEmpty(url))
			return;

		Uri uri = Uri.parse(url);
		MediaPlayer player = MediaPlayer.create(mContext, uri);

		player.start();
	}
}
