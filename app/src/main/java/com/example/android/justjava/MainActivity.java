package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "you cannot have more than 100 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }

    public void decrement(View view) {

        if (quantity == 1) {
            Toast.makeText(this, "you cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        }
    }

    public void submitOrder(View view) {
        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();
        CheckBox ChocolateCheckbox = (CheckBox) findViewById(R.id.Chocolate_checkbox);
        boolean hasChocolate = ChocolateCheckbox.isChecked();
        EditText NameText = (EditText) findViewById(R.id.Name);
        String Name = NameText.getText().toString();
        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(hasWhippedCream, hasChocolate, Name);


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + Name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView)
                findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int price = quantity * 5;

        if (addWhippedCream == true) {
            price = quantity * (6);
        }
        if (addChocolate == true) {
            price = quantity * 7;
        }
        if (addChocolate == true && addWhippedCream == true) {
            price = quantity * 8;
        }
        return price;
    }


    private String createOrderSummary(boolean addWhippedCream, boolean addChocolate, String Name) {
        String priceMessage = getString(R.string.order_summary_name) + Name;
        priceMessage = "\n" + getString(R.string.order_summary_whipped_cream) + addWhippedCream;
        priceMessage = "\n" + getString(R.string.order_summary_chocolate) + addChocolate;
        priceMessage = "\n"+ getString(R.string.order_summary_quantity) + quantity;
        priceMessage = "\n"+getString(R.string.Total) + calculatePrice(addWhippedCream, addChocolate);
        priceMessage =  "\n" + getString(R.string.thank_you);
        return priceMessage;
    }

    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }
}