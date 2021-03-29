package main_package;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.swing.JOptionPane;

public class Monomial implements MonomialOperations
{
	private int degree;
	private float coefficient;
	protected boolean used;
	
	Monomial()
	{
		this.degree = 0;
		this.coefficient = 0;
		this.used = false;
	}
	
	Monomial(int degree, int coefficient)
	{
		this.degree = degree;
		this.coefficient = coefficient;
		this.used = false;
	}
	
	@Override
	public Monomial add(Monomial toAdd)
	{
		Monomial auxMonomial = new Monomial();
		if(this.degree == toAdd.degree)
		{
			auxMonomial.setDegree(toAdd.degree);
			auxMonomial.setCoefficient(this.coefficient + toAdd.coefficient);
			return auxMonomial;
		}
		return null;
		
	}
	
	@Override
	public Monomial subtract(Monomial toSubtract)
	{
		Monomial auxMonomial = new Monomial();
		if(this.degree == toSubtract.degree)
		{
			auxMonomial.setDegree(this.degree);
			auxMonomial.setCoefficient(this.coefficient - toSubtract.coefficient);
			return auxMonomial;
		}
		return null;
		
	}

	@Override
	public Monomial multiply(Monomial toMultiply)
	{
		Monomial auxMonomial = new Monomial();
		auxMonomial.setDegree(this.degree + toMultiply.degree);
		auxMonomial.setCoefficient(this.coefficient * toMultiply.coefficient);
		return auxMonomial;
	}
	
	@Override
	public Monomial divide(Monomial toDivide)
	{
		Monomial auxMonomial = new Monomial();
		auxMonomial.setDegree(this.degree - toDivide.degree);
		auxMonomial.setCoefficient(this.coefficient / toDivide.coefficient);
		return auxMonomial;
	}
	
	@Override
	public Monomial derivate()
	{
		Monomial auxMonomial = new Monomial();
		auxMonomial.setCoefficient(this.coefficient * this.degree);
		auxMonomial.setDegree(this.degree - 1);
		return auxMonomial;
	}
	
	public Monomial integrate()
	{
		Monomial auxMonomial = new Monomial();
		auxMonomial.setDegree(this.degree + 1);
		auxMonomial.setCoefficient(this.coefficient / (this.degree + 1));
		return auxMonomial;
	}
	
	public void setDegree(int a)
	{
		this.degree = a;
	}
	
	public int getDegree()
	{
		return this.degree;
	}
	
	public void setCoefficient(float a)
	{
		this.coefficient = a;
	}
	
	public float getCoefficient()
	{
		return this.coefficient;
	}
	
	public String toString()
	{
		// 
		DecimalFormat myDecimalFormat = new DecimalFormat("0.##");
		String coefficientInString = myDecimalFormat.format(this.coefficient);
		if(this.degree == 1)
		{
			return coefficientInString + "x";
		}
		else if(this.degree == 0)
		{
			return coefficientInString + "";
		}
		else
		{
			return coefficientInString + "x^" + this.degree + "";			
		}
	}

}
