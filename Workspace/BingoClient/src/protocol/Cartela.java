package protocol;

import java.util.ArrayList;
import java.util.Random;

public class Cartela {
	private static final int CARTELA_SIZE = 24;
	private ArrayList<Integer> cartela;

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
	
	public static int getRandomNumber()
	{
		int rdN = new Random().nextInt(98);
		return rdN + 1;
	}

	public ArrayList<Integer> getCartela() {
		return cartela;
	}

	public void setCartela(ArrayList<Integer> cartela) {
		this.cartela = cartela;
	}

	@Override
	public String toString() {
		return "Cartela [cartela=" + cartela + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartela == null) ? 0 : cartela.hashCode());
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
		Cartela other = (Cartela) obj;
		if (cartela == null) {
			if (other.cartela != null)
				return false;
		} else if (!cartela.equals(other.cartela))
			return false;
		return true;
	}
}
