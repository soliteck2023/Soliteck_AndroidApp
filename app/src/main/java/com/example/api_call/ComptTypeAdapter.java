package com.example.api_call;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ComptTypeAdapter extends ArrayAdapter<comptTyperesponse> {
    private List<comptTyperesponse> dataList;

    public ComptTypeAdapter(Context context, int resource, int textViewResourceId, List<comptTyperesponse> dataList) {
        super(context, resource, textViewResourceId, dataList);
        this.dataList = dataList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(dataList.get(position).getName()); // Set the text to the name of the complaint type
        return view;
    }
}