package threads;

import java.io.IOException;
import java.util.Scanner;

import gui.LoginWindow;
import protocol.GFProtocol;
import protocol.Player;
import protocol.Ranking;
import utils.Connection;

public class LoginListener extends Thread {
	private LoginWindow login;
	private Connection connection;
	private boolean running = true;
	
	public LoginListener(LoginWindow parent, Connection connection)
	{
		this.login = parent;
		this.connection = connection;
		
		this.connection.sendPacket(GFProtocol.RANKING_INFORMATION);
	}

	@Override
	public void run() {
		String receivedPacket = "";
		
		try {
			receivedPacket = this.connection.getInput().readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(running && !receivedPacket.isEmpty())
		{
			try {
				receivedPacket = this.connection.getInput().readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int packetType = GFProtocol.getPacketType(receivedPacket);
			switch(packetType)
			{
				case GFProtocol.PacketType.LOGIN_F:
					Player receivedPlayer = GFProtocol.isLoggedIn(receivedPacket);
					if(receivedPlayer == null)
						login.onLoginFailed();
					else
					{
						login.onLogginSuccess(receivedPlayer);
						stopThread();
					}
					break;
				case GFProtocol.PacketType.RANKING:
					System.out.println(receivedPacket);
					Ranking ranking = GFProtocol.getRanking(receivedPacket);
					
					if(ranking == null)
						break;
					
					Player player1 = ranking.getFirstPlayer();
					Player player2 = ranking.getSecondPlayer();
					Player player3 = ranking.getThirdPlayer();
					
					if(player1 != null)
					{
						this.login.getLblJogador1().setText(player1.getName());
						this.login.getLblVitoria1().setText(player1.getWinsCount() + "");
					}
					
					if(player2 != null)
					{
						this.login.getLblJogador2().setText(player2.getName());
						this.login.getLblVitoria2().setText(player2.getWinsCount() + "");
					}
					
					if(player3 != null)
					{
						this.login.getLblJogador3().setText(player3.getName());
						this.login.getLblVitoria3().setText(player3.getWinsCount() + "");
					}
					
					break;
				default: // Pacote desconhecido
					System.out.println("Pacote desconhecido recebido: " + receivedPacket);
					stopThread();
					break;
			}
		}
	}
	
	public void stopThread()
	{
		System.out.println("LoginListener stopped.");
		this.running = false;
	}
	
}
