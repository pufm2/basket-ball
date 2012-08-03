/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package puf.m2.basket.view.stat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import puf.m2.basket.model.entity.Category;
import puf.m2.basket.model.entity.Club;
import puf.m2.basket.model.entity.Office;
import puf.m2.basket.model.entity.Team;
import puf.m2.basket.model.support.BasketException;
import puf.m2.basket.model.support.EntityUtils;
import puf.m2.basket.view.support.ViewSupport;

public class AvgDistanceView extends javax.swing.JPanel implements
		ActionListener {

	private static final long serialVersionUID = 8836329768623018088L;

	// Variables declaration - do not modify
	private javax.swing.JButton btnShowDistance;
	private javax.swing.JComboBox<Team> cboTeam1;
	private javax.swing.JComboBox<Team> cboTeam2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel16;
	private javax.swing.JLabel jLabel17;
	private javax.swing.JLabel jLabel18;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JLabel lblLatitude;
	private javax.swing.JLabel lblLatitude1;
	private javax.swing.JLabel lblLatitudeValue;
	private javax.swing.JLabel lblLatitudeValue1;
	private javax.swing.JLabel lblLongitude;
	private javax.swing.JLabel lblLongitude1;
	private javax.swing.JLabel lblLongitudeValue;
	private javax.swing.JLabel lblLongitudeValue1;
	private javax.swing.JLabel lblResult;
	private javax.swing.JTextField txtAddressCity;
	private javax.swing.JTextField txtAddressCity1;
	private javax.swing.JTextField txtAddressDistrict;
	private javax.swing.JTextField txtAddressDistrict1;
	private javax.swing.JTextField txtAddressNumber;
	private javax.swing.JTextField txtAddressNumber1;
	private javax.swing.JTextField txtAddressStreet;
	private javax.swing.JTextField txtAddressStreet1;
	private javax.swing.JTextField txtOfficeID;
	private javax.swing.JTextField txtOfficeID1;
	private javax.swing.JTextField txtOfficeName;
	private javax.swing.JTextField txtOfficeName1;

	// End of variables declaration

	public AvgDistanceView() {
		initComponents();
		lockControls();
		addActionListeners();

		fillTwoComboTeam();
	}

	private void addActionListeners() {
		cboTeam1.setActionCommand("ChangeTeam1");
		cboTeam1.addActionListener(this);

		cboTeam2.setActionCommand("ChangeTeam2");
		cboTeam2.addActionListener(this);

		btnShowDistance.setActionCommand("ShowDistance");
		btnShowDistance.addActionListener(this);
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

	private void fillTwoComboTeam() {
		try {
			List<Team> teams = EntityUtils.loadByCondition(null,
					Team.class, null);
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

	private void lockControls() {
		txtOfficeID.setEditable(false);
		txtOfficeName.setEditable(false);
		txtAddressNumber.setEditable(false);
		txtAddressStreet.setEditable(false);
		txtAddressDistrict.setEditable(false);
		txtAddressCity.setEditable(false);

		txtOfficeID1.setEditable(false);
		txtOfficeName1.setEditable(false);
		txtAddressNumber1.setEditable(false);
		txtAddressStreet1.setEditable(false);
		txtAddressDistrict1.setEditable(false);
		txtAddressCity1.setEditable(false);
	}

	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		cboTeam1 = new javax.swing.JComboBox<Team>();
		cboTeam2 = new javax.swing.JComboBox<Team>();
		jLabel2 = new javax.swing.JLabel();
		btnShowDistance = new javax.swing.JButton();
		jLabel3 = new javax.swing.JLabel();
		txtAddressDistrict = new javax.swing.JTextField();
		lblLatitude = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		lblLongitudeValue = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		lblLatitudeValue = new javax.swing.JLabel();
		txtAddressStreet = new javax.swing.JTextField();
		jLabel5 = new javax.swing.JLabel();
		txtAddressNumber = new javax.swing.JTextField();
		jLabel4 = new javax.swing.JLabel();
		lblLongitude = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		txtOfficeID = new javax.swing.JTextField();
		jLabel9 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		txtOfficeName = new javax.swing.JTextField();
		jLabel11 = new javax.swing.JLabel();
		txtOfficeName1 = new javax.swing.JTextField();
		lblLongitude1 = new javax.swing.JLabel();
		lblLongitudeValue1 = new javax.swing.JLabel();
		jLabel12 = new javax.swing.JLabel();
		jLabel13 = new javax.swing.JLabel();
		jLabel14 = new javax.swing.JLabel();
		jLabel15 = new javax.swing.JLabel();
		txtAddressStreet1 = new javax.swing.JTextField();
		lblLatitudeValue1 = new javax.swing.JLabel();
		txtAddressNumber1 = new javax.swing.JTextField();
		jLabel16 = new javax.swing.JLabel();
		txtOfficeID1 = new javax.swing.JTextField();
		jLabel17 = new javax.swing.JLabel();
		txtAddressDistrict1 = new javax.swing.JTextField();
		jLabel18 = new javax.swing.JLabel();
		lblLatitude1 = new javax.swing.JLabel();
		lblResult = new javax.swing.JLabel();
		txtAddressCity = new javax.swing.JTextField();
		txtAddressCity1 = new javax.swing.JTextField();

		jLabel1.setText("Choose team 1");

		jLabel2.setText("Choose team 2");

		btnShowDistance.setText("Show distance");

		jLabel3.setText("Office of team 1");

		lblLatitude.setText("Latitude");

		jLabel6.setText("District");

		lblLongitudeValue.setText("longitude value");

		jLabel7.setText("City");

		lblLatitudeValue.setText("latitude value");

		jLabel5.setText("Street");

		txtAddressNumber.setName("txtAddressNumber");

		jLabel4.setText("Number");

		lblLongitude.setText("Longitude");

		jLabel8.setText("Office ID");

		jLabel9.setText("Address");

		jLabel10.setText("Office name");

		jLabel11.setText("Office name");

		lblLongitude1.setText("Longitude");

		lblLongitudeValue1.setText("longitude value");

		jLabel12.setText("Number");

		jLabel13.setText("District");

		jLabel14.setText("Office ID");

		jLabel15.setText("City");

		lblLatitudeValue1.setText("latitude value");

		txtAddressNumber1.setName("txtAddressNumber");

		jLabel16.setText("Street");

		jLabel17.setText("Address");

		jLabel18.setText("Office of team 2");

		lblLatitude1.setText("Latitude");

		lblResult.setText("Distance beetween officee of teams is ");

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
																.addGap(10, 10,
																		10)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jLabel9,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																												.addComponent(
																														jLabel8)
																												.addComponent(
																														jLabel10)
																												.addGroup(
																														layout.createSequentialGroup()
																																.addGap(25,
																																		25,
																																		25)
																																.addGroup(
																																		layout.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.LEADING)
																																				.addGroup(
																																						layout.createParallelGroup(
																																								javax.swing.GroupLayout.Alignment.LEADING)
																																								.addGroup(
																																										layout.createSequentialGroup()
																																												.addGroup(
																																														layout.createParallelGroup(
																																																javax.swing.GroupLayout.Alignment.LEADING)
																																																.addComponent(
																																																		jLabel6)
																																																.addComponent(
																																																		jLabel5))
																																												.addGap(22,
																																														22,
																																														22))
																																								.addGroup(
																																										javax.swing.GroupLayout.Alignment.TRAILING,
																																										layout.createSequentialGroup()
																																												.addComponent(
																																														jLabel4)
																																												.addGap(18,
																																														18,
																																														18)))
																																				.addGroup(
																																						layout.createSequentialGroup()
																																								.addComponent(
																																										jLabel7)
																																								.addGap(36,
																																										36,
																																										36)))
																																.addGroup(
																																		layout.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.TRAILING,
																																				false)
																																				.addGroup(
																																						layout.createSequentialGroup()
																																								.addGroup(
																																										layout.createParallelGroup(
																																												javax.swing.GroupLayout.Alignment.LEADING)
																																												.addComponent(
																																														lblLatitude)
																																												.addComponent(
																																														lblLatitudeValue))
																																								.addGap(75,
																																										75,
																																										75)
																																								.addGroup(
																																										layout.createParallelGroup(
																																												javax.swing.GroupLayout.Alignment.LEADING)
																																												.addComponent(
																																														lblLongitude)
																																												.addComponent(
																																														lblLongitudeValue))
																																								.addGap(10,
																																										10,
																																										10))
																																				.addComponent(
																																						txtAddressDistrict,
																																						javax.swing.GroupLayout.Alignment.LEADING)
																																				.addComponent(
																																						txtAddressStreet,
																																						javax.swing.GroupLayout.Alignment.LEADING)
																																				.addComponent(
																																						txtAddressNumber,
																																						javax.swing.GroupLayout.Alignment.LEADING)
																																				.addComponent(
																																						txtAddressCity,
																																						javax.swing.GroupLayout.Alignment.LEADING,
																																						javax.swing.GroupLayout.DEFAULT_SIZE,
																																						233,
																																						Short.MAX_VALUE))))
																								.addGap(18,
																										18,
																										18)))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGap(10,
																										10,
																										10)
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING,
																												false)
																												.addComponent(
																														jLabel14)
																												.addComponent(
																														jLabel11,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														Short.MAX_VALUE)
																												.addComponent(
																														jLabel17,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														Short.MAX_VALUE)))
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGap(90,
																										90,
																										90)
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																												.addComponent(
																														txtOfficeID1,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														83,
																														javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addComponent(
																														txtOfficeName1,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														234,
																														javax.swing.GroupLayout.PREFERRED_SIZE)))
																				.addComponent(
																						jLabel18)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGap(35,
																										35,
																										35)
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																												.addGroup(
																														layout.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																																.addGroup(
																																		layout.createSequentialGroup()
																																				.addGroup(
																																						layout.createParallelGroup(
																																								javax.swing.GroupLayout.Alignment.LEADING)
																																								.addComponent(
																																										jLabel13)
																																								.addComponent(
																																										jLabel16))
																																				.addGap(22,
																																						22,
																																						22))
																																.addGroup(
																																		javax.swing.GroupLayout.Alignment.TRAILING,
																																		layout.createSequentialGroup()
																																				.addComponent(
																																						jLabel12)
																																				.addGap(18,
																																						18,
																																						18)))
																												.addGroup(
																														layout.createSequentialGroup()
																																.addComponent(
																																		jLabel15)
																																.addGap(36,
																																		36,
																																		36)))
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.TRAILING)
																												.addGroup(
																														layout.createSequentialGroup()
																																.addGroup(
																																		layout.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.LEADING)
																																				.addComponent(
																																						lblLatitude1)
																																				.addComponent(
																																						lblLatitudeValue1))
																																.addGap(75,
																																		75,
																																		75)
																																.addGroup(
																																		layout.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.LEADING)
																																				.addComponent(
																																						lblLongitude1)
																																				.addComponent(
																																						lblLongitudeValue1))
																																.addGap(10,
																																		10,
																																		10))
																												.addGroup(
																														layout.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING,
																																false)
																																.addComponent(
																																		txtAddressDistrict1)
																																.addComponent(
																																		txtAddressStreet1)
																																.addComponent(
																																		txtAddressNumber1,
																																		javax.swing.GroupLayout.PREFERRED_SIZE,
																																		233,
																																		javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addComponent(
																																		txtAddressCity1)))))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		217,
																		Short.MAX_VALUE))
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										jLabel1)
																								.addGap(18,
																										18,
																										18)
																								.addComponent(
																										cboTeam1,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										146,
																										javax.swing.GroupLayout.PREFERRED_SIZE))
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										jLabel2)
																								.addGap(18,
																										18,
																										18)
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																												.addComponent(
																														btnShowDistance)
																												.addComponent(
																														cboTeam2,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														146,
																														javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addComponent(
																														txtOfficeID,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														83,
																														javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addComponent(
																														txtOfficeName,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														234,
																														javax.swing.GroupLayout.PREFERRED_SIZE)))
																				.addComponent(
																						jLabel3)
																				.addComponent(
																						lblResult))
																.addGap(0,
																		0,
																		Short.MAX_VALUE)))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLabel18)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						txtOfficeID1,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						jLabel14))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						txtOfficeName1,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						jLabel11))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jLabel17)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						txtAddressNumber1,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						jLabel12))
																.addGap(6, 6, 6)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						txtAddressStreet1,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						jLabel16))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						txtAddressDistrict1,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						jLabel13))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						txtAddressCity1,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						jLabel15))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						lblLongitude1)
																				.addComponent(
																						lblLatitude1))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						lblLongitudeValue1)
																				.addComponent(
																						lblLatitudeValue1)))
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						jLabel1)
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
																						jLabel2)
																				.addComponent(
																						cboTeam2,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE))
																.addGap(18, 18,
																		18)
																.addComponent(
																		btnShowDistance)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(
																		jLabel3)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						txtOfficeID,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						jLabel8))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						txtOfficeName,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						jLabel10))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jLabel9)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						txtAddressNumber,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						jLabel4))
																.addGap(6, 6, 6)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						txtAddressStreet,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						jLabel5))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						txtAddressDistrict,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						jLabel6))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						txtAddressCity,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						jLabel7))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						lblLongitude)
																				.addComponent(
																						lblLatitude))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						lblLongitudeValue)
																				.addComponent(
																						lblLatitudeValue))))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										23, Short.MAX_VALUE)
								.addComponent(lblResult).addGap(24, 24, 24)));
	}// </editor-fold>

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("ShowDistance".equals(e.getActionCommand())) {
			if (isDuplicateTeams()) {
				JOptionPane.showMessageDialog(this,
						"Please choose team 1 is difference with team 2",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Office office1 = getOfficeOfTeam(((Team) cboTeam1.getSelectedItem()));
			Office office2 = getOfficeOfTeam(((Team) cboTeam2.getSelectedItem()));

			showOfficeInfo(office1, office2);

		}
	}

	private Office getOfficeOfTeam(Team team) {
		Category categoryOfTeam = null;
		Club clubOfTeam = null;
		Office officeOfTeam = null;

		String teamName = null;
		try {
			teamName = team.getTeamName();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		List<Category> categories;
		try {
			categories = EntityUtils
					.loadByCondition(null, Category.class, null);

			for (Category category : categories) {
				Team teams[] = category.getListteam().getArray();
				for (Team _team : teams) {
					if (_team.getTeamName().equals(teamName)) {
						categoryOfTeam = category;
					}
				}
			}

			List<Club> clubs = EntityUtils.loadByCondition(null, Club.class,
					null);
			for (Club club : clubs) {
				Category[] arrCategories = club.getListcategory().getArray();
				for (Category category : arrCategories) {
					if (category.getCategoryName().equals(
							categoryOfTeam.getCategoryName())) {
						clubOfTeam = club;
					}
				}
			}
		} catch (BasketException | SQLException e1) {
			e1.printStackTrace();
		}

		try {
			officeOfTeam = clubOfTeam.getClubOffice().getValue();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return officeOfTeam;

	}

	private void showOfficeInfo(Office officeOfTeam, Office officeOfTeam1) {
		try {
			// Office 1
			txtOfficeID.setText(String.valueOf(officeOfTeam.getId()));
			txtOfficeName.setText(officeOfTeam.getOfficeName());

			txtAddressNumber.setText(officeOfTeam.getOfficeAddress()
					.getAddressNumber());
			txtAddressStreet.setText(officeOfTeam.getOfficeAddress()
					.getAddressStreet());
			txtAddressDistrict.setText(officeOfTeam.getOfficeAddress()
					.getAddressDistrict());
			txtAddressCity.setText(officeOfTeam.getOfficeAddress()
					.getAddressCity());

			lblLatitudeValue.setText(String.valueOf(ViewSupport.getCityByName(
					officeOfTeam.getOfficeAddress().getAddressCity())
					.getLatitude()));
			lblLongitudeValue.setText(String.valueOf(ViewSupport.getCityByName(
					officeOfTeam.getOfficeAddress().getAddressCity())
					.getLongitude()));

			// Office 2
			txtOfficeID1.setText(String.valueOf(officeOfTeam1.getId()));
			txtOfficeName1.setText(officeOfTeam1.getOfficeName());

			txtAddressNumber1.setText(officeOfTeam1.getOfficeAddress()
					.getAddressNumber());
			txtAddressStreet1.setText(officeOfTeam1.getOfficeAddress()
					.getAddressStreet());
			txtAddressDistrict1.setText(officeOfTeam1.getOfficeAddress()
					.getAddressDistrict());
			txtAddressCity1.setText(officeOfTeam1.getOfficeAddress()
					.getAddressCity());

			lblLatitudeValue1.setText(String.valueOf(ViewSupport.getCityByName(
					officeOfTeam1.getOfficeAddress().getAddressCity())
					.getLatitude()));
			lblLongitudeValue1.setText(String.valueOf(ViewSupport
					.getCityByName(
							officeOfTeam1.getOfficeAddress().getAddressCity())
					.getLongitude()));

			// Distance
			lblResult.setText("Distance beetween officee of teams is "
					+ officeOfTeam.distance(officeOfTeam1) + " kilometers");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}