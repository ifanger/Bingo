package protocol;

/**
 * Classe responsável por métodos de criptografia.
 * @author Gustavo Ifanger
 *
 */
public class GFSecurity {
	/**
	 * Codifica uma string com criptografia MD5 e SALT.
	 * @param pass String a ser criptografada.
	 * @return String criptografada.
	 */
	public static String passwordHash(String pass)
	{
		return MD5("GF_" + pass + "_BINGO");
	}
	
	/**
	 * Criptografa uma String em MD5.
	 * @param md5 String a ser criptografada.
	 * @return String criptografada.
	 */
	public static String MD5(String md5) {
	   try {
	        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
	        byte[] array = md.digest(md5.getBytes());
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < array.length; ++i) {
	          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
	       }
	        return sb.toString();
	    } catch (java.security.NoSuchAlgorithmException e) {
	    }
	    return null;
	}
}
