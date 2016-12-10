package base;
/*
 * Contains information about each Card object
 * Rank of Ace is 1
 * Rank of King is 13
 */
public class Card {
	
	// value of each suit
	public static final int CLUBS = 0, DIAMONDS = 1, HEARTS = 2, SPADES = 3;
	
	// rank and suit of each card
	private int rank, suit;
	
	/**
	 *  Creates a Card
	 * @param rank the number of the card
	 * @param suit either CLUBS, DIAMONDS, HEARTS, SPADES
	 */
	public Card(int rank, int suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	/**
	 * @return rank of the card
	 */
	public final int getRank() {
		return rank;
	}
	
	/**
	 * @return suit of the card
	 */
	public final int getSuit() {
		return suit;
	}
	
	public String toString() {
		return "Rank: " + rank + " Suit: " + suit;
	}
	
}
