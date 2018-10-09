package com.mf;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.hp.lft.sdk.*;
import com.hp.lft.sdk.mobile.*;
import com.mf.utils.*;

import unittesting.*;

public class LeanFtTest extends UnitTestClassBase {
    private int counter = 0;
    private String[] names = {"Shahar", "Shahar2"};
    private String[] passwords = {"460d4691b2f164b933e1476fa1", "5954194bd5c6399c6cd6ceb9978eb34043372b53e941331"};
    private String OS_TYPE = "Android";
    private String currentDevice;
    private MobileLabUtils mcUtils;
    private AppModelAOS_Android appModel;

    @BeforeClass
    public void beforeClass() throws Exception {
    }

    @AfterClass
    public void afterClass() throws Exception {
    }

    @BeforeMethod
    public void beforeMethod() throws Exception {
        mcUtils = new MobileLabUtils();
        mcUtils.setInstallApp(false);
        mcUtils.setHighlight(true);
        mcUtils.setAppIdentifier("com.Advantage.aShopping");
        mcUtils.setAppVersion("1.1.4");
    }

    @AfterMethod
    public void afterMethod() throws Exception {
        counter++;
    }

    @Test (threadPoolSize = 3, invocationCount = 1)
    public void test() throws GeneralLeanFtException {
        Device device;
        try {
            DeviceDescription description = new DeviceDescription();
            description.setOsType(OS_TYPE);
            //description.setName("SM-N910C");
            //description.setModel("Sony");

            mcUtils.lockDevice(description, MobileLabUtils.LabType.SRF);
            //device = MobileLab.lockDevice(description);

            mcUtils.setApp();
            device = mcUtils.getDevice();

            currentDevice = "Allocated device: \"" + device.getName() + "\" (" + device.getId() + "), Model :"
                    + device.getModel() + ", OS: " + device.getOSType() + " version: " + device.getOSVersion()
                    + ", manufacturer: " + device.getManufacturer();
            
            Logging.logMessage (currentDevice + ". App in use: \"" + mcUtils.getApp().getName() + "\" v" +
                    mcUtils.getAppVersion(), Logging.LOG_LEVEL.INFO);

            if (mcUtils.isInstallApp())
                mcUtils.getApp().install();
            else
                mcUtils.getApp().restart();

            appModel = new AppModelAOS_Android(device);

            openMenu();
            // log in/out management
            Logging.logMessage("Check if I'm logged, if not, sign me in", Logging.LOG_LEVEL.INFO);
            if (appModel.AdvantageShoppingApplication().SIGNOUTLabel().exists(5)) {     // is anyone signed in?
                if (appModel.AdvantageShoppingApplication().LoggedInUserNameLabel().getText().compareTo(names[counter]) != 0) {    // is current user signed in?
                    signOut();
                    signIn();
                }
                else {      // need to close the menu to continue with the script
                    if (mcUtils.isHighlight())
                        appModel.AdvantageShoppingApplication().HOMELabel().highlight();
                    appModel.AdvantageShoppingApplication().HOMELabel().tap();
                }
            }
            else        // no one is logged it - let's sign in
                signIn();

            // select Tablets
            Logging.logMessage("Select tablets", Logging.LOG_LEVEL.INFO);
            if (mcUtils.isHighlight())
                appModel.AdvantageShoppingApplication().TABLETSLabel().highlight();
            appModel.AdvantageShoppingApplication().TABLETSLabel().tap();

            windowSync(2000);
            // pick a tablet
            Logging.logMessage("Pick a tablet", Logging.LOG_LEVEL.INFO);
            if (mcUtils.isHighlight())
                appModel.AdvantageShoppingApplication().ImageViewProductUiObject().highlight();
            appModel.AdvantageShoppingApplication().ImageViewProductUiObject().tap();

            // add to cart
            Logging.logMessage("Click 'Add To Cart'", Logging.LOG_LEVEL.INFO);
            if (mcUtils.isHighlight())
                appModel.AdvantageShoppingApplication().ADDTOCARTButton().highlight();
            appModel.AdvantageShoppingApplication().ADDTOCARTButton().tap();

            windowSync(2000);
            // go to cart
            Logging.logMessage("Click cart icon", Logging.LOG_LEVEL.INFO);
            if (mcUtils.isHighlight())
                appModel.AdvantageShoppingApplication().CartAccessUiObject().highlight();
            appModel.AdvantageShoppingApplication().CartAccessUiObject().tap();

            // check out
            Logging.logMessage("Click 'Checkout' Pay $...", Logging.LOG_LEVEL.INFO);
            if (mcUtils.isHighlight())
                appModel.AdvantageShoppingApplication().CHECKOUTPAYButton().highlight();
            appModel.AdvantageShoppingApplication().CHECKOUTPAYButton().tap();

            windowSync(5000);

            // Pay
            Logging.logMessage("Click 'Pay Now'", Logging.LOG_LEVEL.INFO);
            if (mcUtils.isHighlight())
                appModel.AdvantageShoppingApplication().PAYNOWButton().highlight();
            appModel.AdvantageShoppingApplication().PAYNOWButton().tap();

            Logging.logMessage("Click the 'X' in Order Payment dialog", Logging.LOG_LEVEL.INFO);
            if (mcUtils.isHighlight())
                appModel.AdvantageShoppingApplication().ImageViewCloseDialogUiObject().highlight();
            appModel.AdvantageShoppingApplication().ImageViewCloseDialogUiObject().tap();

            signOut();

            Logging.logMessage("======================= Test completed successfully =======================", Logging.LOG_LEVEL.INFO);

        } catch (InterruptedException iex) {
            Logging.logMessage("Interrupted exception: " + iex.getMessage() + "\nCurrent device: " + currentDevice, Logging.LOG_LEVEL.ERROR);
        } catch (GeneralLeanFtException err) {
            Logging.logMessage("LeanFT/UFTPro exception: " + err.getMessage() + "\nCurrent device: " + currentDevice, Logging.LOG_LEVEL.ERROR);
            //Assert.fail("(assertion)");
        } catch (Exception ex) {
            Logging.logMessage ("General error: " + ex.getMessage() + "\nCurrent device: " + currentDevice, Logging.LOG_LEVEL.ERROR);
        } finally {
            Logging.logMessage ("Exit test() \nCurrent device: " + currentDevice, Logging.LOG_LEVEL.INFO);
        }
    }

