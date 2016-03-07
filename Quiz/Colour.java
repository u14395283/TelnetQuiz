public class Colour
{
	public String colour(String col, String text)
	{
		if(col.toLowerCase() == "red")
		{
			return ("\033[31m " + text + "\033[0m");
		}

		if(col.toLowerCase() == "blue")
		{
			return ("\033[34m " + text + "\033[0m");
		}

		if(col.toLowerCase() == "green")
		{
			return ("\033[32m" + text + "\033[0m");
		}
		if(col.toLowerCase() == "yellow")
		{
			return ("\033[33m" + text + "\033[0m");
		}
		if(col.toLowerCase() == "clear")
		{
			return ("\033[2J");
		}
		return text;
	}
}