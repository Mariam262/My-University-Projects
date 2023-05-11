package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Activity.MainActivity2;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.nio.channels.AcceptPendingException;
import java.util.Arrays;
import java.util.List;

public class Login extends AppCompatActivity implements View.OnClickListener {

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    FloatingActionButton googleBtn;



    TextView tv;
    //TextInputLayout emailid, passwo;

    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;

    TextInputEditText textInputEditTextEmail;
    TextInputEditText textInputEditTextPassword;


    Button login;
    FloatingActionButton fb, google, twitter;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutemail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);


        login = (Button) findViewById(R.id.button);
        tv = (TextView) findViewById(R.id.textview);
        fb = (FloatingActionButton) findViewById(R.id.fab_facebook);
        google = (FloatingActionButton) findViewById(R.id.fab_google);
        twitter = (FloatingActionButton) findViewById(R.id.fab_twitter);
        databaseHelper = new DatabaseHelper(this);



        fb.setOnClickListener(this);
        google.setOnClickListener(this);
        twitter.setOnClickListener(this);
        tv.setOnClickListener(this);
        login.setOnClickListener(this);


        googleBtn = (FloatingActionButton) findViewById(R.id.fab_google);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null)
        {
            String personEmail = acct.getEmail();
            textInputEditTextEmail.setText(personEmail);
            navigateToSecondActivity();
        }

    }






    private Boolean validateEmail() {
        String val = textInputLayoutEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            textInputLayoutEmail.setErrorEnabled(true);
            textInputEditTextEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            textInputLayoutEmail.setErrorEnabled(true);
            textInputLayoutEmail.setError("Invalid email address");
            return false;
        } else {
            textInputLayoutEmail.setError(null);
            textInputLayoutEmail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = textInputLayoutPassword .getEditText().getText().toString();
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
            textInputLayoutPassword .setErrorEnabled(true);
            textInputLayoutPassword .setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            textInputLayoutPassword .setErrorEnabled(true);
            textInputLayoutPassword .setError("Password is not correct");
            return false;
        } else {
            textInputLayoutPassword .setError(null);
            textInputLayoutPassword .setErrorEnabled(false);
            return true;
        }
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_facebook:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.faceboook.com"));
                startActivity(browserIntent);



                break;

            case R.id.fab_google:
                // Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gmail.com"));
                //startActivity(intent);
                signIn();

                break;

            case R.id.fab_twitter:
                Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.twitter.com"));
                startActivity(browser);

                break;

            case R.id.textview:     //new user sign up
                Intent intent1 = new Intent(Login.this, Signup.class);
                startActivity(intent1);

                break;

            case R.id.button:     //login button

                verifyFromSQLite();





                break;
            default:
                break;


        }
    }

    void signIn(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data)
    {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            }
            catch (ApiException e)
            {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }

    }
    void navigateToSecondActivity(){
        finish();
        Intent intent = new Intent(Login.this,MainActivity2.class);
        startActivity(intent);
    }



    private void verifyFromSQLite() {

        if (!validatePassword() | !validateEmail())
        {
            return;
        }

        //if (validatePassword() | validateEmail()) {

        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {


            emptyInputEditText();
            databaseHelper.getAllProfiles();

            String e= textInputEditTextEmail.getText().toString();
            Intent intent = new Intent(Login.this, MainActivity2.class);

            intent.putExtra("email",e);
            //intent.putExtra("password",p);
            startActivity(intent);


            //Intent accountsIntent = new Intent(activity, UsersListActivity.class);
            //accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());

            //startActivity(accountsIntent);


        } else {
            // Snack Bar to show success message that record is wrong
            Toast.makeText(getApplicationContext(), "Record not matched", Toast.LENGTH_SHORT).show();
        }
    }



    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }




}