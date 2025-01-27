package com.valance.oechappfinal.ui.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.valance.oechappfinal.R
import com.valance.oechappfinal.databinding.OtpVerificationBinding

class VereficationFragment: Fragment() {

    private lateinit var binding: OtpVerificationBinding
    private lateinit var countDownTimer: CountDownTimer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OtpVerificationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countDownTimer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                val text = getString(R.string.resend_after, minutes, seconds)
                binding.timer.text = text
            }

            override fun onFinish() {
                binding.timer.text = getString(R.string.resend)
            }
        }
        countDownTimer.start()

    }
    override fun onDestroyView() {
        super.onDestroyView()
        countDownTimer.cancel()
    }
}