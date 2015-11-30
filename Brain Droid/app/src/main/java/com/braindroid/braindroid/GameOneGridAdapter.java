package com.braindroid.braindroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class GameOneGridAdapter extends BaseAdapter {

	Context context;
	List<String> list;
	private LayoutInflater inflater;
	private TextView tex;

	public GameOneGridAdapter(Context context, List<String> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		
			 inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); // listing items in a grid view
			convertView=inflater.inflate(R.layout.gameonegridadapter, null);
			tex=(TextView)convertView.findViewById(R.id.textView1);
			tex.setText(list.get(position));
		return convertView;
	}
	
	
	  
}
