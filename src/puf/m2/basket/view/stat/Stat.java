package puf.m2.basket.view.stat;

import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import puf.m2.basket.model.entity.Category;
import puf.m2.basket.model.entity.Match;
import puf.m2.basket.model.entity.Player;
import puf.m2.basket.model.entity.ScoreDetail;
import puf.m2.basket.model.entity.Season;
import puf.m2.basket.model.entity.Team;
import puf.m2.basket.model.support.BasketException;
import puf.m2.basket.model.support.EntityUtils;
import puf.m2.basket.view.support.RankTeam;
import puf.m2.basket.view.support.ViewSupport;

public class Stat {

	public String dateToString(Date date, Format formatter) {
		return formatter.format(date);
	}

	public double getAvgDistanceBetweenMeetings() {
		double result = 0;

		return result;
	}

	public double getAvgPointsFromSeason(Season season) {
		double result = 0;
		int totalPoints = 0;
		int matchesInSeason = 0;
		try {
			List<Match> matches = EntityUtils.loadByCondition(null,
					Match.class, null);
			for (Match match : matches) {
				if (match.getSeason().getValue().getId() == season.getId()) {
					ScoreDetail[] scoreDetails = match.getDetails().getArray();
					for (ScoreDetail scoreDetail : scoreDetails) {
						totalPoints += scoreDetail.getValue();
					}
				}
				matchesInSeason++;
			}
		} catch (BasketException | SQLException e) {

			e.printStackTrace();
		}
		if (matchesInSeason == 0)
			result = 0;
		else
			result = totalPoints / matchesInSeason;
		return result;
	}

	public double getAvgPointsOfDate(Date date) {
		double result = 0;
		int totalPoints = 0;
		int matchesInDate = 0;

		Format formatter;
		formatter = new SimpleDateFormat("dd-MMM-yy");
		String matchDate, inputDate;

		inputDate = formatter.format(date);
		try {

			List<Match> matches = EntityUtils.loadByCondition(null,
					Match.class, null);
			for (Match match : matches) {
				matchDate = formatter.format(ViewSupport.toDate(match
						.getMatchDate()));
				if (inputDate.compareTo(matchDate) == 0) {
					ScoreDetail[] scoreDetails = match.getDetails().getArray();
					for (ScoreDetail scoreDetail : scoreDetails) {
						totalPoints += scoreDetail.getValue();

					}
				}
				matchesInDate++;
			}
		} catch (BasketException | SQLException e) {

			e.printStackTrace();
		}
		if (matchesInDate == 0)
			result = 0;
		else
			result = totalPoints / matchesInDate;
		return result;
	}

	public Player getBestPlayer(String inputDate, Category inputCategory) {
		Player result = null;

		Map<Player, Integer> playerScore = new HashMap<Player, Integer>();
		Player bestPlayer = null;

		Format formatter;
		formatter = new SimpleDateFormat("dd-MMM-yy");

		try {
			// get list teams of input category
			Team[] teamsOfCategory = inputCategory.getListteam().getArray();
			List<Match> matches = EntityUtils.loadByCondition(null,
					Match.class, null);

			for (Match match : matches) {
				// if date of match is equals to input date
				if (dateToString(match.getMatchDate(), formatter).equals(
						inputDate)) {
					// get list of score detail
					ScoreDetail[] scoreDetails = match.getDetails().getArray();
					for (ScoreDetail scoreDetail : scoreDetails) {
						Team team = scoreDetail.getTeam().getValue();
						// if team of score detail belong to category
						for (Team teamOfCategory : teamsOfCategory) {
							if (teamOfCategory.getTeamName().equals(
									team.getTeamName())) {
								// get score points of each player in match
								playerScore.put(scoreDetail.getPlayer()
										.getValue(), scoreDetail.getValue());
							}
						}
					}
				}
			}

			// Get Map in Set interface to get key and value
			Set<Entry<Player, Integer>> set = playerScore.entrySet();

			// Move next key and value of Map by iterator
			Iterator<Entry<Player, Integer>> it = set.iterator();

			// assume that first player is the best player
			@SuppressWarnings("rawtypes")
			Map.Entry m;
			int bestPoint = 0;
			if (it.hasNext()) {
				m = it.next();

				bestPlayer = (Player) m.getKey();
				bestPoint = (Integer) m.getValue();
			}

			while (it.hasNext()) {
				// get record from map playerScore
				m = it.next();

				Player nextPlayer = (Player) m.getKey();
				Integer nextPoint = (Integer) m.getValue();

				// update best player and best point
				if (nextPoint > bestPoint) {
					bestPlayer = nextPlayer;
					bestPoint = nextPoint;
				}
			}
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}

		result = bestPlayer;
		return result;
	}

