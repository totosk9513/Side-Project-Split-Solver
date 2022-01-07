package solver_4;
import java.util.*;



public class Backends 
{
	Scanner sc = new Scanner(System.in);
	//FrontheadUI frnthd = new FrontheadUI();
	
	public void test()
	{
		//System.out.print("TEST!");
	}
		
	public double payAmount(Person debtor, Person creditor)
	{
		//System.out.println("Payamount!");
		double result = 0.0;
		//System.out.println(debtor.debtList.size());
		
		for (int i = 0; i < debtor.debtList.size(); i++)
		{
			Debt temp = debtor.debtList.get(i);
			//System.out.println(temp.creditor.name);
			if (temp.creditor == creditor)
			{
				//System.out.print(temp.issuedCost + " ");
				result = result + temp.issuedCost;
			}
		}		
		return result;
	}
	
//	for (int i = 0; i < debtor.debtList.size(); i++)
//	{
//		Debt temp = debtor.debtList.get(i);
//		System.out.println(temp.creditor.name);
//		if (temp.creditor == creditor)
//		{
//			System.out.print(temp.issuedCost + " ");
//			result = result + temp.issuedCost;
//		}
//	}
	
	
	
	/*
	public double retrivedAmount(Person creditor, Person debtor)
	{
		double result = 0.0;
		
		for (int i = 0; i < creditor.bondList.size(); i++)
		{
			Debt temp = debtor.debtList.get(i);
			if (temp.creditor.equals(creditor) == true)
			{
				result = result + temp.issuedCost;
			}
		}
		
		return result;
	}
	*/
}
