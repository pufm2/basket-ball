package puf.m2.basket.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import puf.m2.basket.model.entity.Office;


public class OfficeView extends javax.swing.JPanel implements ActionListener {
	private static final long serialVersionUID = 7015383120837706444L;

	// Variables declaration - do not modify
	Office officeModel;
	FormState formState;

	private javax.swing.JButton btnCancel;
	private javax.swing.JButton btnDelete;
	private javax.swing.JButton btnFind;
	private javax.swing.JButton btnNew;
	private javax.swing.JButton btnSave;
	private javax.swing.JButton btnUpdate;
	private javax.swing.JButton jButton2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JTextField txtAddressCity;
	private javax.swing.JTextField txtAddressDistrict;
	private javax.swing.JTextField txtAddressNumber;
	private javax.swing.JTextField txtAddressStreet;
	private javax.swing.JTextField txtOfficeID;
	private javax.swing.JTextField txtOfficeName;

	// End of variables declaration

	public OfficeView() {
		initComponents();
		addActionListeners();
		
		formState = FormState.NORMAL;
		officeModel = new Office();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Cancel".equals(e.getActionCommand())) {
			formState = FormState.NORMAL;
		} else if ("Delete".equals(e.getActionCommand())) {
			formState = FormState.DELETE;
			if (JOptionPane.showConfirmDialog(this,
					"Do you really to delete this office?",
					"Confirm to delete office", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				// Delete this office
				JOptionPane.showMessageDialog(this, "This office is deleted");
			}
		} else if ("Find".equals(e.getActionCommand())) {
			formState = FormState.FIND;
			String officeName = (String) JOptionPane.showInputDialog(this,
					"Please give a name of office", "Office 1");
			// Find office with that name
			// If exist office, show its information
			// If not exist office, show error message */

		} else if ("New".equals(e.getActionCommand())) {
			formState = FormState.NEW;
		} else if ("Save".equals(e.getActionCommand())) {
			formState = FormState.SAVE;
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
			}
			else {
				if (formState == FormState.NEW) {
					if (isDuplicateData()) {
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
				} else if (formState == FormState.UPDATE) {
					// Update existing office
					updateOffice();
					JOptionPane.showMessageDialog(this,
							"Save new office successful", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} else if ("Update".equals(e.getActionCommand())) {
			formState = FormState.UPDATE;
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
						layout.createSequentialGroup()
						.addGroup(
								layout.createParallelGroup(
										javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												layout.createSequentialGroup()
												.addGap(19, 19,
														19)
														.addGroup(
																layout.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.TRAILING)
																		.addGroup(
																				layout.createParallelGroup(
																						javax.swing.GroupLayout.Alignment.LEADING,
																						false)
																						.addComponent(
																								jLabel1)
																								.addComponent(
																										jLabel3,
																										javax.swing.GroupLayout.Alignment.TRAILING,
																										javax.swing.GroupLayout.DEFAULT_SIZE,
																										javax.swing.GroupLayout.DEFAULT_SIZE,
																										Short.MAX_VALUE)
																										.addComponent(
																												jLabel2))
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
																																												javax.swing.GroupLayout.Alignment.LEADING))))
																																												.addPreferredGap(
																																														javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																														.addGroup(
																																																layout.createParallelGroup(
																																																		javax.swing.GroupLayout.Alignment.LEADING,
																																																		false)
																																																		.addComponent(
																																																				txtOfficeID,
																																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																																				83,
																																																				javax.swing.GroupLayout.PREFERRED_SIZE)
																																																				.addComponent(
																																																						txtOfficeName,
																																																						javax.swing.GroupLayout.DEFAULT_SIZE,
																																																						234,
																																																						Short.MAX_VALUE)
																																																						.addComponent(
																																																								txtAddressNumber,
																																																								javax.swing.GroupLayout.PREFERRED_SIZE,
																																																								81,
																																																								javax.swing.GroupLayout.PREFERRED_SIZE)
																																																								.addComponent(
																																																										txtAddressStreet)
																																																										.addComponent(
																																																												txtAddressDistrict)
																																																												.addComponent(
																																																														txtAddressCity)))
																																																														.addGroup(
																																																																layout.createSequentialGroup()
																																																																.addContainerGap()
																																																																.addGroup(
																																																																		layout.createParallelGroup(
																																																																				javax.swing.GroupLayout.Alignment.LEADING,
																																																																				false)
																																																																				.addComponent(
																																																																						btnSave,
																																																																						javax.swing.GroupLayout.DEFAULT_SIZE,
																																																																						76,
																																																																						Short.MAX_VALUE)
																																																																						.addComponent(
																																																																								btnNew,
																																																																								javax.swing.GroupLayout.DEFAULT_SIZE,
																																																																								javax.swing.GroupLayout.DEFAULT_SIZE,
																																																																								Short.MAX_VALUE))
																																																																								.addPreferredGap(
																																																																										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																																																										.addGroup(
																																																																												layout.createParallelGroup(
																																																																														javax.swing.GroupLayout.Alignment.LEADING,
																																																																														false)
																																																																														.addComponent(
																																																																																btnFind,
																																																																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																																																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																																																																Short.MAX_VALUE)
																																																																																.addComponent(
																																																																																		btnCancel,
																																																																																		javax.swing.GroupLayout.DEFAULT_SIZE,
																																																																																		78,
																																																																																		Short.MAX_VALUE))
																																																																																		.addPreferredGap(
																																																																																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																																																																				.addGroup(
																																																																																						layout.createParallelGroup(
																																																																																								javax.swing.GroupLayout.Alignment.LEADING,
																																																																																								false)
																																																																																								.addComponent(
																																																																																										btnUpdate,
																																																																																										javax.swing.GroupLayout.DEFAULT_SIZE,
																																																																																										78,
																																																																																										Short.MAX_VALUE))
																																																																																										.addPreferredGap(
																																																																																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																																																																												.addComponent(
																																																																																														btnDelete,
																																																																																														javax.swing.GroupLayout.DEFAULT_SIZE,
																																																																																														73,
																																																																																														Short.MAX_VALUE)))
																																																																																														.addContainerGap()));
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
																						.addGap(5, 5, 5)
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
																																																javax.swing.GroupLayout.Alignment.BASELINE)
																																																.addComponent(jLabel7)
																																																.addComponent(
																																																		txtAddressCity,
																																																		javax.swing.GroupLayout.PREFERRED_SIZE,
																																																		javax.swing.GroupLayout.DEFAULT_SIZE,
																																																		javax.swing.GroupLayout.PREFERRED_SIZE))
																																																		.addPreferredGap(
																																																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																																																				37, Short.MAX_VALUE)
																																																				.addGroup(
																																																						layout.createParallelGroup(
																																																								javax.swing.GroupLayout.Alignment.BASELINE)
																																																								.addComponent(btnNew)
																																																								.addComponent(btnFind))
																																																								.addPreferredGap(
																																																										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																																										.addGroup(
																																																												layout.createParallelGroup(
																																																														javax.swing.GroupLayout.Alignment.BASELINE)
																																																														.addComponent(btnSave)
																																																														.addComponent(btnCancel)
																																																														.addComponent(btnUpdate)
																																																														.addComponent(btnDelete))
																																																														.addGap(25, 25, 25)));
	}// </editor-fold>

	private boolean isDuplicateData() {
		return false;
	}

	private boolean isEmptyData() {
		if (txtAddressCity.getText().trim()==""
				|| txtAddressDistrict.getText().trim()==""
				|| txtAddressNumber.getText().trim()==""
				|| txtAddressStreet.getText().trim()==""
				|| txtOfficeID.getText().trim()==""
				|| txtOfficeName.getText().trim()=="")
			return true;
		return false;
	}

	private boolean isTypeMismatch() {
		if (!isInteger(txtOfficeID.getText()))
			return true;
		return false;
	}

	public boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		}
		catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	private void saveOffice() {
	}

	private void updateForm() {
		switch (formState) {

		case NORMAL:
			btnCancel.setVisible(true);
			btnDelete.setVisible(true);
			btnFind.setVisible(true);
			btnNew.setVisible(true);
			btnSave.setVisible(true);
			btnUpdate.setVisible(true);

			txtAddressCity.setText("");
			txtAddressDistrict.setText("");
			txtAddressNumber.setText("");
			txtAddressStreet.setText("");
			txtOfficeID.setText("");
			txtOfficeName.setText("");
			break;

		case NEW:
			btnCancel.setVisible(true);
			btnDelete.setVisible(false);
			btnFind.setVisible(false);
			btnNew.setVisible(false);
			btnSave.setVisible(true);
			btnUpdate.setVisible(false);
			break;

		case FIND:

			break;

		case SAVE:
			btnCancel.setVisible(true);
			btnDelete.setVisible(true);
			btnFind.setVisible(true);
			btnNew.setVisible(true);
			btnSave.setVisible(true);
			btnUpdate.setVisible(true);
			break;

		case UPDATE:
			btnCancel.setVisible(true);
			btnDelete.setVisible(false);
			btnFind.setVisible(false);
			btnNew.setVisible(false);
			btnSave.setVisible(true);
			btnUpdate.setVisible(false);
			break;

		case DELETE:

			break;
		}
	}

	private void updateOffice() {
	}
}
