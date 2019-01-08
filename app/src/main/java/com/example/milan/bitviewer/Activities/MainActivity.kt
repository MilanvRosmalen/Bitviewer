package com.example.milan.bitviewer.Activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.milan.bitviewer.BitViewModel
import com.example.milan.bitviewer.Database.Objects.Coin
import com.example.milan.bitviewer.ViewPager.MyPagerAdapter
import com.example.milan.bitviewer.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var mBitViewModel: BitViewModel
    var symbols:List<Coin> = emptyList()

    var mAdapterViewPager: MyPagerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val vpPager = findViewById<View>(R.id.vpPager) as ViewPager
        this.mBitViewModel = ViewModelProviders.of(this).get(BitViewModel::class.java)
        mBitViewModel.allCoins!!.observe(this, Observer { current ->
            // Update the cached copy of the words in the adapter.
            mBitViewModel.setList(symbols)
            symbols = current!!
            println(symbols)
            mAdapterViewPager!!.setSymbols(symbols)
            mAdapterViewPager!!.notifyDataSetChanged()

            //println(symbols)
        })

        fab.setOnClickListener { view ->
            startActivity(Intent(this, AddSymbol::class.java))
        }
        mAdapterViewPager = MyPagerAdapter(supportFragmentManager,symbols)
        vpPager.adapter = mAdapterViewPager
        useHandler()
    }

    override fun onResume() {
        super.onResume()
        mAdapterViewPager!!.notifyDataSetChanged()
    }

    fun getall(){
        for (item in symbols) {
            mBitViewModel.requestData(item.name)
        }

    }

    var mHandler: Handler? = null
    fun useHandler() {
        mHandler = Handler()
        mHandler!!.postDelayed(mRunnable, 10000)
    }

    private val mRunnable = object : Runnable {

        override fun run() {
            println( "Call asynctask")
            getall()
            mHandler!!.postDelayed(this, 10000)
        }
    }
}




