package com.bawei.dianshangjinmonth01.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.dianshangjinmonth01.R;
import com.bawei.dianshangjinmonth01.bean.ChildsBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RightListAdapter extends RecyclerView.Adapter<RightListAdapter.MyViewHouler> {
    //定义
    private Unbinder unbinder;
    private List<ChildsBean> list = new ArrayList<>();
    //封装添加方法
    public void addListData(List<ChildsBean> list){
        this.list.addAll(list);
    }
    //封装清除方法
    public void clearListData(){
        list.clear();
    }
    @NonNull
    @Override
    public RightListAdapter.MyViewHouler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycontentes2, parent, false);
        return new MyViewHouler(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RightListAdapter.MyViewHouler holder, int position) {
        ChildsBean childsBean = list.get(position);
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.pict_zhan)
                .error(R.drawable.pict_zhan)
                .fallback(R.drawable.pict_zhan)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)));
        Glide.with(holder.avatar.getContext())
                .applyDefaultRequestOptions(requestOptions)
                .load(childsBean.getAvatar())
                .into(holder.avatar);
        holder.name.setText(childsBean.getName());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    //寄存器
    public class MyViewHouler extends RecyclerView.ViewHolder {
        @BindView(R.id.avatar)
        protected ImageView avatar;
        @BindView(R.id.name)
        protected TextView name;
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
}
