package prev;
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
	ArrayList<Person> beneficiary = new ArrayList<Person>();
}
