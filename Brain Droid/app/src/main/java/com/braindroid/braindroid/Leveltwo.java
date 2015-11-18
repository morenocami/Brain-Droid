package com.braindroid.braindroid;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Leveltwo extends Fragment {

	
	private View root;
	private Context context;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		root=inflater.inflate(R.layout.layout, container,false);
		context=getActivity();
		return root;
	}
	
}
