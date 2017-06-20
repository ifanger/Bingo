package utils;

/**
 * Classe de manuzeio de endereço de e-mail.
 * @author Gustavo Ifanger
 *
 */
public class EmailUtils {
	
	/**
	 * Verifica se uma String é um endereço de e-mail válido.
	 * @param email String que representa o endereço de e-mail.
	 * @return Verdadeiro se a String for um e-mail, e falso caso não seja um e-mail.
	 */
	public static boolean isValidEmailAddress(String email)
	{
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
