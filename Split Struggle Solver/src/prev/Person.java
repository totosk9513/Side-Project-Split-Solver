package prev;

public class Person //strcu for person.
{
	//감자 감자 왕감자 <- utf-8 incoding checker. must be "gamja gamja wang gamja" in korean
	String name;
	Debt debtHead;
	Bond bondHead;
	double totalPurchase; //total contribute = total purchases
	double totalDebts; //the amount of money he have to pay to all 	
	
	Person next;
	
	
	public Person(String name)
	{
		this.name = name;
	}
	/*
	
	//BST&heap will be bad due to the duplicated price for purchHead and debtHead
	//채권자 리스트 이게 있으면 누구한테 줘야할 돈이 바로 나오긴 하나 저장이 필요하다면 하기
	
	
	Purchase purchHead; //head of the person's purchase items. 
	
	*/
		
}
