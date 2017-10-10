package com.margarita.a_teams_task.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.margarita.a_teams_task.R;

public class InfoViewHolder extends RecyclerView.ViewHolder {

    private TextView tvName;
    private TextView tvInfo;

    public InfoViewHolder(View itemView) {
        super(itemView);
        this.tvName = itemView.findViewById(R.id.tvName);
        this.tvInfo = itemView.findViewById(R.id.tvInfo);
    }

    public void setName(String name) {
        this.tvName.setText(name);
    }

    public void setInfo(String info) {
        this.tvInfo.setText(info);
    }
}
