package solver_5;

import java.util.*;

public class DB //most of data bases are saved here. //methods that adjust data should be here.
{
	Scanner sc = new Scanner(System.in);
	Back bk = new Back();
	FrontalUI frnt = new FrontalUI();
	
	public Person personHead;
	public int personCnt;
	HashMap<String,Item> ItemDB = new HashMap<String,Item>(50,2.0f);
	ArrayList<String> ItemKeyDB = new ArrayList<String>();
	//HashMap for debt?
	//HashMap for bond?
	
	/////////////////////////////// helper methods for Item ////////////////////////////////////////////////
	
	public Item makeItem()
	{
		Item result = new Item();
		System.out.print("Input name of the item/service!: ");
		String name = sc.nextLine();
		result.setItemName(name);
		
		System.out.print("How much is the item/service?: ");
		double rawCost = sc.nextDouble();
		sc.nextLine();
		result.setRawPrice(rawCost);
		
		/*System.out.println("What is its tax ratio?: \n"
				+ "(Input only 1.xxx format!, if no tax or tax-included, input 1)");
		result.taxRate = sc.nextDouble();
		sc.nextLine();
		
		System.out.println("If it is a good/service that occur tips, please input tip ratio")
		*/
	
		printAllPeopleName();
		System.out.print("Who paid for this item/service?: ");
		String payerName = sc.nextLine();
		System.out.println(payerName);
		Person payer = getPerson(payerName);
		
		result.setItemPayer(payer);
		
		boolean payerBenefited = false;
		String temp;
		
		do
		{
			System.out.print("Who get benefits of the item/service?\n"
					+ "(if no longer input the benefitiaries, type '!exit'):  ");
			temp = sc.nextLine();
			if ((searchPerson(temp) == false))
			{
				if (temp.equals("!exit") == false)
				{
					System.out.println("\"" +temp + "\" is not in the list! re-input the name!");
				}
			}
			else
			{
				Person beneficiary = getPerson(temp);
				
				if (result.beneficiary.contains(beneficiary) == true)
				{
					System.out.println("\"" + beneficiary.name + "\" is already marked as a beneficiary of the item!");
				}
				else
				{
					if (beneficiary.name.equals(payer.name) == true)
					{
						System.out.println("Payer also get benefitis of the item");
						payerBenefited = true;
					}
					result.beneficiary.add(beneficiary);
				}
			}
	
		} while(temp.equals("!exit") == false);
		
		System.out.println("Item " + result.name+ " is created");
		return result;
	}
	
