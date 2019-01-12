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
    var mSymbols:List<Coin> = emptyList()
    var mAdapterViewPager: MyPagerAdapter? = null
    var mHandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initializing viewpager and viewmodel
        val vpPager = findViewById<View>(R.id.vpPager) as ViewPager
        this.mBitViewModel = ViewModelProviders.of(this).get(BitViewModel::class.java)

        //initialize viewpager/adapter
        mAdapterViewPager = MyPagerAdapter(supportFragmentManager,mSymbols)
        vpPager.adapter = mAdapterViewPager

        //fab that goes to Addsymbol activity
        fab.setOnClickListener { view ->
            startActivity(Intent(this, AddSymbol::class.java))
        }

        //Observer of the allcoins list that reacts on changes in the database
        mBitViewModel.allCoins!!.observe(this, Observer { current ->

            //local list gets updated and set in the viewmodel
            mSymbols = current!!
            mBitViewModel.setList(mSymbols)

            // set the list in the viewpager so that it knows what coins need to be displayed
            mAdapterViewPager!!.setSymbols(mSymbols)
            mAdapterViewPager!!.notifyDataSetChanged()
        })
       // start the background update task
        getAll()
    }

    override fun onResume() {
        super.onResume()
        mAdapterViewPager!!.notifyDataSetChanged()
    }


    fun getAll() {
        mHandler = Handler()
        mHandler!!.postDelayed(mRunnable, 10000)
    }

    private val mRunnable = object : Runnable {

        override fun run() {
            println("Call asynctask")
            for (item in mSymbols) {
                mBitViewModel.requestData(item.name)
            }
            mHandler!!.postDelayed(this, 10000)
        }
    }
}




