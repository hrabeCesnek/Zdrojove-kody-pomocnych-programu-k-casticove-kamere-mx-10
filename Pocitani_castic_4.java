package txt_do_poctu;

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
import java.awt.Rectangle;
import javax.swing.JFileChooser;
import javax.swing.JFrame;










import javax.swing.JOptionPane;

import java.util.HashMap;
import java.util.LinkedList;
public class Pocitani_castic_4  {

	public Pocitani_castic_4() {
		
		
		
		
	}
static Rectangle pom_ctverec;	                              // inicializace v�ech pot�ebn�ch prom�nn�ch
static LinkedList <Rectangle> ctverecky = new LinkedList <Rectangle>();
static	JFrame obraz = new JFrame();
private static int rozmer_x= 256;
private static int rozmer_y= 256;
static double [] [] hlavni_pole = new double [rozmer_x][rozmer_y];
static  double [] [] sekundarni_pole = new double [rozmer_x][rozmer_y];
static int pocitadlo = 0;
static int pocet_obklopenych = 0;
static double celkova_energie = 0;


private static void rozhled(int x, int y) {       //rekurzivn� metoda na prohled�v�n� okoln�ho pole
	ctverecky.add(new Rectangle((x+1)*2,(y+1)*2, 2, 2)); //seznam, kter� hraje roli a� p�i vykreslov�n�
	celkova_energie = celkova_energie + hlavni_pole[x][y];    //celkov� energie spojit� ��stice
	hlavni_pole[x][y] = 0;                                    //jej� p�eps�n� na nulu
	pocitadlo++;                                              //prom�nn� kter� slou�� k spo��t�n� 
                                                              //celkov�ho po�tu bu�ek, kter� ��stice zab�r�
	if (x == 0 && y == 0) {                                   //d�le n�sleduj� podm�nky, kter� ur�uj�, zda jsme se neocitli na okraji pole, 
		if(sekundarni_pole[x+1][y] != 0 && sekundarni_pole[x][y+1] != 0 && sekundarni_pole[x+1][y+1] != 0) { //podm�nka zji��uj�c�, zda jsme ze v�ech stran obklopeni
				pocet_obklopenych ++;
		}			
		if(hlavni_pole[x+1][y] != 0) {            //  rozhled do okoln�ch bun�k
			rozhled(x+1,y);                       //rekurzivn� zavol�n� metody rozhled
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
	
	
	
	
	
	
	
	
	
	
else {      // p��dad, kdy nejsme ani na jednom z okraj� pole                      
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









	public static void main(String[] args) throws IOException {
		
		Castice prubezna_castice;            //pomocn� prom�nn�, jej� typ je takt� vyst�tlen v souboru obsahuj�c� jej� t��du - Castice
		
		
	    LinkedList<Castice> Seznam_castic = new LinkedList<Castice>();   // incializace seznamu pro ��stice, kter� budou nalezeny
	
	 
		obraz.setSize(512, 512);
		obraz.setTitle("pozice castic");
	   obraz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  obraz.setBackground(Color.black);                                //inicializace okna pro vykreslov�n�
	  
		
		int [] pocty_castic =new int[]{0,0,0,0};
		
	  JFileChooser chooser = new JFileChooser();
	  chooser.setMultiSelectionEnabled(true);
	  chooser.showOpenDialog((Frame)null);
	  File[] files = chooser.getSelectedFiles();
	  Reader[] rd = new FileReader[files.length];
	  BufferedReader[] brd = new BufferedReader[files.length];        //inicializace prom�nn�ch pro pr�ci se soubory, program dok�e analyzovat n�kolik txt zar�z
	  
	 
	  
	  
	/*	FileDialog dialog = new FileDialog((Frame)null, SWT.MULTI);*/
		
		
	  chooser.setVisible(true);
		Map <typ_castice,Integer> seznam = new HashMap<typ_castice,Integer>();//prom�nn� se seznamem v�ech ��stic
		int p = 0;//prom�nn� pro ur�en� ��sla ��dku
		 seznam.put(typ_castice.Gama, 0);
			seznam.put(typ_castice.Beta, 0);
			seznam.put(typ_castice.Alfa, 0);	
			seznam.put(typ_castice.Other, 0);	
		
		String radek;//prom�nn� pro na�ten� 1 ��dku
		String casti[];//pole textov�ch �etezc�
		try {
			
			for(int i = 0 ; i < files.length; i ++) { //hromadn� inicializace n�stroj� pro �ten� soubor�
				  rd [i] = new FileReader(files[i].getPath());
				  brd [i]= new BufferedReader(rd[i]); 
				  
				  
			  }
			 chooser.setVisible(false);
			
		}
		
		catch (IOException e) {   //odchycen� vyj�mky
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
		
		
		
			for(BufferedReader buff: brd) {
		
		try {
			
			
		
			  
		
		do {// cyklus pro p�evod hodnot txt do 2D pole
			
			
			
			radek = buff.readLine();//na�ten� ��dku
			if(radek!= null) {
				casti = radek.split(" ");//rozd�len� podle mezer na ��sti, tzn. na jednotliv� hodnoty
			for(int i = 0; i < casti.length; i ++) {
			hlavni_pole [i][p] = Double.parseDouble(casti[i]);//zaps�n� do 2D pole
			sekundarni_pole [i][p] = Double.parseDouble(casti[i]);
			
			
			
				
				
				
			}
				
			
				p++;
				
				
				
				
			}
			
			
			
		}while (radek!= null);
		
			   
		
			   
			   
			   
			   
			   
			   
			   
		
		
		
		
		
		
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
	
		
		
		       for(int i = 0; i < 256; i ++) {   //hlavn� cyklus, kter� proch�z� pole s energiemi
	    	   for(int j = 0; j < 256; j ++) {
	           if	(hlavni_pole[j][i] != 0) {                                                  
	        	
	        	rozhled(j,i);          //zavol�n� rekurzivn� metody
	        	if (pocitadlo <= 2)    //d�le n�sleduje rozhodov�n� o typu nalezen� 
	        		                   //��stice za pomoc� prom�n�ch, do kter�ch zapisovala 
	        	{                      //metoda rozhled
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
	        	celkova_energie = 0;
	        	
	        }
	        		
	        		
	        		
	        		
	        	
	        	
	        	
	        	
	        }
	    	
        		
        	
        	
        	
     }
	
		}
		
		
		for(typ_castice typ : seznam.keySet()) {         //d�le n�sleduj� jen v�pisy a vykreslov�n�, konce r�zn�ch verz� analytick�ho programu se li�� dle toho, co bylo t�eba vypisovat
			System.out.println(typ + ":" + seznam.get(typ));
			
		}
		nakresli_ctverecek kresli = new nakresli_ctverecek(ctverecky);
		obraz.add(kresli);
		obraz.setVisible(true);
		JOptionPane.showMessageDialog(null, "alfa: "  + seznam.get(typ_castice.Alfa) + "\n" + "beta: "  + seznam.get(typ_castice.Beta ) +  "\n"+"gama: "  + seznam.get(typ_castice.Gama)  + "\n"+"other: "  + seznam.get(typ_castice.Other), "InfoBox: " + "cetnost castic", JOptionPane.INFORMATION_MESSAGE);
		
		
		for(Reader a: rd )
		{
			a.close();
			
			
		}
		for(Reader a: brd )
		{
			a.close();
			
			
		}	
		FileDialog dialog = new FileDialog((Frame)null, "Select Folder to save");                                           //ulo�en� do souboru
		dialog.setVisible(true);
try (BufferedWriter bw = new BufferedWriter(new FileWriter(dialog.getDirectory()+dialog.getFile()+ ".txt"))) //zde n�sleduje z�pis v�sledk� do souboru, verze programu se li�� podle vypisov�n�
		
		{
			bw.write("Energy Alfa Beta Gama Other");
			double nejvetsi_energie = 0;
			for(Castice a:Seznam_castic ) {
				if(a.getEnergy()>nejvetsi_energie) {
					nejvetsi_energie = a.getEnergy();
					
					
				}
				
				
				
			}
			
			if(nejvetsi_energie > 25000) {
				nejvetsi_energie = 25000;
				
				
			}
			
			
			for(int i = 0; i< ((int)(Math.round(nejvetsi_energie))+1);i++) {
		        for(Castice a: Seznam_castic) {
		        if((int)(Math.round(a.getEnergy())) == i || ((((int)(Math.round(a.getEnergy())) > i)) && (i == ((int)Math.round(nejvetsi_energie))))) {
		        	if(a.getTyp() == typ_castice.Alfa) pocty_castic[0]++;
		        	else if (a.getTyp() == typ_castice.Beta) pocty_castic[1]++;
		        	else if (a.getTyp() == typ_castice.Gama) pocty_castic[2]++;
		        	else if (a.getTyp() == typ_castice.Other) pocty_castic[3]++;
		        	}
		        }
		        bw.newLine();
		        bw.write(i + "      " + pocty_castic[0]+ "     " + pocty_castic[1] + "     " + pocty_castic[2]+ "     " + pocty_castic[3]);
		        bw.flush();
		   
		       for(int j = 0; j < 3;j++) {
		    	   pocty_castic[j] = 0;
		    	   
		    	   
		       }
		        
		        
		        
		        
			}
			
			 bw.newLine();
		        bw.flush();
		
		
		}
		catch (Exception e)
		{
		        System.err.println("Do souboru se nepovedlo zapsat.");
		}
		
		
}}
		
		
		
		
		

