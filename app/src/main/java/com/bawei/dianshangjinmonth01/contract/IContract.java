package com.bawei.dianshangjinmonth01.contract;

import com.bawei.dianshangjinmonth01.bean.DataBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 契约类
 */
public interface IContract {
    interface IView{
        void success(DataBean dataBean);
        void fail(String err);
    }
    interface IRequest{
        @GET("api/student/class_student.json")
        Observable<DataBean> requestClassInfo();
    }
}
