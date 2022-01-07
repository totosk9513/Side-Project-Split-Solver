package solver_2;
import java.util.*;

public class Bond //ä������, ���� �� ����� ����.
{
	//int bondNum; //�� �������� 2���̻� ä���� �־ �׳� subnum ������ ����.
	
	Item item;
	Person buyer;		
	
	double totalCost; //ǰ�� ��ü �ݾ�
	double unitCost;  //�δ� �ݾ�
	int divided; //�� ��� n��?
	ArrayList<Person> debtors = new ArrayList<Person>(); //who are charged?
	
	
	public void setItem(Item item)
	{
		this.item = item;
	}
	
	public Item getItem()
	{
		return this.item;
	}
	
	public void setBuyer(Person name)
	{
		buyer = name;
	}
	
	public Person getBuyer()
	{
		return this.buyer;
	}
	
	public void setTotalCost(double cost)
	{
		totalCost = cost;
	}
	
	public double getTotalCost()
	{
		return totalCost;
	}
	
	public void setUnitCostAuto()
	{
		unitCost = totalCost / (double) debtors.size();
	}
	
	public double getUnitCost()
	{
		return unitCost;
	}
	
}
