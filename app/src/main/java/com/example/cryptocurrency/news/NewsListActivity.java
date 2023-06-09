package com.example.cryptocurrency.news;


import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.example.cryptocurrency.R;
import com.example.cryptocurrency.rest.News;
import com.example.cryptocurrency.rest.NewsService;
import com.grizzly.rest.Model.RestResults;


import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * Created by Ryan on 12/28/2017.
 */

public class NewsListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private NewsListAdapter adapter;
    private RecyclerView recyclerView;
    private AppCompatActivity mActivity;
    private Toolbar mToolbar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Observable<News[]> newsObservable;

    
    public void getNewsObservable(int whatToDo, boolean cache){

        //Example of framework isolation by using observables
        //An standard Rx Action.
        Action1<News[]> subscriber = new Action1<News[]>() {
            @Override
            public void call(News[] newsRestResults) {

                List<NewsItem> myNews = new ArrayList<>();
                if(newsRestResults != null && newsRestResults.length > 0){

                    Parcelable recyclerViewState;
                    recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();
                    for(News news: newsRestResults){
                        NewsItem newsItem = new NewsItem(news.getTitle(),
                                news.getUrl(), news.getBody(),
                                news.getImageurl(), news.getSource(),
                                news.getPublishedOn());
                        if(!myNews.contains(newsItem)) myNews.add(newsItem);
                    }
                    adapter.setData(myNews);
                    recyclerView.setAdapter(adapter);
                    swipeRefreshLayout.setRefreshing(false);
                    recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
                    Log.e("News", "call successful");
                }
                else{
                    swipeRefreshLayout.setRefreshing(false);
                    Log.e("News", "call failed");
                }
            }
        };

        switch (whatToDo){
            case 1:
                //Wrapped observable call
                NewsService.getObservableNews(this, true, new Action1<RestResults<News[]>>() {
                    @Override
                    public void call(RestResults<News[]> newsRestResults) {
                        Parcelable recyclerViewState;
                        recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();
                        if(newsRestResults.isSuccessful()){
                            List<NewsItem> myNews = new ArrayList<>();
                            for(News news: newsRestResults.getResultEntity()){
                                NewsItem newsItem = new NewsItem(news.getTitle(),
                                        news.getUrl(), news.getBody(),
                                        news.getImageurl(), news.getSource(),
                                        news.getPublishedOn());
                                if(!myNews.contains(newsItem)) myNews.add(newsItem);
                            }
                            adapter.setData(myNews);
                            recyclerView.setAdapter(adapter);
                            swipeRefreshLayout.setRefreshing(false);
                            recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
                        }else{
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                });
                break;
            case 2:
                //Observable instance from EasyRest
                if(newsObservable == null) {
                    newsObservable = NewsService.getPlainObservableNews(this, cache).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread());
                }
                newsObservable.subscribe(subscriber);
                break;

                default:
                    //Wrapped observable call
                    NewsService.getObservableNews(this, true, new Action1<RestResults<News[]>>() {
                        @Override
                        public void call(RestResults<News[]> newsRestResults) {
                            Parcelable recyclerViewState;
                            recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();
                            if(newsRestResults.isSuccessful()){
                                List<NewsItem> myNews = new ArrayList<>();
                                for(News news: newsRestResults.getResultEntity()){
                                    NewsItem newsItem = new NewsItem(news.getTitle(),
                                            news.getUrl(), news.getBody(),
                                            news.getImageurl(), news.getSource(),
                                            news.getPublishedOn());
                                    if(!myNews.contains(newsItem)) myNews.add(newsItem);
                                }
                                adapter.setData(myNews);
                                recyclerView.setAdapter(adapter);
                                swipeRefreshLayout.setRefreshing(false);
                                recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
                            }else{
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        }
                    });
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);


        mActivity = this;
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout_recycler);
        recyclerView = findViewById(R.id.newsListRecyclerView);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        swipeRefreshLayout.setColorSchemeResources(R.color.teal_200);
        adapter = new NewsListAdapter(new ArrayList<NewsItem>());
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        getNewsObservable(2, false);
    }




    //TODO: An advantage about using observables is how easily they allow to avoid lifecycle crashes.
    //If we were using a listener, there's a chance the callback runs at a moment when accesing some
    // elements (like widgets) is illegal/impossible. While we can void listeners to avoid that,
    // depending on the framework, that may not be possible. Also, Rx observers aren't part of a
    // particular library, so they allow for greater isolation between your app's layers.
    @Override
    public void onPause(){
        super.onPause();
        //Here, we essentially tell RX to ignore any subscriptions done in the UI thread
        if(newsObservable!=null) newsObservable.unsubscribeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void onResume(){
        super.onResume();
        //Here, we make the activity call the news service everytime it cames back from being paused
        if(swipeRefreshLayout!=null){
            swipeRefreshLayout.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            swipeRefreshLayout.setRefreshing(true);
                                            getNewsObservable(2, true);
                                        }
                                    }
            );
        }else {
            onRefresh();
        }
    }
}
