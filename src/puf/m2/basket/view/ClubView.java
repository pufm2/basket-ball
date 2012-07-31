package puf.m2.basket.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

import puf.m2.basket.model.entity.Category;
import puf.m2.basket.model.entity.Club;
import puf.m2.basket.model.entity.Office;
import puf.m2.basket.model.entity.President;
import puf.m2.basket.model.entity.Secretary;

import puf.m2.basket.model.entity.Treasurer;
import puf.m2.basket.model.entity.VicePresident;
import puf.m2.basket.model.entity.ref.OfficeRef;
import puf.m2.basket.model.entity.ref.TreasurerRef;
import puf.m2.basket.model.entity.ref.VicePresidentRef;
import puf.m2.basket.model.support.BasketException;
import puf.m2.basket.model.support.Condition;
import puf.m2.basket.model.support.EntityUtils;

public class ClubView extends JPanel implements ActionListener {
	private static final long serialVersionUID = 8257054384536718934L;

	private JButton btnAddCategory;
	private JButton btnCancel;
	private JButton btnDelete;

	private JButton btnFind;
	private JButton btnNew;
	private JButton btnSave;
	private JButton btnUpdate;
	private JComboBox<Category> cboCategory;
	private JComboBox<Office> cboOffice;
	private JComboBox<President> cboPresident;
	private JComboBox<Secretary> cboSecretary;
	private JComboBox<Treasurer> cboTreasurer;
	private JComboBox<VicePresident> cboVicePresident;

	// Variables declaration - do not modify
	Club club;
	FormState formState;
	ListModel<Category> listModel;
	private JList<Category> lstCategory;
	boolean pressUpdate = false;
	
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JLabel jLabel8;
	private JScrollPane jScrollPane1;
	private JTextField txtClubID;
	private JTextField txtClubName;

