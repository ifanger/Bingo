package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import threads.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JanelaCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSenha;
	private JTextField txtConfirma;
	private JTextField txtEmail;
	private JTextField txtNome;
	private ClientInterface janelaLogin;
	private Thread thread;

	public JanelaCadastro(ClientInterface janelaLogin, Cliente thread) {
		this.janelaLogin = janelaLogin;
		this.thread = thread;
		
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
		
		txtSenha = new JTextField();
		txtSenha.setBounds(158, 87, 147, 20);
		panel.add(txtSenha);
		txtSenha.setColumns(10);
		
		JLabel lblConfirmao = new JLabel("Confirma\u00E7\u00E3o:");
		lblConfirmao.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblConfirmao.setBounds(31, 115, 122, 20);
		panel.add(lblConfirmao);
		
		txtConfirma = new JTextField();
		txtConfirma.setColumns(10);
		txtConfirma.setBounds(158, 115, 147, 20);
		panel.add(txtConfirma);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(158, 55, 147, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(158, 22, 147, 20);
		panel.add(txtNome);
		
		
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//thread.mandaPacote("");
				
				String nome = txtNome.getText();
				String email = txtEmail.getText();
				String senha = txtSenha.getText();
				String senha2 = txtConfirma.getText();
				String erro = "";
				
				
				if(nome.length()<3 && nome.length()>30){
					erro = "Nome Inválido!";
				}
				
				else if (!senha.equals(senha2)){
					erro = "A senha não corresponde!";					
				}
				
				else if (nome.length()<3){
					erro = "Senha fraca!";
				}
				
				else if (!email.contains("@")){
					erro = "Email Inválido";
				}
				
				JOptionPane.showMessageDialog(null, "" + erro);
				
				janelaLogin.getBingoClient().setVisible(true);
				JanelaCadastro.this.dispose();
			}
		});
		btnOK.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnOK.setBounds(75, 162, 106, 35);
		panel.add(btnOK);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Volta para a tela de Login
					janelaLogin.getBingoClient().setVisible(true);
					JanelaCadastro.this.dispose();
				}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelar.setBounds(199, 162, 106, 35);
		panel.add(btnCancelar);
	}

	public JTextField getTxtSenha() {
		return txtSenha;
	}

	public void setTxtSenha(JTextField txtSenha) {
		this.txtSenha = txtSenha;
	}

	public JTextField getTxtConfirma() {
		return txtConfirma;
	}

	public void setTxtConfirma(JTextField txtConfirma) {
		this.txtConfirma = txtConfirma;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(JTextField txtEmail) {
		this.txtEmail = txtEmail;
	}

	public JTextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(JTextField txtNome) {
		this.txtNome = txtNome;
	}
}
