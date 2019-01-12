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


class AddSymbol: AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var mStatus: String? = null
    private lateinit var mBitViewModel: BitViewModel

    //spinner elements
    private var mSymbols: ArrayList<String> = arrayListOf("XBTUSD","ETHUSD","EOSH19","XRPH19","ADAH19","BCHH19","LTCH19","TRXH19")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_item)
        val spinner:Spinner = findViewById(R.id.spinner)
        val button: Button = findViewById(R.id.button)
        this.mBitViewModel = ViewModelProviders.of(this).get(BitViewModel::class.java)

        // Spinner click listener
        spinner.onItemSelectedListener = this
        // Creating adapter for spinner
        val dataAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, mSymbols)
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // attaching data adapter to spinner
        spinner.adapter = dataAdapter

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

    }

}