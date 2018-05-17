package Orezavatko;


import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


import txt_do_poctu.Castice;
import txt_do_poctu.MTSnimek;
import txt_do_poctu.typ_castice;
import umontreal.ssj.functionfit.LeastSquares;

// Tohle je hlavní tøída na analýzu dE/dx jednotlivých èástic
public class Snimek1Castice extends OrezSnimek {

	LinkedList<Integer> souradnice_x = new LinkedList<Integer>();//všechny x souøadnice bodù
	LinkedList<Integer> souradnice_y = new LinkedList<Integer>();//všechny y souøadnice bodù



public void rozhled(int x, int y) { //upravená metoda rozhled, která dìlá to samé + zapisuje si x a y souøadnice
	super.rozhled(x, y);//zavolání metody rozhled rodièe

	souradnice_x.addFirst(x);
    souradnice_y.addFirst(y); 
	
	
	

	
	
}

	public Snimek1Castice(double[][] pole, String way) throws IOException { // hlavní konstruktor tøídy, jež reprezentuje snímek s 1 èásticí a její analýzu
		                //jako argument je pøedáváno 2D pole s èásticí
		super();//metoda rodièe
		int cislo_castice = 1;
		
		
		hlavni_pole = new double [rozmer_x] [rozmer_y];           //hlavní 2D pole
		sekundarni_pole = new double [rozmer_x] [rozmer_y];
		BufferedWriter bw = null;
		
		
		for(int i = 0;i < 256; i++ ) {                     //pøepis 2D pole z argumentu do dvou 2D poli v metodì
			for (int j = 0; j< 256; j++) {
				hlavni_pole [i] [j] = pole [i] [j];
				sekundarni_pole [i] [j] = pole [i] [j];
				
				
			}
			
			
		}
		
		
	
		Castice prubezna_castice;//pomocný objekt èástice
		
		boolean overload = false; //promìnná kontrolující, zda se v souboru nachází opravdu jedna èástice
		
	
		

		
		for(int i = 0; i < 256; i ++) { //prohledávání pole
	    	for(int j = 0; j < 256; j ++) {
	        if	(hlavni_pole[j][i] != 0) {
	        	if(overload) { //zachycení druhé nežádoucí èástice
	        		JOptionPane.showMessageDialog(null, "V jednom ze snímkù se vyskytuje více èástic.", "InfoBox: " + "cetnost castic", JOptionPane.INFORMATION_MESSAGE);
	        		System.exit(1);
	        		
	        	}
	        	
	        	overload = true;
	        	
	      	zapisovaci_pole = new double[rozmer_x] [rozmer_y];//vytvoøení nového pole pro 1 èástici
	         	max_x = 0;                                    //resetování max a min všech souøadnic
	        	max_y = 0;
	        	min_x = 256;
	            min_y = 256;
	        	rozhled(j,i);	        		//rekurzivní funkce pro nalezení èástice
	        	}
	        
	        	
	   	    
	        }
	        		
	        		
	        
	        		
	        	
	        	
	        	
	        	
	        }
	    	
	    		
	    	
	    	
	    	
	
			
			
			
			
		
		
		
	
		
		
		double [] X_ove = new double [souradnice_x.size()];
		double [] Y_ove = new double [souradnice_y.size()];
		int posuv = 0;
		
		for(int i = 0; i < souradnice_x.size();i++) {//pøepis souøadnic ze seznamu do 2D polí, metoda pro aproximaci pøímky bere jen 2D pole
			X_ove [i] =  souradnice_x.get(i);
			Y_ove [i] = souradnice_y.get(i);
			System.out.println("x: " + souradnice_x.get(i) + "y " + souradnice_y.get(i) );
			
		}
		posuv = 0;
		
		double celko_energie = 0;
		for(int i = 0; i < souradnice_x.size();i++) { //souèet všech bunìk dá celkovou energii
			
			
			 celko_energie =  celko_energie + sekundarni_pole [souradnice_x.get(i)] [souradnice_y.get(i)] ;
		}
		System.out.println("" + celko_energie);
		double [] a_b = new double [2]; //promìnná pro uložení parametrù pøímky, jež bude aproximována pro smìr èástice
		
		a_b = LeastSquares.calcCoefficients(X_ove, Y_ove);// uplatnìní metody nejmenších ètvercù z knihovny SSJ pro aproximaci pøímky, získání parametrù a i b
		
		
		
		posuv = 0;
		
		System.out.print("a: " + a_b[0]);
		System.out.print("b: " + a_b[1]);
		// poèátek hlavní analýzi
		Bode start_point;//bod startu analýzy	
		Bode end_point;//bod konce analýzy
		Bode dir_vector;//vektor smìru
		final double krok = 0.01;//malý násobek posunovacího vektoru
		//nyní následuje správná volba koncového a poèáteèního bodu, jenž je volen dle parametrù pøímky a b, body jsou poèítány vždy jako prùseèík aproximované pøímky a okraje pole
		if(a_b[1]< 0) {      //možnost 1 a parametr je záporný
		
			dir_vector = new Bode(-1*krok,-a_b[1]*krok); 
			if((-(a_b[0]/a_b[1])) >= 0 && (-(a_b[0]/a_b[1])) <= 256) {
		 start_point = new Bode(-(a_b[0]/a_b[1]),0);
			}
			else {
				start_point = new Bode(256,(256*a_b[1])+a_b[0]);
				
				
			}
		 if(a_b[0] >= 0 && a_b[0] <= 256 ) {
		 
		 end_point = new Bode(0,a_b[0]);
		 }
		 else {
			 
	     end_point =  new Bode((256-a_b[0])/a_b[1],256);
	    
		 }
		 
		
		 System.out.println("<0");

		
		
		 
		
		
		
		
		
		}
		else if (a_b[1] > 0) { //Možnost - a je kladné
			dir_vector = new Bode(-1*krok,-a_b[1]*krok);
			if((-(a_b[0]/a_b[1])) >= 0 && (-(a_b[0]/a_b[1])) <= 256) {
			 end_point = new Bode(-(a_b[0]/a_b[1]),0);
			}
			else {
		     end_point =  new Bode(0,a_b[0]);
				
			}
			 if(((256 * a_b[1]) + a_b[0]) >= 0 && ((256 * a_b[1]) + a_b[0]) <=256){
			 
			 start_point = new Bode(256,(256 * a_b[1]) + a_b[0]);
			 }
			 else {
				 
		     start_point = new Bode(((256-a_b[0])/a_b[1]),256); 
				 
				 
			 }
			 
			 
			 System.out.println(">0");
		}
		else { //Možnost 3 a parametr je 0
			dir_vector = new Bode(1*krok, 0 );
			start_point = new Bode(0,a_b[0]);
		 end_point = new Bode(256,(256 * a_b[1]) + a_b[0]);
		 System.out.println("=0");

		}
	    double velikost_vektoru;
		double l = 0;
		Bode act_point = start_point;
		 

		
		
		velikost_vektoru = Math.pow(Math.pow(( dir_vector.getX()), 2) +    Math.pow(( dir_vector.getY()), 2), 0.5);//velikost vektru, kterým posunujeme
		double max_l =  Math.pow(Math.pow(( start_point.getX()) - ( end_point.getX()) , 2) +    Math.pow(( start_point.getY()) - ( end_point.getY()), 2), 0.5);//maximální délka, jež mùže èástice mít
		
		Map<Double,Double> delka_energie = new TreeMap<Double,Double>();//mapa, ve které je pro každou délku daná energie zaznamenaného pixelu
		Map<Double,Double> delka_energie_round = new TreeMap<Double,Double>();//mapa, ve které je pro každou délku daná energie intervalu dráhy
		double pocatecni_l = 0;//promìnné délek
		double l_pos_bodu = 0;
		double p; //absolutní parametr v rovnici pøímky
		double energie_vyhovujicich_castic;
		p = -act_point.getX() -(a_b[1]*act_point.getY());//poèáteèní hodnota parametru
		System.out.println(dir_vector.getX());
		System.out.println(dir_vector.getY());

		System.out.println(velikost_vektoru);
	
		while (delka_energie.isEmpty()) {//tento cyklus se opakuje do doby, dokud nebude nalezen 1 bod ležící na kolmé pøímce
			energie_vyhovujicich_castic = 0;
		for(int i = 0; i < souradnice_x.size();i++) {//kontrola všech bodù, zda neleží na pøímce v daném posunutí
		if	((Math.abs( (souradnice_x.get(i) +  (a_b[1]*souradnice_y.get(i)) + p)/(Math.pow(1 + Math.pow(a_b[1], 2), 0.5)))  <= velikost_vektoru /*vzdálenost bodu od pøímky musí být menší než velikost vektoru, kterým posunujeme*/)   &&  sekundarni_pole[souradnice_x.get(i)][souradnice_y.get(i)] != -1/*mínus jednièkou jsou oznaèeny body, které byly již algorytmem zpracovány*/) {
			
			energie_vyhovujicich_castic = energie_vyhovujicich_castic + sekundarni_pole [souradnice_x.get(i)] [souradnice_y.get(i)];//uložení energie vyhovující buòky, pokud 2 buòky ve stejném posunutí mají vyhovující vzdálenost od posunované pøímky, bere se v potaz jejich souèet
			System.out.println((Math.abs( (souradnice_x.get(i) +  (a_b[1]*souradnice_y.get(i)) + p)/(Math.pow(1 + Math.pow(a_b[1], 2), 0.5)))));//výpis
			
			
			sekundarni_pole[souradnice_x.get(i)][souradnice_y.get(i)] = -1;//oznaèení buòky, že byla již zpracována
            
			
		}
		    
				
				
			}
		 
		if(energie_vyhovujicich_castic > 0) {//umístìní prvního bodu s energií do mapy
			
			delka_energie.put(0.0,energie_vyhovujicich_castic);
			break;
		}
		
		
		act_point = new Bode(act_point.getX() + ( dir_vector.getX()),act_point.getY() + ( dir_vector.getY()));//promìnná daného bodu na aproximované pøímce, s nímž je posunováno
		p = -act_point.getX() -(a_b[1]*act_point.getY());//úprava parametru
		
		l = l + velikost_vektoru;//pøiètení velikosti vektoru k celkové velikosti posunutí
		
		
		}
		
		System.out.println(delka_energie.get(0.0));
		System.out.println(max_l);
		System.out.println(l);
		
		
		pocatecni_l = l;//poèáteèní vzdálenost
		act_point = new Bode(act_point.getX() + ( dir_vector.getX()),act_point.getY() + ( dir_vector.getY()));
		p = -act_point.getX() -(a_b[1]*act_point.getY());
		
		l = l + velikost_vektoru;


		
		
		while ((l - pocatecni_l ) <= (max_l )) {//cyklus se opakuje do doby, než bude pøímka posunuta o maximální vzdálenost
			
         //cyklus je témìø stejný jako pøedchozí cyklus
			energie_vyhovujicich_castic = 0;
			
			
			for(int i = 0; i < souradnice_x.size();i++) {
			if	((Math.abs( (souradnice_x.get(i) +  (a_b[1]*souradnice_y.get(i)) + p)/(Math.pow(1 + Math.pow(a_b[1], 2), 0.5))) <= velikost_vektoru + Math.pow(velikost_vektoru, 0.5) )   &&  sekundarni_pole[souradnice_x.get(i)][souradnice_y.get(i)] != -1) {
				
				energie_vyhovujicich_castic = energie_vyhovujicich_castic + sekundarni_pole [souradnice_x.get(i)] [souradnice_y.get(i)];
				
			

				sekundarni_pole[souradnice_x.get(i)][souradnice_y.get(i)] = -1;
				l_pos_bodu = l  - pocatecni_l;
				
			}
			     
				
				
			}
          if(energie_vyhovujicich_castic > 0) {
				
				delka_energie.put(l - pocatecni_l,energie_vyhovujicich_castic);
				
				
			}
			
			act_point = new Bode(act_point.getX() + ( dir_vector.getX()),act_point.getY() + (dir_vector.getY()));
			p = -act_point.getX() -(a_b[1]*act_point.getY());
			l = l + velikost_vektoru;
			}
		
		
		
		
		
		
		
		System.out.println(l_pos_bodu);
		
		
		
		//dále následují již jen úpravy, zaokrouhlování, výpisy
		//kontrolní souèet energií, zda nebyla vynechána žádná buòka
		double c_energie = 0;
		for(double a : delka_energie.keySet() ) {
		System.out.println(a + " " + delka_energie.get(a));
			c_energie += delka_energie.get(a);
			
			
		}
		if(Math.abs(c_energie - celko_energie) < 1 ) {
			
			System.out.println("V poradku");
		
		
		}
		else {// v pøípadì nerovnosti energií je vypsána chyba
			System.err.println("Chyba energie");
			JFrame vokno = new JFrame("Chyba");
			JOptionPane.showMessageDialog(vokno, "Chyba energie. Nesedí celkový souèet energií" + (c_energie - celko_energie), "InfoBox: " + "cetnost castic" , JOptionPane.INFORMATION_MESSAGE);
			System.exit(1);
			
		}
		
		
		
		
		
		
		System.out.println("" + (c_energie - celko_energie));
		
		
		
		
		
		int pocet_za_polovinou = 0;
		
		//dále následuje úprava smìru, èástice je rozdìlena na 2 poloviny, body ve všech polovinách spoèítány, oèáteèní smìr musí kvùli blobu být tam, kde je víc bodù
		for(double a: delka_energie.keySet()) {
			if(a < (l_pos_bodu/2)) {
			
			pocet_za_polovinou++;
			
			
		}
		}
		if (pocet_za_polovinou < (souradnice_x.size()-pocet_za_polovinou)) {
			Object[] keyArray = delka_energie.keySet().toArray();
			Map<Double,Double> pomocna_mapa  =  new TreeMap<Double,Double>();
			
			for(int i = 0; i < keyArray.length; i++) {
				pomocna_mapa.put(l_pos_bodu - (Double) keyArray[i], delka_energie.get(keyArray[i]));
				
				
			}
			delka_energie = pomocna_mapa;
			
			System.out.println("prehozeno");
			
			
		}
		
		for(double a: delka_energie.keySet()) {
			System.out.println(a + " " + delka_energie.get(a));
			
			
		}
		
		//zaokrouhlení vzdáleností jednotlivých buòek od poèátku dráhy èástice a následné seètení jejich energií do intervalù dráhy
        double roundEnergy = 0;
		for(double i = 0; i <= Math.round(l_pos_bodu); i  += 1) {
			for(double a : delka_energie.keySet()) {
				if(Math.round(a) == i) {
					
					 roundEnergy =  roundEnergy	+ delka_energie.get(a);
					
				}
				
				
				
			}
			
			
		delka_energie_round.put(i,roundEnergy);
		roundEnergy = 0;
			
			
		}
	
		
		
		for(double a : delka_energie_round.keySet() ) {
			System.out.println(a + " " + delka_energie_round.get(a));
		}
		
		
		// zápis do souboru
		try {
			bw = new BufferedWriter(new FileWriter(way +".txt"));
			
			
			
			for(double a : delka_energie_round.keySet() ) {
				bw.write(a + " " + delka_energie_round.get(a));
				bw.newLine();
		    	bw.flush();
			}
		    			
		    	
		}
		catch (Exception e)
        {       e.printStackTrace();
                System.err.println("Do souboru se nepovedlo zapsat.");
        }
		    	
		    
				
				
			
		
		
		hlavni_pole = null;
		sekundarni_pole = null;
		
		
		
	    }
	    }


