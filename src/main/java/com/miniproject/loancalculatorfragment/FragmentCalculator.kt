package com.miniproject.loancalculatorfragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import java.lang.NumberFormatException
import kotlinx.android.synthetic.main.fragment_calculator.*
import kotlin.math.pow

class FragmentCalculator : Fragment() {


    private var currentLoanAmount = 0.00
    private var currentInterestRate = 0
    companion object {
        private const val LOAN_AMOUNT = "LOAN_AMOUNT"
        private const val INTEREST_RATE = "INTEREST_RATE"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_calculator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            currentLoanAmount = 0.00
            currentInterestRate = 5
        }
        else {
            currentLoanAmount = savedInstanceState.getDouble(LOAN_AMOUNT)
            currentInterestRate = savedInstanceState.getInt(INTEREST_RATE)
        }
        loanAmountEditText.addTextChangedListener(loanAmountEditTextWatcher)
        interestRateSeekBar.setOnSeekBarChangeListener(interestRateSeekBarListener)
    }

    private fun updateValues() {
        val yearFiveEMI = calculateEMI(5)
        val yearFiveTotal = yearFiveEMI * 5.00 * 12.00
        // update the GUI
        yearFiveTotalEditText.setText(String.format("%.02f", yearFiveTotal))
        yearFiveEMIEditText.setText(String.format("%.02f", yearFiveEMI))

        val yearTenEMI = calculateEMI(10)
        val yearTenTotal = yearTenEMI * 10.00 * 12.00
        // update the GUI
        yearTenTotalEditText.setText(String.format("%.02f", yearTenTotal))
        yearTenEMIEditText.setText(String.format("%.02f", yearTenEMI))

        val yearFifteenEMI = calculateEMI(15)
        val yearFifteenTotal = yearFifteenEMI * 15.00 * 12.00
        yearFifteenTotalEditText.setText(String.format("%.02f", yearFifteenTotal))
        yearFifteenEMIEditText.setText(String.format("%.02f", yearFifteenEMI))

        val yearTwentyEMI = calculateEMI(20)
        val yearTwentyTotal = yearTwentyEMI * 20.00 * 12.00
        // update the GUI
        yearTwentyTotalEditText.setText(String.format("%.02f", yearTwentyTotal))
        yearTwentyEMIEditText.setText(String.format("%.02f", yearTwentyEMI))

        val yearTwentyFiveEMI = calculateEMI(25)
        val yearTwentyFiveTotal = yearTwentyFiveEMI * 25.00 * 12.00
        // update the GUI
        yearTwentyFiveTotalEditText.setText(String.format("%.02f", yearTwentyFiveTotal))
        yearTwentyFiveEMIEditText.setText(String.format("%.02f", yearTwentyFiveEMI))

        val yearThirtyEMI = calculateEMI(30)
        val yearThirtyTotal = yearThirtyEMI * 30.00 * 12.00
        // update the GUI
        yearThirtyTotalEditText.setText(String.format("%.02f", yearThirtyTotal))
        yearThirtyEMIEditText.setText(String.format("%.02f", yearThirtyEMI))
    }

    private fun calculateEMI(years: Int): Double {
        if (currentInterestRate == 0) {
            return 0.00
        }
        val r = currentInterestRate / 1200.00
        val n = years * 12
        val power = ((1.00 + r).toDouble()).pow(n)
        return (currentLoanAmount * r * power) / (power - 1.00)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putDouble(LOAN_AMOUNT, currentLoanAmount)
        outState.putInt(INTEREST_RATE, currentInterestRate)
    }

    private val interestRateSeekBarListener = object: SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {

            currentInterestRate = progress
            interestRatePercentTextView.text = currentInterestRate.toString() + "%"
            updateValues()
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
        }
    }

    private val loanAmountEditTextWatcher = object: TextWatcher {
        override fun beforeTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {

            try {
                currentLoanAmount = text.toString().toDouble()
            }
            catch (e: NumberFormatException) {
                currentLoanAmount = 0.00
            }

            updateValues()
        }

        override fun afterTextChanged(editable: Editable?) {

        }
    }
}