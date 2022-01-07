import java.util.*;

public class Debt //채무증서 돈을 안쓰고 혜택을 받은사람이 가짐
{
	//int debtNum; // bondnum과 debtnum은 서로 같음
	//int debtSubNum; //채무증서가 여러게 인경우.
	
	String creditor;
	double rawCost; //실제 product/serice 값.
	double issuedCost; //실제 갚아야할 값
	int divided; //몇명 n빵 했는지
	Debt next;
}
