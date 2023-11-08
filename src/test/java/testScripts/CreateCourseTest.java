package testScripts;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.Baseclass;
import genericLibraries.IConstantPath;

//this is the createCourseTest
public class CreateCourseTest extends Baseclass {
	@Test
	public void createCourseTest() throws InterruptedException {
		SoftAssert soft = new SoftAssert();
		home.clickCoursesTab();
		home.clickCategoryLink();
		
		soft.assertTrue(course.getPageHeader().contains("Course List"));
		course.clickNewButton();
		Thread.sleep(2000);
		soft.assertEquals(addCourse.getPageHeader(),"Add New Course");
		Map<String,String>map = excel.readFromExcel("sheet1", "Add Course");
		String courseName = map.get("Name")+jutil.generateRandomNum(100);
		addCourse.setname(courseName);
		addCourse.selectCategory(webutil, map.get("Category"));
		addCourse.setPrice(map.get("price"));
		addCourse.uploadPhoto(map.get("photo"));
		addCourse.setDescription(webutil, map.get("description"));
		addCourse.clickSaveButton();
		
		soft.assertTrue(course.getSuccessMessage().contains("success"));
		
		boolean isPresent = false;
		List<WebElement>courseNameList = course.getCourseList();
		for(WebElement name: courseNameList) {
			if(name.getText().equals(courseName)) {
				isPresent = true;
				break;
			}
		}
		
		soft.assertTrue(isPresent);
		
		course.clickDeleteNewButton(courseName, driver);
		course.clickDelete();
		soft.assertTrue(course.getSuccessMessage().contains("Success"));
		
		if(course.getSuccessMessage().contains("Success"))
			excel.writeToExcel("Sheet1", "Add Course", "Pass", IConstantPath.EXCEL_PATH);
		else
			excel.writeToExcel("Sheet1", "Add Course", "Fail", IConstantPath.EXCEL_PATH);
		soft.assertAll();
			
		
	}
}
