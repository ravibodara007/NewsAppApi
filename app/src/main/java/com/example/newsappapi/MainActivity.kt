package com.example.newsappapi

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newsappapi.API.ApiClient
import com.example.newsappapi.Adapter.NewsAdapter
import com.example.newsappapi.Interface.ApiInterface
import com.example.newsappapi.Modal.NewsModal
import com.example.newsappapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var List = ArrayList<NewsModal>()
    lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var apiinterface = ApiClient.getApiClient().create(ApiInterface::class.java)

        apiinterface.getNews(
            "tesla",
            "2023-06-19",
            "publishedAt",
            "26ffdb5c703246d2ab717ceee3bd0cc4"
        ).enqueue(object : Callback<NewsModal> {
            override fun onResponse(call: Call<NewsModal>, response: Response<NewsModal>) {

                List = response.body() as ArrayList<NewsModal>
                adapter = NewsAdapter(List)
                binding.rcvNews.layoutManager = GridLayoutManager(this@MainActivity, 1)
                binding.rcvNews.adapter = adapter
                binding.rcvNews.visibility = View.GONE

            }

            override fun onFailure(call: Call<NewsModal>, t: Throwable) {

            }

        })


    }
}