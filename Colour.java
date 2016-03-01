public class Colour
{
	public String colour(String col, String text)
	{
		if(col.toLowerCase() == "red")
		{
			return ("\033[31m " + text );
			System.out.println("\033[31;1m Hello \033[0m, \033[32;1;2mworld!\033[0m");
    		System.out.println("\033[31mRed\033[32m,] Green\033[33m, Yellow\033[34m, Blue\033[0m");
		}

		if(col.toLowerCase() == "blue")
		{
			return ("\033[34m " + text);
		}

		if(col.toLowerCase() == "green")
		{
			return ("\033")
		}
	}
}