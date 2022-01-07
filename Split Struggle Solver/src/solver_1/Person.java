package solver_1;

import java.util.*;

public class Person //strcu for person.
{
	//감자 감자 왕감자 <- utf-8 incoding checker. must be "gamja gamja wang gamja" in korean
	String name;
	ArrayList<Debt> debtList = new ArrayList<Debt>();
	ArrayList<Bond> bondList = new ArrayList<Bond>();
	double totalPurchase; //total contribute = total purchases
	double totalDebts; //the amount of money he have to pay to all 	
	
	Person next;
	
	
	public Person(String name)
	{
		this.name = name;
	}
	
	
	public void addDebt(Debt debt)
	{
		debtList.add(debt);
	}
	
	public void addBond(Bond bond)
	{
		bondList.add(bond);
	}
	
		
}
