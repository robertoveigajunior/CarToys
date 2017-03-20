package com.example.robertoluizveigajunior.cartoys.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.example.robertoluizveigajunior.cartoys.R;
import com.example.robertoluizveigajunior.cartoys.model.Car;

/**
 * Created by robertoluizveigajunior on 18/03/17.
 */

public class CarAdapter extends RecyclerView.Adapter{
    private List<Car> carList;
    private Context context;

    public CarAdapter(Context context, List<Car> carList) {
        this.context = context;
        this.carList = carList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_car, parent, false);
        view.setOnCreateContextMenuListener((View.OnCreateContextMenuListener) context);
        CarViewHolder holder = new CarViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        CarViewHolder holder = (CarViewHolder) viewHolder;
        Car car = carList.get(position);

        holder.txtName.setText(car.getName());
        holder.txtModel.setText(car.getModel());
        holder.txtColor.setText(car.getColor());
        holder.txtScale.setText(car.getScale());
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public class CarViewHolder extends RecyclerView.ViewHolder {
        final TextView txtName;
        final TextView txtModel;
        final TextView txtColor;
        final TextView txtScale;

        public CarViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.item_name);
            txtModel = (TextView) itemView.findViewById(R.id.item_model);
            txtColor = (TextView) itemView.findViewById(R.id.item_color);
            txtScale = (TextView) itemView.findViewById(R.id.item_scale);
        }
    }
}
