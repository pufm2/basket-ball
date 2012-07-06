package puf.m2.basket.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.toedter.calendar.JCalendar;

public class PersonView extends javax.swing.JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1265877891170734060L;

	// Variables declaration - do not modify
	private JButton btnCancel;
	private JButton btnClose;
	private JButton btnDelete;
	private JButton btnFind;
	private JButton btnNew;
	private JButton btnSave;
	private JButton btnUpdate;

	private JComboBox<String> cboPosition;
	private JButton jButton2;
	private JLabel jLabel1;
	private JLabel jLabel10;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JLabel jLabel8;
	private JLabel jLabel9;
	private JTextField txtAddressCity;
	private JTextField txtAddressDistrict;
	private JTextField txtAddressNumber;
	private JTextField txtAddressStreet;
	private JCalendar txtBirthday;
	private JTextField txtLicenseNumber;
	private JTextField txtPersonID;
	private JTextField txtPersonName;

	// End of variables declaration
	public PersonView() {
		initComponents();
		addActionListeners();
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jButton2 = new JButton();
		txtPersonID = new JTextField();
		jLabel1 = new JLabel();
		txtPersonName = new JTextField();
		jLabel2 = new JLabel();
		jLabel3 = new JLabel();
		btnNew = new JButton();
		btnFind = new JButton();
		btnClose = new JButton();
		btnSave = new JButton();
		btnCancel = new JButton();
		btnUpdate = new JButton();
		btnDelete = new JButton();
		txtAddressNumber = new JTextField();
		jLabel4 = new JLabel();
		txtAddressStreet = new JTextField();
		jLabel5 = new JLabel();
		jLabel6 = new JLabel();
		jLabel7 = new JLabel();
		txtAddressDistrict = new JTextField();
		txtAddressCity = new JTextField();
		cboPosition = new JComboBox<String>();
		jLabel8 = new JLabel();
		jLabel9 = new JLabel();
		txtLicenseNumber = new JTextField();
		jLabel10 = new JLabel();
		txtBirthday = new JCalendar();

		jButton2.setText("jButton2");

		jLabel1.setText("Person ID");

		jLabel2.setText("Person name");

		jLabel3.setText("Address");

		btnNew.setText("New");
		btnNew.setToolTipText("Add new office");

		btnFind.setText("Find");
		btnFind.setToolTipText("Find an existing office");

		btnClose.setText("Close");
		btnClose.setToolTipText("Close this form");

		btnSave.setText("Save");
		btnSave.setToolTipText("Save new office");

		btnCancel.setText("Cancel");

		btnUpdate.setText("Update");

		btnDelete.setText("Delete");

		txtAddressNumber.setName("txtAddressNumber");

		jLabel4.setText("Number");

		jLabel5.setText("Street");

		jLabel6.setText("District");

		jLabel7.setText("City");

		cboPosition.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Coach", "Player", "President", "Secretary", "Treasure",
				"Vice president" }));

		jLabel8.setText("Position");

		jLabel9.setText("License number");

		jLabel10.setText("Birthday");

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
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(0,
																		0,
																		Short.MAX_VALUE))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(19, 19,
																		19)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										jLabel3,
																										javax.swing.GroupLayout.DEFAULT_SIZE,
																										javax.swing.GroupLayout.DEFAULT_SIZE,
																										Short.MAX_VALUE)
																								.addGap(238,
																										238,
																										238))
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																												.addGroup(
																														layout.createSequentialGroup()
																																.addComponent(
																																		jLabel10)
																																.addGap(38,
																																		38,
																																		38)
																																.addComponent(
																																		txtBirthday,
																																		javax.swing.GroupLayout.PREFERRED_SIZE,
																																		92,
																																		javax.swing.GroupLayout.PREFERRED_SIZE))
																												.addGroup(
																														layout.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.TRAILING)
																																.addGroup(
																																		layout.createSequentialGroup()
																																				.addGroup(
																																						layout.createParallelGroup(
																																								javax.swing.GroupLayout.Alignment.LEADING)
																																								.addComponent(
																																										jLabel4)
																																								.addGroup(
																																										layout.createParallelGroup(
																																												javax.swing.GroupLayout.Alignment.TRAILING)
																																												.addComponent(
																																														jLabel6)
																																												.addComponent(
																																														jLabel7,
																																														javax.swing.GroupLayout.Alignment.LEADING)
																																												.addComponent(
																																														jLabel5,
																																														javax.swing.GroupLayout.Alignment.LEADING)))
																																				.addPreferredGap(
																																						javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																				.addGroup(
																																						layout.createParallelGroup(
																																								javax.swing.GroupLayout.Alignment.LEADING,
																																								false)
																																								.addComponent(
																																										txtAddressNumber,
																																										javax.swing.GroupLayout.PREFERRED_SIZE,
																																										81,
																																										javax.swing.GroupLayout.PREFERRED_SIZE)
																																								.addComponent(
																																										txtAddressStreet)
																																								.addComponent(
																																										txtAddressDistrict)
																																								.addComponent(
																																										txtAddressCity,
																																										javax.swing.GroupLayout.PREFERRED_SIZE,
																																										234,
																																										javax.swing.GroupLayout.PREFERRED_SIZE)))
																																.addGroup(
																																		layout.createSequentialGroup()
																																				.addGroup(
																																						layout.createParallelGroup(
																																								javax.swing.GroupLayout.Alignment.LEADING)
																																								.addComponent(
																																										jLabel1)
																																								.addComponent(
																																										jLabel2)
																																								.addComponent(
																																										jLabel8)
																																								.addComponent(
																																										jLabel9))
																																				.addPreferredGap(
																																						javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																				.addGroup(
																																						layout.createParallelGroup(
																																								javax.swing.GroupLayout.Alignment.LEADING)
																																								.addComponent(
																																										txtPersonName,
																																										javax.swing.GroupLayout.PREFERRED_SIZE,
																																										234,
																																										javax.swing.GroupLayout.PREFERRED_SIZE)
																																								.addComponent(
																																										cboPosition,
																																										javax.swing.GroupLayout.PREFERRED_SIZE,
																																										javax.swing.GroupLayout.DEFAULT_SIZE,
																																										javax.swing.GroupLayout.PREFERRED_SIZE)
																																								.addComponent(
																																										txtLicenseNumber,
																																										javax.swing.GroupLayout.PREFERRED_SIZE,
																																										92,
																																										javax.swing.GroupLayout.PREFERRED_SIZE)
																																								.addComponent(
																																										txtPersonID,
																																										javax.swing.GroupLayout.PREFERRED_SIZE,
																																										91,
																																										javax.swing.GroupLayout.PREFERRED_SIZE)))))
																								.addGap(0,
																										111,
																										Short.MAX_VALUE)))))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														txtPersonID,
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
														txtPersonName,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														cboPosition,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel8))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel9)
												.addComponent(
														txtLicenseNumber,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														txtBirthday,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel10))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jLabel3)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(
														txtAddressNumber,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel4))
								.addGap(6, 6, 6)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														txtAddressStreet,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel5))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel6)
												.addComponent(
														txtAddressDistrict,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel7)
												.addComponent(
														txtAddressCity,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
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
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));

		layout.linkSize(javax.swing.SwingConstants.VERTICAL,
				new java.awt.Component[] { cboPosition, txtLicenseNumber,
						txtPersonID, txtPersonName });

	}// </editor-fold>

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
}
