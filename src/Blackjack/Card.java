/*
 * Lazaro Romero
 * Final Project -- BlackJack
 * Ljr14e
 */
package Blackjack;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon.*;

public class Card {

	public static int cardGraphics = 0;
	public boolean moves = false;
	static Card listCards[] = new Card[52];//deck
	private Image frontImage;
	private Image backImage;
	private int value = 0;
	private float xSpacing;//difference in x coordinates
	private float xMax;
	private float pendingCoor;
	public int state = 0;
	
	public Card() {
		
		loadBack();//when deck of card is created only back image will be shown
		
	}//end of Card()
	
	public Card(Image frontImage) {//set card by displaying image, back is same
		
		this.frontImage = frontImage;//when card is dealt, then card is created with front image/value
		loadBack();
		
	}//end of Card(image)
	
	public void draw(Graphics g, int x, int y, boolean flag) {
		
		if(!moves) {//not in move
			if(flag) {
				g.drawImage(frontImage, x, y, null);
			} else {//if flag is false display back
				g.drawImage(backImage, x, y, null);
			}
			
		}
		
	}//end of draw(g,x,y,f)
	
	private void loadBack() {//set the card's background image
		
		backImage = (new ImageIcon(getClass().getResource("/img/card.png"))).getImage();
		
	}//end of loadBack()
	
	public Image getFrontImage() {
		
		return frontImage;
		
	}//end of getFrontImage()
	
	public void setFrontImage(Image img) {
		
		this.frontImage = img;
		
	}//end of setFrontImage(image)
	
	public int getValue() {
		
		return value;
		
	}//end of getValue()
	
	public void setValue(int val) {
		
		this.value = val;
		
	}//end of setValue(v)
	
	public void moveCards(Graphics g) {//will display drawn cards next to each other
		
		xSpacing -= 25;//difference between cards displayed
		float y = ((float)xSpacing - 850) * pendingCoor + 10;
		g.drawImage(frontImage, (int)xSpacing, (int)y, null);
		
		if(xSpacing > xMax) {
			moves = true;
		} else {
			moves = false;
			cardGraphics++;	
		}
	}//end of moveCards(g)
	
	public void setPendingCoor(int x, int y) {//cards that are pendingCoor how they will be shown
		
		pendingCoor = (float)(y-10)/(float)(x-850);
		xSpacing = 850;
		xMax = x;

	}//end of setpendingCoor(x,y)
	
	
}//end of Card class
