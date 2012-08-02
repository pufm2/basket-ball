package puf.m2.basket.view.support;

public class RankTeam {
	String teamName;
	int value;
	int rank;

	public RankTeam(String teamName, int value, int rank) {
		super();
		this.teamName = teamName;
		this.value = value;
		this.rank = rank;
	}
	
	public int getRank() {
		return rank;
	}
	
	public String getTeamName() {
		return teamName;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
}
