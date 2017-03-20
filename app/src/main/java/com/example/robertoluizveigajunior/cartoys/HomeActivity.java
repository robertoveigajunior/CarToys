package com.example.robertoluizveigajunior.cartoys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.robertoluizveigajunior.cartoys.model.Car;
import com.example.robertoluizveigajunior.cartoys.adapter.CarAdapter;
import com.example.robertoluizveigajunior.cartoys.database.DBHelper;
import com.example.robertoluizveigajunior.cartoys.utils.Constants;
import com.example.robertoluizveigajunior.cartoys.utils.Preferences;


import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , View.OnCreateContextMenuListener, View.OnClickListener,
        MenuItem.OnMenuItemClickListener {
    private Preferences prefs;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private DBHelper dbHelper;
    private TextView worning;
    private List<Car> carList;
    private Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initCommon();
        setToolbar();
        validateFloatingAction();
        validateNavDrawer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        buildFields();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.new_car:
                gotoCarRegister();
                break;

            case R.id.about:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;

            case R.id.logout:
                prefs.logout();
                isValid();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void isValid(){
        if( !prefs.isLogged() ){
            Intent login = new Intent(this, LoginActivity.class);
            startActivity(login);
            finish();
        }
    }

    private void initCommon(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        prefs = new Preferences(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        dbHelper = new DBHelper(this);
        worning = (TextView) findViewById(R.id.txt_msg_empty);
    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
    }

    private void validateFloatingAction(){
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoCarRegister();
            }
        });
    }

    private void validateNavDrawer(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void buildFields(){
        carList = dbHelper.getAllCars();

        showRecyclerView(carList.size());

        CarAdapter adapter = new CarAdapter(this, carList);
        recyclerView.setAdapter(adapter);
    }

    private void showRecyclerView(int listSize){
        if( listSize > 0 ){
            worning.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layout);
        }else{
            worning.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    private void gotoCarRegister(){
        Intent intent = new Intent(this, CarRegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        int position = recyclerView.getChildLayoutPosition(v);
        car = carList.get(position);

        menu.setHeaderTitle(car.getName());
        MenuItem editar = menu.add( getString(R.string.edit) );
        MenuItem deletar = menu.add( getString(R.string.delete) );

        editar.setOnMenuItemClickListener(this);
        deletar.setOnMenuItemClickListener(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        if( menuItem.getTitle().equals( getString(R.string.edit) ) ){
            edit();
        }else if( menuItem.getTitle().equals( getString(R.string.delete) ) ){
            delete();
        }

        return false;
    }

    @Override
    public void onClick(View view) {}

    private void edit(){
        Intent form = new Intent(this, CarRegisterActivity.class);
        form.putExtra(Constants.CAR, car);
        startActivity(form);
    }

    private void delete(){
        dbHelper.delete(car);
        Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show();
        finish();
        startActivity(getIntent());
    }
}
