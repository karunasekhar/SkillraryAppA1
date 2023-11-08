package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminHomePage {
	
	//Declaration
	@FindBy(xpath="//span[text() ='SkillRary Admin']")
	private WebElement adminIcon;
	
	@FindBy(xpath="//span[text()='Users']")
	private WebElement userTab;
	
	
	@FindBy(xpath="//span[text()='Courses']")
	private WebElement coursesTab;
	
	
	@FindBy(xpath="//a[text()=' Course List']")
	private WebElement courseLinkList;
	
	
	@FindBy(xpath="//a[text()=' Category']")
	private WebElement categoryLink;
	
	@FindBy(xpath="//a[text()='Sign out']")
	private WebElement signOutLink;
	
	
	//Initialization
	public AdminHomePage(WebDriver driver)  {
		PageFactory.initElements(driver, this);
		
	}
	//Utilization
	public String getAdminIcon() {
		return adminIcon.getText();
		
	}
	
	public void clickUsersTab() {
		userTab.click();
	}
	
	public void clickCoursesTab() {
		coursesTab.click();
	}
	
	public void clickCourseListLink() {
		courseLinkList.click();
	}
	
	public void clickCategoryLink() {
		categoryLink.click();
	}
	
	public void signOutofApp() {
		adminIcon.click();
		signOutLink.click();
	}
}
