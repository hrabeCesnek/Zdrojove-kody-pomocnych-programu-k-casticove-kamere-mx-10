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



public class Pocitani_castic_9  {            //tento program slou�� k p�i�azov�n� nadmo�sk�ch v��ek ke sn�mk�m
	private static int rozmer_x= 256;        //rozmery sn�mku
	private static int rozmer_y= 256;
	static double [] [] hlavni_pole = new double [rozmer_x][rozmer_y]; //hlavn� pole s energiemi
	public Pocitani_castic_9() {
		
		
		
		
	}




	public static void main(String[] args) throws IOException {
		
	
	LinkedList<Castice> Seznam_castic = new LinkedList<Castice>();//seznam nalezen�ch ��stic
	
	
	
	JFrame obraz = new JFrame();
		obraz.setSize(1024, 1024);
		obraz.setTitle("pozice castic");
	   obraz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  obraz.setBackground(Color.black);                     //vykreslov�n�	
	  GPSAltDec myGPS = new GPSAltDec();                   
	  Map <Integer,Double> casy_vysky = myGPS.decodeAltitude();     // incializace na�� vlastn� t��dy ke �ten� GPS logu, v�ce informac� v souboru t�to t��dy
		 Map <Integer,Snimek> casy_snimky = new TreeMap<Integer,Snimek>();  //mapa casy-sn�mky
		 int pomocny_cas;
		 Snimek pomocny_snimek = null;
		
		
		 LinkedList <Rectangle> ctverecky = new LinkedList <Rectangle>(); //t��da k vykreslov�n�
	  JFileChooser chooser = new JFileChooser();                          
	  chooser.setMultiSelectionEnabled(true);
	  chooser.showOpenDialog((Frame)null);
	  File[] files = chooser.getSelectedFiles();           
	  Snimek snimky[] = new Snimek [files.length];
	  Reader[] rd = new FileReader[files.length];
	  Reader[] rd_dsc = new FileReader[files.length];
	  BufferedReader[] brd = new BufferedReader[files.length];
	  BufferedReader[] brd_dsc = new BufferedReader[files.length];    //na�ten� a inicializace t��d ke �ten� txt sn�mk� a jejich dsc soubor�
	 
        
	
	 double energie_snimku = 0;
	  
	  
	 
	 
	 Map <typ_castice,Integer> seznam = new HashMap<typ_castice,Integer>(); //druh� seznam ��stic
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
		try {                                                             //d�le n�sleduje hromadn� inicializace t��d ke �ten� soubor� (txt i dsc)
			
			for(int i = 0 ; i < files.length; i ++) {
				  rd [i] = new FileReader(files[i].getPath());
				  brd [i]= new BufferedReader(rd[i]); 
				  rd_dsc [i] = new FileReader(files[i].getPath() + ".dsc");
				  brd_dsc [i]= new BufferedReader(rd_dsc[i]); 
				  
			  }
			 chooser.setVisible(false);
			
		}
		
		catch (IOException e) {                                 //odhycen� chyby
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
		
		FileDialog dialog = new FileDialog((Frame)null, "Select Folder to save"); //v�b�r slo�ky pro ulo�en� v�pisu
		dialog.setVisible(true);
		
		
		
			
			for(BufferedReader buff: brd) { //�ten� sn�mk� a jejich dsc soubor�
				
		
		try {
			
			
			
			//�ten� �asu
			radek_dsc = " ";
			while(!(radek_dsc.equals("\"Start time (string)\" (\"Acquisition start time (string)\"):") )) { //�as sn�mku se v dsc souboru nach�z� 2 ��dky po ��dku s t�mto �et�zcem
				
		radek_dsc = brd_dsc[p_dsc].readLine();	
			}
			brd_dsc[p_dsc].readLine();
			
			casti_dsc = brd_dsc[p_dsc].readLine().split(" ");	
			casti_dsc = casti_dsc[3].split(":");        //postupn� rozd�len� a vy�ten� �asu
	
			cas_timepix = (int) ((3600*Double.parseDouble(casti_dsc[0])) + (60*Double.parseDouble(casti_dsc[1])) + (Double.parseDouble(casti_dsc[2])));//p�eveden� �asu na sekundy
			
			p_dsc ++;
			
			
			
			
			
			
			
		do {
			
			
			
			radek = buff.readLine(); // na�ten� sn�mku
			if(radek!= null) {
				casti = radek.split(" ");
			for(int i = 0; i < casti.length; i ++) {
			hlavni_pole [i][p] = Double.parseDouble(casti[i]);
			
			
			
			
				
				
				
			}
				
			
				p++;
				
				
				
				
			}
			
			
			
		}while (radek!= null);
		
			   
		pomocny_snimek = new Snimek(hlavni_pole); //um�st�n� na�ten�ch hodnot do t��dy sn�mek, kde je ji� jejich anal�za provedena
		casy_snimky.put(cas_timepix, pomocny_snimek);  
		snimky[snimek_no] = pomocny_snimek ;	   
		snimek_no++;
			   
			   
			   
			   
		
		
		
		
		
		
		}catch (IOException e) { //odchycen� chyby
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

			for(Snimek a : snimky) //vykreslen� analyzovan�ch sn�mk�
			{				
				
				
				seznam.put(typ_castice.Alfa,a.getPocet_alfa() + seznam.get(typ_castice.Alfa));
				seznam.put(typ_castice.Beta,a.getPocet_beta() + seznam.get(typ_castice.Beta));
				seznam.put(typ_castice.Gama,a.getPocet_gama() + seznam.get(typ_castice.Gama));
				seznam.put(typ_castice.Other,a.getPocet_other() + seznam.get(typ_castice.Other));
				
				

				nakresli_ctverecek kresli = new nakresli_ctverecek(a.getObraz());
				obraz.add(kresli);
				
				
			}
			
			for(typ_castice typ : seznam.keySet()) {	//vyps�n� ��stic dle typ�
			System.out.println(typ + ":" + seznam.get(typ));
			
		}
		
		
		
		
		
		
		obraz.setVisible(true);
		JOptionPane.showMessageDialog(null, "alfa: "  + seznam.get(typ_castice.Alfa) + "\n" + "beta: "  + seznam.get(typ_castice.Beta ) +  "\n"+"gama: "  + seznam.get(typ_castice.Gama)  + "\n"+"other: "  + seznam.get(typ_castice.Other), "InfoBox: " + "cetnost castic", JOptionPane.INFORMATION_MESSAGE); //zobrazen� �etnosti
		
try (BufferedWriter bw = new BufferedWriter(new FileWriter(dialog.getDirectory()+dialog.getFile()+ ".txt"))) //p�i�azov�n� �as� a z�pis do souboru
		
		{

	
	int min_rozdil = 50;
	int nej_cas = -1;
	for (Integer a: casy_snimky.keySet()) { //cyklus, kter� projde v�echny �asy v map� �asy-sn�mky
		
		
		if (!(casy_vysky.get(a) == null)) { //pokus o nalezen� �asu z mapy �asy-sn�mky v map� �asy v��ky, pokud vyhovuje dojde k zaps�n� ve form�tu v��ka + energie + �etnosti 
		bw.write(casy_vysky.get(a)+ " " + (double) (casy_snimky.get(a).getEnergie()) + " "  + (int) (casy_snimky.get(a).getPocet_alfa()) + " " +(int) (casy_snimky.get(a).getPocet_beta()) + " " + (int)(casy_snimky.get(a).getPocet_gama()) + " "  + ((int) casy_snimky.get(a).getPocet_other()) +  " "+ a); 
		}
		else {// opakovan� pokus o nalezen�, nyn� v�ak ji� s malou toleranc� (max - a� 20s)
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
			else { //nenalezen� �asu
				bw.write(" " + "null" + " " + (double) (casy_snimky.get(a).getEnergie()) + " "  + (int) (casy_snimky.get(a).getPocet_alfa()) + " " +(int) (casy_snimky.get(a).getPocet_beta()) + " " + (int)(casy_snimky.get(a).getPocet_gama()) + " "  + (int) (casy_snimky.get(a).getPocet_other()) +  " "+ a); 
				
				
			}
				
				
		
			
			
		}
		bw.newLine();
  	        bw.flush();
	
	
	
		
		}
}catch (Exception e)    //odchycen� chyby
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
		
		
		
		
		

