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

import puf.m2.basket.model.entity.VicePresident;
import puf.m2.basket.model.support.BasketException;
import puf.m2.basket.model.support.Condition;
import puf.m2.basket.model.support.EntityUtils;

public class VicePresidentView extends JPanel implements ActionListener {
	private static final long serialVersionUID = -1265877891170734060L;

	// Variables declaration - do not modify
	VicePresident vicePresident = new VicePresident();
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
	private JTextField txtVicePresidentID;
	private JTextField txtVicePresidentName;

	// End of variables declaration
	public VicePresidentView() {
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
			VicePresident vicepresidentToDelete = null;

			if (!isInteger(txtVicePresidentID.getText())) {
				JOptionPane.showMessageDialog(this,
						"Please input correct vice president ID", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

			try {
				vicePresident = makeVicePresident();
				vicepresidentToDelete = EntityUtils.loadById(
						vicePresident.getId(), VicePresident.class);
			} catch (BasketException | SQLException e1) {
				e1.printStackTrace();
			}
			if (vicepresidentToDelete == null) {
				JOptionPane
						.showMessageDialog(
								this,
								"This vice president ID does not exists, can not delete",
								"Error", JOptionPane.ERROR_MESSAGE);
			} else if (JOptionPane.showConfirmDialog(this,
					"Do you really to delete this vice president?",
					"Confirm to delete vice president",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				// Delete this president
				deleteVicePresident();
				JOptionPane
						.showMessageDialog(this, "This president is deleted");
			}
			formState = FormState.INITIAL;

		} else if ("Find".equals(e.getActionCommand())) {
			formState = FormState.FIND;
			String vicepresidentName = (String) JOptionPane.showInputDialog(
					this, "Please give a name of vice president",
					"Vice_President 1");

			if (vicepresidentName == null)
				return;

			vicepresidentName = vicepresidentName.toUpperCase();
			// Find president with that name
			List<VicePresident> vicepresidents = null;
			try {
				vicepresidents = EntityUtils.loadByCondition(new Condition(
						"PERSON_NAME", vicepresidentName), VicePresident.class,
						"Person_Name");
			} catch (BasketException e1) {
				e1.printStackTrace();
			}
			// If exist vice president, show its information
			if (vicepresidents.size() > 0) {
				vicePresident = vicepresidents.get(0);
				setTextField(vicePresident);
				JOptionPane.showMessageDialog(this, "Vice president founded",
						"Notice", JOptionPane.INFORMATION_MESSAGE);
				formState = FormState.FIND;
			} else {
				// If not exist vice president, show error message
				JOptionPane.showMessageDialog(this,
						"Can not found that vice president", "Error",
						JOptionPane.ERROR_MESSAGE);
				formState = FormState.INITIAL;
			}

		} else if ("New".equals(e.getActionCommand())) {
			pressUpdate = false;
			formState = FormState.NEW;

		} else if ("Save".equals(e.getActionCommand())) {
			if (isEmptyData()) {
				JOptionPane.showMessageDialog(this,
						"Please give enought information of vice president",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
			if (isTypeMismatch()) {
				JOptionPane.showMessageDialog(this,
						"Please give correct type in each text field", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				if (!pressUpdate) {
					vicePresident = makeVicePresident();
					if (isDuplicateData(vicePresident)) {
						JOptionPane
								.showMessageDialog(
										this,
										"Can not insert new vice president which is duplicate ID/ Name with existing vice president",
										"Error", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						// Save new vice president
						saveVicePresident(vicePresident);
						JOptionPane.showMessageDialog(this,
								"Save new vice president successful",
								"Success", JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					// Update existing vice president
					updateVicePresident(vicePresident);
					JOptionPane.showMessageDialog(this,
							"Update vice president successful", "Success",
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

	private void deleteVicePresident() {
		try {
			vicePresident.setDeleted(1);
			vicePresident.update();
		} catch (SQLException | BasketException e) {
			e.printStackTrace();
		}

	}

	private void initComponents() {

		jButton2 = new JButton();
		txtVicePresidentID = new JTextField();
		jLabel1 = new JLabel();
		txtVicePresidentName = new JTextField();
		jLabel2 = new JLabel();
		btnNew = new JButton();
		btnFind = new JButton();
		btnSave = new JButton();
		btnCancel = new JButton();
		btnUpdate = new JButton();
		btnDelete = new JButton();

		jButton2.setText("jButton2");

		jLabel1.setText("Vice President ID");

		jLabel2.setText("Vice President name");

		btnNew.setText("New");
		btnNew.setToolTipText("Add new vice president");

		btnFind.setText("Find");
		btnFind.setToolTipText("Find an existing vice president");

		btnSave.setText("Save");
		btnSave.setToolTipText("Save new vice president");

		btnCancel.setText("Cancel");

		btnUpdate.setText("Update");

		btnDelete.setText("Delete");

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(btnNew,
										GroupLayout.PREFERRED_SIZE, 76,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(btnFind,
										GroupLayout.PREFERRED_SIZE, 78,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(btnCancel,
										GroupLayout.PREFERRED_SIZE, 78,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(btnSave,
										GroupLayout.PREFERRED_SIZE, 76,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(btnUpdate,
										GroupLayout.PREFERRED_SIZE, 78,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(btnDelete,
										GroupLayout.PREFERRED_SIZE, 82,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
				.addGroup(
						layout.createSequentialGroup()
								.addGap(19, 19, 19)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addComponent(jLabel1)
												.addComponent(jLabel2))
								.addGap(16, 16, 16)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addComponent(
														txtVicePresidentName,
														GroupLayout.PREFERRED_SIZE,
														234,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														txtVicePresidentID,
														GroupLayout.PREFERRED_SIZE,
														91,
														GroupLayout.PREFERRED_SIZE))
								.addContainerGap(191, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
												.addComponent(
														txtVicePresidentID,
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
														txtVicePresidentName,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addGap(36, 36, 36)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
												.addComponent(btnNew)
												.addComponent(btnFind)
												.addComponent(btnCancel)
												.addComponent(btnSave)
												.addComponent(btnUpdate)
												.addComponent(btnDelete))
								.addContainerGap(17, Short.MAX_VALUE)));

		layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {
				txtVicePresidentID, txtVicePresidentName });

	}// </editor-fold>

	private boolean isDuplicateData(VicePresident vicePresident) {
		List<VicePresident> vicePresidents = null;

		// check if duplicate vice president ID
		try {
			vicePresidents = EntityUtils.loadByCondition(new Condition("id",
					vicePresident.getId().toString()), VicePresident.class,
					"id");
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
		if (vicePresidents.size() > 0)
			return true;

		// Check if duplicate vice president name
		vicePresidents = null;
		try {
			vicePresidents = EntityUtils.loadByCondition(new Condition(
					"Person_Name", vicePresident.getPersonName()),
					VicePresident.class, "Person_Name");
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
		if (vicePresidents.size() > 0)
			return true;

		return false;

	}

	private boolean isEmptyData() {
		if (txtVicePresidentID.getText().equals("")
				|| txtVicePresidentName.getText().equals(""))
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
		if (!isInteger(txtVicePresidentID.getText()))
			return true;
		return false;
	}

	private VicePresident makeVicePresident() {
		setFieldtoAttribute();
		try {
			vicePresident.setDeleted(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vicePresident;
	}

	private void saveVicePresident(VicePresident vicePresident) {
		setFieldtoAttribute();
		try {
			vicePresident.setDeleted(0);
			vicePresident.save();
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void setFieldtoAttribute() {
		try {
			vicePresident.setId(Integer.parseInt(txtVicePresidentID.getText()
					.trim()));
			vicePresident.setPersonName(txtVicePresidentName.getText().trim());
		} catch (NumberFormatException | SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void setTextField(VicePresident foundedVicePresident) {
		try {
			txtVicePresidentID.setText(foundedVicePresident.getId().toString());
			txtVicePresidentName.setText(foundedVicePresident.getPersonName());
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

	private void updateVicePresident(VicePresident vicePresident) {
		setFieldtoAttribute();
		try {
			vicePresident.setDeleted(0);
			vicePresident.update();
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
	}

}
