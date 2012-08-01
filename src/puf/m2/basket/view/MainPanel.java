/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package puf.m2.basket.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainPanel extends javax.swing.JPanel implements ActionListener {

	private static final long serialVersionUID = 7888031775039917448L;

	// Variables declaration - do not modify
	private JButton btnClubPlayer;
	private JButton btnManageCategory;
	private JButton btnManageClub;
	private JButton btnManageMatch;
	private JButton btnManageOffice;
	private JButton btnManagePerson;
	private JButton btnManageSeason;
	private JButton btnManageTeam;
	private JButton btnTeamCoach;

	// End of variables declaration

	public MainPanel() {
		initComponents();
		addActionListeners();
	}

	private void addActionListeners() {
		btnManageCategory.setActionCommand("ManageCategory");
		btnManageCategory.addActionListener(this);

		btnManageClub.setActionCommand("ManageClub");
		btnManageCategory.addActionListener(this);

		btnManageMatch.setActionCommand("ManageMatch");
		btnManageMatch.addActionListener(this);

		btnManageOffice.setActionCommand("ManageOffice");
		btnManageOffice.addActionListener(this);

		btnManagePerson.setActionCommand("ManagePerson");
		btnManagePerson.addActionListener(this);

		btnManageSeason.setActionCommand("ManageSeason");
		btnManageSeason.addActionListener(this);

		btnManageTeam.setActionCommand("ManageTeam");
		btnManageTeam.addActionListener(this);

		btnClubPlayer.setActionCommand("ClubPlayer");
		btnClubPlayer.addActionListener(this);

		btnTeamCoach.setActionCommand("TeamCoach");
		btnTeamCoach.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("ManageCategory".equals(e.getActionCommand())) {
			Utils.createAndShowGUI(new JFrame("Manage category"),
					new CategoryView());
		} else if ("ManageClub".equals(e.getActionCommand())) {
			Utils.createAndShowGUI(new JFrame("Manage club"), new ClubView());
		} else if ("ManageMatch".equals(e.getActionCommand())) {
			JOptionPane.showMessageDialog(this, "not yet");
		} else if ("ManageOffice".equals(e.getActionCommand())) {
			Utils.createAndShowGUI(new JFrame("Manage office"),
					new OfficeView());
		} else if ("ManagePerson".equals(e.getActionCommand())) {
			Utils.createAndShowGUI(new JFrame("Manage person"),
					new PresidentView());
		} else if ("ManageSeason".equals(e.getActionCommand())) {
			JOptionPane.showMessageDialog(this, "not yet");
		} else if ("ManageTeam".equals(e.getActionCommand())) {
			Utils.createAndShowGUI(new JFrame("Manage team"), new TeamView());
		}
	}

	private void initComponents() {

		btnManageOffice = new JButton();
		btnManagePerson = new JButton();
		btnManageTeam = new JButton();
		btnManageCategory = new JButton();
		btnManageClub = new JButton();
		btnManageSeason = new JButton();
		btnManageMatch = new JButton();
		btnClubPlayer = new JButton();
		btnTeamCoach = new JButton();

		btnManageOffice.setText("Manage offices");

		btnManagePerson.setText("Manage persons");

		btnManageTeam.setText("Manage teams");

		btnManageCategory.setText("Manage categories");

		btnManageClub.setText("Manage clubs");

		btnManageSeason.setText("Manage seasons");

		btnManageMatch.setText("Manage matches");

		btnClubPlayer.setText("Club player");

		btnTeamCoach.setText("Team coach");
		btnTeamCoach.setActionCommand("Team coach");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		btnManageOffice)
																.addGap(18, 18,
																		18)
																.addComponent(
																		btnClubPlayer))
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						btnManagePerson)
																				.addComponent(
																						btnManageTeam)
																				.addComponent(
																						btnManageCategory)
																				.addComponent(
																						btnManageClub)
																				.addComponent(
																						btnManageSeason)
																				.addComponent(
																						btnManageMatch))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		btnTeamCoach)))
								.addContainerGap(172, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(btnManageOffice)
												.addComponent(btnClubPlayer))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(btnTeamCoach)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		btnManagePerson)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		btnManageTeam)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		btnManageCategory)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		btnManageClub)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)

																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		btnManageSeason)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		btnManageMatch)))
								.addContainerGap(40, Short.MAX_VALUE)));
	}// </editor-fold>
}
