package threads;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class KeyboardThread extends Thread {
	

	@Override
	public void run() {
		BufferedReader kBoard = new BufferedReader(new InputStreamReader(System.in));
		
		for(;;)
		{
			String line = "";
			try { line = kBoard.readLine(); }
			catch(Exception e) {}
			
			if(line.toLowerCase().equals("stop"))
			{
				System.out.println("Servidor desligado.");
				System.exit(0);
			}
		}
	}
	
}
