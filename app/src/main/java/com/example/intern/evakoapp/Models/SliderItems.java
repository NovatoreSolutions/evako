package com.example.intern.Evakoapp.Models;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by intern on 24/01/2017.
 */
public class SliderItems extends AppCompatActivity {

    public SliderItems(Context context){

        this.context = context;
    }
    Context context;
    String BackImage;
    String Logo;
    String Name;
    String Description;
    String ItemPrice;


    public String getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(String itemPrice) {
        ItemPrice = itemPrice;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }

    public String getBackImage() {
        return BackImage;
    }

    public void setBackImage(String backImage) {
        BackImage = backImage;
    }



}
