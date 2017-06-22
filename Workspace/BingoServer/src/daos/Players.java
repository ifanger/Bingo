package daos;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DB;
import db.GFResultSet;
import protocol.Player;
import protocol.Ranking;

/**
 * Classe para gerenciar jogadores no banco de dados.
 * @author Gustavo Ifanger
 * 
 */
public class Players {
	
	/**
	 * Verifica se um jogador já existe no banco de dados.
	 * @param email Email do jogador a ser verificado.
	 * @return Verdadeiro caso o jogador exista.
	 * @throws Exception Caso não seja possível realizar a query sql.
	 */
	private static boolean playerExists(String email) throws Exception
	{
		boolean ret = false;
		
		try
		{
			String sqlQuery = "SELECT * FROM players WHERE email = ?";
			
			DB.command.prepareStatement(sqlQuery);
			DB.command.setString(1, email);
			
			GFResultSet result = (GFResultSet) DB.command.executeQuery();
			ret = result.first();
		} catch(SQLException e)
		{
			System.out.println(e.getMessage());
			throw new Exception("Falha ao verificar usuário.");
		}
		
		return ret;
	}
	
	/**
	 * Cadastra um novo jogador no banco de dados.
	 * @param player Jogador a ser cadastrado.
	 * @param password Senha do jogador já com a criptografia.
	 * @throws Exception Caso a senha não esteja criptografada ou já exista um usuário com o mesmo e-mail.
	 */
	public static void register(Player player) throws Exception
	{
		if(player == null)
			throw new Exception("Falha ao registrar usuário. (Player equals null)");
		
		if(player.getPassword().length() != 32)
			throw new Exception("Tentativa de registrar usuário sem senha segura.");
		
		if(playerExists(player.getEmail()))
			throw new Exception("Já existe um usuário com esse e-mail.");
		
		try
		{
			String sqlQuery = "INSERT INTO players "
					+ "(email, name, password, registerDate, monthlyWins) "
					+ "VALUES (?, ?, ?, ?, 0)";
			
			DB.command.prepareStatement(sqlQuery);
			DB.command.setString(1, player.getEmail());
			DB.command.setString(2, player.getName());
			DB.command.setString(3, player.getPassword());
			DB.command.setDate(4, new Date(new java.util.Date().getTime()));
			DB.command.executeUpdate();
			DB.command.commit();
		} catch(SQLException e)
		{
			e.printStackTrace();
			throw new Exception("Falha ao registrar usuário. (SQLException)");
		}
	}
	
	/**
	 * Obtem um jogador através do email.
	 * @param email Email do jogador a ser obtido.
	 * @return Objeto do jogador caso o mesmo exista, se não, retorna nulo.
	 * @throws Exception Caso não exista jogador com o e-mail cadastrado.
	 */
	public static Player getPlayer(String email) throws Exception
	{
		Player player = null;
		
		try
		{
			String sqlQuery = "SELECT * FROM players WHERE email = ?";
			
			DB.command.prepareStatement(sqlQuery);
			DB.command.setString(1, email);
			
			GFResultSet result = (GFResultSet)DB.command.executeQuery();
			
			if(!result.first())
				throw new Exception("Não existe usuário cadastrado com esse e-mail.");
			
			player = new Player();
			player.setEmail(result.getString("email"));
			player.setName(result.getString("name"));
			player.setPassword(result.getString("password"));
			player.setWinsCount(result.getInt("monthlyWins"));
		} catch(SQLException e)
		{
			e.printStackTrace();
			throw new Exception("Falha ao obter jogador.");
		}
		
		return player;
	}
	
	/**
	 * Retorna o Ranking de Jogadores
	 * @return Ranking dos jogadores
	 * @throws Exception Caso ocorra algum problema durante a conexão com o banco de dados.
	 */
	public static Ranking getRanking() throws Exception
	{
		Ranking ranking = null;
		
		try
		{	
			GFResultSet result = null;
			String sqlQuery = "SELECT email FROM players ORDER BY monthlyWins DESC";
			
			DB.command.prepareStatement(sqlQuery);
			result = (GFResultSet) DB.command.executeQuery();

			int i = 0;
			
			Player p1 = null;
			Player p2 = null;
			Player p3 = null;
			
			while(result.next())
			{
				String playerEmail = result.getString("email");
				Player player = Players.getPlayer(playerEmail);
				
				switch(i)
				{
					case 0:
						p1 = player;
						break;
					case 1:
						p2 = player;
						break;
					case 2:
						p3 = player;
						break;
					default:
						break;
				}
				
				i++;
			}
			
			ranking = new Ranking(p1, p2, p3);
		} catch(SQLException e)
		{
			e.printStackTrace();
			throw new Exception("Falha ao obter ranking.");
		}
		
		return ranking;
	}
	
	public static void addWinCount(Player player) throws Exception
	{
		if(player == null)
			throw new Exception("Falha ao falha ao adicionar vitória ao jogador.");
		
		try
		{
			String sqlQuery = "UPDATE players SET monthlyWins = ? WHERE email = ?";
			
			DB.command.prepareStatement(sqlQuery);
			DB.command.setInt(1, player.getWinsCount() + 1);
			DB.command.setString(2, player.getEmail());
			DB.command.executeUpdate();
			DB.command.commit();
		} catch(SQLException e)
		{
			e.printStackTrace();
			throw new Exception("Falha ao somar vitória.");
		}
	}
}
