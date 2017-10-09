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
import android.widget.Button;

import com.margarita.a_teams_task.R;
import com.margarita.a_teams_task.adapters.RecyclerViewAdapter;
import com.margarita.a_teams_task.loaders.InfoLoader;
import com.margarita.a_teams_task.models.base.BaseModel;
import com.rockerhieu.rvadapter.states.StatesRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentInfo extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeContainer;
    private RecyclerView recyclerView;
    private View emptyView;

    // List of loaded items
    private List<BaseModel> items;

    private InfoLoaderCallbacks callbacks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        callbacks = new InfoLoaderCallbacks();
        items = new ArrayList<>();

        View view = inflater.inflate(R.layout.fragment_info, container, false);
        recyclerView = view.findViewById(R.id.infoList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        swipeContainer = view.findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeResources(R.color.colorAccent);
        swipeContainer.setOnRefreshListener(this);

        emptyView = inflater.inflate(R.layout.list_empty, recyclerView, false);
        Button btnLoad = emptyView.findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!swipeContainer.isRefreshing())
                    swipeContainer.setRefreshing(true);
                    onRefresh();
            }
        });
        return view;
    }

    @Override
    public void onRefresh() {
        items.clear();
        performLoading(InfoLoader.LOADER_IP);
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
                        finishLoading(false);
                        break;
                }
            }
            else
                finishLoading(true);
        }

        @Override
        public void onLoaderReset(Loader<BaseModel> loader) {
            swipeContainer.setRefreshing(false);
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

        // Clear items if loading was finished with error
        if (hasError)
            items.clear();

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(
                items, getActivity().getSupportFragmentManager(), getActivity().getSupportLoaderManager());
        StatesRecyclerViewAdapter statesRecyclerViewAdapter = new StatesRecyclerViewAdapter(
                adapter, null, emptyView, null);
        recyclerView.setAdapter(statesRecyclerViewAdapter);

        // Set empty view if items list is empty
        if (hasError)
            statesRecyclerViewAdapter.setState(StatesRecyclerViewAdapter.STATE_EMPTY);
    }
}
