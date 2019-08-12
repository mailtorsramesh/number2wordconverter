package conversion.utilities;

import java.util.HashMap;
import java.util.Map;

public class N2WConverterUtility {

	private N2WConverterUtility() {
	}

	private static Map<String, String> positinalVal = new HashMap<String, String>();

	static {
		positinalVal.put("0", "");
		positinalVal.put("1", "one");
		positinalVal.put("2", "two");
		positinalVal.put("3", "three");
		positinalVal.put("4", "four");
		positinalVal.put("5", "five");
		positinalVal.put("6", "six");
		positinalVal.put("7", "seven");
		positinalVal.put("8", "eight");
		positinalVal.put("9", "nine");
		positinalVal.put("10", "ten");
		positinalVal.put("11", "eleven");
		positinalVal.put("12", "twelve");
		positinalVal.put("13", "thirteen");
		positinalVal.put("14", "forteen");
		positinalVal.put("15", "fifteen");
		positinalVal.put("16", "sixteen");
		positinalVal.put("17", "seventeen");
		positinalVal.put("18", "eighteen");
		positinalVal.put("19", "nineteen");
		positinalVal.put("20", "twenty");
		positinalVal.put("30", "thirty");
		positinalVal.put("40", "forty");
		positinalVal.put("50", "fifty");
		positinalVal.put("60", "sixty");
		positinalVal.put("70", "seventy");
		positinalVal.put("80", "eighty");
		positinalVal.put("90", "ninety");
	}

	public static String getValueString(String ch, int position) {

		if (position == CommonConstants.ONE || position == CommonConstants.TWO)
			return getValue(ch);
		else if (position == CommonConstants.ZERO)
			return new StringBuilder().append(getValue(ch)).append(CommonConstants.SPACE)
					.append(CommonConstants.HUNDRED).toString();
		return null;

	}

	public static String getValue(String str) {
		return positinalVal.get(str);
	}

}
