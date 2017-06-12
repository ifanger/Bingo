package threads;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class Cliente extends Thread {
	private Socket cliente;
	private PrintStream saida;
	
	public Cliente() {
		try {
			
			cliente = new Socket("172.16.12.200", 8090);
            saida = new PrintStream(cliente.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void run() {
		
		Listener escuta = new Listener(this);
		escuta.start();
		
	}
	
	public void mandaPacote(String pacote)
	{
		saida.println(pacote);
		saida.flush();
	}
	
	public void fecharConexao()
	{
		try {
			saida.close();
			cliente.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
        
		
}