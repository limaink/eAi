package com.example.ganhei;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.List;

import com.example.ganhei.R;

import android.media.AudioRecord.OnRecordPositionUpdateListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.*;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;



public class MainActivity extends Activity {

	public ArrayList<String> jogos = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		final EditText jogo = (EditText) findViewById(R.id.editText_jogo);
		final EditText sorteio = (EditText) findViewById(R.id.editText_sorteio);
		Button verApostas = (Button) findViewById(R.id.verApostas);
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		Button conferir = (Button) findViewById(R.id.confereBt);
		Button add_games = (Button) findViewById(R.id.add_game);
		
		add_games.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(jogo.getText().toString().length() <= 0){
				}else{
					jogos.add(jogo.getText().toString());
					jogo.setText("");
				}
			}
		});
		
		verApostas.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent irPapostas = new Intent(v.getContext(),Apostas.class);
				irPapostas.putExtra("aposta_string", jogos);
				startActivityForResult(irPapostas, 10);
			}
		});
		
		conferir.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View v) {
				if(jogo.getText().toString().length() <= 0){
					jogo.setText("");
				}else{
					jogos.add(jogo.getText().toString());
					jogo.setText("");
				}
				Intent CalculaResultado = new Intent(v.getContext(), Resultado.class);
				CalculaResultado.putExtra("sorteio", sorteio.getText().toString());
				CalculaResultado.putExtra("aposta_string", jogos);
				startActivity(CalculaResultado);
			}
		});
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lista_tipos_jogos, R.layout.dropdown_jogos);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
	}
	
	protected void onResume(){
		super.onResume();
		
	}
	
	public void onActivityResult( int requestCode, int resultCode, Intent data){
		if(resultCode == 5){
			jogos = data.getStringArrayListExtra("aposta_string");
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
	
	public boolean ValidaEntradas(){
		
		
		return false;
	}
}


