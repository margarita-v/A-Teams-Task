package com.margarita.a_teams_task.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentContacts extends Fragment {

    private View contactsView;

    public static FragmentContacts newInstance(@NonNull View contentView) {
        FragmentContacts fragmentContacts = new FragmentContacts();
        fragmentContacts.contactsView = contentView;
        return fragmentContacts;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return contactsView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
