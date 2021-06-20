package com.naufal.techmedia.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.naufal.techmedia.core.ui.NewsAdapter
import com.naufal.techmedia.favorite.databinding.ActivityFavoriteBinding
import com.naufal.techmedia.ui.detail.DetailActivity
import maes.tech.intentanim.CustomIntent
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val binding by lazy { ActivityFavoriteBinding.inflate(layoutInflater) }
    private var newsAdapter = NewsAdapter()
    private val mFavoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        newsAdapter.onItemClick = {
            val intent = Intent(applicationContext, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, it)
            startActivity(intent)
            CustomIntent.customType(this, "left-to-right")
        }

        mFavoriteViewModel.favoriteNews.observe(this, { favorite ->
            if (favorite.isNotEmpty()){
                favorite.let { newsAdapter.setNews(it) }
                showRecyclerView()
            }
            else {
                binding.apply {
                    imgNoFavorite.visibility = View.VISIBLE
                    rvFavorite.visibility = View.GONE
                }
            }
        })

        toolbarSetup()
    }

    private fun toolbarSetup() {
        setSupportActionBar(binding.tbFavorite)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.tbFavorite.setNavigationOnClickListener {
            finish()
        }
    }

    private fun showRecyclerView() {
        binding.imgNoFavorite.visibility = View.GONE
        binding.rvFavorite.apply {
            visibility = View.VISIBLE
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }
    }

}