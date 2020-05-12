package com.bawei.dianshangjinmonth01.presenter;

import com.bawei.dianshangjinmonth01.base.BasePresenter;
import com.bawei.dianshangjinmonth01.contract.IContract;

import io.reactivex.Observable;

/**
 * 学生信息请求Presenter
 * 李易泽
 * 20200504
 */
public class StudentInfoPresenter extends BasePresenter {
    //方法实现
    public StudentInfoPresenter(IContract.IView iView) {
        super(iView);
    }
    @Override
    protected Observable getObservable() {
        return iRequest.requestClassInfo();
    }
}
