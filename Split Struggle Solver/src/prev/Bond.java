package prev;
import java.util.*;

public class Bond //ä������, ���� �� ����� ����.
{
	int bondNum; //�� �������� 2���̻� ä���� �־ �׳� subnum ������ ����.
	
	Item item;
	String Purchaser;		
	double totalCost; //ǰ�� ��ü �ݾ�
	double unitCost;  //�δ� �ݾ�
	ArrayList<Person> debtors = new ArrayList<Person>(); //who are charged?
	
	Bond next;
	
	
}