    ///////////////////////////////////////////////////////////////
    ///                     Aux functions                       ///
    ///////////////////////////////////////////////////////////////

    private void windowSync(int milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }

    private void signOut() throws GeneralLeanFtException {
        Logging.logMessage("Enter signing out method", Logging.LOG_LEVEL.INFO);
        openMenu();
        appModel.AdvantageShoppingApplication().SIGNOUTLabel().tap();
        appModel.AdvantageShoppingApplication().YESButton().tap();
        Logging.logMessage("Exit signing out method", Logging.LOG_LEVEL.INFO);
    }

    private void signIn() throws GeneralLeanFtException {
        Logging.logMessage ("Enter signIn() method", Logging.LOG_LEVEL.INFO);

        // open menu
        openMenu();

        // start Login
        Logging.logMessage("Start logging in", Logging.LOG_LEVEL.INFO);
        if (mcUtils.isHighlight())
            appModel.AdvantageShoppingApplication().LOGINLabel().highlight();
        appModel.AdvantageShoppingApplication().LOGINLabel().tap();

        // user name
        Logging.logMessage("User name is as " + names[counter], Logging.LOG_LEVEL.INFO);
        if (mcUtils.isHighlight())
            appModel.AdvantageShoppingApplication().USERNAMELabel().highlight();
        appModel.AdvantageShoppingApplication().MobileEditEditUserNameField().setText(names[counter]);

        // password
        Logging.logMessage("Encrypted password is " + passwords[counter], Logging.LOG_LEVEL.INFO);
        if (mcUtils.isHighlight())
            appModel.AdvantageShoppingApplication().PASSWORDLabel().highlight();
        appModel.AdvantageShoppingApplication().MobileEditEditPasswordField().setSecure(passwords[counter++]);

        // log-in
        Logging.logMessage("Click 'LOGIN'", Logging.LOG_LEVEL.INFO);
        if (mcUtils.isHighlight())
            appModel.AdvantageShoppingApplication().LOGINButton().highlight();
        appModel.AdvantageShoppingApplication().LOGINButton().tap();

        Logging.logMessage ("Exit signIn() method", Logging.LOG_LEVEL.INFO);
    }

    private void openMenu() throws GeneralLeanFtException{
        Logging.logMessage("Open menu", Logging.LOG_LEVEL.INFO);
        if (mcUtils.isHighlight())
            appModel.AdvantageShoppingApplication().ImageViewHamburger().highlight();
        appModel.AdvantageShoppingApplication().ImageViewHamburger().tap();
    }
}
