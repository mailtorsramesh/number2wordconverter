package conversion.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import conversion.converter.Convertable;
import conversion.converter.Number2WordConverter;
import conversion.converter.NumberWordConvertException;

public class App {
	private static final  Logger LOG = Logger.getLogger(App.class.getName());
	static Convertable converter;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Please enter the value : ");

		String number = in.readLine();

		try {
			converter = new Number2WordConverter();
			System.out.print("Output : " + converter.convertToWord(number));
		} catch (NumberWordConvertException e) {
			LOG.log(Level.SEVERE, e.getMessage());
		}

	}

}
