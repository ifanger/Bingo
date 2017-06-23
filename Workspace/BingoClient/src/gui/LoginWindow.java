package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import com.google.gson.Gson;

import protocol.GFProtocol;
import protocol.GFSecurity;
import protocol.Player;
import threads.LoginListener;
import utils.Connection;
import utils.EmailUtils;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionEvent;

/**
 * Formulário de Login
 * @author Gustavo Ifanger
 *
 */
public class LoginWindow {
	private Connection connection;
	private LoginListener listener;
	private RegisterWindow register;
	private GameWindow game;
	private Gson gson;
	private JFrame frmBingoClient;
	private JTextField txtEmail;
	private JTextField txtSenha;
	private JLabel lbMensagem;
	private JLabel lblJogador1;
	private JLabel lblJogador2;
	private JLabel lblJogador3;
	private JLabel lblVitoria1;
	private JLabel lblVitoria2;
	private JLabel lblVitoria3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
					window.frmBingoClient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginWindow() {
		this.gson = new Gson();
		initialize();
	}
	
	public JFrame getBingoClient()
	{
		return this.frmBingoClient;
	}
	
	public Connection getConnection()
	{
		return this.connection;
	}
	
	/**
	 * Método chamado ao clicar no botão de login.
	 */
	public void login()
	{
		if(this.connection == null || !this.connection.isConnected())
		{
			showMessage("Não conectado ao servidor.");
			return;
		}
		
		String loginEmail = txtEmail.getText();
		String loginPassword = txtSenha.getText();
		
		if(loginEmail.trim().isEmpty() || loginPassword.trim().isEmpty())
		{
			showMessage("Preencha todos os campos!");
			return;
		}
		
		if(!EmailUtils.isValidEmailAddress(loginEmail))
		{
			showMessage("Endereço de e-mail inválido!");
			return;
		}
		
		Player player = new Player();
		player.setEmail(txtEmail.getText());
		player.setPassword(GFSecurity.passwordHash(txtSenha.getText()));
		
		this.connection.sendPacket(
				String.format(GFProtocol.LOGIN_ACTION, 
						this.gson.toJson(player)
						));
	}
	
	/**
	 * Método chamado quando o login falha.
	 */
	public void onLoginFailed()
	{
		showMessage("Usuário ou senha incorretos.");
	}
	
	/**
	 * Método chamado quando o login é bem sucedido.
	 * @param player Instância de Player recebida do servidor.
	 */
	public void onLogginSuccess(Player player)
	{
		openGame();
	}
	
	/**
	 * Abre a janela de jogo.
	 */
	public void openGame()
	{
		game.setVisible(true);
		game.setLocationRelativeTo(null);
		frmBingoClient.setVisible(false);
	}
	
	/**
	 * Abre a janela de registro.
	 */
	public void openRegister()
	{
		register.setVisible(true);
		register.setLocationRelativeTo(null);
		frmBingoClient.setVisible(false);
	}
	
	/**
	 * Exibe uma mensagem na label.
	 * @param text Mensagem a ser exibida.
	 */
	public void showMessage(String text)
	{
		lbMensagem.setText(text);
	}

	public JLabel getLblJogador1() {
		return lblJogador1;
	}

	public JLabel getLblJogador2() {
		return lblJogador2;
	}

	public JLabel getLblJogador3() {
		return lblJogador3;
	}
	
	public JLabel getLblVitoria1() {
		return lblVitoria1;
	}

	public JLabel getLblVitoria2() {
		return lblVitoria2;
	}

	public JLabel getLblVitoria3() {
		return lblVitoria3;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBingoClient = new JFrame();
		frmBingoClient.setResizable(false);
		frmBingoClient.setTitle("Entrar");
		frmBingoClient.setBounds(100, 100, 330, 400);
		frmBingoClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBingoClient.getContentPane().setLayout(new BorderLayout(0, 0));
		frmBingoClient.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		frmBingoClient.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(17, 30, 63, 14);
		panel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(78, 24, 203, 30);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		txtSenha = new JPasswordField();
		txtSenha.setColumns(10);
		txtSenha.setBounds(78, 60, 203, 30);
		panel.add(txtSenha);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSenha.setBounds(17, 66, 61, 14);
		panel.add(lblSenha);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		btnEntrar.setBounds(162, 122, 119, 37);
		panel.add(btnEntrar);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openRegister();
			}
		});
		btnCadastrar.setBounds(36, 122, 119, 37);
		panel.add(btnCadastrar);
		
		JLabel lblRecordsDoMs = new JLabel("Records do M\u00EAs");
		lblRecordsDoMs.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecordsDoMs.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRecordsDoMs.setBounds(10, 192, 300, 37);
		panel.add(lblRecordsDoMs);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(57, 241, 67, 14);
		panel.add(lblNewLabel);
		
		JLabel lblVitrias = new JLabel("Vit\u00F3rias");
		lblVitrias.setHorizontalAlignment(SwingConstants.CENTER);
		lblVitrias.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVitrias.setBounds(196, 241, 56, 14);
		panel.add(lblVitrias);
		
		lblJogador1 = new JLabel("-");
		lblJogador1.setHorizontalAlignment(SwingConstants.CENTER);
		lblJogador1.setBounds(10, 266, 164, 14);
		panel.add(lblJogador1);
		
		lblVitoria1 = new JLabel("-");
		lblVitoria1.setHorizontalAlignment(SwingConstants.CENTER);
		lblVitoria1.setBounds(196, 266, 56, 14);
		panel.add(lblVitoria1);
		
		lblVitoria2 = new JLabel("-");
		lblVitoria2.setHorizontalAlignment(SwingConstants.CENTER);
		lblVitoria2.setBounds(196, 291, 56, 14);
		panel.add(lblVitoria2);
		
		lblVitoria3 = new JLabel("-");
		lblVitoria3.setHorizontalAlignment(SwingConstants.CENTER);
		lblVitoria3.setBounds(196, 316, 56, 14);
		panel.add(lblVitoria3);
		
		lblJogador2 = new JLabel("-");
		lblJogador2.setHorizontalAlignment(SwingConstants.CENTER);
		lblJogador2.setBounds(10, 291, 164, 14);
		panel.add(lblJogador2);
		
		lblJogador3 = new JLabel("-");
		lblJogador3.setHorizontalAlignment(SwingConstants.CENTER);
		lblJogador3.setBounds(10, 316, 164, 14);
		panel.add(lblJogador3);
		
		lbMensagem = new JLabel("");
		lbMensagem.setHorizontalAlignment(SwingConstants.CENTER);
		lbMensagem.setBounds(0, 97, 314, 14);
		panel.add(lbMensagem);
		
		Socket socket = null;
		try {
			socket = new Socket("localhost", 8090);
		} catch(IOException e)
		{
			e.printStackTrace();
			showMessage("Não foi possível conectar-se ao servidor.");
		} finally
		{
			this.connection = new Connection(socket);
			register = new RegisterWindow(this, this.connection);
			game = new GameWindow(this, this.connection);
			register.setVisible(false);
			game.setVisible(false);
			
			this.listener = new LoginListener(this, register, game, this.connection);
			
			if(socket != null)
			{
				if(socket.isConnected())
					this.connection.setConnected(true);
				else
					this.connection.setConnected(false);
			} else {
				this.connection.setConnected(false);
			}
			
			this.listener.start();
		}
		
	}
}
