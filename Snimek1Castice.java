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

// Tohle je hlavn� t��da na anal�zu dE/dx jednotliv�ch ��stic
public class Snimek1Castice extends OrezSnimek {

	LinkedList<Integer> souradnice_x = new LinkedList<Integer>();//v�echny x sou�adnice bod�
	LinkedList<Integer> souradnice_y = new LinkedList<Integer>();//v�echny y sou�adnice bod�



public void rozhled(int x, int y) { //upraven� metoda rozhled, kter� d�l� to sam� + zapisuje si x a y sou�adnice
	super.rozhled(x, y);//zavol�n� metody rozhled rodi�e

	souradnice_x.addFirst(x);
    souradnice_y.addFirst(y); 
	
	
	

	
	
}

	public Snimek1Castice(double[][] pole, String way) throws IOException { // hlavn� konstruktor t��dy, je� reprezentuje sn�mek s 1 ��stic� a jej� anal�zu
		                //jako argument je p�ed�v�no 2D pole s ��stic�
		super();//metoda rodi�e
		int cislo_castice = 1;
		
		
		hlavni_pole = new double [rozmer_x] [rozmer_y];           //hlavn� 2D pole
		sekundarni_pole = new double [rozmer_x] [rozmer_y];
		BufferedWriter bw = null;
		
		
		for(int i = 0;i < 256; i++ ) {                     //p�epis 2D pole z argumentu do dvou 2D poli v metod�
			for (int j = 0; j< 256; j++) {
				hlavni_pole [i] [j] = pole [i] [j];
				sekundarni_pole [i] [j] = pole [i] [j];
				
				
			}
			
			
		}
		
		
	
		Castice prubezna_castice;//pomocn� objekt ��stice
		
		boolean overload = false; //prom�nn� kontroluj�c�, zda se v souboru nach�z� opravdu jedna ��stice
		
	
		

		
		for(int i = 0; i < 256; i ++) { //prohled�v�n� pole
	    	for(int j = 0; j < 256; j ++) {
	        if	(hlavni_pole[j][i] != 0) {
	        	if(overload) { //zachycen� druh� ne��douc� ��stice
	        		JOptionPane.showMessageDialog(null, "V jednom ze sn�mk� se vyskytuje v�ce ��stic.", "InfoBox: " + "cetnost castic", JOptionPane.INFORMATION_MESSAGE);
	        		System.exit(1);
	        		
	        	}
	        	
	        	overload = true;
	        	
	      	zapisovaci_pole = new double[rozmer_x] [rozmer_y];//vytvo�en� nov�ho pole pro 1 ��stici
	         	max_x = 0;                                    //resetov�n� max a min v�ech sou�adnic
	        	max_y = 0;
	        	min_x = 256;
	            min_y = 256;
	        	rozhled(j,i);	        		//rekurzivn� funkce pro nalezen� ��stice
	        	}
	        
	        	
	   	    
	        }
	        		
	        		
	        
	        		
	        	
	        	
	        	
	        	
	        }
	    	
	    		
	    	
	    	
	    	
	
			
			
			
			
		
		
		
	
		
		
		double [] X_ove = new double [souradnice_x.size()];
		double [] Y_ove = new double [souradnice_y.size()];
		int posuv = 0;
		
		for(int i = 0; i < souradnice_x.size();i++) {//p�epis sou�adnic ze seznamu do 2D pol�, metoda pro aproximaci p��mky bere jen 2D pole
			X_ove [i] =  souradnice_x.get(i);
			Y_ove [i] = souradnice_y.get(i);
			System.out.println("x: " + souradnice_x.get(i) + "y " + souradnice_y.get(i) );
			
		}
		posuv = 0;
		
		double celko_energie = 0;
		for(int i = 0; i < souradnice_x.size();i++) { //sou�et v�ech bun�k d� celkovou energii
			
			
			 celko_energie =  celko_energie + sekundarni_pole [souradnice_x.get(i)] [souradnice_y.get(i)] ;
		}
		System.out.println("" + celko_energie);
		double [] a_b = new double [2]; //prom�nn� pro ulo�en� parametr� p��mky, je� bude aproximov�na pro sm�r ��stice
		
		a_b = LeastSquares.calcCoefficients(X_ove, Y_ove);// uplatn�n� metody nejmen��ch �tverc� z knihovny SSJ pro aproximaci p��mky, z�sk�n� parametr� a i b
		
		
		
		posuv = 0;
		
		System.out.print("a: " + a_b[0]);
		System.out.print("b: " + a_b[1]);
		// po��tek hlavn� anal�zi
		Bode start_point;//bod startu anal�zy	
		Bode end_point;//bod konce anal�zy
		Bode dir_vector;//vektor sm�ru
		final double krok = 0.01;//mal� n�sobek posunovac�ho vektoru
		//nyn� n�sleduje spr�vn� volba koncov�ho a po��te�n�ho bodu, jen� je volen dle parametr� p��mky a b, body jsou po��t�ny v�dy jako pr�se��k aproximovan� p��mky a okraje pole
		if(a_b[1]< 0) {      //mo�nost 1 a parametr je z�porn�
		
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
		else if (a_b[1] > 0) { //Mo�nost - a je kladn�
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
		else { //Mo�nost 3 a parametr je 0
			dir_vector = new Bode(1*krok, 0 );
			start_point = new Bode(0,a_b[0]);
		 end_point = new Bode(256,(256 * a_b[1]) + a_b[0]);
		 System.out.println("=0");

		}
	    double velikost_vektoru;
		double l = 0;
		Bode act_point = start_point;
		 

		
		
		velikost_vektoru = Math.pow(Math.pow(( dir_vector.getX()), 2) +    Math.pow(( dir_vector.getY()), 2), 0.5);//velikost vektru, kter�m posunujeme
		double max_l =  Math.pow(Math.pow(( start_point.getX()) - ( end_point.getX()) , 2) +    Math.pow(( start_point.getY()) - ( end_point.getY()), 2), 0.5);//maxim�ln� d�lka, je� m��e ��stice m�t
		
		Map<Double,Double> delka_energie = new TreeMap<Double,Double>();//mapa, ve kter� je pro ka�dou d�lku dan� energie zaznamenan�ho pixelu
		Map<Double,Double> delka_energie_round = new TreeMap<Double,Double>();//mapa, ve kter� je pro ka�dou d�lku dan� energie intervalu dr�hy
		double pocatecni_l = 0;//prom�nn� d�lek
		double l_pos_bodu = 0;
		double p; //absolutn� parametr v rovnici p��mky
		double energie_vyhovujicich_castic;
		p = -act_point.getX() -(a_b[1]*act_point.getY());//po��te�n� hodnota parametru
		System.out.println(dir_vector.getX());
		System.out.println(dir_vector.getY());

		System.out.println(velikost_vektoru);
	
		while (delka_energie.isEmpty()) {//tento cyklus se opakuje do doby, dokud nebude nalezen 1 bod le��c� na kolm� p��mce
			energie_vyhovujicich_castic = 0;
		for(int i = 0; i < souradnice_x.size();i++) {//kontrola v�ech bod�, zda nele�� na p��mce v dan�m posunut�
		if	((Math.abs( (souradnice_x.get(i) +  (a_b[1]*souradnice_y.get(i)) + p)/(Math.pow(1 + Math.pow(a_b[1], 2), 0.5)))  <= velikost_vektoru /*vzd�lenost bodu od p��mky mus� b�t men�� ne� velikost vektoru, kter�m posunujeme*/)   &&  sekundarni_pole[souradnice_x.get(i)][souradnice_y.get(i)] != -1/*m�nus jedni�kou jsou ozna�eny body, kter� byly ji� algorytmem zpracov�ny*/) {
			
			energie_vyhovujicich_castic = energie_vyhovujicich_castic + sekundarni_pole [souradnice_x.get(i)] [souradnice_y.get(i)];//ulo�en� energie vyhovuj�c� bu�ky, pokud 2 bu�ky ve stejn�m posunut� maj� vyhovuj�c� vzd�lenost od posunovan� p��mky, bere se v potaz jejich sou�et
			System.out.println((Math.abs( (souradnice_x.get(i) +  (a_b[1]*souradnice_y.get(i)) + p)/(Math.pow(1 + Math.pow(a_b[1], 2), 0.5)))));//v�pis
			
			
			sekundarni_pole[souradnice_x.get(i)][souradnice_y.get(i)] = -1;//ozna�en� bu�ky, �e byla ji� zpracov�na
            
			
		}
		    
				
				
			}
		 
		if(energie_vyhovujicich_castic > 0) {//um�st�n� prvn�ho bodu s energi� do mapy
			
			delka_energie.put(0.0,energie_vyhovujicich_castic);
			break;
		}
		
		
		act_point = new Bode(act_point.getX() + ( dir_vector.getX()),act_point.getY() + ( dir_vector.getY()));//prom�nn� dan�ho bodu na aproximovan� p��mce, s n�m� je posunov�no
		p = -act_point.getX() -(a_b[1]*act_point.getY());//�prava parametru
		
		l = l + velikost_vektoru;//p�i�ten� velikosti vektoru k celkov� velikosti posunut�
		
		
		}
		
		System.out.println(delka_energie.get(0.0));
		System.out.println(max_l);
		System.out.println(l);
		
		
		pocatecni_l = l;//po��te�n� vzd�lenost
		act_point = new Bode(act_point.getX() + ( dir_vector.getX()),act_point.getY() + ( dir_vector.getY()));
		p = -act_point.getX() -(a_b[1]*act_point.getY());
		
		l = l + velikost_vektoru;


		
		
		while ((l - pocatecni_l ) <= (max_l )) {//cyklus se opakuje do doby, ne� bude p��mka posunuta o maxim�ln� vzd�lenost
			
         //cyklus je t�m�� stejn� jako p�edchoz� cyklus
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
		
		
		
		//d�le n�sleduj� ji� jen �pravy, zaokrouhlov�n�, v�pisy
		//kontroln� sou�et energi�, zda nebyla vynech�na ��dn� bu�ka
		double c_energie = 0;
		for(double a : delka_energie.keySet() ) {
		System.out.println(a + " " + delka_energie.get(a));
			c_energie += delka_energie.get(a);
			
			
		}
		if(Math.abs(c_energie - celko_energie) < 1 ) {
			
			System.out.println("V poradku");
		
		
		}
		else {// v p��pad� nerovnosti energi� je vyps�na chyba
			System.err.println("Chyba energie");
			JFrame vokno = new JFrame("Chyba");
			JOptionPane.showMessageDialog(vokno, "Chyba energie. Nesed� celkov� sou�et energi�" + (c_energie - celko_energie), "InfoBox: " + "cetnost castic" , JOptionPane.INFORMATION_MESSAGE);
			System.exit(1);
			
		}
		
		
		
		
		
		
		System.out.println("" + (c_energie - celko_energie));
		
		
		
		
		
		int pocet_za_polovinou = 0;
		
		//d�le n�sleduje �prava sm�ru, ��stice je rozd�lena na 2 poloviny, body ve v�ech polovin�ch spo��t�ny, o��te�n� sm�r mus� kv�li blobu b�t tam, kde je v�c bod�
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
		
		//zaokrouhlen� vzd�lenost� jednotliv�ch bu�ek od po��tku dr�hy ��stice a n�sledn� se�ten� jejich energi� do interval� dr�hy
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
		
		
		// z�pis do souboru
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


