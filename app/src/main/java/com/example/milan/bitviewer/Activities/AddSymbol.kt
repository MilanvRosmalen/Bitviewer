package com.example.milan.bitviewer.Activities

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.example.milan.bitviewer.BitViewModel
import com.example.milan.bitviewer.R
import java.util.ArrayList

class AddSymbol(): AppCompatActivity(), AdapterView.OnItemSelectedListener {
    //private val model = viewmodel
    private var mSpinner: Spinner? = null
    private var mStatus: String? = null
    private lateinit var mBitViewModel: BitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_item)
        mSpinner = findViewById(R.id.spinner)
        val button: Button = findViewById(R.id.button)
        // Spinner click listener
        mSpinner!!.onItemSelectedListener = this
        // Spinner Drop down elements
        val symbols = ArrayList<String>()
        symbols.add("XBTUSD")
        symbols.add("ETHUSD")
        symbols.add("EOSH19")
        symbols.add("XRPH19")
        symbols.add("ADAH19")
        symbols.add("BCHH19")
        symbols.add("LTCH19")
        symbols.add("TRXH19")
        // Creating adapter for spinner
        val dataAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, symbols)
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        this.mBitViewModel = ViewModelProviders.of(this).get(BitViewModel::class.java)
        // attaching data adapter to spinner
        mSpinner!!.adapter = dataAdapter
        button.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            mBitViewModel.requestData(mStatus!!)

            startActivity(intent)
        }

    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        // On selecting a spinner item
        mStatus = parent.getItemAtPosition(position).toString()

    }
    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}