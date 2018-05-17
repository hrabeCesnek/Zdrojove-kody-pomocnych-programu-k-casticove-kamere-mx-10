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
import java.util.TreeMap;
import java.awt.Rectangle;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import nacitani_gps.GPSAltDec;
import java.util.HashMap;
import java.util.LinkedList;



public class Pocitani_castic_9  {            //tento program slouží k pøiøazování nadmoøských výšek ke snímkùm
	private static int rozmer_x= 256;        //rozmery snímku
	private static int rozmer_y= 256;
	static double [] [] hlavni_pole = new double [rozmer_x][rozmer_y]; //hlavní pole s energiemi
	public Pocitani_castic_9() {
		
		
		
		
	}




	public static void main(String[] args) throws IOException {
		
	
	LinkedList<Castice> Seznam_castic = new LinkedList<Castice>();//seznam nalezených èástic
	
	
	
	JFrame obraz = new JFrame();
		obraz.setSize(1024, 1024);
		obraz.setTitle("pozice castic");
	   obraz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  obraz.setBackground(Color.black);                     //vykreslování	
	  GPSAltDec myGPS = new GPSAltDec();                   
	  Map <Integer,Double> casy_vysky = myGPS.decodeAltitude();     // incializace naší vlastní tøídy ke ètení GPS logu, více informací v souboru této tøídy
		 Map <Integer,Snimek> casy_snimky = new TreeMap<Integer,Snimek>();  //mapa casy-snímky
		 int pomocny_cas;
		 Snimek pomocny_snimek = null;
		
		
		 LinkedList <Rectangle> ctverecky = new LinkedList <Rectangle>(); //tøída k vykreslování
	  JFileChooser chooser = new JFileChooser();                          
	  chooser.setMultiSelectionEnabled(true);
	  chooser.showOpenDialog((Frame)null);
	  File[] files = chooser.getSelectedFiles();           
	  Snimek snimky[] = new Snimek [files.length];
	  Reader[] rd = new FileReader[files.length];
	  Reader[] rd_dsc = new FileReader[files.length];
	  BufferedReader[] brd = new BufferedReader[files.length];
	  BufferedReader[] brd_dsc = new BufferedReader[files.length];    //naètení a inicializace tøíd ke ètení txt snímkù a jejich dsc souborù
	 
        
	
	 double energie_snimku = 0;
	  
	  
	 
	 
	 Map <typ_castice,Integer> seznam = new HashMap<typ_castice,Integer>(); //druhý seznam èástic
	 seznam.put(typ_castice.Gama, 0);
		seznam.put(typ_castice.Beta, 0);
		seznam.put(typ_castice.Alfa, 0);	
		seznam.put(typ_castice.Other, 0);	
	 
	 
	 
	 
	 
	 
	
		
		
	  chooser.setVisible(true);
		
		int p = 0;
		int p_dsc = 0;
		int snimek_no = 0;
		
		String radek;
		String radek_dsc = "";
		String casti[];
		String casti_dsc[];
		int cas_timepix = 0;
		try {                                                             //dále následuje hromadná inicializace tøíd ke ètení souborù (txt i dsc)
			
			for(int i = 0 ; i < files.length; i ++) {
				  rd [i] = new FileReader(files[i].getPath());
				  brd [i]= new BufferedReader(rd[i]); 
				  rd_dsc [i] = new FileReader(files[i].getPath() + ".dsc");
				  brd_dsc [i]= new BufferedReader(rd_dsc[i]); 
				  
			  }
			 chooser.setVisible(false);
			
		}
		
		catch (IOException e) {                                 //odhycení chyby
			e.printStackTrace();
			for(Reader a: rd )
			{
				a.close();
				
				
			}
			for(Reader a: brd )
			{
				a.close();
				
				
			}
			for(Reader a: rd_dsc )
			{
				a.close();
				
				
			}
			for(Reader a: brd_dsc )
			{
				a.close();
				
				
			}
			
			System.exit(1);
		
		
		}
		
		FileDialog dialog = new FileDialog((Frame)null, "Select Folder to save"); //výbìr složky pro uložení výpisu
		dialog.setVisible(true);
		
		
		
			
			for(BufferedReader buff: brd) { //ètení snímkù a jejich dsc souborù
				
		
		try {
			
			
			
			//ètení èasu
			radek_dsc = " ";
			while(!(radek_dsc.equals("\"Start time (string)\" (\"Acquisition start time (string)\"):") )) { //èas snímku se v dsc souboru nachází 2 øádky po øádku s tímto øetìzcem
				
		radek_dsc = brd_dsc[p_dsc].readLine();	
			}
			brd_dsc[p_dsc].readLine();
			
			casti_dsc = brd_dsc[p_dsc].readLine().split(" ");	
			casti_dsc = casti_dsc[3].split(":");        //postupné rozdìlení a vyètení èasu
	
			cas_timepix = (int) ((3600*Double.parseDouble(casti_dsc[0])) + (60*Double.parseDouble(casti_dsc[1])) + (Double.parseDouble(casti_dsc[2])));//pøevedení èasu na sekundy
			
			p_dsc ++;
			
			
			
			
			
			
			
		do {
			
			
			
			radek = buff.readLine(); // naètení snímku
			if(radek!= null) {
				casti = radek.split(" ");
			for(int i = 0; i < casti.length; i ++) {
			hlavni_pole [i][p] = Double.parseDouble(casti[i]);
			
			
			
			
				
				
				
			}
				
			
				p++;
				
				
				
				
			}
			
			
			
		}while (radek!= null);
		
			   
		pomocny_snimek = new Snimek(hlavni_pole); //umístìní naètených hodnot do tøídy snímek, kde je již jejich analýza provedena
		casy_snimky.put(cas_timepix, pomocny_snimek);  
		snimky[snimek_no] = pomocny_snimek ;	   
		snimek_no++;
			   
			   
			   
			   
		
		
		
		
		
		
		}catch (IOException e) { //odchycení chyby
			e.printStackTrace();
			for(Reader a: rd )
			{
				a.close();
				
				
			}
			for(Reader a: brd )
			{
				a.close();
				
				
			}
			for(Reader a: rd_dsc )
			{
				a.close();
				
				
			}
			for(Reader a: brd_dsc )
			{
				a.close();
				
				
			}
			
			System.exit(1);
			
			
		}
		p = 0;

		
		
		
		

		
			   
		
		}

			for(Snimek a : snimky) //vykreslení analyzovaných snímkù
			{				
				
				
				seznam.put(typ_castice.Alfa,a.getPocet_alfa() + seznam.get(typ_castice.Alfa));
				seznam.put(typ_castice.Beta,a.getPocet_beta() + seznam.get(typ_castice.Beta));
				seznam.put(typ_castice.Gama,a.getPocet_gama() + seznam.get(typ_castice.Gama));
				seznam.put(typ_castice.Other,a.getPocet_other() + seznam.get(typ_castice.Other));
				
				

				nakresli_ctverecek kresli = new nakresli_ctverecek(a.getObraz());
				obraz.add(kresli);
				
				
			}
			
			for(typ_castice typ : seznam.keySet()) {	//vypsání èástic dle typù
			System.out.println(typ + ":" + seznam.get(typ));
			
		}
		
		
		
		
		
		
		obraz.setVisible(true);
		JOptionPane.showMessageDialog(null, "alfa: "  + seznam.get(typ_castice.Alfa) + "\n" + "beta: "  + seznam.get(typ_castice.Beta ) +  "\n"+"gama: "  + seznam.get(typ_castice.Gama)  + "\n"+"other: "  + seznam.get(typ_castice.Other), "InfoBox: " + "cetnost castic", JOptionPane.INFORMATION_MESSAGE); //zobrazení èetnosti
		
try (BufferedWriter bw = new BufferedWriter(new FileWriter(dialog.getDirectory()+dialog.getFile()+ ".txt"))) //pøiøazování èasù a zápis do souboru
		
		{

	
	int min_rozdil = 50;
	int nej_cas = -1;
	for (Integer a: casy_snimky.keySet()) { //cyklus, který projde všechny èasy v mapì èasy-snímky
		
		
		if (!(casy_vysky.get(a) == null)) { //pokus o nalezení èasu z mapy èasy-snímky v mapì èasy výšky, pokud vyhovuje dojde k zapsání ve formátu výška + energie + èetnosti 
		bw.write(casy_vysky.get(a)+ " " + (double) (casy_snimky.get(a).getEnergie()) + " "  + (int) (casy_snimky.get(a).getPocet_alfa()) + " " +(int) (casy_snimky.get(a).getPocet_beta()) + " " + (int)(casy_snimky.get(a).getPocet_gama()) + " "  + ((int) casy_snimky.get(a).getPocet_other()) +  " "+ a); 
		}
		else {// opakovaný pokus o nalezení, nyní však již s malou tolerancí (max - až 20s)
			min_rozdil = 50;
		for (Integer b: casy_vysky.keySet()) {
			if(Math.abs((a.intValue() - b.intValue())) < min_rozdil) {
			min_rozdil = Math.abs((a - b));
			nej_cas = b;
				
			}
		}
			if(min_rozdil < 20) {
				
				bw.write(casy_vysky.get(nej_cas) + " " + (double)(casy_snimky.get(a).getEnergie()) + " "  + (int) (casy_snimky.get(a).getPocet_alfa()) + " " + (int) (casy_snimky.get(a).getPocet_beta()) + " " + (int)(casy_snimky.get(a).getPocet_gama()) + " "  + (int)(casy_snimky.get(a).getPocet_other()) +   " " + a); 
				
			}
			else { //nenalezení èasu
				bw.write(" " + "null" + " " + (double) (casy_snimky.get(a).getEnergie()) + " "  + (int) (casy_snimky.get(a).getPocet_alfa()) + " " +(int) (casy_snimky.get(a).getPocet_beta()) + " " + (int)(casy_snimky.get(a).getPocet_gama()) + " "  + (int) (casy_snimky.get(a).getPocet_other()) +  " "+ a); 
				
				
			}
				
				
		
			
			
		}
		bw.newLine();
  	        bw.flush();
	
	
	
		
		}
}catch (Exception e)    //odchycení chyby
{       e.printStackTrace();
        System.err.println("Do souboru se nepovedlo zapsat.");
}
		
		
		for(Reader a: rd )
		{
			a.close();
			
			
		}
		for(Reader a: brd )
		{
			a.close();
			
			
		}	
		for(Reader a: rd_dsc )
		{
			a.close();
			
			
		}
		for(Reader a: brd_dsc )
		{
			a.close();
			
			
		}
		
		
	
		
		
}}
		
		
		
		
		

