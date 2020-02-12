package veil.internetshop.simple.modules.directoryresolver;

/**
 * Contains methods for converting numeric id to string
 */
public class StringNumberUtil{

	private static final int ALPHABET_LETTERS_COUNT = 26;

	private StringNumberUtil(){
		throw new UnsupportedOperationException(this.getClass().getSimpleName() + " is util class");
	}

	/**
	 * Returns string id by numeric equivalent
	 *
	 * @param number - numeric id
	 * @return string id by numeric equivalent
	 */
	public static String getByIndex(long number){
		StringBuilder answer = new StringBuilder();

		while(number > 0){
			number -= 1;
			char let = (char)((number) % ALPHABET_LETTERS_COUNT + 'A');
			answer.insert(0, let);
			number /= ALPHABET_LETTERS_COUNT;
		}
		return answer.toString();
	}

	/**
	 * Returns number equivalent of string id
	 *
	 * @param id - string id
	 * @return number equivalent of string id
	 */
	public static long getIndex(String id){
		int length = id.length();
		int index = 0;
		long answer = 0;

		while(index < length){
			answer *= ALPHABET_LETTERS_COUNT;
			answer += ((int)id.charAt(index) - 'A' + 1);
			index++;
		}
		return answer;
	}
}
