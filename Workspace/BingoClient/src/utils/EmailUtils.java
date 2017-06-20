package utils;

/**
 * Classe de manuzeio de endere�o de e-mail.
 * @author Gustavo Ifanger
 *
 */
public class EmailUtils {
	
	/**
	 * Verifica se uma String � um endere�o de e-mail v�lido.
	 * @param email String que representa o endere�o de e-mail.
	 * @return Verdadeiro se a String for um e-mail, e falso caso n�o seja um e-mail.
	 */
	public static boolean isValidEmailAddress(String email)
	{
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
