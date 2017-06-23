package threads;

import java.awt.Color;

import javax.swing.JButton;

import gui.GameWindow;
import gui.LoginWindow;
import gui.RegisterWindow;
import protocol.Cartela;
import protocol.GFProtocol;
import protocol.Player;
import protocol.Ranking;
import utils.Connection;

/**
 * Thread responsável pela leitura de pacotes recebidos pelo servidor.
 * @author Gustavo Ifanger
 *
 */
public class LoginListener extends Thread {
	/**
	 * Armazena a instância da janela de login.
	 */
	private LoginWindow login;
	
	/**
	 * Armazena a instância da janela de registro.
	 */
	private RegisterWindow register;
	
	/**
	 * Armazena a instância da janela de jogo.
	 */
	private GameWindow game;
	
	/**
	 * Armazena a conexão com o servidor.
	 */
	private Connection connection;
	
	/**
	 * Determina se a thread deve continuar executando ou não.
	 */
	private boolean running = true;
	
	/**
	 * Construtor padrão da classe.
	 * @param parent Janela de login.
	 * @param register Janela de registro.
	 * @param game Janela de jogo.
	 * @param connection Conexão com o servidor.
	 */
	public LoginListener(LoginWindow parent, RegisterWindow register, GameWindow game, Connection connection)
	{
		this.login = parent;
		this.register = register;
		this.game = game;
		this.connection = connection;
		
		this.connection.sendPacket(GFProtocol.RANKING_INFORMATION);
	}

	/**
	 * Método de execução de thread.
	 */
	@Override
	public void run() {
		try
		{
			String receivedPacket = "";
			
			while(running && (receivedPacket = this.connection.getInput().readLine()) != null)
			{
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
						Cartela cartela = GFProtocol.getCartela(receivedPacket);
						game.onCartelaReceived(cartela);
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
						
						if(receivedPacket.startsWith("EG/") && receivedPacket.length() > 3)
						{
							Player p = GFProtocol.getWinner(receivedPacket);
							for(JButton btn : this.game.getButtons())
								if(btn != null)
								{
									btn.setBackground(Color.WHITE);
									btn.setText("0");
									btn.setEnabled(true);
								}
							
							if(this.game.getBingoButton() != null)
								this.game.getBingoButton().setEnabled(false);
							
							this.game.showMessage(p.getName() + " ganhou o jogo!");
						}
						
						break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método responsável por parar a thread.
	 */
	public void stopThread()
	{
		System.out.println("LoginListener stopped.");
		this.running = false;
	}
	
}
