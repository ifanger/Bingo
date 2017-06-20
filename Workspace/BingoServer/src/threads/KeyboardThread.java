package threads;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import daos.Players;
import protocol.Ranking;

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
			} else if(line.toLowerCase().equals("ranking"))
			{
				Ranking ranking;
				try {
					ranking = Players.getRanking();
					System.out.println(ranking.toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}
