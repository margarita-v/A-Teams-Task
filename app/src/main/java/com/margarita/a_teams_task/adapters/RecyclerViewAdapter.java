package com.margarita.a_teams_task.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.margarita.a_teams_task.R;
import com.margarita.a_teams_task.models.EchoJson;
import com.margarita.a_teams_task.models.Validation;
import com.margarita.a_teams_task.models.base.BaseModel;
import com.margarita.a_teams_task.viewholders.FormViewHolder;
import com.margarita.a_teams_task.viewholders.InfoViewHolder;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<BaseModel> items;
    private final int INFO_ID = 0, FORM_ID = 1;

    public RecyclerViewAdapter(List<BaseModel> items) {
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        Object item = items.get(position);
        if (item instanceof EchoJson || item instanceof Validation) {
            return FORM_ID;
        } else
            return INFO_ID;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case INFO_ID:
                view = inflater.inflate(R.layout.info, parent, false);
                viewHolder = new InfoViewHolder(view);
                break;
            case FORM_ID:
                view = inflater.inflate(R.layout.form, parent, false);
                viewHolder = new FormViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }
}
