package com.wayne.numaddsubdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wayne.view.AmountView;

public class MainActivity extends Activity {

	 private AmountView mAmountView;

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        mAmountView = (AmountView) findViewById(R.id.amount_view);
	        mAmountView.setGoods_storage(50);
	        mAmountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
	            @Override
	            public void onAmountChange(View view, int amount) {
	                Toast.makeText(getApplicationContext(), "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
	            }
	        });
	    }
}
