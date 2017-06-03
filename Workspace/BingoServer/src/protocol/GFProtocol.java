package protocol;

import com.google.gson.Gson;

public class GFProtocol {

	public static final String RANKING_INFORMATION		= "RI/%s";
	public static final String LOGIN_ACTION				= "L/%s";
	public static final String LOGIN_RESPONSE			= "LR/%s";
	public static final String REGISTER_ACTION			= "NU/%s";
	public static final String REGISTER_RESPONSE		= "NUF/%s";
	public static final String CARTELA					= "CAT/%s";
	public static final String BINGO					= "BG/";
	public static final String BINGO_RESPONSE			= "BGF/%s";
	public static final String NUMBER_CLICK				= "MN/%d";
	public static final String NUMBER_CLICK_RESPONSE	= "MNF/%s";
	public static final String SORT_NUMBER				= "SN/%d";
	public static final String END_GAME					= "EG/%s";
	public static final String KICK						= "KS/";
	
	public static class PacketType {
		public static final int NONE			= 0;
		public static final int RANKING			= 1;
		public static final int LOGIN			= 2;
		public static final int LOGIN_F			= 3;
		public static final int REGISTER		= 4;
		public static final int REGISTER_F		= 5;
		public static final int CARTELA			= 6;
		public static final int BINGO			= 7;
		public static final int BINGO_F			= 8;
		public static final int NUMBER_CLICK	= 9;
		public static final int NUMBER_CLICK_F	= 10;
		public static final int SORT_NUMBER		= 11;
		public static final int END_GAME		= 12;
		public static final int KICK			= 13;
	}
	
	public static int getPacketType(String packet)
	{
		int packetLen = packet.length();
		
		if(packet.startsWith("RI/") && packetLen > 3)
			return PacketType.RANKING;
		
		if(packet.startsWith("L/") && packetLen > 2)
			return PacketType.LOGIN;
		
		if(packet.startsWith("LR/") && packetLen > 3)
			return PacketType.LOGIN_F;
		
		if(packet.startsWith("NU/") && packetLen > 3)
			return PacketType.REGISTER;
		
		if(packet.startsWith("NUF/") && packetLen > 4)
			return PacketType.REGISTER_F;
		
		if(packet.startsWith("CAT/") && packetLen > 4)
			return PacketType.CARTELA;
		
		if(packet.startsWith("BG/") && packetLen == 3)
			return PacketType.BINGO;
		
		if(packet.startsWith("BGF/") && packetLen == 5)
			return PacketType.BINGO_F;
		
		if(packet.startsWith("MN/") && packetLen > 3)
			return PacketType.NUMBER_CLICK;
		
		if(packet.startsWith("MNF/") && packetLen > 4)
			return PacketType.NUMBER_CLICK_F;
		
		if(packet.startsWith("SN/") && packetLen > 3)
			return PacketType.SORT_NUMBER;
		
		if(packet.startsWith("EG/") && packetLen > 3)
			return PacketType.END_GAME;
		
		if(packet.startsWith("KS/") && packetLen > 3)
			return PacketType.KICK;
		
		return PacketType.NONE;
	}
	
	private static Gson gson()
	{
		return new Gson();
	}
	
	public static Ranking getRanking(String packet)
	{
		String data = packet.substring(3);
		Ranking ranking = null;
		
		if(getPacketType(packet) == PacketType.RANKING)
		{
			try
			{
				ranking = gson().fromJson(data, Ranking.class);
			} catch(Exception e)
			{}
		}
		
		return ranking;
	}
	
	public static Player getPlayerFromLoginPacket(String packet)
	{
		String data = packet.substring(2);
		Player player = null;
		
		if(getPacketType(packet) == PacketType.LOGIN)
		{
			try
			{
				player = gson().fromJson(data, Player.class);
			} catch(Exception e)
			{}
		}
		
		return player;
	}
	
	public static Player getPlayerFromRegisterPacket(String packet)
	{
		String data = packet.substring(3);
		Player player = null;
		
		if(getPacketType(packet) == PacketType.REGISTER)
		{
			try
			{
				player = gson().fromJson(data, Player.class);
			} catch(Exception e)
			{}
		}
		
		return player;
	}

}
