/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import java.util.*;
/**
 *
 * @author megankale
 */
public class WarGame {

    static int rounds = 0;
    static int wars = 0;
    static int b = 0;
    static boolean run = true;
    public interface ComparableInterface {
    }
    public class Card implements Comparable {
	public int rank; // rank
	public String suite;
	public String fullName; //title
	
        public Card(int r, String s, String n) {
		rank = r;
		suite = s;
		fullName = n;
	}
    
        @Override
        public int compareTo(Object o) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } 
    }
    public class DeckOfCards {
	Random rndm = new Random();
	
	ArrayList<Card> originalDeck = new ArrayList<Card>();
	String[] cardSuites = {"Spades", "Hearts", "Clubs", "Diamonds"}; //types
	String[] cardRanks = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"}; //names
	
	public DeckOfCards() {
		for (int q = 0; q < 4; q++) {
			for (int i = 1; i < 14; i++) {
				Card newCard = new Card(i, cardSuites[q], cardRanks[i - 1] + " of " + cardSuites[q]);
				originalDeck.add(newCard);
			}
		}
	}

	public void printOriginalDeck() {
		for (int q = 0; q < originalDeck.size(); q++) {
			System.out.println(originalDeck.get(q).fullName );
		}
	}
	

	public void shuffleOriginalDeck() {	
		for (int i = -100; i < rndm.nextInt(200); i++) {
			int x = rndm.nextInt(originalDeck.size());
			int y = rndm.nextInt(originalDeck.size());
			Collections.swap(originalDeck, x, y);
		}
	}
	
	public void addCardTomyHand(playerHand hand) {
		hand.myHand.add(originalDeck.get(0));
		originalDeck.remove(0);
	}
	
	public void removeCardFrommyHand(playerHand hand) {
		originalDeck.add(hand.myHand.get(0));
		hand.myHand.remove(0);
	}
	
    }
    public class playerHand {
	Random rndm = new Random();
	ArrayList<Card> myHand = new ArrayList<Card>();
	
	public void printMyHand() {
		for (int q = 0; q < myHand.size(); q++) {
			System.out.println(myHand.get(q).fullName);
		}
	}
	
	public void shuffleMyHand() {
		if (0 < myHand.size()) {
			for (int i = -100; i < rndm.nextInt(200); i++) {
			int x = rndm.nextInt(myHand.size());
			int y = rndm.nextInt(myHand.size());
		
			Collections.swap(myHand, x, y);
			}
		}
		else {
			System.out.println("Your hand is empty!");
		}
	}
        public void nextCard() {
		myHand.remove(0);
	}
	
    }
    public static void warGame(DeckOfCards d, playerHand p1, playerHand p2) {
                wars++;
		b = b + 2;
		if (b  > p1.myHand.size() - 1 || b > p2.myHand.size() - 1) {
			for (int i = 0; i < b - 1; i++) {
				if (p1.myHand.size() > 0) {
					d.removeCardFrommyHand(p1);
                                        System.out.println("Player two is out of cards, the game is over.");
				}
				if (p2.myHand.size() > 0) {
					d.removeCardFrommyHand(p2);
                                        System.out.println("Player one is out of cards, the game is over.");
				}
			}
		}
		else {
			if (p1.myHand.size() == 0 || p2.myHand.size() == 0) {
				run = false;
			}
			else if (p1.myHand.get(b).rank > p2.myHand.get(b).rank) {
				for (int i = 0; i < b; i++) {
					d.removeCardFrommyHand(p2);
					d.addCardTomyHand(p1);
                                        
					rounds++;
				}
                                System.out.println("Player one won the war.");

			}
			else if (p1.myHand.get(b).rank < p2.myHand.get(b).rank) {
				for (int i = 0; i < b; i++) {
					d.removeCardFrommyHand(p1);
					d.addCardTomyHand(p2);
                                        
					rounds++;
				}
                                System.out.println("Player two won the war.");
			}
			else if (p1.myHand.get(b).rank == p2.myHand.get(b).rank) {
                                System.out.println("The war continues!");
				warGame(d, p1, p2);
			}
		}
		
	}
	
	public static void main(String[] args) {
            try{
                WarGame obj = new WarGame ();
                obj.start (args);
            }
            catch (Exception e){
                e.printStackTrace ();
            }
        }

        public void start (String[] args) throws Exception{   
            DeckOfCards theDeck;
            theDeck = new DeckOfCards();

            theDeck.shuffleOriginalDeck();
            System.out.println("The deck has been shuffled");
		
            playerHand playerOne = new playerHand();
            
            playerHand playerTwo = new playerHand();
            
		
            for (int i = 0; i < 26; i++) {
		theDeck.addCardTomyHand(playerOne);
		theDeck.addCardTomyHand(playerTwo);
            }
            playerOne.shuffleMyHand();
            playerOne.printMyHand();
            System.out.println("Player one has been dealt their hand");
            playerTwo.shuffleMyHand();
            playerTwo.printMyHand();
            System.out.println("Player two has been dealt their hand");
            while (run == true) {
			if (playerOne.myHand.size() == 0 || playerTwo.myHand.size() == 0) {
				break;
			}
			else if (playerOne.myHand.get(0).rank > playerTwo.myHand.get(0).rank) {
                                System.out.println("Player one plays "+ playerOne.myHand.get(0).fullName);
                                System.out.println("Player two plays "+ playerTwo.myHand.get(0).fullName);
				theDeck.removeCardFrommyHand(playerTwo);
				theDeck.addCardTomyHand(playerOne);
                                playerOne.nextCard();
                                System.out.println("Player one wins!");
				rounds++;
			}
			else if (playerOne.myHand.get(0).rank < playerTwo.myHand.get(0).rank) {
                                System.out.println("Player one plays "+ playerOne.myHand.get(0).fullName);
                                System.out.println("Player two plays "+ playerTwo.myHand.get(0).fullName);
				theDeck.removeCardFrommyHand(playerOne);
				theDeck.addCardTomyHand(playerTwo);
                                playerTwo.nextCard();
                                System.out.println("Player two wins!");
				rounds++;
			}
			else if (playerOne.myHand.get(0).rank == playerTwo.myHand.get(0).rank) {
                                System.out.println("Player one plays "+ playerOne.myHand.get(0).fullName);
                                System.out.println("Player two plays "+ playerTwo.myHand.get(0).fullName);
                                System.out.println("It's war!");
				warGame(theDeck, playerOne, playerTwo);
				
			}
		}
		
		System.out.println(" ");
		System.out.println("Rounds: " + rounds);
		System.out.println(" ");
		System.out.println("Wars: " + wars);
		System.out.println(" ");
		if (playerOne.myHand.isEmpty()) {
			System.out.println("Player Two Won!");
		}
		else if (playerTwo.myHand.isEmpty()) {
			System.out.println("Player One Won!");
		}


	}
	


    
    

	
}