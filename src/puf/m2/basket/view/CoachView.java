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

import puf.m2.basket.model.entity.Coach;
import puf.m2.basket.model.support.BasketException;
import puf.m2.basket.model.support.Condition;
import puf.m2.basket.model.support.EntityUtils;

public class CoachView extends JPanel implements ActionListener {
	private static final long serialVersionUID = -1265877891170734060L;

	// Variables declaration - do not modify
	Coach coach = new Coach();
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
	private JTextField txtCoachID;
	private JTextField txtCoachName;

	// End of variables declaration
	public CoachView() {
		initComponents();
		addActionListeners();

		formState = FormState.INITIAL;
		//updateForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Cancel".equals(e.getActionCommand())) {
			formState = FormState.INITIAL;

		} else if ("Delete".equals(e.getActionCommand())) {
			Coach coachToDelete = null;

			if (!isInteger(txtCoachID.getText())) {
				JOptionPane.showMessageDialog(this,
						"Please input correct coach ID", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			try {
				coach = makeCoach();
				coachToDelete = EntityUtils.loadById(coach.getId(),
						Coach.class);
			} catch (BasketException | SQLException e1) {
				e1.printStackTrace();
			}
			if (coachToDelete == null) {
				JOptionPane.showMessageDialog(this,
						"This coach ID does not exists, can not delete",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (JOptionPane.showConfirmDialog(this,
					"Do you really to delete this coach?",
					"Confirm to delete coach", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				// Delete this coach
				deleteCoach();
				JOptionPane.showMessageDialog(this, "This coach is deleted");
			}
			formState = FormState.INITIAL;

		} else if ("Find".equals(e.getActionCommand())) {
			formState = FormState.FIND;
			String coachName = (String) JOptionPane.showInputDialog(this,
					"Please give a name of coach", "Coach 1");

			if (coachName == null)
				return;

			coachName = coachName.toUpperCase();
			// Find coach with that name
			List<Coach> coaches = null;
			try {
				coaches = EntityUtils.loadByCondition(new Condition(
						"PERSON_NAME", coachName), Coach.class,"PERSON_NAME");
			} catch (BasketException e1) {
				e1.printStackTrace();
			}
			// If exist coach, show its information
			if (coaches.size() > 0) {
				coach = coaches.get(0);
				setTextField(coach);
				JOptionPane.showMessageDialog(this, "Coach founded",
						"Notice", JOptionPane.INFORMATION_MESSAGE);
				formState = FormState.FIND;

			} else {
				// If not exist coach, show error message
				JOptionPane.showMessageDialog(this,
						"Can not found that coach", "Error",
						JOptionPane.ERROR_MESSAGE);
				formState = FormState.INITIAL;
			}

		} else if ("New".equals(e.getActionCommand())) {
			pressUpdate=false;
			formState = FormState.NEW;

		} else if ("Save".equals(e.getActionCommand())) {
			if (isEmptyData()) {
				JOptionPane.showMessageDialog(this,
						"Please give enought information of coach",
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
					coach = makeCoach();
					if (isDuplicateData(coach)) {
						JOptionPane
						.showMessageDialog(
								this,
								"Can not insert new coach which is duplicate ID/ Name with existing coach",
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						// Save new coach
						saveCoach(coach);
						JOptionPane.showMessageDialog(this,
								"Save new coach successful", "Success",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					// Update existing coach
					updateCoach(coach);
					JOptionPane.showMessageDialog(this,
							"Update coach successful", "Success",
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

	private void deleteCoach() {
		try {
			coach.setDeleted(1);
			coach.update();	
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
		txtCoachID = new JTextField();
		jLabel1 = new JLabel();
		txtCoachName = new JTextField();
		jLabel2 = new JLabel();
		btnNew = new JButton();
		btnFind = new JButton();
		btnSave = new JButton();
		btnCancel = new JButton();
		btnUpdate = new JButton();
		btnDelete = new JButton();

		jButton2.setText("jButton2");

		jLabel1.setText("Coach ID");

		jLabel2.setText("Coach name");

		btnNew.setText("New");
		btnNew.setToolTipText("Add new coach");

		btnFind.setText("Find");
		btnFind.setToolTipText("Find an existing coach");

		btnSave.setText("Save");
		btnSave.setToolTipText("Save new coach");

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
								GroupLayout.PREFERRED_SIZE,
								76,
								GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnFind,
												GroupLayout.PREFERRED_SIZE,
												78,
												GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(
														LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(btnCancel,
																GroupLayout.PREFERRED_SIZE,
																78,
																GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(btnSave,
																				GroupLayout.PREFERRED_SIZE,
																				76,
																				GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(btnUpdate,
																								GroupLayout.PREFERRED_SIZE,
																								78,
																								GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(
																										LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(btnDelete,
																												GroupLayout.PREFERRED_SIZE,
																												82,
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
																																								txtCoachName,
																																								GroupLayout.PREFERRED_SIZE,
																																								234,
																																								GroupLayout.PREFERRED_SIZE)
																																								.addComponent(
																																										txtCoachID,
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
												txtCoachID,
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
																				txtCoachName,
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

		layout.linkSize(SwingConstants.VERTICAL,
				new java.awt.Component[] { txtCoachID, txtCoachName });

	}// </editor-fold>

	private boolean isDuplicateData(Coach presidentToSave) {
		List<Coach> coach = null;

		// check if duplicate coach ID
		try {
			coach = EntityUtils.loadByCondition(new Condition("id",
					presidentToSave.getId().toString()), Coach.class,"id");
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
		if (coach.size() > 0)
			return true;

		// Check if duplicate coach name
		coach = null;
		try {
			coach = EntityUtils.loadByCondition(new Condition(
					"Person_Name", presidentToSave.getPersonName()),
					Coach.class,"PERSON_NAME");
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
		if (coach.size() > 0)
			return true;

		return false;

	}

	private boolean isEmptyData() {
		if (txtCoachID.getText().equals("")
				|| txtCoachName.getText().equals(""))
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
		if (!isInteger(txtCoachID.getText()))
			return true;
		return false;
	}

	private Coach makeCoach() {
		setFieldtoAttribute();
		try {
			coach.setDeleted(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return coach;
	}

	private void saveCoach(Coach coach) {
		setFieldtoAttribute();
		try {
			coach.setDeleted(0);
			coach.save();
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void setFieldtoAttribute(){
		try {
			coach.setId(Integer.parseInt(txtCoachID.getText().trim()));
			coach.setPersonName(txtCoachName.getText().trim());
		} catch (NumberFormatException | SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void setTextField(Coach foundedPresident) {
		try {
			txtCoachID.setText(foundedPresident.getId().toString());
			txtCoachName.setText(foundedPresident.getPersonName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updateCoach(Coach president2) {
		setFieldtoAttribute();
		try {
			coach.setDeleted(0);
			coach.update();
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void updateForm() {
		switch (formState){
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
}
