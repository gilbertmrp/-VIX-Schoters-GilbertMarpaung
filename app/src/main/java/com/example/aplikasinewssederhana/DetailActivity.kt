package com.example.aplikasinewssederhana

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.aplikasinewssederhana.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding

    companion object {
        const val EXTRA_TITLE = "title"
        const val EXTRA_DESCRIPTION = "description"
        const val EXTRA_URL = "url"
        const val EXTRA_URLTOIMAGE = "urlToImage"
        const val EXTRA_PUBLISHEDAT = "publishedAt"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#C63434")))
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.red_light))
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        title = "Detail Activity"

        val titles = intent.getStringExtra(EXTRA_TITLE)
        val description = intent.getStringExtra(EXTRA_DESCRIPTION)
        val url = intent.getStringExtra(EXTRA_URL)
        val urlToImage = intent.getIntExtra(EXTRA_URLTOIMAGE,0)
        val publishedAt = intent.getStringExtra(EXTRA_PUBLISHEDAT)

        binding.detailTitle.text = titles
        binding.detailDescription.text = description
        binding.detailUrl.text = url
        binding.imageView.setImageResource(urlToImage).toString()
        binding.detailPublisedAt.text = publishedAt
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}