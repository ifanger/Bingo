package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import protocol.GFProtocol;
import protocol.GFSecurity;
import protocol.Player;
import threads.Cliente;
import utils.Connection;
import utils.EmailUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField txtSenha;
	private JPasswordField txtConfirma;
	private JTextField txtEmail;
	private JTextField txtNome;
	private JButton btnOK;
	private LoginWindow janelaLogin;
	private Connection connection;
	
	/**
	 * Esse método é chamado quando o registro é bem sucedido.
	 */
	public void registerSuccess()
	{
		JOptionPane.showMessageDialog(null, "Conta criada com sucesso!");
		close();
	}
	
	/**
	 * Esse método é chamado quando o registro não é bem sucedido.
	 */
	public void registerFailed()
	{
		btnOK.setEnabled(true);
		setTitle("Cadastro de Jogadores");
		JOptionPane.showMessageDialog(null, "Já existe um usuário cadastrado com esse endereço de e-mail!");
	}
	
	/**
	 * Fecha a janela atual
	 */
	public void close()
	{
		janelaLogin.getBingoClient().setVisible(true);
		RegisterWindow.this.dispose();
	}

	public RegisterWindow(LoginWindow janelaLogin, Connection connection) {
		this.janelaLogin = janelaLogin;
		this.connection = connection;
		
		setResizable(false);
		setTitle("Cadastro de Jogadores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 407, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 376, 213);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(58, 21, 95, 22);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(58, 54, 95, 22);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Senha:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(58, 87, 95, 20);
		panel.add(lblNewLabel_2);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(158, 87, 196, 20);
		panel.add(txtSenha);
		txtSenha.setColumns(10);
		
		JLabel lblConfirmao = new JLabel("Confirma\u00E7\u00E3o:");
		lblConfirmao.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblConfirmao.setBounds(31, 115, 122, 20);
		panel.add(lblConfirmao);
		
		txtConfirma = new JPasswordField();
		txtConfirma.setColumns(10);
		txtConfirma.setBounds(158, 115, 196, 20);
		panel.add(txtConfirma);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(158, 55, 196, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(158, 22, 196, 20);
		panel.add(txtNome);
		
		
		
		btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nome = txtNome.getText();
				String email = txtEmail.getText();
				String senha = txtSenha.getText();
				String senha2 = txtConfirma.getText();
				String erro = "";
				
				if(nome.isEmpty() || email.isEmpty() || senha.isEmpty() || senha2.isEmpty())
					return;
				
				if(nome.length() < 3 && nome.length() > 30)
					erro = "Por favor, digite um nome válido. (de 3 a 30 caracteres)";
				
				else if(!senha.equals(senha2))
					erro = "As senhas não correspondem.";
				
				else if(senha.length()<3)
					erro = "A senha digitada é fraca demais.";
				
				else if(!EmailUtils.isValidEmailAddress(email))
					erro = "O endereço de e-mail digitado é inválido.";
				
				if(!erro.isEmpty())
					JOptionPane.showMessageDialog(null, erro);
				else
				{
					Player p = new Player();
					p.setName(nome);
					p.setPassword(GFSecurity.passwordHash(senha));
					p.setEmail(email);
					p.setWinsCount(0);
					
					RegisterWindow.this.connection.sendPacket(
							String.format(GFProtocol.REGISTER_ACTION, 
									new Gson().toJson(p)
									)
							);
					
					setTitle("Cadastrando usuário...");
					btnOK.setEnabled(false);
				}
			}
		});
		btnOK.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnOK.setBounds(248, 162, 106, 35);
		panel.add(btnOK);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					close();
				}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelar.setBounds(132, 162, 106, 35);
		panel.add(btnCancelar);
	}
}
