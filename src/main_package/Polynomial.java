package main_package;

import java.util.Iterator;
import java.util.TreeSet;

public class Polynomial implements PolynomialOperations
{
	protected TreeSet<Monomial> monomialList;

	public Polynomial()
	{
		this.monomialList = new TreeSet<Monomial>(new MonomialComparator());
	}

	public Polynomial(String polynomialExpression)
	{
		this.monomialList = new TreeSet<Monomial>(new MonomialComparator());

		String[] monomialsInStrings = polynomialExpression.split("\\+");
		for (String monomialExpression : monomialsInStrings)
		{
			Monomial newMonomial = new Monomial();
			String[] coefficientAndDegree = monomialExpression.split("x\\^");
			newMonomial.setCoefficient((int)Float.parseFloat(coefficientAndDegree[0])); // When creating a new polynomial, always create it with integer coefficients.
			newMonomial.setDegree(Integer.parseInt(coefficientAndDegree[1]));

			this.insertMonomial(newMonomial);
		}
	}

	public TreeSet<Monomial> getMonomials()
	{
		return this.monomialList;
	}

	public Monomial findMonomialWithDegree(int degree)
	{
		for (Monomial a : this.monomialList)
		{
			if (a.getDegree() == degree)
			{
				return a;
			}
		}
		return null;
	}

	public void insertMonomial(Monomial toInsert)
	{
			if (this.findMonomialWithDegree(toInsert.getDegree()) != null)
			{
				Monomial auxMonomial = new Monomial();
				auxMonomial.setDegree(this.findMonomialWithDegree(toInsert.getDegree()).getDegree());
				auxMonomial.setCoefficient(this.findMonomialWithDegree(toInsert.getDegree()).getCoefficient() + toInsert.getCoefficient());

				// If there is already a monomial with the same degree, add this monomial to the
				// monomial with the same degree.
				// Example: Adding 5x^5 to 2x^5+4x^3 will result in 7x^5+4x^3
				Iterator<Monomial> itr = monomialList.iterator();
				while (itr.hasNext())
				{
					Monomial s = itr.next();
					if (s == this.findMonomialWithDegree(toInsert.getDegree()))
					{
						itr.remove();
					}
				}
				this.monomialList.add(auxMonomial);
			}
			else
			{
				this.monomialList.add(toInsert);
			}
	}

	@Override
	public Polynomial add(Polynomial toAdd)
	{
		Polynomial auxPolynomial = new Polynomial();

		for (Monomial a : this.monomialList)
		{
			for (Monomial b : toAdd.monomialList)
			{
				if (a.add(b) != null && a.used == false && b.used == false)
				{
					auxPolynomial.insertMonomial(a.add(b));
					a.used = true;
					b.used = true;
				}
			}
		}

		for (Monomial a : this.monomialList)
		{
			if (a.used == false)
			{
				auxPolynomial.insertMonomial(a);
				a.used = true;
			}
		}

		for (Monomial b : toAdd.monomialList)
		{
			if (b.used == false)
			{
				auxPolynomial.insertMonomial(b);
				b.used = true;
			}
		}

		auxPolynomial.removeZeroCoefficientMonomials();
		return auxPolynomial;
	}

	@Override
	public Polynomial subtract(Polynomial toSubtract)
	{
		Polynomial auxPolynomial = new Polynomial();

		for (Monomial a : this.monomialList)
		{
			for (Monomial b : toSubtract.monomialList)
			{
				if (a.subtract(b) != null && a.used == false && b.used == false)
				{
					auxPolynomial.insertMonomial(a.subtract(b));
					a.used = true;
					b.used = true;
				}
			}
		}

		for (Monomial a : this.monomialList)
		{
			if (a.used == false)
			{
				auxPolynomial.insertMonomial(a);
				a.used = true;
			}
		}

		for (Monomial b : toSubtract.monomialList)
		{
			if (b.used == false)
			{
				Monomial auxMonomial = new Monomial();
				auxMonomial.setCoefficient(-1 * b.getCoefficient());
				auxMonomial.setDegree(b.getDegree());
				auxPolynomial.insertMonomial(auxMonomial);
				b.used = true;
			}
		}

		auxPolynomial.removeZeroCoefficientMonomials();
		return auxPolynomial;
	}

