package com.ninh.tuvungtienganh;

import com.zenapp.tuvung.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SearchAdapter extends ArrayAdapter<english> {

	public SearchAdapter(Context context) {
		super(context, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null)
		convertView = LayoutInflater.from(getContext()).inflate(R.layout.search_list_item, null);
		((TextView)(convertView.findViewById(R.id.subject))).setText(getItem(position).getName());
		((TextView)(convertView.findViewById(R.id.to))).setText(getItem(position).getVicontent());
		((TextView)(convertView.findViewById(R.id.date))).setText(getItem(position).getRead());
		return convertView;
	}

}
