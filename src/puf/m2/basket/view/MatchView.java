/*
 * and open the template in the editor.
 */
package puf.m2.basket.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListModel;

import puf.m2.basket.model.entity.Match;
import puf.m2.basket.model.entity.Player;
import puf.m2.basket.model.entity.ScoreDetail;
import puf.m2.basket.model.entity.Season;
import puf.m2.basket.model.entity.Team;
import puf.m2.basket.model.entity.ref.SeasonRef;
import puf.m2.basket.model.support.BasketException;
import puf.m2.basket.model.support.Condition;
import puf.m2.basket.model.support.EntityUtils;

import com.toedter.calendar.JDateChooser;

public class MatchView extends JPanel implements ActionListener {
	private static final long serialVersionUID = -2023012005217232748L;

	// Variables declaration - do not modify
	ArrayList<ScoreDetail> arrScoreDetail;
	FormState formState;
	ListModel<ScoreDetail> lstModelScoreDetail;
	Match match;
	Timestamp matchDate;

	// Class attribute
	int matchID;
	boolean pressUpdate = false;
	ScoreDetail scoreDetail;
	Season season;

	private javax.swing.JButton btnAddDetails;
	private javax.swing.JButton btnCancel;
	private javax.swing.JButton btnDelete;
	private javax.swing.JButton btnFind;
	private javax.swing.JButton btnNew;
	private javax.swing.JButton btnSave;
	private javax.swing.JButton btnSetTeams;
	private javax.swing.JButton btnUpdate;
	private javax.swing.JComboBox<Player> cboPlayer;
	private javax.swing.JComboBox<Season> cboSeason;
	private javax.swing.JComboBox<Team> cboTeam;
	private javax.swing.JComboBox<Team> cboTeam1;
	private javax.swing.JComboBox<Team> cboTeam2;
	private javax.swing.JButton jButton2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JList<ScoreDetail> lstDetails;
	private JDateChooser txtMatchDate;
	private javax.swing.JTextField txtMatchID;
	private javax.swing.JTextField txtPoint;

	// End of variables declaration

