/*
 * Lazaro Romero
 * Final Project -- BlackJack
 * Ljr14e
 */
package Blackjack;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.ImageIcon.*;

public class BlackjackMain extends Applet implements Runnable, MouseListener {

	Table table;//where all the magic will be happening
	Thread animThread;//allow multiple things at once
	Graphics tempImage;
	Image img;
	int width;
	int height;
	
	public void init() {//how applet will be initialized
		table = new Table();
		this.setSize(980, 580);
		setCursor(new Cursor(12));
		
		width = getBounds().width;
		height = getBounds().height;
		
		img = createImage(1024, 1024);
		tempImage = img.getGraphics();
		
		this.addMouseListener(this);
		
	}//end of init()
	
	public void update(Graphics g) {//images for updating data
		
		tempImage.setColor(getBackground());
		table.draw(tempImage);
		
		tempImage.drawImage((new ImageIcon(getClass().getResource("/img/stay.png"))).getImage(),0,100,this);
		tempImage.drawImage((new ImageIcon(getClass().getResource("/img/hit.png"))).getImage(),0,200,this);
		tempImage.drawImage((new ImageIcon(getClass().getResource("/img/change.png"))).getImage(),0,300,this);
		
		g.drawImage(img, 0, 0, null);
		
	}//end of update(g)
	
	public void run() {
		
		while(true) {
			
			try {
			
				repaint();
				Thread.sleep(5);

			} catch(InterruptedException e) {
				
				break;
				
			}
			
		}
		
		
	}//end of run()
	
	public void start() {
		if(animThread == null) {
			animThread = new Thread(this);
			animThread.start();			
		}

	}//end of start()
	
	@SuppressWarnings("deprecation")
	public void stop() {
		if(animThread != null) {
			animThread.stop();
			animThread = null;
		}
		
	}//end of stop()
/*
 * Basic mouse interaction functions needed to compile
 */
	public void mouseClicked(MouseEvent e) {
		width = width;
	}
	public void mousePressed(MouseEvent e) {
		table.buttonActions(e.getX(), e.getY());
	}
	public void mouseReleased(MouseEvent e) {
		width = width;
	}
	public void mouseEntered(MouseEvent e) {
		width = width;
	}
	public void mouseExited(MouseEvent e) {
		width = width;
	}
	
}