	@Override
	public Polynomial multiply(Polynomial toMultiply)
	{
		Polynomial auxPolynomial = new Polynomial();

		for (Monomial a : this.monomialList)
		{
			for (Monomial b : toMultiply.monomialList)
			{
				auxPolynomial.insertMonomial(a.multiply(b));
			}
		}

		auxPolynomial.removeZeroCoefficientMonomials();
		return auxPolynomial;
	}

	public Polynomial multiplyWithMonomial(Monomial x)
	{
		Polynomial auxPolynomial = new Polynomial();
		
		for (Monomial a : this.monomialList)
		{
			Monomial auxMonomial = new Monomial();
			auxMonomial.setCoefficient(a.getCoefficient() * x.getCoefficient());
			auxMonomial.setDegree(a.getDegree() + x.getDegree());
			auxPolynomial.insertMonomial(auxMonomial);
		}
		
		auxPolynomial.removeZeroCoefficientMonomials();
		return auxPolynomial;
	}

	//@SuppressWarnings("null")
	@Override
	public String divide(Polynomial toDivide)
	{
		Polynomial quotient = new Polynomial();
		Polynomial toSubtractForNewDivident = new Polynomial();
		Polynomial newDivident = new Polynomial();
		for(Monomial a : this.monomialList)
		{
			newDivident.insertMonomial(a);
		}
		
		while (newDivident.monomialList.isEmpty() == false && newDivident.getMaxDegree() >= toDivide.getMaxDegree())
		{
			  for(Monomial a : toDivide.monomialList)
			  {
				  toSubtractForNewDivident.insertMonomial(a);
			  }
			  
			  Monomial t = new Monomial();
			  t.setCoefficient(newDivident.getMonomials().first().getCoefficient() / toDivide.getMonomials().first().getCoefficient());
			  t.setDegree(newDivident.getMonomials().first().getDegree() - toDivide.getMonomials().first().getDegree()); 
			  quotient.insertMonomial(t);
			  toSubtractForNewDivident = toSubtractForNewDivident.multiplyWithMonomial(t);
			  
			  newDivident = newDivident.subtract(toSubtractForNewDivident);
			  newDivident.resetPolynomial();
			  newDivident.removeZeroCoefficientMonomials();
			  
			  
			  if(t.getDegree() == 0 && t.getCoefficient() == (float) 0) 
			  {
				  break;
			  }
			  toSubtractForNewDivident.deleteAllMonomials();
		}

		String divideResultString = "Quotient:" + quotient.toString() + "	Remainder:" + newDivident.toString(); 
		return divideResultString;
	}
	
	@Override
	public Polynomial derivate()
	{
		Polynomial auxPolynomial = new Polynomial();

		for (Monomial a : this.monomialList)
		{
			auxPolynomial.insertMonomial(a.derivate());
		}

		auxPolynomial.removeZeroCoefficientMonomials();
		return auxPolynomial;
	}

	@Override
	public Polynomial integrate()
	{
		Polynomial auxPolynomial = new Polynomial();

		for (Monomial a : this.monomialList)
		{
			auxPolynomial.insertMonomial(a.integrate());
		}

		auxPolynomial.removeZeroCoefficientMonomials();
		return auxPolynomial;
	}

	public String toString()
	{
		boolean firstExecute = true;
		String polynomialString = "";
		for (Monomial a : this.monomialList)
		{
			// At first, form the polynomial by just writing the first monome. This can be
			// 2x or -2x which is correctly written in both cases.
			if (firstExecute == true)
			{
				polynomialString = polynomialString + a.toString();
			} else
			{
				// This else branch was made to avoid cases such as: 2x^21x^1 instead of
				// 2x^2+1x^1
				if (a.getCoefficient() > 0)
				{
					polynomialString = polynomialString + "+" + a.toString();
				} else if (a.getCoefficient() < 0)
				{
					polynomialString = polynomialString + a.toString();
				}
			}

			firstExecute = false;
		}
		return polynomialString;
	}

	public int getMaxDegree()
	{
		return this.monomialList.first().getDegree();
	}

	public void resetPolynomial()
	{
		for (Monomial a : this.monomialList)
		{
			a.used = false;
		}
	}
	
	public void removeZeroCoefficientMonomials()
	{
		Iterator<Monomial> itr = monomialList.iterator();
		while (itr.hasNext())
		{
			Monomial s = itr.next();
			if (s.getCoefficient() == (float) 0)
			{
				itr.remove();
			}
		}
	}
	
	public void deleteAllMonomials()
	{
		this.monomialList.clear();
	}

}
