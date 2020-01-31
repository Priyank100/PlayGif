package com.priyank.playgif;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import androidx.appcompat.widget.AppCompatImageButton;

import android.util.AttributeSet;

public class GifImageButton extends AppCompatImageButton {

	private boolean mFreezesAnimation;


	public GifImageButton(Context context) {
		super(context);
	}


	public GifImageButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		postInit(GifViewUtils.initImageView(this, attrs, 0, 0));
	}

	public GifImageButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		postInit(GifViewUtils.initImageView(this, attrs, defStyle, 0));
	}

	private void postInit(GifViewUtils.GifImageViewAttributes result) {
		mFreezesAnimation = result.freezesAnimation;
		if (result.mSourceResId > 0) {
			super.setImageResource(result.mSourceResId);
		}
		if (result.mBackgroundResId > 0) {
			super.setBackgroundResource(result.mBackgroundResId);
		}
	}

	@Override
	public void setImageURI(Uri uri) {
		if (!GifViewUtils.setGifImageUri(this, uri)) {
			super.setImageURI(uri);
		}
	}

	@Override
	public void setImageResource(int resId) {
		if (!GifViewUtils.setResource(this, true, resId)) {
			super.setImageResource(resId);
		}
	}

	@Override
	public void setBackgroundResource(int resId) {
		if (!GifViewUtils.setResource(this, false, resId)) {
			super.setBackgroundResource(resId);
		}
	}

	@Override
	public Parcelable onSaveInstanceState() {
		Drawable source = mFreezesAnimation ? getDrawable() : null;
		Drawable background = mFreezesAnimation ? getBackground() : null;
		return new GifViewSavedState(super.onSaveInstanceState(), source, background);
	}

	@Override
	public void onRestoreInstanceState(Parcelable state) {
		if (!(state instanceof GifViewSavedState)) {
			super.onRestoreInstanceState(state);
			return;
		}
		GifViewSavedState ss = (GifViewSavedState) state;
		super.onRestoreInstanceState(ss.getSuperState());
		ss.restoreState(getDrawable(), 0);
		ss.restoreState(getBackground(), 1);
	}

	public void setFreezesAnimation(boolean freezesAnimation) {
		mFreezesAnimation = freezesAnimation;
	}
}
