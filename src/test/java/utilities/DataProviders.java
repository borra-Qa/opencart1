package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="logindata")
	public String[][] getData() throws IOException
	{
		String path="./testData//OpenCartDDT.xlsx";
		ExcelUtilities xlutil=new ExcelUtilities(path);
		
		int totalrows=xlutil.getRowCount("login");
		int totalcell=xlutil.getCellCount("login", 1);
		
		String logindata[][]=new String[totalrows][totalcell];
		
		for(int i=1;i<=totalrows;i++)
		{
			for(int j=0;j<totalcell;j++)
			{
				logindata[i-1][j]=xlutil.getCellData("login", i, j);
			}
		}
		
		return logindata;
		
	}
}

//Data Provider 2
//Data Provider 3
//Data Provider 4
