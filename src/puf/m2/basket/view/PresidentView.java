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

import puf.m2.basket.model.entity.President;
import puf.m2.basket.model.support.BasketException;
import puf.m2.basket.model.support.Condition;
import puf.m2.basket.model.support.EntityUtils;

public class PresidentView extends JPanel implements ActionListener {
	private static final long serialVersionUID = -1265877891170734060L;

	// Variables declaration - do not modify
	President president = new President();
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

	private JTextField txtPresidentID;
	private JTextField txtPresidentName;

	// End of variables declaration
	public PresidentView() {
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
			President presidentToDelete = null;

			if (!isInteger(txtPresidentID.getText())) {
				JOptionPane.showMessageDialog(this,
						"Please input correct president ID", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

			try {
				president = makePresident();
				presidentToDelete = EntityUtils.loadById(president.getId(),
						President.class);
			} catch (BasketException | SQLException e1) {
				e1.printStackTrace();
			}
			if (presidentToDelete == null) {
				JOptionPane.showMessageDialog(this,
						"This president ID does not exists, can not delete",
						"Error", JOptionPane.ERROR_MESSAGE);
			} else if (JOptionPane.showConfirmDialog(this,
					"Do you really to delete this president?",
					"Confirm to delete president", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				// Delete this president
				deletePresident();
				JOptionPane
						.showMessageDialog(this, "This president is deleted");
			}
			formState = FormState.INITIAL;

		} else if ("Find".equals(e.getActionCommand())) {
			String presidentName = (String) JOptionPane.showInputDialog(this,
					"Please give a name of president", "President 1");

			if (presidentName == null)
				return;

			presidentName = presidentName.toUpperCase();
			// Find president with that name
			List<President> presidents = null;
			try {
				presidents = EntityUtils.loadByCondition(new Condition(
						"PERSON_NAME", presidentName), President.class,
						"PERSON_NAME");
			} catch (BasketException e1) {
				e1.printStackTrace();
			}
			// If exist president, show its information
			if (presidents.size() > 0) {
				president = presidents.get(0);
				setTextField(president);
				JOptionPane.showMessageDialog(this, "President founded",
						"Notice", JOptionPane.INFORMATION_MESSAGE);
				formState = FormState.FIND;
			} else {
				// If not exist president, show error message
				JOptionPane.showMessageDialog(this,
						"Can not found that president", "Error",
						JOptionPane.ERROR_MESSAGE);
				formState = FormState.INITIAL;
				return;
			}

		} else if ("New".equals(e.getActionCommand())) {
			pressUpdate = false;
			formState = FormState.NEW;

		} else if ("Save".equals(e.getActionCommand())) {
			if (isEmptyData()) {
				JOptionPane.showMessageDialog(this,
						"Please give enought information of president",
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
					president = makePresident();
					if (isDuplicateData(president)) {
						JOptionPane
								.showMessageDialog(
										this,
										"Can not insert new president which is duplicate ID/ Name with existing president",
										"Error", JOptionPane.ERROR_MESSAGE);
					} else {
						// Save new president
						savePresident();
						JOptionPane.showMessageDialog(this,
								"Save new president successful", "Success",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					// Update existing president
					updatePresident();
					JOptionPane.showMessageDialog(this,
							"Update president successful", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
			formState = FormState.INITIAL;

		} else if ("Update".equals(e.getActionCommand())) {
			pressUpdate = true;
			formState = FormState.INITIAL;
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

	private void deletePresident() {
		try {
			president.setDeleted(1);
			president.update();
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}

	}

	private void initComponents() {

		jButton2 = new JButton();
		txtPresidentID = new JTextField();
		jLabel1 = new JLabel();
		txtPresidentName = new JTextField();
		jLabel2 = new JLabel();
		btnNew = new JButton();
		btnFind = new JButton();
		btnSave = new JButton();
		btnCancel = new JButton();
		btnUpdate = new JButton();
		btnDelete = new JButton();

		jButton2.setText("jButton2");

		jLabel1.setText("President ID");

		jLabel2.setText("President name");

		btnNew.setText("New");
		btnNew.setToolTipText("Add new president");

		btnFind.setText("Find");
		btnFind.setToolTipText("Find an existing president");

		btnSave.setText("Save");
		btnSave.setToolTipText("Save new president");

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
														txtPresidentName,
														GroupLayout.PREFERRED_SIZE,
														234,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														txtPresidentID,
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
														txtPresidentID,
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
														txtPresidentName,
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
				txtPresidentID, txtPresidentName });

	}// </editor-fold>

	private boolean isDuplicateData(President presidentToSave) {
		List<President> president = null;

		// check if duplicate president ID
		try {
			president = EntityUtils.loadByCondition(new Condition("id",
					presidentToSave.getId().toString()), President.class, "id");
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
		if (president.size() > 0)
			return true;

		// Check if duplicate president name
		president = null;
		try {
			president = EntityUtils.loadByCondition(new Condition(
					"Person_Name", presidentToSave.getPersonName()),
					President.class, "PERSON_NAME");
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
		if (president.size() > 0)
			return true;

		return false;

	}

	private boolean isEmptyData() {
		if (txtPresidentID.getText().equals("")
				|| txtPresidentName.getText().equals(""))
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
		if (!isInteger(txtPresidentID.getText()))
			return true;
		return false;
	}

	private President makePresident() {
		setFieldtoAttribute();
		try {
			president.setDeleted(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return president;
	}

	private void savePresident() {
		setFieldtoAttribute();
		try {
			president.setDeleted(0);
			president.save();
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void setFieldtoAttribute() {
		try {
			president.setId(Integer.parseInt(txtPresidentID.getText().trim()));
			president.setPersonName(txtPresidentName.getText().trim());
		} catch (NumberFormatException | SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void setTextField(President foundedPresident) {
		try {
			txtPresidentID.setText(foundedPresident.getId().toString());
			txtPresidentName.setText(foundedPresident.getPersonName());
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

	private void updatePresident() {
		setFieldtoAttribute();
		try {
			president.setDeleted(0);
			president.update();
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
	}
}
