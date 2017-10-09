package com.margarita.a_teams_task.viewholders;

import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.margarita.a_teams_task.R;
import com.margarita.a_teams_task.loaders.InfoLoader;

public class FormViewHolder extends RecyclerView.ViewHolder {

    private TextInputLayout textInputLayout;
    private Button btnSubmit;

    // Hints
    private static final String HINT_JSON = "Please, enter a value for JSON object:";
    private static final String HINT_VALIDATION = "Please, enter a string in JSON format:";

    public FormViewHolder(View itemView) {
        super(itemView);
        this.textInputLayout = itemView.findViewById(R.id.textInputLayout);
        this.btnSubmit = itemView.findViewById(R.id.btnSubmit);
    }

    public Button getBtnSubmit() {
        return this.btnSubmit;
    }

    public String getText() {
        return this.textInputLayout.getEditText().getText().toString();
    }

    public void clearFocus() {
        this.textInputLayout.clearFocus();
    }

    /**
     * Set TextInputLayout hint which depends on loaderId
     * @param loaderId Loader ID which is associated with current form
     */
    public void setHint(int loaderId) {
        if (loaderId == InfoLoader.LOADER_JSON)
            this.textInputLayout.setHint(HINT_JSON);
        else
            this.textInputLayout.setHint(HINT_VALIDATION);
    }
}
