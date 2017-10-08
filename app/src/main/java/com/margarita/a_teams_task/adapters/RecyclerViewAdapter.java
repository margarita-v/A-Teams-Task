package com.margarita.a_teams_task.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.margarita.a_teams_task.R;
import com.margarita.a_teams_task.models.base.BaseModel;
import com.margarita.a_teams_task.viewholders.FormViewHolder;
import com.margarita.a_teams_task.viewholders.InfoViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    // Objects which will be stored in RecyclerView
    private List<Object> items;

    // Actual items count (array will store null objects for forms)
    private int itemsCount;

    // IDs for different types of objects
    private static final int INFO_ID = 0, FORM_ID = 1;

    public RecyclerViewAdapter(List<BaseModel> items, int[] hintStringIds) {
        this.itemsCount = items.size();
        this.items = new ArrayList<>();
        for (BaseModel item: items) {
            this.items.add(item);
        }
        for (int id: hintStringIds) {
            this.items.add(id);
        }
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position < this.itemsCount ? INFO_ID : FORM_ID;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Save context for usage
        this.context = parent.getContext();

        View view;
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(this.context);

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
        if (position < this.itemsCount)
            configureInfoViewHolder((InfoViewHolder) holder, position);
        else
            // Get position in hintStringIds for correct hint
            configureFormViewHolder((FormViewHolder) holder, position);
    }

    //region Configure different view holders
    private void configureInfoViewHolder(InfoViewHolder holder, int position) {
        holder.setText(this.items.get(position).toString());
    }

    private void configureFormViewHolder(final FormViewHolder holder, int position) {
        // Get correct hint by its ID in String resources
        holder.setHint(this.context.getString((int) this.items.get(position)));
        holder.getBtnSubmit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.getText() != null) {
                    //TODO Post value to server and get response
                    //TODO Show response at the dialog fragment
                }
                //TODO Show dialog with empty string error
            }
        });
    }
    //endregion
}
