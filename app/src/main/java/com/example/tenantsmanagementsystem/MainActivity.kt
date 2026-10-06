package com.example.tenantsmanagementsystem



import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantsmanagementsystem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var lastTenant: Tenant? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Read email passed from LoginActivity and display welcome Toast
        val loggedInEmail = intent.getStringExtra("LOGGED_IN_EMAIL")
        if (!loggedInEmail.isNullOrEmpty()) {
            Toast.makeText(this, "Logged in as $loggedInEmail", Toast.LENGTH_SHORT).show()
        }

        // Save Button Listener with validation for all 3 fields
        binding.saveButton.setOnClickListener {
            val name = binding.tenantNameEditText.text.toString().trim()
            val phone = binding.phoneEditText.text.toString().trim()
            val rent = binding.rentEditText.text.toString().trim()

            var isValid = true

            if (name.isEmpty()) {
                binding.tenantNameEditText.error = "Required"
                isValid = false
            }
            if (phone.isEmpty()) {
                binding.phoneEditText.error = "Required"
                isValid = false
            }
            if (rent.isEmpty()) {
                binding.rentEditText.error = "Required"
                isValid = false
            }

            if (!isValid) return@setOnClickListener

            val tenant = Tenant(name, phone, rent)
            binding.tenant = tenant
            lastTenant = tenant
            Toast.makeText(this, "Tenant saved successfully", Toast.LENGTH_SHORT).show()
        }

        // Call Tenant Button Listener (Implicit Intent)
        binding.saveButton.setOnClickListener {
            val tenant = lastTenant
            if (tenant == null) {
                Toast.makeText(this, "Save a tenant first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${tenant.phone}"))
            startActivity(intent)
        }
    }
}