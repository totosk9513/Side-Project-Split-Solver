package solver_4;

public class Debt //ä������ ���� �Ⱦ��� ������ ��������� ����
{
	//int debtNum; // bondnum�� debtnum�� ���� ����
	//int debtSubNum; //ä�������� ������ �ΰ��.
	
	Person creditor;
	Item item;
	Bond linkedBond;
	
	double rawCost; //���� product/serice ��.
	double issuedCost; //���� ���ƾ��� ��
	int divided; //��� n�� �ߴ���
	
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
