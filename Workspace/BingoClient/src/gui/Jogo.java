package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Jogo {

	private JFrame frmJogo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jogo window = new Jogo();
					window.frmJogo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Jogo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJogo = new JFrame();
		frmJogo.setTitle("Jogo");
		frmJogo.setBounds(100, 100, 390, 500);
		frmJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		
		JButton button_0 = new JButton("0");
		button_0.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton button_1 = new JButton("0");
		button_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton button_2 = new JButton("0");
		button_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton button_3 = new JButton("0");
		button_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton button_4 = new JButton("0");
		button_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton button_5 = new JButton("0");
		button_5.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton button_6 = new JButton("0");
		button_6.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton button_7 = new JButton("0");
		button_7.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton button_8 = new JButton("0");
		button_8.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton button_9 = new JButton("0");
		button_9.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton button_10 = new JButton("0");
		button_10.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton button_11 = new JButton("0");
		button_11.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton btnBingo = new JButton("BINGO");
		btnBingo.setFont(new Font("Trebuchet MS", Font.BOLD, 10));
		btnBingo.setForeground(Color.MAGENTA);
		
		JButton button_12 = new JButton("0");
		button_12.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton button_13 = new JButton("0");
		button_13.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton button_14 = new JButton("0");
		button_14.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton button_19 = new JButton("0");
		button_19.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton button_15 = new JButton("0");
		button_15.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton button_16 = new JButton("0");
		button_16.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton button_17 = new JButton("0");
		button_17.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton button_18 = new JButton("0");
		button_18.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton button_20 = new JButton("0");
		button_20.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton button_21 = new JButton("0");
		button_21.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton button_22 = new JButton("0");
		button_22.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton button_23 = new JButton("0");
		button_23.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblNumerosorteado = new JLabel("Numero sorteado:");
		lblNumerosorteado.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumerosorteado.setFont(new Font("Tahoma", Font.BOLD, 18));
		GroupLayout groupLayout = new GroupLayout(frmJogo.getContentPane());
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
							.addComponent(lblNumerosorteado, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
					.addComponent(lblNumerosorteado)
					.addContainerGap(37, Short.MAX_VALUE))
		);
		frmJogo.getContentPane().setLayout(groupLayout);
	}
}
