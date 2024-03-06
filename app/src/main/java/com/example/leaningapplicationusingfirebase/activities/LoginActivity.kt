package com.example.leaningapplicationusingfirebase.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.example.leaningapplicationusingfirebase.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity(), View.OnFocusChangeListener, View.OnClickListener,
    View.OnKeyListener {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.edtEmailLogin.onFocusChangeListener = this
        binding.edtPasswordLogin.onFocusChangeListener = this
        addEvents()

    }

    private fun addEvents() {
        binding.btnLogin.setOnClickListener {
            var auth = FirebaseAuth.getInstance()
            var lginEmail = binding.edtEmailLogin.text.toString().trim()
            var loginPassword = binding.edtPasswordLogin.text.toString().trim()
            auth.signInWithEmailAndPassword(lginEmail, loginPassword)
                .addOnSuccessListener { authResult ->
                    Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@LoginActivity, UploadActivity::class.java))
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this@LoginActivity, "Login Failed", Toast.LENGTH_SHORT).show()
                }

        }
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if (view != null) {
            when (view.id) {
                binding.edtEmailLogin.id -> {
                    if (!hasFocus) {
                        validateEmail()
                    }
                }

                binding.edtPasswordLogin.id -> {
                    if (!hasFocus) {
                        validatePassword()
                    }
                }

            }
        }
    }
    private fun validateFullFields(): Boolean {
        return validateEmail() && validatePassword()
    }
    private fun validatePassword(): Boolean {
        val password = binding.edtPasswordLogin.text.toString().trim()
        return if (password.isEmpty()) {
            binding.edtPasswordLogin.error = "Vui lòng nhập password"
            false
        } else {
            binding.edtPasswordLogin.error = null
            true
        }
    }

    private fun validateEmail(): Boolean {
        val email = binding.edtEmailLogin.text.toString().trim()
        return if (email.isEmpty()) {
            binding.edtEmailLogin.error = "Vui lòng nhập email đăng nhập"
            false
        } else {
            binding.edtEmailLogin.error = null
            true
        }
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        TODO("Not yet implemented")
    }
}