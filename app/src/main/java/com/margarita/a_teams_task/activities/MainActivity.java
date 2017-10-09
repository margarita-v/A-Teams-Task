package com.margarita.a_teams_task.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.margarita.a_teams_task.R;
import com.margarita.a_teams_task.fragments.FragmentContacts;
import com.margarita.a_teams_task.fragments.FragmentInfo;
import com.margarita.a_teams_task.loaders.InfoLoader;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    // Tag for restoring value from bundle
    private static final String SELECTED_ITEM = "SELECTED_ITEM";
    // ID of default menu item
    private static final int DEFAULT_ITEM_ID = R.id.navigation_info;
    // ID of selected menu item
    private static int selectedItemId = DEFAULT_ITEM_ID;

    //Fragments
    private FragmentInfo fragmentInfo;
    private FragmentContacts fragmentContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

        // Set current fragment
        if (savedInstanceState != null) {
            // Restore selected menu item if it was saved
            selectedItemId = savedInstanceState.getInt(SELECTED_ITEM, DEFAULT_ITEM_ID);
        }
        fragmentInfo = new FragmentInfo();
        fragmentContacts = new FragmentContacts();
        selectFragment(selectedItemId);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECTED_ITEM, selectedItemId);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        // Check if a new menu item was selected
        if (selectedItemId != itemId)
            selectFragment(itemId);
        return true;
    }

    /**
     * Add fragment to activity depending on selected menu item
     * @param itemId ID of selected menu item
     */
    private void selectFragment(int itemId) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (itemId) {
            case R.id.navigation_info:
                fragmentTransaction.replace(R.id.container, fragmentInfo);
                break;
            case R.id.navigation_contacts:
                // Destroy all running loaders
                if (getSupportLoaderManager().hasRunningLoaders()) {
                    for (int id : InfoLoader.ALL_LOADERS)
                        getSupportLoaderManager().destroyLoader(id);
                }
                fragmentTransaction.replace(R.id.container, fragmentContacts);
                break;
        }
        // Update selected menu item ID
        selectedItemId = itemId;
        fragmentTransaction.commit();
    }
}
