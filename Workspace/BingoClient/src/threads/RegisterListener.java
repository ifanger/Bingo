package threads;

import java.io.IOException;

import gui.RegisterWindow;
import protocol.GFProtocol;
import utils.Connection;

public class RegisterListener extends Thread {
	private Connection connection;
	private RegisterWindow register;
	private boolean running = true;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public RegisterListener(Connection connection, RegisterWindow register)
	{
		this.connection = connection;
		this.register = register;
	}
	
	@Override
	public void run()
	{
		String receivedPacket = "";
		try {
			receivedPacket = connection.getInput().readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(running && !receivedPacket.isEmpty())
		{
			try {
				receivedPacket = connection.getInput().readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			boolean result = receivedPacket.equals("NUF/T");
			
			if(result)
				register.registerSuccess();
			else
				register.registerFailed();
					
		}
	}
	
	public void stopThread()
	{
		this.running = false;
	}
}
