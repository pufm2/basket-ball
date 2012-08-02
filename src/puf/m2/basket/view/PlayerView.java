package puf.m2.basket.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import puf.m2.basket.model.entity.Address;
import puf.m2.basket.model.entity.Player;
import puf.m2.basket.model.support.BasketException;
import puf.m2.basket.model.support.Condition;
import puf.m2.basket.model.support.EntityUtils;
import puf.m2.basket.view.support.City;
import puf.m2.basket.view.support.ViewSupport;

public class PlayerView extends JPanel implements ActionListener {

	private static final long serialVersionUID = -2288683884337280936L;

	// Variables declaration - do not modify
	Player player;
	boolean pressUpdate = false;
	FormState formState;

	private javax.swing.JButton btnCancel;
	private javax.swing.JButton btnDelete;
	private javax.swing.JButton btnFind;
	private javax.swing.JButton btnNew;
	private javax.swing.JButton btnSave;
	private javax.swing.JButton btnUpdate;
	private javax.swing.JComboBox<City> cboAddressCity;
	private javax.swing.JButton jButton2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JLabel lblLatitudeValue;
	private javax.swing.JLabel lblLongitudeValue;
	private javax.swing.JTextField txtAddressDistrict;
	private javax.swing.JTextField txtAddressNumber;
	private javax.swing.JTextField txtAddressStreet;
	private JDateChooser txtBirthday;
	private javax.swing.JTextField txtLicenseNumber;
	private javax.swing.JTextField txtPersonID;
	private javax.swing.JTextField txtPersonName;

	// End of variables declaration

