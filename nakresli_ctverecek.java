package txt_do_poctu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.JComponent;

public class nakresli_ctverecek extends JComponent { // pomocná tøída k vykreslování snímkù

protected LinkedList<Rectangle> ctverce;
public nakresli_ctverecek (LinkedList<Rectangle> list) {
	
	ctverce = list;
	
	
}
public void paintComponent(Graphics g ) {
	Graphics2D g2 = (Graphics2D) g;
	g2.setColor(Color.RED);
	for(Rectangle a: ctverce) {
	g2.draw(a);
	g2.fill(a);
	}
}



}
