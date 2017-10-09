package com.margarita.a_teams_task.adapters;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.margarita.a_teams_task.R;
import com.margarita.a_teams_task.dialogs.MessageDialog;
import com.margarita.a_teams_task.loaders.InfoLoader;
import com.margarita.a_teams_task.models.base.BaseModel;
import com.margarita.a_teams_task.viewholders.FormViewHolder;
import com.margarita.a_teams_task.viewholders.InfoViewHolder;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LoaderManager loaderManager;
    private FragmentManager fragmentManager;
    private FormLoaderCallbacks callbacks;

    private Context context;

    // Objects which will be stored in RecyclerView
    private Object[] items;

    // Actual items count (array will store IDs of hints)
    private int itemsCount;

    // IDs for different types of objects
    private static final int INFO_ID = 0, FORM_ID = 1;

    // Dialog tag
    private static final String DIALOG_TAG = "DIALOG";

    private String request;

    public RecyclerViewAdapter(Object[] items, int[] loadersIds,
                               FragmentManager fragmentManager, LoaderManager loaderManager) {
        this.loaderManager = loaderManager;
        this.fragmentManager = fragmentManager;
        this.callbacks = new FormLoaderCallbacks();

        // Get actual items count
        this.itemsCount = items.length;

        // Length of whole array
        int len = this.itemsCount + loadersIds.length;

        // Create array for items and IDs
        this.items = new Object[len];

        // Copy actual items to adapter
        System.arraycopy(items, 0, this.items, 0, this.itemsCount);

        // Add IDs of hints
        for (int i = this.itemsCount; i < len; i++)
            this.items[i] = loadersIds[i - this.itemsCount];
    }

    @Override
    public int getItemCount() {
        return this.items.length;
    }

    @Override
    public int getItemViewType(int position) {
        return position < this.itemsCount ? INFO_ID : FORM_ID;
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
        if (position < this.itemsCount)
            configureInfoViewHolder((InfoViewHolder) holder, position);
        else
            configureFormViewHolder((FormViewHolder) holder, position);
    }

    //region Configure different view holders
    private void configureInfoViewHolder(InfoViewHolder holder, int position) {
        holder.setText(this.items[position].toString());
    }

    private void configureFormViewHolder(final FormViewHolder holder, int position) {
        final int loaderId = (int) this.items[position];
        holder.setHint(loaderId);
        holder.getBtnSubmit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request = holder.getText().trim();
                if (request.length() > 0)
                    performLoading(loaderId);
                else
                    configureDialog(R.string.error_title, R.string.error_message);
            }
        });
    }
    //endregion

    private class FormLoaderCallbacks implements LoaderManager.LoaderCallbacks<BaseModel> {

        @Override
        public Loader<BaseModel> onCreateLoader(int id, Bundle args) {
            return new InfoLoader(context, id, request);
        }

        @Override
        public void onLoadFinished(Loader<BaseModel> loader, final BaseModel data) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    if (data != null)
                        configureDialog(R.string.result_title, data.toString());
                    else
                        configureDialog(R.string.error_title, R.string.error_loading);
                }
            });
        }

        @Override
        public void onLoaderReset(Loader<BaseModel> loader) {

        }
    }

    /**
     * Performing loading of different items
     * @param loaderId ID of loader which should be launched
     */
    private void performLoading(int loaderId) {
        this.loaderManager.restartLoader(loaderId, null, callbacks);
    }

    //region Configure dialogs
    private void configureDialog(int titleId, int messageId) {
        MessageDialog.newInstance(titleId, messageId).show(fragmentManager, DIALOG_TAG);
    }

    private void configureDialog(int titleId, String message) {
        MessageDialog.newInstance(titleId, message).show(fragmentManager, DIALOG_TAG);
    }
    //endregion
}
