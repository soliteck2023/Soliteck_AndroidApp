package com.example.api_call;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.api_call.R;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SearchableListDialog extends DialogFragment implements SearchView.OnQueryTextListener, SearchView.OnCloseListener{
    private static final String ITEMS = "items";
    private ListView _listViewItems;
    private DialogInterface.OnClickListener _onClickListener;
    private OnSearchTextChanged _onSearchTextChanged;
    private SearchView _searchView;
    private SearchableItem _searchableItem;
    private String _strPositiveButtonText;
    private String _strTitle;
    private ArrayAdapter listAdapter;

    public static SearchableListDialog newInstance(List items) {
        SearchableListDialog multiSelectExpandableFragment = new SearchableListDialog();
        Bundle args = new Bundle();
        args.putSerializable(ITEMS, (Serializable) items);
        multiSelectExpandableFragment.setArguments(args);
        return multiSelectExpandableFragment;
    }

    @Override
    public boolean onClose() {
        return false;
    }

    public void setOnSearchableItemClickListener(SearchableSpinner searchableItem) {
        this._searchableItem = (SearchableItem) searchableItem;

    }

    public interface OnSearchTextChanged {
        void onSearchTextChanged(String str);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        this._searchView.clearFocus();
        return true;
    }
    public interface SearchableItem<T> extends Serializable {
        void onSearchableItemClicked(T t, int i);
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if (TextUtils.isEmpty(s)) {
            ((ArrayAdapter) this._listViewItems.getAdapter()).getFilter().filter(null);
        } else {
            ((ArrayAdapter) this._listViewItems.getAdapter()).getFilter().filter(s);
        }
        OnSearchTextChanged onSearchTextChanged = this._onSearchTextChanged;
        if (onSearchTextChanged != null) {
            onSearchTextChanged.onSearchTextChanged(s);
            return true;
        }
        return true;
    }
    private void setData(View rootView) {
        @SuppressLint("WrongConstant") SearchManager searchManager = (SearchManager) getActivity().getSystemService("search");
        @SuppressLint("WrongViewCast") SearchView searchView = (SearchView) rootView.findViewById(R.id.search_edit);
        this._searchView = searchView;
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        this._searchView.setIconifiedByDefault(false);
        this._searchView.setOnQueryTextListener(this);
        this._searchView.setOnCloseListener(this);
        this._searchView.clearFocus();
        @SuppressLint("WrongConstant") InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService("input_method");
        mgr.hideSoftInputFromWindow(this._searchView.getWindowToken(), 0);
        List items = (List) getArguments().getSerializable(ITEMS);
        this._listViewItems = (ListView) rootView.findViewById(R.id.list); //type manually
        @SuppressLint("ResourceType") ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), 17367043, items);
        this.listAdapter = arrayAdapter;
        this._listViewItems.setAdapter((ListAdapter) arrayAdapter);
        this._listViewItems.setTextFilterEnabled(true);
        this._listViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.toptoche.searchablespinnerlibrary.SearchableListDialog.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SearchableListDialog.this._searchableItem.onSearchableItemClicked(SearchableListDialog.this.listAdapter.getItem(position), position);
                SearchableListDialog.this.getDialog().dismiss();
            }
        });
    }





}
