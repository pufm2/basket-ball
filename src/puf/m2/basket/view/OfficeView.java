package puf.m2.basket.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import puf.m2.basket.model.entity.Address;
import puf.m2.basket.model.entity.Office;
import puf.m2.basket.model.support.BasketException;
import puf.m2.basket.model.support.Condition;
import puf.m2.basket.model.support.EntityUtils;

public class OfficeView extends JPanel implements ActionListener {
	private static final long serialVersionUID = 7015383120837706444L;

	// Variables declaration - do not modify
	Office office;
	boolean pressUpdate = false;
	FormState formState;

	private JButton btnCancel;
	private JButton btnDelete;

	private JButton btnFind;
	private JButton btnNew;
	private JButton btnSave;
	private JButton btnUpdate;

	private JButton jButton2;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel7;

	private JTextField txtAddressCity;
	private JTextField txtAddressDistrict;
	private JTextField txtAddressNumber;
	private JTextField txtAddressStreet;
	private JTextField txtOfficeID;
	private JTextField txtOfficeName;

	// End of variables declaration

	public OfficeView() {
		initComponents();
		addActionListeners();

		formState = FormState.INITIAL;
		updateForm();
		office = new Office();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Cancel".equals(e.getActionCommand())) {
			formState = FormState.INITIAL;
		} else if ("Delete".equals(e.getActionCommand())) {
			Office officeToDelete = null;

			if (!isInteger(txtOfficeID.getText())) {
				JOptionPane.showMessageDialog(this,
						"Please input correct office ID", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			try {
				officeToDelete = EntityUtils.loadById(office.getId(),
						Office.class);
			} catch (BasketException | SQLException e1) {
				e1.printStackTrace();
			}
			if (officeToDelete == null) {
				JOptionPane.showMessageDialog(this,
						"This office ID does not exists, can not delete",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (JOptionPane.showConfirmDialog(this,
					"Do you really to delete this office?",
					"Confirm to delete office", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				// Delete this office
				deleteOffice();
				JOptionPane.showMessageDialog(this, "This office is deleted");
			}
			formState = FormState.INITIAL;

		} else if ("Find".equals(e.getActionCommand())) {
			formState = FormState.FIND;
			String officeName = (String) JOptionPane.showInputDialog(this,
					"Please give a name of office", "Office 1");

			if (officeName == null)
				return;

			officeName = officeName.toUpperCase();
			// Find office with that name
			List<Office> offices = null;
			try {
				offices = EntityUtils
						.loadByCondition(new Condition("Office_Name",
								officeName), Office.class, "Office_Name");
			} catch (BasketException e1) {
				e1.printStackTrace();
			}
			// If exist office, show its information
			if (offices.size() > 0) {
				office = offices.get(0);
				setTextField(office);
				JOptionPane.showMessageDialog(this, "Office founded", "Notice",
						JOptionPane.INFORMATION_MESSAGE);
				formState = FormState.FIND;

			} else {
				// If not exist office, show error message
				JOptionPane.showMessageDialog(this,
						"Can not found that office", "Error",
						JOptionPane.ERROR_MESSAGE);
				formState = FormState.INITIAL;
			}

		} else if ("New".equals(e.getActionCommand())) {
			pressUpdate = false;
			formState = FormState.NEW;

		} else if ("Save".equals(e.getActionCommand())) {
			if (isEmptyData()) {
				JOptionPane.showMessageDialog(this,
						"Please give enought information of office", "Error",
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
					office = makeOffice();
					if (isDuplicateData(office)) {
						JOptionPane
								.showMessageDialog(
										this,
										"Can not insert new office which is duplicate ID/ Name with existing office",
										"Error", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						// Save new office
						saveOffice();
						JOptionPane.showMessageDialog(this,
								"Save new office successful", "Success",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					// Update existing office
					updateOffice();
					JOptionPane.showMessageDialog(this,
							"Update office successful", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} else if ("Update".equals(e.getActionCommand())) {
			pressUpdate = true;
			formState = FormState.NEW;
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
	}

	private void deleteOffice() {
		try {
			office.setDeleted(1);
			office.update();
		} catch (SQLException | BasketException e) {
			e.printStackTrace();
		}

	}

	private void initComponents() {

		jButton2 = new javax.swing.JButton();
		txtOfficeID = new javax.swing.JTextField();
		jLabel1 = new javax.swing.JLabel();
		txtOfficeName = new javax.swing.JTextField();
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
		txtAddressCity = new javax.swing.JTextField();

		jButton2.setText("jButton2");

		jLabel1.setText("Office ID");

		jLabel2.setText("Office name");

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

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addGroup(
														javax.swing.GroupLayout.Alignment.LEADING,
														layout.createSequentialGroup()
																.addGap(19, 19,
																		19)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																												.addComponent(
																														jLabel1)
																												.addComponent(
																														jLabel2))
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																												.addGroup(
																														layout.createSequentialGroup()
																																.addPreferredGap(
																																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																.addGroup(
																																		layout.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.LEADING)
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
																												.addGroup(
																														layout.createSequentialGroup()
																																.addGap(5,
																																		5,
																																		5)
																																.addComponent(
																																		jLabel3,
																																		javax.swing.GroupLayout.DEFAULT_SIZE,
																																		366,
																																		Short.MAX_VALUE)
																																.addGap(99,
																																		99,
																																		99))))
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
																										78,
																										javax.swing.GroupLayout.PREFERRED_SIZE))))
												.addGroup(
														javax.swing.GroupLayout.Alignment.LEADING,
														layout.createSequentialGroup()
																.addGap(110,
																		110,
																		110)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										jLabel7)
																								.addGap(36,
																										36,
																										36)
																								.addComponent(
																										txtAddressCity,
																										javax.swing.GroupLayout.DEFAULT_SIZE,
																										355,
																										Short.MAX_VALUE))
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
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING,
																												false)
																												.addComponent(
																														txtAddressStreet,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														355,
																														Short.MAX_VALUE)
																												.addComponent(
																														txtAddressDistrict)
																												.addComponent(
																														txtAddressNumber))))
																.addGap(0,
																		0,
																		Short.MAX_VALUE)))
								.addGap(22, 22, 22)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														txtOfficeID,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel1))
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
												.addComponent(jLabel2))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jLabel3)
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
												.addComponent(
														txtAddressDistrict,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel6))
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
												.addComponent(jLabel7))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(btnNew)
												.addComponent(btnFind)
												.addComponent(btnCancel)
												.addComponent(btnSave)
												.addComponent(btnUpdate)
												.addComponent(btnDelete))
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
	}// </editor-fold>

	private boolean isDuplicateData(Office officeToSave) {
		List<Office> office = null;

		// check if duplicate office ID
		try {
			office = EntityUtils.loadByCondition(new Condition("id",
					officeToSave.getId().toString()), Office.class, "id");
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
		if (office.size() > 0)
			return true;

		// Check if duplicate office name
		office = null;
		try {
			office = EntityUtils.loadByCondition(new Condition("Office_Name",
					officeToSave.getOfficeName()), Office.class, "Office_Name");
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
		if (office.size() > 0)
			return true;

		return false;

	}

	private boolean isEmptyData() {
		if (txtAddressCity.getText().equals("")
				|| txtAddressDistrict.getText().equals("")
				|| txtAddressNumber.getText().equals("")
				|| txtAddressStreet.getText().equals("")
				|| txtOfficeID.getText().equals("")
				|| txtOfficeName.getText().equals(""))
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
		if (!isInteger(txtOfficeID.getText()))
			return true;
		return false;
	}

	private Office makeOffice() {
		setFieldtoAttribute();
		try {
			office.setDeleted(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return office;
	}

	private void saveOffice() {
		setFieldtoAttribute();
		try {
			office.setDeleted(0);
			office.save();
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void setFieldtoAttribute() {
		try {
			office.setId(Integer.parseInt(txtOfficeID.getText().trim()));
			office.setOfficeName(txtOfficeName.getText().trim());

			Address address = new Address();
			address.setAddressNumber(txtAddressNumber.getText());
			address.setAddressStreet(txtAddressStreet.getText());
			address.setAddressDistrict(txtAddressDistrict.getText());
			address.setAddressCity(txtAddressCity.getText());
			office.setOfficeAddress(address);

		} catch (NumberFormatException | SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void setTextField(Office foundedOffice) {
		try {
			txtOfficeID.setText(foundedOffice.getId().toString());
			txtOfficeName.setText(foundedOffice.getOfficeName());

			Address officeAddress = foundedOffice.getOfficeAddress();
			txtAddressNumber.setText(officeAddress.getAddressNumber());
			txtAddressStreet.setText(officeAddress.getAddressStreet());
			txtAddressDistrict.setText(officeAddress.getAddressDistrict());
			txtAddressCity.setText(officeAddress.getAddressCity());
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
			
			txtOfficeID.setText("");
			txtOfficeName.setText("");
			txtAddressNumber.setText("");
			txtAddressStreet.setText("");
			txtAddressDistrict.setText("");
			txtAddressCity.setText("");
			
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

	private void updateOffice() {
		setFieldtoAttribute();
		try {
			office.setDeleted(0);
			office.update();
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
	}
}