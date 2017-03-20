package com.example.robertoluizveigajunior.cartoys.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import com.example.robertoluizveigajunior.cartoys.model.Car;
import com.example.robertoluizveigajunior.cartoys.model.User;

/**
 * Created by robertoluizveigajunior on 18/03/17.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String NAME_BD = "carToys.db";
    public static final int VERSION_BD = 1;

    public static final String TB_USER = "users";
    public static final String TB_CARS = "cars";

    public DBHelper(Context context){
        super(context, NAME_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL( buildTbUser() );
        sqLiteDatabase.execSQL( buildTbCar() );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {}

    public void saveUser(User user){
        ContentValues values = new ContentValues();

        values.put("login", user.getLogin());
        values.put("password", user.getPassword());

        getWritableDatabase().insert(TB_USER, null, values);
    }

    public int getUserByLoginPassword(String login, String password){
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM " + TB_USER
                + " WHERE login='"+ login.trim() +"' AND password='"+ password.trim() +"';";

        Cursor c = getReadableDatabase().rawQuery(sql, null);

        while(c.moveToNext()){
            User user = new User();
            userList.add(user);
        }

        return userList.size();
    }

    private String buildTbUser(){
        String sql = "CREATE TABLE " + TB_USER
                + " (id INTEGER PRIMARY KEY, "
                + " login TEXT, "
                + " password TEXT);";

        return sql;
    }

    private String buildTbCar(){
        String sql = "CREATE TABLE " + TB_CARS
                + " (id INTEGER PRIMARY KEY, "
                + " name TEXT, "
                + " model TEXT, "
                + " color TEXT, "
                + " scale TEXT);";

        return sql;
    }

    public void saveCar(Car car){
        ContentValues values = new ContentValues();

        values.put("name", car.getName());
        values.put("model", car.getModel());
        values.put("color", car.getColor());
        values.put("scale", car.getScale());

        getWritableDatabase().insert(TB_CARS, null, values);
    }

    public List<Car> getAllCars(){
        List<Car> carList = new ArrayList<>();
        String sql = "SELECT * FROM " + TB_CARS + " ORDER BY id DESC;";

        Cursor c = getReadableDatabase().rawQuery(sql, null);

        while(c.moveToNext()){
            Car car = new Car();
            car.setId(c.getLong(c.getColumnIndex("id")));
            car.setName(c.getString(c.getColumnIndex("name")));
            car.setModel(c.getString(c.getColumnIndex("model")));
            car.setColor(c.getString(c.getColumnIndex("color")));
            car.setScale(c.getString(c.getColumnIndex("scale")));

            carList.add(car);
        }

        return carList;
    }

    public void edit(Car car){
        ContentValues values = new ContentValues();

        values.put("name", car.getName());
        values.put("model", car.getModel());
        values.put("color", car.getColor());
        values.put("scale", car.getScale());

        String[] args = { car.getId().toString() };
        getWritableDatabase().update(TB_CARS, values, "id=?", args);
    }

    public void delete(Car car){
        String[] args = { car.getId().toString() };
        getWritableDatabase().delete(TB_CARS, "id=?", args);
    }

}