package base;

/*
 * Converts a hand to a binary string
 */
public class Encoding {

	/**
	 * Represents a hand as binary string
	 * @param h Hand to convert
	 * @return the binary representation of a hand
	 */
	public static final String toBinaryString(Hand h) {
		
		String encoding = "";
		
		// each card is 6 bits
		for (Card c: h.getCards()) {
			
			// rank is first 4 bits
			encoding += String.format("%4s", Integer.toBinaryString(c.getRank())).replace(' ', '0');
			
			// suit is last 2 bits
			encoding += String.format("%2s", Integer.toBinaryString(c.getSuit())).replace(' ', '0');
			
		}
		
		return encoding;
		
	}
	
	/**
	 * Turns a binary string into a hand
	 * @param encoding the encoding to convert
	 * @return the Hand that the encoding represents
	 */
	public static final Hand fromBinaryString(String encoding) {
		
		// number of cards in the string
		int numCards = encoding.length() / 6;
		
		if (encoding.length() % 6 != 0) {
			System.err.println("String corrupted, cannot read");
			return null;
		}
		
		// the decoded hand
		Hand h = new Hand();
		
		for (int i = 0; i < numCards; i++) {
			
			int rank = Integer.parseInt(encoding.substring(i * 6, i * 6 + 4), 2);
			int suit = Integer.parseInt(encoding.substring(i * 6 + 4, i * 6 + 6), 2);
			
			h.add(new Card(rank, suit));
			
		}
		
		return h;
		
	}

}
