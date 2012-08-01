package puf.m2.basket.view.support;

public class City {
	private String cityName;
	private double latitude;
	private double longitude;
	
	public City(String cityName, double latitude, double longitude) {
		super();
		this.cityName = cityName;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public String getCityName() {
		return cityName;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public String toString(){
		return getCityName();
	}
}
