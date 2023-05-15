package com.example.cryptocurrency.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cryptocurrency.R;
import com.example.cryptocurrency.adapter.MarketAdapter;
import com.example.cryptocurrency.apis.ApiInterface;
import com.example.cryptocurrency.apis.ApiUtilities;
import com.example.cryptocurrency.databinding.FragmentMarketBinding;
import com.example.cryptocurrency.models.CryptoCurrency;
import com.example.cryptocurrency.models.MarketModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Response;


public class MarketFragment extends Fragment {

    private FragmentMarketBinding binding;
    private List<CryptoCurrency> list;
    private MarketAdapter adapter;
    private String searchText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMarketBinding.inflate(inflater, container, false);

        list = new ArrayList<>();
        adapter = new MarketAdapter(requireContext(), list, "market");
        binding.currencyRecyclerView.setAdapter(adapter);

        new Thread(() -> {

            Response<MarketModel> res = null;
            try {
                res = ApiUtilities.getInstance().create(ApiInterface.class).getMarketData().execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (res != null && res.isSuccessful() && res.body() != null) {

                Response<MarketModel> finalRes = res;
                getActivity().runOnUiThread(() -> {
                    list = finalRes.body().getData().getCryptoCurrencyList();
                    adapter.updateData(list);
                    binding.spinKitView.setVisibility(View.GONE);
                });

            }

        }).start();

        searchCoin();

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private void searchCoin() {
        binding.searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {

            }

            @Override
            public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {

            }

            @Override
            public void afterTextChanged(Editable p0) {
                searchText = p0.toString().toLowerCase();
                upadateRecyclerView();
            }
        });

    }

    private void upadateRecyclerView() {
        ArrayList<CryptoCurrency> data = new ArrayList<>();
        for (CryptoCurrency item : list) {
            String coinName = item.getName().toLowerCase(Locale.getDefault());
            String coinSymbol = item.getSymbol().toLowerCase(Locale.getDefault());

            if (coinName.contains(searchText) || coinSymbol.contains(searchText)) {
                data.add(item);
            }
        }
        adapter.updateData(data);

    }

}