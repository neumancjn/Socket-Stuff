package Socket;



/**
 * The pupose of this program is to
 * practice getting sockets to work
 * between two serveres.
 */
import java.io.IOException;
import java.util.Scanner;

public class SocketPractice
{
   public static void main(String args[]) throws IOException
   {
       Scanner scan = new Scanner(System.in);
       int option = 0;
       
       do
       {
           
            System.out.println("Enter 1 to host or 2 to join a server.");
            option = scan.nextInt();
        
       } while(option != 1 && option != 2);
       
       if(option == 1) 
       {
           SocketServer s = new SocketServer();
        }
        else
        {
            SocketClient c = new SocketClient();
        }
    }
}
