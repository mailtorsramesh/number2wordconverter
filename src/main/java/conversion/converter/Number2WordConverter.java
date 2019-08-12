package conversion.converter;

import conversion.utilities.CommonConstants;
import conversion.utilities.N2WConverterUtility;

public class Number2WordConverter extends AbstractNumberConverter {

	private void validate() throws NumberWordConvertException {

		if (inputString == null)
			throw new NumberWordConvertException(CommonConstants.ERROR_NOT_PROV);

		if ((!inputString.matches(CommonConstants.NUM_COMMA) && inputString.indexOf(CommonConstants.COMMA) >= 0) || (!inputString.matches(CommonConstants.NUMERIC) && inputString.indexOf(CommonConstants.COMMA) < 0))
			throw new NumberWordConvertException(CommonConstants.ERROR_NOT_VALID);

		inputString = inputString.replaceAll(CommonConstants.COMMA, CommonConstants.EMPTY);
		try{
		inputString = Long.decode(inputString).toString();
		}catch(NumberFormatException e){
			throw new NumberWordConvertException(CommonConstants.ERROR_OUT_RANGE);
		}
		if (inputString.length() < CommonConstants.ONE || inputString.length() > CommonConstants.NINE || inputString.equals("0"))
			throw new NumberWordConvertException(CommonConstants.ERROR_OUT_RANGE);

	}

	protected char[] doValidate() throws NumberWordConvertException {

		validate();
		return inputString.toCharArray();

	}

	protected void doConvert(char[] values) {

		boolean isThosandReq = CommonConstants.FALSE;
		boolean isMillionReq = CommonConstants.FALSE;
		boolean isConjReq = CommonConstants.FALSE;
		for (int i = CommonConstants.ZERO; i < values.length; i++) {

			int index = values.length - CommonConstants.ONE - i;
			int elePos = (index + CommonConstants.ONE) % CommonConstants.THREE;
			int eleGroupPos = index / CommonConstants.THREE;
			boolean isSecPos = CommonConstants.FALSE;

			StringBuilder valueBuilder = new StringBuilder().append(values[i]);

			isThosandReq = isThosandReq ? isThosandReq : enableThousandFlag(eleGroupPos, values[i]);
			isMillionReq = isMillionReq ? isMillionReq : enableMillionFlag(eleGroupPos, values[i]);

			if (elePos == CommonConstants.TWO)
				isSecPos = appendForSecondPos(valueBuilder, values[i], values[i + CommonConstants.ONE]);

			if (isConjReq && values[i] != CommonConstants.ZERO_CHAR) {
				this.builder.append(CommonConstants.CONJUNCTION).append(CommonConstants.SPACE);
			}
			appendValue(elePos, values[i], valueBuilder);
			appendGroupName(isThosandReq, isMillionReq, elePos, eleGroupPos, isSecPos);

			isConjReq = (elePos == CommonConstants.ZERO && values[i] != CommonConstants.ZERO_CHAR)
					? CommonConstants.TRUE : CommonConstants.FALSE;

			i = isSecPos ? i+CommonConstants.ONE : i;

		}

	}

	private void appendValue(int elePos, char c, StringBuilder valueBuilder) {
		if (Character.compare(c, CommonConstants.ZERO_CHAR) != CommonConstants.ZERO)
			this.builder.append(N2WConverterUtility.getValueString(valueBuilder.toString(), elePos))
					.append(CommonConstants.SPACE);

	}

	private void appendGroupName(boolean isThosandReq, boolean isMillionReq, int elePos, int eleGroupPos,
			boolean isSecondPos) {

		if (isThosandReq && (elePos == CommonConstants.ONE && eleGroupPos == CommonConstants.ONE)
				|| (isSecondPos && eleGroupPos == CommonConstants.ONE && elePos == CommonConstants.TWO)) {
			this.builder.append(CommonConstants.THOUSAND).append(CommonConstants.SPACE);

		} else if (isMillionReq && (elePos == CommonConstants.ONE && eleGroupPos == CommonConstants.TWO)
				|| (isSecondPos && eleGroupPos == CommonConstants.TWO && elePos == CommonConstants.TWO)) {
			this.builder.append(CommonConstants.MILLION).append(CommonConstants.SPACE);

		}

	}

	private boolean enableMillionFlag(int eleGroupPos, char c) {
		if (eleGroupPos == CommonConstants.TWO
				&& Character.compare(c, CommonConstants.ZERO_CHAR) != CommonConstants.ZERO)
			return CommonConstants.TRUE;

		return CommonConstants.FALSE;
	}

	private boolean enableThousandFlag(int eleGroupPos, char c) {

		if (eleGroupPos == CommonConstants.ONE
				&& Character.compare(c, CommonConstants.ZERO_CHAR) != CommonConstants.ZERO)
			return CommonConstants.TRUE;

		return CommonConstants.FALSE;
	}

	private boolean appendForSecondPos(StringBuilder valueBuilder, char currentChar, char nextChar) {

		if (Character.getNumericValue(currentChar) == CommonConstants.ONE) {
			valueBuilder.append(nextChar);
			return CommonConstants.TRUE;
		} else {
			valueBuilder.append(CommonConstants.ZERO);
			return CommonConstants.FALSE;
		}

	}

}
