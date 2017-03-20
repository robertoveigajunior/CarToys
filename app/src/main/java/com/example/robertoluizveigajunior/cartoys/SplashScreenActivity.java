package com.example.robertoluizveigajunior.cartoys;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.robertoluizveigajunior.cartoys.utils.Preferences;
import com.example.robertoluizveigajunior.cartoys.model.User;
import com.example.robertoluizveigajunior.cartoys.utils.Constants;
import com.example.robertoluizveigajunior.cartoys.database.DBHelper;
import com.example.robertoluizveigajunior.cartoys.api.UserInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {

    private DBHelper helper;
    private ImageView image;
    private final Context context = this;
    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        preferences = new Preferences(this);
        startAnimation();
        startDatabase();
        getUser();
    }

    private void startAnimation() {
        ImageView img = (ImageView)findViewById(R.id.imageSplash);
        img.setBackgroundResource(R.anim.anim);
        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
        frameAnimation.start();
    }

    private void startDatabase(){
        helper = new DBHelper(context);
    }

    private void getUser(){
        UserInterface api = UserInterface.retrofit.create(UserInterface.class);
        Call<User> callApi = api.getUser();

        callApi.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if( preferences.isLogged() ){
                    Intent home = new Intent(context, HomeActivity.class);
                    startActivity(home);
                    finish();
                }else{
                    User user= response.body();
                    helper.saveUser( user );

                    Intent login = new Intent(context, LoginActivity.class);
                    login.putExtra(Constants.USER, user);
                    startActivity(login);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(context, R.string.erro_api, Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
