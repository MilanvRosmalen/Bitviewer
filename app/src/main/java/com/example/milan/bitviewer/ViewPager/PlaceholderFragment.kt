package com.example.milan.bitviewer.ViewPager

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.milan.bitviewer.BitViewModel
import com.example.milan.bitviewer.R


class PlaceholderFragment : Fragment() {


    // Store instance variables
    private var symbol: String? = null
    private var page: Int = 0
    private lateinit var mBitViewModel: BitViewModel
    private var mTvSymbol: TextView? = null
    private var mTvPrice:TextView? = null
    // Store instance variables based on arguments passed
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.mBitViewModel = ViewModelProviders.of(this).get(BitViewModel::class.java)
        symbol = arguments!!.getString("Symbol")
        page = arguments!!.getInt("Page")
    }

    // Inflate the view for the fragment based on layout XML
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View?{

        val view = inflater.inflate(R.layout.placeholder_fragment, container, false)
        mTvSymbol = view.findViewById(R.id.tv_symbol) as TextView
        mTvPrice = view.findViewById(R.id.tv_price) as TextView


        mBitViewModel.allPrices!!.observe(this, Observer { current ->
            // Update the cached copy of the words in the adapter.
            val price = current!!.findLast { it -> it.coin == symbol }

            mTvPrice!!.text = price!!.price.toBigDecimal().toString()

            when (price!!.movement){
                "ZeroPlusTick" -> mTvPrice!!.setTextColor(Color.GREEN)
                "PlusTick"->  mTvPrice!!.setTextColor(Color.GREEN)
                "MinusTick" -> mTvPrice!!.setTextColor(Color.RED)
                "ZeroMinusTick" ->  mTvPrice!!.setTextColor(Color.RED)
            }
            mTvSymbol!!.text = price!!.coin
        })

        return view
    }

    companion object {

        // newInstance constructor for creating fragment with arguments
        fun newInstance(page:Int, symbol: String): PlaceholderFragment {
            val fragmentFirst = PlaceholderFragment()
            val args = Bundle()
            args.putInt("Page", page)
            args.putString("Symbol", symbol)
            fragmentFirst.setArguments(args)
            return fragmentFirst
        }

    }
}




