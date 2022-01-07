package solver_2;
import java.util.*;

public class FrontheadUI 
{
	Scanner sc = new Scanner(System.in);
	Backends bknd = new Backends();
	//DB db = new DB();
	
	public boolean yesOrNo()
	{
		char x = sc.next().charAt(0);
		boolean result = false;
		
		if (( (x == 'Y') || (x == 'y') || (x == 'N') || (x == 'n') ) == false)
		{
			while( ( (x == 'Y') || (x == 'y') || (x == 'N') || (x == 'n') ) == false )
			{
				System.out.print("Please only input Y/y/N/n!");
				x = sc.next().charAt(0);
			}
		}
		else if ( (x == 'Y') || (x == 'y'))
		{
			result = true;
		}
		else
		{
			result = false;
		}
		
		return result;
	}
	
	public void showFunction(DB db)
	{
		System.out.println("Select one of the numbers below to show data: \n"
				+ "0:Do nothing; Exit \n"
				+ "1:Show all results \n"
				+ "2:Show one person's result \n"
				+ "3:Show a result between 2 persons \n");
		int selector = -1;
		selector = sc.nextInt();
		
		switch(selector)
		{
			case 0: //exit
				System.out.println("Choose do nothing, exit");
				break;
			case 1: //show all results
				break;
			case 2: //show one's person's result
				Person target = db.selectPersonFromAllPeople();	
				
				if (target == null)
				{
					System.out.println("Nothing cannot be printed since the list is empty!");
					break;
				}
				else if (db.ItemDB.isEmpty() == true)
				{
					System.out.println("Nothing cannot be printed since no item inputted!");
					break;
				}
				else
				{
					if (target.debtList.isEmpty() == true)
					{
						System.out.println("\"" + target.name + "\" has no debts!");
					}
					if (target.bondList.isEmpty() == true)
					{
						System.out.println("\"" + target.name + "\" has no bonds!");
					}
				}
				
				double net = target.totalPurchase - target.totalDebts;
				System.out.println("Debugger: totalPurch: " + target.totalPurchase + " totaldebt: " + target.totalDebts);
				if (net > 0)
				{
					System.out.println(target.name + " have to retrived " + net + " from others");
				}
				else if (net < 0)
				{
					System.out.println(target.name + " have to pay " + ( net * (-1) ) + " to others");
				}
				else
				{
					System.out.println(target.name + " has zero net results");
				}	
				break;
			case 3: //show a mutual result.
				Person targetA = db.selectPersonFromAllPeople();
				Person targetB = db.selectPersonFromAllPeople();
				if (targetB == targetA)
				{
					do
					{
						System.out.println("Picked two people are same! Please choose two differenet people");
						targetA = db.selectPersonFromAllPeople();
						targetB = db.selectPersonFromAllPeople();
					} while (targetB == targetA);
				}
				
				double debtAtoB = 0, debtBtoA = 0;
				
				for (int i = 0; i < targetA.debtList.size(); i++)
				{
					Debt debtA = targetA.debtList.get(i);
					if (debtA.creditor == targetB)
					{
						debtAtoB = debtAtoB + debtA.issuedCost;
					
					}
				}
				
				for (int i = 0; i < targetB.debtList.size(); i++)
				{
					Debt debtB = targetB.debtList.get(i);
					if (debtB.creditor == targetA)
					{
						debtBtoA = debtBtoA + debtB.issuedCost;
					}
				}
				
				if (debtAtoB > debtBtoA)
				{
					double debt = debtAtoB - debtBtoA;
					System.out.println("\"" + targetA.name + "\" have to pay " + debt + " to \"" + targetB.name + "\"");
				}
				else if (debtAtoB < debtBtoA)
				{
					double debt = debtBtoA - debtAtoB;
					System.out.println("\"" + targetB.name + "\" have to pay " + debt + " to \"" + targetA.name + "\"");
				}
				else
				{
					System.out.println("They paid same amount for each other! Don't need to pay back!");
				}
				
				
				break;
			default:
				System.out.println("Invalid input. Please input one of the numbers of the instructions.");
				break;
				
		}
		
		
	}
	
	
}
