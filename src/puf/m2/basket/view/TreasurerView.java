package puf.m2.basket.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import puf.m2.basket.model.entity.Treasurer;
import puf.m2.basket.model.support.BasketException;
import puf.m2.basket.model.support.Condition;
import puf.m2.basket.model.support.EntityUtils;

public class TreasurerView extends javax.swing.JPanel implements ActionListener {
	private static final long serialVersionUID = -1265877891170734060L;

	// Variables declaration - do not modify
	Treasurer treasurer = new Treasurer();
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

	private JTextField txtTreasurerID;
	private JTextField txtTreasurerName;

	// End of variables declaration

	public TreasurerView() {
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
			Treasurer treasurerToDelete = null;

			if (!isInteger(txtTreasurerID.getText())) {
				JOptionPane.showMessageDialog(this,
						"Please input correct treasurer ID", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

			try {
				treasurer = makeTreasurer();
				treasurerToDelete = EntityUtils.loadById(treasurer.getId(),
						Treasurer.class);
			} catch (BasketException | SQLException e1) {
				e1.printStackTrace();
			}
			if (treasurerToDelete == null) {
				JOptionPane.showMessageDialog(this,
						"This treasurer ID does not exists, can not delete",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (JOptionPane.showConfirmDialog(this,
					"Do you really to delete this treasurer?",
					"Confirm to delete treasurer", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				// Delete this treasurer
				deleteTreasurer();
				JOptionPane
						.showMessageDialog(this, "This treasurer is deleted");
			}
			formState = FormState.INITIAL;

		} else if ("Find".equals(e.getActionCommand())) {
			formState = FormState.FIND;
			String treasurerName = (String) JOptionPane.showInputDialog(this,
					"Please give a name of treasurer", "Treasurer 1");

			if (treasurerName == null)
				return;

			treasurerName = treasurerName.toUpperCase();
			// Find treasurer with that name
			List<Treasurer> treasurers = null;
			try {
				treasurers = EntityUtils.loadByCondition(new Condition(
						"PERSON_NAME", treasurerName), Treasurer.class,
						"Person_Name");
			} catch (BasketException e1) {
				e1.printStackTrace();
			}
			// If exist treasurer, show its information
			if (treasurers.size() > 0) {
				treasurer = treasurers.get(0);
				setTextField(treasurer);
				JOptionPane.showMessageDialog(this, "Treasurer founded",
						"Notice", JOptionPane.INFORMATION_MESSAGE);
				formState = FormState.FIND;
			} else {
				// If not exist treasurer, show error message
				JOptionPane.showMessageDialog(this,
						"Can not found that treasurer", "Error",
						JOptionPane.ERROR_MESSAGE);
				formState = FormState.INITIAL;
			}

		} else if ("New".equals(e.getActionCommand())) {
			pressUpdate = false;
			formState = FormState.NEW;

		} else if ("Save".equals(e.getActionCommand())) {
			if (isEmptyData()) {
				JOptionPane.showMessageDialog(this,
						"Please give enought information of treasurer",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
			if (isTypeMismatch()) {
				JOptionPane.showMessageDialog(this,
						"Please give correct type in each text field", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				if (!pressUpdate) {
					treasurer = makeTreasurer();
					if (isDuplicateData(treasurer)) {
						JOptionPane
								.showMessageDialog(
										this,
										"Can not insert new treasurer which is duplicate ID/ Name with existing treasurer",
										"Error", JOptionPane.ERROR_MESSAGE);
					} else {
						// Save new treasurer
						saveTreasurer();
						JOptionPane.showMessageDialog(this,
								"Save new treasurer successful", "Success",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					// Update existing treasurer
					updateTreasurer();
					JOptionPane.showMessageDialog(this,
							"Update treasurer successful", "Success",
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

	private void deleteTreasurer() {
		try {
			treasurer.setDeleted(1);
			treasurer.update();
		} catch (SQLException | BasketException e) {
			e.printStackTrace();
		}
	}

	private void initComponents() {

		jButton2 = new JButton();
		txtTreasurerID = new JTextField();
		jLabel1 = new JLabel();
		txtTreasurerName = new JTextField();
		jLabel2 = new JLabel();
		btnNew = new JButton();
		btnFind = new JButton();
		btnSave = new JButton();
		btnCancel = new JButton();
		btnUpdate = new JButton();
		btnDelete = new JButton();

		jButton2.setText("jButton2");

		jLabel1.setText("Treasurer ID");

		jLabel2.setText("Treasurer name");

		btnNew.setText("New");
		btnNew.setToolTipText("Add new treasurer");

		btnFind.setText("Find");
		btnFind.setToolTipText("Find an existing treasurer");

		btnSave.setText("Save");
		btnSave.setToolTipText("Save new treasurer");

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
														txtTreasurerName,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														234,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														txtTreasurerID,
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
														txtTreasurerID,
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
														txtTreasurerName,
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
				new java.awt.Component[] { txtTreasurerID, txtTreasurerName });

	}// </editor-fold>

	private boolean isDuplicateData(Treasurer treasurer) {
		List<Treasurer> treasurers = null;

		// check if duplicate treasurer ID
		try {
			treasurers = EntityUtils.loadByCondition(new Condition("id",
					treasurer.getId().toString()), Treasurer.class, "id");
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
		if (treasurers.size() > 0)
			return true;

		// Check if duplicate treasurer name
		treasurers = null;
		try {
			treasurers = EntityUtils.loadByCondition(new Condition(
					"Person_Name", treasurer.getPersonName()), Treasurer.class,
					"Person_Name");
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
		if (treasurers.size() > 0)
			return true;

		return false;

	}

	private boolean isEmptyData() {
		if (txtTreasurerID.getText().equals("")
				|| txtTreasurerName.getText().equals(""))
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
		if (!isInteger(txtTreasurerID.getText()))
			return true;
		return false;
	}

	private Treasurer makeTreasurer() {
		setFieldtoAttribute();
		try {
			treasurer.setDeleted(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return treasurer;
	}

	private void saveTreasurer() {
		setFieldtoAttribute();
		try {
			treasurer.setDeleted(0);
			treasurer.save();
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void setFieldtoAttribute() {
		try {
			treasurer.setId(Integer.parseInt(txtTreasurerID.getText().trim()));
			treasurer.setPersonName(txtTreasurerName.getText().trim());
		} catch (NumberFormatException | SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void setTextField(Treasurer foundedTreasurer) {
		try {
			txtTreasurerID.setText(foundedTreasurer.getId().toString());
			txtTreasurerName.setText(foundedTreasurer.getPersonName());
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

	private void updateTreasurer() {
		setFieldtoAttribute();
		try {
			treasurer.setDeleted(0);
			treasurer.update();
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
	}
}
