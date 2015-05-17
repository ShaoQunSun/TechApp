package com.tech.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;


public class AnimImageView extends TextView {
    
    private static final int TYPE_BASE = 0;
    public static final int TYPE_LEFT = TYPE_BASE;
    public static final int TYPE_RIGHT = TYPE_BASE + 1;
    
    
    private AnimationDrawable mAnimationDrawableLeft;
    private AnimationDrawable mAnimationDrawableRight;
    private int mType = TYPE_LEFT;
    
    public AnimImageView(Context context) {
        super(context);
    }

    public AnimImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void initDrawable(AnimationDrawable animate, int [] resArray) {
       if (null == animate || null == resArray || 0 == resArray.length) {
           return;
       }
       
       for (int res : resArray) {
           Drawable drawable = getResources().getDrawable(res);
           drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
           animate.addFrame(drawable, 300);
       }
    }
    
    public void setDrawableByType(int type, int [] resArray) {
        if (null == resArray) {
            return;
        }
        
        setType(type);
        switch (mType) {
            case TYPE_LEFT:
                if (null == mAnimationDrawableLeft) {
                    mAnimationDrawableLeft = new AnimationDrawable();
                    initDrawable(mAnimationDrawableLeft, resArray);
                    mAnimationDrawableLeft.setOneShot(false);
                    mAnimationDrawableLeft.setVisible(true, true);
                }
                break;
            case TYPE_RIGHT:
                if (null == mAnimationDrawableRight) {
                    mAnimationDrawableRight = new AnimationDrawable();
                    initDrawable(mAnimationDrawableRight, resArray);
                    mAnimationDrawableRight.setOneShot(false);
                    mAnimationDrawableRight.setVisible(true, true);
                }
                break;
            default:
                break;
        }
    }
    
    public void setType(int type) {
        mType = type;
    }
    
    private Drawable mDefaultDrawable = null;
    
    public void start() {
        Drawable[] drawables = null;
        drawables = getCompoundDrawables();
        switch (mType) {
            case TYPE_LEFT:
                mDefaultDrawable = drawables[0];
                setCompoundDrawablesWithIntrinsicBounds(mAnimationDrawableLeft, drawables[1], drawables[2], drawables[3]);
                mAnimationDrawableLeft.start();
                break;
            case TYPE_RIGHT:
                mDefaultDrawable = drawables[2];
                super.setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], mAnimationDrawableRight, drawables[3]);
                mAnimationDrawableRight.start();
                break;
            default:
                break;
        }
    }
    
    public void stop() {
        Drawable[] drawables = null;
        drawables = getCompoundDrawables();
        switch (mType) {
            case TYPE_LEFT:
                setCompoundDrawablesWithIntrinsicBounds(mDefaultDrawable, drawables[1], drawables[2], drawables[3]);
                mAnimationDrawableLeft.stop();
                break;
            case TYPE_RIGHT:
                setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], mDefaultDrawable, drawables[3]);
                mAnimationDrawableRight.stop();
                break;
            default:
                break;
        }
    }
}
