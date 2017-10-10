package com.margarita.a_teams_task.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.margarita.a_teams_task.R;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class FragmentContacts extends Fragment {

    //region Strings for a contacts view
    private static final String DESCRIPTION = "This is a test project which implements client-server architecture";
    private static final String CONNECT = "Connect with us";
    private static final String MAIL = "margo.himera@yandex.ru";
    private static final String GITHUB = "GitHub account";
    private static final String GITHUB_LINK = "margarita-v";
    private static final String WEBSITE = "View VK page";
    private static final String WEBSITE_LINK = "https://vk.com/margarita_h";
    private static final String PHONE = "89507605844";
    //endregion

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Element phoneElement = new Element()
                .setTitle(PHONE)
                .setIconDrawable(R.drawable.ic_phone_black_24dp)
                .setIntent(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + PHONE)));

        return new AboutPage(container.getContext())
                .isRTL(false)
                .setImage(R.drawable.ic_android_black_24dp)
                .setDescription(DESCRIPTION)
                .addGroup(CONNECT)
                .addEmail(MAIL, MAIL)
                .addItem(phoneElement)
                .addGitHub(GITHUB_LINK, GITHUB)
                .addWebsite(WEBSITE_LINK, WEBSITE)
                .create();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
