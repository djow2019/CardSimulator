package base;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/*
 * A standard deck contains 52 cards: 13 ranks with 4 suits
 */
public class Deck {

	// the stack that holds the card objects in the deck
	private final Stack<Card> deck = new Stack<Card>();

	/**
	 * Adds 52 standard cards to the deck
	 */
	public final void initializeStandardDeck() {
		for (int i = 1; i <= 13; i++) {
			for (int j = 0; j < 4; j++) {
				deck.push(new Card(i, j));
			}
		}
		shuffle();
	}

	/**
	 * Shuffles all the cards in the deck
	 */
	public final void shuffle() {
		
		// memory location to temporarily store all the cards
		Card[] cardholder = new Card[deck.size()];
		
		// random number generator
		Random rand = new Random();
		
		// list of all free locations to store the cards
		List<Integer> slots = new ArrayList<Integer>();
		
		// add all possible locations
		for (int i = 0; i < deck.size(); i++) {
			slots.add(i);
		}
		
		// store all the cards on the stack in the temporary cardholder
		while (!deck.isEmpty()) {
			
			int location = rand.nextInt(slots.size());
			cardholder[slots.remove(location)] = deck.pop();
			
		}
		
		// return all the cards from the cardholder to the stack
		for (int i = 0; i < cardholder.length; i++) {
			deck.push(cardholder[i]);
		}
 	}

	/**
	 * @return The Card at the top of the deck and removes it
	 */
	public final Card draw() {
		return deck.pop();
	}

	/**
	 * @return The card at the top of the deck
	 */
	public final Card peek() {
		return deck.peek();
	}

}
