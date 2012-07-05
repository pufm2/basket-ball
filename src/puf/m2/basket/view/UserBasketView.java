package puf.m2.basket.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import puf.m2.basket.model.UserBasket;

public class UserBasketView extends javax.swing.JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	// Variables declaration - do not modify
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JPasswordField txtPassword;
	private JTextField txtUsername;

	// End of variables declaration

	private JFrame parent;

	public UserBasketView(JFrame parent) {
		this.parent = parent;
		initComponents();
	}

	public void actionPerformed(ActionEvent e) {
		if ("Login".equals(e.getActionCommand())) {
			String username = txtUsername.getText();
			@SuppressWarnings("deprecation")
			String password = txtPassword.getText();
			requestLogin(username, password);
		}
	}

	private void initComponents() {

		lblUsername = new JLabel();
		lblPassword = new JLabel();
		txtUsername = new JTextField();
		txtPassword = new JPasswordField();
		javax.swing.JButton btnLogin = new javax.swing.JButton();

		lblUsername.setText("Username");

		lblPassword.setText("Password");

		txtPassword.setText("");
		txtPassword.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					requestLogin(txtUsername.getText(), txtPassword.getText());
				}
			}
		});

		btnLogin.setText("Login");
		btnLogin.addActionListener(this);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(btnLogin)
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				false)
																				.addComponent(
																						lblPassword,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						lblUsername,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						txtUsername)
																				.addComponent(
																						txtPassword,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						128,
																						Short.MAX_VALUE))))
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(lblUsername)
												.addComponent(
														txtUsername,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(lblPassword)
												.addComponent(
														txtPassword,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addComponent(btnLogin)
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
	}

	public void requestLogin(String username, String password) {
		UserBasket userBasket = UserBasket.requestLogin(username, password);

		if (userBasket != null) {
			parent.setVisible(false);
			Utils.createAndShowGUI(new JFrame("Manage basket-ball meetings"),
						new MainForm());
		} else {
			JOptionPane.showMessageDialog(parent, "Invalid Credential",
					"Alert", JOptionPane.ERROR_MESSAGE);
		}
	}
}