	public ClubView() {
		initComponents();
		addActionListeners();

		club = new Club();

		listModel = new DefaultListModel<Category>();

		fillComboOffice();
		fillComboPresident();
		fillComboVicePresident();
		fillComboSecretary();
		fillComboTreasurer();
		fillComboCategory();

		formState = FormState.INITIAL;
		updateForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Cancel".equals(e.getActionCommand())) {
			formState = FormState.INITIAL;

		} else if ("Delete".equals(e.getActionCommand())) {
			Club clubToDelete = new Club();

			if (!isInteger(txtClubID.getText())) {
				JOptionPane.showMessageDialog(this,
						"Please input correct club ID", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			try {
				clubToDelete = EntityUtils.loadById(club.getId(), Club.class);
			} catch (BasketException | SQLException e1) {
				e1.printStackTrace();
			}
			if (clubToDelete == null) {
				JOptionPane.showMessageDialog(this,
						"This club ID does not exists, can not delete",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (JOptionPane.showConfirmDialog(this,
					"Do you really to delete this club?",
					"Confirm to delete club", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				// Delete this club
				deleteClub();
				JOptionPane
						.showMessageDialog(this, "This president is deleted");
			}
			formState = FormState.INITIAL;

		} else if ("Find".equals(e.getActionCommand())) {
			formState = FormState.FIND;
			String clubName = (String) JOptionPane.showInputDialog(this,
					"Please give a name of club", "Club 1");

			if (clubName == null)
				return;

			clubName = clubName.toUpperCase();

			// Find club with that name
			List<Club> clubs = null;
			try {
				clubs = EntityUtils.loadByCondition(new Condition("CLUB_NAME",
						clubName), Club.class, "CLUB_NAME");
			} catch (BasketException e1) {
				e1.printStackTrace();
			}
			// If exist club, show its information
			if (clubs.size() > 0) {
				club = clubs.get(0);
				updateControlValues(club);

				JOptionPane.showMessageDialog(this, "Club is founded",
						"Notice", JOptionPane.INFORMATION_MESSAGE);

				formState = FormState.FIND;
			} else {
				// If not exist club, show error message
				JOptionPane.showMessageDialog(this, "Can not found that club",
						"Error", JOptionPane.ERROR_MESSAGE);
				formState = FormState.INITIAL;
			}

		} else if ("New".equals(e.getActionCommand())) {
			formState = FormState.NEW;
			fillComboCategory();
			fillComboOffice();
			fillComboPresident();
			fillComboSecretary();
			fillComboTreasurer();
			fillComboVicePresident();

		} else if ("Save".equals(e.getActionCommand())) {

			if (isEmptyData()) {
				JOptionPane.showMessageDialog(this,
						"Please give enought information of club", "Error",
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
					club = makeClub();
					if (isDuplicateData(club)) {
						JOptionPane
								.showMessageDialog(
										this,
										"Can not insert new club which is duplicate ID/ Name with existing club",
										"Error", JOptionPane.ERROR_MESSAGE);
					} else {
						// Save new club
						saveClub(club);
						JOptionPane.showMessageDialog(this,
								"Save new club successful", "Success",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					// Update existing club
					club = makeClub();
					updateClub(club);
					JOptionPane.showMessageDialog(this,
							"Update club successful", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
			formState = FormState.INITIAL;
		} else if ("Update".equals(e.getActionCommand())) {
			pressUpdate = true;
			formState = FormState.NEW;

		} else if ("AddCategory".equals(e.getActionCommand())) {
			((DefaultListModel<Category>) listModel)
					.addElement((Category) cboCategory.getSelectedItem());
			lstCategory.setModel(listModel);
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

		btnAddCategory.setActionCommand("AddCategory");
		btnAddCategory.addActionListener(this);
	}

	private void deleteClub() {
		try {
			club.setDeleted(1);
			club.update();
		} catch (SQLException | BasketException e) {
			e.printStackTrace();
		}
	}

	private void fillComboCategory() {
		try {
			List<Category> categories = EntityUtils.loadByCondition(null, Category.class, null);
			if (categories.size() <= 0) {
				JOptionPane
						.showMessageDialog(
								this,
								"There are no Category in this system \n Please add Category first",
								"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			cboCategory.removeAll();
			for (Category category : categories) {
				cboCategory.addItem(category);
			}
		} catch (BasketException e) {
			e.printStackTrace();
		}
	}

	private void fillComboOffice() {
		try {
			List<Office> offices = EntityUtils.loadByCondition(null, Office.class, null);
			if (offices.size() <= 0) {
				JOptionPane
						.showMessageDialog(
								this,
								"There are no office in this system \n Please add office first",
								"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			cboOffice.removeAll();
			for (Office office : offices) {
				cboOffice.addItem(office);
			}
		} catch (BasketException e) {
			e.printStackTrace();
		}
	}

	private void fillComboPresident() {
		try {
			List<President> presidents = EntityUtils.loadByCondition(null, President.class, null);
			if (presidents.size() <= 0) {
				JOptionPane
						.showMessageDialog(
								this,
								"There are no president in this system \n Please add president first",
								"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			cboPresident.removeAll();
			for (President president : presidents) {
				cboPresident.addItem(president);
			}
		} catch (BasketException e) {
			e.printStackTrace();
		}
	}

	private void fillComboSecretary() {
		try {
			List<Secretary> secretaries = EntityUtils.loadByCondition(null, Secretary.class, null);
			if (secretaries.size() <= 0) {
				JOptionPane
						.showMessageDialog(
								this,
								"There are no secretary in this system \n Please add secretary first",
								"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			cboSecretary.removeAll();
			for (Secretary secretary : secretaries) {
				cboSecretary.addItem(secretary);
			}
		} catch (BasketException e) {
			e.printStackTrace();
		}
	}

	private void fillComboTreasurer() {
		try {
			List<Treasurer> treasurers = EntityUtils.loadByCondition(null, Treasurer.class, null);
			if (treasurers.size() <= 0) {
				JOptionPane
						.showMessageDialog(
								this,
								"There are no secretary in this system \n Please add secretary first",
								"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			cboTreasurer.removeAll();
			for (Treasurer treasurer : treasurers) {
				cboTreasurer.addItem(treasurer);
			}
		} catch (BasketException e) {
			e.printStackTrace();
		}
	}

	private void fillComboVicePresident() {
		try {
			List<VicePresident> vicePresidents = EntityUtils.loadByCondition(null, VicePresident.class, null);
			if (vicePresidents.size() <= 0) {
				JOptionPane
						.showMessageDialog(
								this,
								"There are no Vice President in this system \n Please add Vice President first",
								"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			cboVicePresident.removeAll();
			for (VicePresident vicePresident : vicePresidents) {
				cboVicePresident.addItem(vicePresident);
			}
		} catch (BasketException e) {
			e.printStackTrace();
		}
	}

	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		txtClubID = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		txtClubName = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		cboOffice = new javax.swing.JComboBox<Office>();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		cboCategory = new javax.swing.JComboBox<Category>();
		cboTreasurer = new javax.swing.JComboBox<Treasurer>();
		cboVicePresident = new javax.swing.JComboBox<VicePresident>();
		cboPresident = new javax.swing.JComboBox<President>();
		cboSecretary = new javax.swing.JComboBox<Secretary>();
		btnCancel = new javax.swing.JButton();
		btnSave = new javax.swing.JButton();
		btnDelete = new javax.swing.JButton();
		btnUpdate = new javax.swing.JButton();
		btnNew = new javax.swing.JButton();
		btnFind = new javax.swing.JButton();
		btnAddCategory = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		lstCategory = new javax.swing.JList<Category>();

		jLabel1.setText("Club ID");

		jLabel2.setText("Club name");

		jLabel3.setText("Office");

		jLabel4.setText("President");

		jLabel5.setText("Vice president");

		jLabel6.setText("Treasurer");

		jLabel7.setText("Secretary");

		jLabel8.setText("Categories");

		btnCancel.setText("Cancel");

		btnSave.setText("Save");
		btnSave.setToolTipText("Save new office");

		btnDelete.setText("Delete");

		btnUpdate.setText("Update");

		btnNew.setText("New");
		btnNew.setToolTipText("Add new office");

		btnFind.setText("Find");
		btnFind.setToolTipText("Find an existing office");

		btnAddCategory.setText("Add");

		jScrollPane1.setViewportView(lstCategory);

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
												.addComponent(jLabel1)
												.addComponent(jLabel2)
												.addComponent(jLabel3)
												.addComponent(jLabel7)
												.addComponent(jLabel8)
												.addComponent(jLabel6)
												.addComponent(jLabel4)
												.addComponent(jLabel5)
												.addComponent(
														btnNew,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														76,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						txtClubName)
																				.addComponent(
																						txtClubID,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						74,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						cboTreasurer,
																						0,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						cboPresident,
																						0,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						cboVicePresident,
																						0,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						cboSecretary,
																						0,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						cboOffice,
																						0,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						cboCategory,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						180,
																						javax.swing.GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		btnAddCategory))
												.addGroup(
														layout.createSequentialGroup()
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
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addComponent(
														jScrollPane1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														438,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(18, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel1)
												.addComponent(
														txtClubID,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel2)
												.addComponent(
														txtClubName,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel3)
												.addComponent(
														cboOffice,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														cboPresident,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel4))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel5)
												.addComponent(
														cboVicePresident,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														cboTreasurer,
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
														cboSecretary,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel7))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel8)
												.addComponent(
														cboCategory,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(btnAddCategory))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jScrollPane1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										87,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(btnNew)
												.addComponent(btnFind)
												.addComponent(btnCancel)
												.addComponent(btnSave)
												.addComponent(btnUpdate)
												.addComponent(btnDelete))
								.addGap(45, 45, 45)));
	}// </editor-fold>

	private boolean isDuplicateData(Club club) {
		List<Club> clubs = null;

		// check if duplicate club ID
		try {
			clubs = EntityUtils.loadByCondition(new Condition("id", club
					.getId().toString()), Club.class, "id");
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
		if (clubs.size() > 0)
			return true;

		// Check if duplicate club name
		clubs = null;
		try {
			clubs = EntityUtils.loadByCondition(
					new Condition("Club_Name", club.getClubName()), Club.class,
					"Club_Name");
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
		if (clubs.size() > 0)
			return true;

		return false;

	}

	private boolean isEmptyData() {
		if (txtClubID.getText().equals("") || txtClubName.getText().equals(""))
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
		if (!isInteger(txtClubID.getText()))
			return true;
		return false;
	}

	private Club makeClub() {
		try {
			club.setDeleted(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return club;
	}

	private void saveClub(Club club) {
		setFieldtoAttribute();
		try {
			club.setDeleted(0);
			club.save();
		} catch (NumberFormatException | BasketException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void setFieldtoAttribute() {
		try {
			club.setId(Integer.parseInt(txtClubID.getText()));
			club.setClubName(txtClubName.getText());
			club.setClubOffice(EntityUtils.loadById(
					((Office) cboOffice.getSelectedItem()).getId(),
					Office.class).getRef(OfficeRef.class));
			club.setClubPresident(EntityUtils.loadById(
					((President) cboPresident.getSelectedItem()).getId(),
					President.class).getRef());
			club.setClubSecretary(EntityUtils.loadById(
					((Secretary) cboSecretary.getSelectedItem()).getId(),
					Secretary.class).getRef());
			club.setClubTreasurer(EntityUtils.loadById(
					((Treasurer) cboTreasurer.getSelectedItem()).getId(),
					Treasurer.class).getRef(TreasurerRef.class));
			club.setClubVicePresident(EntityUtils.loadById(
					((VicePresident) cboVicePresident.getSelectedItem())
							.getId(), VicePresident.class).getRef(VicePresidentRef.class));

			// Add category from list to club
			Category _category = null;
			for (int i = 0; i < lstCategory.getModel().getSize(); i++) {
				_category = lstCategory.getModel().getElementAt(i);
				try {
					List<Category> categories = EntityUtils.loadByCondition(
							new Condition("Category_Name", _category
									.getCategoryName()), Category.class,
							"Category_Name");
					for (Category category : categories) {
						club.addCategory(category);
					}
				} catch (BasketException e) {
					e.printStackTrace();
				}
			}

		} catch (NumberFormatException | SQLException | BasketException e) {
			e.printStackTrace();
		}
	}

	private void updateClub(Club club) {
		setFieldtoAttribute();
		try {
			club.update();
		} catch (NumberFormatException | BasketException e) {
			e.printStackTrace();
		}
	}

	private void updateControlValues(Club club) {
		try {
			txtClubID.setText(club.getId().toString());
			txtClubName.setText(club.getClubName());

			// Update combobox Category when change clubID
			Category[] categoreis = club.getListcategory().getArray();
			cboCategory.removeAllItems();
			for (Category category : categoreis) {
				cboCategory.addItem(category);
			}

			// Update combobox Office when change clubID
			Office office = club.getClubOffice().getValue();
			cboOffice.removeAllItems();
			cboOffice.addItem(office);

			// Update combobox President when change clubID
			President president = club.getClubPresident().getValue();
			cboPresident.removeAllItems();
			cboPresident.addItem(president);

			// Update combobox Vice President when change clubID
			VicePresident vicePresident = club.getClubVicePresident()
					.getValue();
			cboVicePresident.removeAllItems();
			cboVicePresident.addItem(vicePresident);

			// Update combobox Treasurer when change clubID
			Treasurer treasurer = club.getClubTreasurer().getValue();
			cboTreasurer.removeAllItems();
			cboTreasurer.addItem(treasurer);

			// Update combobox Secretary when change clubID
			Secretary secretary = club.getClubSecretary().getValue();
			cboSecretary.removeAllItems();
			cboSecretary.addItem(secretary);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// End of variables declaration
	private void updateForm() {
		switch (formState) {
		case INITIAL:
			btnCancel.setVisible(false);
			btnSave.setVisible(false);
			btnUpdate.setVisible(false);
			btnDelete.setVisible(false);

			btnNew.setVisible(true);
			btnFind.setVisible(true);

			txtClubID.setText("");
			txtClubName.setText("");
			break;
		case NEW:
			btnNew.setVisible(false);
			btnFind.setVisible(false);
			btnUpdate.setVisible(false);
			btnDelete.setVisible(false);

			btnSave.setVisible(true);
			btnCancel.setVisible(true);
			
			txtClubID.setText("");
			txtClubName.setText("");
			
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
