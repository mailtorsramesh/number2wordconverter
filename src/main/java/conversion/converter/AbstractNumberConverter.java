package conversion.converter;

public abstract class AbstractNumberConverter implements Convertable {

	protected StringBuilder builder = null;
	protected String inputString = null;

	protected abstract char[] doValidate() throws NumberWordConvertException;
	protected abstract void doConvert(char[] values);


	public String convertToWord(String number) throws NumberWordConvertException {
		this.builder = new StringBuilder();
		this.inputString = number;
		doConvert(doValidate());
		return builder.toString().trim();
	}
}
