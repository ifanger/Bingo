package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import protocol.Cartela;
import protocol.GFProtocol;
import utils.Connection;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameWindow extends JFrame {
	private LoginWindow login;
	private Connection connection;
	private JLabel lbMensagem;
	private JButton button_0;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;
	private JButton button_6;
	private JButton button_7;
	private JButton button_8;
	private JButton button_9;
	private JButton button_10;
	private JButton button_11;
	private JButton button_12;
	private JButton button_13;
	private JButton button_14;
	private JButton button_15;
	private JButton button_16;
	private JButton button_17;
	private JButton button_18;
	private JButton button_19;
	private JButton button_20;
	private JButton button_21;
	private JButton button_22;
	private JButton button_23;
	private JButton btnBingo;
	private ArrayList<JButton> buttons;
	
	/**
	 * Create the application.
	 */
	public GameWindow(LoginWindow janelaLogin, Connection connection) {
		initialize();
		
		this.login = janelaLogin;
		this.connection = connection;
		this.buttons = new ArrayList<>();
		
		this.buttons.add(button_0);
		this.buttons.add(button_1);
		this.buttons.add(button_2);
		this.buttons.add(button_3);
		this.buttons.add(button_4);
		this.buttons.add(button_5);
		this.buttons.add(button_6);
		this.buttons.add(button_7);
		this.buttons.add(button_8);
		this.buttons.add(button_9);
		this.buttons.add(button_10);
		this.buttons.add(button_11);
		this.buttons.add(button_12);
		this.buttons.add(button_13);
		this.buttons.add(button_14);
		this.buttons.add(button_15);
		this.buttons.add(button_16);
		this.buttons.add(button_17);
		this.buttons.add(button_18);
		this.buttons.add(button_19);
		this.buttons.add(button_20);
		this.buttons.add(button_21);
		this.buttons.add(button_22);
		this.buttons.add(button_23);
		
		for(JButton btn : buttons)
		{
			btn.setText("-");
			btn.setEnabled(false);
			btn.setBackground(Color.WHITE);
		}
		
		btnBingo.setEnabled(false);
		btnBingo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection.sendPacket(GFProtocol.BINGO);
			}
		});
	}
	
	public void showMessage(String message)
	{
		this.lbMensagem.setText(message);
	}
	
	public void showMessageBox(String message)
	{
		JOptionPane.showMessageDialog(null, message);
	}
	
	public ArrayList<JButton> getButtons()
	{
		return this.buttons;
	}
	
	public JButton getBingoButton()
	{
		return this.btnBingo;
	}
	
	public void onCartelaReceived(Cartela cartela)
	{
		for(int i = 0; i < 24; i++)
		{
			JButton btn = buttons.get(i);
			btn.setText(cartela.getCartela().get(i) + "");
			btn.setEnabled(true);
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(btn.getBackground() == Color.WHITE)
						btn.setBackground(Color.GRAY);
					else
						btn.setBackground(Color.WHITE);
					
					int n = 0;
					for(JButton btn : buttons)
						if((btn.getBackground() == Color.GRAY))
							n++;
					
					if(n == 24)
						btnBingo.setEnabled(true);
					else
						btnBingo.setEnabled(false);
				}
			});
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("Jogo do Bingo");
		setBounds(100, 100, 390, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnB = new JButton("B");
		btnB.setEnabled(false);
		btnB.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnI = new JButton("I");
		btnI.setEnabled(false);
		btnI.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton btnN = new JButton("N");
		btnN.setEnabled(false);
		btnN.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton btnG = new JButton("G");
		btnG.setEnabled(false);
		btnG.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton btnO = new JButton("O");
		btnO.setEnabled(false);
		btnO.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		button_0 = new JButton("0");
		button_0.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		button_1 = new JButton("0");
		button_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		button_2 = new JButton("0");
		button_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		button_3 = new JButton("0");
		button_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		button_4 = new JButton("0");
		button_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		button_5 = new JButton("0");
		button_5.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		button_6 = new JButton("0");
		button_6.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		button_7 = new JButton("0");
		button_7.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		button_8 = new JButton("0");
		button_8.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		button_9 = new JButton("0");
		button_9.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		button_10 = new JButton("0");
		button_10.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		button_11 = new JButton("0");
		button_11.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		btnBingo = new JButton("BINGO");
		btnBingo.setFont(new Font("Trebuchet MS", Font.BOLD, 10));
		btnBingo.setForeground(Color.MAGENTA);
		
		button_12 = new JButton("0");
		button_12.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		button_13 = new JButton("0");
		button_13.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		button_14 = new JButton("0");
		button_14.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		button_19 = new JButton("0");
		button_19.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		button_15 = new JButton("0");
		button_15.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		button_16 = new JButton("0");
		button_16.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		button_17 = new JButton("0");
		button_17.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		button_18 = new JButton("0");
		button_18.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		button_20 = new JButton("0");
		button_20.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		button_21 = new JButton("0");
		button_21.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		button_22 = new JButton("0");
		button_22.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		button_23 = new JButton("0");
		button_23.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		lbMensagem = new JLabel("Aguardando servidor...");
		lbMensagem.setHorizontalAlignment(SwingConstants.CENTER);
		lbMensagem.setFont(new Font("Tahoma", Font.BOLD, 18));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnB, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnI, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnN, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnG, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnO, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(button_0, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(button_5, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_6, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_7, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_8, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_9, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(button_10, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_11, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnBingo, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_12, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_13, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(button_14, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_15, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_16, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_17, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_18, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(button_19, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_20, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_21, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_22, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_23, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(15, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lbMensagem, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(15))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnO, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnG, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnN, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnI, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnB, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(button_0, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(button_5, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_6, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_7, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_8, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_9, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(button_10, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_11, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBingo, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_12, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_13, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(button_14, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_15, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_16, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_17, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_18, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(button_23, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_22, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_21, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_20, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_19, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lbMensagem)
					.addContainerGap(37, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}
}
