package com.margarita.a_teams_task.viewholders;

import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.margarita.a_teams_task.R;

public class FormViewHolder extends RecyclerView.ViewHolder {

    private TextInputLayout textInputLayout;
    private Button btnSubmit;

    public FormViewHolder(View itemView) {
        super(itemView);
        this.textInputLayout = itemView.findViewById(R.id.textInputLayout);
        this.btnSubmit = itemView.findViewById(R.id.btnSubmit);
    }

    public Button getBtnSubmit() {
        return this.btnSubmit;
    }

    public void setHint(String hint) {
        this.textInputLayout.setHint(hint);
    }

    public int getTextSize() {
        return this.textInputLayout.getEditText().getText().length();
    }
}
