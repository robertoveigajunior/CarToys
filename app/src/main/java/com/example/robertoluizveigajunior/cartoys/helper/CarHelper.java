package com.example.robertoluizveigajunior.cartoys.helper;

import android.app.Activity;
import android.widget.EditText;

import com.example.robertoluizveigajunior.cartoys.R;
import com.example.robertoluizveigajunior.cartoys.model.Car;

/**
 * Created by robertoluizveigajunior on 18/03/17.
 */

public class CarHelper {

    private Car car;
    private Activity activity;
    private EditText nameEdit;
    private EditText modelEdit;
    private EditText colorEdit;
    private EditText scaleEdit;

    public CarHelper(Activity activity){
        this.activity = activity;
        nameEdit = (EditText) activity.findViewById(R.id.edtxt_name);
        modelEdit = (EditText) activity.findViewById(R.id.edtxt_model);
        colorEdit = (EditText) activity.findViewById(R.id.edtxt_color);
        scaleEdit = (EditText) activity.findViewById(R.id.edtxt_scale);
        car = new Car();
    }

    public Car getCarFromForm(){
        car.setName( nameEdit.getText().toString() );
        car.setModel( modelEdit.getText().toString() );
        car.setColor( colorEdit.getText().toString() );
        car.setScale( scaleEdit.getText().toString() );

        return car;
    }

    public void setRegister(Car car){
        nameEdit.setText(car.getName());
        modelEdit.setText(car.getModel());
        colorEdit.setText(car.getColor());
        scaleEdit.setText(car.getScale());
    }

    public boolean formValidate(){
        boolean valid = true;

        if( nameEdit.getText().toString().equals("") ){
            valid = false;
        }

        if( modelEdit.getText().toString().equals("") ){
            valid = false;
        }

        if( colorEdit.getText().toString().equals("") ){
            valid = false;
        }

        if( scaleEdit.getText().toString().equals("") ){
            valid = false;
        }

        return valid;
    }
}
