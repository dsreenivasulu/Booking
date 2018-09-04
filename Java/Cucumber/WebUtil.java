//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lifeimage.automation.util;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.ConnectionHub;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.llp.LLPException;
import ca.uhn.hl7v2.llp.MinLowerLayerProtocol;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.GenericParser;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.parser.PipeParser;
import com.lifeimage.automation.pageobjects.emr.EMRForkPage;
import com.lifeimage.automation.pageobjects.homepage.LandingHomePage;
import com.lifeimage.automation.pageobjects.login.LoginPage;
import com.lifeimage.automation.pageobjects.pacsserver.PacsLoginPage;
import com.lifeimage.automation.pageobjects.phr.login.PhrLoginPage;
import com.lifeimage.automation.pageobjects.uploader.UploadExamPage;
import com.lifeimage.automation.pageobjects.users.UserAdminPage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang3.StringUtils;
import org.dcm4che2.tool.dcmsnd.DcmSnd;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebUtil {
    private static final int WAIT_TIME_OUT = 60;
    public static final String LILA_SERVER = (new PropertyReader()).readProperty("LILA_SERVER");
    private static final String LEMR_SERVER = (new PropertyReader()).readProperty("LEMR_SERVER");
    private static final String PACS_A_URL = (new PropertyReader()).readProperty("PACS_A_URL");
    private static final String PACS_B_URL = (new PropertyReader()).readProperty("PACS_B_URL");
    private static final String PACS_C_URL = (new PropertyReader()).readProperty("PACS_C_URL");
    private static final String PACS_D_URL = (new PropertyReader()).readProperty("PACS_D_URL");
    private static final String PACS_E_URL = (new PropertyReader()).readProperty("PACS_E_URL");
    private static final String LILA1_SERVER = (new PropertyReader()).readProperty("LILA1_SERVER");
    private static final String LILA2_SERVER = (new PropertyReader()).readProperty("LILA2_SERVER");
    private static final String LILA3_SERVER = (new PropertyReader()).readProperty("LILA3_SERVER");
    private static final String LILA4_SERVER = (new PropertyReader()).readProperty("LILA4_SERVER");
    private static final String ADMIN_USERNAME = (new PropertyReader()).readProperty("LILA_ADMIN_USERNAME");
    private static final String ADMIN_PASSWORD = (new PropertyReader()).readProperty("LILA_ADMIN_PASSWORD");
    private static final String ADMIN_PROTOCOL = (new PropertyReader()).readProperty("ADMIN_APP_PROTOCOL");
    private static final String ADMIN_HOST = (new PropertyReader()).readProperty("ADMIN_APP_SERVER");
    private static final String ADMIN_PORT = (new PropertyReader()).readProperty("ADMIN_APP_PORT");
    private static final String ADMIN_CONTEXT = (new PropertyReader()).readProperty("ADMIN_APP_CONTEXT");
    private static final String PHR_CLOUD_URL = (new PropertyReader()).readProperty("PHR_ClOUD_URL");
    private static final String UBOX_PROTOCOL = (new PropertyReader()).readProperty("UBOX_APP_PROTOCOL");
    private static final String UBOX_HOST = (new PropertyReader()).readProperty("UBOX_APP_SERVER");
    private static final String UBOX_PORT = (new PropertyReader()).readProperty("UBOX_APP_PORT");
    private static final String UBOX_CONTEXT = (new PropertyReader()).readProperty("UBOX_APP_CONTEXT");
    static String ADMIN_URL;
    static String UBOX_URL;
    public static LandingHomePage landingHomePage;
    static final String LOGIN_URL;
    static final String INDEX_URL;
    private static final String RESTART_URL;
    static final String LEMR_LOGIN_URL;
    static final String BASE_URL;
    public static final String LOGOUT_URL;
    private static final String LOG_URL;
    static final String EMR_LAUNCH = "launchinbox.html";
    static final String EMR_JSON = "launchinbox.json";
    static final String LILA1_LOGIN_URL;
    static final String LILA2_LOGIN_URL;
    static final String LILA3_LOGIN_URL;
    static final String LILA4_LOGIN_URL;
    static final String PHR_LOGIN_URL;
    public static final String SERIES_SOP_XML;
    public static final String PASSWORD_RESET_MAIL_LINK;
    protected static final Logger logger;
    public static Long TIME_IN_MS;

    public WebUtil() {
    }

    public static LoginPage logout(WebDriver driver) {
        driver.manage().deleteAllCookies();
        waitForElementVisible(driver, By.cssSelector("#j_username_"));
        return (LoginPage)PageFactory.initElements(driver, LoginPage.class);
    }

    public void clickAddExamButton(WebDriver driver) {
        this.clickBtn(driver, "Add existing exams");
    }

    private void clickBtn(WebDriver driver, String string) {
        By btns = By.cssSelector(".btn-primary");
        waitForElementVisible(driver, btns);
        List<WebElement> findElements = driver.findElements(btns);
        Iterator i$ = findElements.iterator();

        WebElement webElement;
        do {
            if(!i$.hasNext()) {
                return;
            }

            webElement = (WebElement)i$.next();
        } while(!string.equalsIgnoreCase(webElement.getText()));

        webElement.click();
    }

    public static PhrLoginPage gotoPhrSignInPage(WebDriver driver) {
        driver.get(PHR_LOGIN_URL);
        return (PhrLoginPage)PageFactory.initElements(driver, PhrLoginPage.class);
    }

    public static LoginPage gotoSignInPage(WebDriver driver) {
        driver.get(LOGIN_URL);
        return (LoginPage)PageFactory.initElements(driver, LoginPage.class);
    }

    public static LoginPage gotoLILA1SignInPage(WebDriver driver) {
        driver.get(LILA1_LOGIN_URL);
        return (LoginPage)PageFactory.initElements(driver, LoginPage.class);
    }

    public static LoginPage gotoLILA2SignInPage(WebDriver driver) {
        driver.get(LILA2_LOGIN_URL);
        return (LoginPage)PageFactory.initElements(driver, LoginPage.class);
    }

    public static LoginPage gotoLILA3SignInPage(WebDriver driver) {
        driver.get(LILA3_LOGIN_URL);
        return (LoginPage)PageFactory.initElements(driver, LoginPage.class);
    }

    public static String getLILAHost() throws MalformedURLException {
        return (new URL(LOGIN_URL)).getHost();
    }

    public static String getUboxURL() {
        return UBOX_URL;
    }

    public static String getAdminAppLoginURL() {
        return ADMIN_URL;
    }

    public static String getCloudLogOutURL() {
        return getUboxURL() + "/logout";
    }

    public static String getRandomString(String middleString) {
        Random random = new Random();
        int unique1 = random.nextInt(1000);
        int unique2 = random.nextInt(1000);
        return unique1 + middleString + unique2;
    }

    public static PacsLoginPage gotoPacsSignInPage(WebDriver driver, String PACSServerName) {
        if(PACSServerName.equalsIgnoreCase("PACS_A")) {
            driver.get(PACS_A_URL);
        } else if(PACSServerName.equalsIgnoreCase("PACS_B")) {
            driver.get(PACS_B_URL);
        } else if(PACSServerName.equalsIgnoreCase("PACS_C")) {
            driver.get(PACS_C_URL);
        } else if(PACSServerName.equalsIgnoreCase("PACS_D")) {
            driver.get(PACS_D_URL);
        } else {
            driver.get(PACS_E_URL);
        }

        return (PacsLoginPage)PageFactory.initElements(driver, PacsLoginPage.class);
    }

    public static void selectUploaderDestiniationForGroups(WebDriver driver, String[] optionList, String userName) throws Throwable {
        String uploaderuserName = "Administrator, LILA";
        waitForElementVisible(driver, By.cssSelector(".field-instruction>a"));
        click(driver, By.cssSelector(".field-instruction>a"));
        click(driver, By.linkText("People"));
        WebElement row_element = getRowFromTable(driver, By.className("paginating-table"), uploaderuserName, 3);
        if(row_element != null) {
            List<WebElement> colElements = row_element.findElements(By.tagName("td"));
            String[] arr$ = optionList;
            int len$ = optionList.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                String selectOption = arr$[i$];
                if(selectOption.equals("manual")) {
                    ((WebElement)colElements.get(0)).findElement(By.name("manualNominateGroupIds")).click();
                } else if(selectOption.equals("auto")) {
                    ((WebElement)colElements.get(1)).findElement(By.name("autoNominateGroupIdsOnUpload")).click();
                } else {
                    ((WebElement)colElements.get(2)).findElement(By.name("autoNominateGroupIdsOnOrder")).click();
                }
            }
        }

    }

    public static boolean checkLogFileForContent1(WebDriver driver, String content) {
        String currentUrl = driver.getCurrentUrl();
        driver.get(LOG_URL);
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + content + "')]"));
        driver.get(currentUrl);
        return list.size() > 0;
    }

    public static void hoverAndClick(WebDriver driver, By by) {
        Actions action = new Actions(driver);
        WebElement warningIcon = driver.findElement(by);
        action.moveToElement(warningIcon).build().perform();
    }

    public static void sendKeysAndEnter(WebDriver driver, By by, String s) {
        WebElement element = driver.findElement(by);
        element.sendKeys(new CharSequence[]{s});
        element.sendKeys(new CharSequence[]{Keys.ENTER});
    }

    public static void clickEditAndsendKeys(WebDriver driver, By editBy, By by, String s) {
        clickLink(driver, editBy);
        WebElement element = driver.findElement(by);
        element.clear();
        element.sendKeys(new CharSequence[]{s});
        driver.findElement(editBy).click();
    }

    public static WebElement clickLink(WebDriver driver, By editBy) {
        waitForElementVisible(driver, editBy);
        WebElement findElement = driver.findElement(editBy);
        clickButton(driver, findElement);
        return findElement;
    }

    public static void clickButton(WebDriver driver, WebElement findElement) {
        if(driver instanceof InternetExplorerDriver) {
            String onClickScript = "if(document.createEvent){     var evObj = document.createEvent('MouseEvents');     evObj.initEvent('click',true, false);      arguments[0].dispatchEvent(evObj);} else if(document.createEventObject){      arguments[0].fireEvent('onclick');}";
            waitFor(2000L);
            ((InternetExplorerDriver)driver).executeScript(onClickScript, new Object[]{findElement});
            waitFor(2000L);
        } else {
            findElement.click();
        }

    }

    public static UserAdminPage gotoUserAdminPage(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 60L);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading-mask.qa-loading-mask")));
        int count = 10;

        while(count > 0) {
            try {
                waitForElementClickable(driver, By.cssSelector(".qa-admin"));
                click(driver, By.cssSelector(".qa-admin"));
                waitForElementVisible(driver, By.cssSelector(".admin-title"));
                waitForElementVisible(driver, By.cssSelector(".cujo-box-stretch.admin-content-container"));
                waitForElementVisible(driver, By.xpath("//button[@name='createUser']"));
                return (UserAdminPage)PageFactory.initElements(driver, UserAdminPage.class);
            } catch (Exception var4) {
                --count;
            }
        }

        return (UserAdminPage)PageFactory.initElements(driver, UserAdminPage.class);
    }

    public static UserAdminPage gotoConfigurationPage(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 60L);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading-mask.qa-loading-mask")));
        int count = 10;

        while(count > 0) {
            try {
                waitForElementClickable(driver, By.cssSelector(".qa-admin"));
                click(driver, By.cssSelector(".qa-admin"));
                waitForElementVisible(driver, By.cssSelector(".admin-title"));
                waitForElementVisible(driver, By.cssSelector(".word-break.qa-app-config-heading"));
                return (UserAdminPage)PageFactory.initElements(driver, UserAdminPage.class);
            } catch (Exception var4) {
                --count;
            }
        }

        return (UserAdminPage)PageFactory.initElements(driver, UserAdminPage.class);
    }

    public static UploadExamPage gotoUploadExamPage(WebDriver driver) {
        int count = 10;

        while(count > 0) {
            waitForElementVisible(driver, By.cssSelector("#upload_button"));
            click(driver, By.cssSelector("#upload_button"));
            boolean isUploadPageFound = waitForUpoadPage(driver);
            if(isUploadPageFound) {
                return (UploadExamPage)PageFactory.initElements(driver, UploadExamPage.class);
            }

            try {
                WebDriverWait wait = new WebDriverWait(driver, 120L);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='li_workflow_WizardNavBar_0']/li[1]/span")));
                return (UploadExamPage)PageFactory.initElements(driver, UploadExamPage.class);
            } catch (Exception var4) {
                --count;
            }
        }

        return null;
    }

    private static boolean waitForUpoadPage(WebDriver driver) {
        boolean isUploadPageFound = false;
        int cnt = 6;

        do {
            --cnt;

            try {
                WebDriverWait wait = new WebDriverWait(driver, 5L);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("upload")));
                isUploadPageFound = true;
            } catch (Exception var4) {
                if(isElementDisplayed(driver, By.name("create"))) {
                    isUploadPageFound = true;
                    break;
                }
            }
        } while(cnt > 0);

        return isUploadPageFound;
    }

    public static UploadExamPage gotoLEMRUploadExamPage(WebDriver driver) {
        int count = 10;

        while(count > 0) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, 60L);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Next')]")));
                return (UploadExamPage)PageFactory.initElements(driver, UploadExamPage.class);
            } catch (StaleElementReferenceException var3) {
                --count;
            }
        }

        return null;
    }

    public static void click(WebDriver driver, By by) {
        int count = 10;

        while(count > 0) {
            try {
                WebElement element = driver.findElement(by);
                element.click();
                return;
            } catch (StaleElementReferenceException var6) {
                --count;
            } catch (NoSuchElementException var7) {
                try {
                    Thread.sleep(200L);
                } catch (InterruptedException var5) {
                    ;
                }

                --count;
            }
        }

    }

    public static void sendKeys(WebDriver driver, By by, String s, boolean append) {
        WebElement element = driver.findElement(by);
        if(!append) {
            element.clear();
        }

        element.sendKeys(new CharSequence[]{s});
    }

    public static void createDynamicURL(StepsContext stepsContext) {
        long timeMillis = System.currentTimeMillis();
        stepsContext.addState("dynamicEmailId", "qatest2+" + timeMillis + "@lifeimage.com");
    }

    public static void switchToFrame(WebDriver driver, String by) {
        driver.switchTo().defaultContent();
        driver.switchTo().frame(by);
    }

    public static void selectCheckBox(WebDriver driver, By by) {
        waitForElementVisible(driver, by);
        WebElement element = driver.findElement(by);
        if(!element.isSelected()) {
            element.click();
        }

    }

    public static void selectCheckBox(WebDriver driver, WebElement webElement) {
        waitForElementVisible(driver, webElement);
        if(!webElement.isSelected()) {
            webElement.click();
        }

    }

    public static void selectEditCheckBox(WebDriver driver, By editBy, By by) {
        waitForElementVisible(driver, editBy);
        clickLink(driver, editBy);
        WebElement element = driver.findElement(by);
        if(!element.isSelected()) {
            element.click();
        }

        clickLink(driver, editBy);
    }

    public static void ensureElementClick(WebDriver driver, By clickableElement, By verifyElement) {
        int count = 10;

        while(count > 0) {
            try {
                waitForElementVisible(driver, clickableElement);
                click(driver, clickableElement);
                waitForElementVisible(driver, verifyElement);
                break;
            } catch (TimeoutException var5) {
                --count;
            }
        }

    }

    public static boolean isGetFirstSelectedOptionOnDropDown(WebDriver driver, By by, String groupName) {
        waitForElementVisible(driver, by);
        Select select = new Select(driver.findElement(by));
        WebElement option = select.getFirstSelectedOption();
        return option.getText().contains(groupName);
    }

    public static void unSelectEditCheckBox(WebDriver driver, By editBy, By by) {
        WebElement findElement = clickLink(driver, editBy);
        waitForElementVisible(driver, by);
        WebElement element = driver.findElement(by);
        if(element.isSelected()) {
            element.click();
        }

        findElement.click();
    }

    public static void unSelectCheckBox(WebDriver driver, By by) {
        waitForElementVisible(driver, by);
        WebElement element = driver.findElement(by);
        if(element.isSelected()) {
            element.click();
        }

    }

    public static void waitForElementClickable(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, 60L);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void waitForElementClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 60L);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void unSelectCheckBox(WebElement webElement, By by) {
        WebElement element = webElement.findElement(by);
        if(element.isSelected()) {
            element.click();
        }

    }

    public static void selectCheckBox(WebElement webElement, By by) {
        WebElement element = webElement.findElement(by);
        if(!element.isSelected()) {
            element.click();
        }

    }

    public static void waitForElementVisible(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, 60L);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static boolean isElementVisible(WebDriver driver, By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 60L);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (Exception var3) {
            return false;
        }
    }

    public static boolean isElementVisible(WebDriver driver, By by, int waitTime) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, (long)waitTime);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (Exception var4) {
            return false;
        }
    }

    public static void waitForElementEnabled(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, 60L);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static boolean isElementExist(WebDriver driver, By by) {
        return driver.findElements(by).size() > 0;
    }

    public static String getText(WebDriver driver, By by) {
        return driver.findElement(by).getText();
    }

    public static void waitForElementInVisible(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, 60L);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public static void clearAndSendKeys(WebDriver driver, By by, String s) {
        WebElement element = driver.findElement(by);
        element.clear();
        element.sendKeys(new CharSequence[]{s});
    }

    public static void clearAndSendKeys(WebDriver driver, WebElement element, String s) {
        element.clear();
        element.sendKeys(new CharSequence[]{s});
    }

    public static boolean isElementExistInTable(WebDriver driver, String tableName, String username, int colIndex) {
        boolean isExist = false;
        List<WebElement> colValues = driver.findElements(By.cssSelector(tableName + ">tbody>tr>td:nth-child(" + colIndex + ")"));
        Iterator i$ = colValues.iterator();

        while(i$.hasNext()) {
            WebElement colElement = (WebElement)i$.next();
            if(!colElement.getText().contains("\\") && colElement.getText().equalsIgnoreCase(username)) {
                isExist = true;
                break;
            }

            if(colElement.getText().contains("\\") && colElement.getText().contains("\\" + username)) {
                isExist = true;
                break;
            }
        }

        return isExist;
    }

    public static void selectFromDropdown(WebDriver driver, By by, String options) {
        Select oSelect = new Select(driver.findElement(by));
        oSelect.selectByVisibleText(options);
    }

    public static List<WebElement> getAllOptions(WebDriver driver, By by) {
        Select oSelect = new Select(driver.findElement(by));
        return oSelect.getOptions();
    }

    public static void clickEditAndSelectFromDropdown(WebDriver driver, By editBy, By by, String options) {
        clickLink(driver, editBy);
        Select oSelect = new Select(driver.findElement(by));
        oSelect.selectByVisibleText(options);
        driver.findElement(editBy).click();
    }

    public static void doubleClick(WebDriver driver, By by) {
        driver.findElement(by).click();
        driver.findElement(by).click();
    }

    public static void clickLogout(WebDriver driver) {
        if(driver.findElements(By.xpath(".//*[@id='dijit_Dialog_2']/div[1]/span[2]")).size() != 0 && driver.findElement(By.xpath(".//*[@id='dijit_Dialog_2']/div[1]/span[2]")).isDisplayed()) {
            waitForElementVisible(driver, By.xpath(".//*[@id='dijit_Dialog_2']/div[1]/span[2]"));
            click(driver, By.xpath(".//*[@id='dijit_Dialog_2']/div[1]/span[2]"));
            waitForElementInVisible(driver, By.xpath(".//*[@id='dijit_Dialog_2']/div[1]/span[2]"));
        }

        if(driver.findElements(By.linkText("Log out")).size() != 0 && driver.findElement(By.linkText("Log out")).isDisplayed()) {
            WebDriverWait wait = new WebDriverWait(driver, 20L);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading-mask.qa-loading-mask")));
            By logout = By.linkText("Log out");
            click(driver, logout);
            waitForElementVisible(driver, By.cssSelector("#j_username_"));
        }

    }

    public static void clickPACSLogout(WebDriver driver) {
        driver.findElement(By.className("logout")).click();
    }

    public static boolean isElementDisplayed(WebDriver driver, By by) {
        try {
            WebElement element = driver.findElement(by);
            return element.isDisplayed();
        } catch (NoSuchElementException var3) {
            return false;
        }
    }

    public static boolean isElementEnabled(WebDriver driver, By by) {
        return driver.findElement(by).isEnabled();
    }

    public static boolean isElementSelected(WebDriver driver, By by) {
        return driver.findElement(by).isSelected();
    }

    public static List<WebElement> getRowListFromTable(WebDriver driver, By by) {
        WebElement myTable = driver.findElement(by);
        WebElement tableBodyElement = myTable.findElement(By.tagName("tbody"));
        return tableBodyElement.findElements(By.tagName("tr"));
    }

    public static WebElement getRowFromTableWithContains(WebDriver driver, By by, String elementName, int colIndex) {
        if(!isElementDisplayed(driver, by)) {
            return null;
        } else {
            label37:
            do {
                int count = 10;

                while(count > 0) {
                    try {
                        WebElement myTable = driver.findElement(by);
                        WebElement tableBodyElement = myTable.findElement(By.tagName("tbody"));
                        List<WebElement> rowsTable = tableBodyElement.findElements(By.tagName("tr"));
                        int rowsCount = rowsTable.size();
                        int i = 1;

                        while(true) {
                            if(i >= rowsCount) {
                                continue label37;
                            }

                            List<WebElement> columnList = ((WebElement)rowsTable.get(i)).findElements(By.tagName("td"));
                            String diplayName = ((WebElement)columnList.get(colIndex)).getText();
                            String[] List = diplayName.split("\n");
                            if(List[0].contains(elementName)) {
                                return (WebElement)rowsTable.get(i);
                            }

                            ++i;
                        }
                    } catch (Exception var13) {
                        --count;
                    }
                }
            } while(isNextButtonEnabled(driver));

            return null;
        }
    }

    public static WebElement getRowFromTable(WebDriver driver, By by, String elementName, int colIndex) {
        if(!isElementDisplayed(driver, by)) {
            return null;
        } else {
            label37:
            do {
                int count = 10;

                while(count > 0) {
                    try {
                        WebElement myTable = driver.findElement(by);
                        WebElement tableBodyElement = myTable.findElement(By.tagName("tbody"));
                        List<WebElement> rowsTable = tableBodyElement.findElements(By.tagName("tr"));
                        int rowsCount = rowsTable.size();
                        int i = 1;

                        while(true) {
                            if(i >= rowsCount) {
                                continue label37;
                            }

                            List<WebElement> columnList = ((WebElement)rowsTable.get(i)).findElements(By.tagName("td"));
                            String diplayName = ((WebElement)columnList.get(colIndex)).getText();
                            String[] List = diplayName.split("\n");
                            if(List[0].equalsIgnoreCase(elementName)) {
                                return (WebElement)rowsTable.get(i);
                            }

                            ++i;
                        }
                    } catch (Exception var13) {
                        --count;
                    }
                }
            } while(isNextButtonEnabled(driver));

            return null;
        }
    }

    public static List<String> getRowsFromTable(WebDriver driver, By by, String elementName, int colIndex) {
        ArrayList listOfGroups = new ArrayList();

        label29:
        do {
            int count = 10;

            while(count > 0) {
                try {
                    listOfGroups = new ArrayList();
                    WebElement myTable = driver.findElement(by);
                    WebElement tableBodyElement = myTable.findElement(By.tagName("tbody"));
                    List<WebElement> rowsTable = tableBodyElement.findElements(By.tagName("tr"));
                    int rowsCount = rowsTable.size();
                    int i = 1;

                    while(true) {
                        if(i >= rowsCount) {
                            continue label29;
                        }

                        List<WebElement> columnList = ((WebElement)rowsTable.get(i)).findElements(By.tagName("td"));
                        String diplayName = ((WebElement)columnList.get(colIndex)).getText();
                        listOfGroups.add(diplayName);
                        ++i;
                    }
                } catch (Exception var13) {
                    --count;
                }
            }
        } while(isNextButtonEnabled(driver));

        return listOfGroups;
    }

    public static WebElement getRowFromTableWithOutHeader(WebDriver driver, By by, String elementName, int colIndex) {
        label34:
        do {
            int count = 10;

            while(count > 0) {
                try {
                    WebElement myTable = driver.findElement(by);
                    WebElement tableBodyElement = myTable.findElement(By.tagName("tbody"));
                    List<WebElement> rowsTable = tableBodyElement.findElements(By.tagName("tr"));
                    int rowsCount = rowsTable.size();
                    int i = 0;

                    while(true) {
                        if(i >= rowsCount) {
                            continue label34;
                        }

                        List<WebElement> columnList = ((WebElement)rowsTable.get(i)).findElements(By.tagName("td"));
                        String diplayName = ((WebElement)columnList.get(colIndex)).getText();
                        String[] List = diplayName.split("\n");
                        if(List[0].equalsIgnoreCase(elementName)) {
                            return (WebElement)rowsTable.get(i);
                        }

                        ++i;
                    }
                } catch (Exception var13) {
                    --count;
                }
            }
        } while(isNextButtonEnabled(driver));

        return null;
    }

    public static void refreshPage(WebDriver driver) {
        int cnt = 3;

        while(true) {
            --cnt;

            try {
                driver.navigate().refresh();
                WebDriverWait wait = new WebDriverWait(driver, 50L);
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading-mask.qa-loading-mask")));
                break;
            } catch (UnhandledAlertException var3) {
                handleUploaderAlert(driver);
                waitFor(1000L);
                if(cnt <= 0) {
                    break;
                }
            }
        }

    }

    public static boolean isNextEnable(WebDriver driver) {
        if(driver.getPageSource().contains("Next")) {
            WebElement nextButton = driver.findElement(By.xpath("//div[contains(@class,'paging-controls-container')]//button[3]"));
            if(nextButton.isDisplayed() && nextButton.isEnabled()) {
                nextButton.click();
                return true;
            }
        }

        return false;
    }

    public static boolean isNextEnable(WebDriver driver, By parentId) {
        WebElement parent = driver.findElement(parentId);
        if(parent.isDisplayed()) {
            List<WebElement> nextButtons = parent.findElements(By.xpath("//div[contains(@class,'paging-controls-container')]//button[3]"));
            Iterator i$ = nextButtons.iterator();

            while(i$.hasNext()) {
                WebElement webElement = (WebElement)i$.next();
                if(webElement.isDisplayed() && webElement.isEnabled()) {
                    webElement.click();
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isNextButtonEnabled(WebDriver driver) {
        if(driver.getPageSource().contains("Next")) {
            By linkText = By.linkText("Next");
            List<WebElement> elements = driver.findElements(linkText);
            if(elements != null && elements.size() > 0) {
                ((WebElement)elements.get(0)).click();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isNextButtonEnabledOnUploader(WebDriver driver) {
        if(driver.getPageSource().contains("Next")) {
            driver.findElement(By.xpath("//*[@dojoattachpoint='_nextPageButton']")).click();
            return true;
        } else {
            return false;
        }
    }

    public static void findAndClickOnElementInDijitTable(WebDriver driver, int tableIndex, String elementToClick, int colIndex) {
        label18:
        while(true) {
            List<WebElement> colValues = driver.findElements(By.xpath(".//*[@id='li_widget_grid_PaginatingGrid_" + tableIndex + "']/div[2]/div[2]/table/tbody/tr/td[" + colIndex + "]"));
            Iterator i$ = colValues.iterator();

            WebElement colElement;
            do {
                if(!i$.hasNext()) {
                    if(isNextEnable(driver, By.xpath(".//*[@id='li_widget_grid_PaginatingGrid_" + tableIndex + "']"))) {
                        continue label18;
                    }

                    return;
                }

                colElement = (WebElement)i$.next();
            } while(!colElement.getText().equals(elementToClick));

            colElement.click();
            return;
        }
    }

    public static void findAndClickOnLEMRElementInDijitTable(WebDriver driver, int tableIndex, String elementToClick, int colIndex) {
        do {
            List<WebElement> colValues = driver.findElements(By.xpath("(.//div[contains(@class, 'paginating-grid')])[" + tableIndex + "]/div[2]/div[2]/table/tbody/tr/td[" + colIndex + "]"));
            Iterator i$ = colValues.iterator();

            while(i$.hasNext()) {
                WebElement colElement = (WebElement)i$.next();
                if(colElement.getText().equals(elementToClick)) {
                    colElement.click();
                    break;
                }
            }
        } while(isNextEnable(driver));

    }

    public static void setCheckBoxChecked(WebDriver driver, By by, boolean isChecked) {
        WebElement element = driver.findElement(by);
        if(element.isSelected()) {
            element.click();
        }

        if(isChecked) {
            element.click();
        }

    }

    public static EMRForkPage gotToEMRFork(WebDriver driver, String aruments) {
        StringBuilder urlBuilder = new StringBuilder(BASE_URL);
        urlBuilder.append("launchinbox.html");
        urlBuilder.append("?").append(aruments);
        driver.get(urlBuilder.toString());
        EMRForkPage initElements = (EMRForkPage)PageFactory.initElements(driver, EMRForkPage.class);
        initElements.updateProfession(driver);
        return initElements;
    }

    public static String gotToEMRJSON(WebDriver driver, String aruments) {
        StringBuilder urlBuilder = new StringBuilder(BASE_URL);
        urlBuilder.append("launchinbox.json");
        urlBuilder.append("?").append(aruments);
        driver.get(urlBuilder.toString());
        waitForElementVisible(driver, By.tagName("body"));
        return driver.findElement(By.tagName("pre")).getText();
    }

    public void clickUploadNewExamButton(WebDriver driver) {
        this.clickBtn(driver, "Upload new exams");
    }

    public static List<String> csvToList(String csv) {
        ArrayList<String> list = new ArrayList();
        if(csv != null && csv.trim().length() > 0) {
            String[] strArray = csv.split("(?<!\\\\),");
            String[] arr$ = strArray;
            int len$ = strArray.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                String string = arr$[i$];
                if(string != null && string.trim().length() > 0) {
                    list.add(string.replace("\\,", ",").trim());
                }
            }
        }

        return list;
    }

    public static void goToIndexPage(WebDriver driver) {
        driver.get(INDEX_URL);
    }

    public static boolean handleUploaderAlert(WebDriver driver) {
        try {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
            return true;
        } catch (NoAlertPresentException var2) {
            return false;
        }
    }

    public static void waitForLoadPage(WebDriver driver) {
        int cnt = 3;

        while(true) {
            --cnt;

            try {
                ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return Boolean.valueOf(((JavascriptExecutor)driver).executeScript("return document.readyState", new Object[0]).equals("complete"));
                    }
                };
                WebDriverWait wait = new WebDriverWait(driver, 30L);
                wait.until(pageLoadCondition);
                return;
            } catch (Exception var4) {
                if(cnt >= 0) {
                    return;
                }
            }
        }
    }

    private static boolean isLogoutLinkDisplayed(WebDriver driver) {
        return driver.findElements(By.partialLinkText("logout")).size() != 0;
    }

    public static void assertTrue(WebDriver driver, String message, boolean condition) {
        if(!condition) {
            saveScreenshot(driver);
            if(isLogoutLinkDisplayed(driver)) {
                clickLogout(driver);
            }

            fail(message);
        }

    }

    public static void assertTrue(WebDriver driver, boolean condition) {
        if(!condition) {
            saveScreenshot(driver);
            if(isLogoutLinkDisplayed(driver)) {
                clickLogout(driver);
            }
        }

    }

    public static void assertFalse(WebDriver driver, String message, boolean condition) {
        assertTrue(driver, message, !condition);
    }

    public static void saveScreenshot(WebDriver driver) {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File screenshotAs = (File)ts.getScreenshotAs(OutputType.FILE);
        String format = (new SimpleDateFormat("yyyyMMddhhmmss")).format(new Date());
        File file = new File("build/screenshots/" + format + ".png");

        try {
            FileUtils.copyFile(screenshotAs, file);
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }

    public static byte[] saveScreenshotAsByte(WebDriver driver) {
        WebDriver augmentedDriver = (new Augmenter()).augment(driver);
        driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
        byte[] screenshotAs = (byte[])((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.BYTES);
        return screenshotAs;
    }

    private static void fail(String message) {
        if(message == null) {
            throw new AssertionError();
        } else {
            throw new AssertionError(message);
        }
    }

    public static boolean checkLogFileForContent(WebDriver driver, String content) {
        return getNumberOfOccuranceInlogFile(driver, content) > 0;
    }

    public static int getNumberOfOccuranceInlogFile(WebDriver driver, String content) {
        String data = getData(LOG_URL, ADMIN_USERNAME, ADMIN_PASSWORD);
        int countMatches = StringUtils.countMatches(data, content);
        return countMatches;
    }

    public static String getSeriesSopXMLURL(Integer studyId, Integer seriesId) {
        return String.format(SERIES_SOP_XML, new Object[]{studyId, seriesId});
    }

    public static String getData(String uri, String userName, String userPassword) {
        BufferedReader reader = null;
        StringBuilder loginBuilder = (new StringBuilder()).append("Basic ").append(Base64.encodeBase64String((userName + ":" + userPassword).getBytes()));

        HttpURLConnection connection;
        try {
            URL url = new URL(uri);
            connection = (HttpURLConnection)url.openConnection();
            connection.addRequestProperty("Authorization", loginBuilder.toString());
            StringBuilder data = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while((line = reader.readLine()) != null) {
                data.append(line);
            }

            String var9 = data.toString();
            return var9;
        } catch (Exception var13) {
            connection = null;
        } finally {
            IOUtils.closeQuietly(reader);
        }

        return connection;
    }

    public static void waitForElementVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 60L);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementVisible(WebDriver driver, List<WebElement> element) {
        WebDriverWait wait = new WebDriverWait(driver, 60L);
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public static void selectFromDropdownForHtml(WebDriver driver, By by, String options) {
        waitForMilliSeconds(1000);
        driver.findElement(by).click();
        waitForMilliSeconds(1000);
        driver.findElement(By.linkText(options)).click();
    }

    public static void waitForMilliSeconds(int milliSeconds) {
        try {
            Thread.sleep((long)milliSeconds);
        } catch (InterruptedException var2) {
            ;
        }

    }

    public static boolean restartTomcatServices(WebDriver driver) {
        String currentUrl = driver.getCurrentUrl();
        driver.get(RESTART_URL);
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'Tomcat is restarted successfully')]"));
        driver.get(currentUrl);
        return list.size() > 0;
    }

    public static void deleteAllCookiesInBrowser(WebDriver driver) {
        driver.manage().deleteAllCookies();
    }

    public static void waitFor(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException var3) {
            ;
        }

    }

    public static LoginPage gotoLemrSignInPage(WebDriver driver) {
        driver.get(LEMR_LOGIN_URL);
        return (LoginPage)PageFactory.initElements(driver, LoginPage.class);
    }

    public static void clickLEMRLogout(WebDriver driver) {
        waitForElementVisible(driver, By.partialLinkText("Logout"));
        click(driver, By.partialLinkText("Logout"));
    }

    public static boolean isCheckBoxSelected(WebDriver driver, By allCheckboxNode) {
        waitForElementVisible(driver, allCheckboxNode);
        WebElement element = driver.findElement(allCheckboxNode);
        return element.isSelected();
    }

    public static void sendExamToAE(String ae, String host, String port, String dir) {
        String[] newArgs = new String[]{String.format("%s@%s:%s", new Object[]{ae, host, port}), dir};
        DcmSnd.main(newArgs);
    }

    public static boolean waitForElementToAttach(WebDriver driver, By by) {
        int count = 3;

        do {
            try {
                if(by != null && driver.findElement(by).isDisplayed() && driver.findElement(by).isEnabled()) {
                    return true;
                }
            } catch (NoSuchElementException | StaleElementReferenceException var6) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException var5) {
                    ;
                }

                --count;
            }
        } while(count != 0);

        return false;
    }

    public static boolean waitForElementToPresent(WebDriver driver, By by) {
        int count = 3;

        while(true) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, 300L);
                wait.until(ExpectedConditions.presenceOfElementLocated(by));
                return true;
            } catch (NoSuchElementException | StaleElementReferenceException var6) {
                try {
                    Thread.sleep(300L);
                } catch (InterruptedException var5) {
                    ;
                }

                --count;
                if(count == 0) {
                    return false;
                }
            }
        }
    }

    public static boolean isElementAttached(WebDriver driver, By by) {
        int count = 10;

        do {
            try {
                if(by != null && driver.findElement(by).isDisplayed()) {
                    return true;
                }
            } catch (NoSuchElementException | StaleElementReferenceException var6) {
                try {
                    Thread.sleep(300L);
                } catch (InterruptedException var5) {
                    ;
                }

                --count;
            }
        } while(count != 0);

        return false;
    }

    public static void goTologout(WebDriver driver) {
        driver.get(LOGOUT_URL);
        waitForElementVisible(driver, By.id("j_username_"));
    }

    public static LoginPage gotoLILA4SignInPage(WebDriver driver) {
        driver.get(LILA4_LOGIN_URL);
        return (LoginPage)PageFactory.initElements(driver, LoginPage.class);
    }

    public static void verifySignInPage(WebDriver driver) {
        waitForElementVisible(driver, By.cssSelector("#j_username_"));
    }

    public static boolean isElementClickable(WebDriver driver, By by) {
        return !driver.findElement(by).getAttribute("class").equals("disabled");
    }

    public static boolean isTrue(String status) {
        return "true".equalsIgnoreCase(status) || "yes".equalsIgnoreCase(status) || "active".equalsIgnoreCase(status) || "checked".equalsIgnoreCase(status);
    }

    public static void waitForMs(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException var3) {
            var3.printStackTrace();
        }

    }

    public static void createDynamicEmail(StepsContext stepsContext) {
        long timeMillis = System.currentTimeMillis();
        stepsContext.addState("dynamicEmailId", "qatest2+" + timeMillis + "@lifeimage.com");
    }

    public static WebElement waitUntilAndGet(WebDriver driver, ExpectedCondition<WebElement> visibilityOfElementLocated, int timeInSec) {
        do {
            --timeInSec;

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException var5) {
                ;
            }

            try {
                WebElement value = (WebElement)visibilityOfElementLocated.apply(driver);
                if(value != null) {
                    return value;
                }
            } catch (Exception var4) {
                ;
            }
        } while(timeInSec > 0);

        return null;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static Message sendAndReceiveMessage(String theHost, int thePort, String messageStr) throws HL7Exception, LLPException, IOException {
        theHost = getLILAHost();
        int thePort = 2575;
        ConnectionHub connectionHub = ConnectionHub.getInstance();
        Message responseMessage = null;
        Connection connection = null;

        try {
            Parser p = new GenericParser();
            Message requestMessage = null;
            requestMessage = p.parse(messageStr);
            connection = connectionHub.attach(theHost, thePort, new PipeParser(), MinLowerLayerProtocol.class);
            Initiator initiator = connection.getInitiator();
            responseMessage = initiator.sendAndReceive(requestMessage);
        } catch (Throwable var12) {
            var12.printStackTrace();
        } finally {
            if(connection != null) {
                connectionHub.detach(connection);
            }

        }

        return responseMessage;
    }

    public static Message sendAndReceiveMessage(String filePath, String host, int port) throws HL7Exception, LLPException, IOException {
        String messageStr = readHL7Message(filePath);
        ConnectionHub connectionHub = ConnectionHub.getInstance();
        Message responseMessage = null;
        Connection connection = null;

        try {
            Parser p = new PipeParser();
            Message requestMessage = null;
            requestMessage = p.parse(messageStr);
            connection = connectionHub.attach(host, port, new PipeParser(), MinLowerLayerProtocol.class);
            Initiator initiator = connection.getInitiator();
            responseMessage = initiator.sendAndReceive(requestMessage);
        } catch (Throwable var13) {
            var13.printStackTrace();
        } finally {
            if(connection != null) {
                connectionHub.detach(connection);
            }

        }

        return responseMessage;
    }

    public static String readHL7Message(String filePath) {
        String message = null;

        try {
            message = new String(Files.readAllBytes(Paths.get(filePath, new String[0])));
        } catch (FileNotFoundException var3) {
            var3.printStackTrace();
        } catch (IOException var4) {
            var4.printStackTrace();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return message;
    }

    public static boolean collectionContains(ArrayList<String> list, String csv) {
        String[] expectedItems = csv.split(",");
        boolean isAllExpectedItemsPresent = true;
        String[] arr$ = expectedItems;
        int len$ = expectedItems.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            String expectedItem = arr$[i$];
            isAllExpectedItemsPresent = isAllExpectedItemsPresent && list.contains(expectedItem.trim());
            if(logger.isDebugEnabled() && !isAllExpectedItemsPresent) {
                logger.debug(expectedItem.trim() + " is not present in the ui");
            }
        }

        return isAllExpectedItemsPresent;
    }

    public static void selectFromDropdownByIndex(WebDriver driver, By by, int index) {
        Select oSelect = new Select(driver.findElement(by));
        oSelect.selectByIndex(index);
    }

    public static Boolean booleanToString(String option) {
        return option.equals("")?null:Boolean.valueOf(option);
    }

    public static void createDynamicNumber(StepsContext stepsContext) {
        long timeMillis = System.currentTimeMillis();
        stepsContext.addState("dynamicNumber", Long.valueOf(timeMillis));
    }

    public static boolean isElementsAttached(WebDriver driver, List<WebElement> elements) {
        boolean isAttached = true;

        WebElement element;
        for(Iterator i$ = elements.iterator(); i$.hasNext(); isAttached = isAttached && isElementAttached(driver, element)) {
            element = (WebElement)i$.next();
        }

        return isAttached;
    }

    public static boolean isElementAttached(WebDriver driver, WebElement element) {
        int count = 10;

        do {
            try {
                if(element != null && element.isDisplayed()) {
                    return true;
                }
            } catch (NoSuchElementException | StaleElementReferenceException var6) {
                try {
                    Thread.sleep(300L);
                } catch (InterruptedException var5) {
                    ;
                }

                --count;
            }
        } while(count != 0);

        return false;
    }

    public static boolean isElementAttachedAndClick(WebDriver driver, WebElement element) {
        int count = 10;

        do {
            try {
                if(element != null && element.isDisplayed()) {
                    Actions actions = new Actions(driver);
                    actions.moveToElement(element).click().perform();
                    waitForMs(100L);
                    return true;
                }
            } catch (NoSuchElementException | StaleElementReferenceException var4) {
                --count;
            }
        } while(count != 0);

        return false;
    }

    public static String getFormattedLifeIMAGEMailId(String email) {
        String formattedEmailId = null;
        if(email != null) {
            String emailName = email.substring(0, email.indexOf(64));
            formattedEmailId = emailName + System.currentTimeMillis() + "@lifeimage.com";
        } else {
            formattedEmailId = "qatest2+" + System.currentTimeMillis() + "@lifeimage.com";
        }

        return formattedEmailId;
    }

    public static String getLilaBaseUrl() {
        return BASE_URL;
    }

    public static void restartLILA(WebDriver driver) throws IOException {
        File file = new File("");
        String path = file.getCanonicalFile().getParentFile().getParentFile().getAbsolutePath() + File.separatorChar;
        String gradlewCommand = "";
        if(SystemUtils.IS_OS_WINDOWS) {
            gradlewCommand = path + "gradlew.bat :test:automation-bdd:restartTomcat -DSERVER=" + LILA_SERVER;
        } else {
            gradlewCommand = path + "./gradlew :test:automation-bdd:restartTomcat -DSERVER=" + LILA_SERVER;
        }

        Runtime.getRuntime().exec(gradlewCommand);
        waitFor(30000L);
        int count = 10;

        while(true) {
            --count;
            driver.get(LOGIN_URL);
            waitForLoadPage(driver);

            try {
                WebDriverWait wait = new WebDriverWait(driver, 30L);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#j_username_")));
                return;
            } catch (Exception var6) {
                waitFor(10000L);
                if(count <= 0) {
                    return;
                }
            }
        }
    }

    static {
        ADMIN_URL = ADMIN_PROTOCOL + "://" + ADMIN_HOST + ":" + ADMIN_PORT + "/" + ADMIN_CONTEXT;
        UBOX_URL = UBOX_PROTOCOL + "://" + UBOX_HOST + ":" + UBOX_PORT + "/" + UBOX_CONTEXT;
        LOGIN_URL = "http://" + LILA_SERVER + "/inbox/login.jsp";
        INDEX_URL = "http://" + LILA_SERVER + "/inbox/index.html";
        RESTART_URL = "http://" + LILA_SERVER + "/inbox/restart/v1";
        LEMR_LOGIN_URL = "http://" + LEMR_SERVER + "/lemr/login.jsp";
        BASE_URL = "http://" + LILA_SERVER + "/inbox/";
        LOGOUT_URL = "http://" + LILA_SERVER + "/inbox/j_spring_security_logout";
        LOG_URL = "http://" + LILA_SERVER + "/inbox/logs/v1";
        LILA1_LOGIN_URL = "http://" + LILA1_SERVER + "/inbox/login.jsp";
        LILA2_LOGIN_URL = "http://" + LILA2_SERVER + "/inbox/login.jsp";
        LILA3_LOGIN_URL = "http://" + LILA3_SERVER + "/inbox/login.jsp";
        LILA4_LOGIN_URL = "http://" + LILA4_SERVER + "/inbox/login.jsp";
        PHR_LOGIN_URL = "http://" + PHR_CLOUD_URL + "/universal-inbox/login?OrganizationCode=rsna&ServiceName=phr";
        SERIES_SOP_XML = "http://" + LILA_SERVER + "/inbox/obj/viewable/" + (new SimpleDateFormat("YYYYMMdd")).format(new Date()) + "/%d/%d/series_sops.xml";
        PASSWORD_RESET_MAIL_LINK = "http://" + LILA_SERVER + "/inbox/account/userlogin.html?ak=";
        logger = LoggerFactory.getLogger(WebUtil.class);
        TIME_IN_MS = Long.valueOf(System.currentTimeMillis());
    }

    public abstract static class ExecuteWithRetry<T> {
        private int retryCount;

        public ExecuteWithRetry(int retryCount) {
            this.retryCount = retryCount;
        }

        public T execute() throws WebDriverException {
            int count = this.retryCount;
            StaleElementReferenceException lastException = null;

            while(count > 0) {
                try {
                    return this.run();
                } catch (StaleElementReferenceException var6) {
                    --count;

                    try {
                        Thread.sleep(300L);
                    } catch (InterruptedException var5) {
                        ;
                    }

                    lastException = var6;
                }
            }

            throw lastException;
        }

        public abstract T run();
    }

    public abstract static class WaitForPage {
        private int retryCount;

        public WaitForPage(int retryCount) {
            this.retryCount = retryCount;
        }

        public Page getPage() {
            int count = this.retryCount;

            while(count > 0) {
                try {
                    return this.getPageImpl();
                } catch (StaleElementReferenceException var3) {
                    --count;
                }
            }

            return null;
        }

        public abstract Page getPageImpl();
    }
}
