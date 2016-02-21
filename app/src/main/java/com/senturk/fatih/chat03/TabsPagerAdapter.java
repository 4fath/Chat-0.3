package com.senturk.fatih.chat03;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

public class TabsPagerAdapter extends PagerAdapter {


	public TabsPagerAdapter(FragmentManager supportFragmentManager) {

	}

	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Top Rated fragment activity
			return new TopRatedFragment();
			//return new Fragment();
		case 1:
			// Games fragment activity
			return new MoviesFragment();

		}

		return null;
	}


	public int getCount() {
		// get item count - equal to number of tabs
		return 2;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return false;
	}

}
