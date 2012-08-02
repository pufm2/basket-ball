package puf.m2.basket.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import puf.m2.basket.view.stat.*;
import puf.m2.basket.view.support.*;

public class MainFrame extends JFrame implements ActionListener {

	public MainFrame() {
		super("Basket-ball management system");
		initFrame();
	}

	private void initFrame() {
		setSize(600, 400);

		JMenuBar myMenuBar = new JMenuBar();
		setJMenuBar(myMenuBar);

		// Menu bar
		JMenu mnuPerson = new JMenu("Person");
		mnuPerson.setMnemonic('P');
		JMenu mnuOrganization = new JMenu("Organization");
		mnuOrganization.setMnemonic('O');
		JMenu mnuTournament = new JMenu("Tournament");
		mnuTournament.setMnemonic('T');
		JMenu mnuHelp = new JMenu("Help");
		mnuHelp.setMnemonic('H');

		// Menu Person
		JMenuItem mnuPlayer = new JMenuItem("Player");
		mnuPlayer.setActionCommand("Player");
		mnuPlayer.addActionListener(this);

		JMenuItem mnuPresident = new JMenuItem("President");
		mnuPresident.setActionCommand("President");
		mnuPresident.addActionListener(this);

		JMenuItem mnuVicePresident = new JMenuItem("Vice President");
		mnuVicePresident.setActionCommand("Vice President");
		mnuVicePresident.addActionListener(this);

		JMenuItem mnuSecretary = new JMenuItem("Secretary");
		mnuSecretary.setActionCommand("Secretary");
		mnuSecretary.addActionListener(this);

		JMenuItem mnuTreasurer = new JMenuItem("Treasurer");
		mnuTreasurer.setActionCommand("Treasurer");
		mnuTreasurer.addActionListener(this);

		JMenuItem mnuCoach = new JMenuItem("Coach");
		mnuCoach.setActionCommand("Coach");
		mnuCoach.addActionListener(this);

		mnuPerson.add(mnuPlayer);
		mnuPerson.add(mnuPresident);
		mnuPerson.add(mnuVicePresident);
		mnuPerson.add(mnuSecretary);
		mnuPerson.add(mnuTreasurer);
		mnuPerson.add(mnuCoach);

		// Menu Organization
		JMenuItem mnuCategory = new JMenuItem("Category");
		mnuCategory.setActionCommand("Category");
		mnuCategory.addActionListener(this);

		JMenuItem mnuClub = new JMenuItem("Club");
		mnuClub.setActionCommand("Club");
		mnuClub.addActionListener(this);

		JMenuItem mnuOffice = new JMenuItem("Office");
		mnuOffice.setActionCommand("Office");
		mnuOffice.addActionListener(this);

		mnuOrganization.add(mnuCategory);
		mnuOrganization.add(mnuClub);
		mnuOrganization.add(mnuOffice);

		// Menu Tournament
		JMenuItem mnuSeason = new JMenuItem("Season");
		mnuSeason.setActionCommand("Season");
		mnuSeason.addActionListener(this);

		JMenuItem mnuMatch = new JMenuItem("Match");
		mnuMatch.setActionCommand("Match");
		mnuMatch.addActionListener(this);

		JMenuItem mnuTeam = new JMenuItem("Team");
		mnuTeam.setActionCommand("Team");
		mnuTeam.addActionListener(this);

		mnuTournament.add(mnuTeam);
		mnuTournament.add(mnuSeason);
		mnuTournament.add(mnuMatch);

		// Menu report
		JMenu mnuReport = new JMenu("Report");
		mnuReport.setMnemonic('R');

		JMenuItem mnuAvgScoreOfDate = new JMenuItem("Average score of date");
		mnuAvgScoreOfDate.setActionCommand("AvgScoreOfDate");
		mnuAvgScoreOfDate.addActionListener(this);

		JMenuItem mnuAvgScoreOfSeason = new JMenuItem("Average score of season");
		mnuAvgScoreOfSeason.setActionCommand("AvgScoreOfSeason");
		mnuAvgScoreOfSeason.addActionListener(this);

		JMenuItem mnuAvgDistance = new JMenuItem(
				"Average of moving distance betwween two meetings");
		mnuAvgDistance.setActionCommand("AvgDistance");
		mnuAvgDistance.addActionListener(this);

		JMenuItem mnuBestPlayerOfDate = new JMenuItem(
				"Best player of date for a category");
		mnuBestPlayerOfDate.setActionCommand("BestPlayerOfDate");
		mnuBestPlayerOfDate.addActionListener(this);

		JMenuItem mnuRankTeams = new JMenuItem("Ranking teams");
		mnuRankTeams.setActionCommand("RankTeams");
		mnuRankTeams.addActionListener(this);

		mnuReport.add(mnuAvgScoreOfDate);
		mnuReport.add(mnuAvgScoreOfSeason);
		mnuReport.add(mnuAvgDistance);
		mnuReport.add(mnuBestPlayerOfDate);
		mnuReport.add(mnuRankTeams);

		// Menu About
		JMenuItem mnuAbout = new JMenuItem("About");
		mnuAbout.setActionCommand("About");
		mnuAbout.addActionListener(this);

		mnuHelp.add(mnuAbout);

		// Close windows
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

		myMenuBar.add(mnuPerson);
		myMenuBar.add(mnuOrganization);
		myMenuBar.add(mnuTournament);
		myMenuBar.add(mnuReport);
		myMenuBar.add(mnuAbout);
		setJMenuBar(myMenuBar);

		setCenter(this);
	}

