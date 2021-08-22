package ClientServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
//enter the Server class
public class Server{
	/* 
	 * Enter the main class,this is where
	 * the editor will look for executable 
	 * code.
	 */
	public static void main(String [] args) throws IOException {
		
	
	Socket socket = null;
	InputStreamReader inputStreamReader = null;
	OutputStreamWriter outputStreamWriter = null;
	BufferedReader bufferedReader = null;
	BufferedWriter bufferedWriter  =null;
	ServerSocket serverSocket = null;
	
	serverSocket = new ServerSocket(1234);
	
	/*
	 * After creating a routable port for this
	 * program.
	 */
	
	while(true) {
		
		try {
			
			/*
			 * call the serverSocket method accept,
			 * which will setup the connection with the client.
			 */
			
			socket = serverSocket.accept();
		
			inputStreamReader = new InputStreamReader(socket.getInputStream());
			outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
		
			bufferedReader = new BufferedReader(inputStreamReader);
			bufferedWriter = new BufferedWriter(outputStreamWriter);
		
			String msgFromClient = bufferedReader.readLine();
			Date date = new Date();
			
			while(true) {
				
				//String msgFromClient = bufferedReader.readLine();
				System.out.println("Client :"+msgFromClient);
				System.out.println(date);
			
				bufferedWriter.write("MSG Received");
				bufferedWriter.newLine();
				bufferedWriter.flush();
						
				if(msgFromClient.equalsIgnoreCase("BYE"));
				break;
				}
			
				socket.close();
			
				inputStreamReader.close();
				outputStreamWriter.close();
			
				bufferedReader.close();
				bufferedWriter.close();
		
				}catch(IOException e) {
					e.printStackTrace();
				}	
			}/* infinite while loop is closed here */ 
		}
	}


				
				