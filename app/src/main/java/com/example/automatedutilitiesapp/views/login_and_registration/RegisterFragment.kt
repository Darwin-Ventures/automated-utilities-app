package com.example.automatedutilitiesapp.views.login_and_registration

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.automatedutilitiesapp.R
import com.example.automatedutilitiesapp.databinding.FragmentRegisterBinding
import com.example.automatedutilitiesapp.di.components.CustomDialog
import com.example.automatedutilitiesapp.di.components.Messaging
import com.example.automatedutilitiesapp.helper.USERS_REFERENCE
import com.example.automatedutilitiesapp.helper.Validators
import com.example.automatedutilitiesapp.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.jakewharton.rxbinding2.view.RxView
import java.util.concurrent.TimeUnit
import kotlinx.android.synthetic.main.fragment_register.*
import org.koin.android.ext.android.inject


class RegisterFragment : Fragment() {

    private val messaging: Messaging by inject()
    private val customDialog: CustomDialog by inject()
    private val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegisterBinding.inflate(inflater)
        firebaseAuth = FirebaseAuth.getInstance()

        registerUserListener()
        navigateToLoginFragment(binding.alreadyRegisteredButton)

        // Inflate the layout for this fragment
        return binding.root
    }

    /**
     * Get user's data and validate them
     */
    private fun registerUserListener(){
        RxView.clicks(binding.registerButton).map {
            val name = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val confirmPassword = binding.passwordConfirmEditText.text.toString()

            if (validateUser(name, email, password, confirmPassword) && Validators.isValidUser(
                    name,
                    email,
                    password,
                    confirmPassword
                )){
                createUserOnFirebase(email, password)
            }
        }.throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe()
    }

    /**
     * Check is user's registration data are correct
     */
    private fun  validateUser(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean{
        var flag = true
        if(!Validators.validateName(name)){
            binding.nameEditText.error = getString(R.string.short_name_error)
            flag = false
        }
        if(!Validators.validateEmail(email)){
            binding.emailEditText.error = getString(R.string.email_error)
            flag = false
        }
        if(!Validators.validatePasswordLength(password)){
            binding.passwordEditText.error = getString(R.string.password_length_error)
            flag = false
        }
        if(!Validators.validatePasswordConfirmation(password, confirmPassword)){
            binding.passwordConfirmEditText.error = getString(R.string.password_mismatch_error)
            flag = false
        }

        return  flag
    }

    /**
     * Create user in firebase database
     */
    private fun createUserOnFirebase(email: String, password: String){
        val customDialog = customDialog.progressDialog(this.context!!, getString(R.string.registering))

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            val currentUser = firebaseAuth.currentUser
            val reference = firebaseDatabase.getReference(USERS_REFERENCE)
            val user = User(
                nameEditText.text.toString(),
                currentUser!!.uid,
                email
            )
            reference.child(currentUser.uid).setValue(user)
            customDialog.cancel()

            messaging.showToast("success", getString(R.string.registered_successfully))
            navigate()
        }.addOnFailureListener {
            customDialog.cancel()
            messaging.showToast("error", it.message.toString())
        }
    }

    /**
     * Navigates to loginFragment from registerFragment
     */
    private fun navigate() {
        when (findNavController().currentDestination!!.id) {
            R.id.registerFragment -> findNavController().navigate(R.id.action_registerFragment_to_mainFragment)
        }
    }

    /**
     * Navigates to loginFragment
     */
    private fun navigateToLoginFragment(view: View){
        RxView.clicks(view).map {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }.throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe()
    }

    /**
     * Set color of part alreadyRegisteredButton text
     */
    private fun setRegisteredButtonTextColor(){
        val spannable = SpannableString(getString(R.string.already_member_login_me))
        spannable.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.colorPrimaryDark)),
            18,
            26,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        alreadyRegisteredButton.text = spannable
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRegisteredButtonTextColor()
    }

}
