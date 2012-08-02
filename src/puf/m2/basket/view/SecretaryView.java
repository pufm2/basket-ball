package puf.m2.basket.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import puf.m2.basket.model.entity.Secretary;
import puf.m2.basket.model.support.BasketException;
import puf.m2.basket.model.support.Condition;
import puf.m2.basket.model.support.EntityUtils;

public class SecretaryView extends javax.swing.JPanel implements ActionListener {
	private static final long serialVersionUID = -1265877891170734060L;

	// Variables declaration - do not modify
	Secretary secretary = new Secretary();
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
	private JTextField txtSecretaryID;
	private JTextField txtSecretaryName;

	// End of variables declaration
	public SecretaryView() {
		initComponents();
		addActionListeners();

		formState = FormState.INITIAL;
		updateForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Cancel".equals(e.getActionCommand())) {
			formState = FormState.INITIAL;

		} else if ("Delete".equals(e.getActionCommand())) {
			Secretary secretaryToDelete = null;

			if (!isInteger(txtSecretaryID.getText())) {
				JOptionPane.showMessageDialog(this,
						"Please input correct secretary ID", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

			try {
				secretary = makeSecretary();
				secretaryToDelete = EntityUtils.loadById(secretary.getId(),
						Secretary.class);
			} catch (BasketException | SQLException e1) {
				e1.printStackTrace();
			}
			if (secretaryToDelete == null) {
				JOptionPane.showMessageDialog(this,
						"This secretary ID does not exists, can not delete",
						"Error", JOptionPane.ERROR_MESSAGE);
			} else if (JOptionPane.showConfirmDialog(this,
					"Do you really to delete this secretary?",
					"Confirm to delete secretary", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				// Delete this secretary
				deleteSecretary();
				JOptionPane
						.showMessageDialog(this, "This secretary is deleted");
			}
			formState = FormState.INITIAL;

		} else if ("Find".equals(e.getActionCommand())) {
			formState = FormState.FIND;
			String secretaryName = (String) JOptionPane.showInputDialog(this,
					"Please give a name of secretary", "Secretary 1");

			if (secretaryName == null)
				return;

			secretaryName = secretaryName.toUpperCase();
			// Find secretary with that name
			List<Secretary> secretaries = null;
			try {
				secretaries = EntityUtils.loadByCondition(new Condition(
						"PERSON_NAME", secretaryName), Secretary.class,
						"PERSON_NAME");
			} catch (BasketException e1) {
				e1.printStackTrace();
			}
			// If exist secretary, show its information
			if (secretaries.size() > 0) {
				secretary = secretaries.get(0);
				setTextField();
				JOptionPane.showMessageDialog(this, "Secretary founded",
						"Notice", JOptionPane.INFORMATION_MESSAGE);
				formState = FormState.FIND;
			} else {
				// If not exist secretary, show error message
				JOptionPane.showMessageDialog(this,
						"Can not found that secretary", "Error",
						JOptionPane.ERROR_MESSAGE);
				formState = FormState.INITIAL;
			}

		} else if ("New".equals(e.getActionCommand())) {
			pressUpdate = false;
			formState = FormState.NEW;

		} else if ("Save".equals(e.getActionCommand())) {
			if (isEmptyData()) {
				JOptionPane.showMessageDialog(this,
						"Please give enought information of secretary",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (isTypeMismatch()) {
				JOptionPane.showMessageDialog(this,
						"Please give correct type in each text field", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				if (!pressUpdate) {
					secretary = makeSecretary();
					if (isDuplicateData(secretary)) {
						JOptionPane
								.showMessageDialog(
										this,
										"Can not insert new secretary which is duplicate ID/ Name with existing secretary",
										"Error", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						// Save new secretary
						saveSecretary();
						JOptionPane.showMessageDialog(this,
								"Save new secretary successful", "Success",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					// Update existing secretary
					secretary = makeSecretary();
					updateSecretary();
					JOptionPane.showMessageDialog(this,
							"Update secretary successful", "Success",
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

	private void deleteSecretary() {
		try {
			secretary.setDeleted(1);
			secretary.update();
		} catch (SQLException | BasketException e) {
			e.printStackTrace();
		}
	}

	private void initComponents() {

		jButton2 = new JButton();
		txtSecretaryID = new JTextField();
		jLabel1 = new JLabel();
		txtSecretaryName = new JTextField();
		jLabel2 = new JLabel();
		btnNew = new JButton();
		btnFind = new JButton();
		btnSave = new JButton();
		btnCancel = new JButton();
		btnUpdate = new JButton();
		btnDelete = new JButton();

		jButton2.setText("jButton2");

		jLabel1.setText("Secretary ID");

		jLabel2.setText("Secretary name");

		btnNew.setText("New");
		btnNew.setToolTipText("Add new secretary");

		btnFind.setText("Find");
		btnFind.setToolTipText("Find an existing secretary");

		btnSave.setText("Save");
		btnSave.setToolTipText("Save new secretary");

		btnCancel.setText("Cancel");

		btnUpdate.setText("Update");

		btnDelete.setText("Delete");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(btnNew,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										76,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(btnFind,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										78,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(btnCancel,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										78,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(btnSave,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										76,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(btnUpdate,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										78,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(btnDelete,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										82,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
				.addGroup(
						layout.createSequentialGroup()
								.addGap(19, 19, 19)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel1)
												.addComponent(jLabel2))
								.addGap(16, 16, 16)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														txtSecretaryName,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														234,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														txtSecretaryID,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														91,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(191, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														txtSecretaryID,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel1))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel2)
												.addComponent(
														txtSecretaryName,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(36, 36, 36)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(btnNew)
												.addComponent(btnFind)
												.addComponent(btnCancel)
												.addComponent(btnSave)
												.addComponent(btnUpdate)
												.addComponent(btnDelete))
								.addContainerGap(17, Short.MAX_VALUE)));

		layout.linkSize(javax.swing.SwingConstants.VERTICAL,
				new java.awt.Component[] { txtSecretaryID, txtSecretaryName });

	}// </editor-fold>

	private boolean isDuplicateData(Secretary secretary) {
		List<Secretary> secretaries = null;

		// check if duplicate secretary ID
		try {
			secretaries = EntityUtils.loadByCondition(new Condition("id",
					secretary.getId().toString()), Secretary.class, "id");
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
		if (secretaries.size() > 0)
			return true;

		// Check if duplicate secretary name
		secretaries = null;
		try {
			secretaries = EntityUtils.loadByCondition(new Condition(
					"Person_Name", secretary.getPersonName()), Secretary.class,
					"PERSON_NAME");
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
		if (secretaries.size() > 0)
			return true;

		return false;

	}

	private boolean isEmptyData() {
		if (txtSecretaryID.getText().equals("")
				|| txtSecretaryName.getText().equals(""))
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
		if (!isInteger(txtSecretaryID.getText()))
			return true;
		return false;
	}

	private Secretary makeSecretary() {
		setFieldtoAttribute();
		try {
			secretary.setDeleted(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return secretary;
	}

	private void saveSecretary() {
		setFieldtoAttribute();
		try {
			secretary.setDeleted(0);
			secretary.save();
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void setFieldtoAttribute() {
		try {
			secretary.setId(Integer.parseInt(txtSecretaryID.getText().trim()));
			secretary.setPersonName(txtSecretaryName.getText().trim());
		} catch (NumberFormatException | SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void setTextField() {
		try {
			txtSecretaryID.setText(secretary.getId().toString());
			txtSecretaryName.setText(secretary.getPersonName());
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

	private void updateSecretary() {
		setFieldtoAttribute();
		try {
			secretary.setDeleted(0);
			secretary.update();
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
	}

}
