package CardGame;

import java.util.ArrayList;
import java.util.Scanner;

public class CardGame {
	
	static Deck deck = new Deck(1);
	static ArrayList<CardPlayer> players = new ArrayList();
	
	public static void main(String args[])
	{

		Scanner scan = new Scanner(System.in);
		
		int keepPlaying = 0;
		int numPlayers = 0;
		
		CardPlayer dealer = new CardPlayer(numPlayers++);
		players.add(dealer);
		CardPlayer player1 = new CardPlayer(numPlayers++);
		players.add(player1);
		
		do
		{
			
			dealHand();
			
			int answer = 0;
			
			/*
			 * Checks if player1 wants to hit or stay.
			 */
			while(!player1.busted && answer != 2)
			{
				System.out.println("The dealer is showing: " + dealer.showing());
				System.out.println("The You have: " + player1.getHand());
				
				System.out.println("Would you like to hit?");
				System.out.println("Enter 1 for yes and 2 for no:");
				answer = scan.nextInt();
				
				if(answer == 1)
				{
					player1.hit();
				}
			}
			
			/*
			 * determines the winner!
			 */
			if(dealer.handValue >= player1.handValue || player1.busted)
				System.out.println("The Dealer Won with: " + dealer.handValue);
			else
				System.out.println("You Won!! the Dealer had: " +dealer.handValue);
			
			System.out.println("You had: " +player1.handValue);
			
			/*
			 * asks the player if they want to keep playing.
			 */
			System.out.println("Woult you like to keep playing?");
			System.out.println("Enter 1 for yes and 2 for no:");
			keepPlaying = scan.nextInt();
			
		}while(keepPlaying == 1);
	}
	
	//=============================================================================
	
	/*
	 * deals a starting had to all players.
	 */
	public static void dealHand()
	{
		for(CardPlayer p : players)
		{
			p.newHand();
			p.hit();
		}
		
		for(CardPlayer p : players)
		{
			p.hit();
		}
	}

}
