package puf.m2.basket.view.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ViewSupport {
	
//	public static HashMap<Team, Integer> sortHashMap(HashMap<Team, Integer> input){
//	    Map<Team, Integer> tempMap = new HashMap<Team, Integer>();
//	   
//	    for (Team wsState : input.keySet()){
//	        tempMap.put(wsState,input.get(wsState));
//	    }
//
//	    List<Team> mapKeys = new ArrayList<Team>(tempMap.keySet());
//	    List<Integer> mapValues = new ArrayList<Integer>(tempMap.values());
//	   
//	    HashMap<Team, Integer> sortedMap = new LinkedHashMap<Team, Integer>();
//	   
//	    TreeSet<Integer> sortedSet = new TreeSet<Integer>(mapValues);
//	    Object[] sortedArray = sortedSet.toArray();
//	    
//	    int size = sortedArray.length;
//	    for (int i=0; i<size; i++){
//	        sortedMap.put(mapKeys.get(mapValues.indexOf(sortedArray[i])), 
//	                      (Integer)sortedArray[i]);
//	    }
//	    return sortedMap;
//  
//
//	}
	
	public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
        	@Override
			public int compare(Entry<String, Integer> m1,
					Entry<String, Integer> m2) {
        		 return (m2.getValue()).compareTo(m1.getValue());
			}
        });

        HashMap<String, Integer> result = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
	
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
