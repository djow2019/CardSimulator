package base;

import java.util.ArrayList;
import java.util.List;

/*
 * A hand contains cards that can be used in a variety of ways
 */
public class Hand {
	
	// list of all cards in the current hand
	private final List<Card> cards = new ArrayList<Card>();
	
	/** Adds a card to your hand from a deck
	 * @param d the deck to draw from
	 */
	public final void draw(final Deck d) {
		cards.add(d.draw());
	}
	
	/**
	 * Adds a card to your hand
	 * @param c the card to add
	 */
	public final void add(final Card c) {
		cards.add(c);
	}
	
	/**
	 * @return the list of the cards in this hand
	 */
	public final List<Card> getCards() {
		return cards;
	}
	
	public String toString() {
		String temp = "";
		for (Card c: cards) {
			temp += c.toString() +"\n";
		}
		return temp;
	}
	
}
