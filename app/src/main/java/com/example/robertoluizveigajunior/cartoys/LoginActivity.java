package com.example.robertoluizveigajunior.cartoys;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.robertoluizveigajunior.cartoys.model.User;
import com.example.robertoluizveigajunior.cartoys.utils.Constants;
import com.example.robertoluizveigajunior.cartoys.utils.Preferences;
import com.example.robertoluizveigajunior.cartoys.database.DBHelper;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    private User user;
    private EditText loginEdit, passwordEdit;
    private CheckBox ckSaveLogin;
    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initCommon();
        NavValidation();
    }

    private void initCommon(){
        user = (User) getIntent().getSerializableExtra(Constants.USER);
        loginEdit = (EditText) findViewById(R.id.edtxt_login);
        passwordEdit = (EditText) findViewById(R.id.edtxt_password);
        ckSaveLogin = (CheckBox) findViewById(R.id.save_login);
        preferences = new Preferences(this);
    }

    private void NavValidation(){
        if( preferences.isLogged() ){
            gotoHome();
        }
    }

    public void userLogin(View v){
        String login = loginEdit.getText().toString();
        String password = passwordEdit.getText().toString();

        if( isValid() ){
            if( user != null ){
                login(login, password);
            }else{
                find(login, password);
            }
        }
    }

    private boolean isValid(){
        boolean valid = true;

        if( loginEdit.getText().toString().equals("") ){
            loginEdit.setError( getString(R.string.login_empty) );
            valid = false;
        }
        if( passwordEdit.getText().toString().equals("") ){
            passwordEdit.setError( getString(R.string.password_empty) );
            valid = false;
        }

        return valid;
    }

    private void login(String login, String password){
        if( user.getLogin().equals(login) && user.getPassword().equals(password) ){

            if( ckSaveLogin.isChecked() ){
                preferences.saveLogin();
            }

            gotoHome();

        }else{
            Toast.makeText(this, R.string.erro_login, Toast.LENGTH_SHORT).show();
        }
    }

    private void find(String login, String password){
        DBHelper dbHelper = new DBHelper(this);
        if( dbHelper.getUserByLoginPassword(login, password) > 0 ){
            if( ckSaveLogin.isChecked() ){
                preferences.saveLogin();
            }

            gotoHome();
        }else{
            Toast.makeText(this, R.string.erro_login, Toast.LENGTH_SHORT).show();
        }
    }

    private void gotoHome(){
        Intent home = new Intent(this, HomeActivity.class);
        startActivity(home);
        finish();
    }


}

