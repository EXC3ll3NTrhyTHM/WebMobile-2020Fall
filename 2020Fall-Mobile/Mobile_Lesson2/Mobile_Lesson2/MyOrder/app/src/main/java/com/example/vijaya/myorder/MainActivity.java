package com.example.vijaya.myorder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String MAIN_ACTIVITY_TAG = "MainActivity";
    final int PEPPERONI_PRICE = 1;
    final int CHEESE_PRICE = 2;
    final int PIZZA_PRICE = 10;
    int quantity = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {

        String orderSummaryMessage = createOrderSummary();
        Intent viewOrderSummary = new Intent(MainActivity.this, SummaryActivity.class);
        viewOrderSummary.putExtra("message", orderSummaryMessage);
        viewOrderSummary.setType("text/plain");
        startActivity(viewOrderSummary);

        // Write the relevant code for making the buttons work(i.e implement the implicit and explicit intents

    }

    public void sendEmail(View view) {
        String mailto = "mailto:bob@example.org" +
                "?cc=" + "alice@example.com" +
                "&subject=" + Uri.encode("Pizza!!!!") +
                "&body=" + Uri.encode(createOrderSummary());

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SENDTO);
        sendIntent.setData(Uri.parse(mailto));

        if (sendIntent.resolveActivity(getPackageManager()) !=null){
            startActivity(sendIntent);
        }
    }

    private String boolToString(boolean bool) {
        return bool ? (getString(R.string.yes)) : (getString(R.string.no));
    }

    private String createOrderSummary() {

        // get user input
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();

        // check if whipped cream is selected
        CheckBox pepperoni = (CheckBox) findViewById(R.id.pepperoni_checked);
        boolean hasPepperoni = pepperoni.isChecked();

        // check if chocolate is selected
        CheckBox cheese = (CheckBox) findViewById(R.id.cheese_checked);
        boolean hasCheese = cheese.isChecked();

        // calculate and store the total price
        float totalPrice = calculatePrice(hasPepperoni, hasCheese);


        String orderSummaryMessage = getString(R.string.order_summary_name, userInputName) + "\n" +
                getString(R.string.order_summary_pepperoni, boolToString(hasPepperoni)) + "\n" +
                getString(R.string.order_summary_cheese, boolToString(hasCheese)) + "\n" +
                getString(R.string.order_summary_quantity, quantity) + "\n" +
                getString(R.string.order_summary_total_price, totalPrice) + "\n" +
                getString(R.string.thank_you);
        return orderSummaryMessage;
    }

    /**
     * Method to calculate the total price
     *
     * @return total Price
     */
    private float calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int basePrice = PIZZA_PRICE;
        if (hasWhippedCream) {
            basePrice += PEPPERONI_PRICE;
        }
        if (hasChocolate) {
            basePrice += CHEESE_PRICE;
        }
        return quantity * basePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method increments the quantity of coffee cups by one
     *
     * @param view on passes the view that we are working with to the method
     */

    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select less than one hundred cups of coffee");
            Context context = getApplicationContext();
            String lowerLimitToast = getString(R.string.too_much_coffee);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;
        }
    }

    /**
     * This method decrements the quantity of coffee cups by one
     *
     * @param view passes on the view that we are working with to the method
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select atleast one cup of coffee");
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.too_little_coffee);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;
        }
    }
}