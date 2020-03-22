package com.example.automatedutilitiesapp.helper

import android.util.Patterns

class Validators {
    companion object{

        fun validateName(name: String): Boolean{
            return name.length > 5
        }

        fun validateEmail(email: String): Boolean{
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun validatePasswordLength(password: String): Boolean{
            return password.length >= 5
        }

        fun validatePasswordConfirmation(password: String, confirmPassword: String): Boolean {
            return password == confirmPassword && validatePasswordLength(
                password
            ) && validatePasswordLength(
                confirmPassword
            )
        }

        fun validatePresentationLength(presentation: String): Boolean{
            return presentation.length > 15
        }

        fun validateCountryLength(country: String): Boolean{
            return country.length > 3
        }

        fun validateAge(age: String): Boolean{
            return age.isNotEmpty() && age.toInt() >= 16
        }

        fun isValidUser(
            name: String,
            email: String,
            password: String,
            confirmPassword: String
        ): Boolean {
            return validateName(name) &&
                    validateEmail(email) &&
                    validatePasswordLength(password) &&
                    validatePasswordLength(confirmPassword) &&
                    validatePasswordConfirmation(password, confirmPassword)
        }

        fun validProfileData(
            presentation: String,
            name: String,
            age: String,
            country: String
        ): Boolean {
            return validatePresentationLength(presentation) &&
                    validateName(name) &&
                    validateAge(age) &&
                    validateCountryLength(country)
        }

    }
}