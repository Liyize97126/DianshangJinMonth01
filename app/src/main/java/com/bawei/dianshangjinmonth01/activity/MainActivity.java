package com.bawei.dianshangjinmonth01.activity;

import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.dianshangjinmonth01.R;
import com.bawei.dianshangjinmonth01.adapter.LeftListAdapter;
import com.bawei.dianshangjinmonth01.adapter.RightListAdapter;
import com.bawei.dianshangjinmonth01.base.BaseActivity;
import com.bawei.dianshangjinmonth01.bean.ChildsBean;
import com.bawei.dianshangjinmonth01.bean.DataBean;
import com.bawei.dianshangjinmonth01.bean.SaveInfoBean;
import com.bawei.dianshangjinmonth01.contract.IContract;
import com.bawei.dianshangjinmonth01.dao.DaoMaster;
import com.bawei.dianshangjinmonth01.dao.SaveInfoBeanDao;
import com.bawei.dianshangjinmonth01.presenter.StudentInfoPresenter;
import com.bawei.dianshangjinmonth01.util.RetrofitUtil;
import com.bawei.dianshangjinmonth01.view.SearchView;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    //定义
    @BindView(R.id.classnum)
    protected TextView classnum;
    @BindView(R.id.recycls)
    protected RecyclerView recycls;
    @BindView(R.id.recychr)
    protected RecyclerView recychr;
    @BindView(R.id.searchv)
    protected SearchView searchv;
    private LeftListAdapter leftListAdapter;
    private RightListAdapter rightListAdapter;
    private StudentInfoPresenter studentInfoPresenter;
    private SaveInfoBeanDao saveInfoBeanDao;
    @Override
    protected boolean isFullScreen() {
        return false;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
    @Override
    protected void initData() {
        //初始化对象
        searchv.setOnSearchGo(new SearchView.OnSearchGo() {
            @Override
            public void searchGo() {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        recycls.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recychr.setLayoutManager(new GridLayoutManager(this,3));
        leftListAdapter = new LeftListAdapter();
        rightListAdapter = new RightListAdapter();
        recycls.setAdapter(leftListAdapter);
        recychr.setAdapter(rightListAdapter);
        leftListAdapter.setOnClickBack(new LeftListAdapter.OnClickBack() {
            @Override
            public void clickBack(List<ChildsBean> childsBeans, String cls) {
                rightListAdapter.clearListData();
                rightListAdapter.addListData(childsBeans);
                classnum.setText(cls);
                rightListAdapter.notifyDataSetChanged();
            }
        });
        saveInfoBeanDao = DaoMaster.newDevSession(this,SaveInfoBeanDao.TABLENAME).getSaveInfoBeanDao();
        studentInfoPresenter = new StudentInfoPresenter(new IContract.IView() {
            @Override
            public void success(DataBean dataBean) {
                //保存数据
                String toJson = new Gson().toJson(dataBean);
                saveInfoBeanDao.insertOrReplaceInTx(new SaveInfoBean(1,toJson));
                leftListAdapter.addListData(dataBean.getCategory());
                rightListAdapter.addListData(dataBean.getCategory().get(0).getChilds());
                classnum.setText(dataBean.getCategory().get(0).getClazz());
                leftListAdapter.notifyDataSetChanged();
                rightListAdapter.notifyDataSetChanged();
            }
            @Override
            public void fail(String err) {
                Toast.makeText(MainActivity.this,"发生错误了！\n" + err,Toast.LENGTH_LONG).show();
            }
        });
        //判断数据库
        SaveInfoBean unique = saveInfoBeanDao.queryBuilder().where(SaveInfoBeanDao.Properties.Id.eq(1)).unique();
        if(unique != null){
            DataBean dataBean = new Gson().fromJson(unique.getJsonData(), DataBean.class);
            leftListAdapter.addListData(dataBean.getCategory());
            rightListAdapter.addListData(dataBean.getCategory().get(0).getChilds());
            classnum.setText(dataBean.getCategory().get(0).getClazz());
            leftListAdapter.notifyDataSetChanged();
            rightListAdapter.notifyDataSetChanged();
            Toast.makeText(this,"已加载缓存数据！",Toast.LENGTH_LONG).show();
        } else {
            //判断网络
            if(RetrofitUtil.getRetrofitUtil().hasNet(this)){
                studentInfoPresenter.request();
            } else {
                Toast.makeText(this,"设备无网络！",Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    protected void initDestroy() {
        if(studentInfoPresenter != null){
            studentInfoPresenter.destroy();
            studentInfoPresenter = null;
        }
    }
}
