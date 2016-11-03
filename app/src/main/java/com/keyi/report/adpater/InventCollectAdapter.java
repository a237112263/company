package com.keyi.report.adpater;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.utils.EmptyUtils;
import com.keyi.report.R;
import com.keyi.report.bean.InventCollectMsg;

import java.util.List;

/**
 * Created by Administrator on 2016/7/21.
 */
public class InventCollectAdapter extends AnimRecyclerViewAdapter<InventCollectAdapter.ViewHolder> {
    private List<InventCollectMsg.DataBean> dataBeans;

    public InventCollectAdapter( List<InventCollectMsg.DataBean> dataBeans) {
        this.dataBeans = dataBeans;
    }

    public interface OnRecyclerViewListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    private OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener){
        this.onRecyclerViewListener=onRecyclerViewListener;
    }
    @Override
    public int getItemCount() {
        return dataBeans.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item1, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mXuHao.setText(position+1+"");
        holder.mBianMa.setText(dataBeans.get(position).getOuterIid());
        holder.mGuiGe.setText(dataBeans.get(position).getOuterSkuId());
        if (EmptyUtils.isEmpty(dataBeans.get(position).getUseQty())||dataBeans.get(position).getUseQty()==0){
            holder.mKuYongKuCun.setText("0");
            holder.mKuYongKuCun.setTextColor(Color.rgb(0, 255, 128));
        }else {
            if (dataBeans.get(position).getUseQty()<0){
                holder.mKuYongKuCun.setTextColor(Color.rgb(255, 0, 0));
            }else {
                holder.mKuYongKuCun.setTextColor(Color.rgb(0, 0, 0));
            }
            holder.mKuYongKuCun.setText(""+dataBeans.get(position).getUseQty());
        }
        if (EmptyUtils.isEmpty(dataBeans.get(position).getAlarmCountSupp())||dataBeans.get(position).getAlarmCountSupp().toString().equals("0")){
            holder.mYuJingKuCun.setText("0");
            holder.mYuJingKuCun.setTextColor(Color.rgb(0, 255, 128));
        }else {
            if (Integer.parseInt(dataBeans.get(position).getAlarmCountSupp().toString())<0){
                holder.mYuJingKuCun.setTextColor(Color.rgb(255, 0, 0));
            }else {
                holder.mYuJingKuCun.setTextColor(Color.rgb(0, 0, 0));
            }
            holder.mYuJingKuCun.setText(dataBeans.get(position).getAlarmCountSupp().toString());
        }
        if (onRecyclerViewListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=holder.getLayoutPosition();
                    onRecyclerViewListener.onItemClick(holder.itemView,pos);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onRecyclerViewListener.onItemLongClick(holder.itemView,position);
                    return false;
                }
            });
        }

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mXuHao;
        public TextView mBianMa;
        public TextView mGuiGe;
        public TextView mKuYongKuCun;
        public TextView mYuJingKuCun;
        public ViewHolder(View itemView) {
            super(itemView);
            mXuHao = (TextView) itemView.findViewById(R.id.text_name0);
            mBianMa = (TextView) itemView.findViewById(R.id.text_name);
            mGuiGe = (TextView) itemView.findViewById(R.id.text_name1);
            mKuYongKuCun = (TextView) itemView.findViewById(R.id.text_sex);
            mYuJingKuCun = (TextView) itemView.findViewById(R.id.text_age);
        }
    }
}
