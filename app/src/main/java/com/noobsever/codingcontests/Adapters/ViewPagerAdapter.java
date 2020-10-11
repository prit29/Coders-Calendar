package com.noobsever.codingcontests.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.noobsever.codingcontests.Fragments.AtCoder_Fragment;
import com.noobsever.codingcontests.Fragments.Codeforces_Fragment;
import com.noobsever.codingcontests.Fragments.Coodechef_Fragment;
import com.noobsever.codingcontests.Fragments.Google_Fragment;
import com.noobsever.codingcontests.Fragments.HackerEarth_Fragment;
import com.noobsever.codingcontests.Fragments.HackerRank_Fragment;
import com.noobsever.codingcontests.Fragments.LeetCode_Fragment;
import com.noobsever.codingcontests.Fragments.SPOJ_Fragment;
import com.noobsever.codingcontests.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentsList = new ArrayList<>();
    private List<String> fragmentTitles = new ArrayList<>();

    // This method will instantiate and attach the required fragments to tabs.
    public void initFragments(List<String> fragList) {
        this.fragmentTitles = fragList;
        for(String s : fragList) {
            if(s.equals(Constants.CODEFORCES)) fragmentsList.add(new Codeforces_Fragment());
            else if(s.equals(Constants.CODECHEF)) fragmentsList.add(new Coodechef_Fragment());
            else if(s.equals(Constants.HACKERRANK)) fragmentsList.add(new HackerRank_Fragment());
            else if(s.equals(Constants.HACKEREARTH)) fragmentsList.add(new HackerEarth_Fragment());
            else if(s.equals(Constants.SPOJ)) fragmentsList.add(new SPOJ_Fragment());
            else if(s.equals(Constants.ATCODER)) fragmentsList.add(new AtCoder_Fragment());
            else if(s.equals(Constants.LEETCODE)) fragmentsList.add(new LeetCode_Fragment());
            else if(s.equals(Constants.GOOGLE)) fragmentsList.add(new Google_Fragment());
        }
    }

    // The method below can be then used to get fragments if we need them.
    public List<Fragment> getFragmentsList() {
        return fragmentsList;
    }

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitles.get(position);
    }
}
