package puf.m2.basket.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import puf.m2.basket.model.entity.Category;

public class CategoryView extends javax.swing.JPanel implements ActionListener {
	private static final long serialVersionUID = 1945726675774788232L;

	// Variables declaration - do not modify
	FormState formState;
	Category categoryModel;
	
	private JButton btnAddTeam;
    private JButton btnCancel;
    private JButton btnDelete;
    private JButton btnFind;
    private JButton btnNew;
    private JButton btnSave;
    private JButton btnUpdate;
    private javax.swing.JComboBox<String> cboTeam;
    private JButton jButton2;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel8;
    private JScrollPane jScrollPane1;
    private JList<String> lstTeam;
    private JTextField txtCategoryID;
    private JTextField txtCategoryName;
    // End of variables declaration

    public CategoryView() {
    	initComponents();
    	addActionListeners();
    	
    	formState = FormState.NORMAL;
    	categoryModel = new Category();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Cancel".equals(e.getActionCommand())) {
			formState = FormState.NORMAL;
		} else if ("Delete".equals(e.getActionCommand())) {
			formState = FormState.DELETE;
			if (JOptionPane.showConfirmDialog(this,
					"Do you really to delete this category?",
					"Confirm to delete category", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				// Delete this category
				JOptionPane.showMessageDialog(this, "This category is deleted");
			}
		} else if ("Find".equals(e.getActionCommand())) {
			formState = FormState.FIND;
			String categoryName = (String) JOptionPane.showInputDialog(this,
					"Please give a name of category", "Category 1");
			// Find category with that name
			// If exist category, show its information
			// If not exist category, show error message */

		} else if ("New".equals(e.getActionCommand())) {
			formState = FormState.NEW;
		} else if ("Save".equals(e.getActionCommand())) {
			formState = FormState.SAVE;
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
			}
			else {
				if (formState == FormState.NEW) {
					if (isDuplicateData()) {
						JOptionPane
						.showMessageDialog(
								this,
								"Can not insert new category which is duplicate ID/ Name with existing category",
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						// Save new category
						saveCategory();
						JOptionPane.showMessageDialog(this,
								"Save new category successful", "Success",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else if (formState == FormState.UPDATE) {
					// Update existing category
					updateCategory();
					JOptionPane.showMessageDialog(this,
							"Update new category successful", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} else if ("Update".equals(e.getActionCommand())) {
			formState = FormState.UPDATE;
		}

		// Finally for each button
		updateForm();
	}

	private void updateCategory() {
		
	}

	private void saveCategory() {
		
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

			txtCategoryID.setText("");
			txtCategoryName.setText("");

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

	private boolean isEmptyData() {
		if (txtCategoryID.getText().trim()==""
				|| txtCategoryName.getText().trim()=="")
			return true;
		return false;
	}
	
	private boolean isTypeMismatch() {
		if (!isInteger(txtCategoryID.getText()))
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
	
	private boolean isDuplicateData() {
		return false;
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
        cboTeam = new JComboBox<String>();
        jLabel8 = new JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstTeam = new javax.swing.JList<String>();
        btnAddTeam = new JButton();

        jButton2.setText("jButton2");

        jLabel1.setText("Category ID");

        jLabel2.setText("Category name");

        btnNew.setText("New");
        btnNew.setToolTipText("Add new category");

        btnFind.setText("Find");
        btnFind.setToolTipText("Find an existing category");

        btnSave.setText("Save");
        btnSave.setToolTipText("Save new category");

        btnCancel.setText("Cancel");

        btnUpdate.setText("Update");

        btnDelete.setText("Delete");

        cboTeam.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Choose category(s) in list" }));

        jLabel8.setText("Team");

        jScrollPane1.setViewportView(lstTeam);

        btnAddTeam.setText("Add");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                            .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnFind, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                           .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cboTeam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAddTeam))
                            .addComponent(txtCategoryID, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCategoryID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboTeam)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(btnAddTeam)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew)
                    .addComponent(btnFind)
                    )
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnCancel)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete))
                .addGap(23, 23, 23))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtCategoryID, txtCategoryName});

    }// </editor-fold>
}
