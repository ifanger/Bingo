package protocol;

import java.util.ArrayList;
import java.util.Random;

/**
 * Essa classe representa uma cartela de bingo.
 * @author Gustavo Ifanger
 *
 */
public class Cartela {
	/**
	 * Armazena o tamanho m�ximo de uma cartela.
	 */
	private static final int CARTELA_SIZE = 24;
	
	/**
	 * Armazena a lista de n�meros da cartela.
	 */
	private ArrayList<Integer> cartela;

	/**
	 * Construtor padr�o da classe.
	 */
	public Cartela()
	{
		this.cartela = new ArrayList<Integer>();
		
		while(this.cartela.size() < CARTELA_SIZE)
		{
			int generatedNumber = this.getRandomNumber();
			
			if(!this.cartela.contains(generatedNumber))
				this.cartela.add(generatedNumber);
		}
	}
	
	/**
	 * Gera um n�mero aleat�rio v�lido para a cartela.
	 * @return Inteiro que representa esse n�mero aleat�rio.
	 */
	public static int getRandomNumber()
	{
		int rdN = new Random().nextInt(98);
		return rdN + 1;
	}

	/**
	 * Retorna a lista de n�meros da cartela.
	 * @return Lista de inteiros contendo os n�meros da cartela.
	 */
	public ArrayList<Integer> getCartela() {
		return cartela;
	}

	/**
	 * Altera a lista de n�meros da cartela.
	 * @param cartela Lista de inteiros.
	 */
	public void setCartela(ArrayList<Integer> cartela) {
		this.cartela = cartela;
	}

	/**
	 * Converte a cartela para string.
	 */
	@Override
	public String toString() {
		return "Cartela [cartela=" + cartela + "]";
	}

	/**
	 * Gera o hashcode da cartela.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartela == null) ? 0 : cartela.hashCode());
		return result;
	}

	/**
	 * Compara duas cartelas.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cartela other = (Cartela) obj;
		if (cartela == null) {
			if (other.cartela != null)
				return false;
		} else if (!cartela.equals(other.cartela))
			return false;
		return true;
	}
}
