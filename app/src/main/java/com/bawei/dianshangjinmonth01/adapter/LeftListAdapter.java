package com.bawei.dianshangjinmonth01.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.dianshangjinmonth01.R;
import com.bawei.dianshangjinmonth01.bean.CategoryBean;
import com.bawei.dianshangjinmonth01.bean.ChildsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LeftListAdapter extends RecyclerView.Adapter<LeftListAdapter.MyViewHouler> {
    //定义
    private Unbinder unbinder;
    private List<CategoryBean> list = new ArrayList<>();
    private OnClickBack onClickBack;
    public void setOnClickBack(OnClickBack onClickBack) {
        this.onClickBack = onClickBack;
    }
    //封装添加方法
    public void addListData(List<CategoryBean> list){
        this.list.addAll(list);
    }
    @NonNull
    @Override
    public LeftListAdapter.MyViewHouler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycontentes1, parent, false);
        return new LeftListAdapter.MyViewHouler(view);
    }
    @Override
    public void onBindViewHolder(@NonNull LeftListAdapter.MyViewHouler holder, int position) {
        CategoryBean categoryBean = list.get(position);
        holder.clazz.setText(categoryBean.getClazz());
        holder.itemView.setTag(categoryBean);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoryBean categoryBean = (CategoryBean) v.getTag();
                if(onClickBack != null){
                    onClickBack.clickBack(categoryBean.getChilds(),categoryBean.getClazz());
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    //寄存器
    public class MyViewHouler extends RecyclerView.ViewHolder {
        @BindView(R.id.clazz)
        protected TextView clazz;
        public MyViewHouler(@NonNull View itemView) {
            super(itemView);
            //绑定
            unbinder = ButterKnife.bind(this, itemView);
        }
    }
    //释放资源
    public void destroy(){
        list.clear();
        unbinder.unbind();
    }
    //定义点击回调
    public interface OnClickBack {
        void clickBack(List<ChildsBean> childsBeans,String cls);
    }
}
