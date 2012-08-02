package puf.m2.basket.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

import puf.m2.basket.model.entity.Team;
import puf.m2.basket.model.support.BasketException;
import puf.m2.basket.model.support.Condition;
import puf.m2.basket.model.support.EntityUtils;

public class TeamView extends JPanel implements ActionListener {

	private static final long serialVersionUID = -580791303394022630L;

	// Variables declaration - do not modify
	Team team;
	FormState formState;
	boolean pressUpdate = false;

	private JButton btnCancel;
	private JButton btnDelete;
	private JButton btnFind;
	private JButton btnNew;
	private JButton btnSave;
	private JButton btnUpdate;
	private JButton jButton2;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JTextField txtTeamID;
	private JTextField txtTeamName;

	public TeamView() {
		initComponents();
		addActionListeners();

		team = new Team();
		formState = FormState.INITIAL;
		updateForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Cancel".equals(e.getActionCommand())) {
			formState = FormState.INITIAL;

		} else if ("Delete".equals(e.getActionCommand())) {
			String teamToDelete = null;

			if (!isInteger(txtTeamID.getText())) {
				JOptionPane.showMessageDialog(this,
						"Please input correct team ID", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

			try {
				team = makeTeam();
				teamToDelete = EntityUtils.loadById(team.getId(), String.class);
			} catch (BasketException | SQLException e1) {
				e1.printStackTrace();
			}
			if (teamToDelete == null) {
				JOptionPane.showMessageDialog(this,
						"This team ID does not exists, can not delete",
						"Error", JOptionPane.ERROR_MESSAGE);
			} else if (JOptionPane.showConfirmDialog(this,
					"Do you really to delete this team?",
					"Confirm to delete team", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				// Delete this team
				deleteTeam();
				JOptionPane
						.showMessageDialog(this, "This president is deleted");
			}
			formState = FormState.INITIAL;

		} else if ("Find".equals(e.getActionCommand())) {
			String teamName = (String) JOptionPane.showInputDialog(this,
					"Please give a name of team", "Team 1");

			if (teamName == null)
				return;

			teamName = teamName.toUpperCase();
			// Find team with that name
			List<Team> teams = null;
			try {
				teams = EntityUtils.loadByCondition(new Condition("TEAM_NAME",
						teamName), Team.class, "Team_Name");
			} catch (BasketException e1) {
				e1.printStackTrace();
			}
			// If exist team, show its information
			if (teams.size() > 0) {
				team = teams.get(0);
				setTextField();
				JOptionPane.showMessageDialog(this, "Team founded", "Notice",
						JOptionPane.INFORMATION_MESSAGE);
				formState = FormState.FIND;
			} else {
				// If not exist team, show error message
				JOptionPane.showMessageDialog(this, "Can not found that team",
						"Error", JOptionPane.ERROR_MESSAGE);
				formState = FormState.INITIAL;
			}

		} else if ("New".equals(e.getActionCommand())) {
			pressUpdate = false;
			formState = FormState.NEW;

		} else if ("Save".equals(e.getActionCommand())) {
			if (isEmptyData()) {
				JOptionPane.showMessageDialog(this,
						"Please give enought information of team", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (isTypeMismatch()) {
				JOptionPane.showMessageDialog(this,
						"Please give correct type in each text field", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				if (!pressUpdate) {
					team = makeTeam();
					if (isDuplicateData(team)) {
						JOptionPane
								.showMessageDialog(
										this,
										"Can not insert new team which is duplicate ID/ Name with existing team",
										"Error", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						// Save new team
						saveTeam(team);
						JOptionPane.showMessageDialog(this,
								"Save new team successful", "Success",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					// Update existing team
					team = makeTeam();
					updateTeam();
					JOptionPane.showMessageDialog(this,
							"Update team successful", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
			formState = FormState.INITIAL;

		} else if ("Update".equals(e.getActionCommand())) {
			pressUpdate = true;
			formState = FormState.NEW;
		}

		// Finally for each button
		updateForm();
	}

	private void deleteTeam() {
		try {
			team.setDeleted(1);
			team.update();
		} catch (SQLException | BasketException e) {
			e.printStackTrace();
		}

	}

	private void addActionListeners() {
		btnCancel.setActionCommand("Cancel");
		btnCancel.addActionListener(this);

		btnDelete.setActionCommand("Delete");
		btnDelete.addActionListener(this);

		btnFind.setActionCommand("Find");
		btnFind.addActionListener(this);

		btnNew.setActionCommand("New");
		btnNew.addActionListener(this);

		btnSave.setActionCommand("Save");
		btnSave.addActionListener(this);

		btnUpdate.setActionCommand("Update");
		btnUpdate.addActionListener(this);
	}

	private void initComponents() {

		jButton2 = new JButton();
		txtTeamID = new JTextField();
		jLabel1 = new JLabel();
		txtTeamName = new JTextField();
		jLabel2 = new JLabel();
		btnNew = new JButton();
		btnFind = new JButton();
		btnSave = new JButton();
		btnCancel = new JButton();
		btnUpdate = new JButton();
		btnDelete = new JButton();

		jButton2.setText("jButton2");

		jLabel1.setText("Team ID");

		jLabel2.setText("Team name");

		btnNew.setText("New");
		btnNew.setToolTipText("Add new office");

		btnFind.setText("Find");
		btnFind.setToolTipText("Find an existing office");

		btnSave.setText("Save");
		btnSave.setToolTipText("Save new office");

		btnCancel.setText("Cancel");

		btnUpdate.setText("Update");

		btnDelete.setText("Delete");

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addContainerGap()
																.addComponent(
																		btnNew,
																		GroupLayout.PREFERRED_SIZE,
																		76,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		btnFind,
																		GroupLayout.PREFERRED_SIZE,
																		78,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		btnCancel,
																		GroupLayout.PREFERRED_SIZE,
																		78,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		btnSave,
																		GroupLayout.PREFERRED_SIZE,
																		76,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		btnUpdate,
																		GroupLayout.PREFERRED_SIZE,
																		78,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		btnDelete,
																		GroupLayout.PREFERRED_SIZE,
																		82,
																		GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(19, 19,
																		19)
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jLabel1)
																				.addComponent(
																						jLabel2))
																.addGap(23, 23,
																		23)
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.LEADING)
																				.addComponent(
																						txtTeamName,
																						GroupLayout.PREFERRED_SIZE,
																						234,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						txtTeamID,
																						GroupLayout.PREFERRED_SIZE,
																						91,
																						GroupLayout.PREFERRED_SIZE))))
								.addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
												.addComponent(
														txtTeamID,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel1))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel2)
												.addComponent(
														txtTeamName,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
												.addComponent(btnNew)
												.addComponent(btnFind)
												.addComponent(btnCancel)
												.addComponent(btnUpdate)
												.addComponent(btnDelete)
												.addComponent(btnSave))
								.addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));

		layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {
				txtTeamID, txtTeamName });

	}// </editor-fold>

	private boolean isDuplicateData(Team team) {
		List<String> teams = null;

		// check if duplicate team ID
		try {
			teams = EntityUtils.loadByCondition(new Condition("id", team
					.getId().toString()), String.class, "id");
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
		if (teams.size() > 0)
			return true;

		// Check if duplicate team name
		teams = null;
		try {
			teams = EntityUtils.loadByCondition(
					new Condition("Team_name", team.getTeamName()), String.class,
					"Team_name");
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
		if (teams.size() > 0)
			return true;

		return false;

	}

	private boolean isEmptyData() {
		if (txtTeamID.getText().equals("") || txtTeamName.getText().equals(""))
			return true;
		return false;
	}

	public boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	private boolean isTypeMismatch() {
		if (!isInteger(txtTeamID.getText()))
			return true;
		return false;
	}

	private Team makeTeam() {
		setFieldtoAttribute();
		try {
			team.setDeleted(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return team;
	}

	private void saveTeam(Team team) {
		setFieldtoAttribute();
		try {
			team.setDeleted(0);
			team.save();
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void setFieldtoAttribute() {
		try {
			team.setId(Integer.parseInt(txtTeamID.getText().trim()));
			team.setTeamName(txtTeamName.getText().trim());
		} catch (NumberFormatException | SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void setTextField() {
		try {
			txtTeamID.setText(team.getId().toString());
			txtTeamName.setText(team.getTeamName());
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private void updateForm() {
		switch (formState) {
		case INITIAL:
			btnCancel.setVisible(false);
			btnSave.setVisible(false);
			btnUpdate.setVisible(false);
			btnDelete.setVisible(false);

			btnNew.setVisible(true);
			btnFind.setVisible(true);
			break;
		case NEW:
			btnNew.setVisible(false);
			btnFind.setVisible(false);
			btnUpdate.setVisible(false);
			btnDelete.setVisible(false);

			btnSave.setVisible(true);
			btnCancel.setVisible(true);
			break;
		case FIND:
			btnSave.setVisible(false);

			btnCancel.setVisible(true);
			btnNew.setVisible(true);
			btnFind.setVisible(true);
			btnUpdate.setVisible(true);
			btnDelete.setVisible(true);
			break;
		}
	}

	private void updateTeam() {
		setFieldtoAttribute();
		try {
			team.setDeleted(0);
			team.update();
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
	}
}
