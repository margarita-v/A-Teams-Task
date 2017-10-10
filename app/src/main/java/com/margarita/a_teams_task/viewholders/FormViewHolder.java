package com.margarita.a_teams_task.viewholders;

import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.margarita.a_teams_task.R;

public class FormViewHolder extends RecyclerView.ViewHolder {

    private TextView tvName;
    private TextInputLayout textInputLayout;
    private Button btnSubmit;

    public FormViewHolder(View itemView) {
        super(itemView);
        this.tvName = itemView.findViewById(R.id.tvName);
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

    public void setName(String name) {
        this.tvName.setText(name);
    }
}
