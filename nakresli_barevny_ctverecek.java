package txt_do_poctu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

public class nakresli_barevny_ctverecek extends nakresli_ctverecek { //pomocná tøída k vykreslování barevných snímkù
	private LinkedList<Color> barve;
	public nakresli_barevny_ctverecek(LinkedList<Rectangle> list, LinkedList<Color> barve) {
		super(list);
		this.barve = barve;
	}
	public void paintComponent(Graphics g ) {
		Graphics2D g2 = (Graphics2D) g;
		
		for(Rectangle a: ctverce) {
			g2.setColor(barve.getFirst());	
			barve.removeFirst();
		g2.draw(a);
		g2.fill(a);
		}
	}
}
