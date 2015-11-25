package com.braindroid.braindroid;

import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;

public class HelperUtils {

	Context context;
	Resources res;

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
