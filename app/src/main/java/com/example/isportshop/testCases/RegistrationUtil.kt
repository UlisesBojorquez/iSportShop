package com.example.isportshop.testCases

object RegistrationUtil {

    /**
     * The input is not valid if...
     * ...inputs are empty
     * ...user already exist
     * ...the confirm password is not the same as the real password
     * ...the password contains less than 6 digits
     * ...the email doesn't exist
     * ...the email has an invalid format
     * */
    fun validateRegistrationInput(
        name : String,
        lastname : String,
        email : String,
        password : String,
        confirmPassword : String
    ) : Boolean {

        if(name.isEmpty() || lastname.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            return false
        }

        if(password.count { it.isDigit() } < 6){
            return false
        }
        if(password != confirmPassword){
            return false
        }

        return true
    }

}