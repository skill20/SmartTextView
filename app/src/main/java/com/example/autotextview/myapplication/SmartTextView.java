package com.example.autotextview.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * @author qingqing.wang
 * @version 1.0.0
 * @date 2015-12-14 14:37
 * @since 5.3.0
 */
public class SmartTextView extends TextView {

    public SmartTextView(Context context) {
        this(context, null);
    }

    public SmartTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public SmartTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //设置默认字体大小，12sp。
        setTextSize(12);
    }


    private void measureText(String text, int width) {

        TextPaint paint = getPaint();

        if (width > 0) {
            //控件的长度
            int maxWidth = width - this.getPaddingLeft()
                    - this.getPaddingRight();
            paint.setTextSize(getTextSize());

            //测量string的长度
            float cWidth = paint.measureText(text);
            float cTextSize = getTextSize();

            //string长度跟控件长度对比
            while (cWidth > maxWidth) {
                cTextSize = cTextSize - 1;
                paint.setTextSize(cTextSize);
                cWidth = paint.measureText(text);
            }
            setTextSize(TypedValue.COMPLEX_UNIT_PX, cTextSize);
            //不重新设置一下会有问题，还不清楚具体原因
            setText(text);
        }
    }


    String mTemp;

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        if (!TextUtils.equals(mTemp, getText().toString()))
            measureText(getText().toString(), getWidth());
        mTemp = getText().toString();
    }
}
