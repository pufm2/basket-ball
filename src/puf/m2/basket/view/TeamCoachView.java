package puf.m2.basket.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

public class TeamCoachView extends javax.swing.JPanel implements ActionListener{
	private static final long serialVersionUID = -8395390533641301270L;

	// Variables declaration - do not modify
    private JButton btnCancel;
    private JButton btnClose;
    private JButton btnDelete;
    private JButton btnFind;
    private JButton btnNew;
    private JButton btnSave;
    private JButton btnUpdate;
    private JComboBox<String> cboCoach;
    private JComboBox<String> cboTeam;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JCalendar txtEndDate;
    private JTextField txtID;
    private JCalendar txtStartDate;
    // End of variables declaration

    public TeamCoachView() {
    	initComponents();
    	addActionListeners();
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

		btnClose.setActionCommand("Close");
		btnClose.addActionListener(this);

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
        jLabel2 = new JLabel();
        cboTeam = new javax.swing.JComboBox<String>();
        cboCoach = new javax.swing.JComboBox<String>();
        jLabel3 = new JLabel();
        txtID = new JTextField();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        txtStartDate = new JCalendar();
        txtEndDate = new JCalendar();
        btnSave = new JButton();
        btnCancel = new JButton();
        btnUpdate = new JButton();
        btnDelete = new JButton();
        btnNew = new JButton();
        btnFind = new JButton();
        btnClose = new JButton();

        jLabel1.setText("Team");

        jLabel2.setText("Coach");

        cboTeam.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Choose team from list" }));

        cboCoach.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Choose coach from list" }));

        jLabel3.setText("ID");

        jLabel4.setText("Start date");

        jLabel5.setText("End date");

        btnSave.setText("Save");
        btnSave.setToolTipText("Save new office");

        btnCancel.setText("Cancel");

        btnUpdate.setText("Update");

        btnDelete.setText("Delete");

        btnNew.setText("New");
        btnNew.setToolTipText("Add new office");

        btnFind.setText("Find");
        btnFind.setToolTipText("Find an existing office");

        btnClose.setText("Close");
        btnClose.setToolTipText("Close this form");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboTeam, 0, 210, Short.MAX_VALUE)
                            .addComponent(txtEndDate, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                            .addComponent(txtStartDate)
                            .addComponent(cboCoach, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnFind, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTeam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboCoach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew)
                    .addComponent(btnFind)
                    .addComponent(btnClose))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnCancel)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>
}
