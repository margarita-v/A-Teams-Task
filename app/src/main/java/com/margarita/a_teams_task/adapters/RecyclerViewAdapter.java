package com.margarita.a_teams_task.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.margarita.a_teams_task.R;
import com.margarita.a_teams_task.loaders.InfoLoader;
import com.margarita.a_teams_task.models.EchoJson;
import com.margarita.a_teams_task.models.Validation;
import com.margarita.a_teams_task.models.base.BaseModel;
import com.margarita.a_teams_task.viewholders.FormViewHolder;
import com.margarita.a_teams_task.viewholders.InfoViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // Items which will be stored in RecyclerView
    private List<BaseModel> items;

    // ID of common objects
    private static final int INFO_ID = 0;
    // Loaders IDs which will be associated with forms
    private static final int[] FORM_LOADERS_IDS = { InfoLoader.LOADER_JSON, InfoLoader.LOADER_VALIDATION };

    // Interface implementation
    private final OnLoadingPerform onLoadingPerformListener;

    public RecyclerViewAdapter(List<BaseModel> items, @NonNull OnLoadingPerform onLoadingPerform) {
        this.items = new ArrayList<>(items);
        this.onLoadingPerformListener = onLoadingPerform;
    }

    private int size() {
        return this.items.size();
    }

    @Override
    public int getItemCount() {
        return size() + FORM_LOADERS_IDS.length;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < size())
            return INFO_ID;
        return FORM_LOADERS_IDS[position - size()];
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
            case InfoLoader.LOADER_JSON:
                view = inflater.inflate(R.layout.form_json, parent, false);
                viewHolder = new FormViewHolder(view);
                break;
            case InfoLoader.LOADER_VALIDATION:
                view = inflater.inflate(R.layout.form_validation, parent, false);
                viewHolder = new FormViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position < size())
            configureInfoViewHolder((InfoViewHolder) holder, this.items.get(position));
        else
            configureFormViewHolder((FormViewHolder) holder, getItemViewType(position));
    }

    //region Configure different view holders
    private void configureInfoViewHolder(InfoViewHolder holder, BaseModel item) {
        holder.setInfo(item.toString());
        holder.setName(item.getClassName());
    }

    private void configureFormViewHolder(final FormViewHolder holder, final int loaderId) {
        if (loaderId == InfoLoader.LOADER_JSON)
            holder.setName(EchoJson.CLASS_NAME);
        else
            holder.setName(Validation.CLASS_NAME);

        holder.getBtnSubmit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoadingPerformListener.onLoadingPerform(loaderId, holder.getText().trim());
            }
        });
    }
    //endregion

    /**
     * Interface for connection with fragment when loading should be started
     */
    public interface OnLoadingPerform {
        /**
         * Method for loading request creation
         * @param loaderId ID of loader which should be launched
         * @param request String request which was get from the user's input
         */
        void onLoadingPerform(int loaderId, @NonNull String request);
    }
}
