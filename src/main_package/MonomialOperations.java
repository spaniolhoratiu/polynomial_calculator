package main_package;

public interface MonomialOperations
{
	public Monomial add(Monomial y);
	public Monomial subtract(Monomial y);
	public Monomial multiply(Monomial y);
	public Monomial divide(Monomial y);
	public Monomial derivate();
	public Monomial integrate();
}
