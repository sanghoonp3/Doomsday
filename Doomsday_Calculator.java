import java.util.Scanner;

public class Doomsday_Calculator 
{
	private static int[] MaxDaysPerMonth = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private static int[] LeapYearMaxDaysPerMonth = new int[] {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	private static int[] DoomsdayDay = new int[] {3, 28, 14, 4, 9, 6, 11, 8, 9, 10, 7, 12};
	private static int[] LeapYearDoomsdayDay = new int[] {4, 29, 14, 4, 9, 6, 11, 8, 9, 10, 7, 12};
	
	private static Boolean isLeapYear = false;
	
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		Boolean isValid = false;
		
		System.out.println("Please enter a date in MM/DD/YYYY: ");
		
		while(isValid == false)
		{
			String date = in.next();
			if(date.contains("/"))
			{
				String[] splitDate = dateSeparator(date);
				int month = Integer.parseInt(splitDate[0]);
				int day = Integer.parseInt(splitDate[1]);
				int year = Integer.parseInt(splitDate[2]);
				
				if(isValidDate(month, day, year) == true)
				{
					int dayCalc = dayCalculator(year);
					int dayDifference = 0;
					int dayOfTheWeek = 0;
					
					if(isLeapYear == false)
					{
						if(day >= DoomsdayDay[month - 1])
						{
							dayDifference = day - DoomsdayDay[month - 1];
							dayOfTheWeek = dayCalc + dayDifference;
						}
						else
						{
							dayDifference = DoomsdayDay[month - 1] - day;
							dayOfTheWeek = dayCalc - dayDifference;
						}
					}
					else
					{
						if(day >= LeapYearDoomsdayDay[month - 1])
						{
							dayDifference = day - LeapYearDoomsdayDay[month - 1];
							dayOfTheWeek = dayCalc + dayDifference;
						}
						else
						{
							dayDifference = LeapYearDoomsdayDay[month - 1] - day;
							dayOfTheWeek = dayCalc - dayDifference;
						}
					}
					
					while(dayOfTheWeek < 0)
					{
						dayOfTheWeek = dayOfTheWeek + 7;
					}
					
					if(dayOfTheWeek > 6)
					{
						dayOfTheWeek = dayOfTheWeek % 7;
					}
					
					System.out.println(date + " is a " + printDay(dayOfTheWeek));
					isValid = true;
				}
				else
				{
					System.out.println("Invalid Date. Please enter a new date in the form MM/DD/YYYY: ");
				}
			}
			else
			{
				System.out.println("Invalid Date format. Please enter a new date in the form MM/DD/YYYY: ");
			}
		}
		in.close();
	}
	
	public static String[] dateSeparator(String date)
	{
		String[] mdy = date.split("/");
		return mdy;
	}
	
	public static boolean isValidDate(int month, int day, int year)
	{
		if(year < 1582)
		{
			return false;
		}
		else
		{
			if(month > 12 || month < 1)
			{
				return false;
			}
			else
			{
				if(isLeapYear(year) == true)
				{
					if(day > LeapYearMaxDaysPerMonth[month - 1] || day < 0)
					{
						return false;
					}
					isLeapYear = true;
					return true;
				}
				else
				{
					if(day > MaxDaysPerMonth[month - 1] || day < 0)
					{
						return false;
					}
					return true;
				}
			}
		}
	}
	
	public static boolean isLeapYear(int year)
	{
		if(year % 400 == 0)
		{
			return true;
		}
		else if(year % 100 == 0)
		{
			return false;
		}
		else if(year % 4 == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static int CenturyCodeCalculator(int year)
	{
		String textYear = Integer.toString(year);
		int century = Integer.parseInt(textYear.substring(0, textYear.length() - 2));
				
		if(century % 4 == 3)
		{
			return 3;
		}
		else if(century % 4 == 0)
		{
			return 2;
		}
		else if(century % 4 == 1)
		{
			return 0;
		}
		else
		{
			return 5;
		}
	}
	
	public static int dayCalculator(int year)
	{
		int a = CenturyCodeCalculator(year);
		int lastTwoDigitYear = year % 100;
		int b = lastTwoDigitYear / 12;
		int c = lastTwoDigitYear % 12;
		int d = c / 4;
		int sum = a + b + c + d;
		while(sum > 7)
		{
			sum = sum - 7;
		}
		return sum;
	}
	
	public static String printDay(int day)
	{
		switch(day)
		{
			case 0: 
				return("Sunday");
				
			case 1: 
				return("Monday");
				
			case 2: 
				return("Tuesday");
				
			case 3: 
				return("Wednesday");
				
			case 4: 
				return("Thursday");
				
			case 5: 
				return("Friday");
				
			case 6: 
				return("Saturday");
		}
		return null;
	}
}
