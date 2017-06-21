package game;

import protocol.Cartela;
import protocol.GFProtocol;
import protocol.Player;
import threads.ClientThread;

public class PlayerHandler extends Player {
	private Cartela cartela;
	private ClientThread connection;
	
	public PlayerHandler(Player player, ClientThread connection)
	{
		super.setName(player.getName());
		super.setEmail(player.getEmail());
		super.setWinsCount(player.getWinsCount());
		super.setPassword(player.getPassword());
		this.connection = connection;
	}
	
	public Cartela getCartela() {
		return cartela;
	}

	public void setCartela(Cartela cartela) {
		this.cartela = cartela;
	}

	public void kick()
	{
		this.sendMessage("MB/Você foi expulso do jogo!");
		this.connection.disconnectPlayer();
	}
	
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
