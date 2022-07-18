package com.example.android.justjava;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
int quantity=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void increment(View view){
        quantity = quantity+1;
        display(quantity);
    }
    public void decrement(View view)
    {
        if(quantity>0){
            quantity = quantity - 1;
            display(quantity);
        }
    }

    public void submitOrder(View view) {
        int Price = quantity*10;
        String priceMessage = "Total= $" + Price;
        priceMessage = priceMessage + "\nthank you";
        displayMessage(priceMessage);
    }

     /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView)
        findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    private void displayPrice(int number){
        TextView priceTextView = (TextView)
        findViewById(R.id.Price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.Price_text_view);
        priceTextView.setText(message);
    }

}