/*
 * Lazaro Romero
 * Final Project -- BlackJack
 * Ljr14e
 */

package Blackjack;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.ImageIcon.*;

public class Table {

	static public boolean distributeCards = false;
	private Image img;
	public int cardNumber = 0;
	public float amountBet = 10;
	
	private float amountAvailable = 100;//can't bet more than this or less than 0
	private Card userCards[] = new Card[10];//max 10 cards in play per game
	int numOfUserCards = 0;
	int userHandValue = 0;
	
	private Card dealerCards[] = new Card[10];
	int numOfDealerCards = 0;
	int dealerHandValue = 0;
	
	boolean playing = true;
	
	public Table() {
		
		loadImage();
		loadCards();
		shuffle();
		dealCard(true);
		dealCard(true);
		dealCard(false);
		dealCard(false);
		
	}//end of Table()
	
	private void loadImage() {
		
		img = ((new ImageIcon(getClass().getResource("/img/table.png"))).getImage());
		//System.out.println(getClass().getResource("/img/table.png"));
	}//end of loadImage()
	
	private void loadCards() {//creates a deck of cards with respective value and image
		
		Image image;
		Card list[] = Card.listCards;
		String path = "";
		int i;
		
		for(i = 0; i < 13; i++) {
			path = "/img/";
			path += "c" + (i+1) + ".png";
			image = (new ImageIcon(getClass().getResource(path))).getImage();
			list[i] = new Card(image);
			if(i < 10) {
				list[i].setValue(i+1);
			} else {
				list[i].setValue(10);
			}
		}
		
		for(int j = 0; i < 26; j++, i++) {
			path = "/img/";
			path += "d" + (j+1) + ".png";
			//System.out.println(getClass().getResource(path));
			image = (new ImageIcon(getClass().getResource(path))).getImage();
			list[i] = new Card(image);
			if(j < 10) {
				list[i].setValue(j+1);
			} else {
				list[i].setValue(10);
			}
		}
		
		for(int k = 0; i < 39; k++, i++) {
			path = "/img/";
			path += "e" + (k+1) + ".png";
			image = (new ImageIcon(getClass().getResource(path))).getImage();
			list[i] = new Card(image);
			if(k < 10) {
				list[i].setValue(k+1);
			} else {
				list[i].setValue(10);
			}
		}
		
		for(int l = 0; i < 52; l++, i++) {
			path = "/img/";
			path += "t" + (l+1) + ".png";
			image = (new ImageIcon(getClass().getResource(path))).getImage();
			list[i] = new Card(image);
			if(l < 10) {
				list[i].setValue(l+1);
			} else {
				list[i].setValue(10);
			}
		}
		
	}//end of loadCards()
	
	private void shuffle() {//randomly place cards within deck
		
		Card card;
		Random r = new Random();
		int opt = 0;
		
		for(int i = 0; i < Card.listCards.length; i++) {
			opt = (int)(r.nextDouble() * (Card.listCards.length));
			card = Card.listCards[opt];
			Card.listCards[opt] = Card.listCards[i];
			Card.listCards[i] = card;
		}
		
	}//end of shuffle()
	
	private void dealCard(boolean play) {
		
		Card card = null;
		if(play) {
			userCards[numOfUserCards++] = Card.listCards[cardNumber++];//deal card accordingly
				//System.out.println(userCards[numOfUserCards - 1].getValue());
			userHandValue += userCards[numOfUserCards - 1].getValue();//add value of cards dealt
				//System.out.println("Error1");
			card = userCards[numOfUserCards - 1];//current card is set to latest card dealt
				//System.out.println("Error2");
			card.setPendingCoor(450, 350);
		} else {
			dealerCards[numOfDealerCards++] = Card.listCards[cardNumber++];
			dealerHandValue += dealerCards[numOfDealerCards - 1].getValue();
			card = dealerCards[numOfDealerCards - 1];
			card.setPendingCoor(450, 50);
		}
		
		card.moves = true;
		
	}//end of dealCard(t/f)
	
