package com.ramaavigationdrawertab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TabbedFragment extends Fragment {

	public static final String TAG = TabbedFragment.class.getSimpleName();

	ViewPager mViewPager;
	SelectionPagerAdapter mSelectionPagerAdapter;

	public static TabbedFragment newInstance() {
		return new TabbedFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mSelectionPagerAdapter = new SelectionPagerAdapter(
				getChildFragmentManager());

		View v = inflater.inflate(R.layout.tabbedfragment, container, false);

		mViewPager = (ViewPager) v.findViewById(R.id.pager);
		mViewPager.setAdapter(mSelectionPagerAdapter);

		return v;

	}
}
