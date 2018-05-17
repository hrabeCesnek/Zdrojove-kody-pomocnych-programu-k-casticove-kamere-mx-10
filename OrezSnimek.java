package Orezavatko;


import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

import txt_do_poctu.Castice;
import txt_do_poctu.MTSnimek;
import txt_do_poctu.typ_castice;


public class OrezSnimek extends MTSnimek {

protected double zapisovaci_pole [] []; 
protected double prepisovaci_pole [] [];


public void rozhled(int x, int y) {//klasická vylepšená metoda rozhled
	super.rozhled(x, y);
	
	zapisovaci_pole[x][y] = sekundarni_pole[x][y]; //pole pro uložení jedné èástice
	
	
	
}



	public OrezSnimek(double[][] pole, String way, int min_vel, double min_energie, double min_uhl /*pøijaté parametry jsou 2D pole, cesta k uložení a parametry, které zadal uživatel*/ ) throws IOException {//hlavní konstruktor, kde probíhá vìtšina kódu
		
		super();
		int cislo_castice = 1;
		int posun_x;
		int posun_y;
		hlavni_pole = new double [rozmer_x] [rozmer_y];
		sekundarni_pole = new double [rozmer_x] [rozmer_y];
		BufferedWriter bw = null;
		prepisovaci_pole = new double [256][256];
		
		for(int i = 0;i < 256; i++ ) {
			for (int j = 0; j< 256; j++) {
				hlavni_pole [i] [j] = pole [i] [j];
				sekundarni_pole [i] [j] = pole [i] [j];
				
				
			}
			
			
		}
		
		
		
		Castice prubezna_castice;
		double energie_snimku = 0;
		seznam.put(typ_castice.Gama, 0);
		seznam.put(typ_castice.Beta, 0);
		seznam.put(typ_castice.Alfa, 0);	
		seznam.put(typ_castice.Other, 0);	
		seznam.put(typ_castice.Mion, 0);
		pocitadlo = 0;
		pocet_obklopenych = 0;
		celkova_energie = 0;
		
		
		//program používá stejných algoritmù pro prohledávání pole jako pøedchozí programy
		
		for(int i = 0; i < 256; i ++) {
	    	for(int j = 0; j < 256; j ++) {
	        if	(hlavni_pole[j][i] != 0) {
	        	
	        	zapisovaci_pole = new double[rozmer_x] [rozmer_y];
	        	prepisovaci_pole = new double[rozmer_x] [rozmer_y];
	        	for(int hh = 0; hh < rozmer_x ;hh++) { //vyèištìní pole od pøedchozí èástice
	        		for(int f = 0; f < rozmer_y;f++) {
	        			zapisovaci_pole[hh][f] = 0.0;
	        			
	        			
	        		}
	        		
	        		
	        		
	        	}
	        	
	        	
	        	
	        	
	        	max_x = 0;
	        	max_y = 0;
	        	min_x = 256;
	            min_y = 256;
	        	rozhled(j,i);
	        	
				if (pocitadlo <= 2)
	        	{   //vyfiltrování èástic gama, jejichž vypisování by mohlo zpùsobit zahlcení disku
	        		seznam.put(typ_castice.Gama, seznam.get(typ_castice.Gama) + 1);
	        			prubezna_castice = new Castice(typ_castice.Gama,celkova_energie);
	        			Seznam_castic.add(prubezna_castice);
	        	}
               else if (pocet_obklopenych > min_vel && (Math.pow(Math.pow(max_x - min_x,2) + Math.pow(max_y - min_y,2),0.5)) > min_uhl && celkova_energie > min_energie) {
	        		//pøípad, kdy èástice odpovídá zadaným parametrùm
	        		seznam.put(typ_castice.Mion, seznam.get(typ_castice.Mion) + 1);
	        		prubezna_castice = new Castice(typ_castice.Mion,celkova_energie);
	        		Seznam_castic.add(prubezna_castice);
	        		
	        		
	        		
	        		
	        		
	        		
	        		try 
	        		
	        		{
	      	
	        			
	        			bw = new BufferedWriter(new FileWriter(way + cislo_castice+".txt"));
	        			// vypoèítání posunu èástice ke støedu 
	        			posun_x = ((int)(max_x+min_x)/2) - (rozmer_x/2) ;
	        			posun_y = ((int)(max_y+min_y)/2) - (rozmer_y/2) ;
	        			cislo_castice++; //èíslo èástice, v jednoum txt souboru mùže být více èástic, co tìmto parametrùm vyhovují
	        			
	        			for(int k = 0; k < rozmer_y; k++) { 
	        		    	for(int l = 0; l < rozmer_x; l++) { //pøepsání do støedu dalšího pole 
	        		    		if(zapisovaci_pole [l][k] != 0.0) {
	        		    		prepisovaci_pole [l-posun_x][k-posun_y] = zapisovaci_pole [l][k];
	        		    		
	        		    			
	        		    			
	        		    			
	        		    			
	        		    		}
	        		    		
	        		    		
	        		    		
	        		    		
	        		    		
	        		    		
	        		    			
	        		    		}
	        		    		
	        		    		
	        		    	}
	        		    	
	        		    	
	        		    	
	        		    
	        			
	        			
	        			
	        			
	        			
	        		    for(int k = 0; k < rozmer_y; k++) {      //zapsání pole do souboru
	        		    	for(int l = 0; l < rozmer_x; l++) {
	        		    		if(prepisovaci_pole[l][k] != 0.0) { 
	        		    		bw.write((prepisovaci_pole[l][k]) + " " );
	        		    		}
	        		    		else {
	        		    			bw.write("0 " );
	        		    			
	        		    			
	        		    		}
	        		    		
	        		    		
	        		    	}
	        		    	bw.newLine();
	        		    	bw.flush();
	        		    	
	        		    	
	        		    }
	        				
	        				
	        				
	        			
	        			
	        		  
	        			
	        			
	        			
	        	
	        	
	        }catch (Exception e)
	        {       e.printStackTrace();
	                System.err.println("Do souboru se nepovedlo zapsat.");
	        }
	        		
	        		bw.close();
	        		
	        		
	        		
	        	
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        	}
	        	else if (pocet_obklopenych < 5) {
	        		
	        		seznam.put(typ_castice.Beta, seznam.get(typ_castice.Beta) + 1);
	        		prubezna_castice = new Castice(typ_castice.Beta,celkova_energie);
	        		Seznam_castic.add(prubezna_castice);
	        	}
	             else if (pocet_obklopenych >= 5 && celkova_energie > 150) {
	        		seznam.put(typ_castice.Alfa, seznam.get(typ_castice.Alfa) + 1);
	        		prubezna_castice = new Castice(typ_castice.Alfa,celkova_energie);
	        		Seznam_castic.add(prubezna_castice);
	        	} 
	             else
	             {
	            	 seznam.put(typ_castice.Other, seznam.get(typ_castice.Other) + 1);
		        		prubezna_castice = new Castice(typ_castice.Other,celkova_energie);
		        		Seznam_castic.add(prubezna_castice);
	            	 
	             }
	        	
	        	pocitadlo = 0;
	        	pocet_obklopenych = 0;
	        	energie_snimku = energie_snimku +  celkova_energie;
	        	 celkova_energie = 0;
	        	
	   	    
	        }
	        		
	        		
	        
	        		
	        	
	        	
	        	
	        	
	        }
	    	
	    		
	    	
	    	
	    	
	 }
			
			
			
			
		
		
		
		this.energie = energie_snimku;
		this.pocet_alfa = seznam.get(typ_castice.Alfa);
		this.pocet_beta = seznam.get(typ_castice.Beta);
		this.pocet_gama = seznam.get(typ_castice.Gama);
		this.pocet_other = seznam.get(typ_castice.Other);
		this.pocet_mion = seznam.get(typ_castice.Mion);
		hlavni_pole = null;
		sekundarni_pole = null;
		
		
		
	}


	public OrezSnimek() {
		// TODO Auto-generated constructor stub
	}
	}


