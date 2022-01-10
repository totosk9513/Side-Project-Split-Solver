package solver_5;
import java.util.*;

public class FrontalUI 
{
	Scanner sc = new Scanner(System.in);
	Back bk = new Back();
	//DB db = new DB();
	
	public boolean yesOrNo()
	{
		char x = sc.next().charAt(0);
		boolean result = false;
		
		if (( (x == 'Y') || (x == 'y') || (x == 'N') || (x == 'n') ) == false)
		{
			while( ( (x == 'Y') || (x == 'y') || (x == 'N') || (x == 'n') ) == false )
			{
				System.out.print("Please only input Y/y/N/n!: ");
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
	
	/*public void showTwoPersonsResults(Person A, Person B)
	{
		
	}*/
	public void showData(DB db) //何盒肯己
	{
		System.out.println("Select one of the numbers below for next options: \n"
				+ "0:Exit\t"
				+ "1:Show Items\t"
				+ "2:Show Persons\t"
				+ "3:Show Reciepts (Not implemented Yet!)\t");
		int indicator = -1;
		
		indicator = sc.nextInt();
		
		switch(indicator)
		{
		case 0: //exit
			System.out.println("Exit the menu...");
			break;
		case 1:	//show Item
			db.showItems(); 
			System.out.println("");
			break;
		case 2:	//show Persons
			db.selectAndPrintPersonData(); //何盒肯己
			break;
	 /* case 3: //show reciepts
	  * 	break;
	 */
		default:
			System.out.println("Invalid input. Please input one of the numbers of the instructions.");
			break;
		}
	}
	
	public void showResult(DB db)
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
				
				Person fstptr = db.personHead;
				//System.out.println(ptr.name);
				
				if (fstptr == null)
				{
					System.out.println("Nothing cannot be printed since there is no person in the list!");
					break;
				}
				else if (db.ItemDB.isEmpty() == true)
				{
					System.out.println("Nothing cannot be printed since no item inputted yet!");
					break;
				}
								
				while (fstptr != null)
				{
					//System.out.println(ptr.name);
					Person sndptr = db.personHead;
					
					double totalpurch = fstptr.getTotalPurchase();
					double totaldebts = fstptr.getTotalDebts();
					System.out.println(fstptr.name + " spent total " + totalpurch + " and owe (or/and self benefited) " + totaldebts + "." );
			
					while(sndptr != null)
					{
						if (fstptr.equals(sndptr) == false)
						{
							double debts = 0.0, credits = 0.0;
							if (fstptr.debtors.contains(sndptr) == true) //creditor = fstptr, debtor = sndptr
							{
								//System.out.print("1st if");
								credits = bk.payAmount(sndptr, fstptr);
							}
							
							if (sndptr.debtors.contains(fstptr) == true) // creditor = sndptr, debtor = fstptr
							{
								//System.out.print("2nd if");
								debts = bk.payAmount(fstptr, sndptr);
							}
							
							double net = credits - debts;
							
							if (credits < debts) //1stptr owe 2ndptr
							{
								System.out.println(fstptr.name + " have to pay " + -(net) + " to " + sndptr.name);
							}
							else if (credits > debts) // 2ndptr owe 1stptr
							{
								System.out.println(fstptr.name + " need to retrive " + net + " from " + sndptr.name);
							}
							else
							{
								System.out.println(fstptr.name + " and " + sndptr.name + " spent same amount money for each other.");
							}
							
						}
						sndptr = sndptr.next;
					}
					fstptr = fstptr.next;
				}
				break;
			case 2: //show one's person's result
				Person target = db.getPersonFromAll();	
				
				if (target == null)
				{
					System.out.println("Nothing cannot be printed since there is no person in the list!");
					break;
				}
				else if (db.ItemDB.isEmpty() == true)
				{
					System.out.println("Nothing cannot be printed since no item inputted yet!");
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
				System.out.print("First Person: ");
				Person targetA = db.getPersonFromAll();
				System.out.print("Second Person: ");
				Person targetB = db.getPersonFromAll();
				if (targetB == targetA)
				{
					do
					{
						System.out.println("Picked two people are same! Please choose two differenet people");
						System.out.print("First Person: ");
						targetA = db.getPersonFromAll();
						System.out.print("Second Person: ");
						targetB = db.getPersonFromAll();
					} while (targetB == targetA);
				}
				
				double debtAtoB = 0, debtBtoA = 0;
				
				debtAtoB = bk.payAmount(targetA, targetB);
				debtBtoA = bk.payAmount(targetB, targetA);
								
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
