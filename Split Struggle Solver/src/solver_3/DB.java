package solver_3;

import java.util.*;

public class DB //most of data bases are saved here. //methods that adjust data should be here.
{
	Scanner sc = new Scanner(System.in);
	Backends bknd = new Backends();
	FrontheadUI frnthd = new FrontheadUI();
	
	public Person personHead;
	public int personCnt;
	HashMap<String,Item> ItemDB = new HashMap<String,Item>(100,2.0f);
	//HashMap for debt?
	//HashMap for bond?
	
	/////////////////////////////// helper methods for Item ////////////////////////////////////////////////
	Item makeItem() //각 person의 totalpurchase/totaldebts 세팅이 안되여있음. 
						//-> 이거는 다른 메소드로 처리하고, 최종 issue할 때 그 다른 메소드로 적용
	{
		Item result = new Item();
		System.out.println("Input name of the item/service!");
		String name = sc.next();
		result.setItemName(name);
		
		System.out.println("How much is the item/service?: ");
		double rawCost = sc.nextDouble();
		result.setRawPrice(rawCost);
		
		/*System.out.println("What is its tax ratio?: \n"
				+ "(Input only 1.xxx format!, if no tax or tax-included, input 1)");
		result.taxRate = sc.nextDouble();
		
		System.out.println("If it is a good/service that occur tips, please input tip ratio")
		*/
	
		printAllPeopleName();
		System.out.println("Who paid for this item/service?");
		String payerName = sc.next();
		Person payer = getPerson(payerName);
		result.setItemPayer(payer);
		
		boolean payerBenefited = false;
		String temp;
		
		do
		{
			System.out.println("Who get benefits of the item/service?\n"
					+ "if no longer input the benefitiaries, type '!exit' ");
			temp = sc.next();
			if ((searchPerson(temp) == false))
			{
				if (temp.equals("!exit") == false)
				{
					System.out.println(temp + " is not in the list! re-input the name!");
				}
			}
			else
			{
				Person beneficiary = getPerson(temp);
				
				if (result.beneficiary.contains(beneficiary) == true)
				{
					System.out.println(beneficiary.name + " is already marked as a beneficiary of the item!");
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
	
	public void addItemToDB(Item item) //여기서 bond debts 생성하고 적용하는거 까지 다 해야할듯
	{
		issueBond(item);
		issueDebts(item);
		ItemDB.put(item.name, item);
		//setTotalPurchaseAndDebtsByItem(item);
	}
	
	
	//////////////////////////// helper methods for person  ////////////////////////////////////////////
	
	public Person makePerson()
	{
		System.out.println("Input the person's name");
		String name = sc.next();
		Person newPerson = null;
		if (searchPerson(name) == true)
		{
			do
			{
				System.out.println(name + "is already in the list. Please change the name!");
				System.out.println("Input the person's name");
				name = sc.next();
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
		System.out.println(newPerson.name + "is inserted in the list!");
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
	
	public Person getPerson(String target)//찾아서 person을 return하는 문으로 바꿔야하나
	{
		Person ptr = personHead;
		Person result = null;
		while (ptr != null)
		{
			//System.out.println("head = " + personHead.name + " ptr = " + ptr.name);
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
	
	public void deletePerson() // 부분완성 + 미완성
	{
		// 주의!! 한 사람을 지우러면 그 사람과 관련된 debt, bonds, item을 먼저 지우고 그 사람을 삭제해야함. 
		
		printAllPeopleName();
		System.out.println("Input a person you want to delete from the list");
		String name = sc.next();
		if (searchPerson(name) == false)
		{
			do
			{
				System.out.println(name + " is not in the list. Re-input one of the people's name in the list below");
				printAllPeopleName();
				
				name = sc.next();
			}while(searchPerson(name) == false);
		}
		
		Person ptr = personHead;
		Person prev = personHead;
		
		while(ptr!= null)
		{
			if (name.equals(ptr.name))
			{
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
		//한 사람을 지우고 난뒤 그 사람과 관련된 debt, bonds, item을 지워야함. 
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
	
	public void changePersonDataSelector(Person target) //미완성
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
			
			switch(selector)
			{
				case 0: // 0:Do nothing
					System.out.println("You selected do nothing. return to previous options.");
					break;
					
				case 1:	//Change name
					System.out.println("Input new name to change " + target.name + ".");
					String newName = sc.next();
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
		while (ptr != null)
		{
			System.out.print(ptr.name + "\t");
			ptr = ptr.next;
		}
		System.out.println();
	}
	
	public Person selectPersonFromAllPeople()
	{
		if (personCnt == 0)
		{
			System.out.println("The list is empty!");
			return null;
		}
		
		printAllPeopleName();
		System.out.println("Input name of the person in the list");
		String name = sc.next();
		if (searchPerson(name) == false)
		{
			do 
			{
				System.out.println(name + "is not in the list! Re-input name of a person");
			} while (searchPerson(name) == false);
		}
		Person target = getPerson(name);
		
		return target;
	}
	
	public void selectAndPrintPersonData() //부분완성 + 미완성
	{
		Person target = selectPersonFromAllPeople();
		if (target != null)
		{
			int indicator = -1;
			
			System.out.println("Select one of those to print information of \"" + target.name + "\": ");
			System.out.println("0:Exit\t"
					+ "1:Total contribute\t"
					+ "2:Total debts\t"
					+ "3:List one's bonds\t"
					+ "4:List one's debts\t");	
					// maybe 5 for list one's creditors?
			do
			{
				indicator = sc.nextInt();
				
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
					//아래에 있는 부분은 다른 method로 따로 만들기
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
