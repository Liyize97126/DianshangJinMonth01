package com.bawei.dianshangjinmonth01.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bawei.dianshangjinmonth01.R;

/**
 * 自定义搜索框视图
 */
public class SearchView extends FrameLayout {
    private OnSearchGo onSearchGo;
    public void setOnSearchGo(OnSearchGo onSearchGo) {
        this.onSearchGo = onSearchGo;
    }
    public SearchView(@NonNull Context context) {
        super(context);
        initView();
    }
    public SearchView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    public SearchView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    private void initView(){
        View view = View.inflate(getContext(), R.layout.view_search, this);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onSearchGo != null){
                    onSearchGo.searchGo();
                }
            }
        });
    }
    public interface OnSearchGo{
        void searchGo();
    }
}
