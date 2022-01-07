package solver_4;

public class Debt //채무증서 돈을 안쓰고 혜택을 받은사람이 가짐
{
	//int debtNum; // bondnum과 debtnum은 서로 같음
	//int debtSubNum; //채무증서가 여러게 인경우.
	
	Person creditor;
	Item item;
	Bond linkedBond;
	
	double rawCost; //실제 product/serice 값.
	double issuedCost; //실제 갚아야할 값
	int divided; //몇명 n빵 했는지
	
	public void setCreditor(Person person)
	{
		creditor = person;
	}
	
	public Person getCreditor()
	{
		return creditor;
	}
	
	public void setItem(Item item)
	{
		this.item = item;
	}
	
	public Item getItem()
	{
		return this.item;
	}
	
	public void setLinkedBond(Bond bond)
	{
		linkedBond = bond;
	}
	
	public Bond getLinkedBond()
	{
		return linkedBond;
	}
	
	public void setRawCost(double cost)
	{
		rawCost = cost;
	}
	
	public double getRawCost()
	{
		return rawCost;
	}
	
	public void setIssuedCostManual(double cost)
	{
		issuedCost = cost;
	}
	
	public void setIssuedCostAuto()
	{
		if (divided == 0)
		{
			return;
		}
		else
		{
			issuedCost = rawCost / divided;
		}
	}
	
	public double getIssuedCost()
	{
		return issuedCost;
	}
	
	public void setDivided(int num)
	{
		divided = num;
	}
	
	
}
