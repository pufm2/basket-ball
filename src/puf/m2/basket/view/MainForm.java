/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package puf.m2.basket.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MainForm extends javax.swing.JPanel implements ActionListener{

	private static final long serialVersionUID = 7888031775039917448L;

	// Variables declaration - do not modify
    private JButton btnManageCategory;
    private JButton btnManageClub;
    private JButton btnManageCoach;
    private JButton btnManageMatch;
    private JButton btnManageOffice;
    private JButton btnManagePerson;
    private JButton btnManagePlayer;
    private JButton btnManageSeason;
    private JButton btnManageTeam;
    // End of variables declaration

    public MainForm() {
        initComponents();
        addActionListeners();
    }
    private void addActionListeners() {
    	btnManageCategory.setActionCommand("ManageCategory");
    	btnManageCategory.addActionListener(this);
    	
    	btnManageClub.setActionCommand("ManageClub");
    	btnManageCategory.addActionListener(this);
    	
    	btnManageCoach.setActionCommand("ManageCoach");
    	btnManageCoach.addActionListener(this);
    	
    	btnManageMatch.setActionCommand("ManageMatch");
    	btnManageMatch.addActionListener(this);
    	
    	btnManageOffice.setActionCommand("ManageOffice");
    	btnManageOffice.addActionListener(this);
    	
    	btnManagePerson.setActionCommand("ManagePerson");
    	btnManagePerson.addActionListener(this);
    	
    	btnManagePlayer.setActionCommand("ManagePlayer");
    	btnManagePlayer.addActionListener(this);
    	
    	btnManageSeason.setActionCommand("ManageSeason");
    	btnManageSeason.addActionListener(this);
    	
    	btnManageTeam.setActionCommand("ManageTeam");
    	btnManageTeam.addActionListener(this);
		
	}
	@Override
    public void actionPerformed(ActionEvent e) {
		if ("ManageCategory".equals(e.getActionCommand())){
			
		}
		else if ("ManageClub".equals(e.getActionCommand())){
			
		}
		else if ("ManageCoach".equals(e.getActionCommand())){
			
		}
		else if ("ManageMatch".equals(e.getActionCommand())){
			
		}
		else if ("ManageOffice".equals(e.getActionCommand())){
			
		}
		else if ("ManagePerson".equals(e.getActionCommand())){
			
		}
		else if ("ManagePlayer".equals(e.getActionCommand())){
			
		}
		else if ("ManageSeason".equals(e.getActionCommand())){
			
		}
		else if ("ManageTeam".equals(e.getActionCommand())){
			
		}
    }

    private void initComponents() {

        btnManageOffice = new javax.swing.JButton();
        btnManagePerson = new javax.swing.JButton();
        btnManageTeam = new javax.swing.JButton();
        btnManageCategory = new javax.swing.JButton();
        btnManageClub = new javax.swing.JButton();
        btnManageCoach = new javax.swing.JButton();
        btnManagePlayer = new javax.swing.JButton();
        btnManageSeason = new javax.swing.JButton();
        btnManageMatch = new javax.swing.JButton();

        btnManageOffice.setText("Manage offices");

        btnManagePerson.setText("Manage persons");

        btnManageTeam.setText("Manage teams");

        btnManageCategory.setText("Manage categories");

        btnManageClub.setText("Manage clubs");

        btnManageCoach.setText("Manage coaches");

        btnManagePlayer.setText("Manage players");

        btnManageSeason.setText("Manage seasons");

        btnManageMatch.setText("Manage matches");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnManageOffice)
                    .addComponent(btnManagePerson)
                    .addComponent(btnManageTeam)
                    .addComponent(btnManageCategory)
                    .addComponent(btnManageClub)
                    .addComponent(btnManageCoach)
                    .addComponent(btnManagePlayer)
                    .addComponent(btnManageSeason)
                    .addComponent(btnManageMatch))
                .addContainerGap(267, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnManageOffice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnManagePerson)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnManageTeam)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnManageCategory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnManageClub)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnManageCoach)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnManagePlayer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnManageSeason)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnManageMatch)
                .addContainerGap(34, Short.MAX_VALUE))
        );
    }// </editor-fold>
}
