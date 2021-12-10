package com.transcendence.petrichor.ui.setting.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.transcendence.core.utils.L;
import com.transcendence.petrichor.R;
import com.transcendence.petrichor.ui.setting.bean.LanguageBean;

import java.util.ArrayList;
import java.util.List;

public class LanguageSetAdapter extends RecyclerView.Adapter<LanguageSetAdapter.LanguageSetViewHolder> {

    private Context mContext;
    private List<LanguageBean> sourceList;
    private LanguageSetAdapterOnClick listener;
    private int mSelectPos = 0;
    private boolean mIsClick;

    public LanguageSetAdapter(Context context, List<LanguageBean> data) {
        mContext = context;
        sourceList = data;
    }

    @NonNull
    @Override
    public LanguageSetAdapter.LanguageSetViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_wan_setting_language_rv_item, viewGroup, false);
        return new LanguageSetViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull LanguageSetViewHolder viewHolder, int position) {
        if (viewHolder instanceof LanguageSetViewHolder) {
            LanguageSetViewHolder holder = viewHolder;
            holder.tvLanguage.setText(sourceList.get(position).getDesc());
            if (mSelectPos == holder.getAdapterPosition() && mIsClick) {
                holder.iv.setVisibility(View.VISIBLE);
            } else {
                holder.iv.setVisibility(View.INVISIBLE);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!mIsClick) {
                        listener.onLanItemClick(sourceList.get(holder.getAdapterPosition()),holder.getAdapterPosition(), true);
                    } else {
                        listener.onLanItemClick(sourceList.get(holder.getAdapterPosition()),holder.getAdapterPosition(), mSelectPos != holder.getAdapterPosition());
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return sourceList == null ? 0 : sourceList.size();
    }

    //底部上拉刷新，数据直接在底部显示
    public void loadMore(List<LanguageBean> data) {
        sourceList.addAll(data);
        notifyDataSetChanged();
    }

    //底部下拉刷新，数据直接从上往下添加数据，显示在顶部
    public void refreshData(List<LanguageBean> data) {
        sourceList.addAll(0, data);
        notifyDataSetChanged();
//            notifyItemInserted(0); 一次只能加一项数据
    }

    public void setSelectPos(int selectPos, boolean isClick) {
        this.mSelectPos = selectPos;
        this.mIsClick = isClick;
    }


    public class LanguageSetViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tvLanguage;

        private LanguageSetViewHolder(View view) {
            super(view);
            iv = view.findViewById(R.id.iv);
            tvLanguage = view.findViewById(R.id.tv_language);
        }
    }

    public interface LanguageSetAdapterOnClick {
        void onLanItemClick(LanguageBean bean,int position, boolean isClick);
    }

    public void setListener(LanguageSetAdapterOnClick listener) {
        this.listener = listener;
    }
}
