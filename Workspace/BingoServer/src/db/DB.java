package db;

import daos.Players;

public class DB {
	public static final GFPreparedStatement	command;
	public static final Players				players;

	private static final String MYSQL_IP	= "172.16.12.12";
	private static final String MYSQL_DB	= "bdsqlac14";
	private static final String MYSQL_USER	= "bdsqlac14";
	private static final String MYSQL_PASS	= "Rbtlr3";
	
	static
	{
		GFPreparedStatement	_command	= null;
		Players				_players	= null;
		
		try
		{
			
			_command =
		            new GFPreparedStatement (
		            		"com.microsoft.sqlserver.jdbc.SQLServerDriver",
		            		"jdbc:sqlserver://" + MYSQL_IP + ":1433;databasename=" + MYSQL_DB,
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
