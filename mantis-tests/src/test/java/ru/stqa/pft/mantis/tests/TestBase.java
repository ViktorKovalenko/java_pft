package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectBindingStub;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.openqa.selenium.remote.BrowserType.CHROME;

public class TestBase {


    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", CHROME));
    private MantisConnectBindingStub mc;

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
       app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
       app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();

    }
    public boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if(app.soap().issueStatus(issueId).equals("resolved")){
            return false;
        }
        if (app.soap().issueStatus(issueId).equals("closed")){
            return false;
        }else {
            return true;
        }
    }


    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    }