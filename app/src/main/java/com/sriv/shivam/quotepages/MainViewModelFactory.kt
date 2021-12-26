package com.sriv.shivam.quotepages

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/* The purpose of this class is to create the object of MainViewModel class when a parameter is required
   to be passed from the MainActivity. This class is inherited from ViewModelProvider which is
   actually responsible for object creation of MainViewModel.
 */
class MainViewModelFactory(val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(context) as T
    }
}