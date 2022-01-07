package solver_4;

import java.util.*;

public class Solver 
{
	// Developed by ReeWill_N2O >3<
	// Minseok Park, Undergrad student of Rutgers Univ - New Brunswick 
	// This projects contains a lot of Korean notations.
	
	
	public static void main(String[] args)
	{
		System.out.println("Welcome to the Split Struggle Solver!");
		
		int pplCount = 0;
		
		Scanner sc = new Scanner(System.in);
		DB db = new DB();
		FrontheadUI frnthd = new FrontheadUI();
		Backends bknd = new Backends();

		int uiNo = -1;
		//or not, start looping until they hit end button
		//감자 감자 왕감자 <- utf-8 incoding checker. must be "gamja gamja wang gamja" in korean
		
		
		while(uiNo != 0)
		{	
			System.out.println("============================================================================================\n"
					+ "Select one of the numbers below to process: \n"
					+ "--------------------------------------------------------------------------------------------\n"
					+ "0:End the program \n"
					+ "1:Start reciept wizard		2:Make/Add a person		3:Delete a person\n"
					+ "4:Change one's data		5:Show Data			6:Issue a purchase of Item\n"
					+ "7:Adjust a purchase/Item	8:Adjust bonds			9:Adjust debts\n"
					+ "10:Show results\n"
					+ "============================================================================================");
			System.out.print("Enter: ");
			uiNo = sc.nextInt();
			sc.nextLine(); //Reason=> https://greendreamtrre.tistory.com/139
			
			//boolean breaker = false;
			
			switch(uiNo)
			{
			case 0:	//0:End the program
				System.out.println("Bye Bye!");
				break;
			case 1: //1:Start receipt wizard
				System.out.println("Receipt wizard is not implemented yet.");
				break;
			case 2: //2:Make/Add a person
				System.out.println("Adding a new person");
				db.addPerson();
				break;
			case 3: //3:Delete a person
				db.deletePerson();
				break;
			case 4:	//4:Change one's data	
				System.out.println("Change data of a person from the list");
				break;
			case 5:	//5:Show Data	
				frnthd.showData(db);	//부분 완성			
				break;
			case 6:	//6:Issue a purchase of Item
				if (db.personCnt <= 0)
				{
					System.out.println("There is no people in the list! Input people first before issue a purchase of item!");
					break;
				}
				Item item = db.makeItem();
				if (db.isTheItemInDB(item.name) == true)
				{
					System.out.println("The item is already created and be in the list!");
				}
				else
				{
					System.out.print("Do you want to add this item into the data set and issue debts and bonds? \n"
							+ "input: Y/y or N/n only: ");
					if (frnthd.yesOrNo() == true)
					{
					
						
						//Person creditor = db.getPerson(bondTmp.buyer.name);
						//creditor.addBond(bondTmp);
						//addbond같은걸 db내 에서 해야할듯.
						db.addItemToDB(item);	
						//db.issueDebts(item);
						
						/*
						Bond bondTmp = db.createBond(item);
						System.out.println("Debugger: ");
						System.out.println("Item name: " + item.name);
						System.out.println("Item cost: " + item.price);
						System.out.println("Buyer name from bond: " + bondTmp.buyer.name);
						System.out.println("Creditor name from item " + item.payer.name);
						System.out.println("Creditor name from bond: " + bondTmp.buyer.name);
						//System.out.println("Bond")
						
						for (int i = 0 ; i < bondTmp.debtors.size(); i++)
						{
							Person tempDebtor = bondTmp.debtors.get(i);
							Debt debt = tempDebtor.debtList.get(0);
							
							System.out.println("Debt of " + tempDebtor.name + " is: ");
							System.out.println("Creditor: " + debt.creditor.name);
							System.out.println("Item: " + debt.item.name);
							System.out.println("Raw Cost: " + debt.rawCost);
							System.out.println("Issued Cost: " + debt.issuedCost);
						}*/
						
					}
					
				}
				break;
			case 7:	//7:Adjust a purchase
				break;
			case 8:	//8:Adjust bonds	
				break;
			case 9:	//9:Adjust debts
				break;
			case 10: //10:Show results
				frnthd.showResult(db);
					break;
			default: //invalid integer input
				System.out.println("Invalid input. Please input one of the numbers of the instructions.");
				break;
					
			}
					
		}
		
		sc.close();
	}
	
}
