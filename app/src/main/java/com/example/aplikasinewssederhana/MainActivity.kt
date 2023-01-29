package com.example.aplikasinewssederhana

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplikasinewssederhana.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var newsApi : NewsAPI
    private lateinit var adapter : NewsAdapter
    private lateinit var toggle: ActionBarDrawerToggle
    private var selectedArticle : Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toggle = ActionBarDrawerToggle(this,binding.drawerLayout,R.string.open,R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_bookmark ->{

                }
            }
            true
        }

        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#C63434")))
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.red_light))

        setRecyclerView()
        getTopHeadLines()
    }

    fun onArticleClick(article : Article) {
        selectedArticle = article

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setRecyclerView() {
        adapter = NewsAdapter(arrayListOf())
        binding.rvDataNews.adapter = adapter
        binding.rvDataNews.layoutManager = LinearLayoutManager(this)
    }

    private fun getTopHeadLines() {
        newsApi = RetrofitHelper.getNewsApi()
        newsApi.getData("us","business", "5bdca1d44ce446b18ee5fec63bc21add")
            .enqueue(object : Callback<News>{
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    if(response.isSuccessful) {
                        val result = response.body()
                        Log.d("Debug", result.toString())
                        showData(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    Log.d("Debug", t.toString())
                }

            })
    }

    private fun showData(data: News) {
        val results = data.articles
        adapter.setData(results)
    }

}