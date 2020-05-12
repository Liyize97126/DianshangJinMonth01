package com.bawei.dianshangjinmonth01.base;

import com.bawei.dianshangjinmonth01.bean.DataBean;
import com.bawei.dianshangjinmonth01.contract.IContract;
import com.bawei.dianshangjinmonth01.util.RetrofitUtil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Presenter基类
 * 李易泽
 * 20200504
 */
public abstract class BasePresenter {
    //定义
    private IContract.IView iView;
    protected IContract.IRequest iRequest;
    //构造
    public BasePresenter(IContract.IView iView) {
        this.iView = iView;
        iRequest = RetrofitUtil.getRetrofitUtil().create(IContract.IRequest.class);
    }
    //请求方法
    public void request(){
        //发起请求
        getObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DataBean>() {
                    @Override
                    public void accept(DataBean dataBean) throws Exception {
                        //反馈
                        iView.success(dataBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //反馈
                        throwable.printStackTrace();
                        iView.fail(throwable.getMessage());
                    }
                });
    }
    //释放资源
    public void destroy(){
        if(iView != null){
            iView = null;
        }
    }
    //方法封装
    protected abstract Observable getObservable();
}
