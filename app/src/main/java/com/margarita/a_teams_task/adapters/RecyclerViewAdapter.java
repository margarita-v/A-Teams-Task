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

    // Objects which will be stored in RecyclerView
    private List<BaseModel> items;
    // IDs for different types of objects
    private static final int INFO_ID = 0, FORM_ID = 1;

    // Hints for TextInputLayouts
    private static final String HINT_JSON = "Please, enter a value for JSON object:";
    private static final String HINT_VALIDATION = "Please, enter a string in JSON format:";

    public RecyclerViewAdapter(List<BaseModel> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return this.items.size();
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
        BaseModel item = items.get(position);

        switch (holder.getItemViewType()) {
            case INFO_ID:
                configureInfoViewHolder((InfoViewHolder) holder, item);
                break;
            case FORM_ID:
                configureFormViewHolder((FormViewHolder) holder, item);
                break;
        }
    }

    //region Configure different view holders
    private void configureInfoViewHolder(InfoViewHolder holder, BaseModel item) {
        holder.setText(item.toString());
    }

    private void configureFormViewHolder(final FormViewHolder holder, BaseModel item) {
        if (item instanceof EchoJson)
            holder.setHint(HINT_JSON);
        else
            holder.setHint(HINT_VALIDATION);
        holder.getBtnSubmit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.getText() != null) {
                    //TODO Post value to server and get response
                    //TODO Change fragment on response
                }
                //TODO Show dialog with empty string error
            }
        });
    }
    //endregion
}
