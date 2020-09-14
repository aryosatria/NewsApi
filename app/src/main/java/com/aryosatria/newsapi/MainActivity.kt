package com.aryosatria.newsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.aryosatria.newsapi.databinding.ActivityMainBinding
import com.aryosatria.newsapi.model.ResponseNews
import retrofit2.Callback
import com.aryosatria.newsapi.service.RetrofitBuilder
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    //untuk menampilkan view
    private lateinit var binding: ActivityMainBinding

    //untuk mengaktifkan adapter
    private lateinit var adapterRv: CdvNewsHeadlineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.plant(Timber.DebugTree())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        adapterRv = CdvNewsHeadlineAdapter()
        binding.run {
            setContentView(root)
            setSupportActionBar(toolBar)

            //untuk membuild recyclerview
            mainRv.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = adapterRv
            }

            val call = RetrofitBuilder.getService().fetchHeadlines()
            call.enqueue(object : Callback<ResponseNews>{
                override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                    Timber.e(t)
                    Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_SHORT)
                }

                override fun onResponse(
                    call: Call<ResponseNews>,
                    response: Response<ResponseNews>
                ) {
                    response.body()?.articles?.let {
                        adapterRv.addData(it)
                    }
                }
            })
        }
    }
}