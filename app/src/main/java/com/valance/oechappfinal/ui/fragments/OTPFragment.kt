package com.valance.oechappfinal.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.valance.oechappfinal.R
import com.valance.oechappfinal.databinding.OtpVerificationBinding

class OTPFragment: Fragment() {
    private lateinit var binding:OtpVerificationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = OtpVerificationBinding.inflate(inflater,container,false)
        return  binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val allFieldsFilled = binding.editText1.text!!.isNotEmpty() &&
                        binding.editText2.text!!.isNotEmpty() &&
                        binding.editText3.text!!.isNotEmpty() &&
                        binding.editText4.text!!.isNotEmpty() &&
                        binding.editText5.text!!.isNotEmpty() &&
                        binding.editText6.text!!.isNotEmpty()


                binding.appCompatTextView9.isEnabled = allFieldsFilled
                if (allFieldsFilled) {
                    goToNewPasswordFragment()
                    binding.appCompatTextView9.setBackgroundResource(R.drawable.button_signin1)
                } else {
                }

            }
        }

        binding.editText1.addTextChangedListener(textWatcher)
        binding.editText2.addTextChangedListener(textWatcher)
        binding.editText3.addTextChangedListener(textWatcher)
        binding.editText4.addTextChangedListener(textWatcher)
        binding.editText5.addTextChangedListener(textWatcher)
        binding.editText6.addTextChangedListener(textWatcher)

    }

    private  fun goToNewPasswordFragment(){
        binding.appCompatTextView9.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.my_nav_host_fragment, NewPasswordFragment())
                .commit()
        }
    }
}