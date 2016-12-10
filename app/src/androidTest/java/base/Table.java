package base;
import java.util.ArrayList;
import java.util.List;

/*
 * Determines how many players and what types of cards are in play
 */
public class Table {

	// the deck of cards in play
	private Deck deck;

	// the number of hands in play
	private List<Hand> players;

	public Table(Deck deck, int players) {

		this.deck = deck;
		this.players = new ArrayList<Hand>();
		for (int i = 0; i < players; i++) {
			this.players.add(new Hand());
		}

	}
	
	/**
	 * @param numPlayer the index of the player at the table
	 * @return the hand of that player
	 */
	public Hand getPlayerHand(int numPlayer) {
		return players.get(numPlayer);
	}

	/**
	 * Deals num cards to each player
	 * 
	 * @param num
	 */
	public void dealCards(int num) {
		for (int i = 0; i < num; i++) {
			for (Hand h : players) {
				h.draw(deck);
			}
		}
	}

}
