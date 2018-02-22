package com.softuni.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private String username;
    private boolean hasCreamTopping = false;
    private boolean hasSugarTopping = false;
    private int coffeeCount = 0;

    @BindView(R.id.btn_order) Button btnOrder;
    @BindView(R.id.edt_username) EditText edtUsername;
    @BindView(R.id.chbx_cream)    CheckBox chbxCream;
    @BindView(R.id.chbx_sugar)    CheckBox chbxSugar;
    @BindView(R.id.txt_coffee_count)    TextView txtCoffeeCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_less)
    public void onLessClicked() {
        this.coffeeCount = Integer.parseInt(txtCoffeeCount.getText().toString());
        coffeeCount--;
        txtCoffeeCount.setText(String.valueOf(coffeeCount));
    }

    @OnClick(R.id.btn_more)
    public void onMoreClicked() {
        this.coffeeCount = Integer.parseInt(txtCoffeeCount.getText().toString());
        coffeeCount++;
        txtCoffeeCount.setText(String.valueOf(coffeeCount));
    }

    @OnClick(R.id.btn_order)
    public void onOrderClicked() {
        this.username = edtUsername.getText().toString();
        this.hasCreamTopping = chbxCream.isChecked();
        this.hasSugarTopping = chbxSugar.isChecked();
        this.coffeeCount = Integer.parseInt(txtCoffeeCount.getText().toString());

        String orderText = "Hello from " + username +
                " \n Please get me " + coffeeCount + " coffees";
        if(hasCreamTopping) {
            orderText += " Also add cream.";
        }
        if(hasSugarTopping) {
            orderText += " Also add extra sugar.";
        }

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("*/*");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, "coffeee@gmail.com");
        emailIntent.putExtra(Intent.EXTRA_TEXT, orderText);
        startActivity(emailIntent);
    }
}
