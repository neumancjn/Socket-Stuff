package CardGame;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Random;

public class Deck {
	
	private static ArrayList<Card> cards = new ArrayList();
	private static Stack<Card> deck;

	/*
	 * Initializes Deck objects for players without making and/or
	 * shuffling the deck.
	 */
	public Deck()
	{
		
	}
	
	//==================================================================================
	
	/*
	 * Creates a new Deck object and calls makeDeck and shuffle.
	 * For the server to make the deck initially.
	 */
	public Deck(int i)
	{
		makeDeck();
		shuffle();
		//print();
	}
	
	//==================================================================================
	
	/*
	 * Creates a deck of 52 Card Objects
	 * and adds them to ArrayList cards.
	 */
	private static void makeDeck()
	{
		for(int i = 0; i < 4; i++)
		{
			for(int x = 1; x <= 13; x++)
			{
				Card card = new Card(x, i);
				cards.add(card);
			}
		}
	}
	
	//===================================================================================
	
	/*
	 * Shuffles the deck by selecting a random card from ArrayList cards
	 * and then moving that card to the end of ArrayList cards so it will 
	 * not be chosen again. While pushing that card on the top of Stack deck.
	 */
	public static void shuffle()
	{
		Random ran = new Random();
		
		deck = new Stack(); // creates new empty stack.
		
		for(int i = 51;i != -1; i--)
		{
			int temp = ran.nextInt(i+1);
			deck.push(cards.get(temp));
			
			/*
			 * removes a card in a random position while 
			 * adding it to the end of ArrayList cards.
			 */
			cards.add(cards.remove(temp)); // 
		}
		
	}
	
	//================================================================================
	
	/*
	 * Removes a card from the top of deck, and returns it.
	 */
	public static Card dealCard()
	{
		return deck.pop();
	}
	
	//================================================================================
	
	/*
	 * Prints every card in the deck.
	 */
	public static void print()
	{
		while(!deck.isEmpty())
			System.out.println(deck.pop());
	}
}
