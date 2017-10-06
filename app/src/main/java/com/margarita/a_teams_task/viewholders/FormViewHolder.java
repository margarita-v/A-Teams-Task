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
        textInputLayout = (TextInputLayout) itemView.findViewById(R.id.textInputLayout);
        btnSubmit = (Button) itemView.findViewById(R.id.btnSubmit);
    }

    public TextInputLayout getTextInputLayout() {
        return textInputLayout;
    }

    public void setTextInputLayout(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
    }

    public Button getBtnSubmit() {
        return btnSubmit;
    }

    public void setBtnSubmit(Button btnSubmit) {
        this.btnSubmit = btnSubmit;
    }
}
