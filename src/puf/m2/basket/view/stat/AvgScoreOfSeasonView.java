package puf.m2.basket.view.stat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import puf.m2.basket.model.entity.Season;
import puf.m2.basket.model.support.BasketException;
import puf.m2.basket.model.support.EntityUtils;

public class AvgScoreOfSeasonView extends javax.swing.JPanel implements
		ActionListener {

	private static final long serialVersionUID = -2297897041599175622L;

	// Variables declaration - do not modify
	private javax.swing.JButton btnAvgScore;

	private javax.swing.JComboBox<Season> cboSeason;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel lblResult;

	// End of variables declaration

	public AvgScoreOfSeasonView() {
		initComponents();
		addActionListeners();
		fillComBoSeason();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Season season = (Season) cboSeason.getSelectedItem();
		Stat stat = new Stat();
		double result = stat.getAvgPointsFromSeason(season);

		if (result != 0) {
			lblResult
					.setText("Average of score points since the beginning of the season is "
							+ String.valueOf(result));
		} else {
			lblResult.setText("Does not have any match in this season");
		}
	}

	private void addActionListeners() {
		btnAvgScore.setActionCommand("AvgScore");
		btnAvgScore.addActionListener(this);
	}

	private void fillComBoSeason() {
		List<Season> seasons;
		try {
			seasons = EntityUtils.loadByCondition(null, Season.class, "id");
			for (Season season : seasons) {
				cboSeason.addItem(season);
			}

		} catch (BasketException e) {
			e.printStackTrace();
		}

	}

	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		btnAvgScore = new javax.swing.JButton();
		lblResult = new javax.swing.JLabel();
		cboSeason = new javax.swing.JComboBox<Season>();

		jLabel1.setText("Choose season");

		btnAvgScore.setText("Average score");

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
																.addComponent(
																		jLabel1)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(
																		cboSeason,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		127,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(78, 78,
																		78)
																.addComponent(
																		btnAvgScore))
												.addGroup(
														layout.createSequentialGroup()
																.addContainerGap()
																.addComponent(
																		lblResult)))
								.addContainerGap(157, Short.MAX_VALUE)));
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
														cboSeason,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addComponent(btnAvgScore)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(lblResult)
								.addContainerGap(77, Short.MAX_VALUE)));
	}// </editor-fold>
}
