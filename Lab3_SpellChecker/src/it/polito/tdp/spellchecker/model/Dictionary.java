package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dictionary {

	private List<String> dizionario;
	private String lenguage;
	
	public Dictionary() {
		
	}
	
	
	public boolean loadDictionary(String lenguage) {
		
		if (dizionario != null && this.lenguage.equals(lenguage)) {
			return true;
		}
		dizionario = new ArrayList<String>();
		this.lenguage = lenguage;
		
		try {
			FileReader fr = new FileReader("rsc/"+lenguage+".txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			
			while((word = br.readLine()) != null) {
				dizionario.add(word.toLowerCase());
						
			} 
			br.close();
			Collections.sort(dizionario);
			return true;
		} catch (IOException e) {
			System.out.println("Errore nella lettura del file");
			return false;
		}
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList) {
		
		List<RichWord> paroleSbagliate = new ArrayList<RichWord>();
		
		for(String s: inputTextList) {
			
			RichWord richWord = new RichWord(s);
			if(dizionario.contains(s.toLowerCase())) {
				richWord.setCorrect(true);
			} else {
				richWord.setCorrect(false);
			}
			
			paroleSbagliate.add(richWord);
		}
		return paroleSbagliate;
	}
	
}
