package protocol;

public class Ranking {
	private Player firstPlayer;
	private Player secondPlayer;
	private Player thirdPlayer;
	
	public Ranking(Player p1, Player p2, Player p3)
	{
		this.firstPlayer = p1;
		this.secondPlayer = p2;
		this.thirdPlayer = p3;
	}
	
	public Player getFirstPlayer() {
		return firstPlayer;
	}
	
	public Player getSecondPlayer() {
		return secondPlayer;
	}
	
	public Player getThirdPlayer() {
		return thirdPlayer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstPlayer == null) ? 0 : firstPlayer.hashCode());
		result = prime * result + ((secondPlayer == null) ? 0 : secondPlayer.hashCode());
		result = prime * result + ((thirdPlayer == null) ? 0 : thirdPlayer.hashCode());
		return result;
	}

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

	@Override
	public String toString() {
		return "Ranking [firstPlayer=" + firstPlayer + ", secondPlayer=" + secondPlayer + ", thirdPlayer=" + thirdPlayer
				+ "]";
	}
	
	
}
