package ClientServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
//Client Server Algorithm
public class Client{
	
	public static void main(String [] args) {
	
	InputStreamReader inputStreamReader = null;
	OutputStreamWriter outputStreamWriter = null;
	BufferedReader bufferedReader = null;
	BufferedWriter bufferedWriter = null;
	Socket socket = null;
	
	while(true) {
	
		try {
			// IP  address of server,port 1234.
		
			socket = new Socket ("localhost",1234);		
			
			/* Read from the server and output to the server,
			 * that is why we the socket keyword.		
			 */
			
			/* 
			 * Instantiate an object that is of 
			 * Input/OutputStreamReader/Writer.It
			 * will call the getInputStream/getOutputWriter method 
			 * which is in the socket object.
			 * 
			 * The socket object will store all the information that 
			 * is written by the client for the server to come get it.
			 * And it will also store all information written by the server 
			 * so that the client can read it.
			 */
			
			inputStreamReader = new InputStreamReader(socket.getInputStream());
			outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
		
			/* 
			 * Make more efficient by reading more than one character 
			 * at a time
			 */
		
			/* 
			 * Instantiate an object that is of BufferedRead/Writer 
			 * type.It will take input/output stream as an argument.
			 */
		
			bufferedReader = new BufferedReader(inputStreamReader);
			bufferedWriter = new BufferedWriter(outputStreamWriter);
		
			Scanner scanner = new Scanner (System.in);
		
			/* 
			 * while loop will run infinitely,it will break only if 
			 * msgsToSend is BYE.
			 */
		
			while(true) {
			
				// Read the string entered on the keyboard
				
				String msgtoSend = scanner.nextLine();
			
				/*
				 * From the bufferedWriter object call the write 
				 * method which will send the string contained 
				 * in the msgtoSend variable to the server.By using the
				 * socket object.
				 */
			
				bufferedWriter.write(msgtoSend);
			
				/*
				 * From the bufferedWriter object call the newLine
				 * method with will add each word the user entered onto a 
				 * new line.
				 */
				
				bufferedWriter.newLine();
			
				/*
				 * From the bufferedWriter object,call the flush
				 * method which will remove all the remaining infromation 
				 * in the buffer after every new line is entered by the 
				 * user.
				 */
				
				bufferedWriter.flush();
				
				/*
				 * Read the message entered by the server and enter
				 * and print it to the screen on the client pc.
				 */
				
				System.out.println("Server :"+bufferedReader.readLine());				
							
				/* 
				 * If the msgtoSend is BYE,break the loop.
				 * this applies whether the string is in upper
				 * case or lower case...
				 */
				
				if(msgtoSend.equalsIgnoreCase("BYE")) ;
					break;
				}
			
			}catch(IOException e){
				e.printStackTrace();
			}finally {
				try {
				
					if(socket!=null)
						socket.close();
					if(inputStreamReader!=null)
						inputStreamReader.close();
					if(outputStreamWriter!=null)
						outputStreamWriter.close();
					if(bufferedReader!=null)
						bufferedReader.close();
					if(bufferedWriter!=null)
						bufferedWriter.close();
				
					}catch(IOException e){
						e.printStackTrace();
				}
			}			
	  	}	
	}	
}