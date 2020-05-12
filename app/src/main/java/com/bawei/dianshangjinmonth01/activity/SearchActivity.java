package com.bawei.dianshangjinmonth01.activity;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.dianshangjinmonth01.R;
import com.bawei.dianshangjinmonth01.base.BaseActivity;
import com.bawei.dianshangjinmonth01.view.HistoryView;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {
    @BindView(R.id.search_text)
    protected EditText searchText;
    @BindView(R.id.histov)
    protected HistoryView histov;
    @Override
    protected boolean isFullScreen() {
        return false;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }
    @Override
    protected void initData() {
    }
    @OnClick(R.id.submit_search)
    protected void submitSearch(){
        String text = searchText.getText().toString();
        if(TextUtils.isEmpty(text)){
            Toast.makeText(this,"搜索框不能为空！",Toast.LENGTH_LONG).show();
        } else {
            histov.addHistoryView(text);
        }
    }
    @Override
    protected void initDestroy() {
    }
}
