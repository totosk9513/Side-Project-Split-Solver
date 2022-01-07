package prev;
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
		//pplCount = sc.nextInt();
		
		//if it starts firstime, just guide to input station of ppl's info
		
		
		int uiNo = -1;
		//or not, start looping until they hit end button
		//감자 감자 왕감자 <- utf-8 incoding checker. must be "gamja gamja wang gamja" in korean
		
		FrontheadUI ui = new FrontheadUI();
		Person front = null;
		HashMap<String,Item> ItemDB = new HashMap<String,Item>(100,2.0f);
		
		while(uiNo != 0)
		{	
			System.out.println("Select one of the numbers below to process: \n"
					+ "-------------------------------------------------------------------\n"
					+ "0:End the program \n"
					+ "1:Start reciept wizard	2:Make/Add a person	3:Delete a person\n"
					+ "4:Change one's data	5:Show Data		6:Issue a purchase\n"
					+ "7:Adjust a purchase	8:Issue debts		9:Adjust debts\n"
					+ "10:Show results\n");
			uiNo = sc.nextInt();
			//boolean breaker = false;
			
			switch(uiNo)
			{
			case 0:	System.out.println("Exiting the program.");
					//breaker = true;
					break;
					
			case 1: 
				System.out.println("Receipt wizard is not devloped yet.");
				break;
			case 2:
				//add person
				Person newPerson = null;
				char x = 0;
				if (pplCount == 0) // 첫 초기화
				{
					System.out.println("You are inputting the first person!");
					newPerson = ui.makePerson();
									
				}
				
				else //첫 초기화가 아닌 경우
				{
					newPerson = ui.makePerson();
					System.out.println(ui.isThePersonThere(front, newPerson.name));
					if (ui.isThePersonThere(front, newPerson.name) == true)
					{
						System.out.println(newPerson.name + " is already in the list! Adjust the name if it is differnet person with same name.");
						newPerson = null; //free the memory to prevent memory leak
						break;
					}
				}
				System.out.println("Do you want to add " + newPerson.name + " in the list? y/n");
				
				boolean yn = ui.yesOrNo();
				if (yn == true)
				{
					if (ui.isThePersonThere(front, newPerson.name) == false)
					{
						front = ui.addPerson(front, newPerson);
						System.out.println(front.name);
						pplCount++;
						System.out.println("total " + pplCount + " person(s) is(are) in the list");
					}					
					
				}
				else
				{
					System.out.println(newPerson.name + " is not added into the list");
				}
			
				
				break;
			case 3:
				if (pplCount == 0)
				{
					System.out.println("The list is empty");
					break;
				}
				ui.printAllPeopleName(front);
				System.out.println("Input name of the person you want to delete from the list");
				String deleteTarget = sc.next();
				
				if (ui.isThePersonThere(front, deleteTarget) == true)
				{
					front = ui.deletePerson(front, deleteTarget);
					pplCount--;
					System.out.println(deleteTarget + " is deleted from the list!");
				}
				else
				{
					System.out.println("The person is not in the list! Check your typo.");
				}
				break;
			case 4:	//4:Change one's data
				if (pplCount == 0)
				{
					System.out.println("The list is empty");
					break;
				}
				ui.printAllPeopleName(front);
				System.out.println("Input name of the person you want to change his/her information from the list");
				String changeTarget = sc.next();
				
				if (ui.isThePersonThere(front, changeTarget) == true)
				{
					Person targetPerson = ui.searchPerson(front, changeTarget);
					System.out.println("You picked " + targetPerson.name + ".");
					ui.changePersonDataSelector(targetPerson);
					
				}
				else
				{
					System.out.println("The person is not in the list! Check your typo.");
				}
				break;
				
			case 5:	//5:Show Data	
				if (pplCount == 0)
				{
					System.out.println("The list is empty");
					break;
				}
				System.out.println("Printing out all members in the list! : ");
				ui.printAllPeopleName(front);
				
				System.out.println("Input name of the person you want to his/her data from the list");
				String peakTarget = sc.next();
				
				if (ui.isThePersonThere(front, peakTarget) == true)
				{
					Person targetPerson = ui.searchPerson(front, peakTarget);
					ui.printPersonDataSelect(targetPerson);
				}
				else
				{
					System.out.println("The person is not in the list! Check your typo.");
				}
				break;
			case 6:	//6:Issue a purchase
				System.out.println("Input name of the item/service: ");
				String itemName = "";
				do
				{
					itemName = sc.next();
					
					if (ui.isTheItemInDB(itemName, ItemDB) == true)	
					{
						System.out.println("The item " + itemName + " is already in the list. Change its name.");
					}
					
				} while (ui.isTheItemInDB(itemName, ItemDB) == true);
				
											
				Item item = ui.makeItem(itemName, front); //Item object is saved in a hashmap called ItemDB
				ItemDB.put(itemName, item);
				
				Bond bond = ui.createBond(item);
				ui.issueDebts(front, bond);
				break;
			case 7:	//7:Adjust a purchase
				break;
			case 8:	//8:Issue debts
				break;
			case 9:	//9:Adjust debts
				break;
			case 10: //10:Show results
					break;
			default: //invalid integer input
				System.out.println("Invalid input. Please input one of the numbers of the instructions.");
				break;
					
			}
					
		}
		
		sc.close();
	}
	
}
