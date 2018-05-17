package txt_do_poctu;

public class MTSnimek extends Snimek { //tøída snímku expedimentálnì urèená k hledání mionù, lze však podle ní nalézat dlouhé pøímé èástice, analýza se nikterak moc neliší od pøedchozích
	protected int max_x = 0;
	protected int max_y = 0;
	protected int min_x = 256;
	protected int min_y = 256;
	protected int pocet_mion = 0;
	
	public void rozhled (int x , int y) { //tato metoda se od metody svého rodièe liší jen zapisováním max x-ových a y-ových souøadnic
		if(x > max_x) {
			
			max_x = x;
			
		}
	   if(x < min_x) {
			
			min_x = x;
			
		}
       if(y > max_y) {
			
			max_y = y;
			
		}
	   if(y < min_y) {
			
			min_y = y;
			
		}
		
		
		
		
		super.rozhled(x,y);//vykonání metody rodièe
		
		
   
   
		
		
	}
	public MTSnimek() {
		
			
		}
	public MTSnimek(double[][] pole) {
		super();
		hlavni_pole = new double [256] [256];
		sekundarni_pole = new double [256] [256];
		
		
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
		
		for(int i = 0; i < 256; i ++) {
	    	for(int j = 0; j < 256; j ++) {
	        if	(hlavni_pole[j][i] != 0) {
	        	max_x = 0;
	        	max_y = 0;
	        	min_x = 256;
	            min_y = 256;
	        	rozhled(j,i);
	        	
				if (pocitadlo <= 2)
	        	{
	        		seznam.put(typ_castice.Gama, seznam.get(typ_castice.Gama) + 1);
	        			prubezna_castice = new Castice(typ_castice.Gama,celkova_energie);
	        			Seznam_castic.add(prubezna_castice);
	        	}
               else if (pocet_obklopenych < 3 && (Math.pow(Math.pow(max_x - min_x,2) + Math.pow(max_y - min_y,2),0.5)) > 25) {
	        		
	        		seznam.put(typ_castice.Mion, seznam.get(typ_castice.Mion) + 1);
	        		prubezna_castice = new Castice(typ_castice.Mion,celkova_energie);
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
		this.pocet_mion = seznam.get(typ_castice.Mion);
		hlavni_pole = null;
		sekundarni_pole = null;
		
		
		
	}
	
	public int getPocet_mion() {
		
		return this.pocet_mion;
		
	}
	
	
	}
	


