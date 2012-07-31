package puf.m2.basket.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListModel;
import javax.swing.SwingConstants;

import puf.m2.basket.model.entity.Category;
import puf.m2.basket.model.entity.Team;
import puf.m2.basket.model.support.BasketException;
import puf.m2.basket.model.support.Condition;
import puf.m2.basket.model.support.EntityUtils;

public class CategoryView extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1945726675774788232L;

	private JButton btnAddTeam;
	private JButton btnCancel;

	private JButton btnDelete;
	private JButton btnFind;
	private JButton btnNew;
	private JButton btnSave;
	private JButton btnUpdate;

	// Variables declaration - do not modify
	Category category;
	private JComboBox<Team> cboTeam;
	FormState formState;
	boolean pressUpdate = false;
	ListModel<Team> listModelTeam;
	
	private JButton jButton2;
	private JLabel jLabel1;

	private JLabel jLabel2;
	private JLabel jLabel8;
	private JScrollPane jScrollPane1;
	
	private JList<Team> lstTeam;
		
	private JTextField txtCategoryID;
	private JTextField txtCategoryName;

	// End of variables declaration

	public CategoryView() {
		initComponents();
		addActionListeners();

		listModelTeam = new DefaultListModel<Team>();
		category = new Category();
		formState = FormState.INITIAL;
		updateForm();
		fillComboTeam();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if ("Cancel".equals(e.getActionCommand())) {
			formState = FormState.INITIAL;

		} else if ("Delete".equals(e.getActionCommand())) {
			Category categoryToDelete = null;

			if (!isInteger(txtCategoryID.getText())) {
				JOptionPane.showMessageDialog(this,
						"Please input correct category ID", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			try {
				category = makeCategory();
				categoryToDelete = EntityUtils.loadById(category.getId(),
						Category.class);
			} catch (BasketException | SQLException e1) {
				e1.printStackTrace();
			}
			if (categoryToDelete == null) {
				JOptionPane.showMessageDialog(this,
						"This category ID does not exists, can not delete",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (JOptionPane.showConfirmDialog(this,
					"Do you really to delete this category?",
					"Confirm to delete category", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				deleteCategory();
				JOptionPane.showMessageDialog(this, "This category is deleted");
			}
			formState = FormState.INITIAL;

		} else if ("Find".equals(e.getActionCommand())) {
			String categoryName = (String) JOptionPane.showInputDialog(this,
					"Please give a name of category", "Category 1");

			if (categoryName == null)
				return;

			categoryName = categoryName.toUpperCase();

			// Find category with that name
			List<Category> categorys = null;
			try {
				categorys = EntityUtils.loadByCondition(new Condition(
						"CATEGORY_NAME", categoryName), Category.class,
						"CATEGORY_NAME");
			} catch (BasketException e1) {
				e1.printStackTrace();
			}

			// If exist category, show its information
			if (categorys.size() > 0) {
				category = categorys.get(0);
				setTextField(category);

				JOptionPane.showMessageDialog(this, "Category founded",
						"Notice", JOptionPane.INFORMATION_MESSAGE);
				formState = FormState.FIND;
			} else {
				// If not exist category, show error message
				JOptionPane.showMessageDialog(this,
						"Can not found that category", "Error",
						JOptionPane.ERROR_MESSAGE);
				formState = FormState.INITIAL;
			}

		} else if ("New".equals(e.getActionCommand())) {
			pressUpdate = false;
			formState = FormState.NEW;

		} else if ("Save".equals(e.getActionCommand())) {
			if (isEmptyData()) {
				JOptionPane.showMessageDialog(this,
						"Please give enought information of category", "Error",
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
					category = makeCategory();
					if (isDuplicateData(category)) {
						JOptionPane
								.showMessageDialog(
										this,
										"Can not insert new category which is duplicate ID/ Name with existing category",
										"Error", JOptionPane.ERROR_MESSAGE);
					} else {
						// Save new category
						saveCategory(category);
						JOptionPane.showMessageDialog(this,
								"Save new category successful", "Success",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					// Update existing category
					category = makeCategory();
					updateCategory(category);
					JOptionPane.showMessageDialog(this,
							"Update category successful", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
			formState = FormState.INITIAL;

		} else if ("Update".equals(e.getActionCommand())) {
			pressUpdate = true;
			formState = FormState.NEW;

		} else if ("AddTeam".equals(e.getActionCommand())) {
			((DefaultListModel<Team>) listModelTeam).addElement((Team) cboTeam
					.getSelectedItem());
			lstTeam.setModel(listModelTeam);
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

		btnAddTeam.setActionCommand("AddTeam");
		btnAddTeam.addActionListener(this);
	}

	private void deleteCategory() {
		try {
			category.setDeleted(1);
			category.update();
		} catch (SQLException | BasketException e) {
			e.printStackTrace();
		}

	}

	private void fillComboTeam() {
		try {
			List<Team> teams = EntityUtils.loadByCondition(null, Team.class, null);
			if (teams.size() <= 0) {
				JOptionPane
						.showMessageDialog(
								this,
								"There are no team in this system \n Please add team first",
								"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			cboTeam.removeAll();
			for (Team team : teams) {
				cboTeam.addItem(team);
			}
		} catch (BasketException e) {
			e.printStackTrace();
		}
	}

	private void initComponents() {

		jButton2 = new JButton();
		txtCategoryID = new JTextField();
		jLabel1 = new JLabel();
		txtCategoryName = new JTextField();
		jLabel2 = new JLabel();
		btnNew = new JButton();
		btnFind = new JButton();
		btnSave = new JButton();
		btnCancel = new JButton();
		btnUpdate = new JButton();
		btnDelete = new JButton();
		cboTeam = new JComboBox<Team>();
		jLabel8 = new JLabel();
		jScrollPane1 = new JScrollPane();
		lstTeam = new JList<Team>();
		btnAddTeam = new JButton();

		jButton2.setText("jButton2");

		jLabel1.setText("Category ID");

		jLabel2.setText("Category name");

		btnNew.setText("New");
		btnNew.setToolTipText("Add new office");

		btnFind.setText("Find");
		btnFind.setToolTipText("Find an existing office");

		btnSave.setText("Save");
		btnSave.setToolTipText("Save new office");

		btnCancel.setText("Cancel");

		btnUpdate.setText("Update");

		btnDelete.setText("Delete");

		jLabel8.setText("Category");

		jScrollPane1.setViewportView(lstTeam);

		btnAddTeam.setText("Add");

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(19, 19, 19)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		btnNew,
																		GroupLayout.PREFERRED_SIZE,
																		76,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		btnFind,
																		GroupLayout.PREFERRED_SIZE,
																		78,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		btnCancel,
																		GroupLayout.PREFERRED_SIZE,
																		78,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		btnSave,
																		GroupLayout.PREFERRED_SIZE,
																		76,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		btnUpdate,
																		GroupLayout.PREFERRED_SIZE,
																		78,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		btnDelete,
																		GroupLayout.PREFERRED_SIZE,
																		82,
																		GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jLabel1)
																				.addComponent(
																						jLabel2)
																				.addComponent(
																						jLabel8))
																.addGap(23, 23,
																		23)
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.LEADING)
																				.addComponent(
																						txtCategoryName,
																						GroupLayout.PREFERRED_SIZE,
																						234,
																						GroupLayout.PREFERRED_SIZE)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										cboTeam,
																										GroupLayout.PREFERRED_SIZE,
																										GroupLayout.DEFAULT_SIZE,
																										GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(
																										LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(
																										btnAddTeam))
																				.addComponent(
																						txtCategoryID,
																						GroupLayout.PREFERRED_SIZE,
																						91,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						jScrollPane1,
																						GroupLayout.PREFERRED_SIZE,
																						362,
																						GroupLayout.PREFERRED_SIZE))))
								.addContainerGap(29, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
												.addComponent(
														txtCategoryID,
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
														txtCategoryName,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addComponent(cboTeam)
												.addGroup(
														layout.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
																.addComponent(
																		jLabel8)
																.addComponent(
																		btnAddTeam)))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jScrollPane1,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(26, 26, 26)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
												.addComponent(btnNew)
												.addComponent(btnFind)
												.addComponent(btnCancel)
												.addComponent(btnSave)
												.addComponent(btnUpdate)
												.addComponent(btnDelete))
								.addGap(27, 27, 27)));

		layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {
				txtCategoryID, txtCategoryName });

	}// </editor-fold>

	private boolean isDuplicateData(Category category) {
		List<Category> categories = null;

		// check if duplicate category ID
		try {
			categories = EntityUtils.loadByCondition(new Condition("id",
					category.getId().toString()), Category.class, "ID");
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
		if (categories.size() > 0)
			return true;

		// Check if duplicate secretary name
		categories = null;
		try {
			categories = EntityUtils.loadByCondition(new Condition(
					"Category_Name", category.getCategoryName()),
					Category.class, "CATEGORY_NAME");
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
		if (categories.size() > 0)
			return true;

		return false;

	}

	private boolean isEmptyData() {
		if (txtCategoryID.getText().equals("")
				|| txtCategoryName.getText().equals(""))
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
		if (!isInteger(txtCategoryID.getText()))
			return true;
		return false;
	}

	private Category makeCategory() {
		setFieldtoAttribute();
		try {
			category.setDeleted(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}

	private void saveCategory(Category category) {
		setFieldtoAttribute();
		try {
			category.setDeleted(0);
			category.save();
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void setFieldtoAttribute() {
		try {
			category.setId(Integer.parseInt(txtCategoryID.getText()));
			category.setCategoryName(txtCategoryName.getText());

			// Add team from list to category
			String teamName = "";
			for (int i = 0; i < lstTeam.getModel().getSize(); i++) {
				teamName = lstTeam.getModel().getElementAt(i).getTeamName();
				try {
					List<Team> teams = EntityUtils.loadByCondition(
							new Condition("Team_Name", teamName), Team.class,
							"TEAM_NAME");
					for (Team team : teams) {
						category.addTeam(team);
					}
				} catch (BasketException e) {
					e.printStackTrace();
				}
			}

		} catch (NumberFormatException | SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void setTextField(Category category) {
		try {
			txtCategoryID.setText(category.getId().toString());
			txtCategoryName.setText(category.getCategoryName());

			// Update combobox Team when change categoryID
			Team[] teams = category.getListteam().getArray();
			cboTeam.removeAllItems();
			for (Team team : teams) {
				cboTeam.addItem(team);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private void updateCategory(Category category) {
		setFieldtoAttribute();
		try {
			category.update();
		} catch (BasketException e) {
			e.printStackTrace();
		}
	}

	public void updateForm() {
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
			
			txtCategoryID.setText("");
			txtCategoryName.setText("");
			
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
