package genericLibraries;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import pomPages.AddNewCategoryPage;
import pomPages.AddNewCoursePage;
import pomPages.AddNewUserPage;
import pomPages.AdminHomePage;
import pomPages.CourseListPage;
import pomPages.LoginPage;
import pomPages.UsersPage;
import pomPages.WelcomePage;
import pomPages.categorypage;

public class Baseclass {
	
	//@BeforeSuite
	//@BeforeTest
	
	protected PropertiesUtility property;
	protected ExcelUtility excel;
	protected JavaUtility jutil;
	protected WebDriverUtility webutil;
	protected WebDriver driver;
	
	public static WebDriver sdriver;
	public static JavaUtility sjutil;
	
	protected WelcomePage welcome;
	protected LoginPage login;
	protected AdminHomePage home;
	protected UsersPage users;
	protected CourseListPage course;
	protected categorypage category;
	protected AddNewUserPage addUser;
	protected AddNewCoursePage addCourse;
	protected AddNewCategoryPage addCategory;
	
	@BeforeClass
	
	public void classConfig() {
		property = new PropertiesUtility();
		excel = new ExcelUtility();
		jutil = new JavaUtility();
		webutil = new WebDriverUtility();
		
		
		property.propertiesInitialization(IConstantPath.PROPERTIES_PATH);
		driver = webutil.launchBrowser(property.readFromProperties("browser"));
		
		sdriver = driver;
		sjutil = jutil ;
	}
	
	@BeforeMethod
	
	public void methodConfig() {
		excel.excelInitialization(IConstantPath.EXCEL_PATH);
		
		welcome = new WelcomePage(driver);
		login = new LoginPage(driver);
		home = new AdminHomePage(driver);
		users = new UsersPage(driver);
		course = new CourseListPage(driver);
		category = new categorypage(driver);
		addUser = new AddNewUserPage(driver);
		addCourse = new AddNewCoursePage(driver);
		addCategory = new AddNewCategoryPage(driver);
		
		webutil.navigateToApp(property.readFromProperties("url"));
		Assert.assertEquals(welcome.getLogo(), "SkillRary-ECommerce");
		
		long time = Long.parseLong(property.readFromProperties("timeouts"));
		webutil.waitTillElementFound(time);
		
		
		welcome.clickLoginButton();
		Assert.assertEquals(login.getPageHeader(), "Login");
		
		
		login.setEmail(property.readFromProperties("username"));
		login.setPassword(property.readFromProperties("password"));
		login.clickLogin();
		Assert.assertEquals(home.getAdminIcon(),"SkillRary Admin");
		
	}
	
	@AfterMethod
	public void methodTearDown() {
		excel.closeExcel();
		home.signOutofApp();
	}
	
	@AfterClass
	public void classTearDown() {
		webutil.closeAllWindow();
	}
	
	
	//@AfterTest
	//@AfterSuite
	
	

}
