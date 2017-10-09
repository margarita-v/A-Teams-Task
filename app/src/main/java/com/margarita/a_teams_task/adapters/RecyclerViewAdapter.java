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
import android.view.inputmethod.InputMethodManager;

import com.margarita.a_teams_task.R;
import com.margarita.a_teams_task.dialogs.MessageDialog;
import com.margarita.a_teams_task.loaders.InfoLoader;
import com.margarita.a_teams_task.models.base.BaseModel;
import com.margarita.a_teams_task.viewholders.FormViewHolder;
import com.margarita.a_teams_task.viewholders.InfoViewHolder;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LoaderManager loaderManager;
    private FragmentManager fragmentManager;
    private FormLoaderCallbacks callbacks;

    private Context context;

    // Items which will be stored in RecyclerView
    private List<BaseModel> items;

    // IDs for different types of objects
    private static final int INFO_ID = 0, FORM_ID = 1;
    // Loaders IDs which will be associated with forms
    private static final int[] FORM_LOADERS_IDS = { InfoLoader.LOADER_JSON, InfoLoader.LOADER_VALIDATION };
    private static final String DIALOG_TAG = "DIALOG";

    private String request;

    public RecyclerViewAdapter(List<BaseModel> items, FragmentManager fragmentManager, LoaderManager loaderManager) {
        this.loaderManager = loaderManager;
        this.fragmentManager = fragmentManager;
        this.callbacks = new FormLoaderCallbacks();
        this.items = items;
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
                request = holder.getText().trim();
                if (request.length() > 0)
                    performLoading(loaderId);
                else
                    configureDialog(R.string.error_title, R.string.error_message);
                // Clear focus of edit text and hide keyboard
                holder.clearFocus();
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);            }
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
