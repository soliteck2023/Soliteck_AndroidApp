import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.io.Serializable;
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

    @Override
    public boolean onClose() {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        this._searchView.clearFocus();
        return true;
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


}