	public MatchView() {
		initComponents();
		addActionListeners();

		match = new Match();

		lstModelScoreDetail = new DefaultListModel<ScoreDetail>();
		arrScoreDetail = new ArrayList<ScoreDetail>();

		formState = FormState.INITIAL;
		fillComboSeason();
		fillTwoComboTeam();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Cancel".equals(e.getActionCommand())) {
			formState = FormState.INITIAL;
		} else if ("Delete".equals(e.getActionCommand())) {
			Match matchToDelete = null;

			if (!isInteger(txtMatchID.getText())) {
				JOptionPane.showMessageDialog(this,
						"Please input correct match ID", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			try {
				match = makeMatch();
				matchToDelete = EntityUtils
						.loadById(match.getId(), Match.class);
			} catch (BasketException | SQLException e1) {
				e1.printStackTrace();
			}
			if (matchToDelete == null) {
				JOptionPane.showMessageDialog(this,
						"This match ID does not exists, can not delete",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (JOptionPane.showConfirmDialog(this,
					"Do you really to delete this match?",
					"Confirm to delete match", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				// Delete this season
				deleteMatch();
				JOptionPane.showMessageDialog(this, "This match is deleted");
			}
			formState = FormState.INITIAL;

		} else if ("Find".equals(e.getActionCommand())) {
			formState = FormState.FIND;
			String matchId = JOptionPane.showInputDialog(this,
					"Please give an ID of match", "1");

			// Find match with that id
			List<Match> matches = null;
			try {
				matches = EntityUtils.loadByCondition(new Condition("id",
						matchId), Match.class, "id");
			} catch (BasketException e1) {
				e1.printStackTrace();
			}

			// If exist match, show its information
			if (matches.size() > 0) {
				match = matches.get(0);
				setTextField();
				JOptionPane.showMessageDialog(this, "Match is founded",
						"Notice", JOptionPane.INFORMATION_MESSAGE);
				formState = FormState.FIND;
			} else {
				// If not exist match, show error message
				JOptionPane.showMessageDialog(this, "Can not found that match",
						"Error", JOptionPane.ERROR_MESSAGE);
				formState = FormState.INITIAL;
			}

		} else if ("New".equals(e.getActionCommand())) {
			pressUpdate = false;
			formState = FormState.NEW;

		} else if ("Save".equals(e.getActionCommand())) {
			if (isEmptyData()) {
				JOptionPane.showMessageDialog(this,
						"Please give enought information of match", "Error",
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
					match = makeMatch();
					if (isDuplicateID()) {
						JOptionPane
						.showMessageDialog(
								this,
								"Can not insert new match which is duplicate ID with existing match",
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						// Save new match
						saveMatch();
						JOptionPane.showMessageDialog(this,
								"Save new match successful", "Success",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					// Update existing match
					match = makeMatch();
					updateMatch();
					JOptionPane.showMessageDialog(this,
							"Update match successful", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				}
				formState = FormState.INITIAL;

			}
		} else if ("Update".equals(e.getActionCommand())) {
			pressUpdate = true;
			formState = FormState.NEW;
		} else if ("AddTeams".equals(e.getActionCommand())) {
			if (isDuplicateTeams()) {
				JOptionPane.showMessageDialog(this,
						"Please choose team 1 is difference with team 2",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			addComboTeam();
		} else if ("AddDetails".equals(e.getActionCommand())) {
			addDetailsToList();
		} else if ("ChangeTeam".equals(e.getActionCommand())) {
			addComboPlayer();
		}

		// Finally for each button
		updateForm();

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

		btnSetTeams.setActionCommand("AddTeams");
		btnSetTeams.addActionListener(this);

		btnAddDetails.setActionCommand("AddDetails");
		btnAddDetails.addActionListener(this);

		cboTeam.setActionCommand("ChangeTeam");
		cboTeam.addActionListener(this);
	}

	private void addComboPlayer() {
		// Remove exists player in combo box
		cboPlayer.removeAllItems();

		List<Player> players;
		try {
			players = EntityUtils.loadByCondition(null, Player.class, null);
			for (Player player : players) {
				cboPlayer.addItem(player);
			}
		} catch (BasketException e) {
			e.printStackTrace();
		}
	}

	private void addComboTeam() {
		Team team = null;
		cboTeam.removeAllItems();

		team = (Team) cboTeam1.getSelectedItem();
		try {
			System.out.println(team.getTeamName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cboTeam.addItem(team);

		team = null;
		team = (Team) cboTeam2.getSelectedItem();
		try {
			System.out.println(team.getTeamName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cboTeam.addItem(team);
	}

	private void addDetailsToList() {
		Player player = (Player) cboPlayer.getSelectedItem();
		Team team = (Team) cboTeam.getSelectedItem();

		if (!isInteger(txtPoint.getText())) {
			JOptionPane.showMessageDialog(this, "Match point must be a number",
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		int value = Integer.parseInt(txtPoint.getText());

		ScoreDetail scoreDetail = new ScoreDetail();
		try {
			scoreDetail.setId(0);
			scoreDetail.setPlayer(player.getRef());
			scoreDetail.setTeam(team.getRef());
			scoreDetail.setValue(value);
		} catch (SQLException | BasketException e) {
			e.printStackTrace();
		}

		// Store score details of match
		arrScoreDetail.add(scoreDetail);

		((DefaultListModel<ScoreDetail>) lstModelScoreDetail)
		.addElement(scoreDetail);
		lstDetails.setModel(lstModelScoreDetail);
	}

	private void deleteMatch() {
		try {
			match.setDeleted(1);
			match.update();
		} catch (SQLException | BasketException e) {
			e.printStackTrace();
		}
	}

	private void fillComboSeason() {
		try {
			List<Season> seasons = EntityUtils.loadByCondition(null,
					Season.class, null);
			if (seasons.size() <= 0) {
				JOptionPane
				.showMessageDialog(
						this,
						"There are no season in this system \n Please add season first",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			cboSeason.removeAll();
			for (Season season : seasons) {
				cboSeason.addItem(season);
			}
		} catch (BasketException e) {
			e.printStackTrace();
		}
	}

	private void fillComboSeason(Season season) {
		cboSeason.addItem(season);
	}

	private void fillComboTeams() {
		cboTeam1.removeAll();
		try {
			cboTeam1.addItem(match.getTeam1().getValue());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		cboTeam2.removeAll();
		try {
			cboTeam2.addItem(match.getTeam1().getValue());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void fillTwoComboTeam() {
		try {
			List<Team> teams = EntityUtils.loadByCondition(null, Team.class,
					null);
			if (teams.size() <= 0) {
				JOptionPane
				.showMessageDialog(
						this,
						"There are no team in this system \n Please add team first",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			cboTeam1.removeAll();
			cboTeam2.removeAll();
			for (Team team : teams) {
				cboTeam1.addItem(team);
				cboTeam2.addItem(team);
			}
		} catch (BasketException e) {
			e.printStackTrace();
		}
	}

	private void initComponents() {

		jButton2 = new javax.swing.JButton();
		txtMatchID = new javax.swing.JTextField();
		jLabel1 = new javax.swing.JLabel();
		txtMatchDate = new JDateChooser();
		jLabel2 = new javax.swing.JLabel();
		btnNew = new javax.swing.JButton();
		btnFind = new javax.swing.JButton();
		btnSave = new javax.swing.JButton();
		btnCancel = new javax.swing.JButton();
		btnUpdate = new javax.swing.JButton();
		btnDelete = new javax.swing.JButton();
		jLabel3 = new javax.swing.JLabel();
		cboSeason = new javax.swing.JComboBox<Season>();
		jLabel4 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		cboTeam = new javax.swing.JComboBox<Team>();
		cboPlayer = new javax.swing.JComboBox<Player>();
		txtPoint = new javax.swing.JTextField();
		jScrollPane1 = new javax.swing.JScrollPane();
		lstDetails = new javax.swing.JList<ScoreDetail>();
		btnAddDetails = new javax.swing.JButton();
		jLabel9 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		cboTeam1 = new javax.swing.JComboBox<Team>();
		cboTeam2 = new javax.swing.JComboBox<Team>();
		btnSetTeams = new javax.swing.JButton();

		jButton2.setText("jButton2");

		jLabel1.setText("Match ID");

		jLabel2.setText("Match Date");

		btnNew.setText("New");
		btnNew.setToolTipText("Add new office");

		btnFind.setText("Find");
		btnFind.setToolTipText("Find an existing office");

		btnSave.setText("Save");
		btnSave.setToolTipText("Save new office");

		btnCancel.setText("Cancel");

		btnUpdate.setText("Update");

		btnDelete.setText("Delete");

		jLabel3.setText("Season");

		jLabel4.setText("Details");

		jLabel6.setText("Player");

		jLabel7.setText("Point(s)");

		jScrollPane1.setViewportView(lstDetails);

		btnAddDetails.setText("Add");

		jLabel9.setText("Team");

		jLabel5.setText("Team 1");

		jLabel8.setText("Team 2");

		btnSetTeams.setText("Set teams of match");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
						.addGroup(
								layout.createParallelGroup(
										javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												layout.createSequentialGroup()
												.addGap(20, 20,
														20)
														.addGroup(
																layout.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																		.addGroup(
																				layout.createSequentialGroup()
																				.addGroup(
																						layout.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																								.addGroup(
																										layout.createSequentialGroup()
																										.addGap(45,
																												45,
																												45)
																												.addComponent(
																														jLabel9))
																														.addGroup(
																																layout.createSequentialGroup()
																																.addGap(3,
																																		3,
																																		3)
																																		.addComponent(
																																				jLabel4)))
																																				.addGap(66,
																																						66,
																																						66)
																																						.addComponent(
																																								jLabel6)
																																								.addGap(69,
																																										69,
																																										69)
																																										.addComponent(
																																												jLabel7))
																																												.addGroup(
																																														javax.swing.GroupLayout.Alignment.TRAILING,
																																														layout.createParallelGroup(
																																																javax.swing.GroupLayout.Alignment.LEADING,
																																																false)
																																																.addComponent(
																																																		jScrollPane1)
																																																		.addGroup(
																																																				layout.createSequentialGroup()
																																																				.addComponent(
																																																						cboTeam,
																																																						javax.swing.GroupLayout.PREFERRED_SIZE,
																																																						82,
																																																						javax.swing.GroupLayout.PREFERRED_SIZE)
																																																						.addPreferredGap(
																																																								javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																																								.addComponent(
																																																										cboPlayer,
																																																										javax.swing.GroupLayout.PREFERRED_SIZE,
																																																										100,
																																																										javax.swing.GroupLayout.PREFERRED_SIZE)
																																																										.addPreferredGap(
																																																												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																																												.addComponent(
																																																														txtPoint,
																																																														javax.swing.GroupLayout.PREFERRED_SIZE,
																																																														76,
																																																														javax.swing.GroupLayout.PREFERRED_SIZE)
																																																														.addPreferredGap(
																																																																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																																																.addComponent(
																																																																		btnAddDetails)))))
																																																																		.addGroup(
																																																																				layout.createSequentialGroup()
																																																																				.addComponent(
																																																																						btnNew,
																																																																						javax.swing.GroupLayout.PREFERRED_SIZE,
																																																																						76,
																																																																						javax.swing.GroupLayout.PREFERRED_SIZE)
																																																																						.addPreferredGap(
																																																																								javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																																																								.addComponent(
																																																																										btnFind,
																																																																										javax.swing.GroupLayout.PREFERRED_SIZE,
																																																																										78,
																																																																										javax.swing.GroupLayout.PREFERRED_SIZE)
																																																																										.addGap(0, 0, 0)
																																																																										.addComponent(
																																																																												btnCancel,
																																																																												javax.swing.GroupLayout.PREFERRED_SIZE,
																																																																												78,
																																																																												javax.swing.GroupLayout.PREFERRED_SIZE)
																																																																												.addPreferredGap(
																																																																														javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																																																														.addComponent(
																																																																																btnSave,
																																																																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																																																																76,
																																																																																javax.swing.GroupLayout.PREFERRED_SIZE)
																																																																																.addPreferredGap(
																																																																																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																																																																		.addComponent(
																																																																																				btnUpdate,
																																																																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																																																																				78,
																																																																																				javax.swing.GroupLayout.PREFERRED_SIZE)
																																																																																				.addPreferredGap(
																																																																																						javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																																																																						.addComponent(
																																																																																								btnDelete,
																																																																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																																																																								82,
																																																																																								javax.swing.GroupLayout.PREFERRED_SIZE))
																																																																																								.addGroup(
																																																																																										layout.createSequentialGroup()
																																																																																										.addGap(19, 19,
																																																																																												19)
																																																																																												.addGroup(
																																																																																														layout.createParallelGroup(
																																																																																																javax.swing.GroupLayout.Alignment.LEADING)
																																																																																																.addComponent(
																																																																																																		jLabel1)
																																																																																																		.addComponent(
																																																																																																				jLabel2)
																																																																																																				.addComponent(
																																																																																																						jLabel3)
																																																																																																						.addComponent(
																																																																																																								jLabel5)
																																																																																																								.addComponent(
																																																																																																										jLabel8))
																																																																																																										.addPreferredGap(
																																																																																																												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																																																																																												.addGroup(
																																																																																																														layout.createParallelGroup(
																																																																																																																javax.swing.GroupLayout.Alignment.LEADING)
																																																																																																																.addComponent(
																																																																																																																		txtMatchDate,
																																																																																																																		javax.swing.GroupLayout.PREFERRED_SIZE,
																																																																																																																		234,
																																																																																																																		javax.swing.GroupLayout.PREFERRED_SIZE)
																																																																																																																		.addComponent(
																																																																																																																				txtMatchID,
																																																																																																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																																																																																																				91,
																																																																																																																				javax.swing.GroupLayout.PREFERRED_SIZE)
																																																																																																																				.addComponent(
																																																																																																																						cboSeason,
																																																																																																																						javax.swing.GroupLayout.PREFERRED_SIZE,
																																																																																																																						93,
																																																																																																																						javax.swing.GroupLayout.PREFERRED_SIZE)
																																																																																																																						.addComponent(
																																																																																																																								cboTeam1,
																																																																																																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																																																																																																								155,
																																																																																																																								javax.swing.GroupLayout.PREFERRED_SIZE)
																																																																																																																								.addComponent(
																																																																																																																										cboTeam2,
																																																																																																																										javax.swing.GroupLayout.PREFERRED_SIZE,
																																																																																																																										155,
																																																																																																																										javax.swing.GroupLayout.PREFERRED_SIZE))))
																																																																																																																										.addContainerGap(34, Short.MAX_VALUE))
																																																																																																																										.addGroup(
																																																																																																																												layout.createSequentialGroup().addGap(100, 100, 100)
																																																																																																																												.addComponent(btnSetTeams).addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
						.addGroup(
								layout.createParallelGroup(
										javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												layout.createSequentialGroup()
												.addContainerGap()
												.addGroup(
														layout.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(
																		txtMatchID,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				jLabel1))
																				.addPreferredGap(
																						javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(
																								txtMatchDate,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addGroup(
																												layout.createParallelGroup(
																														javax.swing.GroupLayout.Alignment.BASELINE)
																														.addComponent(
																																cboSeason,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addComponent(
																																		jLabel3)))
																																		.addGroup(
																																				layout.createSequentialGroup()
																																				.addGap(39, 39,
																																						39)
																																						.addComponent(
																																								jLabel2)))
																																								.addGap(18, 18, 18)
																																								.addGroup(
																																										layout.createParallelGroup(
																																												javax.swing.GroupLayout.Alignment.BASELINE)
																																												.addComponent(jLabel5)
																																												.addComponent(
																																														cboTeam1,
																																														javax.swing.GroupLayout.PREFERRED_SIZE,
																																														javax.swing.GroupLayout.DEFAULT_SIZE,
																																														javax.swing.GroupLayout.PREFERRED_SIZE))
																																														.addPreferredGap(
																																																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																																.addGroup(
																																																		layout.createParallelGroup(
																																																				javax.swing.GroupLayout.Alignment.BASELINE)
																																																				.addComponent(
																																																						cboTeam2,
																																																						javax.swing.GroupLayout.PREFERRED_SIZE,
																																																						javax.swing.GroupLayout.DEFAULT_SIZE,
																																																						javax.swing.GroupLayout.PREFERRED_SIZE)
																																																						.addComponent(jLabel8))
																																																						.addGroup(
																																																								layout.createParallelGroup(
																																																										javax.swing.GroupLayout.Alignment.LEADING)
																																																										.addGroup(
																																																												layout.createSequentialGroup()
																																																												.addGap(0,
																																																														0,
																																																														Short.MAX_VALUE)
																																																														.addComponent(
																																																																jLabel4))
																																																																.addGroup(
																																																																		layout.createSequentialGroup()
																																																																		.addGap(18, 18,
																																																																				18)
																																																																				.addComponent(
																																																																						btnSetTeams)
																																																																						.addGap(0,
																																																																								16,
																																																																								Short.MAX_VALUE)))
																																																																								.addPreferredGap(
																																																																										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																																																										.addGroup(
																																																																												layout.createParallelGroup(
																																																																														javax.swing.GroupLayout.Alignment.BASELINE)
																																																																														.addComponent(jLabel6)
																																																																														.addComponent(jLabel7)
																																																																														.addComponent(jLabel9))
																																																																														.addPreferredGap(
																																																																																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																																																																.addGroup(
																																																																																		layout.createParallelGroup(
																																																																																				javax.swing.GroupLayout.Alignment.BASELINE)
																																																																																				.addComponent(
																																																																																						cboTeam,
																																																																																						javax.swing.GroupLayout.PREFERRED_SIZE,
																																																																																						javax.swing.GroupLayout.DEFAULT_SIZE,
																																																																																						javax.swing.GroupLayout.PREFERRED_SIZE)
																																																																																						.addComponent(
																																																																																								cboPlayer,
																																																																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																																																																								javax.swing.GroupLayout.DEFAULT_SIZE,
																																																																																								javax.swing.GroupLayout.PREFERRED_SIZE)
																																																																																								.addComponent(
																																																																																										txtPoint,
																																																																																										javax.swing.GroupLayout.PREFERRED_SIZE,
																																																																																										javax.swing.GroupLayout.DEFAULT_SIZE,
																																																																																										javax.swing.GroupLayout.PREFERRED_SIZE)
																																																																																										.addComponent(btnAddDetails))
																																																																																										.addPreferredGap(
																																																																																												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																																																																												.addComponent(jScrollPane1,
																																																																																														javax.swing.GroupLayout.PREFERRED_SIZE,
																																																																																														javax.swing.GroupLayout.DEFAULT_SIZE,
																																																																																														javax.swing.GroupLayout.PREFERRED_SIZE)
																																																																																														.addGap(18, 18, 18)
																																																																																														.addGroup(
																																																																																																layout.createParallelGroup(
																																																																																																		javax.swing.GroupLayout.Alignment.BASELINE)
																																																																																																		.addComponent(btnNew)
																																																																																																		.addComponent(btnFind)
																																																																																																		.addComponent(btnCancel)
																																																																																																		.addComponent(btnUpdate)
																																																																																																		.addComponent(btnDelete)
																																																																																																		.addComponent(btnSave))
																																																																																																		.addGap(20, 20, 20)));

		layout.linkSize(javax.swing.SwingConstants.VERTICAL,
				new java.awt.Component[] { txtMatchDate, txtMatchID });

	}// </editor-fold>

	private boolean isDuplicateID() {
		List<Match> matches = null;

		// check if duplicate match ID
		try {
			matches = EntityUtils.loadByCondition(new Condition("id", match
					.getId().toString()), Match.class, "id");
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
		if (matches.size() > 0)
			return true;

		return false;
	}

	public boolean isDuplicateTeams() {
		boolean result = false;
		try {
			if (((Team) cboTeam1.getSelectedItem()).getId() == ((Team) cboTeam2
					.getSelectedItem()).getId())
				result = true;
			else
				result = false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private boolean isEmptyData() {
		if (txtMatchID.getText().equals(""))
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
		if (!isInteger(txtMatchID.getText()))
			return true;
		return false;
	}

	private Match makeMatch() {
		setFieldtoAttribute();
		try {
			match.setDeleted(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return match;
	}

	private void saveMatch() {
		setFieldtoAttribute();
		try {
			match.setDeleted(0);
			match.save();
		} catch (SQLException | BasketException e) {
			e.printStackTrace();
		}
	}

	private void setFieldtoAttribute() {
		try {
			match.setId(Integer.parseInt(txtMatchID.getText()));
			match.setMatchDate(new Timestamp(txtMatchDate.getDate().getTime()));
			match.setSeason(EntityUtils.loadById(
					((Season) cboSeason.getSelectedItem()).getId(),
					Season.class).getRef(SeasonRef.class));

			match.setTeam1(cboTeam1.getItemAt(0).getRef());
			match.setTeam2(cboTeam1.getItemAt(0).getRef());

			for (ScoreDetail scoreDetail : arrScoreDetail) {
				match.addScoreDetail(scoreDetail);
			}

		} catch (NumberFormatException | SQLException | BasketException e) {
			e.printStackTrace();
		}
	}

	private void setTextField() {
		try {
			txtMatchID.setText(match.getId().toString());
			txtMatchDate.setDate(new Date(match.getMatchDate().getTime()));

			// Update combo box Season when change matchID
			fillComboSeason(match.getSeason().getValue());

			// Update combo box Team 1 and Team 2
			fillComboTeams();

			// Update list of score detail
			fillListDetails();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void fillListDetails() {
		// Create a list that allows adds and removes
		DefaultListModel<ScoreDetail> model = new DefaultListModel<ScoreDetail>();
		int i=0;

		ScoreDetail scoreDetails[];
		try {
			scoreDetails = match.getDetails().getArray();
			for (ScoreDetail scoreDetail : scoreDetails){
				model.add(i++, scoreDetail);
			}
			lstDetails.setModel(model);		
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

	private void updateMatch() {
		setFieldtoAttribute();
		try {
			match.setDeleted(0);
			match.update();
		} catch (SQLException | BasketException e) {
			e.printStackTrace();
		}
	}
}
