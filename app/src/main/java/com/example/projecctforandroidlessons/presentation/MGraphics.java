package com.example.projecctforandroidlessons.presentation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;

public class MGraphics extends View {

    private Paint mPaint = new Paint();

    public MGraphics(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);
        canvas.drawPaint(mPaint);

        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.YELLOW);
        canvas.drawCircle(950,30, 25, mPaint);

        mPaint.setColor(Color.GREEN);

        canvas.drawRect(20, 650, 950, 680,mPaint );

        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(32);
        canvas.drawText("Hello dear, Bob! ", 30, 648, mPaint);

        int x = 810;
        int y = 190;

        mPaint.setColor(Color.GRAY);
        mPaint.setTextSize(27);
        String textForRotateTextView = "here is rotated textView";

        canvas.rotate(-45, 50, 50);


        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText(textForRotateTextView, x, y, mPaint);


        canvas.restore();

        




    }
}
