package puf.m2.basket.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ClubView extends javax.swing.JPanel implements ActionListener {
	private static final long serialVersionUID = 8257054384536718934L;

    // Variables declaration - do not modify
    private JButton btnAdd;
    
	private JButton btnCancel;
   
	private JButton btnDelete;

    private JButton btnFind;                                       

    private JButton btnNew;
    private JButton btnSave;
    private JButton btnUpdate;
    private JComboBox<String> cboCategory;
    private JComboBox<String> cboOffice;
    private JComboBox<String> cboPresident;
    private JComboBox<String> cboSecretary;
    private JComboBox<String> cboTreasurer;
    private JComboBox<String> cboVicePresident;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> lstCategory;
    private JTextField txtClubID;
    private JTextField txtClubName;
    // End of variables declaration
    public ClubView() {
        initComponents();addActionListeners();
	}
    @Override
	public void actionPerformed(ActionEvent e) {
		if ("Cancel".equals(e.getActionCommand())) {

		} else if ("Close".equals(e.getActionCommand())) {

		} else if ("Delete".equals(e.getActionCommand())) {

		} else if ("Find".equals(e.getActionCommand())) {

		} else if ("New".equals(e.getActionCommand())) {

		} else if ("Save".equals(e.getActionCommand())) {

		} else if ("Update".equals(e.getActionCommand())) {

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

        jLabel1 = new JLabel();
        txtClubID = new JTextField();
        jLabel2 = new JLabel();
        txtClubName = new JTextField();
        jLabel3 = new JLabel();
        cboOffice = new JComboBox<String>();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        cboCategory = new JComboBox<String>();
        cboTreasurer = new JComboBox<String>();
        cboVicePresident = new JComboBox<String>();
        cboPresident = new JComboBox<String>();
        cboSecretary = new JComboBox<String>();
        btnCancel = new JButton();
        btnSave = new JButton();
        btnDelete = new JButton();
        btnUpdate = new JButton();
        btnNew = new JButton();
        btnFind = new JButton();
        btnAdd = new JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstCategory = new javax.swing.JList<String>();

        jLabel1.setText("Club ID");

        jLabel2.setText("Club name");

        jLabel3.setText("Office");

        cboOffice.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Choose office from list" }));

        jLabel4.setText("President");

        jLabel5.setText("Vice president");

        jLabel6.setText("Treasurer");

        jLabel7.setText("Secretary");

        jLabel8.setText("Categories");

        cboCategory.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Choose category(ies) from list" }));

        cboTreasurer.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Choose treasurer from list" }));

        cboVicePresident.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Choose vice president from list" }));

        cboPresident.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Choose president from list" }));

        cboSecretary.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Choose secretary from list" }));

        btnCancel.setText("Cancel");

        btnSave.setText("Save");
        btnSave.setToolTipText("Save new office");

        btnDelete.setText("Delete");

        btnUpdate.setText("Update");

        btnNew.setText("New");
        btnNew.setToolTipText("Add new office");

        btnFind.setText("Find");
        btnFind.setToolTipText("Find an existing office");

        btnAdd.setText("Add");

        jScrollPane1.setViewportView(lstCategory);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtClubName)
                                    .addComponent(txtClubID, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboTreasurer, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboPresident, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboVicePresident, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboSecretary, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboOffice, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAdd))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnFind, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                  
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtClubID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtClubName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboOffice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboPresident, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cboVicePresident, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTreasurer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboSecretary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cboCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addContainerGap())
        );
    }// </editor-fold>
}
