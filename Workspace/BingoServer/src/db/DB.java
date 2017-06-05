package db;

import daos.Players;

public class DB {
	public static final GFPreparedStatement	command;
	public static final Players				players;

	private static final String MYSQL_IP	= "42d5f7f7-3155-45e6-8a89-a789010c451d.sqlserver.sequelizer.com";
	private static final String MYSQL_DB	= "db42d5f7f7315545e68a89a789010c451d";
	private static final String MYSQL_USER	= "vrmkuirbtfeopmqd";
	private static final String MYSQL_PASS	= "N2oTCVhmJY2rX55LRsYFn7XgXGFh8DXzcuWoESzeGsW7BxoXrEYrMg3Qdg55oamf";
	
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
