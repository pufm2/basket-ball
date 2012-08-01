package puf.m2.basket.view.stat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import puf.m2.basket.model.entity.Category;
import puf.m2.basket.model.entity.Player;
import puf.m2.basket.model.support.BasketException;
import puf.m2.basket.model.support.EntityUtils;

public class BestPlayerOfDateView extends javax.swing.JPanel implements
		ActionListener {

	private static final long serialVersionUID = 1085807853591036268L;

	// Variables declaration - do not modify
	private javax.swing.JButton btnFindBestPlayer;

	private javax.swing.JComboBox<Category> cboCategory;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JTextField txtAddressNumber;
	private javax.swing.JTextField txtBirthday;
	private javax.swing.JTextField txtCity;
	private JDateChooser txtDate;
	private javax.swing.JTextField txtDistrict;
	private javax.swing.JTextField txtID;
	private javax.swing.JTextField txtLicenseNumber;
	private javax.swing.JTextField txtPlayerName;
	private javax.swing.JTextField txtStreet;

	// End of variables declaration

	public BestPlayerOfDateView() {
		initComponents();
		addActionListeners();
		fillComboCategory();

		txtID.setEditable(false);
		txtPlayerName.setEditable(false);
		txtLicenseNumber.setEditable(false);
		txtBirthday.setEditable(false);		
		txtAddressNumber.setEditable(false);
		txtStreet.setEditable(false);
		txtDistrict.setEditable(false);
		txtCity.setEditable(false);
	}

	private void addActionListeners() {
		btnFindBestPlayer.setActionCommand("Find");
		btnFindBestPlayer.addActionListener(this);
	}

	private void fillComboCategory() {
		List<Category> categories;
		try {
			categories = EntityUtils.loadByCondition(null, Category.class,
					"CATEGORY_NAME");
			for (Category category : categories) {
				cboCategory.addItem(category);
			}
		} catch (BasketException e) {
			e.printStackTrace();
		}
	}

	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		cboCategory = new javax.swing.JComboBox<Category>();
		jLabel2 = new javax.swing.JLabel();
		txtDate = new JDateChooser();
		btnFindBestPlayer = new javax.swing.JButton();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		jLabel9 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		jLabel11 = new javax.swing.JLabel();
		jLabel12 = new javax.swing.JLabel();
		jLabel13 = new javax.swing.JLabel();
		txtID = new javax.swing.JTextField();
		txtPlayerName = new javax.swing.JTextField();
		txtLicenseNumber = new javax.swing.JTextField();
		txtBirthday = new javax.swing.JTextField();
		txtAddressNumber = new javax.swing.JTextField();
		txtStreet = new javax.swing.JTextField();
		txtDistrict = new javax.swing.JTextField();
		txtCity = new javax.swing.JTextField();

		jLabel1.setText("Choose category");

		jLabel2.setText("Choose date");

		btnFindBestPlayer.setText("Find best player");

		jLabel3.setText("Best player information");

		jLabel4.setText("ID");

		jLabel6.setText("Player name");

		jLabel7.setText("License number");

		jLabel8.setText("Birthday");

		jLabel9.setText("Address");

		jLabel10.setText("Address number");

		jLabel11.setText("Street");

		jLabel12.setText("District");

		jLabel13.setText("City");

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
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jLabel3)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGap(10,
																										10,
																										10)
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																												.addComponent(
																														jLabel9)
																												.addGroup(
																														layout.createSequentialGroup()
																																.addGroup(
																																		layout.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.LEADING)
																																				.addComponent(
																																						jLabel7)
																																				.addComponent(
																																						jLabel4)
																																				.addComponent(
																																						jLabel6)
																																				.addComponent(
																																						jLabel8))
																																.addGap(25,
																																		25,
																																		25)
																																.addGroup(
																																		layout.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.LEADING,
																																				false)
																																				.addComponent(
																																						txtID)
																																				.addComponent(
																																						txtPlayerName)
																																				.addComponent(
																																						txtLicenseNumber)
																																				.addComponent(
																																						txtBirthday,
																																						javax.swing.GroupLayout.DEFAULT_SIZE,
																																						144,
																																						Short.MAX_VALUE))
																																.addPreferredGap(
																																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																																		javax.swing.GroupLayout.DEFAULT_SIZE,
																																		Short.MAX_VALUE)
																																.addComponent(
																																		jLabel5,
																																		javax.swing.GroupLayout.PREFERRED_SIZE,
																																		135,
																																		javax.swing.GroupLayout.PREFERRED_SIZE))))))
												.addGroup(
														layout.createSequentialGroup()
																.addContainerGap()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jLabel1)
																				.addComponent(
																						jLabel2))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						cboCategory,
																						0,
																						144,
																						Short.MAX_VALUE)
																				.addComponent(
																						txtDate)))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(66, 66,
																		66)
																.addComponent(
																		btnFindBestPlayer))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(45, 45,
																		45)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jLabel10)
																				.addComponent(
																						jLabel11)
																				.addComponent(
																						jLabel12)
																				.addComponent(
																						jLabel13))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						txtAddressNumber)
																				.addComponent(
																						txtStreet)
																				.addComponent(
																						txtDistrict)
																				.addComponent(
																						txtCity,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						191,
																						Short.MAX_VALUE))))
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
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
														cboCategory,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel2)
												.addComponent(
														txtDate,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(btnFindBestPlayer)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jLabel3)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel4)
												.addComponent(jLabel5)
												.addComponent(
														txtID,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel6)
												.addComponent(
														txtPlayerName,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel7)
												.addComponent(
														txtLicenseNumber,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLabel8)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(
																		jLabel9))
												.addComponent(
														txtBirthday,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel10)
												.addComponent(
														txtAddressNumber,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel11)
												.addComponent(
														txtStreet,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel12)
												.addComponent(
														txtDistrict,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel13)
												.addComponent(
														txtCity,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
	}// </editor-fold>

	public static java.util.Date toDate(java.sql.Timestamp timestamp) {
		long milliseconds = timestamp.getTime()
				+ (timestamp.getNanos() / 1000000);
		return new java.util.Date(milliseconds);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Player result = null;
		Stat stat = new Stat();

		Format formatter;
		formatter = new SimpleDateFormat("dd-MMM-yy");

		String inputDate = formatter.format(txtDate.getDate());
		Category inputCategory = (Category) cboCategory.getSelectedItem();

		result = stat.getBestPlayer(inputDate, inputCategory);

		if (result != null) {
			try {
				txtID.setText(result.getId().toString());
				txtPlayerName.setText(result.getPersonName());
				txtLicenseNumber.setText(result.getPlayerLicenceNumber());
				txtBirthday.setText(formatter.format(toDate(result
						.getPlayerBirthday())));
				txtAddressNumber.setText(result.getPlayerAddress()
						.getAddressNumber());
				txtStreet.setText(result.getPlayerAddress().getAddressStreet());
				txtDistrict.setText(result.getPlayerAddress()
						.getAddressDistrict());
				txtCity.setText(result.getPlayerAddress().getAddressCity());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else {
			JOptionPane.showMessageDialog(this,
					"Does not have any player in that day for that category",
					"Notice", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
