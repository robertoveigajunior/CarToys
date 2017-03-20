package com.example.robertoluizveigajunior.cartoys.model;

import java.io.Serializable;

/**
 * Created by robertoluizveigajunior on 18/03/17.
 */

public class Car implements Serializable {
    private Long id;
    private String name;
    private String model;
    private String color;
    private String scale;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }
}
