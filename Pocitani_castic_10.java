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
public class Pocitani_castic_10  {                                     //tento program slouží k pøiøazování nadmoøských výšek ke snímkùm
	private static int rozmer_x= 256;                                  
	private static int rozmer_y= 256;
	static double [] [] hlavni_pole = new double [rozmer_x][rozmer_y];
	public Pocitani_castic_10() {
		
		
		
		
	}




	public static void main(String[] args) throws IOException {
		
		
	LinkedList<Castice> Seznam_castic = new LinkedList<Castice>();
	
	
	
	JFrame obraz = new JFrame();
		obraz.setSize(1024, 1024);
		obraz.setTitle("pozice castic");
	   obraz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  obraz.setBackground(Color.black);
	  GPSAltDec myGPS = new GPSAltDec();
	  Map <Integer,Double> casy_vysky = myGPS.decodeAltitude();
		 Map <Integer,MTSnimek> casy_snimky = new TreeMap<Integer,MTSnimek>();
		
		 MTSnimek pomocny_snimek = null;
		
	  JFileChooser chooser = new JFileChooser();
	  chooser.setMultiSelectionEnabled(true);
	  chooser.showOpenDialog((Frame)null);
	  File[] files = chooser.getSelectedFiles();
	 
	  MTSnimek snimky[] = new MTSnimek [files.length];
	  Reader[] rd = new FileReader[files.length];
	  Reader[] rd_dsc = new FileReader[files.length];
	  BufferedReader[] brd = new BufferedReader[files.length];
	  BufferedReader[] brd_dsc = new BufferedReader[files.length];
	 
	  
	 
	 
	 Map <typ_castice,Integer> seznam = new HashMap<typ_castice,Integer>();
	 seznam.put(typ_castice.Gama, 0);
		seznam.put(typ_castice.Beta, 0);
		seznam.put(typ_castice.Alfa, 0);	
		seznam.put(typ_castice.Other, 0);
		seznam.put(typ_castice.Mion, 0);
	 
	 
	 
	 
	 
	 
	
		
		
	  chooser.setVisible(true);
		
		int p = 0;
		int p_dsc = 0;
		int snimek_no = 0;
		
		String radek;
		String radek_dsc = "";
		String casti[];
		String casti_dsc[];
		int cas_timepix = 0;
		try {
			
			for(int i = 0 ; i < files.length; i ++) {
				  rd [i] = new FileReader(files[i].getPath());
				  brd [i]= new BufferedReader(rd[i]); 
				  rd_dsc [i] = new FileReader(files[i].getPath() + ".dsc");
				  brd_dsc [i]= new BufferedReader(rd_dsc[i]); 
				  
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
		
		FileDialog dialog = new FileDialog((Frame)null, "Select Folder to save");
		dialog.setVisible(true);
		
		
		
			
			for(BufferedReader buff: brd) {
				
		
		try {
			
			
			
			
			radek_dsc = " ";
			while(!(radek_dsc.equals("\"Start time (string)\" (\"Acquisition start time (string)\"):") )) {
				
		radek_dsc = brd_dsc[p_dsc].readLine();	
			}
			brd_dsc[p_dsc].readLine();
	
			casti_dsc = brd_dsc[p_dsc].readLine().split(" ");	
			casti_dsc = casti_dsc[3].split(":");
		
			cas_timepix = (int) ((3600*Double.parseDouble(casti_dsc[0])) + (60*Double.parseDouble(casti_dsc[1])) + (Double.parseDouble(casti_dsc[2])));
			
			p_dsc ++;
			
			
			
			
			
			
			
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
		
			   
		pomocny_snimek = new MTSnimek(hlavni_pole);
		casy_snimky.put(cas_timepix, pomocny_snimek);  
		snimky[snimek_no] = pomocny_snimek ;	   
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
	
			for(MTSnimek a : snimky)
			{				
				
				
				seznam.put(typ_castice.Alfa,a.getPocet_alfa() + seznam.get(typ_castice.Alfa));
				seznam.put(typ_castice.Beta,a.getPocet_beta() + seznam.get(typ_castice.Beta));
				seznam.put(typ_castice.Gama,a.getPocet_gama() + seznam.get(typ_castice.Gama));
				seznam.put(typ_castice.Other,a.getPocet_other() + seznam.get(typ_castice.Other));
				seznam.put(typ_castice.Mion,a.getPocet_mion() + seznam.get(typ_castice.Mion));
				

				nakresli_ctverecek kresli = new nakresli_ctverecek(a.getObraz());
				obraz.add(kresli);
				
				
			}
			
			for(typ_castice typ : seznam.keySet()) {	
			System.out.println(typ + ":" + seznam.get(typ));
			
		}
		
		
		
		
		
		
		obraz.setVisible(true);
		JOptionPane.showMessageDialog(null, "alfa: "  + seznam.get(typ_castice.Alfa) + "\n" + "beta: "  + seznam.get(typ_castice.Beta ) +  "\n"+"gama: "  + seznam.get(typ_castice.Gama)  + "\n"+"other: "  + seznam.get(typ_castice.Other) + "\n" + "mion:" + seznam.get(typ_castice.Mion), "InfoBox: " + "cetnost castic", JOptionPane.INFORMATION_MESSAGE);
		
try (BufferedWriter bw = new BufferedWriter(new FileWriter(dialog.getDirectory()+dialog.getFile()+ ".txt")))
		
		{

	
	int min_rozdil = 50;
	int nej_cas = -1;
	for (Integer a: casy_snimky.keySet()) {
		
		
		if (!(casy_vysky.get(a) == null)) {
		bw.write(casy_vysky.get(a)+ " " + (double) (casy_snimky.get(a).getEnergie()) + " "  + (int) (casy_snimky.get(a).getPocet_alfa()) + " " +(int) (casy_snimky.get(a).getPocet_beta()) + " " + (int)(casy_snimky.get(a).getPocet_gama()) + " "  + ((int) casy_snimky.get(a).getPocet_other()) + " "  + (int) (casy_snimky.get(a).getPocet_mion()) + " "+ a); 
		}
		else {
			min_rozdil = 50;
		for (Integer b: casy_vysky.keySet()) {
			if(Math.abs((a.intValue() - b.intValue())) < min_rozdil) {
			min_rozdil = Math.abs((a - b));
			nej_cas = b;
				
			}
		}
			if(min_rozdil < 20) {
				
				bw.write(casy_vysky.get(nej_cas) + " " + (double)(casy_snimky.get(a).getEnergie()) + " "  + (int) (casy_snimky.get(a).getPocet_alfa()) + " " + (int) (casy_snimky.get(a).getPocet_beta()) + " " + (int)(casy_snimky.get(a).getPocet_gama()) + " "  + (int)(casy_snimky.get(a).getPocet_other()) +  " "  + (int) (casy_snimky.get(a).getPocet_mion()) + " " + a); 
				
			}
			else {
				bw.write(" " + "null" + " " + (double) (casy_snimky.get(a).getEnergie()) + " "  + (int) (casy_snimky.get(a).getPocet_alfa()) + " " +(int) (casy_snimky.get(a).getPocet_beta()) + " " + (int)(casy_snimky.get(a).getPocet_gama()) + " "  + (int) (casy_snimky.get(a).getPocet_other()) + " "  + (int) (casy_snimky.get(a).getPocet_mion()) +  " "+ a); 
				
				
			}
				
				
		
			
			
		}
		bw.newLine();
  	        bw.flush();
	
	
	
		
		}
}catch (Exception e)
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
		
		
		
		
		

