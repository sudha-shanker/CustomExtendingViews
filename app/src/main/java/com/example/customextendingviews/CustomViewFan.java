package com.example.customextendingviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class CustomViewFan extends View {
    Paint p;
    int x=100;
    public CustomViewFan(Context context)
    {
        super(context);
        init();
    }

    public void init() {
        p = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.RED);
        p.setColor(Color.YELLOW);
        canvas.drawRect(100,100,500,500,p);

        p.setColor(Color.GREEN);
        canvas.drawArc(500,500,800,800,x,30,
                true,p);
        canvas.drawArc(500,500,800,800,x+120,
                30,true,p);
        canvas.drawArc(500,500,800,800, x+240,
                30,true,p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        for(int i=0;i<=50000;i++) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startFan();
                    break;
                case MotionEvent.ACTION_UP:
                    stopFan();
                    break;
            }
        }
        return true;
    }
    public void stopFan() {
    }
    public void startFan() {
        x = x+5;
        // invalidate() means redraw on screen  and results to a call of the view's onDraw() method
        invalidate();
    }
}
