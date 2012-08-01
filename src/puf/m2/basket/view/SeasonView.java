package puf.m2.basket.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;
import puf.m2.basket.model.entity.Season;
import puf.m2.basket.model.support.BasketException;
import puf.m2.basket.model.support.Condition;
import puf.m2.basket.model.support.EntityUtils;
import puf.m2.basket.view.support.ViewSupport;

public class SeasonView extends JPanel implements ActionListener {
	private static final long serialVersionUID = 7386815518964502831L;

	// Variables declaration - do not modify
	Season season;
	FormState formState;
	boolean pressUpdate = false;

	private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private JDateChooser txtEndDate;
    private javax.swing.JTextField txtSeasonID;
    private javax.swing.JTextField txtSeasonName;
    private JDateChooser txtStartDate;
	// End of variables declaration

	public SeasonView() {
		initComponents();
		addActionListeners();

		season = new Season();
		formState = FormState.INITIAL;
		updateForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Cancel".equals(e.getActionCommand())) {
			formState = FormState.INITIAL;
		} else if ("Delete".equals(e.getActionCommand())) {
			Season seasonToDelete = null;

			if (!isInteger(txtSeasonID.getText())) {
				JOptionPane.showMessageDialog(this,
						"Please input correct season ID", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			try {
				season = makeSeason();
				seasonToDelete = EntityUtils.loadById(season.getId(),
						Season.class);
			} catch (BasketException | SQLException e1) {
				e1.printStackTrace();
			}
			if (seasonToDelete == null) {
				JOptionPane.showMessageDialog(this,
						"This season ID does not exists, can not delete",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (JOptionPane.showConfirmDialog(this,
					"Do you really to delete this season?",
					"Confirm to delete season", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				// Delete this season
				deleteSeason();
				JOptionPane.showMessageDialog(this, "This season is deleted");
			}
			formState = FormState.INITIAL;

		} else if ("Find".equals(e.getActionCommand())) {
			formState = FormState.FIND;
			String seasonName = JOptionPane.showInputDialog(this,
					"Please give name of season", "Season 1");
			
			// Find season with that name
			if (seasonName == null)
				return;
			else
				seasonName = seasonName.toUpperCase();			

			List<Season> seasons = null;
			try {
				seasons = EntityUtils.loadByCondition(new Condition("SEASON_NAME",
						seasonName), Season.class, "SEASON_NAME");
			} catch (BasketException e1) {
				e1.printStackTrace();
			}
			// If exist season, show its information
			if (seasons.size() > 0) {
				season = seasons.get(0);
				setTextField();
				JOptionPane.showMessageDialog(this, "Season is founded",
						"Notice", JOptionPane.INFORMATION_MESSAGE);
				formState = FormState.FIND;

			} else {
				// If not exist season, show error message
				JOptionPane.showMessageDialog(this,
						"Can not found that season", "Error",
						JOptionPane.ERROR_MESSAGE);
				formState = FormState.INITIAL;
			}

		} else if ("New".equals(e.getActionCommand())) {
			pressUpdate = false;
			formState = FormState.NEW;

		} else if ("Save".equals(e.getActionCommand())) {
			if (isEmptyData()) {
				JOptionPane.showMessageDialog(this,
						"Please give enought information of season", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			if (isTypeMismatch()) {
				JOptionPane.showMessageDialog(this,
						"Please give correct type in each text field", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				if (!pressUpdate) {
					season = makeSeason();
					if (isDuplicateData(season)) {
						JOptionPane
								.showMessageDialog(
										this,
										"Can not insert new season which is duplicate ID/ Name with existing season",
										"Error", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						// Save new season
						saveSeason();
						JOptionPane.showMessageDialog(this,
								"Save new season successful", "Success",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					// Update existing season
					season = makeSeason();
					updateSeason();
					JOptionPane.showMessageDialog(this,
							"Update season successful", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
			formState = FormState.INITIAL;

		} else if ("Update".equals(e.getActionCommand())) {
			pressUpdate = true;
			formState = FormState.NEW;
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
	}

	private void deleteSeason() {
		try {
			season.setDeleted(1);
			season.update();
		} catch (SQLException | BasketException e) {
			e.printStackTrace();
		}
	}

	private void initComponents() {

		jButton2 = new javax.swing.JButton();
        txtSeasonID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtStartDate = new JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        btnNew = new javax.swing.JButton();
        btnFind = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtEndDate = new JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtSeasonName = new javax.swing.JTextField();

        jButton2.setText("jButton2");

        jLabel1.setText("Season ID");

        jLabel2.setText("Start Date");

        btnNew.setText("New");
        btnNew.setToolTipText("Add new office");

        btnFind.setText("Find");
        btnFind.setToolTipText("Find an existing office");

        btnSave.setText("Save");
        btnSave.setToolTipText("Save new office");

        btnCancel.setText("Cancel");

        btnUpdate.setText("Update");

        btnDelete.setText("Delete");

        jLabel3.setText("End Date");

        jLabel4.setText("Season name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtStartDate, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                            .addComponent(txtSeasonID, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEndDate, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                            .addComponent(txtSeasonName))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSeasonID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSeasonName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew)
                    .addComponent(btnFind)
                    .addComponent(btnCancel)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnSave))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtSeasonID, txtStartDate});

	}// </editor-fold>

	private boolean isDuplicateData(Season season) {
		List<Season> seasons = null;

		// check if duplicate season ID
		try {
			seasons = EntityUtils.loadByCondition(new Condition("id", season
					.getId().toString()), Season.class, "id");
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
		if (seasons.size() > 0)
			return true;
		
		seasons=null;
		
		// check if duplicate season name
				try {
					seasons = EntityUtils.loadByCondition(new Condition("SEASON_NAME", season
							.getId().toString()), Season.class, "SEASON_NAME");
				} catch (BasketException | SQLException e) {
					e.printStackTrace();
				}
				if (seasons.size() > 0)
					return true;


		return false;
	}

	private boolean isEmptyData() {
		if (txtSeasonID.getText().equals("") || txtSeasonName.getText().equals(""))
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
		if (!isInteger(txtSeasonID.getText()))
			return true;
		return false;
	}

	private Season makeSeason() {
		setFieldtoAttribute();
		try {
			season.setDeleted(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return season;
	}

	private void saveSeason() {
		setFieldtoAttribute();
		try {
			season.setDeleted(0);
			season.save();
		} catch (NumberFormatException | BasketException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void setFieldtoAttribute() {
		try {
			season.setId(Integer.parseInt(txtSeasonID.getText().trim()));
			season.setSeasonName(txtSeasonName.getText().trim());
			season.setSeasonStartdate(new Timestamp(txtStartDate.getDate()
					.getTime()));
			season.setSeasonEnddate(new Timestamp(txtEndDate.getDate()
					.getTime()));
		} catch (NumberFormatException | SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void setTextField() {
		try {
			txtSeasonID.setText(season.getId().toString());
			txtSeasonName.setText(season.getSeasonName());
			txtStartDate.setDate(ViewSupport.toDate(season.getSeasonStartdate()));
			txtEndDate.setDate(ViewSupport.toDate(season.getSeasonEnddate()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updateForm() {
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

	private void updateSeason() {
		setFieldtoAttribute();
		try {
			season.setDeleted(0);
			season.update();
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}
	}
}