	public void showItems()
	{
		if (ItemDB.isEmpty() == true)
		{
			System.out.println("There is no any issued item/services in the list!");
			return;
		}
		System.out.println("Show all Items below: ");
		printAllItems();
		System.out.println("");
		
		System.out.print("Input one of the items in the list for its detail: ");
		String input = sc.nextLine();
		
		if (ItemDB.containsKey(input) == false)
		{
			do
			{
				System.out.println("\"" + input + "\" is not in the item list!. Re entered a name of the item in the list!");
				input = sc.nextLine();
			} while (ItemDB.containsKey(input) == false);
		}
		Item item = ItemDB.get(input);
		
		System.out.println("Item " + item.name + "'s info: \n"
						+ "Payer: " + item.getItemPayer().name + "\n"
						+ "RawPrice: " + item.getRawPrice() + "\n"
						+ "Price: " + item.getPrice() + "\n");
		System.out.println("Beneficiaries of the item: ");
		for (int i = 0; i < item.beneficiary.size(); i++)
		{
			Person beneficiary = item.beneficiary.get(i);
			System.out.print(beneficiary.getName() + "\t");
			if (  (i != 0) && ((i % 5) == 0) && (i < item.beneficiary.size()) )
			{
				System.out.println("");
			}
		}
		
		
	
		
	}
	
	
	////////////////////////////// helper methods for Item Hashmap ///////////////////////////////////////////
	public boolean isTheItemInDB(String target)
	{
		
		if (ItemDB.containsKey(target) ==  true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void printAllItems()
	{
		for (int i = 0; i < ItemKeyDB.size(); i++)
		{
			String currentKey = ItemKeyDB.get(i);
			Item currentValue = ItemDB.get(currentKey);
			System.out.print(currentValue.name + "\t");
			if ( (i != 0) && ( (i % 5) == 0 ) && ( i < ItemKeyDB.size()) )
			{
				System.out.println("");
			}
		}
	}
	
	public void addItemToDB(Item item) //?????? bond debts ???????? ?????????? ???? ?? ????????
	{
		issueBond(item);
		issueDebts(item);
		ItemDB.put(item.name, item);
		ItemKeyDB.add(item.name);
		//setTotalPurchaseAndDebtsByItem(item);
	}
	
	
	//////////////////////////// helper methods for person  ////////////////////////////////////////////
	
	public Person makePerson()
	{
		System.out.print("Input the person's name: ");
		String name = sc.nextLine();
		Person newPerson = null;
		if (searchPerson(name) == true)
		{
			do
			{
				System.out.println("\"" + name + "\" is already in the list. Please change the name!");
				System.out.print("Input the person's name: ");
				name = sc.nextLine();
			} while(searchPerson(name) == true);
			newPerson = new Person(name);
		}
		else
		{
			newPerson = new Person(name);
		}
		
		return newPerson;
	}
	
	public void addPerson() 
	{
		Person newPerson = makePerson();
		
		if (personHead == null)
		{
			personHead = newPerson;
		}
		else
		{
			newPerson.next = personHead;
			personHead = newPerson;
		}
		System.out.println(newPerson.name + " is inserted in the list!");
		personCnt++;
	}
	
	public boolean searchPerson(String target)
	{
		Person ptr = personHead;
		while (ptr != null)
		{
			//System.out.println(ptr.name + " " + target);
			if (target.equals(ptr.name) == true)
			{
				//System.out.println(ptr.name + " " + target + " are same");
				//indicator = true;
				return true;
			}
			ptr = ptr.next;
		}
		return false;
	}
	
	public Person getPerson(String target)//?????? person?? return???? ?????? ??????????
	{
		Person ptr = personHead;
		Person result = null;
		while (ptr != null)
		{
			//System.out.println("head = \"" + personHead.name + "\" ptr = \"" + ptr.name + "\"");
			if (target.equals(ptr.name) == true)
			{
				//System.out.println(target + " is in the list!");
				result = ptr;
				return result;
			}
			ptr = ptr.next;
		}
		//System.out.println(target + " is not in the list!");
		return null;
	}
	
	public void deleteTargetPerson(Person target) // this method delete all data with deleted person.
	{
		//deleting the person from the person list.
		Person ptr = personHead;
		Person prev = personHead;		
		
		while(ptr!= null)
		{
			if (target == ptr)
			{
				System.out.println("\"" + ptr.name + "\" is successfully deleted from the list");
				if (ptr == personHead)
				{
					personHead = personHead.next;
				}
				else
				{
					prev.next = ptr.next;
				}
			}
			prev = ptr;
			ptr = ptr.next;
		}
		personCnt--;
		
	}
	
	public void deletePerson() // this method delete only person. ???????? + ?????? // ?????? ??????????.
	{
		// ????!! ?? ?????? ???????? ?? ?????? ?????? debt, bonds, item?? ???? ?????? ?? ?????? ??????????. 
		//?????? ???????? ?????? ???? ????!
		
		/*?????? protocol. Protocols when deleting a person
		 * ?????? ?????? ????????:
		 * 1. ???????? ?????? bond???? ?????? ???? 
		 * 2. ???????? ?????? bond???? item ?????? ????
		 * 3. ???????? ?????? bond???? ?????? ?? bond?? ?????? ????/???????????? debt???? ?????? ???? 
		 * 4. ???????? ?????? bond???? ?????? ?? ?? bond?? debtors?? clear -> bond.clearDebtors
		 * 
		 * 
		 * 5-1. ???????? ?????? debts?? ????????, ?? debt ?????? creditor?? debt ?????? ??????.
		 * debt?? ?????????? ???? ???? ?????? debt???? issuedcost?? ?????? ??????, creditor?? ???? debt?? ?? ???????? ?????? debt?? ??????????
		 * 		ex) item dinner, 33 USD, creditor:A, debtor: A,B,C
		 * 			C?? dinner?? ?????? debt?? ?????? A?? debt(dinner) amount: 11 usd => 11 + 11(from C) = 22?? ????
		 * 5-2. ???????? ?????? debt?? ????????, item?? benficiary???? ?????? ????
		 * 
		 * 6. ???????? ?????? debtors, creditors, debtlist, bondlist clear -> clear ?????? ????.	
		*/
		
		//1. find the person 
		if (personCnt <= 0)
		{
			System.out.println("The list of people is empty! Nothing cannot be deleted!");
			return;
		}
		
		System.out.println("Deleting a person from the list. (All related data of the person will be deleted too.)");

		Person target = getPersonFromAll();
		//deleting related objects of the targeted person
		
		// // 1.deleting bond and item
		
		//System.out.println("target's first bond: " + target.bondList.get(0).item.name);
		
		if (target.bondList.isEmpty() == false) //????
		{
			//System.out.println("start clear bond list");
			clearBondList(target);			
		}
		
		if (target.debtList.isEmpty() == false)
		{
			clearDebtlist(target);
		}
		
		
		// delete the person
		deleteTargetPerson(target);
		
	}
	
	public void clearDebtlist(Person target)
	{
		for (int i = 0; i < target.debtList.size(); i++)
		{
			Debt tempDebt = target.debtList.get(i);
			
			if (tempDebt.creditor == target) // ???? debt???????? ???? ?????? ???? ???? 
			{
				target.debtList.remove(i);
				tempDebt = null; //to prevent memory leak
			}
			else // ???????????? ???????? ???? debt?? ????, ???? ?????? ?????? ??, ?????? debt?? ?????? creditor???? ?????? (policy)
			{
				double issuedCost = tempDebt.issuedCost;
				Person creditor = tempDebt.creditor;
				Debt creditorDebt = null;
				for (int k = 0; k < creditor.debtList.size(); k++)
				{
					if (tempDebt == creditor.debtList.get(k));
					creditorDebt = creditor.debtList.get(k);
				}
				creditorDebt.setIssuedCostManual(creditorDebt.getIssuedCost() + issuedCost);
				creditor.setTotalDebtsAuto();
				
				deleteDebtor(target, creditor);
				
				target.debtList.remove(i);
				tempDebt = null; //to prevent memory leak
			}
			
			
		}
	}
	
	public void clearBondList(Person target)
	{
		for (int i = 0; i < target.bondList.size(); i++)
		{
			Bond tempBond = target.bondList.get(i);
			Item tempItem = tempBond.getItem();
			
			for (int k = 0; k < tempBond.debtors.size(); k++)
			{
				Person debtor = tempBond.debtors.get(k);
				if (debtor != target)
				{
					//System.out.println("Debtor's name = " + debtor.name);
					deleteLinkedDebts(tempBond, debtor);
					deleteCreditor(debtor, target);
					debtor.setTotalDebtsAuto();
				}
				//find the debt which linked bond is the tempBond
			}
			
			ItemDB.remove(tempItem.name);
		}
		target.clearBondList();
	}
	
	public void deleteCreditor(Person debtor, Person creditor)
	{
		for (int i = 0; i < debtor.creditors.size(); i++)
		{
			if (debtor.creditors.get(i) == creditor)
			{
				debtor.creditors.remove(i);
			}
		}
	}
	
	public void deleteDebtor(Person debtor, Person creditor)
	{
		System.out.println(creditor.name + "'s debtors.size = " + creditor.debtors.size());
		for (int i = 0; i < creditor.debtors.size(); i++)
		{
			System.out.println(creditor.name + "'s debtors " + i + " = " + creditor.debtors.get(i).name);
			if (creditor.debtors.get(i) == debtor)
			{
				creditor.debtors.remove(i);
			}
		}
	}
	
	public void setTotalPurchaseAndDebtsByItem(Item item)
	{
		// set/adjust creditor's Totalpurchase
		Person creditor = item.payer;
		System.out.println("Creditor name: " + creditor.name);
		
		creditor.setTotalPurchaseAuto();
		System.out.println(creditor.name + "'s totalpurch: " + creditor.totalPurchase);
		
		// set/adjust all debtors Totaldebts
		for (int i = 0; i < item.beneficiary.size(); i++)
		{
			Person debtorTemp = item.beneficiary.get(i);
			System.out.println("debtor name: " + debtorTemp.name);
			debtorTemp.setTotalDebtsAuto();
			System.out.println(debtorTemp.name + "'s totaldebt: " + debtorTemp.totalDebts);
		}
	}
	
	public void changePersonDataSelector(Person target) //??????
	{
		int selector = -1;
		while (selector != 0)
		{
			System.out.println("what information do you want to change? \n"
					+ "0:Do nothing; Exit \n"
					+ "1:Change name	2:Adjust one's debt		3:Adjust one's bond\n"
					+ "4:Adjust total Purchase	5: Adjust total Debts\n"
					+ "Warning: Changing one's total Purchase/debts may cause inconsistent data!");
			selector = sc.nextInt();
			sc.nextLine();
			
			switch(selector)
			{
				case 0: // 0:Do nothing
					System.out.println("You selected do nothing. return to previous options.");
					break;
					
				case 1:	//Change name
					System.out.print("Input new name to change " + target.name + ": ");
					String newName = sc.nextLine();
					target.name = newName;
					break;
					
				case 2:	//Adjust one's debt
					break;
					
				case 3:	//Adjust one's bond
					break;
					
				case 4:	// Adjust total Purchase
					break;
					
				case 5:	// Adjust total Debts
					break;
					
				default:
					System.out.println("Invalid input. Please input one of the numbers of the instructions.");
					break;	
			}		
		}
	}
	
	public void printAllPeopleName()
	{
		Person ptr = personHead;
		System.out.println("All people in the list below: ");
		System.out.print("[");
		while (ptr != null)
		{
			System.out.print(ptr.name);
			if(ptr.next != null)
			{
				System.out.print("\t");
			}
			ptr = ptr.next;
		}
		System.out.println("]");
	}
	
	public Person getPersonFromAll()
	{
		if (personCnt == 0)
		{
			System.out.println("The list is empty!");
			return null;
		}
		
		printAllPeopleName();
		System.out.print("Input name of the person in the list: ");
		String name = sc.nextLine();
		if (searchPerson(name) == false)
		{
			do 
			{
				System.out.print(name + "is not in the list! Re-input name of a person: ");
				name = sc.nextLine();
			} while (searchPerson(name) == false);
		}
		Person target = getPerson(name);
		
		return target;
	}
		
	
	public void selectAndPrintPersonData() //???????? + ??????
	{
		Person target = getPersonFromAll();
		if (target != null)
		{
			int indicator = -1;
					// maybe 5 for list one's creditors?
			do
			{	
				System.out.println("Select one of those to print information of \"" + target.name + "\": ");
				System.out.println("0:Exit\t"
								+ "1:Total contribute\t"
								+ "2:Total debts\t"
								+ "3:Net contribute & debt\t"
								+ "4:List one's debts\t"
								+ "5:List one's contributes");	
				indicator = sc.nextInt();
				sc.nextLine();
				
				switch(indicator)
				{
				case 0: //exit
					System.out.println("Exit the menu...");
					break;
				case 1: //Total contribute
					System.out.println("Total contribute: " + target.totalPurchase);
					break;
				case 2://Total debts
					System.out.println("Total debts: " + target.totalDebts);
					break;
				case 3: //Net contribute & debt
					//?????? ???? ?????? ???? method?? ???? ??????
					double net = target.totalPurchase - target.totalDebts;
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
						System.out.println(target.name + "has zero net results");
					}
					
					break;
				case 4:
					break;
				case 5:
					break;
				default:
					System.out.println("Invalid input. Please input one of the numbers of the instructions.");
					break;
				}
			} while (indicator != 0);
			
		}

	}
	
	
	
	///////////////////////////////////  helper methods for debt  //////////////////////////////////////////
	
	public Debt createDebt(Item item)
	{
		Debt result = new Debt();
		result.setCreditor(item.payer);
		result.setRawCost(item.price);
		result.setItem(item);
		
		Bond linkedBond = item.linkedBond;
		result.setLinkedBond(linkedBond);
		result.setDivided(linkedBond.debtors.size());
		result.setIssuedCostAuto();

		return result;
	}
	
	public void deleteLinkedDebts(Bond target, Person debtor)
	{
		for (int i = 0; i < debtor.debtList.size(); i++)
		{
			Debt temp = debtor.debtList.get(i);
			//System.out.println("Linkedbond item name = " + temp.linkedBond.item.name + " bond item name: " + target.item.name);
			if(temp.linkedBond == (target))
			{
				//System.out.println("Found linked Bond of " + debtor.name);
				debtor.debtList.remove(i);
				/*
				if (debtor.debtList.isEmpty()) //debugger
				{
					System.out.println("deleted");
				}*/
				temp = null; //to prevent memory leak;
			}
		}
	}
	
	public void issueDebts(Item item) //item must be initialized and must have linked bond before issue debts!
	{
		if (item.linkedBond != null)
		{
			Bond bond = item.getLinkedBond();
			int debtorSize = bond.debtors.size();
			for (int i = 0; i < debtorSize; i++)
			{
				Person debtor = bond.debtors.get(i);
				Debt temp = createDebt(item);
				debtor.debtList.add(temp);
				if (debtor.creditors.contains(temp.creditor) == false)
				{
					debtor.addCreditor(temp.creditor);
				}
				debtor.setTotalDebtsAuto();
			}
		}
		else
		{
			System.out.println("Debugger: " + item.name + " does not have a linked bond yet! Linked the bond before start issusing debts");
		}
		
	}
	
	/*Debt createDebtByBond(Bond bond)
	{
		Debt result = new Debt();
		
		result.creditor = bond.buyer;
		result.rawCost = bond.totalCost;
		result.issuedCost = bond.unitCost;
		result.divided = bond.item.beneficiary.size();
		
		return result;
	}*/
	
	
	/*void addDebt(Person person, Debt debt)
	{
		person.debtList.add(debt);
	}*/
	
	
	
	///////////////////////////////////  helper methods for bond   ////////////////////////////////////////////
	public Bond createBond(Item item)
	{
		Bond result = new Bond();
		
		result.setItem(item);
		result.setBuyer(item.payer);
		result.setTotalCost(item.price);
		
		for (int i = 0; i < item.beneficiary.size(); i++)
		{
			Person temp = item.beneficiary.get(i);
			result.debtors.add(temp);
		}
		
		item.linkedBond = result;
		
		return result;
	}
	
	public void issueBond(Item item)
	{
		Bond bond = createBond(item);
		Person creditor = getPerson(bond.buyer.name);
		creditor.addBond(bond);
		for(int i = 0; i < bond.debtors.size(); i++)
		{
			Person debtor = bond.debtors.get(i);
			if (creditor.debtors.contains(debtor) == false)
			{
				creditor.addDebtor(debtor);
			}
		}
		creditor.setTotalPurchaseAuto();
	}
	
	public Bond findBondfromPerson(Person person, Item item)
	{
		Bond result = null;
		for (int i = 0; i < person.bondList.size(); i++)
		{
			if (person.bondList.get(i).item == item)
			{
				return result = person.bondList.get(i);
			}
			
		}
		return result;
	}
	
	public void addBond() 
	{
		
	}
	
	
	
}