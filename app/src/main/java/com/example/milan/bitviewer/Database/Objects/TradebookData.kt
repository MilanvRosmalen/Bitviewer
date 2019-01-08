package com.example.milan.bitviewer.Database.Objects

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TradebookData {

    @SerializedName("timestamp")
    @Expose
    var timestamp: String? = null
    @SerializedName("symbol")
    @Expose
    var symbol: String? = null
    @SerializedName("side")
    @Expose
    var side: String? = null
    @SerializedName("size")
    @Expose
    var size: Int? = null
    @SerializedName("price")
    @Expose
    var price: Float? = null
    @SerializedName("tickDirection")
    @Expose
    var tickDirection: String? = null
    @SerializedName("trdMatchID")
    @Expose
    var trdMatchID: String? = null
    @SerializedName("grossValue")
    @Expose
    var grossValue: Int? = null
    @SerializedName("homeNotional")
    @Expose
    var homeNotional: Double? = null
    @SerializedName("foreignNotional")
    @Expose
    var foreignNotional: Double? = null

}