package CardGame;

public class Card {
	
	private int number;
	private String suit;

	Card(int num, int s)
	{
		number = num;
		suit = setSuit(s);
	}
	
	Card(String num, String s)
	{
		number = Integer.parseInt(num);
		suit = s;
	}
	
	private String setSuit(int s)
	{
		if(s == 0)
			return "spades";
		else if(s == 1)
			return "hearts";
		else if(s == 2)
			return "diamonds";
		else
			return "clubs";
	}
	
	public int getNum()
	{
		return number;
	}
	
	public String getSuit()
	{
		return suit;
	}
	
	public String toString()
	{
		return number + " " + suit;
	}
}
