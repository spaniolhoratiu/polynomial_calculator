package main_package;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

public class JUnitTests
{
	private Polynomial testPolynomial1;
	private Polynomial testPolynomial2;

	@Test
	public void test1()
	{
		testPolynomial1 = new Polynomial("1x^4+2x^3+1x^2+5x^0");
		testPolynomial2 = new Polynomial("1x^1+1x^0");
		String output = testPolynomial1.add(testPolynomial2).toString();
		assertEquals("1x^4+2x^3+1x^2+1x+6", output);
	}

	
	  @Test
	  public void test2()
	  { 
		  testPolynomial1 = new Polynomial("1x^4+2x^3+1x^2+5x^0");
		  testPolynomial2 = new Polynomial("1x^1+1x^0");
		  String output = testPolynomial1.subtract(testPolynomial2).toString();
		  assertEquals("1x^4+2x^3+1x^2-1x+4", output);
	  }
	  
	  @Test
	  public void test3()
	  { 
		  testPolynomial1 = new Polynomial("1x^4+2x^3+1x^2+5x^0");
		  testPolynomial2 = new Polynomial("1x^1+1x^0");
		  String output = testPolynomial1.multiply(testPolynomial2).toString();
		  assertEquals("1x^5+3x^4+3x^3+1x^2+5x+5", output);
	  }
	  
	  @Test
	  public void test4()
	  { 
		  testPolynomial1 = new Polynomial("1x^4+2x^3+1x^2+5x^0");
		  testPolynomial2 = new Polynomial("1x^1+1x^0");
		  String output = testPolynomial1.divide(testPolynomial2).toString();
		  assertEquals("Quotient:1x^3+1x^2	Remainder:5", output);
	  }
	  
	  @Test
	  public void test5()
	  { 
		  testPolynomial1 = new Polynomial("1x^4+2x^3+1x^2+5x^0");
		  String output = testPolynomial1.derivate().toString();
		  assertEquals("4x^3+6x^2+2x", output);
	  }
	  
	  @Test
	  public void test6()
	  { 
		  testPolynomial1 = new Polynomial("1x^4+2x^3+1x^2+5x^0");
		  String output = testPolynomial1.integrate().toString();
		  assertEquals("0.2x^5+0.5x^4+0.33x^3+5x", output);
	  }

	 
	 
}
