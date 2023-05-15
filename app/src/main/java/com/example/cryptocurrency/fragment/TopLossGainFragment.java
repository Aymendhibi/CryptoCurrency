package com.example.cryptocurrency.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cryptocurrency.R;
import com.example.cryptocurrency.adapter.MarketAdapter;
import com.example.cryptocurrency.apis.ApiInterface;
import com.example.cryptocurrency.apis.ApiUtilities;
import com.example.cryptocurrency.databinding.FragmentTopLossGainBinding;
import com.example.cryptocurrency.models.CryptoCurrency;
import com.example.cryptocurrency.models.MarketModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Response;


public class TopLossGainFragment extends Fragment {

    private FragmentTopLossGainBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTopLossGainBinding.inflate(inflater);
        getMarketData();
        return binding.getRoot();
    }

    private void getMarketData() {
        final int position = requireArguments().getInt("position");
        new Thread(() -> {
            Response<MarketModel> res = null;
            try {
                res = ApiUtilities.getInstance().create(ApiInterface.class).getMarketData().execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (res != null && res.body() != null) {
                Response<MarketModel> finalRes = res;
                getActivity().runOnUiThread(() -> {
                    List<CryptoCurrency> dataItem = finalRes.body().getData().getCryptoCurrencyList();
                    Collections.sort(dataItem, (o1, o2) -> Integer.compare(
                            Integer.parseInt(String.valueOf(Double.valueOf(o2.getQuotes().get(0).getPercentChange24h()).intValue())),
                            Integer.parseInt(String.valueOf(Double.valueOf(o1.getQuotes().get(0).getPercentChange24h()).intValue()))
                    ));

                    binding.spinKitView.setVisibility(View.GONE);
                    List<CryptoCurrency> list = new ArrayList<>();
                    if (position == 0) {
                        list.clear();
                        for (int i = 0; i <= 9; i++) {
                            list.add(dataItem.get(i));
                        }
                        binding.topGainLoseRecyclerView.setAdapter(new MarketAdapter(
                                requireContext(),
                                list,
                                "home"
                        ));

                    } else {

                        list.clear();
                        for (int i = 0; i <= 9; i++) {
                            list.add(dataItem.get(dataItem.size() - 1 - i));
                        }
                        binding.topGainLoseRecyclerView.setAdapter(new MarketAdapter(
                                requireContext(),
                                list,
                                "home"
                        ));

                    }
                });
            }

        }).start();
    }


}