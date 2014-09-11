package com.example.ganhei;

import java.util.ArrayList;
import java.util.Arrays;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class Resultado extends Activity {
	
	Integer[] sorteio_int = new Integer[6];
	ArrayList<String> jogos = new ArrayList<String>();
	ArrayList<Integer[]> apostas = new ArrayList<Integer[]>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultado);
		Intent intent = getIntent();

		jogos = intent.getStringArrayListExtra("aposta_string");
		sorteio_int = Char2Int(intent.getStringExtra("sorteio"),6);
		for (int num_apostas = 0; num_apostas < jogos.size(); num_apostas++){
			apostas.add(Char2Int(jogos.get(num_apostas), BetSize(jogos.get(num_apostas))));
		}
		
		CompareNumbers(sorteio_int, apostas, jogos.size());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.resultado, menu);
		return true;
	}
	
	public int CompareNumbers ( Integer[] sorteio, ArrayList<Integer[]> jogo, int qnt_jogos){
		int hits = 0;
		String resultado = "O acerto é ";
		for(int aposta = 0; aposta <qnt_jogos; aposta++)
		{
			hits = 0;
			int tamanho = jogo.get(aposta).length;
			for(int i = 0; i < 6; i++){
				for(int j = 0; j < tamanho; j++){
					if(jogo.get(aposta)[j] == sorteio[i])
						hits++;
				}
			}
			resultado = resultado + Integer.toString(hits) + " ";
		}	
		final TextView acertos = (TextView) findViewById(R.id.editTResultado);
		acertos.setText(resultado);
		acertos.setTextSize(26);
		acertos.setVisibility(1);
		return hits;
	}
	
	public int BetSize ( String jogo){
		int tamanho = jogo.length();
		int size = 1;
		char atual, anterior = 'n';
		for (int j = 0; j < tamanho; j++){
			atual = jogo.charAt(j);
			if((atual == ' ' || atual == '.' || atual == ',') && (Character.getNumericValue(anterior) >= 0 && Character.getNumericValue(anterior)  <=9))
				size++;
			anterior = atual;
		}
		return size;
	}
	
	public Integer[] Char2Int(String jogo, int size){
		char primeiro='n', segundo='n', atual='n';
		int i = 0, j = 0;
		int tamanho = jogo.length();
		Integer[] jogo_final = new Integer[size];

		do{
			atual = jogo.charAt(i);
			if((Character.getNumericValue(atual) >= 0)&&(Character.getNumericValue(atual) <= 9)||(atual == ' ' || atual == '.' || atual == ',')){
				if (i == tamanho - 1){
					if(primeiro == 'n')
						primeiro = atual;
					else
						segundo = atual;
				}
				if((atual == ' ' || atual == '.' || atual == ',') || (i == tamanho - 1)){
					if(primeiro != 'n'){
						if(segundo != 'n')
							jogo_final[j] = Character.getNumericValue(primeiro)*10 + Character.getNumericValue(segundo);
						else
							jogo_final[j] = Character.getNumericValue(primeiro);
						j++;
						primeiro = 'n';
						segundo = 'n';
						}
				}else{
					if(primeiro == 'n')
						primeiro = atual;
					else
						segundo = atual;
					
				}
		}
		i++;
		}while(i < tamanho);

		Arrays.sort(jogo_final);

		return jogo_final;
	}
	
}
