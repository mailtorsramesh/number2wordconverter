package number2word.converter;

import org.junit.Test;

import conversion.converter.Number2WordConverter;
import conversion.converter.NumberWordConvertException;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;

public class AppTest

{

	static Number2WordConverter converter;

	@BeforeClass
	public static void setUp() {
		converter = new Number2WordConverter();
	}

	@Test
	public void convertWord() throws NumberWordConvertException {

		assertEquals("one hundred and thirty one", converter.convertToWord("131"));
	}

	@Test
	public void convertWordWithMax() throws NumberWordConvertException {

		assertEquals(
				"nine hundred and ninety nine million nine hundred and ninety nine thousand nine hundred and ninety nine",
				converter.convertToWord("999999999"));
	}

	@Test
	public void convertWordWithMaxWithComma() throws NumberWordConvertException {

		assertEquals(
				"nine hundred and ninety nine million nine hundred and ninety nine thousand nine hundred and ninety nine",
				converter.convertToWord("999,999,999"));
	}

	@Test
	public void convertWordWithMin() throws NumberWordConvertException {
		assertEquals("one", converter.convertToWord("1"));
	}

	@Test(expected = NumberWordConvertException.class)
	public void convertWordWithNull() throws NumberWordConvertException {
		converter.convertToWord(null);
	}

	@Test(expected = NumberWordConvertException.class)
	public void convertWordWithSpclChar() throws NumberWordConvertException {
		converter.convertToWord("123$3");
	}

	@Test(expected = NumberWordConvertException.class)
	public void convertWordWithDecimal() throws NumberWordConvertException {
		converter.convertToWord("3123.3");
	}

	@Test(expected = NumberWordConvertException.class)
	public void convertWordWithBeyondUpper() throws NumberWordConvertException {
		converter.convertToWord("92323232399");
	}

	@Test(expected = NumberWordConvertException.class)
	public void convertWordWithWithinLower() throws NumberWordConvertException {
		converter.convertToWord("0");
	}

	@Test(expected = NumberWordConvertException.class)
	public void convertWordWithEmpty() throws NumberWordConvertException {
		converter.convertToWord("");
	}
}
