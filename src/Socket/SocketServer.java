package Socket;

import CardGame.Card;


/**
 * Write a description of class SocketServer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class SocketServer
{

    ServerSocket host;
    PrintWriter out;
    BufferedReader input;
    ObjectOutputStream output;
    ObjectInputStream inputs;
    Socket s;

    /*
     * sets up the server.
     */
    public SocketServer() throws IOException
    {
        host = new ServerSocket(9090);
        s = host.accept();

    }

    /*
     * listens for a string from the client.
     */
    public String listenString() throws IOException
    {
    	//inputs = new ObjectInputStream(s.getInputStream());
        input = new BufferedReader(new InputStreamReader(s.getInputStream()));
        System.out.println(input.readLine());
        return input.readLine();
    }

    /*
     * sends a string to the clients.
     */
    public void sendString(String info) throws IOException
    {
    	//output = new ObjectOutputStream(s.getOutputStream());
        out = new PrintWriter(s.getOutputStream(), true);
        out.println(info);
    }
    
    /*
     * Listens for an ArrayList from the client, and returns it.
     */
    public ArrayList<Object> listenObject() throws IOException
    {
    	ArrayList list;
    	try{
    		inputs = new ObjectInputStream(s.getInputStream());
    		list = (ArrayList<Object>)inputs.readObject();
    		return list;
    	} catch(Exception e)
    	{
    		list = new ArrayList();
    		return list;
    	}
    }

    /*
     * sends a string to the clients.
     */
    public void sendObject(ArrayList<Object> list) throws IOException
    {
    	output = new ObjectOutputStream(s.getOutputStream());
        output.writeObject(list);
    }
    /*
     * Closes the Socket and ServerSocket.
     */
    public void close() throws IOException
    {
    	s.close();
    	host.close();
    }
}