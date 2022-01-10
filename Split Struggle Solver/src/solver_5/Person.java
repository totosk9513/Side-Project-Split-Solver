package solver_5;

import java.util.*;

public class Person //strcu for person.
{
	//감자 감자 왕감자 <- utf-8 incoding checker. must be "gamja gamja wang gamja" in korean
	String name;
	ArrayList<Debt> debtList = new ArrayList<Debt>();
	ArrayList<Bond> bondList = new ArrayList<Bond>();
	
	ArrayList<Person> debtors = new ArrayList<Person>();
	ArrayList<Person> creditors = new ArrayList<Person>();	
	
	double totalPurchase; //total contribute = total purchases
	double totalDebts; //the amount of money he have to pay to all 	
	
	Person next;
	
	
	public Person(String name)
	{
		this.name = name;
	}
	
	public void setOrChangeName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
		
	public void addDebt(Debt debt)
	{
		debtList.add(debt);
	}
	
	public void addBond(Bond bond)
	{
		bondList.add(bond);
	}
	
	public void addDebtor(Person debtor)
	{
		debtors.add(debtor);
	}
	
	public void addCreditor(Person creditor)
	{
		creditors.add(creditor);
	}
	
	public void clearDebtList()
	{
		debtList.clear();
	}
	
	public void clearBondList()
	{
		bondList.clear();
	}
	public void clearDebtors()
	{
		debtors.clear();
	}
	public void clearCreditors()
	{
		creditors.clear();
	}
	
	public void setTotalPurchaseManual(double cost)
	{
		this.totalPurchase = cost;
	}
	
	public void setTotalPurchaseAuto()
	{
		double purch = 0.0;
		for (int i = 0; i < bondList.size(); i++)
		{
			Bond temp = bondList.get(i);
			purch = purch + temp.totalCost;
		}
		totalPurchase = purch;
	}
	
	public double getTotalPurchase()
	{
		return this.totalPurchase;
	}
	
	public void setTotalDebtsManual(double debtCost)
	{
		totalDebts = debtCost;
	}
	
	public void setTotalDebtsAuto()
	{
		double debt = 0.0;
		for (int i = 0; i < debtList.size(); i++)
		{
			Debt temp = debtList.get(i);
			System.out.println(temp.item.name + " costs " + temp.issuedCost);
			debt = debt + temp.issuedCost;
		}
		totalDebts = debt;
	}
	
	public double getTotalDebts()
	{
		return totalDebts;
	}
	
		
}
