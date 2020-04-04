package com.example.automatedutilitiesapp.views.login_and_registration

import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.getInputField
import com.afollestad.materialdialogs.input.input
import com.example.automatedutilitiesapp.R
import com.example.automatedutilitiesapp.databinding.FragmentLoginBinding
import com.example.automatedutilitiesapp.di.components.CustomDialog
import com.example.automatedutilitiesapp.di.components.Messaging
import com.example.automatedutilitiesapp.helper.Validators
import com.google.firebase.auth.FirebaseAuth
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.inject
import sun.bob.mcalendarview.MarkStyle.text
import java.util.concurrent.TimeUnit


class LoginFragment : Fragment() {

    private val messaging: Messaging by inject()
    private val customDialog: CustomDialog by inject()
    private val firebaseAuth = FirebaseAuth.getInstance()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)


        loginUserListener(binding.loginButton)
        navigateToRegistrationFragment(binding.notMemberButton)
        forgottenPasswordListener(binding.forgetPasswordButton)

        // Inflate the layout for this fragment
        return binding.root
    }

    /**
     * Handles forgotten password request
     */
    private fun forgottenPasswordListener(view: View) {
        RxView.clicks(view).map {
            MaterialDialog(this.context!!).show {
                input(
                    hint = getString(R.string.email),
                    waitForPositiveButton = true,
                    allowEmpty = false,
                    inputType = InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS
                )
                positiveButton(text = getString(R.string.request_password)){
                    this.getInputField().text.toString()
                    requestPasswordReset(text)
                }
                negativeButton(R.string.CANCEL)
            }
        }.throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe()

    }

    /**
     *  Sends password reset request
     */
    private fun requestPasswordReset(email: String) {
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    messaging.showToast("success", getString(R.string.check_your_mail))
                }
                else {
                    messaging.showToast("error", getString(R.string.error_occurred))
                }
            }
    }

    /**
     * Valid of credentials
     */
    private fun loginUserListener(view: View){
        RxView.clicks(view).map {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if(validateUserCredentials(email, password)){
                requestFirebaseCredentialValidation(email, password)
            }
        }.throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe()
    }

    /**
     * Login user
     */
    private fun requestFirebaseCredentialValidation(email: String, password: String) {
        val progressDialog = customDialog.progressDialog(this.context!!, getString(R.string.logging_in))
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    navigateToMain()
                    progressDialog.cancel()
                    messaging.showToast("success",getString(R.string.logged_in))
                }else{
                    progressDialog.cancel()
                    messaging.showToast("error", it.exception!!.message.toString())
                }
            }
    }

    /**
     * Validate user data
     */
    private fun validateUserCredentials(email: String, password: String): Boolean {
        var flag = true
        if (!Validators.validateEmail(email)) {
            binding.emailEditText.error = getString(R.string.email_error)
            flag = false
        }
        if (!Validators.validatePasswordLength(password)) {
            binding.passwordEditText.error = getString(R.string.password_length_error)
            flag = false
        }
        return flag
    }

    /**
     * Navigate to mainFragment
     */
    private fun navigateToMain(){
        when (findNavController().currentDestination!!.id) {
            R.id.loginFragment -> findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }
    }

    /**
     * Navigate to registerFragment
     */
    private fun navigateToRegistrationFragment(view: View){
        RxView.clicks(view).map {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }.throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe()
    }

    /**
     * Set color of part notMemberButton text
     */
    private fun setNotMemberButtonTextColor() {
        val spannable = SpannableString(getString(R.string.not_a_member_sign_up_now))
        spannable.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.colorPrimaryDark)),
            14,
            25,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        notMemberButton.text = spannable
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setNotMemberButtonTextColor()
    }

}
