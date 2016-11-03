package com.keyi.report.adpater;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.keyi.report.R;
import com.keyi.report.bean.FilterOrderSalesMsg;

import java.util.List;

/**
 * Created by Administrator on 2016/7/21.
 */
public class ShuaDanAdapter extends AnimRecyclerViewAdapter<ShuaDanAdapter.ViewHolder> {
    private List<FilterOrderSalesMsg.DataBean> dataBeans;

    public ShuaDanAdapter(List<FilterOrderSalesMsg.DataBean> dataBeans) {
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
                R.layout.list_item_shuadan, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mXuHao.setText(position+1+"");
        holder.mBianMa.setText(dataBeans.get(position).getOuterIid());
        holder.mGuiGe.setText(dataBeans.get(position).getOuterSkuId());
        holder.mDianpu.setText(dataBeans.get(position).getSellerNick());
        holder.mPayTime.setText(dataBeans.get(position).getPayTime());
        holder.mNum.setText(dataBeans.get(position).getNum());
        holder.mPayMoney.setText(dataBeans.get(position).getPayment());
        if(position%2 != 0){
            holder.itemView.setBackgroundColor(Color.argb(250, 255, 255, 255));
        }else{
            holder.itemView.setBackgroundColor(Color.argb(250 ,  224 ,  243 ,  250 ));
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
        public TextView mDianpu;
        public TextView mPayTime;
        public TextView mNum;
        public TextView mPayMoney;
        public ViewHolder(View itemView) {
            super(itemView);
            mXuHao = (TextView) itemView.findViewById(R.id.text_xuhao);
            mBianMa = (TextView) itemView.findViewById(R.id.text_bianma);
            mGuiGe = (TextView) itemView.findViewById(R.id.text_guige);
            mDianpu = (TextView) itemView.findViewById(R.id.text_dianpu);
            mPayTime = (TextView) itemView.findViewById(R.id.text_paytime);
            mNum = (TextView) itemView.findViewById(R.id.text_num);
            mPayMoney = (TextView) itemView.findViewById(R.id.text_paymoney);
        }
    }
}
