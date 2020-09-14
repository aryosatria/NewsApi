package com.aryosatria.newsapi

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.size.Scale
import com.aryosatria.newsapi.databinding.CdvNewsHeadlineBinding
import com.aryosatria.newsapi.model.ArticlesItem

class CdvNewsHeadlineAdapter : RecyclerView.Adapter<CdvNewsHeadlieVH>(){

    //untuk mengambil data di dalam model article item
    private val listData = ArrayList<ArticlesItem>()

    //fungsi ini berfungsi untuk add data ke dalam recyclerview
    fun addData(items: List<ArticlesItem>){
        listData.clear()
        listData.addAll(items)
        notifyDataSetChanged()
    }

    //berfungsi untuk menginflate atau menerapkan operasi yang di buat kedalam Layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CdvNewsHeadlieVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CdvNewsHeadlineBinding.inflate(layoutInflater, parent, false)
        return CdvNewsHeadlieVH(binding)
    }

    //digunakan untuk mengetahui panjang/banyak data(size) guna kebutuhan Looping
    override fun getItemCount(): Int {
        return listData.size
    }

    //digunakan untuk posisikan widget pada layout model
    override fun onBindViewHolder(holder: CdvNewsHeadlieVH, position: Int) {
        holder.bind(listData[position])
    }
}

class CdvNewsHeadlieVH(private val binding: CdvNewsHeadlineBinding) :
    RecyclerView.ViewHolder(binding.root){
    fun bind(article: ArticlesItem){
        binding.run {
            txtTitle.text = cropText(article.title?: "Tidak ada judul")
            txtSubtitle.text = article.publishedAt
            imgHeadline.apply {
                load(article.urlToImage){
                    scale(Scale.FILL)
                }
                contentDescription = article.description
            }
            // untuk melakukan aksi klik pada gambar
            root.setOnClickListener {
                val intent = Intent(it.context, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.DETAIL_NEWS, article) // DETAIL_NEWS BERFUNGSI SEBAGAI VARIABLE YANG AKAN  DIKIRIMKAN KE DETAIL ACTIVITY
                }
                it.context.startActivity(intent)
            }
        }
    }
    private fun cropText(text: String): String {
        return text.take(50) + if (text.length > 50)"..." else ""
    }
}
