package com.wayne.bottomnavigationbardemo2;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{

	private TextView txt_top, txt_deal, txt_poi, txt_more, txt_user;
	private View fragment_container;
	private FirstFragment f1, f2, f3, f4;
	private FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		bindView();
	}

	private void bindView() {
		// TODO Auto-generated method stub
		txt_top = (TextView) this.findViewById(R.id.txt_top);
		txt_deal = (TextView) this.findViewById(R.id.txt_deal);
		txt_poi = (TextView) this.findViewById(R.id.txt_poi);
		txt_user = (TextView) this.findViewById(R.id.txt_user);
		txt_more = (TextView) this.findViewById(R.id.txt_more);
		fragment_container = findViewById(R.id.fragment_container);
		
		txt_deal.setOnClickListener(this);
		txt_poi.setOnClickListener(this);
		txt_user.setOnClickListener(this);
		txt_more.setOnClickListener(this);
	}
	
	public void selected(){
		txt_deal.setSelected(false);
		txt_poi.setSelected(false);
		txt_user.setSelected(false);
		txt_more.setSelected(false);
	}
	
	public void hideAllFragment(FragmentTransaction transaction){
		if(f1 != null){
			transaction.hide(f1);
		}
		
		if(f2 != null){
			transaction.hide(f2);
		}
		
		if(f3 != null){
			transaction.hide(f3);
		}
		
		if(f4 != null){
			transaction.hide(f4);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		hideAllFragment(transaction);
		switch (v.getId()) {
		case R.id.txt_deal:
			selected();
			txt_deal.setSelected(true);
			if(f1==null){
				f1 = new FirstFragment("第一个Fragment");
				transaction.add(R.id.fragment_container, f1);
			}else{
				transaction.show(f1);
			}
			break;
		case R.id.txt_poi:
			selected();
			txt_poi.setSelected(true);
			if(f2==null){
				f2 = new FirstFragment("第二个Fragment");
				transaction.add(R.id.fragment_container, f2);
			}else{
				transaction.show(f2);
			}
			break;
		case R.id.txt_user:
			selected();
			txt_user.setSelected(true);
			if(f3==null){
				f3 = new FirstFragment("第三个Fragment");
				transaction.add(R.id.fragment_container, f3);
			}else{
				transaction.show(f3);
			}
			break;
		case R.id.txt_more:
			selected();
			txt_more.setSelected(true);
			if(f4==null){
				f4 = new FirstFragment("第四个Fragment");
				transaction.add(R.id.fragment_container, f4);
			}else{
				transaction.show(f4);
			}
			break;
		default:
			break;
		}
		transaction.commit();
	}

}
