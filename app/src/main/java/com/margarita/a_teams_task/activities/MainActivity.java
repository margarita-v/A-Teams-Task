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
    // ID of selected menu item
    private static int selectedItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

        // Set current fragment
        MenuItem selectedItem;
        if (savedInstanceState != null) {
            // Restore selected menu item if it was saved
            selectedItemId = savedInstanceState.getInt(SELECTED_ITEM, 0);
            selectedItem = navigationView.getMenu().findItem(selectedItemId);
        } else {
            selectedItem = navigationView.getMenu().getItem(0);
        }
        selectFragment(selectedItem);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECTED_ITEM, selectedItemId);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        selectFragment(item);
        return true;
    }

    /**
     * Add fragment to activity depending on selected menu item
     * @param item Selected menu item
     */
    private void selectFragment(MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_info:
                fragment = new FragmentInfo();
                break;
            case R.id.navigation_contacts:
                fragment = new FragmentContacts();
                break;
        }

        // Update selected menu item ID
        selectedItemId = item.getItemId();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }
}
