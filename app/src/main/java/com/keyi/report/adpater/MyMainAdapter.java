package com.keyi.report.adpater;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.keyi.report.R;

import java.util.List;

/**
 * Created by Administrator on 2016/7/21.
 */
public class MyMainAdapter extends AnimRecyclerViewAdapter<MyMainAdapter.ViewHolder> {
    List<String> list;

    public MyMainAdapter(List<String> list) {
        this.list = list;
    }

    public interface OnRecyclerViewListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }
    private OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener){
        this.onRecyclerViewListener=onRecyclerViewListener;
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textView.setText(list.get(position));
        Log.e("list",list.get(position));
        showItemAnim(holder.itemView, position);
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
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
          textView = (TextView) itemView.findViewById(R.id.tv_list);
        }
    }
}
