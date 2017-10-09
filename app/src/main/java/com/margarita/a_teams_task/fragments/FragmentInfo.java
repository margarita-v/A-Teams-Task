package com.margarita.a_teams_task.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.margarita.a_teams_task.R;
import com.margarita.a_teams_task.adapters.RecyclerViewAdapter;
import com.margarita.a_teams_task.loaders.InfoLoader;
import com.margarita.a_teams_task.models.base.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class FragmentInfo extends Fragment {

    private SwipeRefreshLayout swipeContainer;
    private RecyclerView recyclerView;

    // IDs of string resources for hints in TextInputLayouts
    private static final int[] HINTS_IDS = { R.string.hint_json, R.string.hint_validation };

    // List of loaded items
    private List<BaseModel> items;

    private InfoLoaderCallbacks callbacks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        callbacks = new InfoLoaderCallbacks();
        items = new ArrayList<>();

        View view = inflater.inflate(R.layout.fragment_info, container, false);
        recyclerView = view.findViewById(R.id.infoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        swipeContainer = view.findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeResources(R.color.colorAccent);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                items.clear();
                performLoading(InfoLoader.LOADER_IP);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Start loading items consistently
        swipeContainer.setRefreshing(true);
        performLoading(InfoLoader.LOADER_IP);
    }

    private class InfoLoaderCallbacks implements LoaderManager.LoaderCallbacks<BaseModel> {

        @Override
        public Loader<BaseModel> onCreateLoader(int id, Bundle args) {
            return new InfoLoader(getContext(), id);
        }

        @Override
        public void onLoadFinished(Loader<BaseModel> loader, BaseModel data) {
            if (data != null) {
                items.add(data);
                // Start loading of the next item
                switch (loader.getId()) {
                    case InfoLoader.LOADER_IP:
                        performLoading(InfoLoader.LOADER_HEADERS);
                        break;
                    case InfoLoader.LOADER_HEADERS:
                        performLoading(InfoLoader.LOADER_DATETIME);
                        break;
                    case InfoLoader.LOADER_DATETIME:
                        RecyclerViewAdapter adapter = new RecyclerViewAdapter(
                                items.toArray(), HINTS_IDS, getActivity().getSupportFragmentManager());
                        recyclerView.setAdapter(adapter);
                        finishLoading(false);
                        break;
                }
            }
            else {
                Toast.makeText(getContext(), "Loading error", Toast.LENGTH_SHORT).show();
                finishLoading(true);
            }
        }

        @Override
        public void onLoaderReset(Loader<BaseModel> loader) {
            finishLoading(true);
            recyclerView.setAdapter(null);
        }
    }

    /**
     * Performing loading of different items
     * @param loaderId ID of loader which should be launched
     */
    private void performLoading(int loaderId) {
        getActivity().getSupportLoaderManager().restartLoader(loaderId, null, callbacks);
    }

    /**
     * Perform actions after loading
     * @param hasError True if loading finished with errors
     */
    private void finishLoading(boolean hasError) {
        swipeContainer.setRefreshing(false);
        if (hasError)
            items.clear();
    }
}
