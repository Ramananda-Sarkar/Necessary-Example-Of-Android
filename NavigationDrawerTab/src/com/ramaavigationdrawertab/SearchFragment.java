package com.ramaavigationdrawertab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SearchFragment extends Fragment {
	public static final String ARG_SECTION_NUMBER = "section_number";

	public static SearchFragment instatnce(){
		return new SearchFragment();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.search_fragment, container, false);
	}
}
