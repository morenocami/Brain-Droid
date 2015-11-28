package com.braindroid.braindroid;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class GridAdapter extends BaseAdapter {

	Context context;
	List<String> list;
	private LayoutInflater inflater;
	private TextView tex;
	private Resources res;

	public GridAdapter() {
		// TODO Auto-generated constructor stub
	}

	public GridAdapter(Context context, List<String> list) {
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

		Log.i(User.TAG, "GridAdapter.java");

		inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView=inflater.inflate(R.layout.list_item, null);
			tex=(TextView)convertView.findViewById(R.id.textView1);
			tex.setText(list.get(position));
//		res=context.getResources();	
//		int width=(int) res.getDimension(R.dimen.frame_width);
//		int height=(int) res.getDimension(R.dimen.frame_height);
//		
//				width=width/list.size();
//				height=height/list.size();
//		convertView.setLayoutParams(new ViewGroup.LayoutParams(width, height));
		
		return convertView;
	}
	
	
	  
}
