package com.example.customextendingviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class CustomViewDrawing extends View {
    Paint p;
    Path mpath;
    Bitmap bimap_light;
    Bitmap bimap_dark;
    boolean b;
    Canvas c;

    int draw_dark = R.drawable.ic_delete_sweep_black_24dp;
    int draw_light = R.drawable.ic_delete_sweep_black_24dp_light;


    public CustomViewDrawing(Context context){
        super(context);
         bimap_dark =  getBitmapFromVectorDrawable(context,draw_dark);
         bimap_light = getBitmapFromVectorDrawable(context,draw_light);
        init();
    }


    public void init() {
        p = new Paint();
        mpath = new Path();
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(10f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        c = canvas;
        canvas.drawColor(Color.MAGENTA);
        canvas.drawPath(mpath,p);
        Bitmap bitmap_light = Bitmap.createScaledBitmap(bimap_light, 120, 120, false);
        Bitmap bitmap_dark = Bitmap.createScaledBitmap(bimap_dark, 120, 120, false);

        if(b)
        {
            canvas.drawBitmap(bitmap_dark,10,10,p);
        }
        else
            canvas.drawBitmap(bitmap_light,10,10,p);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                mpath.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                b=true;
                mpath.lineTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                int x = (int)event.getX();
                int y = (int)event.getY();
                if((x>= 10 & x<=110)&(y>= 10 & y<=120))
                {
                    b = false;
                    mpath.reset();
                }
        }
        invalidate();
       //
        return true;

    }


    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        drawable = (DrawableCompat.wrap(drawable)).mutate();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }
}
