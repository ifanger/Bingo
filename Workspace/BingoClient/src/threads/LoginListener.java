package threads;

import java.io.IOException;
import java.util.Scanner;

import gui.GameWindow;
import gui.LoginWindow;
import gui.RegisterWindow;
import protocol.GFProtocol;
import protocol.Player;
import protocol.Ranking;
import utils.Connection;

public class LoginListener extends Thread {
	private LoginWindow login;
	private RegisterWindow register;
	private GameWindow game;
	private Connection connection;
	private boolean running = true;
	
	public LoginListener(LoginWindow parent, RegisterWindow register, GameWindow game, Connection connection)
	{
		this.login = parent;
		this.register = register;
		this.game = game;
		this.connection = connection;
		
		this.connection.sendPacket(GFProtocol.RANKING_INFORMATION);
	}

	@Override
	public void run() {
		try
		{
			String receivedPacket = "";
			
			while(running && (receivedPacket = this.connection.getInput().readLine()) != null)
			{
				System.out.println("Recebido: " + receivedPacket);
				
				int packetType = GFProtocol.getPacketType(receivedPacket);
				switch(packetType)
				{
					case GFProtocol.PacketType.LOGIN_F:
						Player receivedPlayer = GFProtocol.isLoggedIn(receivedPacket);
						if(receivedPlayer == null)
							login.onLoginFailed();
						else
							login.onLogginSuccess(receivedPlayer);
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
					case GFProtocol.PacketType.SORT_NUMBER:
						int drawnNumber = GFProtocol.getDrawnNumber(receivedPacket);
						if(drawnNumber >= 0)
						{
							game.showMessage("Numero Sorteado: " + drawnNumber);
						}
						break;
					case GFProtocol.PacketType.CARTELA:
						break;
					default: // Pacote desconhecido
						if(receivedPacket.startsWith("NUF/") && receivedPacket.length() > 4)
						{
							String retorno = receivedPacket.substring(4);
							if(retorno.equals("T"))
								register.registerSuccess();
							else
								register.registerFailed();
						}
						
						if(receivedPacket.startsWith("M/") && receivedPacket.length() > 2)
							game.showMessage(receivedPacket.substring(2));
						
						if(receivedPacket.startsWith("MB/") && receivedPacket.length() > 3)
							game.showMessageBox(receivedPacket.substring(3));
						
						break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stopThread()
	{
		System.out.println("LoginListener stopped.");
		this.running = false;
	}
	
}