	private static final long serialVersionUID = 8548809193653203342L;

	public void setCenter(JFrame frame) {
		// Set at center of screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (screenSize.height - this.getHeight()) / 2;
		int width = (screenSize.width - this.getWidth()) / 2;

		this.setLocation(width, height);

		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if ("Player".equals(cmd))
			Utils.createAndShowGUI(this, new PlayerView());
		else if ("President".equals(cmd))
			Utils.createAndShowGUI(this, new PresidentView());
		else if ("Vice President".equals(cmd))
			Utils.createAndShowGUI(this, new VicePresidentView());
		else if ("Secretary".equals(cmd))
			Utils.createAndShowGUI(this, new SecretaryView());
		else if ("Treasurer".equals(cmd))
			Utils.createAndShowGUI(this, new TreasurerView());
		else if ("Coach".equals(cmd))
			Utils.createAndShowGUI(this, new CoachView());

		else if ("Category".equals(cmd))
			Utils.createAndShowGUI(this, new CategoryView());
		else if ("Club".equals(cmd))
			Utils.createAndShowGUI(this, new ClubView());
		else if ("Office".equals(cmd))
			Utils.createAndShowGUI(this, new OfficeView());

		else if ("Season".equals(cmd))
			Utils.createAndShowGUI(this, new SeasonView());
		else if ("Match".equals(cmd))
			Utils.createAndShowGUI(this, new MatchView());
		else if ("Team".equals(cmd))
			Utils.createAndShowGUI(this, new TeamView());

		else if ("AvgScoreOfDate".equals(cmd))
			Utils.createAndShowGUI(this, new AvgScoreOfDateView());
		else if ("AvgScoreOfSeason".equals(cmd))
			Utils.createAndShowGUI(this, new AvgScoreOfSeasonView());
		else if ("AvgDistance".equals(cmd))
			Utils.createAndShowGUI(this, new AvgDistanceView());
		else if ("BestPlayerOfDate".equals(cmd))
			Utils.createAndShowGUI(this, new BestPlayerOfDateView());
		else if ("RankTeams".equals(cmd))
			rankTeams();
		else if (cmd.equals("About"))
			showAbout();
	}

	private void rankTeams() {
		Stat stat = new Stat();
		ArrayList<RankTeam> rankTeams = new ArrayList<RankTeam>();
		rankTeams = stat.rankTeams();

		String result = "Ranking for all teams \n";
		for (RankTeam rankTeam : rankTeams) {
			result = result + rankTeam.getTeamName() + " - Points: "
					+ rankTeam.getValue() + " - Rank: " + rankTeam.getRank()
					+ "\n";
		}
		JOptionPane.showMessageDialog(this, result, "Ranking teams",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void showAbout() {
		String strAbout = new String("\"Baket ball management system\" \n"
				+ "Project of Advanced Database course \n"
				+ "Prepared by Nguyen Huu Phat and Le Xuan Hoan \n"
				+ "MINF10 - PUF at Ho Chi Minh city, VietNam \n" + "Jul 2012");
		JOptionPane.showMessageDialog(null, strAbout, "About",
				JOptionPane.INFORMATION_MESSAGE);
	}
}