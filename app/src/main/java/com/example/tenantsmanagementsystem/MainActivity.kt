package com.example.tenantsmanagementsystem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantsmanagementsystem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val name = binding.tenantNameEditText.text.toString()

            // Lab Challenge 1: Show an error if the name field is empty[cite: 15]
            if (name.isEmpty()) {
                binding.tenantNameEditText.error = "Tenant name is required"
                return@setOnClickListener
            }

            val phone = binding.phoneEditText.text.toString()
            val rent = binding.rentEditText.text.toString()

            // Pass the data object to the XML layout[cite: 13, 14]
            val tenant = Tenant(name, phone, rent)
            binding.tenant = tenant

            // Lab Challenge 4: Clear the three input fields after saving[cite: 15]
            binding.tenantNameEditText.text.clear()
            binding.phoneEditText.text.clear()
            binding.rentEditText.text.clear()
        }
    }
}