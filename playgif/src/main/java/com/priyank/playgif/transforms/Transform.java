package com.priyank.playgif.transforms;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Interface to support clients performing custom transformations before the current GIF Bitmap is drawn.
 */
public interface Transform {

	void onBoundsChange(Rect bounds);

	void onDraw(Canvas canvas, Paint paint, Bitmap buffer);
}
