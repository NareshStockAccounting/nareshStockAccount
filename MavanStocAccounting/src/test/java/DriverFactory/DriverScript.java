package DriverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import CommonFunLibrary.FunctionLibrary;
import Utilities.ExcelFileUtils;

public class DriverScript 
{
WebDriver driver;


	public  void startTest() throws Throwable
	{

		ExcelFileUtils excel=new ExcelFileUtils();
		
		for (int i = 1; i <=excel.rowCount("MasterTestCases"); i++) 
		 {
			String ModuleStatus="";
			if(excel.getData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
			{
				String TCModule=excel.getData("MasterTestCases", i, 1);
				System.out.println("category :;"+TCModule);
				int rowcount=excel.rowCount(TCModule);
				for (int j = 1; j <=rowcount; j++) 
				{
					
					String Description=excel.getData(TCModule, j, 0);
					String Object_Type=excel.getData(TCModule, j, 1);
					
					String Locator_Type=excel.getData(TCModule, j, 2);
					String Locator_Value=excel.getData(TCModule, j, 3);
					String Test_Data=excel.getData(TCModule, j, 4);
					System.out.println("Object_Type :;"+Object_Type);
					try
					{
						
					if(Object_Type.equalsIgnoreCase("startBrowser"))
					{
						System.out.println("Vasu");
						
						driver=FunctionLibrary.startBrowser(driver);
					}
					if(Object_Type.equalsIgnoreCase("openApplication"))
					{
						System.out.println("Vasu1234567");
						FunctionLibrary.openApplication(driver);
						System.out.println("Vasu123");
					}
					if(Object_Type.equalsIgnoreCase("typeAction"))
					{
						FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
					}
					if(Object_Type.equalsIgnoreCase("clickAction"))
					{
						FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value);
					}
					if(Object_Type.equalsIgnoreCase("waitForElement"))
					{
						FunctionLibrary.wiatForElement(driver, Locator_Type, Locator_Value, Test_Data);
					}
					if(Object_Type.equalsIgnoreCase("closeBrowser"))
					{
						FunctionLibrary.closeBrowser(driver);
					}
					if(Object_Type.equalsIgnoreCase("captureData"))
					{
						FunctionLibrary.captureData(driver, Locator_Type, Locator_Value);
					}
					if(Object_Type.equalsIgnoreCase("pageDown"))
					{
						FunctionLibrary.pageDown(driver);
					}
					if(Object_Type.equalsIgnoreCase("tableValidation"))
					{
						FunctionLibrary.tableValidation(driver, Test_Data);
					}
					if(Object_Type.equalsIgnoreCase("stockCategories")){
						System.out.println("mouse Click");
						FunctionLibrary.mouseClick(driver);
					}
					if(Object_Type.equalsIgnoreCase("tableValidation1")){
						System.out.println("tb11");
						FunctionLibrary.tableValidation1(driver);
					}
					excel.setData(TCModule, j, 5, "PASS");
					ModuleStatus="true";
					}catch(Exception e)
					{
						
					File screnShotFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(screnShotFile, new File("D:\\StockAccountingVasu\\MavanStocAccounting\\ScreenShots"+FunctionLibrary.genarateDate()+".png"));
						excel.setData(TCModule, j, 5, "FAIL");
						ModuleStatus="false";
						break;
					}
					
				}
				if (ModuleStatus.equalsIgnoreCase("true"))
				{
				excel.setData("MastertestCases", i, 3, "PASS");	
				}else
					if(ModuleStatus.equalsIgnoreCase("false"))
					{
						excel.setData("MastertestCases", i, 3, "FAIL");
					}
			}
			else
			{
				excel.setData("MastertestCases", i, 3, "Not Executed");	
			}
		}
	}
}

