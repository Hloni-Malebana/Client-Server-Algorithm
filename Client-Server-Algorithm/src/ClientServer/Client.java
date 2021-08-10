package ClientServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client{
	public static void main(String [] args) {
	
	Socket socket = null;
	InputStreamReader inputStreamReader = null;
	OutputStreamWriter outputStreamWriter = null;
	BufferedReader bufferedReader = null;
	BufferedWriter bufferedWriter  =null;
	
	try {
		//ip address of server,tcp port 
		socket = new Socket ("localhost",1234);
		
		//Read from the server and output to the server
		inputStreamReader = new InputStreamReader(socket.getInputStream());
		outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
		
		//Make more efficient by reading more than one character at a time
		bufferedReader = new BufferedReader(inputStreamReader);
		bufferedWriter = new BufferedWriter(outputStreamWriter);
		
		Scanner scanner = new Scanner (System.in);
		int count = 0;
		/*
		 * while loop will run infinitely,
		 * it will break only if msgsToSend
		 * is BYE.
		 */
		while(true) {
			
			//Read from the keyboard
			String msgtoSend = scanner.nextLine();
			
			/* Use buffered writer to send user input
			 * to the server.
			 */
			bufferedWriter.write(msgtoSend);
			bufferedWriter.newLine();
			
			/*
			 * If I remove line 54,the user can continue
			 * to put user inputs.But the user inputs
			 * are not sent to the server.
			 */
			
			bufferedWriter.flush();
			
			/*
			 * Read the message entered by the server and enter
			 * and print it to the screen on the client pc.
			 */
			System.out.println("Server :"+bufferedReader.readLine());		
			//I think this is where the bug resides
			if(msgtoSend.equalsIgnoreCase("BYE"));
				break;
		}
		}catch(IOException e){
			e.printStackTrace();
		
		}finally{
			try {
				if(socket != null)
					socket.close();
				if(inputStreamReader != null)
					inputStreamReader.close();
				if(outputStreamWriter != null)
					outputStreamWriter.close();
				if(bufferedReader != null)
					bufferedReader.close();
				if(bufferedWriter != null)
					bufferedWriter.close();
			
			}catch(IOException e){
				e.printStackTrace();
			}	
		}			
	}	
}	
