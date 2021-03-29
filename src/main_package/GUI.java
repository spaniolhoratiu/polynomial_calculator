package main_package;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI
{
	private Polynomial p1;
	private Polynomial p2;

	static JFrame jframe;

	JPanel p1Panel;
	JLabel p1Label;
	JTextField p1TextField;
	JButton p1OkButton;
	
	JPanel p2Panel;
	JLabel p2Label;
	JTextField p2TextField;
	JButton p2OkButton;
	
	JPanel basicOperationsPanel;
	JButton plusButton;
	JButton minusButton;
	JButton multiplyButton;
	JButton divideButton;
	
	JPanel advancedOperationsPanel;
	JButton p1IntegrateButton;
	JButton p1DerivateButton;
	JButton p2IntegrateButton;
	JButton p2DerivateButton;
	
	JPanel resultPanel;
	JLabel resultLabel;
	JTextField resultTextField;
	JButton clearButton;
	
	JPanel finalPanel;
	
	GUI()
	{
		p1 = new Polynomial();
		p2 = new Polynomial();

		jframe = new JFrame("Polynomial Processing");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(600, 400);
		
		
		//Panel 1. Input polynomial 1.
		p1Panel = new JPanel();
		p1Label = new JLabel("Polynomial 1:");
		p1TextField = new JTextField();
		Font timesNewRomanFont = new Font("Times New Roman", Font.BOLD,15);
		p1TextField.setFont(timesNewRomanFont);
		p1TextField.setPreferredSize(new Dimension(400, 25));
		p1OkButton = new JButton("OK");
		p1OkButton.addActionListener(new ActionListenerP1OKButton());
		//p1Panel.setBounds(0, 0, 400, 25);
		p1Panel.setLayout(new FlowLayout());
		p1Panel.add(p1Label);
		p1Panel.add(p1TextField);	
		p1Panel.add(p1OkButton);
		
		//Panel 2. Input polynomial 2.
		p2Panel = new JPanel();
		p2Label = new JLabel("Polynomial 2:");
		p2TextField = new JTextField();
		p2TextField.setFont(timesNewRomanFont);
		p2TextField.setPreferredSize(new Dimension(400, 25));
		p2OkButton = new JButton("OK");
		p2OkButton.addActionListener(new ActionListenerP2OKButton());
		//p2Panel.setBounds(0, 25, 400, -25);
		p2Panel.setLayout(new FlowLayout());
		p2Panel.add(p2Label);
		p2Panel.add(p2TextField);	
		p2Panel.add(p2OkButton);
		
		//Panel 3. Basic operations(add, subtract, multiply, divide)
		basicOperationsPanel = new JPanel();
		plusButton = new JButton("+");
		plusButton.addActionListener(new ActionListenerPlusButton());
		minusButton = new JButton("-");
		minusButton.addActionListener(new ActionListenerMinusButton());
		multiplyButton = new JButton("*");
		multiplyButton.addActionListener(new ActionListenerMultiplyButton());
		divideButton = new JButton("/");
		divideButton.addActionListener(new ActionListenerDivideButton());
		basicOperationsPanel.setLayout(new FlowLayout());
		basicOperationsPanel.add(plusButton);
		basicOperationsPanel.add(minusButton);
		basicOperationsPanel.add(multiplyButton);
		basicOperationsPanel.add(divideButton);
		
		//Panel 4. Advanced operations(integration, derivation)
		advancedOperationsPanel = new JPanel();
		p1DerivateButton = new JButton("Derivate P1");
		p1DerivateButton.addActionListener(new ActionListenerDerivateFirstPolynomial());
		p2DerivateButton = new JButton("Derivate P2");
		p2DerivateButton.addActionListener(new ActionListenerDerivateSecondPolynomial());
		p1IntegrateButton = new JButton("Integrate P1");
		p1IntegrateButton.addActionListener(new ActionListenerIntegrateFirstPolynomial());
		p2IntegrateButton = new JButton("Integrate P2");
		p2IntegrateButton.addActionListener(new ActionListenerIntegrateSecondPolynomial());
		
		advancedOperationsPanel.setLayout(new FlowLayout());
		advancedOperationsPanel.add(p1IntegrateButton);		
		advancedOperationsPanel.add(p1DerivateButton);		
		advancedOperationsPanel.add(p2IntegrateButton);		
		advancedOperationsPanel.add(p2DerivateButton);		

		//Panel 5. Result
		resultPanel = new JPanel();
		resultLabel = new JLabel("Result:");
		resultTextField = new JTextField();
		Font resultFont = new Font("Courier", Font.BOLD, 20);
		resultTextField.setFont(resultFont);
		resultTextField.setPreferredSize(new Dimension(450, 35));
		
		clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListenerClear());
		
		resultPanel.setLayout(new FlowLayout());
		resultPanel.add(resultLabel);
		resultPanel.add(resultTextField);
		resultPanel.add(clearButton);
		
		

		finalPanel = new JPanel();
		finalPanel.add(p1Panel);
		finalPanel.add(p2Panel);
		finalPanel.add(basicOperationsPanel);
		finalPanel.add(advancedOperationsPanel);
		finalPanel.add(resultPanel);
		finalPanel.setLayout(new BoxLayout(finalPanel, BoxLayout.PAGE_AXIS));
		jframe.setResizable(false);
		jframe.add(finalPanel);
		jframe.setContentPane(finalPanel);
		jframe.setVisible(true);
	}

	public static void main(String[] args)
	{
		GUI gui = new GUI();
		/*
		 * Polynomial a = new Polynomial("3.2x^3"); System.out.println(a.toString()); a
		 * = a.integrate(); System.out.println(a.toString());
		 */
		/*
		 * System.out.println("Started running..."); Polynomial a = new
		 * Polynomial("1x^3+1x^2+5x^0"); Polynomial b = new Polynomial("1x^3+1x^2");
		 * System.out.println(a.subtract(b));
		 */
		
		/*
		 * Polynomial a = new Polynomial("1x^4+2x^3+1x^2+5x^0"); Polynomial b = new
		 * Polynomial("1x^1+1x^0"); System.out.println(a.add(b).toString());
		 */
	}

	
	private class ActionListenerP1OKButton implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			try 
			{
				p1 = new Polynomial(p1TextField.getText());
				p1TextField.setText(p1.toString());

			}
			catch(Exception e)
			{
				 JOptionPane.showMessageDialog(null, "Bad input for polynomial 1", "Error", JOptionPane.ERROR_MESSAGE);
				 p1TextField.setText("Re-enter polynomial here!");
			}
			finally
			{
				p1.resetPolynomial();
				p2.resetPolynomial();
			}
		}
	}
	
	private class ActionListenerP2OKButton implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			try 
			{
				p2 = new Polynomial(p2TextField.getText());
				p2TextField.setText(p2.toString());

			}
			catch(Exception e)
			{
				 JOptionPane.showMessageDialog(null, "Bad input for polynomial 2", "Error", JOptionPane.ERROR_MESSAGE);
				 p2TextField.setText("Re-enter polynomial here!");
			}
			finally
			{
				p1.resetPolynomial();
				p2.resetPolynomial();
			}
		}
	}
	
	
	private class ActionListenerPlusButton implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Polynomial aux = p1.add(p2);
			resultTextField.setText(aux.toString());
			p1.resetPolynomial();
			p2.resetPolynomial();
		}
	}

	private class ActionListenerMinusButton implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Polynomial aux = p1.subtract(p2);
			resultTextField.setText(aux.toString());
			p1.resetPolynomial();
			p2.resetPolynomial();
		}
	}

	private class ActionListenerMultiplyButton implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Polynomial aux = p1.multiply(p2);
			resultTextField.setText(aux.toString());
			p1.resetPolynomial();
			p2.resetPolynomial();
		}
	}

	private class ActionListenerDivideButton implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if(p2.getMonomials().size() == 1 && p2.getMonomials().first().getCoefficient() == 0.0)
			{
				resultTextField.setText("Divison by 0 detected. Not possible.");
			}
			else
			{
				resultTextField.setText(p1.divide(p2));
				p1.resetPolynomial();
				p2.resetPolynomial();	
				
			}
		}
	}

	private class ActionListenerDerivateFirstPolynomial implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Polynomial aux = p1.derivate();
			resultTextField.setText(aux.toString());
			p1.resetPolynomial();
		}
	}

	private class ActionListenerDerivateSecondPolynomial implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Polynomial aux = p2.derivate();
			resultTextField.setText(aux.toString());
			p2.resetPolynomial();
		}
	}

	private class ActionListenerIntegrateFirstPolynomial implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Polynomial aux = p1.integrate();
			resultTextField.setText(aux.toString());
			p1.resetPolynomial();
		}
	}

	private class ActionListenerIntegrateSecondPolynomial implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Polynomial aux = p2.integrate();
			resultTextField.setText(aux.toString());
			p2.resetPolynomial();
		}
	}

	private class ActionListenerClear implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			resultTextField.setText("");
			p1TextField.setText("");
			p2TextField.setText("");
			
			p1.deleteAllMonomials();
			p2.deleteAllMonomials();
		}
	}

}
