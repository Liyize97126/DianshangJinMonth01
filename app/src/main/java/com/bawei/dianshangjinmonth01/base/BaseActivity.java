package com.bawei.dianshangjinmonth01.base;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 页面基类
 * 李易泽
 * 20200504
 */
public abstract class BaseActivity extends AppCompatActivity {
    //定义
    private Unbinder unbinder;
    //页面创建
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断是否需要全屏页面
        if(isFullScreen()){
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        //加载视图
        setContentView(getLayoutId());
        //绑定ButterKnife
        unbinder = ButterKnife.bind(this);
        //界面效果
        getSupportActionBar().hide();
        //初始化数据
        initData();
    }
    //释放资源
    @Override
    protected void onDestroy() {
        super.onDestroy();
        initDestroy();
        unbinder.unbind();
    }
    //方法封装
    protected abstract boolean isFullScreen();
    protected abstract int getLayoutId();
    protected abstract void initData();
    protected abstract void initDestroy();
}
