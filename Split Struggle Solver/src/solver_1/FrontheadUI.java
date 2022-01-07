package solver_1;
import java.util.*;

public class FrontheadUI 
{
	Scanner sc = new Scanner(System.in);
	Backends bknd = new Backends();
	//DB db = new DB();
	
	public boolean yesOrNo()
	{
		char x = sc.next().charAt(0);
		boolean result = false;
		
		if (( (x == 'Y') || (x == 'y') || (x == 'N') || (x == 'n') ) == false)
		{
			while( ( (x == 'Y') || (x == 'y') || (x == 'N') || (x == 'n') ) == false )
			{
				System.out.print("Please only input Y/y/N/n!");
				x = sc.next().charAt(0);
			}
		}
		else if ( (x == 'Y') || (x == 'y'))
		{
			result = true;
		}
		else
		{
			result = false;
		}
		
		return result;
	}
	
	public void showFunction(DB db)
	{
		System.out.println("Select one of the numbers below to show data: \n"
				+ "0:Do nothing; Exit \n"
				+ "1:Show all results \n"
				+ "2:Show one person's result \n"
				+ "3:Show a result between 2 persons \n");
		int selector = -1;
		selector = sc.nextInt();
		
		switch(selector)
		{
			case 0: //exit
				System.out.println("Choose do nothing, exit");
				break;
			case 1: //show all
				break;
			case 2: //show one's person's result
				db.printAllPeopleName();
				
				break;
			case 3: //show a mutual result.
				break;
			default:
				System.out.println("Invalid input. Please input one of the numbers of the instructions.");
				break;
				
		}
		
		
	}
	
	
}
