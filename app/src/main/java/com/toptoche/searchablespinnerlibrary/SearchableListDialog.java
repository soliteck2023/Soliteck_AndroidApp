package com.toptoche.searchablespinnerlibrary;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.api_call.R;

import java.io.Serializable;
import java.util.List;

public class SearchableListDialog extends DialogFragment implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {
    private static final String ITEMS = "items";
    private ListView _listViewItems;
    private DialogInterface.OnClickListener _onClickListener;
    private OnSearchTextChanged _onSearchTextChanged;
    private SearchView _searchView;
    private SearchableItem _searchableItem;
    private String _strPositiveButtonText;
    private String _strTitle;
    private ArrayAdapter listAdapter;

    /* loaded from: classes.dex */
    public interface OnSearchTextChanged {
        void onSearchTextChanged(String str);
    }

    /* loaded from: classes.dex */
    public interface SearchableItem<T> extends Serializable {
        void onSearchableItemClicked(T t, int i);
    }

    public static SearchableListDialog newInstance(List items) {
        SearchableListDialog multiSelectExpandableFragment = new SearchableListDialog();
        Bundle args = new Bundle();
        args.putSerializable(ITEMS, (Serializable) items);
        multiSelectExpandableFragment.setArguments(args);
        return multiSelectExpandableFragment;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setSoftInputMode(2);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        if (savedInstanceState != null) {
            this._searchableItem = (SearchableItem) savedInstanceState.getSerializable("item");
        }
        View rootView = inflater.inflate(R.layout.searchable_list_dialog, (ViewGroup) null);
        setData(rootView);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setView(rootView);
        String strPositiveButton = this._strPositiveButtonText;
        if (strPositiveButton == null) {
            strPositiveButton = "CLOSE";
        }
        alertDialog.setPositiveButton(strPositiveButton, this._onClickListener);
        String strTitle = this._strTitle;
        if (strTitle == null) {
            strTitle = "Select Item";
        }
        alertDialog.setTitle(strTitle);
        AlertDialog dialog = alertDialog.create();
        dialog.getWindow().setSoftInputMode(2);
        return dialog;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("item", this._searchableItem);
        super.onSaveInstanceState(outState);
    }

    public void setTitle(String strTitle) {
        this._strTitle = strTitle;
    }

    public void setPositiveButton(String strPositiveButtonText) {
        this._strPositiveButtonText = strPositiveButtonText;
    }

    public void setPositiveButton(String strPositiveButtonText, DialogInterface.OnClickListener onClickListener) {
        this._strPositiveButtonText = strPositiveButtonText;
        this._onClickListener = onClickListener;
    }

    public void setOnSearchableItemClickListener(SearchableItem searchableItem) {
        this._searchableItem = searchableItem;
    }

    public void setOnSearchTextChangedListener(OnSearchTextChanged onSearchTextChanged) {
        this._onSearchTextChanged = onSearchTextChanged;
    }

    private void setData(View rootView) {
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) rootView.findViewById(R.id.search);
        this._searchView = searchView;
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        this._searchView.setIconifiedByDefault(false);
        this._searchView.setOnQueryTextListener(this);
        this._searchView.setOnCloseListener(this);
        this._searchView.clearFocus();
        InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(this._searchView.getWindowToken(), 0);
        List items = (List) getArguments().getSerializable(ITEMS);
        this._listViewItems = (ListView) rootView.findViewById(R.id.listItems);
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

    @Override // android.widget.SearchView.OnCloseListener
    public boolean onClose() {
        return false;
    }

    @Override // android.widget.SearchView.OnQueryTextListener
    public boolean onQueryTextSubmit(String s) {
        this._searchView.clearFocus();
        return true;
    }

    @Override // android.widget.SearchView.OnQueryTextListener
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
}
