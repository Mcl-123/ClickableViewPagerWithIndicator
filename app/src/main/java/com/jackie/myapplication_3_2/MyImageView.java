package com.jackie.myapplication_3_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;


public class MyImageView extends android.support.v7.widget.AppCompatImageView {

    public float x;
    public float y;
    private Bitmap bitmap;
    private Paint paint;

    public MyImageView(Context context) {
        this(context, null);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);

        setAdjustViewBounds(true);
        setScaleType(ScaleType.CENTER_INSIDE);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.error_bug);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (x <= 0 && y <= 0) {
            return;
        }

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        canvas.drawBitmap(bitmap, x - width / 2, y - height / 2, paint);
    }

    public void update(float x, float y) {
        this.x = x;
        this.y = y;
        invalidate();
    }
}
