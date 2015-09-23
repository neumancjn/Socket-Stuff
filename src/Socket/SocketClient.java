package Socket;

import CardGame.Card;

/**
 * Write a description of class SocketClient here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import javax.swing.JOptionPane;

public class SocketClient
{
    PrintWriter out;
    BufferedReader input;
    Socket s;
    
    /*
     * sets up a Client for the game.
     */
    public SocketClient()throws IOException
    {
        String serverAddress = JOptionPane.showInputDialog(
            "Enter IP Address of a machine that is\n" +
            "running the date service on port 9090:");
        s = new Socket(serverAddress, 9090);
        
    }
    
    /*
     * used to send a string to the server.
     */
    public void send(String info) throws IOException
    {
        out = new PrintWriter(s.getOutputStream(), true);
        out.println(info);
    }
    
    /*
     * listens for a string from the server.
     */
    public String listen() throws IOException
    {
        input = new BufferedReader(new InputStreamReader(s.getInputStream()));
        System.out.println(input.readLine());
        return input.readLine();
    }
    
    /*
     * closes the Socket.
     */
    public void close() throws IOException
    {
    	s.close();
    }
}
