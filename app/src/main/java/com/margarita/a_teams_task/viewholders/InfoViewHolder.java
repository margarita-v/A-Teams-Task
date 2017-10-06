package com.margarita.a_teams_task.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.margarita.a_teams_task.R;

public class InfoViewHolder extends RecyclerView.ViewHolder {

    private TextView tvInfo;

    public InfoViewHolder(View itemView) {
        super(itemView);
        tvInfo = (TextView) itemView.findViewById(R.id.tvInfo);
    }

    public TextView getTvInfo() {
        return tvInfo;
    }

    public void setTvInfo(TextView tvInfo) {
        this.tvInfo = tvInfo;
    }
}
