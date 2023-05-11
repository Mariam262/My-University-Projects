package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Activity.MainActivity2;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class Signup extends AppCompatActivity implements View.OnClickListener {

    //TextInputLayout username, email, confirmpassword, pass, phonenum;
    Button reg;   //signup
    TextView t;   //login if have account
    DatabaseHelper databaseHelper;
    User user;


    TextInputLayout textInputLayoutName;
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;
    TextInputLayout textInputLayoutConfirmPassword;
    TextInputLayout textInputLayoutphonenum;

    TextInputEditText textInputEditTextName;
    TextInputEditText textInputEditTextEmail;
    TextInputEditText textInputEditTextPassword;
    TextInputEditText textInputEditTextConfirmPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);



        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputLayoutphonenum= (TextInputLayout) findViewById(R.id.textInputLayoutphonenum);

        reg = (Button) findViewById(R.id.signupbutton);   //registration  button
        t = (TextView) findViewById(R.id.textview8);// already have an acc

        databaseHelper = new DatabaseHelper(this);

        user = new User();

        textInputEditTextName=(TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail=(TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword=(TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword=(TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);


        reg.setOnClickListener(this);
        t.setOnClickListener(this);

    }

    /*public void getAllUser()
    {
        // Reading all contacts
        List<User> userDetails = databaseHelper.getAllUser();

        for (User users : userDetails) {
            String log = "email: " + users.getEmail()
                    + " ,: pass" + users.getPassword();

            // Writing Contacts to log
            Log.e("Details: ", log);
        }
    }*/

    private Boolean validateUsername() {
        String val = textInputLayoutName.getEditText().getText().toString();
        if (val.isEmpty()) {
            textInputLayoutName.setErrorEnabled(true);
            textInputLayoutName.setError("Field cannot be empty");
            return false;
        } else {
            textInputLayoutName.setError(null);
            textInputLayoutName.setErrorEnabled(false);
            return true;
        }

    }


    private Boolean validateEmail() {
        String val =textInputLayoutEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            textInputLayoutEmail.setError("Field cannot be empty");
            textInputLayoutEmail.setErrorEnabled(true);
            return false;
        } else if (!val.matches(emailPattern)) {
            textInputLayoutEmail.setError("Invalid email address");
            textInputLayoutEmail.setErrorEnabled(true);
            return false;
        } else {
            textInputLayoutEmail.setError(null);
            textInputLayoutEmail.setErrorEnabled(false);
            return true;
        }
    }


    private Boolean validatePhoneNo() {
        String val = textInputLayoutphonenum.getEditText().getText().toString();
        if (val.isEmpty()) {
            textInputLayoutphonenum.setErrorEnabled(true);
            textInputLayoutphonenum.setError("Field cannot be empty");
            return false;
        } else if (val.length() < 11 | val.length() > 11) {
            textInputLayoutphonenum.setErrorEnabled(true);
            textInputLayoutphonenum.setError("Invalid Phonenumber");
            return false;
        } else {
            textInputLayoutphonenum.setError(null);
            textInputLayoutphonenum.setErrorEnabled(false);
            return true;
        }
    }



    private Boolean validatePassword() {
        String val = textInputLayoutPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (val.isEmpty()) {
            textInputLayoutPassword.setErrorEnabled(true);
            textInputLayoutPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            textInputLayoutPassword.setErrorEnabled(true);
            textInputLayoutPassword.setError("Password is too weak");
            return false;
        } else {
            textInputLayoutPassword.setError(null);
            textInputLayoutPassword.setErrorEnabled(false);
            return true;
        }
    }




    /*private Boolean validateconfirmPassword() {
        String val = confirmpassword.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (val.isEmpty()) {
            confirmpassword.setErrorEnabled(true);
            confirmpassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            confirmpassword.setErrorEnabled(true);
            confirmpassword.setError("Password is too weak");
            return false;
        } else {
            confirmpassword.setError(null);
            confirmpassword.setErrorEnabled(false);
            return true;
        }
    }*/
    public boolean isPasswordMatches(){
        String value1 = textInputEditTextPassword.getText().toString().trim();
        String value2 = textInputEditTextConfirmPassword.getText().toString().trim();
        if (!value1.contentEquals(value2))
        {

            return false;
        }
        else {
            textInputLayoutPassword.setError("Password  do not match");
            textInputLayoutPassword.setErrorEnabled(false);
            textInputLayoutConfirmPassword.setErrorEnabled(false);
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textview8:    //already have accou
                Intent intent1 = new Intent(Signup.this, Login.class);
                startActivity(intent1);

                break;

            case R.id.signupbutton:     //register
                /*if (!validateconfirmPassword() | !validatePassword() | !validatePhoneNo() | !validateEmail() | !validateUsername())
                {
                    return;
                }
                else if (validateconfirmPassword() && validatePassword() && validatePhoneNo() && validateEmail() && validateUsername())
                {
                    Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Signup.this,Login.class);
                    startActivity(intent);
                }*/

                postDataToSQLite();

                break;

            default:
                break;


        }

    }



    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {

        if (!isPasswordMatches() | !validatePassword() | !validatePhoneNo() | !validateEmail() | !validateUsername())
        {
            return;
        }
        //if (isInputEditTextMatches() | validatePassword() | validatePhoneNo() | validateEmail() | validateUsername()) {


        if (!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {

            user.setUsername(textInputEditTextName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());

            databaseHelper.addUser(user);

            // Snack Bar to show success message that record saved successfully
            Toast.makeText(getApplicationContext(), "Record saved successfully", Toast.LENGTH_SHORT).show();
            emptyInputEditText();
            Intent intent = new Intent(Signup.this, Login.class);
            startActivity(intent);


        } else {
            // Snack Bar to show error message that record already exists
            Toast.makeText(getApplicationContext(), "Record already exists", Toast.LENGTH_SHORT).show();
        }
    }




    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
    }

}
