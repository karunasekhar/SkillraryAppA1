package testScripts;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.Baseclass;

public class CreateCategoryTest extends Baseclass{
	@Test
	public void createCategoryTest() {
		SoftAssert soft = new SoftAssert();
		home.clickCoursesTab();
		home.clickCategoryLink();
		
		soft.assertTrue(category.getPageHeader().contains("Category"));
		category.clickNewButton();
		
		soft.assertEquals(addCategory.getPageHeader(),"Add New Category");
		Map<String,String>map = excel.readFromExcel("sheet1", "Add Category");
		String categoryName = map.get("Name")+jutil.generateRandomNum(100);
		addCategory.setName(categoryName);
		addCategory.clickSave();
		
		
		soft.assertTrue(category.getSuccessMessage().contains("success"));
		boolean isPresent = false;
		List<WebElement>categoryList = category.getCategoryList();
		for(WebElement e : categoryList) {
			if(e.getText().equals(categoryName)) {
				isPresent = true;
				break;
			}
		}
		soft.assertTrue(isPresent);
		
		
		category.clickDeleteButton(categoryName, driver);
		category.clickDelete();
		soft.assertTrue(category.getSuccessMessage().contains("Success"));
		soft.assertAll();
	}
	

}
