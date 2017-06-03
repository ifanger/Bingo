package db;

import daos.Players;

public class DB {
	public static final GFPreparedStatement	command;
	public static final Players				players;

	private static final String MYSQL_IP	= "127.0.0.1";
	private static final String MYSQL_DB	= "bingo";
	private static final String MYSQL_USER	= "root";
	private static final String MYSQL_PASS	= "";
	
	static
	{
		GFPreparedStatement	_command	= null;
		Players				_players	= null;
		
		try
		{
			
			_command =
		            new GFPreparedStatement (
		            "com.mysql.jdbc.Driver",
		            "jdbc:mysql://" + MYSQL_IP + ":3306/" + MYSQL_DB,
		            MYSQL_USER, MYSQL_PASS);

		    _players = new Players();
			
		} catch(Exception e)
		{
			System.err.println(e.getMessage());
			System.exit(0);
		}
		
		command		= _command;
		players		= _players;
	}
}
