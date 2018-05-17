package nacitani_gps;


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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;





public class GPSAltDec {
public GPSAltDec() {                                        // tento program slouží k analyzování GPS logu a vytvoøení mapy èasy - výšky
	
	
}
public Map<Integer,Double> decodeAltitude() throws IOException { //hlavní metoda, kterou používá program pro párování nadmoøské výšky a snímku
	
	JFileChooser chooser = new JFileChooser();
	  chooser.setMultiSelectionEnabled(true);
	  chooser.showOpenDialog((Frame)null);
	  File[] files = chooser.getSelectedFiles();
	  Reader[] rd = new FileReader[files.length];
	  BufferedReader[] brd = new BufferedReader[files.length]; //inicializace k naètení souboru Logù (logù mùže být vybráno i více
	  
	 
	  
	  
	
	
	  JFrame frame = new JFrame("Oprava casu");
	  int opravny_cas = Integer.parseInt((JOptionPane.showInputDialog(frame,"Zadejte zpozdeni casu oproti timepix, pokud jsou casy sladene, zadejte 0"))); //možnost opravného posuvu èasu oproti timepix, pokud jsou èasy sladìné
	  chooser.setVisible(true);
		
	
		String radek;
		String casti[];
		String casti_b[];                          //pomocné øetìzce
		Map <Integer,Double> casy_vysky = new TreeMap<Integer,Double>(); //hlavní mapa
		
		
		try {
			
			for(int i = 0 ; i < files.length; i ++) { //hromadná inicializace k naètení Logù
				  rd [i] = new FileReader(files[i].getPath());
				  brd [i]= new BufferedReader(rd[i]); 
				  
				  
			  }
			 chooser.setVisible(false);
			
		}
		
		catch (IOException e) { //odchycení chyby
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
		
		
		
			for(BufferedReader buff: brd) { //cyklus analýzy Logu
		
		try {
			
	
		
		do {
			
			
			
			buff.readLine();
			radek = buff.readLine();
			
			if(radek!= null) {
				
			
				casti = radek.split("GPGGA,"); //oddìlení èástí textového øetìzce, které udávají èas a výšku
				casti = casti[1].split(",N,");
				 casti = casti[0].split(","); 
				  
			casti_b = radek.split(",E,");
			casti_b = casti_b[1].split(",M,");
			casti_b = casti_b[0].split(",");
			          //zapsání do mapy
					casy_vysky.put(((int)((Double.parseDouble(casti [0].substring(0,2)) * 3600) + (Double.parseDouble(casti [0].substring(2,4)) * 60) + (Double.parseDouble(casti [0].substring(4,10)))  + (double) opravny_cas)),Double.parseDouble(casti_b [3]));
			
		
			}
				
			
			
				
				
				
				
			}
			
			
			
		while (radek!= null);
		
			   
		
			   
		
			   
			   
			   
			   
			   
		
		
		
		
		
		
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
			
			
			
			
		}
      		
      		
      		
      		
      	
      	
      	
      	
      }
  
  	
  
		
		
			
	        	
	        	
	        
			for(Reader a: rd )
			{
				a.close();
				
				
			}
			for(Reader a: brd )
			{
				a.close();
				
				
			}

      	
  				
  				
	
	
	
	return casy_vysky; //vrácení mapy
	
	
	
	
	
	
}
}
		
			
			
			
			

