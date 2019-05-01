package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import domain.detail.account.LoginDetails;

public class LoginPage {
	WebDriver driver;
	public static String username;

	@FindBy(name ="username")
	private WebElement usernameField;
	
	@FindBy(name ="password")
	private WebElement passwordFiel;
	
	@FindBy(css ="button.btn.btn-primary.btn-custom.w-md.waves-effect.waves-light")
	private WebElement loginButton;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterUsernameAs(String username) {
		usernameField.clear();
		usernameField.sendKeys(username);
		LoginPage.username = username;
	}

	public void enterPasswordAs(String password) {
		passwordFiel.clear();
		passwordFiel.sendKeys(password);
	}

	public void loginToMainPage() {
		loginButton.click();
	}

	public void loginToMainPage(LoginDetails loginDetails) {
		//waitForLoginPageAppear() ;
		this.enterUsernameAs(loginDetails.getUsername());
		this.enterPasswordAs(loginDetails.getPassword());
		loginButton.click();
		//waitForHomePageAppear();

	}

	private void waitForLoginPageAppear() {
		util.WaitFor wait = new util.WaitFor(driver);
		wait.presenceOfTheElement(By.cssSelector("div.main-wrapper"));
	}
	
	private void waitForHomePageAppear() {
		util.WaitFor wait = new util.WaitFor(driver);
		wait.presenceOfTheElement(By.cssSelector("div.my-projects.section"));
	}
}
