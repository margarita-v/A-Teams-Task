package com.margarita.a_teams_task.adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.margarita.a_teams_task.R;
import com.margarita.a_teams_task.dialogs.MessageDialog;
import com.margarita.a_teams_task.viewholders.FormViewHolder;
import com.margarita.a_teams_task.viewholders.InfoViewHolder;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private FragmentManager fragmentManager;
    private Context context;

    // Objects which will be stored in RecyclerView
    private Object[] items;

    // Actual items count (array will store IDs of hints)
    private int itemsCount;

    // IDs for different types of objects
    private static final int INFO_ID = 0, FORM_ID = 1;

    // Dialog tag
    private static final String DIALOG_TAG = "DIALOG";

    public RecyclerViewAdapter(Object[] items, int[] hintStringIds, FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;

        // Get actual items count
        this.itemsCount = items.length;

        // Length of whole array
        int len = this.itemsCount + hintStringIds.length;

        // Create array for items and IDs
        this.items = new Object[len];

        // Copy actual items to adapter
        System.arraycopy(items, 0, this.items, 0, this.itemsCount);

        // Add IDs of hints
        for (int i = this.itemsCount; i < len; i++)
            this.items[i] = hintStringIds[i - this.itemsCount];
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
        holder.setText(this.items[position].toString());
    }

    private void configureFormViewHolder(final FormViewHolder holder, int position) {
        // Get correct hint by its ID in String resources
        holder.setHint(this.context.getString((int) this.items[position]));
        holder.getBtnSubmit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.getTextSize() > 0) {
                    //TODO Post value to server and get response
                    //TODO Show response at the dialog fragment
                } else {
                    MessageDialog.newInstance(R.string.error_title, R.string.error_message)
                            .show(fragmentManager, DIALOG_TAG);
                }
            }
        });
    }
    //endregion
}
