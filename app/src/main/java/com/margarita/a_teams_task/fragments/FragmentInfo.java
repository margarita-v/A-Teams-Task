package com.margarita.a_teams_task.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.margarita.a_teams_task.R;
import com.margarita.a_teams_task.adapters.RecyclerViewAdapter;
import com.margarita.a_teams_task.loaders.InfoLoader;
import com.margarita.a_teams_task.models.base.BaseModel;

public class FragmentInfo extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    private static final int[] HINTS_IDS = { R.string.hint_json, R.string.hint_validation };
    private static final int SIZE = 3;
    private BaseModel[] items;
    private int currentItemIndex;

    private InfoLoaderCallbacks callbacks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        callbacks = new InfoLoaderCallbacks();
        items = new BaseModel[SIZE];

        View view = inflater.inflate(R.layout.fragment_info, container, false);
        recyclerView = view.findViewById(R.id.infoList);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TODO Perform loading
        getActivity().getSupportLoaderManager().initLoader(InfoLoader.LOADER_IP, null, callbacks);
    }

    private class InfoLoaderCallbacks implements LoaderManager.LoaderCallbacks<BaseModel> {

        @Override
        public Loader<BaseModel> onCreateLoader(int id, Bundle args) {
            return new InfoLoader(getContext(), id);
        }

        @Override
        public void onLoadFinished(Loader<BaseModel> loader, BaseModel data) {
            switch (loader.getId()) {
                case InfoLoader.LOADER_IP:
                    items[currentItemIndex++] = data;
                    getActivity().getSupportLoaderManager().initLoader(InfoLoader.LOADER_HEADERS, null, callbacks);
                    break;
                case InfoLoader.LOADER_HEADERS:
                    items[currentItemIndex++] = data;
                    getActivity().getSupportLoaderManager().initLoader(InfoLoader.LOADER_DATETIME, null, callbacks);
                    break;
                case InfoLoader.LOADER_DATETIME:
                    items[currentItemIndex++] = data;
                    adapter = new RecyclerViewAdapter(items, HINTS_IDS);
                    recyclerView.setAdapter(adapter);
                    break;
            }
        }

        @Override
        public void onLoaderReset(Loader<BaseModel> loader) {

        }
    }
}
