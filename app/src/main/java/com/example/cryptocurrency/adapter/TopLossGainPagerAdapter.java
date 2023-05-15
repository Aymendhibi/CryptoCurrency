package com.example.cryptocurrency.adapter;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cryptocurrency.fragment.TopLossGainFragment;

public class TopLossGainPagerAdapter  extends FragmentStateAdapter {



    public TopLossGainPagerAdapter(Fragment fragment) {
        super(fragment);
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @SuppressLint("SuspiciousIndentation")
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        TopLossGainFragment fragment = new TopLossGainFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        fragment.setArguments(bundle);
        return fragment;
    }
}
