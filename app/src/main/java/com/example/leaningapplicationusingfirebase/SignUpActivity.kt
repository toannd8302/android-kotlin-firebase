package com.example.leaningapplicationusingfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.example.leaningapplicationusingfirebase.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth


class SignUpActivity : AppCompatActivity(), View.OnFocusChangeListener, View.OnClickListener,
    View.OnKeyListener {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //code validate
        binding.edtEmail.onFocusChangeListener = this
        binding.edtFullName.onFocusChangeListener = this
        binding.edtPassword.onFocusChangeListener = this
        binding.edtConfirmPassword.onFocusChangeListener = this
        // Initialize Firebase Auth
        addEvents()
    }
    private fun addEvents() {
        binding.btnLoginTranfer.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnSignUp.setOnClickListener {
            val fullName = binding.edtFullName.text.toString()
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            val confirmPassword = binding.edtConfirmPassword.text.toString()
            try {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(
                                    baseContext, "User created successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)

                            }else{
                                Toast.makeText(
                                    baseContext, "User created failed",
                                    Toast.LENGTH_SHORT
                                ).show()
                                //Ở lại trang login
                            }
                        }
            }catch (e: Exception){
                Log.e("Error", e.message.toString())
            }
        }
    }
        private fun validateFullFields(): Boolean {
            return validateEmail() && validateUsername() && validatePassword() && validateConfirmPassword()
        }
        private fun validateEmail(): Boolean {
            val email = binding.edtEmail.text.toString().trim()
            return if (email.isEmpty()) {
                binding.edtEmail.error = "Vui lòng nhập tên đăng nhập"
                false
            } else {
                binding.edtEmail.error = null
                true
            }
        }

        private fun validateUsername(): Boolean {
            val userName = binding.edtFullName.text.toString().trim()
            return if (userName.isEmpty()) {
                binding.edtFullName.error = "Vui lòng nhập tên đăng nhập"
                false
            } else {
                binding.edtFullName.error = null
                true
            }
        }

        private fun validatePassword(): Boolean {
            val password = binding.edtPassword.text.toString().trim()
            return if (password.isEmpty()) {
                binding.edtPassword.error = "Vui lòng nhập mật khẩu"
                false
            } else {
                binding.edtPassword.error = null
                true
            }
        }

        private fun validateConfirmPassword(): Boolean {
            val confirmPassword = binding.edtConfirmPassword.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()
            return if (confirmPassword.isEmpty()) {
                binding.edtConfirmPassword.error = "Vui lòng nhập lại mật khẩu"
                false
            } else if (confirmPassword != password) {
                binding.edtConfirmPassword.error = "Mật khẩu không khớp"
                false
            } else {
                binding.edtConfirmPassword.error = null
                true
            }
        }
        override fun onFocusChange(view: View?, hasFocus: Boolean) {
            if (view != null) {
                when (view.id) {
                    binding.edtEmail.id -> {
                        if (!hasFocus) {
                            validateEmail()
                        }
                    }
                    binding.edtFullName.id -> {
                        if (!hasFocus) {
                            validateUsername()
                        }
                    }
                    binding.edtPassword.id -> {
                        if (!hasFocus) {
                            validatePassword()
                        }
                    }
                    binding.edtConfirmPassword.id -> {
                        if (!hasFocus) {
                            validateConfirmPassword()
                        }
                    }
                }
            }
        }
        override fun onClick(v: View?) {
            TODO("Not yet implemented")
        }
        override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
            TODO("Not yet implemented")
        }
    }
