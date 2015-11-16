package com.braindroid.braindroid;

import android.content.Context;
import android.content.res.Resources;

import java.util.Arrays;
import java.util.List;

public class HelperUtils {

	Context context;
	Resources res;
	
	public HelperUtils() {
		// TODO Auto-generated constructor stub
	}
	public HelperUtils(Context context) {
		super();
		this.context = context;
		res=context.getResources();
	}
	
	
	
	public List<String> loadMasterDataFromString(int characters) {
		// TODO Auto-generated method stub
		List<String> list;
			list=Arrays.asList(res.getStringArray(characters));
		return list;
	}

	
	
	
	
}
