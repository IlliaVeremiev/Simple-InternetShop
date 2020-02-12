package veil.internetshop.simple.modules.directoryresolver;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Identifier that consists of <b>n</b> numeric elements,
 * that can be easy converted to letters equivalent
 *
 * @author Illia_Veremiev
 */
public class ComplexIdentifier{

	private IdPath[] numericId;

	/**
	 * Constructor with parameters
	 *
	 * @param pattern - id pattern. May consists of numbers separated by <b>:</b>,
	 *                that intends max value of current if path
	 */
	private ComplexIdentifier(final String pattern){
		numericId = Arrays.stream(pattern.split(":")).map(Integer::parseInt).map(IdPath::new).toArray(IdPath[]::new);
	}

	/**
	 * Constructor with parameters
	 *
	 * @param pattern - id pattern. May consists of numbers separated by <b>:</b>,
	 *                that intends max value of current if path
	 */
	public ComplexIdentifier(final String pattern, final String value){
		this(pattern);
		if(value.isEmpty()){
			return;
		}
		String[] parts = value.split(":");
		for(int i = 0; i < parts.length && i < numericId.length; i++){
			numericId[i].setValue(Integer.valueOf(parts[i]));
		}
	}

	/**
	 * Returns id is string representation
	 *
	 * @return id is string representation
	 */
	public String getId(){
		return Arrays.stream(numericId)
				.map(IdPath::getValue)
				.map(String::valueOf)
				.collect(Collectors.joining(":"));
	}

	/**
	 * Returns id in path representation
	 *
	 * @return id in path representation
	 */
	public String getPath(){
		return Arrays.stream(numericId)
				.map(IdPath::getValue)
				.map(StringNumberUtil::getByIndex)
				.collect(Collectors.joining("/"));
	}

	/**
	 * Returns false if achieved maximum value for all keys
	 *
	 * @return false if achieved maximum value for all keys
	 */
	public boolean increment(){
		for(int i = numericId.length - 1; i >= 0; i--){
			if(! numericId[i].increment()){
				return true;
			}
		}
		return false;
	}

	private class IdPath{

		private int value = 1;

		private int maxValue;

		IdPath(final int maxValue){
			this.maxValue = maxValue;
		}

		int getValue(){
			return value;
		}

		public void setValue(int value){
			this.value = value;
		}

		/**
		 * Increments value, returns true if value if overflows
		 *
		 * @return
		 */
		boolean increment(){
			if(value == maxValue){
				value = 1;
				return true;
			}
			value++;
			return false;
		}
	}
}
