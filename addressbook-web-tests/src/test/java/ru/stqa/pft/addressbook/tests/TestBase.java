package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.AplicationManager;

import static org.openqa.selenium.remote.BrowserType.CHROME;

public class TestBase {


    protected static final AplicationManager app = new AplicationManager(CHROME);

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();

    }

    public AplicationManager getApp() {
        return app;
    }
}
