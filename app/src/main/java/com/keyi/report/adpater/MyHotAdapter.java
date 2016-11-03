package com.keyi.report.adpater;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.keyi.report.R;
import com.keyi.report.bean.HotShop;

/**
 * Created by Administrator on 2016/7/21.
 */
public class MyHotAdapter extends AnimRecyclerViewAdapter<MyHotAdapter.ViewHolder> {
    HotShop hotShop;

    public MyHotAdapter(HotShop hotShop) {
        this.hotShop = hotShop;
    }

    public interface OnRecyclerViewListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    @Override
    public int getItemCount() {
        return hotShop.getData().size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_hot_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textView.setText("第" + (position + 1) + "名");
        holder.textView2.setText(hotShop.getData().get(position).getPayment() + "");
        holder.textView1.setText(hotShop.getData().get(position).getSellerNick());
        showItemAnim(holder.itemView, position);
        if (onRecyclerViewListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    onRecyclerViewListener.onItemClick(holder.itemView, pos);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onRecyclerViewListener.onItemLongClick(holder.itemView, position);
                    return false;
                }
            });
        }

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView1;
        TextView textView2;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv1_listview_hot);
            textView1 = (TextView) itemView.findViewById(R.id.tv2_listview_hot);
            textView2 = (TextView) itemView.findViewById(R.id.tv3_listview_hot);
        }
    }
}
