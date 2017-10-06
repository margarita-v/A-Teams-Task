package com.margarita.a_teams_task.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.margarita.a_teams_task.R;

public class InfoViewHolder extends RecyclerView.ViewHolder {

    private TextView tvInfo;

    public InfoViewHolder(View itemView) {
        super(itemView);
        this.tvInfo = itemView.findViewById(R.id.tvInfo);
    }

    public void setText(String text) {
        this.tvInfo.setText(text);
    }
}
