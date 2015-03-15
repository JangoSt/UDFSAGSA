package com.wirundich.kalorienrechner.Components;

/**
 * Created by Matze on 12.03.2015.
*/
        import android.content.ClipData;
        import android.content.Context;
        import android.content.res.TypedArray;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.BlurMaskFilter;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.Rect;
        import android.graphics.drawable.ColorDrawable;
        import android.graphics.drawable.Drawable;
        import android.util.AttributeSet;
        import android.util.Log;
        import android.view.MotionEvent;
        import android.widget.SeekBar;

        import com.wirundich.kalorienrechner.R;

public class VerticalSeekBar extends SeekBar {
    private int SLIDERUP_HIGHT = 100;
//    public VerticalSeekBar(Context context) {
//        super(context);
//
//    }
//    public VerticalSeekBar(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//
//    }
    boolean mshowText;
    int mTextPos;
    Paint mTextPaint,mPiePaint,mShadowPaint, mBitMapColor  ;
    float mTextHeight;
    Color floatColor;
    Bitmap slider;
    public VerticalSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.VerticalSeekBar,
                0,0
        );
        try{
            mshowText = a.getBoolean(R.styleable.VerticalSeekBar_showTextBar, false);
            mTextPos = a.getInteger(R.styleable.VerticalSeekBar_labelPosition, 0);
        }finally {
            a.recycle();
        }
        init();
        setProgressDrawable(new ColorDrawable(android.R.color.transparent));

    }
    private void init(){
        Bitmap bitmapTemp = BitmapFactory.decodeResource(getResources(),R.drawable.sliderup);
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(getResources().getColor(R.color.AppThemeColor));
        mTextPaint.setTextSize(40);
        floatColor = new Color();
        slider = Bitmap.createScaledBitmap(bitmapTemp,100,SLIDERUP_HIGHT,true);
        bitmapTemp.recycle();
//
//        if(mTextHeight == 0){
//            mTextHeight = mTextPaint.getTextSize();
//        }
//        else
//            mTextPaint.setTextSize(mTextHeight);
//        mPiePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPiePaint.setStyle(Paint.Style.FILL);
//        mPiePaint.setTextSize(40);
//
        mShadowPaint = new Paint(0);
        mShadowPaint.setColor(0xff101010);
        mShadowPaint.setMaskFilter( new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL));

    }


    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }
    protected void onDraw(Canvas c) {

        c.rotate(-90);
        c.translate(-getHeight(), 0);
        super.onDraw(c);
        int yValue = (int)((double)(getHeight()-getThumbOffset())/getMax()*getProgress());

        mShadowPaint.setColor(Color.argb(100,(int)((double)255/getMax()*getProgress()),(int)(255-(double)255/getMax()*getProgress()),0));
        c.drawRect(0,0,yValue,getWidth(),mShadowPaint);
        c.rotate(90);
        c.drawBitmap(slider,getWidth()/2-(slider.getWidth()/2),-yValue-(slider.getHeight()),mBitMapColor);

//        c.rotate(90);
//        c.drawText(this.getProgress()+"",0,-yValue,mTextPaint);


//        c.drawOval(
//                mShadowBounds,
//                mShadowPaint
//        );
//
//        // Draw the label text
//        c.drawText(mData.get(mCurrentItem).mLabel, mTextX, mTextY, mTextPaint);
//
//        // Draw the pie slices
//        for (int i = 0; i < mData.size(); ++i) {
//            ClipData.Item it = mData.get(i);
//            mPiePaint.setShader(it.mShader);
//            c.drawArc(mBounds,
//                    360 - it.mEndAngle,
//                    it.mEndAngle - it.mStartAngle,
//                    true, mPiePaint);
//        }
//
//        // Draw the pointer
//        canvas.drawLine(mTextX, mPointerY, mPointerX, mPointerY, mTextPaint);
//        canvas.drawCircle(mPointerX, mPointerY, mPointerSize, mTextPaint);
//    }
//
//        Paint paint = new Paint();
//        getY();
//        paint.setColor(getResources().getColor(R.color.AppThemeColor));
//        int yValue = (getHeight()-getThumbOffset())/getMax()*getProgress();
//       // Log.i("Canvas draw",c.getWidth()+" , "+c.getHeight()+" , "+getPaddingTop() +" , ");
//        c.rotate(90);


    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        super.onSizeChanged(w, h, oldw, oldh);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return false;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                int i=0;
                i=getMax() - (int) (getMax() * event.getY() / getHeight());
                setProgress(i);
               // Log.i("Progress",getProgress()+"");
                onSizeChanged(getWidth(), getHeight(), 0, 0);
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }
    public int getSide(){
        return mTextPos;
    }
}