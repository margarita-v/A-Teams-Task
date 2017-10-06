package com.margarita.a_teams_task.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.margarita.a_teams_task.R;
import com.margarita.a_teams_task.fragments.FragmentContacts;
import com.margarita.a_teams_task.fragments.FragmentInfo;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    // Tag for restoring value from bundle
    private static final String SELECTED_ITEM = "arg_selected_item";
    // ID of default menu item
    private static final int DEFAULT_ITEM_ID = R.id.navigation_info;
    // ID of selected menu item
    private static int selectedItemId = DEFAULT_ITEM_ID;

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
        Fragment fragment = null;
        switch (itemId) {
            case R.id.navigation_info:
                fragment = new FragmentInfo();
                break;
            case R.id.navigation_contacts:
                fragment = new FragmentContacts();
                break;
        }

        // Update selected menu item ID
        selectedItemId = itemId;

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }
}
