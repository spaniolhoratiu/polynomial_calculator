package main_package;

import java.util.Comparator;

//Comparator to sort polynomials in decreasing order of their degree(normal form of a polynomial).
//Specifically required to implement the division between two polynomials.

public class MonomialComparator implements Comparator<Monomial>
{
	@Override
	public int compare(Monomial m1, Monomial m2)
	{
		if(m1.getDegree() < m2.getDegree())
		{
			return 1;
		}
		else
		{
			return -1;
		}
	}

}
