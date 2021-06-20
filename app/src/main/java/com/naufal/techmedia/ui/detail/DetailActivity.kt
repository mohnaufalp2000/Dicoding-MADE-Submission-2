package com.naufal.techmedia.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.naufal.techmedia.R
import com.naufal.techmedia.core.domain.model.News
import com.naufal.techmedia.databinding.ActivityDetailBinding
import maes.tech.intentanim.CustomIntent
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private val mDetailViewModel: DetailViewModel by viewModel()

    companion object{
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val detail = intent.getParcelableExtra<News>(EXTRA_DATA)
        populateDetail(detail)
        toolbarSetup()
    }

    private fun toolbarSetup() {
        setSupportActionBar(binding.tbDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.tbDetail.setNavigationOnClickListener {
            finish()
        }
    }

    private fun populateDetail(detail: News?) {
        detail?.let {
            binding.webviewDetail.apply {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    webViewClient = WebViewClient()
                    loadUrl(detail.url)
                }
            }
            var state = detail.isFavorite
            setFavorite(state)
            binding.btnFavorite.setOnClickListener {
                val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
                val date = Date()

                state = !state
                mDetailViewModel.setFavoriteNews(detail, state, dateFormat.format(date))
                setFavorite(state)
            }
        }
    }

    private fun setFavorite(state: Boolean) {
        if (state){
            binding.btnFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding.btnFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    override fun finish() {
        super.finish()
        CustomIntent.customType(this, "right-to-left")

    }

}