	public PlayerView() {
		initComponents();
		addActionListeners();

		player = new Player();
		formState = FormState.INITIAL;
		updateForm();
		fillComboCity();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Cancel".equals(e.getActionCommand())) {
			formState = FormState.INITIAL;

		} else if ("Delete".equals(e.getActionCommand())) {
			Player playerToDelete = null;

			if (!isInteger(txtPersonID.getText())) {
				JOptionPane.showMessageDialog(this,
						"Please input correct player ID", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

			try {
				player = makePlayer();
				playerToDelete = EntityUtils.loadById(player.getId(),
						Player.class);
			} catch (BasketException | SQLException e1) {
				e1.printStackTrace();
			}
			if (playerToDelete == null) {
				JOptionPane.showMessageDialog(this,
						"This player ID does not exists, can not delete",
						"Error", JOptionPane.ERROR_MESSAGE);
			} else if (JOptionPane.showConfirmDialog(this,
					"Do you really to delete this player?",
					"Confirm to delete player", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				// Delete this player
				deletePlayer();
				JOptionPane
						.showMessageDialog(this, "This president is deleted");

			}
			formState = FormState.INITIAL;

		} else if ("Find".equals(e.getActionCommand())) {
			formState = FormState.FIND;
			String playerName = (String) JOptionPane.showInputDialog(this,
					"Please give a name of player", "Player 1");

			if (playerName == null)
				return;

			playerName = playerName.toUpperCase();

			// Find player with that name
			List<Player> players = null;
			try {
				players = EntityUtils
						.loadByCondition(new Condition("PERSON_NAME",
								playerName), Player.class, "PERSON_NAME");
			} catch (BasketException e1) {
				e1.printStackTrace();
			}
			// If exist player, show its information
			if (players.size() > 0) {
				player = players.get(0);
				updateControlValues();

				JOptionPane.showMessageDialog(this, "Player is founded",
						"Notice", JOptionPane.INFORMATION_MESSAGE);
				formState = FormState.FIND;
			} else {
				// If not exist player, show error message
				JOptionPane.showMessageDialog(this,
						"Can not found that player", "Error",
						JOptionPane.ERROR_MESSAGE);
				formState = FormState.INITIAL;
			}

		} else if ("New".equals(e.getActionCommand())) {
			formState = FormState.NEW;
			txtPersonID.setText("");
			txtPersonName.setText("");

		} else if ("Save".equals(e.getActionCommand())) {
			if (isEmptyData()) {
				JOptionPane.showMessageDialog(this,
						"Please give enought information of player", "Error",
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
					player = makePlayer();
					if (isDuplicateData(player)) {
						JOptionPane
								.showMessageDialog(
										this,
										"Can not insert new player which is duplicate ID/ Name with existing player",
										"Error", JOptionPane.ERROR_MESSAGE);
					} else {
						// Save new player
						savePlayer();
						JOptionPane.showMessageDialog(this,
								"Save new player successful", "Success",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					// Update existing player
					updatePlayer();
					JOptionPane.showMessageDialog(this,
							"Update player successful", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
			formState = FormState.INITIAL;

		} else if ("Update".equals(e.getActionCommand())) {
			pressUpdate = true;
			formState = FormState.NEW;
		} else if ("ChangeCity".equals(e.getActionCommand())) {
			updateLatitude_Longitude();
		}
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

		cboAddressCity.setActionCommand("ChangeCity");
		cboAddressCity.addActionListener(this);
	}

	private void deletePlayer() {
		try {
			player.setDeleted(1);
			player.update();
		} catch (SQLException | BasketException e) {
			e.printStackTrace();
		}
	}

	private void fillComboCity() {
		for (int i = 0; i < ViewSupport.getCities().size(); i++)
			cboAddressCity.addItem(ViewSupport.getCities().get(i));

		updateLatitude_Longitude();
	}

	private void initComponents() {

		jButton2 = new javax.swing.JButton();
		txtPersonID = new javax.swing.JTextField();
		jLabel1 = new javax.swing.JLabel();
		txtPersonName = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		btnNew = new javax.swing.JButton();
		btnFind = new javax.swing.JButton();
		btnSave = new javax.swing.JButton();
		btnCancel = new javax.swing.JButton();
		btnUpdate = new javax.swing.JButton();
		btnDelete = new javax.swing.JButton();
		txtAddressNumber = new javax.swing.JTextField();
		jLabel4 = new javax.swing.JLabel();
		txtAddressStreet = new javax.swing.JTextField();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		txtAddressDistrict = new javax.swing.JTextField();
		jLabel9 = new javax.swing.JLabel();
		txtLicenseNumber = new javax.swing.JTextField();
		jLabel10 = new javax.swing.JLabel();
		txtBirthday = new JDateChooser();
		cboAddressCity = new javax.swing.JComboBox<City>();
		jLabel8 = new javax.swing.JLabel();
		jLabel11 = new javax.swing.JLabel();
		lblLatitudeValue = new javax.swing.JLabel();
		lblLongitudeValue = new javax.swing.JLabel();

		jButton2.setText("jButton2");

		jLabel1.setText("Person ID");

		jLabel2.setText("Person name");

		jLabel3.setText("Address");

		btnNew.setText("New");
		btnNew.setToolTipText("Add new office");

		btnFind.setText("Find");
		btnFind.setToolTipText("Find an existing office");

		btnSave.setText("Save");
		btnSave.setToolTipText("Save new office");

		btnCancel.setText("Cancel");

		btnUpdate.setText("Update");

		btnDelete.setText("Delete");

		txtAddressNumber.setName("txtAddressNumber");

		jLabel4.setText("Number");

		jLabel5.setText("Street");

		jLabel6.setText("District");

		jLabel7.setText("City");

		jLabel9.setText("License number");

		jLabel10.setText("Birthday");

		jLabel8.setText("Latitude");

		jLabel11.setText("Longitude");

		lblLatitudeValue.setText("jLabel12");

		lblLongitudeValue.setText("jLabel12");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(19, 19, 19)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
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
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(0,
																		21,
																		Short.MAX_VALUE))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLabel3,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addContainerGap())
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																												.addComponent(
																														jLabel9)
																												.addComponent(
																														jLabel10))
																								.addGap(18,
																										18,
																										18)
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING,
																												false)
																												.addComponent(
																														txtBirthday,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														138,
																														Short.MAX_VALUE)
																												.addComponent(
																														txtLicenseNumber)))
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																												.addComponent(
																														jLabel1)
																												.addComponent(
																														jLabel2))
																								.addGap(30,
																										30,
																										30)
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																												.addComponent(
																														txtPersonName,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														234,
																														javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addComponent(
																														txtPersonID,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														91,
																														javax.swing.GroupLayout.PREFERRED_SIZE)))
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGap(49,
																										49,
																										49)
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																												.addComponent(
																														jLabel4)
																												.addGroup(
																														layout.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.TRAILING)
																																.addComponent(
																																		jLabel6)
																																.addComponent(
																																		jLabel7,
																																		javax.swing.GroupLayout.Alignment.LEADING)
																																.addComponent(
																																		jLabel5,
																																		javax.swing.GroupLayout.Alignment.LEADING)))
																								.addGap(7,
																										7,
																										7)
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																												.addGroup(
																														layout.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING,
																																false)
																																.addComponent(
																																		txtAddressNumber,
																																		javax.swing.GroupLayout.PREFERRED_SIZE,
																																		81,
																																		javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addComponent(
																																		txtAddressStreet,
																																		javax.swing.GroupLayout.DEFAULT_SIZE,
																																		234,
																																		Short.MAX_VALUE)
																																.addComponent(
																																		txtAddressDistrict)
																																.addComponent(
																																		cboAddressCity,
																																		0,
																																		javax.swing.GroupLayout.DEFAULT_SIZE,
																																		Short.MAX_VALUE))
																												.addGroup(
																														layout.createSequentialGroup()
																																.addGroup(
																																		layout.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.TRAILING)
																																				.addComponent(
																																						lblLatitudeValue)
																																				.addComponent(
																																						jLabel8))
																																.addGap(101,
																																		101,
																																		101)
																																.addGroup(
																																		layout.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.LEADING)
																																				.addComponent(
																																						jLabel11)
																																				.addComponent(
																																						lblLongitudeValue))))))
																.addContainerGap()))));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														txtPersonID,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel1))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel2)
												.addComponent(
														txtPersonName,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel9)
												.addComponent(
														txtLicenseNumber,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														txtBirthday,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel10))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jLabel3)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(
														txtAddressNumber,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel4))
								.addGap(6, 6, 6)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														txtAddressStreet,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel5))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel6)
												.addComponent(
														txtAddressDistrict,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel7)
												.addComponent(
														cboAddressCity,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel8)
												.addComponent(jLabel11))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(lblLatitudeValue)
												.addComponent(lblLongitudeValue))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										19, Short.MAX_VALUE)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(btnNew)
												.addComponent(btnFind)
												.addComponent(btnCancel)
												.addComponent(btnSave)
												.addComponent(btnUpdate)
												.addComponent(btnDelete))
								.addGap(30, 30, 30)));

		layout.linkSize(javax.swing.SwingConstants.VERTICAL,
				new java.awt.Component[] { txtLicenseNumber, txtPersonID,
						txtPersonName });

	}// </editor-fold>

	private boolean isDuplicateData(Player player) {
		List<Player> players = null;

		// check if duplicate player ID
		try {
			players = EntityUtils.loadByCondition(new Condition("id", player
					.getId().toString()), Player.class, "id");
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
		if (players.size() > 0)
			return true;

		// Check if duplicate player name
		players = null;
		try {
			players = EntityUtils.loadByCondition(new Condition("Person_Name",
					player.getPersonName()), Player.class, "PERSON_NAME");
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
		if (players.size() > 0)
			return true;

		return false;

	}

	private boolean isEmptyData() {
		if (txtPersonID.getText().equals("")
				|| txtPersonName.getText().equals(""))
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
		if (!isInteger(txtPersonID.getText()))
			return true;
		return false;
	}

	private Player makePlayer() {
		setFieldtoAttribute();
		return player;
	}

	private void savePlayer() {
		setFieldtoAttribute();
		try {
			player.save();
		} catch (BasketException e) {
			e.printStackTrace();
		}
	}

	private void setFieldtoAttribute() {
		try {
			player.setId(Integer.parseInt(txtPersonID.getText().trim()));
			player.setPersonName(txtPersonName.getText().trim());
			player.setPlayerLicenceNumber(txtLicenseNumber.getText());
			player.setPlayerBirthday(new Timestamp(txtBirthday.getDate()
					.getTime()));

			Address address = new Address();
			address.setAddressNumber(txtAddressNumber.getText());
			address.setAddressStreet(txtAddressStreet.getText());
			address.setAddressDistrict(txtAddressDistrict.getText());

			City city = (City) cboAddressCity.getSelectedItem();
			address.setAddressCity(city.getCityName());

			player.setPlayerAddress(address);
		} catch (NumberFormatException | SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void updateControlValues() {
		try {
			txtPersonID.setText(player.getId().toString());
			txtPersonName.setText(player.getPersonName().toString());
			txtLicenseNumber
					.setText(player.getPlayerLicenceNumber().toString());
			txtBirthday.setDate(ViewSupport.toDate(player.getPlayerBirthday()));

			txtAddressNumber.setText(player.getPlayerAddress()
					.getAddressNumber().toString());
			txtAddressStreet.setText(player.getPlayerAddress()
					.getAddressStreet().toString());
			txtAddressDistrict.setText(player.getPlayerAddress()
					.getAddressDistrict().toString());

			// System.out.println(player.getPlayerAddress().getAddressCity());
			for (int i = 0; i < cboAddressCity.getItemCount(); i++) {
				// System.out.println(cboAddressCity.getItemAt(i).getCityName());
				if (cboAddressCity.getItemAt(i).getCityName()
						.equals(player.getPlayerAddress().getAddressCity())) {
					cboAddressCity.setSelectedIndex(i);
					updateLatitude_Longitude();
				}
			}

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

	public void updateLatitude_Longitude() {
		String cityName = ((City) cboAddressCity.getSelectedItem())
				.getCityName();
		lblLatitudeValue.setText(String.valueOf(ViewSupport.getCityByName(
				cityName).getLatitude()));
		lblLongitudeValue.setText(String.valueOf(ViewSupport.getCityByName(
				cityName).getLongitude()));
	}

	private void updatePlayer() {
		setFieldtoAttribute();
		try {
			player.update();
		} catch (BasketException e) {
			e.printStackTrace();
		}
	}
}
