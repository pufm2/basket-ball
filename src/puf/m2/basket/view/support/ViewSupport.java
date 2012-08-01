package puf.m2.basket.view.support;

import java.util.ArrayList;
import java.util.Date;

public class ViewSupport {
	public static Date toDate(java.sql.Timestamp timestamp) {
		long milliseconds = timestamp.getTime()
				+ (timestamp.getNanos() / 1000000);
		return new Date(milliseconds);
	}

	public static ArrayList<City> getCities(){
		ArrayList<City> result = new ArrayList<City>();

		City city;
		
		city = new City("Can Tho", 10.035457, 105.783749);
		result.add(city);
		
		city = new City("Da Lat",11.956708, 108.456345);
		result.add(city);
		
		city = new City("Ha Long", 20.985162, 107.045288);
		result.add(city);
		
		city = new City("Ha Noi", 21.036442,  105.849838);
		result.add(city);
		
		city = new City("Ho Chi Minh city", 10.887254, 106.625061);
		result.add(city);

		city = new City("Nha Trang", 12.252786, 109.194489);
		result.add(city);
		
		return result;
	}
	
	public static City getCityByName(String cityName){
		City result = null;
		
		ArrayList<City> cities = new ArrayList<City>();
		cities = getCities();
		for (int i=0; i<cities.size(); i++)
			if (cities.get(i).getCityName().equals(cityName)){
				result = cities.get(i);
				break;
			}
		
		return result;
	}
}
