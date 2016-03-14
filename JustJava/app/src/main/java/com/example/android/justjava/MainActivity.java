package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int numberOfCoffees = 2;
    public void submitOrder(View view) {
        CheckBox checkBox1 = (CheckBox) findViewById(R.id.cream_check);
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.chocolate_check);
        boolean hasWhippedCream = checkBox1.isChecked();
        boolean hasChocolate = checkBox2.isChecked();
        int price = calculatePrice(hasWhippedCream, hasChocolate);
        createOrderSummary(numberOfCoffees, hasWhippedCream, hasChocolate, price);
    }

    public int calculatePrice(boolean whipped, boolean chocolated){
        int price = 5;
        if (whipped)
            price++;
        if (chocolated)
            price += 2;
        return price;
    }

    public void createOrderSummary(int cups, boolean whipped, boolean chocolated, int price) {
        StringBuilder summary = new StringBuilder();
        EditText nameBox = (EditText) findViewById(R.id.name_field);
        String name = nameBox.getText().toString();
        summary.append("Name: " + name + "\n");
        summary.append("Add whipped cream? " + whipped + "\n");
        summary.append("Add chocolate? " + chocolated + "\n");
        summary.append("Quantity: " + cups + "\n");
        summary.append("Total: $" + cups*price + "\n");
        summary.append("Thank you!");
        sendEmail(summary.toString());
    }

    public void sendEmail(String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void increment(View view) {
        if (numberOfCoffees < 100) {
            numberOfCoffees++;
            display(numberOfCoffees);
        }
        else {
            Toast.makeText(this, "Too many cups!", Toast.LENGTH_SHORT).show();
        }
    }

    public void decrement(View view) {
        if (numberOfCoffees > 1) {
            numberOfCoffees--;
            display(numberOfCoffees);
        }
        else {
            Toast.makeText(this, "You must have at least 1 cup!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

}
