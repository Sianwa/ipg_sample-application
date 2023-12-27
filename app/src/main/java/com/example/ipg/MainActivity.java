package com.example.ipg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.interswitchgroup.mobpaylib.Config;
import com.interswitchgroup.mobpaylib.MobPay;
import com.interswitchgroup.mobpaylib.api.model.TransactionResponse;
import com.interswitchgroup.mobpaylib.interfaces.TransactionFailureCallback;
import com.interswitchgroup.mobpaylib.interfaces.TransactionSuccessCallback;
import com.interswitchgroup.mobpaylib.model.Customer;
import com.interswitchgroup.mobpaylib.model.Merchant;
import com.interswitchgroup.mobpaylib.model.Payment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myButton = findViewById(R.id.button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to execute when the button is clicked
                final Merchant merchant = new Merchant("ISWKEN0001", "ISWKE");

                int lower = 100000000;
                int upper = 999999999;
                String transactionRef = String.valueOf((int) (Math.random() * (upper - lower)) + lower);
                final Payment payment = new Payment("963", transactionRef, "MOBILE", "3TLP0001", "CRD", "KES", "TZL_ANDR_132");
                payment.setPreauth("1");

                final Customer customer = new Customer("blabla");
                customer.setEmail("someone@somewhere.something");

                Config config = new Config();
                config.setIconUrl("YOUR ICONS URL");


               MobPay mobPay;// Instantiate the mobpay library object to make a payment and get the results in the callbacks
                try {
                    mobPay = MobPay.getInstance(MainActivity.this, "IKIAAB09E4ECCB92A508AEDE0D01B36472E27C309F05", "f4Vm0o0bXBQTIG4DAKrbs4urZZgJSieih/EiHjnf9/E=", config);

                    mobPay.pay(MainActivity.this,// The instance of an activity that will be active until the payment is completed
                            merchant,
                            payment,
                            customer,
                            new TransactionSuccessCallback() {
                                @Override
                                public void onSuccess(TransactionResponse transactionResponse) {
                                    // Your code to proceed to after success
                                }
                            },
                            new TransactionFailureCallback() {
                                @Override
                                public void onError(Throwable error) {
                                    // Code to handle failure
                                }
                            });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}