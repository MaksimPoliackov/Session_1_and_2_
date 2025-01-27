package com.valance.oechappfinal.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.valance.oechappfinal.R
import com.valance.oechappfinal.databinding.ForgotPasswordBinding

class ForgotFragment: Fragment() {

    private lateinit var binding: ForgotPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ForgotPasswordBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.SignIn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.my_nav_host_fragment, RegistrationFragment())
                .commit()
        }
        applyEmailMask(binding.email)

        binding.email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                checkAllFields()
            }
        })
    }
    private fun applyEmailMask(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val email = s.toString()
                val atIndex = email.indexOf('@')
                if (atIndex != -1) {
                    val maskedEmail = maskEmail(email, atIndex)
                    editText.removeTextChangedListener(this)
                    editText.setText(maskedEmail)
                    editText.setSelection(maskedEmail.length)
                    editText.addTextChangedListener(this)
                }
            }
        })
    }
    private fun maskEmail(email: String, atIndex: Int): String {
        val username = email.substring(0, atIndex)
        val domain = email.substring(atIndex)
        val maskedUsername = username.replace(Regex("[^@\\s]"), "*")
        return maskedUsername + domain
    }
    private fun checkAllFields() {

        val email = binding.email.text.toString().trim()

        val isValid =  email.isNotEmpty()

        if (isValid) {
            goToOTPFragment()
            binding.appCompatTextView9.setBackgroundResource(R.drawable.button_signin1)
        } else {
            binding.appCompatTextView9.setBackgroundResource(R.drawable.button_signin)
        }
    }

    private fun goToOTPFragment(){
        binding.appCompatTextView9.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.my_nav_host_fragment, OTPFragment())
                .commit()
        }
    }
}