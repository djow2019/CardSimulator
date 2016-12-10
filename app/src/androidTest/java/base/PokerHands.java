package base;
import java.util.List;

/*
 * This utility class checks for traditional poker hands
 * Each method assumes each hand is made from a standard deck
 */
public class PokerHands {
	
	/** A pair has two cards with the same rank
	 * @param h Hand to check
	 * @return true if there is a pair in the hand
	 */
	public static final boolean hasPair(Hand h) {
		
		List<Card> cards = h.getCards();
		
		int[] hash = new int[13];
		
		// checks for duplicate cards of the same rank
		for (Card c: cards) {
			
			int spot = c.getRank() - 1;
			
			if (hash[spot] == 1) {
				return true;
			} else {
				hash[spot] = 1;
			}
			
		}
		
		return false;
		
	}
	
	/** A three of a kind has three cards with the same rank
	 * @param h Hand to check
	 * @return true if there is a triple in the hand
	 */
	public static final boolean hasThreeOfAKind(Hand h) {
		
		List<Card> cards = h.getCards();
		
		int[] hash = new int[13];
		
		// checks for duplicate cards of the same rank
		for (Card c: cards) {
			
			int spot = c.getRank() - 1;
			
			if (hash[spot] == 2) {
				return true;
			} else {
				hash[spot]++;
			}
			
		}
		
		return false;
		
	}
	
	/** A four of a kind has four cards with the same rank
	 * @param h Hand to check
	 * @return true if there is quadruple in the hand
	 */
	public static final boolean hasFourOfAKind(Hand h) {
		
		List<Card> cards = h.getCards();
		
		int[] hash = new int[13];
		
		// checks for duplicate cards of the same rank
		for (Card c: cards) {
			
			int spot = c.getRank() - 1;
			
			if (hash[spot] == 3) {
				return true;
			} else {
				hash[spot]++;
			}
			
		}
		
		return false;
		
	}
	
	/**
	 * A straight has five cards of the same rank in a row
	 * @param h the hand to check
	 * @return true if there is a straight
	 */
	public static final boolean hasStraight(Hand h) {
		
		List<Card> cards = h.getCards();
		
		// insertion sort
		Card[] temp = new Card[cards.size()];
		
		temp[0] = cards.get(0);
		
		// sorts first i cards and adds
		// the next one where it belongs
		for (int i = 1; i < cards.size(); i++) {
			
			// temporary card on stack used for swapping
			Card t = cards.get(i);
			
			for (int j = 0; j <= i - 1; j++) {
				
				// swap the cards if one is less than the other
				if (t.getRank() < temp[j].getRank()) {
					Card k = temp[j];
					temp[j] = t;
					t = k;
				} 
			}
			
			temp[i] = t;
			
		}
		
		// check for 5 in a row!
		int streak = 1;
		for (int i = 1; i < temp.length; i++) {
			
			if (temp[i].getRank() == temp[i - 1].getRank() + 1) {
				streak++;
			} else {
				streak = 0;
			}
			
			if (streak == 5) {
				return true;
			}
		}
		
		return false;
		
	}
	
	/**
	 * A Flush has 5 cards with the same suit
	 * @param h hand to check
	 * @return true if there is a flush
	 */
	public static final boolean hasFlush(Hand h) {
		
		List<Card> cards = h.getCards();
		
		int[] suits = new int[4];
		
		for (Card c: cards) {
			suits[c.getSuit()]++;
		}
		
		for (int i = 0; i < suits.length; i++) {
			if (suits[i] == 5) {
				return true;
			}
		}
		
		return false;
		
	}
	
	/**
	 * A full house is 3 of a kind and a pair
	 * @param h Hand to check
	 * @return true if there is a full house
	 */
	public static final boolean hasFullHouse(Hand h) {
		
		List<Card> cards = h.getCards();
		
		int[] hash = new int[13];
		
		// checks for duplicate cards of the same rank
		for (Card c: cards) {
			hash[c.getRank() - 1]++;
		}
		
		boolean hasTriple = false, hasPair = false;
		
		for (int i = 0; i < hash.length; i++) {
			
			if (hash[i] == 2) {
				hasPair = true;
			} else if (hash[i] == 3) {
				hasTriple = true;
			}
			
		}
		
		return hasTriple && hasPair;
		
	}
	
	/**
	 * A straight flush is a straight and a flush
	 * @param h hand to check
	 * @return true if there is a straight flush
	 */
	public static final boolean hasStraightFlush(Hand h) {
		
		// Temporary; if there are more than 5 cards,
		// you can have a straight and flush that are not
		// the same5 cards
		return hasStraight(h) && hasFlush(h);
	}
	
	/**
	 * @param h the hand to check
	 * @return highest poker hand in your hand
	 */
	public static final String whatDoIHave(Hand h) {
		
		if (hasStraightFlush(h)) {
			return "Straight Flush";
		} else if(hasFourOfAKind(h)) {
			return "Four of a kind";
		} else if (hasFullHouse(h)) {
			return "Full House";
		} else if (hasFlush(h)) {
			return "Flush";
		} else if (hasStraight(h)) {
			return "Straight";
		} else if (hasThreeOfAKind(h)) {
			return "Three of a kind";
		} else if (hasPair(h)) {
			return "Pair";
		} else {
			return "Nothing :(";
		}
		
		
	}

}
