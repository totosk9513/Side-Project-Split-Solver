package prev;

public class Person //strcu for person.
{
	//���� ���� �հ��� <- utf-8 incoding checker. must be "gamja gamja wang gamja" in korean
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
	//ä���� ����Ʈ �̰� ������ �������� ����� ���� �ٷ� ������ �ϳ� ������ �ʿ��ϴٸ� �ϱ�
	
	
	Purchase purchHead; //head of the person's purchase items. 
	
	*/
		
}
