package com.margarita.a_teams_task.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.margarita.a_teams_task.R;
import com.margarita.a_teams_task.loaders.InfoLoader;
import com.margarita.a_teams_task.models.base.BaseModel;
import com.margarita.a_teams_task.viewholders.FormViewHolder;
import com.margarita.a_teams_task.viewholders.InfoViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // Activity context for usage
    private Context context;

    // Items which will be stored in RecyclerView
    private List<BaseModel> items;

    // IDs for different types of objects
    private static final int INFO_ID = 0, FORM_ID = 1;
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
        return position < size() ? INFO_ID : FORM_ID;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
        if (position < size())
            configureInfoViewHolder((InfoViewHolder) holder, this.items.get(position));
        else
            configureFormViewHolder((FormViewHolder) holder, FORM_LOADERS_IDS[position - size()]);
    }

    //region Configure different view holders
    private void configureInfoViewHolder(InfoViewHolder holder, BaseModel item) {
        holder.setText(item.toString());
    }

    private void configureFormViewHolder(final FormViewHolder holder, final int loaderId) {
        holder.setHint(loaderId);
        holder.getBtnSubmit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Callback to fragment
                onLoadingPerformListener.onLoadingPerform(loaderId, holder.getText().trim());
                // Clear focus of edit text and hide keyboard
                holder.clearFocus();
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
    }

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
