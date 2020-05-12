package com.bawei.dianshangjinmonth01.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bawei.dianshangjinmonth01.R;

/**
 * 历史记录展示
 */
public class HistoryView extends FrameLayout {
    public HistoryView(@NonNull Context context) {
        super(context);
    }
    public HistoryView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public HistoryView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //定义
        int sumWidth = 0;
        int width = getWidth();
        int lines = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if(sumWidth + view.getWidth() > width){
                lines++;
                sumWidth = 0;
            }
            //设置布局
            view.layout(sumWidth,lines*view.getHeight(),sumWidth + view.getWidth(),lines*view.getHeight() + view.getHeight());
            sumWidth += view.getWidth();
        }
    }
    public void addHistoryView(String text){
        TextView textView = (TextView) View.inflate(getContext(), R.layout.view_history,null);
        textView.setText(text);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(textView,layoutParams);
    }
}
