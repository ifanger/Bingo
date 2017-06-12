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

import threads.Cliente;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientInterface {

	private Thread conectora;
	private JFrame frmBingoClient;
	private JTextField txtEmail;
	private JTextField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientInterface window = new ClientInterface();
					window.frmBingoClient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ClientInterface() {
		conectora = new Cliente();
		conectora.start();
		
		initialize();
	}
	
	public JFrame getBingoClient()
	{
		return this.frmBingoClient;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(JTextField txtEmail) {
		this.txtEmail = txtEmail;
	}

	public JTextField getTxtSenha() {
		return txtSenha;
	}

	public void setTxtSenha(JTextField txtSenha) {
		this.txtSenha = txtSenha;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBingoClient = new JFrame();
		frmBingoClient.setResizable(false);
		frmBingoClient.setTitle("Bingo Client");
		frmBingoClient.setBounds(100, 100, 330, 400);
		frmBingoClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBingoClient.getContentPane().setLayout(new BorderLayout(0, 0));
		frmBingoClient.setLocationRelativeTo(null); //start no centro
		
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
		txtSenha.setBounds(78, 60, 100, 30);
		panel.add(txtSenha);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSenha.setBounds(17, 66, 61, 14);
		panel.add(lblSenha);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnEntrar.setBounds(37, 119, 119, 37);
		panel.add(btnEntrar);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Abrindo Jframe de cadastro
				
				JanelaCadastro Cadastro = new JanelaCadastro(ClientInterface.this, (Cliente)conectora);
				Cadastro.setVisible(true);
				frmBingoClient.setVisible(false);
				Cadastro.setLocationRelativeTo(null);
			}
		});
		btnCadastrar.setBounds(166, 119, 119, 37);
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
		
		JLabel lblJogador1 = new JLabel("-");
		lblJogador1.setHorizontalAlignment(SwingConstants.CENTER);
		lblJogador1.setBounds(10, 266, 164, 14);
		panel.add(lblJogador1);
		
		JLabel lblVitoria1 = new JLabel("-");
		lblVitoria1.setHorizontalAlignment(SwingConstants.CENTER);
		lblVitoria1.setBounds(196, 266, 56, 14);
		panel.add(lblVitoria1);
		
		JLabel lblVitoria2 = new JLabel("-");
		lblVitoria2.setHorizontalAlignment(SwingConstants.CENTER);
		lblVitoria2.setBounds(196, 291, 56, 14);
		panel.add(lblVitoria2);
		
		JLabel lblVitoria3 = new JLabel("-");
		lblVitoria3.setHorizontalAlignment(SwingConstants.CENTER);
		lblVitoria3.setBounds(196, 316, 56, 14);
		panel.add(lblVitoria3);
		
		JLabel lblJogador2 = new JLabel("-");
		lblJogador2.setHorizontalAlignment(SwingConstants.CENTER);
		lblJogador2.setBounds(10, 291, 164, 14);
		panel.add(lblJogador2);
		
		JLabel lblJogador3 = new JLabel("-");
		lblJogador3.setHorizontalAlignment(SwingConstants.CENTER);
		lblJogador3.setBounds(10, 316, 164, 14);
		panel.add(lblJogador3);
	}
}
