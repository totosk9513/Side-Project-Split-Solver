package solver_2;
import java.util.ArrayList;

public class Item //products, items, servies, goods....
{
	
	String name;
	double rawPrice;
	//double taxRate;
	//double tipRate;
	//double tipCost;
	
	//double price = (rawPrice * taxRate) + tipCost;
	double price = rawPrice;
	
	Person payer;
	Bond linkedBond;
	ArrayList<Person> beneficiary = new ArrayList<Person>();
	
	public void setItemName(String name)
	{
		this.name = name;
	}
	
	public String getItemName()
	{
		return this.name;
	}
	
	public void setItemPayer(Person payer)
	{
		this.payer = payer;
	}
	
	public Person getItemPayer()
	{
		return this.payer;
	}
	
	public void setLinkedBond(Bond bond)
	{
		this.linkedBond = bond;
	}
	
	public Bond getLinkedBond()
	{
		return linkedBond;
	}
	
	public void setRawPrice(double price)
	{
		rawPrice = price;
		this.price = rawPrice;
	}
	
	public double getRawPrice()
	{
		return this.rawPrice;
	}
	
	public double getPrice() 
	{
		return this.price;
	}
	
	
	
}
