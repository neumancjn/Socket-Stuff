package CardGame;

import java.util.ArrayList;

public class CardPlayer {
	
	private Deck deck = new Deck();
	private ArrayList<Card> hand;
	private Card c;
	
	public boolean busted;
	public int handValue;
	public int playerNumber;
	
	public CardPlayer(int pNum)
	{
		playerNumber = pNum;
	}
	
	//============================================================================
	
	/*
	 * Sets busted to false, handValue to 0, and ArrayList hand 
	 * to an empty ArrayList.
	 */
	public void newHand()
	{
		busted = false;
		handValue = 0;
		hand = new ArrayList();
	}
	
	//===============================================================================
	
	/*
	 * Deals one card to ArrayList hand, then determines the players
	 * handValue, and makes sure the player has not busted. Returns
	 * the card dealt.
	 */
	public String hit()
	{
		hand.add(deck.dealCard());
		handValue = addHand();
		return hand.get(hand.size()-1).toString();
	}
	
	//===================================================================================
	
	/*
	 * adds a card to the players using the String given.
	 */
	public void addCard(String s)
	{
		hand.add(c = new Card(s.substring(0, s.indexOf(" ")), s.substring(s.indexOf(" ")+1)));
	}
	
	//====================================================================================
	
	/*
	 * Determines the value of this players hand. This function also determines
	 * if aces in this players hand (if any) should be worth 1 or 11. It Also
	 * determines if the player has busted.
	 */
	public int addHand()
	{
		int numAce = 0; // keeps track of the number of aces in this hand.
		int hValue = 0; // keeps track of the hands value
		
		for(Card c : hand)
		{
			int cardValue = c.getNum();
			
			if(cardValue == 1) // Checks for aces.
			{
				hValue += cardValue;
				numAce++;
			} else if(cardValue < 11) // Checks for numbered cards.
			{
				hValue +=cardValue;
			} else // Sets all face cards value to ten.
			{
				hValue += 10;
			}
		}
		
		while(numAce != 0 && hValue < 12) // Sets aces in hand equal to 11 if beneficial.
		{
			hValue += 10;
			numAce--;
		}
		
		if(hValue > 21)
			busted = true;
		
		return hValue;
	}
	
	//=================================================================================================
	
	/*
	 * Returns all cards in hand except the first one dealt.
	 */
	public String showing()
	{
		String handShown = "";
		
		for(int i = hand.size()-1; i > 1; i--)
		{
			handShown += (hand.get(i)).toString() + ", ";
		}
		
		handShown += (hand.get(1)).toString();
		
		return handShown;
	}
	
	//==================================================================================================
	
	/*
	 * Returns all cards in hand.
	 */
	public String getHand()
	{
		String cardsInHand = "";
		
		for(int i = hand.size()-1; i > 0; i--)
		{
			cardsInHand += (hand.get(i)).toString() + ", ";
		}
		
		cardsInHand += (hand.get(0)).toString();
		
		return cardsInHand;
	}

}
