package com.example.ganhei;

import java.util.ArrayList;

import com.example.ganhei.R.id;

import android.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;

public class Apostas extends Activity {

	ArrayList<String> apostas = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.example.ganhei.R.layout.activity_apostas);
		
		Button deleteBt = (Button) findViewById(com.example.ganhei.R.id.button_delete);
		
		
		final ScrollView sv = (ScrollView) findViewById(com.example.ganhei.R.id.scrollView1);
		final LinearLayout ll = (LinearLayout) findViewById(com.example.ganhei.R.id.linearScroll);
		//ll.setOrientation(LinearLayout.VERTICAL);
		sv.removeAllViews();
		sv.addView(ll);
		
		apostas = getIntent().getStringArrayListExtra("aposta_string");

		for(int num_aposta = 0; num_aposta < apostas.size(); num_aposta++){
			CheckBox ch = new CheckBox(getApplicationContext());
			ch.setText(apostas.get(num_aposta).toString());
			ch.setId(num_aposta);
			ch.setTextColor(getResources().getColor(R.color.black));
			ll.addView(ch);			
		}
		
		sv.invalidate();
		
		deleteBt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				apagarApostas();
				ll.removeAllViews();
				for(int num_aposta = 0; num_aposta < apostas.size(); num_aposta++){
					CheckBox ch = new CheckBox(getApplicationContext());
					ch.setText(apostas.get(num_aposta).toString());
					ch.setId(num_aposta);
					ch.setTextColor(getResources().getColor(R.color.black));
					ll.addView(ch);			
				}
				sv.invalidate();
				Intent voltar = new Intent();
				voltar.putExtra("aposta_string", apostas);
				setResult(5,voltar);
				
			}
		});
	}
	public void OnBackPressed(){
		super.onBackPressed();
		finish();
	}

	public void apagarApostas(){
		LinearLayout ll = (LinearLayout) findViewById(com.example.ganhei.R.id.linearScroll);
		CheckBox ch = new CheckBox(getApplicationContext());
		for(int i = 0; i < ll.getChildCount(); i++){
			ch = (CheckBox) ll.getChildAt(i);
			if(ch.isChecked()){
				apostas.remove(i);
			}
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.apostas, menu);
		return true;
	}
	
	
}
