package com.bawei.dianshangjinmonth01.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bawei.dianshangjinmonth01.R;
import com.bawei.dianshangjinmonth01.base.BaseActivity;

import butterknife.BindView;

public class SplashActivity extends BaseActivity {
    //定义
    @BindView(R.id.daowen)
    protected TextView daowen;
    private int len = 2;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0){
                if(len > 0){
                    len--;
                    daowen.setText(String.valueOf(len));
                    handler.sendEmptyMessageDelayed(0,1000);
                } else {
                    handler.removeCallbacksAndMessages(null);
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.start_anim,R.anim.end_anim);
                    finish();
                }
            }
        }
    };
    @Override
    protected boolean isFullScreen() {
        return true;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }
    @Override
    protected void initData() {
        handler.sendEmptyMessageDelayed(0,1000);
    }
    @Override
    protected void initDestroy() {
        handler.removeCallbacksAndMessages(null);
    }
}
