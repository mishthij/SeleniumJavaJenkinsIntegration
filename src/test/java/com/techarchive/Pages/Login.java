package com.techarchive.Pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login extends PageObject{

    @FindBy(xpath = "//input[@id='user-name']")
    private WebElement user_name;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//input[@id='login-button']")
    private WebElement login_button;

    public Login(WebDriver driver) {
        super(driver);
    }

    public void loginUser( String usrName, String pwd) {
        enterUserame(usrName);
        enterPassword(pwd);
        pressLoginButton();
    }

    public void enterUserame(String usrName){
        this.user_name.sendKeys(usrName);
    }

    public void enterPassword(String pwd){
        this.password.sendKeys(pwd);
    }
    public void logincredential(String username, String password){
        this.user_name.sendKeys(username);
        this.password.sendKeys(password);
    }
    public void pressLoginButton(){
        this.login_button.click();
    }

}
