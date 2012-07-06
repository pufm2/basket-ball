package puf.m2.basket.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class TeamView extends javax.swing.JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -580791303394022630L;

	// Variables declaration - do not modify
	private JButton btnAddPlayer;

	private JButton btnCancel;

	private JButton btnClose;

	private JButton btnDelete;
	private JButton btnFind;
	private JButton btnNew;
	private JButton btnSave;
	private JButton btnUpdate;
	private JComboBox<String> cboPlayer;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel8;
	private JScrollPane jScrollPane1;
	private JList<String> lstPlayer;
	private JTextField txtTeamID;
	private JTextField txtTeamName;
	public TeamView() {
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

	// End of variables declaration

	private void initComponents() {

		txtTeamID = new javax.swing.JTextField();
		jLabel1 = new JLabel();
		txtTeamName = new javax.swing.JTextField();
		jLabel2 = new JLabel();
		btnNew = new javax.swing.JButton();
		btnFind = new javax.swing.JButton();
		btnClose = new javax.swing.JButton();
		btnSave = new javax.swing.JButton();
		btnCancel = new javax.swing.JButton();
		btnUpdate = new javax.swing.JButton();
		btnDelete = new javax.swing.JButton();
		cboPlayer = new JComboBox<String>();
		jLabel8 = new JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		lstPlayer = new javax.swing.JList<String>();
		btnAddPlayer = new javax.swing.JButton();

		jLabel1.setText("Team ID");

		jLabel2.setText("Team name");

		btnNew.setText("New");
		btnNew.setToolTipText("Add new Team");

		btnFind.setText("Find");
		btnFind.setToolTipText("Find an existing Team");

		btnClose.setText("Close");
		btnClose.setToolTipText("Close this form");

		btnSave.setText("Save");
		btnSave.setToolTipText("Save new Team");

		btnCancel.setText("Cancel");

		btnUpdate.setText("Update");

		btnDelete.setText("Delete");

		cboPlayer.setModel(new javax.swing.DefaultComboBoxModel<String>(
				new String[] { "Choose player in list" }));

		jLabel8.setText("Player");

		jScrollPane1.setViewportView(lstPlayer);

		btnAddPlayer.setText("Add");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addContainerGap()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						btnSave,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						76,
																						Short.MAX_VALUE)
																				.addComponent(
																						btnNew,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						btnFind,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						btnCancel,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						78,
																						Short.MAX_VALUE))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						btnClose,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						btnUpdate,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						78,
																						Short.MAX_VALUE))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		btnDelete,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		82,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(19, 19,
																		19)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
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
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						txtTeamName,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						234,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										cboPlayer,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										javax.swing.GroupLayout.DEFAULT_SIZE,
																										javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(
																										btnAddPlayer))
																				.addComponent(
																						txtTeamID,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						91,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						jScrollPane1,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						311,
																						javax.swing.GroupLayout.PREFERRED_SIZE))))
								.addContainerGap(34, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														txtTeamID,
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
														txtTeamName,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel8)
												.addComponent(btnAddPlayer)
												.addComponent(cboPlayer))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jScrollPane1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(48, 48, 48)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(btnNew)
												.addComponent(btnFind)
												.addComponent(btnClose))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(btnSave)
												.addComponent(btnCancel)
												.addComponent(btnUpdate)
												.addComponent(btnDelete))
								.addGap(23, 23, 23)));

		layout.linkSize(javax.swing.SwingConstants.VERTICAL,
				new java.awt.Component[] { txtTeamID, txtTeamName });

	}// </editor-fold>
}
