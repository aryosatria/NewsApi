package com.aryosatria.newsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.api.load
import coil.size.Scale
import com.aryosatria.newsapi.databinding.ActivityDetailBinding
import com.aryosatria.newsapi.model.ArticlesItem

class DetailActivity : AppCompatActivity() {

    //variable untuk menangkap data yang dikirimkan oleh MainActivity melalui CdvNewsHeadLineAdapter
    companion object {
        const val DETAIL_NEWS = "DETAIL_NEWS"
    }

    //untuk menampilkan view , karena kita akan menampilkan detail activity maka yang di extend ActivityDetailBinding
    //Jika yang di extend MainActivity maka yang di extend ActivityMainBinding
    //intinya tinggal tambahin tulisan binding di akhir
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        //untuk menampilkan data yang dikirimkan oleh MainActivity melalui CdvHeadLineAdapter
        val data = intent.getParcelableExtra(DETAIL_NEWS) as ArticlesItem

        binding.run {
            setContentView(root)

            //untuk membuild actionbar
            setSupportActionBar(toolBar)

            //untuk menampilkan tombol back
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            title = data.title
            imgToolbar.apply {
                load(data.urlToImage) {
                    scale(Scale.FILL)
                }
                contentDescription = data.description
            }
            //untuk get content
            txtContent.text = data.content

            //untuk get publishAt
            txtDate.text = data.publishedAt
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}