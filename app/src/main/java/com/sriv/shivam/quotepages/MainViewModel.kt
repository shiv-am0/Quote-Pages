package com.sriv.shivam.quotepages

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context: Context): ViewModel() {
    private var quoteList: Array<Quote> = emptyArray()
    var index = 0
    var quoteListSize: Int = 0

    init {
        quoteList = loadQuoteFromAssets()
    }

    private fun loadQuoteFromAssets(): Array<Quote> {
        // Open the quotes.json file
        val inputStream = context.assets.open("quotes.json")
        // Get size of json file
        val size: Int = inputStream.available()
        quoteListSize = size
        Log.i("quote", "$quoteListSize")
        // Create a ByteArray of size of inputStream to store quotes in read mode
        val buffer = ByteArray(size)
        // Open file stored in inputStream in read with buffer
        inputStream.read(buffer)
        inputStream.close()
        // Convert data into String from buffer
        val json = String(buffer, Charsets.UTF_8)
        // Create a Gson object to parse the json
        val gson = Gson()
        return gson.fromJson(json, Array<Quote>::class.java)
    }

    fun getQuote() = quoteList[index]

    fun nextQuote() = quoteList[++index]

    fun previousQuote() = quoteList[--index]
}