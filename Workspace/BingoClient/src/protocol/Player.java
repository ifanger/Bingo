package protocol;

/**
 * Classe que representa um jogador.
 * @author Gustavo Ifanger
 *
 */
public class Player {
	/**
	 * Nome do jogador.
	 */
	private String name;
	
	/**
	 * E-mail do jogador.
	 */
	private String email;
	
	/**
	 * Senha do jogador.
	 */
	private String password;
	
	/**
	 * Quantidade de vitórias do jogador.
	 */
	private int winsCount;
	
	/**
	 * Construtor padrão da classe.
	 */
	public Player()
	{
		this.name = "";
		this.email = "";
		this.password = "";
		this.winsCount = 0;
	}

	/**
	 * Retorna o nome do jogador.
	 * @return Nome do jogador.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Altera o nome do jogador.
	 * @param name Novo nome do jogador.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retorna o e-mail do jogador.
	 * @return E-mail do jogador.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Altera o e-mail do jogador.
	 * @param email Novo e-mail do jogador.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retorna a quantidade de pontos do jogador.
	 * @return Quantidade de pontos do jogador.
	 */
	public int getWinsCount() {
		return winsCount;
	}

	/**
	 * Altera a quantidade de pontos do jogador.
	 * @param winsCount Nova quantidade de pontos do jogador.
	 */
	public void setWinsCount(int winsCount) {
		this.winsCount = winsCount;
	}

	/**
	 * Retorna a senha do jogador.
	 * @return Senha do jogador.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Altera a senha do jogador.
	 * @param password Senha do jogador.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Converte o jogador para String.
	 */
	@Override
	public String toString() {
		return "Player [name=" + name + ", email=" + email + ", winsCount=" + winsCount + "]";
	}

	/**
	 * Gera o HashCode do jogador.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + winsCount;
		return result;
	}

	/**
	 * Compara dois jogadores.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (winsCount != other.winsCount)
			return false;
		return true;
	}
	
	
	
}