	public ArrayList<RankTeam> rankTeams() {
		HashMap<Team, Integer> rankTeams = new HashMap<Team, Integer>();
		List<Match> matches;

		try {
			matches = EntityUtils.loadByCondition(null, Match.class, null);
			Team team = null;
			int value = 0;

			// Add all team and value to map
			for (Match match : matches) {
				ScoreDetail scoreDetails[] = match.getDetails().getArray();
				for (ScoreDetail scoreDetail : scoreDetails) {
					team = scoreDetail.getTeam().getValue();
					value = scoreDetail.getValue();
					rankTeams.put(team, value);
				}
			}
		} catch (BasketException | SQLException e) {
			e.printStackTrace();
		}

		// Get Map in Set interface to get key and value
		Set<Entry<Team, Integer>> set = rankTeams.entrySet();

		// Move next key and value of Map by iterator
		Iterator<Entry<Team, Integer>> it = set.iterator();

		@SuppressWarnings("rawtypes")
		Map.Entry m;

		Team team = null;
		int value = 0;

		ArrayList<RankTeam> arrRankTeam = new ArrayList<RankTeam>();

		while (it.hasNext()) {
			// get record from map team
			m = it.next();

			team = (Team) m.getKey();
			value = (Integer) m.getValue();

			try {
				arrRankTeam.add(new RankTeam(team.getTeamName(), value, 0));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		HashMap<String, Integer> sortTeams = new HashMap<String, Integer>();
		String teamName = null;

		for (int i = 0; i < arrRankTeam.size(); i++) {
			teamName = arrRankTeam.get(i).getTeamName();
			if (!sortTeams.containsKey(teamName)) {
				sortTeams.put(teamName, arrRankTeam.get(i).getValue());
			} else {
				int oldValue = sortTeams.get(teamName);
				int newValue = arrRankTeam.get(i).getValue() + oldValue;
				sortTeams.remove(teamName);
				sortTeams.put(teamName, newValue);
			}
		}

		Set<Entry<String, Integer>> setRankTeam = sortTeams.entrySet();

		// Move next key and value of Map by iterator
		Iterator<Entry<String, Integer>> itr = setRankTeam.iterator();

		@SuppressWarnings("rawtypes")
		Map.Entry entryRankTeam;
		String entryTeamName = "";
		Integer entryValue = null;

		while (itr.hasNext()) {
			// get record from map team
			entryRankTeam = itr.next();

			entryTeamName = (String) entryRankTeam.getKey();
			entryValue = (Integer) entryRankTeam.getValue();
		}

		sortTeams = ViewSupport.sortByValue(sortTeams);

		// Ranking by value
		int rank = 1;
		itr = setRankTeam.iterator();

		ArrayList<RankTeam> arrRankTeams = new ArrayList<RankTeam>();

		while (itr.hasNext()) {
			Entry<String, Integer> entry = itr.next();
			RankTeam rankTeam = new RankTeam(entry.getKey(), entry.getValue()
					.intValue(), rank);
			arrRankTeams.add(rankTeam);
			rank++;
		}

		return arrRankTeams;
	}
}
