package CardGame;

import Socket.SocketServer;
import Socket.SocketClient;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;


public class SocketCardGame {
	
	static SocketServer s;
	static SocketClient c;
	static boolean isHost;
	static Deck deck = new Deck(1);
	static ArrayList<CardPlayer> players = new ArrayList();
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String args[]) throws IOException
	{

		determineHost();
		
		if(isHost)
		{
			host();
		}else
		{
			client();
		}
		
		
	}
	
	//=============================================================================
	
	public static void host() throws IOException
	{
		int keepPlaying = 0;
		int numPlayers = 0;
		
		CardPlayer dealer = new CardPlayer(numPlayers++);
		players.add(dealer);
		CardPlayer player1 = new CardPlayer(numPlayers++);
		players.add(player1);
		
		if(s.listen().toLowerCase().equals("yes"))
		{
			CardPlayer player2 = new CardPlayer(numPlayers++);
			players.add(player2);
		}
		
		
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
		
		s.close();
	}
	
	//=============================================================================
	
	public static void client() throws IOException
	{
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
		
		c.close();
	}
	
	//=============================================================================
	
	/*
	 * Sets the player as the Server or Client.
	 */
	public static void determineHost() throws IOException
	{
		int option = 0;
	       
	       do
	       {
	           
	            System.out.println("Enter 1 to host or 2 to join a server.");
	            option = scan.nextInt();
	        
	       } while(option != 1 && option != 2);
	       
	       if(option == 1) 
	       {
	           s = new SocketServer();
	           isHost = true;
	        }
	        else
	        {
	            c = new SocketClient();
	            isHost = false;
	        }
	}
	
	//=============================================================================
	
	/*
	 * deals a starting had to all players.
	 */
	public static void dealHand() throws IOException
	{
		for(CardPlayer p : players)
		{
			p.newHand();
		}
		
		for(CardPlayer p : players)
		{
			s.send(p.hit());
		}
	}

}