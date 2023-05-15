package com.example.cryptocurrency.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cryptocurrency.R;
import com.example.cryptocurrency.databinding.CurrencyItemLayoutBinding;
import com.example.cryptocurrency.fragment.HomeFragment;
import com.example.cryptocurrency.fragment.HomeFragmentDirections;
import com.example.cryptocurrency.fragment.MarketFragment;
import com.example.cryptocurrency.fragment.MarketFragmentDirections;
import com.example.cryptocurrency.models.CryptoCurrency;

import java.util.List;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.MarketViewHolder> {
    private Context context;
    private List<CryptoCurrency> list;
    private String type;

    public MarketAdapter(Context context, List<CryptoCurrency> list, String type) {
        this.context = context;
        this.list = list;
        this.type = type;
    }

    @NonNull
    @Override
    public MarketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.currency_item_layout, parent, false);
        return new MarketViewHolder(view);
    }

    public void updateData(List<CryptoCurrency> dataItem) {
        list = dataItem;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MarketViewHolder holder, int position) {
        CryptoCurrency item = list.get(position);
        holder.binding.currencyNameTextView.setText(item.getName());
        holder.binding.currencySymbolTextView.setText(item.getSymbol());

        Glide.with(context).load("https://s2.coinmarketcap.com/static/img/coins/64x64/" + item.getId() + ".png")
                .thumbnail(Glide.with(context).load(R.drawable.spinner))
                .into(holder.binding.currencyImageView);

        Glide.with(context).load("https://s3.coinmarketcap.com/generated/sparklines/web/7d/usd/" + item.getId() + ".png")
                .thumbnail(Glide.with(context).load(R.drawable.spinner))
                .into(holder.binding.currencyChartImageView);

        holder.binding.currencyPriceTextView.setText(String.format("$%.02f", item.getQuotes().get(0).getPrice()));

        if (item.getQuotes().get(0).getPercentChange24h() > 0) {
            holder.binding.currencyChangeTextView.setTextColor(ContextCompat.getColor(context, R.color.green));
            holder.binding.currencyChangeTextView.setText("+ " + String.format("%.02f", item.getQuotes().get(0).getPercentChange24h()) + "%");
        } else {
            holder.binding.currencyChangeTextView.setTextColor(ContextCompat.getColor(context, R.color.red));
            holder.binding.currencyChangeTextView.setText(String.format("%.02f", item.getQuotes().get(0).getPercentChange24h()) + "%");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type == "home") {
                Navigation.findNavController(view).navigate(
                        HomeFragmentDirections.actionHomeFragment2ToDetailsFragment().setData(item)
                );} else if (type == "market") {

                    Navigation.findNavController(view).navigate(
                            MarketFragmentDirections.actionMarketFragment2ToDetailsFragment().setData(item)
                    );
                }
            }
        });



        }

    public static class MarketViewHolder extends RecyclerView.ViewHolder {
        CurrencyItemLayoutBinding binding;

        public MarketViewHolder(View view) {
            super(view);
            binding = CurrencyItemLayoutBinding.bind(view);
        }


    }
}