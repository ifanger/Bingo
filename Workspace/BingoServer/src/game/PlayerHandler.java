package game;

import protocol.Cartela;
import protocol.GFProtocol;
import protocol.Player;
import threads.ClientThread;

/**
 * Essa � uma extens�o de Player, no entando, cont�m dados como conex�o e cartela.
 * @author Gustavo Ifanger
 *
 */
public class PlayerHandler extends Player {
	private Cartela cartela;
	private ClientThread connection;
	
	/**
	 * Construtor padr�o da classe.
	 * @param player Jogador a obter informa��es.
	 * @param connection Conex�o do jogador.
	 */
	public PlayerHandler(Player player, ClientThread connection)
	{
		super.setName(player.getName());
		super.setEmail(player.getEmail());
		super.setWinsCount(player.getWinsCount());
		super.setPassword(player.getPassword());
		this.connection = connection;
	}
	
	/**
	 * Retorna a cartela desse jogador.
	 * @return Cartela do jogador.
	 */
	public Cartela getCartela() {
		return cartela;
	}

	/**
	 * Altera a cartela do jogador.
	 * @param cartela Nova cartela do jogador.
	 */
	public void setCartela(Cartela cartela) {
		this.cartela = cartela;
	}

	/**
	 * Expulsa o jogador do jogo.
	 */
	public void kick()
	{
		this.sendMessage("MB/Voc� foi expulso do jogo! (provavelmente o jogo est� em andamento)");
		this.connection.disconnectPlayer();
	}
	
	/**
	 * Envia um pacote para o jogador.
	 * @param packet Pacote a ser enviado.
	 */
	public void sendMessage(String packet)
	{
		this.connection.sendPacket(packet);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public int hashCode()
	{
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return super.equals(obj);
	}
}