	public void draw(Graphics g) {
		
		String txt = "Bet: ";
		txt += amountBet;
		txt += " -> Available: " + amountAvailable;
		g.drawImage(img, 0, 0, null);
		g.drawString(txt, 10, 565);
		showPendingCards(g);
		showCardMessage(g);
	
	}//end of draw(g)
	
	public void showPendingCards(Graphics g){//display deck, show depth for 3D-ish
		
		for(int i = 0; i < cardNumber; i++) {
			if(Card.listCards[i].moves && i != Card.cardGraphics) {
				Card.listCards[i].draw(g, 850-(i/7), 10+i*2, false);
			}
		}
		
		for(int i = Card.listCards.length - 1; i > cardNumber; i--) {
			Card.listCards[i].draw(g, 850-(i/7), 10+i*2, false);
		}
		
	}//end of showPendingCards(g)
	
	public void showCardMessage(Graphics g) {
		
		for(int i = 0;i < numOfUserCards; i++) {
			if(compare(userCards[i]) && userCards[i].moves) {
				userCards[i].moveCards(g);
			} else {
				userCards[i].draw(g, 450+i*20, 350, true);
			}
		}
		for(int i = 0; i < numOfDealerCards; i++) {
			if(i == 0 && playing) {
				if(compare(dealerCards[i]) && dealerCards[i].moves) {
					dealerCards[i].moveCards(g);
				} else {
					dealerCards[i].draw(g, 450+i*20, 50, false);
				}
			} else {
				if(compare(dealerCards[i]) && dealerCards[i].moves) {
					dealerCards[i].moveCards(g);
				} else {
					dealerCards[i].draw(g, 450+i*20, 50, true);
				}
			}
			
		}
		
	}//end of showCardMessage(g)
	
	public boolean compare(Card c) {
		
		int i;
		for(i = 0; i < Card.listCards.length; i++) {
			if(Card.listCards[i].equals(c)) {
				break;
			}
		}
		if( i == Card.cardGraphics) {
			return true;
		} else {
			return false;
		}
		
	}//end of compare(c)
	
	public void buttonActions(int x, int y) {
		
		if((x > 0 && x < 200) && (y > 100 && y < 170)) {//buttons are found by coordinates
			checkForWinner();
			userHandValue = 0;
			dealerHandValue = 0;
			numOfDealerCards = 0;
			numOfUserCards = 0;
			dealCard(false);
			dealCard(false);
			dealCard(true);
			dealCard(true);
			playing = true;
		}
		
		if((x > 0 && x < 200) && (y > 200 && y < 270)) {
			dealCard(true);
			if(userHandValue > 21) {
				playing = false;
				amountAvailable -= amountBet;
				if(amountAvailable <= 0) {
					JOptionPane.showMessageDialog(null, "GAME OVER! You're all out of money! \nGame will reset");
					amountAvailable = 100;
					amountBet = 10;
				} else {
					JOptionPane.showMessageDialog(null, "Total is " + userHandValue + "	!BUST!");
				}
				playing = true;
				userHandValue = 0;
				dealerHandValue = 0;
				numOfUserCards = 0;
				numOfDealerCards = 0;
				dealCard(false);
				dealCard(false);
				dealCard(true);
				dealCard(true);
				
			}
		}
		
		if((x > 0 && x < 200) && (y > 300 && y< 370)) {
			amountBet = Float.parseFloat(JOptionPane.showInputDialog("Enter bet amount: "));
		}

	}//end of buttonActions(x,y)
	
	private void checkForWinner() {
		
		boolean flag = true;
		while(flag) {
			if(dealerHandValue > 21) {//if dealer has 21 or more, user wins
				playing = false;
				JOptionPane.showMessageDialog(null, "You Win!");
				playing = true;
				amountAvailable += amountBet;
				flag = false;
				break;
			} else {
				if(dealerHandValue > userHandValue) {
					playing = false;
					JOptionPane.showMessageDialog(null, "You Lose!");
					playing = true;
					amountAvailable -= amountBet;
					flag = false;
					break;
				}
			}
			dealCard(false);
		}
		
	}//end of checkForWinner()
	
}//end of Table class
