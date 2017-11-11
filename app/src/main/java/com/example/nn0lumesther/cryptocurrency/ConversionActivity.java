package com.example.nn0lumesther.cryptocurrency;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConversionActivity extends AppCompatActivity {

    String currencyCode;
    String currencyFullName;
    EditText btcEdit, ethEdit, flatEdit;
    Button btcConvertButton, ethConvertButton, flatConvertButton, closeButton;

    double btcRate;
    double ethRate;

    TextView moneyCodeView;
    TextView fullNameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);

        moneyCodeView = (TextView) findViewById(R.id.money_code_view);
        fullNameView = (TextView) findViewById(R.id.full_name_view);
        btcEdit = (EditText) findViewById(R.id.btc_edit);
        ethEdit = (EditText) findViewById(R.id.eth_edit);
        flatEdit = (EditText) findViewById(R.id.currency_edit);
        btcConvertButton = (Button) findViewById(R.id.btc_convert_button);
        ethConvertButton = (Button) findViewById(R.id.eth_convert_button);
        flatConvertButton = (Button) findViewById(R.id.flat_convert_button);
        closeButton = (Button) findViewById(R.id.button_close);

        closeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        currencyCode = intent.getStringExtra(Constants.EXTRA_CURRENCY);
        btcRate = intent.getDoubleExtra(Constants.EXTRA_BTC_RATE, 0);
        ethRate = intent.getDoubleExtra(Constants.EXTRA_ETH_RATE, 0);
        currencyFullName = getFullName(currencyCode);
        String conversionMessage = currencyFullName + " Conversion";

        moneyCodeView.setText(currencyCode);
        fullNameView.setText(conversionMessage);
        if(getActionBar() != null) getActionBar().setTitle(currencyFullName);
        if(getSupportActionBar() != null) getSupportActionBar().setTitle(currencyFullName);

    }

    //Get full money name from curency code
    public String getFullName(String moneyCode) {
        switch (moneyCode) {
            case "NGN": return "Naira";
            case "USD": return "US Dollar";
            case "EUR": return "Euro";
            case "JPY": return "Yen";
            case "GBP": return "Pound Sterling";
            case "AUD": return "Australian Dollar";
            case "CAD": return "Canadian Dollar";
            case "CHF": return "Swiss Franc";
            case "CNY": return "Yuan";
            case "KES": return "Kenyan Shilling";
            case "GHS": return "Cedi";
            case "UGX": return "Ugandan Shilling";
            case "ZAR": return "Rand";
            case "XAF": return "CFA Franc BCEAO";
            case "NZD": return "New Zealand Dollar";
            case "MYR": return "Malaysian Ringgit";
            case "BND": return "Brunei Dollar";
            case "GEL": return "Lari";
            case "RUB": return "Russian Ruble";
            case "INR": return "Indian Rupee";
            default: return "";
        }
    }

    @SuppressLint("DefaultLocale")
    //Method to do the conversion from one currency to the 2 others
    public void doConvert(View view) {
        if(view == btcConvertButton) {
            try {
                double btcAmount = Double.parseDouble(btcEdit.getText().toString());
                flatEdit.setText(String.format("%1$,.2f", (btcAmount * btcRate)));
                ethEdit.setText(String.format("%1$,.2f", (btcAmount * (ethRate / btcRate))));
            } catch (NumberFormatException e) {
                Snackbar.make(findViewById(R.id.main_scroll_view), Constants.INVALID_CONVERSION, Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }

        } else if(view == ethConvertButton) {
            try {
                double ethAmount = Double.parseDouble(ethEdit.getText().toString());
                flatEdit.setText(String.format("%1$,.2f", (ethAmount * ethRate)));
                btcEdit.setText(String.format("%1$,.2f", (ethAmount * (btcRate / ethRate))));
            } catch (NumberFormatException e) {
                Snackbar.make(findViewById(R.id.main_scroll_view), Constants.INVALID_CONVERSION, Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }

        } else if(view == flatConvertButton) {
            try {
                double flatAmount = Double.parseDouble(flatEdit.getText().toString());
                btcEdit.setText(String.format("%1$,.2f", (flatAmount / btcRate)));
                ethEdit.setText(String.format("%1$,.2f", (flatAmount / ethRate)));
            } catch (NumberFormatException e) {
                Snackbar.make(findViewById(R.id.main_scroll_view), Constants.INVALID_CONVERSION, Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        }
    }
}
