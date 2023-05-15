package com.example.cryptocurrency.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cryptocurrency.R;
import com.example.cryptocurrency.adapter.TopLossGainPagerAdapter;
import com.example.cryptocurrency.adapter.TopMarketAdapter;
import com.example.cryptocurrency.apis.ApiInterface;
import com.example.cryptocurrency.apis.ApiUtilities;


import retrofit2.Response;

import android.annotation.SuppressLint;
import android.widget.Button;


import com.example.cryptocurrency.databinding.FragmentHomeBinding;
import com.example.cryptocurrency.models.CryptoCurrency;
import com.example.cryptocurrency.models.MarketModel;
import com.example.cryptocurrency.news.NewsListActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.IOException;
import java.util.List;


public class HomeFragment extends Fragment  {
    //private FragmentHomeBinding binding;
    private FragmentHomeBinding binding;
    Button button6;
    private View rootView;
    @SuppressLint("SuspiciousIndentation")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        getTopCurrencyList();
        setTabLayout();

        binding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigate();
            }
        });
        return binding.getRoot();




    }



    private void setTabLayout() {
        TopLossGainPagerAdapter adapter = new TopLossGainPagerAdapter(this);
        binding.contentViewPager.setAdapter(adapter);
        binding.contentViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 0) {
                    binding.topGainIndicator.setVisibility(View.VISIBLE);
                    binding.topLoseIndicator.setVisibility(View.GONE);
                } else {
                    binding.topGainIndicator.setVisibility(View.GONE);
                    binding.topLoseIndicator.setVisibility(View.VISIBLE);
                }
            }
        });

        new TabLayoutMediator(binding.tabLayout, binding.contentViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                String title = (position == 0) ? "Top Gainers" : "Top Losers" ;
                tab.setText(title);
            }
        }).attach();
    }

    private void getTopCurrencyList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response<MarketModel> res;
                try {
                    res = ApiUtilities.getInstance().create(ApiInterface.class).getMarketData().execute();
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }

                if (res != null && res.body() != null) {
                    final List<CryptoCurrency> cryptoCurrencyList = res.body().getData().getCryptoCurrencyList();

                    // Update UI on the main thread
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            binding.topCurrencyRecyclerView.setAdapter(new TopMarketAdapter(requireContext(), cryptoCurrencyList));
                        }
                    });

                    Log.d("SHUBH", "getTopCurrencyList: " + cryptoCurrencyList);
                }
            }
        }).start();
    }

    private void navigate(){

        Intent intent = new Intent(getContext(), NewsListActivity.class);
        startActivity(intent);}
}