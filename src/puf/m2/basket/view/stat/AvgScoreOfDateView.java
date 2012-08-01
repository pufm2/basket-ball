package puf.m2.basket.view.stat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.toedter.calendar.JDateChooser;

public class AvgScoreOfDateView extends javax.swing.JPanel implements ActionListener{

	private static final long serialVersionUID = -8312946485661547611L;

	// Variables declaration - do not modify
	private javax.swing.JButton btnAvgScore;

	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel lblResult;
	private JDateChooser txtDateOfMeeting;
	// End of variables declaration

	public AvgScoreOfDateView() {
		initComponents();
		addActionListeners();
	}

	private void addActionListeners() {
		btnAvgScore.setActionCommand("AvgScore");
		btnAvgScore.addActionListener(this);		
	}

	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		txtDateOfMeeting = new JDateChooser();
		btnAvgScore = new javax.swing.JButton();
		lblResult = new javax.swing.JLabel();

		jLabel1.setText("Date of meeting");

		btnAvgScore.setText("Average score");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addContainerGap()
										.addComponent(jLabel1)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(txtDateOfMeeting, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup()
												.addGap(78, 78, 78)
												.addComponent(btnAvgScore))
												.addGroup(layout.createSequentialGroup()
														.addContainerGap()
														.addComponent(lblResult)))
														.addContainerGap(141, Short.MAX_VALUE))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel1)
								.addComponent(txtDateOfMeeting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addComponent(btnAvgScore)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(lblResult)
								.addContainerGap(38, Short.MAX_VALUE))
				);
	}// </editor-fold>

	@Override
	public void actionPerformed(ActionEvent e) {
		Date dateOfMatch = txtDateOfMeeting.getDate();
		Stat stat = new Stat();
		double result = stat.getAvgPointsOfDate(dateOfMatch);
		
		if (result!=0) {
			Format formatter;
			formatter = new SimpleDateFormat("dd-MMM-yy");
			
			lblResult.setText("Average of score points be meeting at " + formatter.format(dateOfMatch) + " is " + String.valueOf(result));
		}
		else{
			lblResult.setText("Does not have any match in this date");
		}
	}
}
