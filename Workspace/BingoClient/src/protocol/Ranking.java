package protocol;

/**
 * Representa o Ranking de jogadores.
 * @author Gustavo Ifanger
 *
 */
public class Ranking {
	/**
	 * Primeiro colocado no Ranking.
	 */
	private Player firstPlayer;
	
	/**
	 * Segundo colocado no Ranking.
	 */
	private Player secondPlayer;
	
	/**
	 * Terceiro colocado no Ranking.
	 */
	private Player thirdPlayer;
	
	/**
	 * Construtor padrão da classe.
	 * @param p1 Primeiro colocado do Ranking. (pode ser null)
	 * @param p2 Segundo colocado do Ranking. (pode ser null)
	 * @param p3 Terceiro colocado do Ranking. (pode ser null)
	 */
	public Ranking(Player p1, Player p2, Player p3)
	{
		this.firstPlayer = p1;
		this.secondPlayer = p2;
		this.thirdPlayer = p3;
	}
	
	/**
	 * Retorna o primeiro colocado.
	 * @return Objeto do tipo Player que representa o primeiro colocado.
	 */
	public Player getFirstPlayer() {
		return firstPlayer;
	}

	/**
	 * Retorna o segundo colocado.
	 * @return Objeto do tipo Player que representa o segundo colocado.
	 */
	public Player getSecondPlayer() {
		return secondPlayer;
	}
	
	/**
	 * Retorna o terceiro colocado.
	 * @return Objeto do tipo Player que representa o terceiro colocado.
	 */
	public Player getThirdPlayer() {
		return thirdPlayer;
	}

	/**
	 * Obtem o HashCode do Ranking.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstPlayer == null) ? 0 : firstPlayer.hashCode());
		result = prime * result + ((secondPlayer == null) ? 0 : secondPlayer.hashCode());
		result = prime * result + ((thirdPlayer == null) ? 0 : thirdPlayer.hashCode());
		return result;
	}

	/**
	 * Compara dois Rankings.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ranking other = (Ranking) obj;
		if (firstPlayer == null) {
			if (other.firstPlayer != null)
				return false;
		} else if (!firstPlayer.equals(other.firstPlayer))
			return false;
		if (secondPlayer == null) {
			if (other.secondPlayer != null)
				return false;
		} else if (!secondPlayer.equals(other.secondPlayer))
			return false;
		if (thirdPlayer == null) {
			if (other.thirdPlayer != null)
				return false;
		} else if (!thirdPlayer.equals(other.thirdPlayer))
			return false;
		return true;
	}

	/**
	 * Converte o Ranking para String.
	 */
	@Override
	public String toString() {
		return "Ranking [firstPlayer=" + firstPlayer + ", secondPlayer=" + secondPlayer + ", thirdPlayer=" + thirdPlayer
				+ "]";
	}
	
	
}
