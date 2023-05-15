package com.example.cryptocurrency.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.cryptocurrency.MainActivity;
import com.example.cryptocurrency.R;
import com.example.cryptocurrency.databinding.FragmentDetailsBinding;
import com.example.cryptocurrency.models.CryptoCurrency;


public class DetailsFragment extends Fragment {
    private FragmentDetailsBinding binding;
    private DetailsFragmentArgs item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater);

        item = DetailsFragmentArgs.fromBundle(getArguments());
        CryptoCurrency data = item.getData();

       setUpDetails(data);
        loadChart(data);
        setButtonOnClick(data);
        binding.backStackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigate();
            }
        });

        return binding.getRoot();
    }

    private void setButtonOnClick(CryptoCurrency item) {
        AppCompatButton oneMonth = binding.button;
        AppCompatButton oneWeek = binding.button1;
        AppCompatButton oneDay = binding.button2;
        AppCompatButton fourHour = binding.button3;
        AppCompatButton oneHour = binding.button4;
        AppCompatButton fifteenMinute = binding.button5;

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button5:
                        loadChartData(v, "15", item, oneDay, oneMonth, oneWeek, fourHour, oneHour);
                        break;
                    case R.id.button4:
                        loadChartData(v, "1H", item, oneDay, oneMonth, oneWeek, fourHour, fifteenMinute);
                        break;
                    case R.id.button3:
                        loadChartData(v, "4H", item, oneDay, oneMonth, oneWeek, fifteenMinute, oneHour);
                        break;
                    case R.id.button2:
                        loadChartData(v, "D", item, fifteenMinute, oneMonth, oneWeek, fourHour, oneHour);
                        break;
                    case R.id.button1:
                        loadChartData(v, "W", item, oneDay, oneMonth, fifteenMinute, fourHour, oneHour);
                        break;
                    case R.id.button:
                        loadChartData(v, "M", item, oneDay, fifteenMinute, oneWeek, fourHour, oneHour);
                        break;
                }
            }
        };

        fifteenMinute.setOnClickListener(clickListener);
        oneHour.setOnClickListener(clickListener);
        fourHour.setOnClickListener(clickListener);
        oneDay.setOnClickListener(clickListener);
        oneWeek.setOnClickListener(clickListener);
        oneMonth.setOnClickListener(clickListener);
    }

    private void loadChartData(View view, String s, CryptoCurrency item, AppCompatButton oneDay, AppCompatButton oneMonth, AppCompatButton oneWeek, AppCompatButton fourHour, AppCompatButton oneHour) {
        disableButton(oneDay, oneMonth, oneWeek, fourHour, oneHour);
        view.setBackgroundResource(R.drawable.active_button);
        binding.detaillChartWebView.getSettings().setJavaScriptEnabled(true);
        binding.detaillChartWebView.loadUrl(
                "https://s.tradingview.com/widgetembed/?frameElementId=tradingview_76d87&symbol="
                        + "USD&interval=" + s + "&hidesidetoolbar=1&hidetoptoolbar=1&symboledit=1&saveimage=1&toolbarbg="
                        + "F1F3F6&studies=[]&hideideas=1&theme=Dark&style=1&timezone=Etc%2FUTC&studies_overrides={}&overrides={}&enabled_features=[]&disabled_features="
                        + "[]&locale=en&utm_source=coinmarketcap.com&utm_medium=widget&utm_campaign=chart&utm_term=BTC"
        );
    }

    private void disableButton(AppCompatButton oneDay, AppCompatButton oneMonth, AppCompatButton oneWeek, AppCompatButton fourHour, AppCompatButton oneHour) {
        oneDay.setBackground(null);
        oneMonth.setBackground(null);
        oneWeek.setBackground(null);
        fourHour.setBackground(null);
        oneHour.setBackground(null);
    }

    private void loadChart(CryptoCurrency item) {
        binding.detaillChartWebView.getSettings().setJavaScriptEnabled(true);
        binding.detaillChartWebView.loadUrl(
                "https://s.tradingview.com/widgetembed/?frameElementId=tradingview_76d87&symbol=" +
                        "USD&interval=D&hidesidetoolbar=1&hidetoptoolbar=1&symboledit=1&saveimage=1&toolbarbg=" +
                        "F1F3F6&studies=[]&hideideas=1&theme=Dark&style=1&timezone=Etc%2FUTC&studies_overrides={}&overrides={}&enabled_features=[]&disabled_features=" +
                        "[]&locale=en&utm_source=coinmarketcap.com&utm_medium=widget&utm_campaign=chart&utm_term=BTC"
        );

    }
    private void setUpDetails(CryptoCurrency data) {
        binding.detailSymbolTextView.setText(data.getSymbol());
        Glide.with(requireContext())
                .load("https://s2.coinmarketcap.com/static/img/coins/64x64/" + data.getId() + ".png")
                .thumbnail(Glide.with(requireContext()).load(R.drawable.spinner))
                .into(binding.detailImageView);

        binding.detailPriceTextView.setText(String.format("$%.4f", data.getQuotes().get(0).getPrice()));

        if (data.getQuotes().get(0).getPercentChange24h() > 0) {
            binding.detailChangeTextView.setTextColor(requireContext().getResources().getColor(R.color.green));
            binding.detailChangeImageView.setImageResource(R.drawable.ic_caret_up);
            binding.detailChangeTextView.setText("+ " + String.format("%.02f", data.getQuotes().get(0).getPercentChange24h()) + "%");
        } else {
            binding.detailChangeImageView.setImageResource(R.drawable.ic_caret_down);
            binding.detailChangeTextView.setTextColor(requireContext().getResources().getColor(R.color.red));
            binding.detailChangeTextView.setText(String.format("%.02f", data.getQuotes().get(0).getPercentChange24h()) + "%");
        }
    }


    private void navigate(){

        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);}
}