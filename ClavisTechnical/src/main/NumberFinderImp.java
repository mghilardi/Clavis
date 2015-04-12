package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import demo.CustomNumberEntity;
import demo.FastestComparator;
import demo.NumberFinder;

public class NumberFinderImp implements NumberFinder {
	
	/*@Override
	public boolean contains(int valueToFind, List<CustomNumberEntity> list) {
		if (Integer.valueOf(valueToFind) == null) {
			return false;
		}
		for (CustomNumberEntity customNumberEntity : list) {
			try {
				if (valueToFind == Integer.parseInt(customNumberEntity.getNumber())) {
					return true;
				}
			} catch (NumberFormatException e) {
				continue;
			}
		}
		return false;
	}*/

	@Override
	public List<CustomNumberEntity> readFromFile(String filePath) {
		JSONParser parser = new JSONParser();
		List<CustomNumberEntity> cneList = new ArrayList<CustomNumberEntity>();
		try {
			Object obj = parser.parse(new FileReader(filePath));
			JSONArray numbers = (JSONArray) obj;
			Iterator iterator = numbers.iterator();
			while (iterator.hasNext()) {
				JSONObject jsonObject = (JSONObject) iterator.next();
				String number = (String) jsonObject.get("number");
				// System.out.print(number + ", ");
				CustomNumberEntity cne = new CustomNumberEntity(number);
				cneList.add(cne);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return cneList;
	}

	@Override
	public boolean contains(int valueToFind, List<CustomNumberEntity> list) {
		if (Integer.valueOf(valueToFind) == null) {
			return false;
		}
		FastestComparator fc = new FastestComparator();
		for (CustomNumberEntity customNumberEntity : list) {
			try {
				if (Integer.valueOf(customNumberEntity.getNumber()) instanceof Integer) {
					if (fc.compare(valueToFind, customNumberEntity) == 0) {
						return true;
					}
				}
			} catch (NumberFormatException e) {
				continue;
			}
		}
		return false;
	}

}
