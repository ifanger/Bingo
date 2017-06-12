package threads;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JLabel;

public class Cliente extends Thread {
	private Socket cliente;
	private PrintStream saida;
	private JLabel labelSaida;
	
	public Cliente() {
	}

	@Override
	public void run() {
		try {
			Thread.sleep(500);
			cliente = new Socket("172.16.12.200", 8090);
            saida = new PrintStream(cliente.getOutputStream());
        } catch (IOException | InterruptedException e) {
        	this.exibeMensagem("Não foi possível conectar-se ao servidor.", Color.RED);
            e.printStackTrace();
        }
		
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
	
	public void setLabel(JLabel novaLabel)
	{
		if(novaLabel != null)
			this.labelSaida = novaLabel;
	}
	
	public void exibeMensagem(String mensagem, Color cor)
	{
		if(labelSaida != null)
    	{
    		labelSaida.setForeground(Color.RED);
    		labelSaida.setText(mensagem);
    	}
	}
        
		
}