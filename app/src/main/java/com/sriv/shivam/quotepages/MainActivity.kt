package com.sriv.shivam.quotepages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.sriv.shivam.quotepages.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize MainViewModel
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(applicationContext))[MainViewModel::class.java]
        // Set initial quote
        setQuote(mainViewModel.getQuote())

        // On NEXT click
        binding.next.setOnClickListener {
            if(mainViewModel.index == mainViewModel.quoteListSize) {
                Toast.makeText(this, "This is the last quote!", Toast.LENGTH_SHORT).show()
            }
            else {
                setQuote(mainViewModel.nextQuote())
            }
        }

        // On PREVIOUS click
        binding.previous.setOnClickListener {
            if(mainViewModel.index == 0) {
                Toast.makeText(this, "This is the first quote!", Toast.LENGTH_SHORT).show()
            }
            else {
                setQuote(mainViewModel.previousQuote())
            }
        }

        // On share button click
        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().text)
            startActivity(intent)
        }
    }

    // This function will fetch the quote from Quote model according to the index and set into views
    private fun setQuote(quote: Quote)  {
        binding.quoteText.text = quote.text
        binding.quoteAuthor.text = quote.author
    }
}