package com.example.swipetabdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentPageAdapter extends FragmentPagerAdapter {

	public FragmentPageAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int id) {
		switch (id) {
		case 0:
			return new JavaFragment();
		case 1:
			return new PhpFragment();
		case 2:
			return new AndroidFragment();
		default:
			break;
		}
		return null;
	}

	@Override
	public int getCount() {
		return 3;
	}

}
