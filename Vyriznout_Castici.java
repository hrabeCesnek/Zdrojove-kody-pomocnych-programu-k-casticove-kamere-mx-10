package Orezavatko;

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
import java.nio.file.Files;
import java.util.Map;
import java.util.TreeMap;
import java.awt.Rectangle;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import txt_do_poctu.Castice;
import txt_do_poctu.nakresli_ctverecek;
import txt_do_poctu.typ_castice;
import java.util.HashMap;
import java.util.LinkedList;
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
import java.util.HashMap;
import java.util.LinkedList;







public class Vyriznout_Castici  {   //tohle je hlavní øídící tøída pro vyøezávání èástic ze samostatných souborù, úzce spolupracuje s tøídou OrezSnimek
	private static int rozmer_x= 256;
	private static int rozmer_y= 256;
	static double [] [] hlavni_pole = new double [rozmer_x][rozmer_y];
	public Vyriznout_Castici() {
		
		
		
		
	}




	public static void main(String[] args) throws IOException {
		
    //inicializace je velice podobná pøedchozím programùm
	LinkedList<Castice> Seznam_castic = new LinkedList<Castice>();//seznam èástic
	
	
	
	JFrame obraz = new JFrame();                            //vykreslování
		obraz.setSize(1024, 1024);
		obraz.setTitle("pozice castic");
	   obraz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  obraz.setBackground(Color.black);
	 
		 OrezSnimek pomocny_snimek = null;
		
		
	  JFileChooser chooser = new JFileChooser();              
	  chooser.setMultiSelectionEnabled(true);
	  chooser.showOpenDialog((Frame)null);
	  File[] files = chooser.getSelectedFiles();
	  OrezSnimek snimky[] = new OrezSnimek [files.length];
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
	 
	     // otevíraní dialogových oken, aby uživatel zadal parametry
		JFrame min_velikost = new JFrame("velikost");
		  int m_velikost = Integer.parseInt((JOptionPane.showInputDialog(min_velikost,"Zadejte minimální poèet obklopených pixelù")));
		  JFrame min_energie = new JFrame("energie");
		  double mini_energie = Integer.parseInt((JOptionPane.showInputDialog(min_energie,"Zadejte minimální hodnotu energie")));
		  JFrame min_uhlopricka = new JFrame("uhlopricka");
		  double uhl = Integer.parseInt((JOptionPane.showInputDialog(min_uhlopricka,"Zadejte minimální delku uhlopricky castice")));
	 
	
		
		// hromadná inicializace tøíd ke ètení
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
		
		
		
			
			for(BufferedReader buff: brd) {       //postupné ètení každého souboru
				
		
		try {
			
			
			
			
	
			
			
			
			
			
			
			//ctení txt souboru
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
		
			   
		pomocny_snimek = new OrezSnimek(hlavni_pole,dialog.getDirectory() + files[snimek_no].getName(),m_velikost,mini_energie,uhl);//zavolání a vytvoøení objektu OrezSnimek, vypisování probíhá v nìm 
		
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
	//vykreslování a vypisování
			for(OrezSnimek a : snimky)
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
		

		
		for(Reader a: rd )
		{
			a.close();
			
			
		}
		for(Reader a: brd )
		{
			a.close();
			
			
		}	
	
		
		
}}
		
		
		
		
		

