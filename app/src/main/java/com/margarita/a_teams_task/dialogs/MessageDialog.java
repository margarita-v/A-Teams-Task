package com.margarita.a_teams_task.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v7.app.AppCompatDialogFragment;

public class MessageDialog extends AppCompatDialogFragment
        implements DialogInterface.OnClickListener {

    // IDs of dialogs usage: show message or show loading result
    private static final int PARAMS_MESSAGE = 1;
    private static final int PARAMS_RESULT = 2;

    // String keys for Bundle
    private static final String DIALOG_KEY = "DIALOG_KEY";
    private static final String TITLE_KEY = "TITLE_KEY";
    private static final String MESSAGE_KEY = "MESSAGE_KEY";
    
    public static MessageDialog newInstance(int titleId, int messageId) {
        Bundle args = new Bundle();
        args.putInt(TITLE_KEY, titleId);
        args.putInt(MESSAGE_KEY, messageId);
        args.putInt(DIALOG_KEY, PARAMS_MESSAGE);
        return createDialog(args, true);
    }

    public static MessageDialog newInstance(int titleId, String message) {
        Bundle args = new Bundle();
        args.putInt(TITLE_KEY, titleId);
        args.putString(MESSAGE_KEY, message);
        args.putInt(DIALOG_KEY, PARAMS_RESULT);
        return createDialog(args, false);
    }

    private static MessageDialog createDialog(Bundle args, boolean isCancelable) {
        MessageDialog dialog = new MessageDialog();
        dialog.setArguments(args);
        dialog.setCancelable(isCancelable);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setPositiveButton(android.R.string.ok, this);

        Bundle args = getArguments();
        if (args != null) {
            int dialogId = args.getInt(DIALOG_KEY);
            int titleId = args.getInt(TITLE_KEY);

            switch (dialogId) {
                case PARAMS_MESSAGE:
                    int messageId = args.getInt(MESSAGE_KEY);
                    builder.setTitle(titleId).setMessage(messageId);
                    break;
                case PARAMS_RESULT:
                    String message = args.getString(MESSAGE_KEY);
                    builder.setTitle(titleId).setMessage(message);
                    break;
            }
        }
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        dismiss();
    }
}

