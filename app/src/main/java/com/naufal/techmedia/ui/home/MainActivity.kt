package com.naufal.techmedia.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.naufal.techmedia.R
import com.naufal.techmedia.core.data.Resource
import com.naufal.techmedia.core.ui.NewsAdapter
import com.naufal.techmedia.databinding.ActivityMainBinding
import com.naufal.techmedia.ui.detail.DetailActivity
import maes.tech.intentanim.CustomIntent
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var newsAdapter = NewsAdapter()
    private val mMainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(binding.root)

        newsAdapter.onItemClick = {
            val intent = Intent(applicationContext, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, it)
            startActivity(intent)
            CustomIntent.customType(this, "left-to-right")
        }

        mMainViewModel.news.observe(this, { news ->
            if (news != null) {
                when (news) {
                    is Resource.Success -> {
                        news.data?.let { newsAdapter.setNews(it) }
                        showRecyclerView()
                    }
                    is Resource.Error -> {
                        binding.apply {
                            pbMain.visibility = View.GONE
                            rvNews.visibility = View.GONE
                            imgError.visibility = View.VISIBLE
                        }
                    }
                }
            }
        })
        toolbarSetup()
        refreshLayout()
    }

    private fun refreshLayout() {
        binding.swMain.setOnRefreshListener {
            Handler(mainLooper).postDelayed({
                binding.swMain.isRefreshing = false
                mMainViewModel.news.observe(this, { news ->
                    if (news != null) {
                        when (news) {
                            is Resource.Success -> {
                                news.data?.let { newsAdapter.setNews(it) }
                                showRecyclerView()
                            }
                            is Resource.Error -> {
                                binding.apply {
                                    pbMain.visibility = View.GONE
                                    rvNews.visibility = View.GONE
                                    imgError.visibility = View.VISIBLE
                                }
                            }
                        }
                    }
                })
            }, 3000)
        }
    }

    private fun toolbarSetup() {
        binding.tbMain.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_favorite -> {
                    startActivity(Intent(this, Class.forName("com.naufal.techmedia.favorite.FavoriteActivity")))
                    true
                }
                else -> false
            }
        }
    }

    private fun showRecyclerView() {
        binding.apply {
            pbMain.visibility = View.GONE
            imgError.visibility = View.GONE
        }
        binding.rvNews.apply {
            visibility = View.VISIBLE
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }
    }
}
