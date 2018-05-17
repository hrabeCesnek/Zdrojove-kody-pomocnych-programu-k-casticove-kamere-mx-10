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
public class Nacist_vyriznutou_castici  { // tato tøída slouží jako pomocná k analýze stopy kolmou pøímkou, pøedává cesty k souborùm a ovládá vykreslování jednotlivých èástic
	private static int rozmer_x= 256;             // velká èást probíhá ve tøídì Snimek1Castice
	private static int rozmer_y= 256;
	static double [] [] hlavni_pole = new double [rozmer_x][rozmer_y];
	public Nacist_vyriznutou_castici() {
		
		
		
		
	}

// inicializace potøebných promìnných


	public static void main(String[] args) throws IOException {
		
	
	LinkedList<Castice> Seznam_castic = new LinkedList<Castice>(); //seznam nalezených èástic
	
	
	
	JFrame obraz = new JFrame(); //okno pro vykreslování
		obraz.setSize(1012, 1012);
		obraz.setTitle("pozice castic");
	   obraz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  obraz.setBackground(Color.black);
	  
		 Snimek1Castice pomocny_snimek = null; //pomocná promìnná typu snímek s jednou èásticí
		
		
	  JFileChooser chooser = new JFileChooser(); //výbìr souborù èástic
	  chooser.setMultiSelectionEnabled(true);    
	  chooser.showOpenDialog((Frame)null);
	  File[] files = chooser.getSelectedFiles(); // pole souborù èástic
	  
	  Snimek1Castice snimky[] = new Snimek1Castice [files.length];//pole objektu typu snímek s jednou èásticí
	  Reader[] rd = new FileReader[files.length]; //ètení ze souborù
	  
	  BufferedReader[] brd = new BufferedReader[files.length];
	  
	
	  
	  
	 
	 
	 Map <typ_castice,Integer> seznam = new HashMap<typ_castice,Integer>();//pomocný seznam nalezených èástic
	 seznam.put(typ_castice.Gama, 0);
		seznam.put(typ_castice.Beta, 0);
		seznam.put(typ_castice.Alfa, 0);	
		seznam.put(typ_castice.Other, 0);
		seznam.put(typ_castice.Mion, 0);
	 
	 
	 
	 
	 
	 
	
		
		
	  chooser.setVisible(true); //výbìr souborù povolen
		// postupné nahrátí všech vybraných souborù a vytvoøení instancí tøíd, které je budou èíst
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
		
		FileDialog dialog = new FileDialog((Frame)null, "Select Folder to save"); //výbìr složky pro uložení výpisù
		dialog.setVisible(true);
		
		//dále následuje naètení do 2D pole
		
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
		
			   
		pomocny_snimek = new Snimek1Castice(hlavni_pole,dialog.getDirectory() + files[snimek_no].getName() );// zavolání konstruktoru tøídy Snimek1castice
	                                                                                                         // v této tøídì se provádí i výpis do souboru
		snimky[snimek_no] = pomocny_snimek ;	 //postupné ukládání všech snímkù s jednou èásticí do pole   
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
	
			for(Snimek1Castice a : snimky) //vykreslovací cyklus
			{				
				
				
				

				nakresli_barevny_ctverecek kresli = new nakresli_barevny_ctverecek(a.getObraz(),a.getBarve()); //vykreslení èástic
				obraz.add(kresli);
				
				
			}
			
			for(typ_castice typ : seznam.keySet()) {	
			System.out.println(typ + ":" + seznam.get(typ));
			
		}
		
		
		
		
		
		
		obraz.setVisible(true);//zobrazení nákresu
		
		
		
		for(Reader a: rd ) //konec
		{
			a.close();
			
			
		}
		for(Reader a: brd )
		{
			a.close();
			
			
		}	
	
		
}}
		
		
		
		
		

