package com.example.htlgrk.whattowear;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * Created by Christoph on 11.10.2016.
 */
public class ViewPagerAdapter1 extends FragmentPagerAdapter{

    String[] array = new String[]{"M", "D", "M", "D", "F", "S", "S", "M","D", "M"};

    public ViewPagerAdapter1(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position){

            case 0: return new Fragment1();
            case 1: return new Fragment2();
            case 2: return new Fragment3();
            case 3: return new Fragment4();
            case 4: return new Fragment5();
            case 5: return new Fragment6();
            case 6: return new Fragment7();
            case 7: return new Fragment8();
            case 8: return new Fragment9();
            case 9: return new Fragment10();




        }
        return null;
    }

    @Override
    public int getCount() {
        return 10;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return ((array[position]));
    }
}
