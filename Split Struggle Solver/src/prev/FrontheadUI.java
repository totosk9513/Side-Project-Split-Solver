package prev;
import java.util.*;

public class FrontheadUI 
{
	Scanner sc = new Scanner(System.in);
	Backends bknd = new Backends();
	
	boolean isTheItemInDB(String target, HashMap<String,Item> DB)
	{
		
		if (DB.containsKey(target) ==  true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	Item makeItem(String itemName, Person head)
	{
		Item result = new Item();
		result.name = itemName;
		
		System.out.println("How much is the item/service?: ");
		result.rawPrice = sc.nextDouble();
		
		/*System.out.println("What is its tax ratio?: \n"
				+ "(Input only 1.xxx format!, if no tax or tax-included, input 1)");
		result.taxRate = sc.nextDouble();
		
		System.out.println("If it is a good/service that occur tips, please input tip ratio")
		*/
		System.out.println("Who paid for this item/service?");
		String name = sc.next();
		Person payer = searchPerson(head, name);
		result.payer = payer;
		
		boolean payerBenefited = false;
		String temp = "";
		
		do
		{
			System.out.println("Who get benefits of the item/service?"
					+ "if no longer input the benefitiaries, type 'exit' ");
			temp = sc.next();
			if (temp.equals("exit") == true)
			{
				break;
			}
			Person benefitiary = searchPerson(head, temp);
			if (benefitiary.name.equals(payer.name) == true)
			{
				System.out.println("Payer also get benefitis of the item");
				payerBenefited = true;
			}
			
			result.beneficiary.add(benefitiary);
		}while(temp.equals("exit") == false);
		
		System.out.println("Item " + result.name+ " is created");
		return result;
	}
	
	Bond createBond(Item item)
	{
		Bond result = new Bond();
		//bond, debt 생성하고 나눠주기.
		
		result.item = item;
		result.Purchaser = item.payer.name;
		result.totalCost = item.price;
		result.unitCost = (item.price / item.beneficiary.size());
		
		for (int i = 0; i < item.beneficiary.size(); i++)
		{
			Person temp = item.beneficiary.get(i);
			result.debtors.add(temp); 
		}
		
		return result;
	}
	
	void addBond(Person person, Bond bond)
	{
		if (person.bondHead == null)
		{
			person.bondHead = bond;
		}
		else
		{
			bond.next = person.bondHead;
			person.bondHead = bond;
		}
	}
	
	Debt createDebt(Bond bond)
	{
		Debt result = new Debt();
		result.creditor = bond.Purchaser;
		result.rawCost = bond.totalCost;
		result.issuedCost = bond.unitCost;
		result.divided = bond.item.beneficiary.size();
		
		return result;
	}
	
	void addDebt(Person person, Debt debt)//뭔가 불안한데....
	{
		//Debt ptr = person.debtHead;
		if (person.debtHead == null)
		{
			person.debtHead = debt;
		}
		else
		{
			debt.next = person.debtHead;
			person.debtHead = debt;
		}
		
	}
	
	void issueDebts(Person head, Bond bond)
	{
		for (int i = 0; i < bond.debtors.size(); i++)
		{
			Person debtor = bond.debtors.get(i);
			Debt debt = createDebt(bond);
			addDebt(debtor, debt);
		}
	}
	
	boolean isThePersonThere(Person front, String target)
	{
		Person ptr = front;
		//boolean indicator = false;
		while (ptr != null)
		{
			System.out.println(ptr.name + " " + target);
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
	
	Person getThePerson(Person front, String target)
	{
		Person ptr = front;
		Person thePerson = null;
		while (ptr != null)
		{
			if(ptr.name == target)
			{
				thePerson = ptr;
			}
		}
		return thePerson;
	}
	
	boolean yesOrNo()
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
	
	Person searchPerson(Person front, String target)//찾아서 person을 return하는 문으로 바꿔야하나
	
	{
		Person ptr = front;
		Person result = null;
		while (ptr != null)
		{
			System.out.println("head = " + front.name + " ptr = " + ptr.name);
			if (target.equals(ptr.name) == true)
			{
				System.out.println(target + " is in the list!");
				result = ptr;
				return result;
			}
			ptr = ptr.next;
		}
		System.out.println(target + " is not in the list!");
		return null;
	}
	
	void printPersonDataAll(Person front, String target)
	{
		Person ptr = front;
		boolean exist = false;
		while (ptr != null)
		{
			if (ptr.name == target)
			{
				exist = true;
				break;
			}
		}
		
		if (exist == false)
		{
			System.out.println(target + "does not exist!");
		}
		else
		{
			//get name, get...... just everthing!
		}
	}
	
	void printPersonDataSelect(Person target)
	{
	
	int indicator = -1;
	
		System.out.println("Select one of those to print information of " + target.name + ":");
		System.out.println("0:Exit\t"
							+ "1:Total contribute\t"
							+ "2:Total debts\t"
							+ "3:List one's bonds\t"
							+ "4:List one's debts\t");	
							// maybe 5 for list one's creditors?
		
		do
		{
			indicator = sc.nextInt();
			
			if (indicator == 1)
			{
				System.out.println("Total contribute: " + target.totalPurchase);
			}
			else if (indicator == 2)
			{
				System.out.println("Total debts: " + target.totalDebts);
			}
			else if (indicator == 3)
			{
				//list out all of his/her bonds
				System.out.println(target.bondHead + ":\n "
						+ "Item name: " + target.bondHead.item.name);
				
				/*System.out.println("Choose one of the items for detail.\n"
								+ "if do not need, input 0");
								*/
			}
			else if (indicator == 4)
			{
				//list out all of his/her debts
				
				/*System.out.println("Choose one of the debts for detail.\n"
								+ "if do not need, input 0");
								*/
			}
			else
			{
				System.out.println("Please choose integer between 0 to 4!");
			}
			
		}while (indicator != 0);
	}
	
	void changePersonDataSelector(Person target)
	{
		System.out.println("what information do you want to change? \n"
				+ "0:Do nothing \n"
				+ "1:Change name	2:Adjust one's debt		3:Adjust one's bond\n"
				+ "4:Adjust total Purchase	5: Adjust total Debts\n"
				+ "Warning: Changing one's total Purchase/debts may cause inconsistent data!");
		int selector = sc.nextInt();
		
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
		
		//return target;		
	}
	
	void printAllPeopleName(Person front)
	{
		Person ptr = front;
		while (ptr != null)
		{
			System.out.print(ptr.name + "\t");
			ptr = ptr.next;
		}
		System.out.println();
	}
	
	
	//데이터가 추가되거나 삭제되거나 바뀔때마다 모든 사람들의 debt/credit/purchases 등등 데이터도 변경 되어야함!
		//이거는 dynamically 데이터를 바꾸지 말고, 계산 요청이 들어올때 만 다시 계산해서 출력해주기.
	
	Person makePerson()
	{
		System.out.println("Input the person's name");
		String name = sc.next();
		Person newPerson = new Person(name);
		
		/*
		System.out.println("Do you want to input other details now? Y/N");
		char x = sc.next().charAt(0);
		
		if (( (x == 'Y') || (x == 'y') || (x == 'N') || (x == 'n') ) == false)
		{
			while( ( (x == 'Y') || (x == 'y') || (x == 'N') || (x == 'n') ) == false )
			{
				System.out.print("Please only input Y/y/N/n!");
				x = sc.next().charAt(0);
			}
		}
		if (x == 'Y' || x == 'y')
		{
			//do detail data inputting method
		}
		else if (x == 'N' || x == 'n')
		{
			System.out.println("you may input " + newPerson.name + "'s detail later.");
		}
			*/
		return newPerson;
	}
	
	Person addPerson(Person front, Person newPerson) //조금 바꿔야 할듯. make person으로 통일? 아니면 둘다 쓰고 둘다 허용?
	{
		boolean valid = isThePersonThere(front, newPerson.name);
		if (valid == true)
		{
			System.out.println(newPerson.name + "is already in the list! "
						+ "\n if there are different person in the same name, make one of them a bit different"
						+ "or put different numbers to distinguish.");
			return null;
		}
		else
		{	
			if (front == null) //first element insert.
			{
				front = newPerson;
				
			}
			else //after that...
			{
				newPerson.next = front;
				front = newPerson;
			}
			System.out.println(newPerson.name + "is inserted in the list.");
			return front;
		}
	}

	
	Person deletePerson(Person front, String target)
	{
		
		Person ptr = front;
		Person prev = front;
		while (ptr != null)
		{
			System.out.println("head = " + front.name + " ptr = " + ptr.name + " prev = " + prev.name);
			if (target.equals(ptr.name) == true)
			{
				if (ptr == front) // 헤드노드 일 경우
				{
					front = front.next;
				}
				else
				{
					prev.next = ptr.next;
				}
				return front;
			}
			prev = ptr;
			ptr = ptr.next;
		}
		
		return front; //For C style programming
	}
	
	
}
