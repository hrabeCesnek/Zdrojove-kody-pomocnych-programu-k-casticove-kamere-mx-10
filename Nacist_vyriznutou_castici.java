package Orezavatko;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.Map;
import java.util.TreeMap;
import java.awt.Rectangle;
import javax.swing.JFileChooser;
import javax.swing.JFrame;










import javax.swing.JOptionPane;

import nacitani_gps.GPSAltDec;
import txt_do_poctu.Castice;
import txt_do_poctu.nakresli_barevny_ctverecek;
import txt_do_poctu.nakresli_ctverecek;
import txt_do_poctu.typ_castice;

import java.util.HashMap;
import java.util.LinkedList;


import java.awt.Color;
import java.awt.FileDialog;

import java.awt.Frame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.TreeMap;

import java.awt.Rectangle;

import javax.swing.JFileChooser;
import javax.swing.JFrame;










import javax.swing.JOptionPane;

import nacitani_gps.GPSAltDec;

import java.util.HashMap;
import java.util.LinkedList;
public class Nacist_vyriznutou_castici  { // tato t��da slou�� jako pomocn� k anal�ze stopy kolmou p��mkou, p�ed�v� cesty k soubor�m a ovl�d� vykreslov�n� jednotliv�ch ��stic
	private static int rozmer_x= 256;             // velk� ��st prob�h� ve t��d� Snimek1Castice
	private static int rozmer_y= 256;
	static double [] [] hlavni_pole = new double [rozmer_x][rozmer_y];
	public Nacist_vyriznutou_castici() {
		
		
		
		
	}

// inicializace pot�ebn�ch prom�nn�ch


	public static void main(String[] args) throws IOException {
		
	
	LinkedList<Castice> Seznam_castic = new LinkedList<Castice>(); //seznam nalezen�ch ��stic
	
	
	
	JFrame obraz = new JFrame(); //okno pro vykreslov�n�
		obraz.setSize(1012, 1012);
		obraz.setTitle("pozice castic");
	   obraz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  obraz.setBackground(Color.black);
	  
		 Snimek1Castice pomocny_snimek = null; //pomocn� prom�nn� typu sn�mek s jednou ��stic�
		
		
	  JFileChooser chooser = new JFileChooser(); //v�b�r soubor� ��stic
	  chooser.setMultiSelectionEnabled(true);    
	  chooser.showOpenDialog((Frame)null);
	  File[] files = chooser.getSelectedFiles(); // pole soubor� ��stic
	  
	  Snimek1Castice snimky[] = new Snimek1Castice [files.length];//pole objektu typu sn�mek s jednou ��stic�
	  Reader[] rd = new FileReader[files.length]; //�ten� ze soubor�
	  
	  BufferedReader[] brd = new BufferedReader[files.length];
	  
	
	  
	  
	 
	 
	 Map <typ_castice,Integer> seznam = new HashMap<typ_castice,Integer>();//pomocn� seznam nalezen�ch ��stic
	 seznam.put(typ_castice.Gama, 0);
		seznam.put(typ_castice.Beta, 0);
		seznam.put(typ_castice.Alfa, 0);	
		seznam.put(typ_castice.Other, 0);
		seznam.put(typ_castice.Mion, 0);
	 
	 
	 
	 
	 
	 
	
		
		
	  chooser.setVisible(true); //v�b�r soubor� povolen
		// postupn� nahr�t� v�ech vybran�ch soubor� a vytvo�en� instanc� t��d, kter� je budou ��st
		int p = 0;
		
		int snimek_no = 0;
		
		String radek;
		
		String casti[];
		
		int cas_timepix = 0;
		try {
			
		for(int i = 0 ; i < files.length; i ++) { 
				  rd [i] = new FileReader(files[i].getPath());
				  brd [i]= new BufferedReader(rd[i]); 
				
				  
			  }
			 chooser.setVisible(false);
			
		}
		
		catch (IOException e) {
			e.printStackTrace();
			for(Reader a: rd )
			{
				a.close();
				
				
			}
			for(Reader a: brd )
			{
				a.close();
				
				
			}
		
			
			System.exit(1);
		
		
		}
		
		FileDialog dialog = new FileDialog((Frame)null, "Select Folder to save"); //v�b�r slo�ky pro ulo�en� v�pis�
		dialog.setVisible(true);
		
		//d�le n�sleduje na�ten� do 2D pole
		
			for(BufferedReader buff: brd) {
				
		
		try {
			
			
			
			
	
			
			
			
			
			
			
		do {
			
			
			
			radek = buff.readLine();
			if(radek!= null) {
				casti = radek.split(" ");
			for(int i = 0; i < casti.length; i ++) {
			hlavni_pole [i][p] = Double.parseDouble(casti[i]);
			
			
			
			
				
				
				
			}
				
			
				p++;
				
				
				
				
			}
			
			
			
		}while (radek!= null);
		
			   
		pomocny_snimek = new Snimek1Castice(hlavni_pole,dialog.getDirectory() + files[snimek_no].getName() );// zavol�n� konstruktoru t��dy Snimek1castice
	                                                                                                         // v t�to t��d� se prov�d� i v�pis do souboru
		snimky[snimek_no] = pomocny_snimek ;	 //postupn� ukl�d�n� v�ech sn�mk� s jednou ��stic� do pole   
		snimek_no++;
			   
			   
			   
			   
		
		
		
		
		
		
		}catch (IOException e) {
			e.printStackTrace();
			for(Reader a: rd )
			{
				a.close();
				
				
			}
			for(Reader a: brd )
			{
				a.close();
				
				
			}
			
			
			System.exit(1);
			
			
		}
		p = 0;
	
				
			   
		
		}
	
			for(Snimek1Castice a : snimky) //vykreslovac� cyklus
			{				
				
				
				

				nakresli_barevny_ctverecek kresli = new nakresli_barevny_ctverecek(a.getObraz(),a.getBarve()); //vykreslen� ��stic
				obraz.add(kresli);
				
				
			}
			
			for(typ_castice typ : seznam.keySet()) {	
			System.out.println(typ + ":" + seznam.get(typ));
			
		}
		
		
		
		
		
		
		obraz.setVisible(true);//zobrazen� n�kresu
		
		
		
		for(Reader a: rd ) //konec
		{
			a.close();
			
			
		}
		for(Reader a: brd )
		{
			a.close();
			
			
		}	
	
		
}}
		
		
		
		
		

