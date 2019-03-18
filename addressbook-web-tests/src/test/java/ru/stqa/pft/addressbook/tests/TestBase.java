package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.appmanager.AplicationManager;

import static org.openqa.selenium.remote.BrowserType.CHROME;

public class TestBase {


    protected final AplicationManager app = new AplicationManager(CHROME);

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();

    }

    public AplicationManager getApp() {
        return app;
    }
}
