package com.example.robertoluizveigajunior.cartoys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.robertoluizveigajunior.cartoys.database.DBHelper;
import com.example.robertoluizveigajunior.cartoys.helper.CarHelper;
import com.example.robertoluizveigajunior.cartoys.model.Car;
import com.example.robertoluizveigajunior.cartoys.utils.Constants;


public class CarRegisterActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private CarHelper helper;
    private Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_register);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initCommon();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if( car != null ){
            helper.setRegister(car);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;

            case R.id.new_car:
                if( helper.formValidate() ){
                    saveCar();
                }
                return false;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveCarEditCar(){
        if( car == null ){
            saveCar();
        }else{
            editCar();
        }
    }

    private void saveCar(){
        Car car = helper.getCarFromForm();
        dbHelper.saveCar(car);
        dbHelper.close();
        Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show();
        finish();
    }

    private void editCar(){
        Car carHelper = helper.getCarFromForm();

        car.setName( carHelper.getName() );
        car.setModel( carHelper.getModel() );
        car.setColor( carHelper.getColor() );
        car.setScale( carHelper.getScale() );

        dbHelper.edit(car);
        Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show();
        finish();
    }

    private void initCommon(){
        helper = new CarHelper(this);
        dbHelper = new DBHelper(this);
        car = (Car) getIntent().getSerializableExtra(Constants.CAR);
    }
}
