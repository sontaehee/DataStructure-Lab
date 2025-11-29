package org.likebnb.ds.utils;

import java.util.Objects;

public class ObjectUtils {

	static public <T> int compare(T a, T b) {
		int result = 0;
		
		if (a instanceof String) {
			result = Objects.compare((String)a, (String)b, String::compareTo);
		} else if (a instanceof Character) {
			result = Objects.compare((Character)a, (Character)b, Character::compareTo);
		} else if (a instanceof Integer) {
			result = Objects.compare((Integer)a, (Integer)b, Integer::compareTo);
		} 

		return result;
	}
}
