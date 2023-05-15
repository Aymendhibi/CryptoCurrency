package com.example.cryptocurrency.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cryptocurrency.R;
import com.example.cryptocurrency.databinding.TopCurrencyLayoutBinding;
import com.example.cryptocurrency.models.CryptoCurrency;

import java.util.List;

public class TopMarketAdapter extends RecyclerView.Adapter<TopMarketAdapter.TopMarketViewHolder> {
    private Context context;
    private List<CryptoCurrency> list;

    public TopMarketAdapter(Context context, List<CryptoCurrency> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TopMarketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.top_currency_layout, parent, false);
        return new TopMarketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopMarketViewHolder holder, int position) {
        CryptoCurrency item = list.get(position);
        holder.binding.topCurrencyNameTextView.setText(item.getName());
        Glide.with(context)
                .load("https://s2.coinmarketcap.com/static/img/coins/64x64/" + item.getId() + ".png")
                .thumbnail(Glide.with(context).load(R.drawable.spinner))
                .into(holder.binding.topCurrencyImageView);
        if (item.getQuotes().get(0).getPercentChange24h() > 0) {
            holder.binding.topCurrencyChangeTextView.setTextColor(ContextCompat.getColor(context, R.color.green));
            holder.binding.topCurrencyChangeTextView.setText("+ " + String.format("%.02f", item.getQuotes().get(0).getPercentChange24h()) + "%");
        } else {
            holder.binding.topCurrencyChangeTextView.setTextColor(ContextCompat.getColor(context, R.color.red));
            holder.binding.topCurrencyChangeTextView.setText(String.format("%.02f", item.getQuotes().get(0).getPercentChange24h()) + "%");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class TopMarketViewHolder extends RecyclerView.ViewHolder {
        TopCurrencyLayoutBinding binding;

        public TopMarketViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = TopCurrencyLayoutBinding.bind(itemView);
        }
    }
}