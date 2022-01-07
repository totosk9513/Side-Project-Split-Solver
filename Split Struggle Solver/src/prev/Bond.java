package prev;
import java.util.*;

public class Bond //채권증서, 돈을 쓴 사람이 가짐.
{
	int bondNum; //한 아이템의 2개이상 채권이 있어도 그냥 subnum 만들지 않음.
	
	Item item;
	String Purchaser;		
	double totalCost; //품목 자체 금액
	double unitCost;  //인당 금액
	ArrayList<Person> debtors = new ArrayList<Person>(); //who are charged?
	
	Bond next;
	
	
}
