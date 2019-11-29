package com.elmaghraby.jetpackcomponents

import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.elmaghraby.jetpackcomponents.databinding.MainBinder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainBinder
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        binding.viewModel = viewModel
        setLiveDataObservers()
        setEventListeners()
    }

    private fun setLiveDataObservers() {
        viewModel.getData().observe(this, object : Observer<String> {
            override fun onChanged(string: String) {
                binding.textResult.text = string
            }
        })
    }

    private fun setEventListeners() {
        binding.buttonSendData.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                binding.viewModel!!.setData(
                    CustomModel(
                        binding.exitTextFirstName.text.toString(),
                        binding.exitTextLastName.text.toString()
                    )
                )
            }

        })
    }
}
