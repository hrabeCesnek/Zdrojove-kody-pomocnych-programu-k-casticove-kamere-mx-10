package txt_do_poctu;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JFrame;

public class Snimek implements Comparable<Snimek>{       //pomocná tøída reprezentující 1 snímek v txt souboru 256 x 256 obsahuje funkce k jeho naètení a ke zpìtnému naèítání vlastností 
protected double energie;
protected int pocet_alfa = 0;
protected int pocet_beta = 0; 
protected int pocet_gama = 0;
protected int pocet_other = 0;
protected Map <typ_castice,Integer> seznam = new HashMap<typ_castice,Integer>();
protected static LinkedList<Castice> Seznam_castic= new LinkedList<Castice>();
static Rectangle pom_ctverec;	
static LinkedList <Rectangle> ctverecky = new LinkedList <Rectangle>();
static LinkedList <Color> barvicky = new LinkedList <Color>();
protected final static int rozmer_x= 256;
protected final static int rozmer_y= 256;
protected static double [] [] hlavni_pole;
protected static  double [] [] sekundarni_pole;
protected static int pocitadlo;
protected static int pocet_obklopenych;
protected static double celkova_energie;
protected double obklopene [] [];


public void rozhled(int x, int y) {                                //stejná metoda jako u prvotního programu na analýzu snímkù, jsou zde jen pøidány podmínky pro barevné vykreslení
	
	ctverecky.addFirst(new Rectangle((x+1)*2,(y+1)*2, 2, 2)); 
	if(hlavni_pole[x][y] < 10) {
		barvicky.addFirst(Color.green);
		
	}
	else if(hlavni_pole[x][y] < 50) {
		barvicky.addFirst(Color.yellow);
		
	}
		
		else if(hlavni_pole[x][y] < 150) {
			barvicky.addFirst(Color.orange);
			
			
			
		}
		else {
			barvicky.addFirst(Color.red);

			
			
		}
	
	
		
	
	
	celkova_energie = celkova_energie + hlavni_pole[x][y];
	hlavni_pole[x][y] = 0;
	pocitadlo++;

	
	
	
	
	
	if (x == 0 && y == 0) {
		if(sekundarni_pole[x+1][y] != 0 && sekundarni_pole[x][y+1] != 0 && sekundarni_pole[x+1][y+1] != 0) {
				pocet_obklopenych ++;
		}
				
				
		if(hlavni_pole[x+1][y] != 0) {
			rozhled(x+1,y);
		}
		if(hlavni_pole[x][y+1] != 0) {
			rozhled(x,y+1);
		}
		if(hlavni_pole[x+1][y+1] != 0) {
			rozhled(x+1,y+1);
		}
		
		
		
		
	}

	
	
	else if (x == 255 && y == 255) {
		
		if(sekundarni_pole[x-1][y] != 0 && sekundarni_pole[x][y-1] != 0 && sekundarni_pole[x-1][y-1] != 0) {
			pocet_obklopenych ++;
	}
		
		
		
		if(hlavni_pole[x-1][y] != 0) {
			rozhled(x-1,y);
		}
		if(hlavni_pole[x][y-1] != 0) {
			rozhled(x,y-1);
		}
		if(hlavni_pole[x-1][y-1] != 0) {
			rozhled(x-1,y-1);
		}
		
		
		
		
	}
	
	
	
else if (x == 0 && y == 255) {
		
	if(sekundarni_pole[x+1][y] != 0 && sekundarni_pole[x][y-1] != 0 && sekundarni_pole[x+1][y-1] != 0) {
		pocet_obklopenych ++;
}
	
	
		if(hlavni_pole[x+1][y] != 0) {
			rozhled(x+1,y);
		}
		if(hlavni_pole[x][y-1] != 0) {
			rozhled(x,y-1);
		}
		if(hlavni_pole[x+1][y-1] != 0) {
			rozhled(x+1,y-1);
		}
		
		
		
		
	}
	
	
	
	
	
else if (x == 255 && y == 0) {
	
	if(sekundarni_pole[x-1][y] != 0 && sekundarni_pole[x][y+1] != 0 && sekundarni_pole[x-1][y+1] != 0) {
		pocet_obklopenych ++;
}
	
	
	
	
	
	
	
	
	
	
	if(hlavni_pole[x-1][y] != 0) {
		rozhled(x-1,y);
	}
	if(hlavni_pole[x][y+1] != 0) {
		rozhled(x,y+1);
	}
	if(hlavni_pole[x-1][y+1] != 0) {
		rozhled(x-1,y+1);
	}
	
	
	
	
}
else if(x == 0) {
	
	
	
	if(sekundarni_pole[x+1][y] != 0 && sekundarni_pole[x][y+1] != 0  && sekundarni_pole[x][y-1] != 0 && sekundarni_pole[x+1][y+1] != 0
			   && sekundarni_pole[x+1][y-1] != 0            ) {
		pocet_obklopenych ++;
}
	
	
	
	
	
	if(hlavni_pole[x+1][y] != 0) {
		rozhled(x+1,y);
	}
	 if(hlavni_pole[x][y+1] != 0) {
		rozhled(x,y+1);
	}
	 if(hlavni_pole[x][y-1] != 0) {
		rozhled(x,y-1);
	}
	 if(hlavni_pole[x+1][y+1] != 0) {
		rozhled(x+1,y+1);
	}
	 if(hlavni_pole[x+1][y-1] != 0) {
		rozhled(x+1,y-1);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
	
	
	
else if(x == 255) {
	
	
	
	if(sekundarni_pole[x-1][y] != 0 && sekundarni_pole[x][y+1] != 0  && sekundarni_pole[x][y-1] != 0 && sekundarni_pole[x-1][y+1] != 0
			   && sekundarni_pole[x-1][y-1] != 0            ) {
		pocet_obklopenych ++;
}
	
	
	
	
	
	if(hlavni_pole[x-1][y] != 0) {
		rozhled(x-1,y);
	}
	 if(hlavni_pole[x][y+1] != 0) {
		rozhled(x,y+1);
	}
	 if(hlavni_pole[x][y-1] != 0) {
		rozhled(x,y-1);
	}
	 if(hlavni_pole[x-1][y+1] != 0) {
		rozhled(x-1,y+1);
	}
	 if(hlavni_pole[x-1][y-1] != 0) {
		rozhled(x-1,y-1);
	}
	
	
	
	
	
	
}
else if(y == 0) {
	
	if(sekundarni_pole[x+1][y] != 0  && sekundarni_pole[x][y+1] != 0 && sekundarni_pole[x-1][y] != 0 && sekundarni_pole[x+1][y+1] != 0
			 && sekundarni_pole[x-1][y+1] != 0     )  {
		pocet_obklopenych ++;
}
	
	
	
	
	
	if(hlavni_pole[x+1][y] != 0) {
		rozhled(x+1,y);
	}
	 if(hlavni_pole[x][y+1] != 0) {
		rozhled(x,y+1);
	}
	 if(hlavni_pole[x-1][y] != 0) {
		rozhled(x-1,y);
	}
	 if(hlavni_pole[x+1][y+1] != 0) {
		rozhled(x+1,y+1);
	}
	 if(hlavni_pole[x-1][y+1] != 0) {
		rozhled(x-1,y+1);
	}
	
	
	
	
	
	
}
	
	
else if(y == 255) {
	
	if(sekundarni_pole[x+1][y] != 0 && sekundarni_pole[x][y-1] != 0 && sekundarni_pole[x-1][y] != 0 && sekundarni_pole[x+1][y-1] != 0
			 && sekundarni_pole[x-1][y-1] != 0     ) {
		pocet_obklopenych ++;
}
	
	
	
	
	
	if(hlavni_pole[x+1][y] != 0) {
		rozhled(x+1,y);
	}
	 if(hlavni_pole[x][y-1] != 0) {
		rozhled(x,y-1);
	}
	 if(hlavni_pole[x-1][y] != 0) {
		rozhled(x-1,y);
	}
	 if(hlavni_pole[x+1][y-1] != 0) {
		rozhled(x+1,y-1);
	}
	 if(hlavni_pole[x-1][y-1] != 0) {
		rozhled(x-1,y-1);
	}
	
	
	
	
	
	
}
	
	
	
	
	
	
	
	
	
	
else {
	if(sekundarni_pole[x+1][y] != 0 && sekundarni_pole[x][y+1] != 0 && sekundarni_pole[x-1][y] != 0 && sekundarni_pole[x][y-1] != 0 && sekundarni_pole[x+1][y+1] != 0
			&& sekundarni_pole[x-1][y-1] != 0	 && sekundarni_pole[x-1][y+1] != 0    && sekundarni_pole[x+1][y-1] != 0            ) {
		pocet_obklopenych ++;
}
	
	
	
	
	
	if(hlavni_pole[x+1][y] != 0) {
		rozhled(x+1,y);
	}
	 if(hlavni_pole[x][y+1] != 0) {
		rozhled(x,y+1);
	}
	 if(hlavni_pole[x-1][y] != 0) {
		rozhled(x-1,y);
	}
	 if(hlavni_pole[x][y-1] != 0) {
		rozhled(x,y-1);
	}
	 if(hlavni_pole[x+1][y+1] != 0) {
		rozhled(x+1,y+1);
	}
	 if(hlavni_pole[x-1][y-1] != 0) {
		rozhled(x-1,y-1);
	}
	 if(hlavni_pole[x-1][y+1] != 0) {
		rozhled(x-1,y+1);
	}
	 if(hlavni_pole[x+1][y-1] != 0) {
		rozhled(x+1,y-1);
	}
}
	
	
	
	
	
	
	
	
	
}



















public Snimek  (){}         //tento konstruktor je zde jen z dùvodu, že další programy vužívají dìdiènosti z této tøídy

public Snimek  (double pole [] []){     //hlavní konstruktor, analyzování snímku, hlavním argumentem je pole 256x256 s hodnotami energií
	hlavni_pole = new double [256] [256];
	sekundarni_pole = new double [256] [256];
	
	                                      //následuje analýza, která je témìø shodná s analýzou z prvotního programu na analýtz snímkù 
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
	pocitadlo = 0;
	pocet_obklopenych = 0;
	celkova_energie = 0;
	
	for(int i = 0; i < 256; i ++) {
    	for(int j = 0; j < 256; j ++) {
        if	(hlavni_pole[j][i] != 0) {
        	
        	rozhled(j,i);
        	
			if (pocitadlo <= 2)
        	{
        		seznam.put(typ_castice.Gama, seznam.get(typ_castice.Gama) + 1);
        			prubezna_castice = new Castice(typ_castice.Gama,celkova_energie);
        			Seznam_castic.add(prubezna_castice);
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
	hlavni_pole = null;
	sekundarni_pole = null;
	
	
	                                  //dále následují metody, které nám vracejí informace o snímcích
}
public double getEnergie() {
	
	return this.energie;
	
}
public int getPocet_alfa() {
	
	return this.pocet_alfa;
	
}
public int getPocet_beta() {
	
	return this.pocet_beta;
	
}
public int getPocet_gama() {
	
	return this.pocet_gama;
	
}
public int getPocet_other() {
	
	return this.pocet_other;
	
}
public LinkedList<Castice> getVypis() {
	
	return this.Seznam_castic;
	
}
public LinkedList<Rectangle> getObraz(){
	return this.ctverecky;
	
	
}
public LinkedList<Color> getBarve(){
	return this.barvicky;
	
	
}





















@Override
public int compareTo(Snimek arg0) {
	
	return Double.compare(this.energie, arg0.energie);
}





}
