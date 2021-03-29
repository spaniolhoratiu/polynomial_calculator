package main_package;

public interface PolynomialOperations
{
	public Polynomial add(Polynomial y);
	public Polynomial subtract(Polynomial y);
	public Polynomial multiply(Polynomial y);
	public String divide(Polynomial y);
	public Polynomial derivate();
	public Polynomial integrate();
}
