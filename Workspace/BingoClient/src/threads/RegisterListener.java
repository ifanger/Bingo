package threads;

import java.util.Scanner;

import gui.RegisterWindow;
import protocol.GFProtocol;
import utils.Connection;

public class RegisterListener extends Thread {
	private Connection connection;
	private RegisterWindow register;
	private boolean running = true;
	
	public RegisterListener(Connection connection, RegisterWindow register)
	{
		this.connection = connection;
		this.register = register;
	}
	
	@Override
	public void run()
	{
		while(this.running)
		{
			Scanner scanner = connection.getInput();
			
			if(scanner.hasNextLine())
			{
				String receivedPacket = scanner.nextLine();
				int packetType = GFProtocol.getPacketType(receivedPacket);
				
				switch(packetType)
				{
					case GFProtocol.PacketType.REGISTER_F:
						boolean result = GFProtocol.isRegistred(receivedPacket);
						if(result)
						{
							register.registerSuccess();
							stopThread();
						}
						else
							register.registerFailed();
						break;
				}
			}
		}
	}
	
	public void stopThread()
	{
		this.running = false;
	}
}
