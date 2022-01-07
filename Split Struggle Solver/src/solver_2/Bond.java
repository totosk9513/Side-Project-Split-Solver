package solver_2;
import java.util.*;

public class Bond //채권증서, 돈을 쓴 사람이 가짐.
{
	//int bondNum; //한 아이템의 2개이상 채권이 있어도 그냥 subnum 만들지 않음.
	
	Item item;
	Person buyer;		
	
	double totalCost; //품목 자체 금액
	double unitCost;  //인당 금액
	int divided; //총 몇명 n빵?
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
