package com.mf;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.hp.lft.sdk.*;
import com.hp.lft.sdk.mobile.*;

import unittesting.*;

public class LeanFtTest extends UnitTestClassBase {
    private int counter = 0;
    private String[] names = {"sshiff", "Shahar2"};
    private String[] passwords = {"5954194bd5c6399c6cd6ceb9978eb34043372b53e941331", "5954194bd5c6399c6cd6ceb9978eb34043372b53e941331"};
    private String OS_TYPE = "Android";
    private String currentDevice;
    private MCUtils mcutils;
    private AppModelAOS_Android appModel;

    @BeforeClass
    public void beforeClass() throws Exception {
    }

    @AfterClass
    public void afterClass() throws Exception {
    }

    @BeforeMethod
    public void beforeMethod() throws Exception {
        mcutils = new MCUtils();
        mcutils.INSTALL_APP = false;
        mcutils.HIGHLIGHT = true;
        mcutils.APP_IDENTIFIER = "com.Advantage.aShopping";
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

            mcutils.lockDevice(description, DeviceSource.MOBILE_CENTER);
            //device = MobileLab.lockDevice(description);

            mcutils.setApp();
            device = mcutils.device;

            currentDevice = "Allocated device: \"" + device.getName() + "\" (" + device.getId() + "), Model :"
                    + device.getModel() + ", OS: " + device.getOSType() + " version: " + device.getOSVersion()
                    + ", manufacturer: " + device.getManufacturer();

            mcutils.logMessages (currentDevice + ". App in use: \"" + mcutils.app.getName() + "\" v" + mcutils.app.getVersion(), LOG_LEVEL.INFO);

            if (mcutils.INSTALL_APP)
                mcutils.app.install();
            else
                mcutils.app.restart();

            appModel = new AppModelAOS_Android(mcutils.device);

            openMenu();
            // log in/out management
            mcutils.logMessages("Check if I'm logged, if not, sign me in", LOG_LEVEL.INFO);
            if (appModel.AdvantageShoppingApplication().SIGNOUTLabel().exists(5)) {     // is anyone signed in?
                if (appModel.AdvantageShoppingApplication().LoggedInUserNameLabel().getText().compareTo(names[counter]) != 0) {    // is current user signed in?
                    signOut();
                    signIn();
                }
                else {      // need to close the menu to continue with the script
                    if (mcutils.HIGHLIGHT)
                        appModel.AdvantageShoppingApplication().HOMELabel().highlight();
                    appModel.AdvantageShoppingApplication().HOMELabel().tap();
                }
            }
            else        // no one is logged it - let's sign in
                signIn();

            // select Tablets
            mcutils.logMessages("Select tablets", LOG_LEVEL.INFO);
            if (mcutils.HIGHLIGHT)
                appModel.AdvantageShoppingApplication().TABLETSLabel().highlight();
            appModel.AdvantageShoppingApplication().TABLETSLabel().tap();

            windowSync(2000);
            // pick a tablet
            mcutils.logMessages("Pick a tablet", LOG_LEVEL.INFO);
            if (mcutils.HIGHLIGHT)
                appModel.AdvantageShoppingApplication().ImageViewProductUiObject().highlight();
            appModel.AdvantageShoppingApplication().ImageViewProductUiObject().tap();

            // add to cart
            mcutils.logMessages("Click 'Add To Cart'", LOG_LEVEL.INFO);
            if (mcutils.HIGHLIGHT)
                appModel.AdvantageShoppingApplication().ADDTOCARTButton().highlight();
            appModel.AdvantageShoppingApplication().ADDTOCARTButton().tap();

            windowSync(2000);
            // go to cart
            mcutils.logMessages("Click cart icon", LOG_LEVEL.INFO);
            if (mcutils.HIGHLIGHT)
                appModel.AdvantageShoppingApplication().CartAccessUiObject().highlight();
            appModel.AdvantageShoppingApplication().CartAccessUiObject().tap();

            // check out
            mcutils.logMessages("Click 'Checkout' Pay $...", LOG_LEVEL.INFO);
            if (mcutils.HIGHLIGHT)
                appModel.AdvantageShoppingApplication().CHECKOUTPAYButton().highlight();
            appModel.AdvantageShoppingApplication().CHECKOUTPAYButton().tap();

            windowSync(5000);

            // Pay
            mcutils.logMessages("Click 'Pay Now'", LOG_LEVEL.INFO);
            if (mcutils.HIGHLIGHT)
                appModel.AdvantageShoppingApplication().PAYNOWButton().highlight();
            appModel.AdvantageShoppingApplication().PAYNOWButton().tap();

            mcutils.logMessages("Click the 'X' in Order Payment dialog", LOG_LEVEL.INFO);
            if (mcutils.HIGHLIGHT)
                appModel.AdvantageShoppingApplication().ImageViewCloseDialogUiObject().highlight();
            appModel.AdvantageShoppingApplication().ImageViewCloseDialogUiObject().tap();

            signOut();

            mcutils.logMessages("======================= Test completed successfully =======================", LOG_LEVEL.INFO);

        } catch (InterruptedException iex) {
            mcutils.logMessages("Interrupted exception: " + iex.getMessage() + "\nCurrent device: " + currentDevice, LOG_LEVEL.ERROR);
        } catch (GeneralLeanFtException err) {
            mcutils.logMessages("LeanFT/UFTPro exception: " + err.getMessage() + "\nCurrent device: " + currentDevice, LOG_LEVEL.ERROR);
            //Assert.fail("(assertion)");
        } catch (Exception ex) {
            mcutils.logMessages ("General error: " + ex.getMessage() + "\nCurrent device: " + currentDevice, LOG_LEVEL.ERROR);
        } finally {
            mcutils.logMessages ("Exit test() \nCurrent device: " + currentDevice, LOG_LEVEL.INFO);
        }
    }

    ///////////////////////////////////////////////////////////////
    ///                     Aux functions                       ///
    ///////////////////////////////////////////////////////////////

    private void windowSync(int milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }

    private void signOut() throws GeneralLeanFtException {
        mcutils.logMessages("Enter signing out method", LOG_LEVEL.INFO);
        openMenu();
        appModel.AdvantageShoppingApplication().SIGNOUTLabel().tap();
        appModel.AdvantageShoppingApplication().YESButton().tap();
        mcutils.logMessages("Exit signing out method", LOG_LEVEL.INFO);
    }

    private void signIn() throws GeneralLeanFtException {
        mcutils.logMessages ("Enter signIn() method", LOG_LEVEL.INFO);

        // open menu
        openMenu();

        // start Login
        mcutils.logMessages("Start logging in", LOG_LEVEL.INFO);
        if (mcutils.HIGHLIGHT)
            appModel.AdvantageShoppingApplication().LOGINLabel().highlight();
        appModel.AdvantageShoppingApplication().LOGINLabel().tap();

        // user name
        mcutils.logMessages("User name is as " + names[counter], LOG_LEVEL.INFO);
        if (mcutils.HIGHLIGHT)
            appModel.AdvantageShoppingApplication().USERNAMELabel().highlight();
        appModel.AdvantageShoppingApplication().MobileEditEditUserNameField().setText(names[counter]);

        // password
        mcutils.logMessages("Encrypted password is " + passwords[counter], LOG_LEVEL.INFO);
        if (mcutils.HIGHLIGHT)
            appModel.AdvantageShoppingApplication().PASSWORDLabel().highlight();
        appModel.AdvantageShoppingApplication().MobileEditEditPasswordField().setSecure(passwords[counter++]);

        // log-in
        mcutils.logMessages("Click 'LOGIN'", LOG_LEVEL.INFO);
        if (mcutils.HIGHLIGHT)
            appModel.AdvantageShoppingApplication().LOGINButton().highlight();
        appModel.AdvantageShoppingApplication().LOGINButton().tap();

        mcutils.logMessages ("Exit signIn() method", LOG_LEVEL.INFO);
    }

    private void openMenu() throws GeneralLeanFtException{
        mcutils.logMessages("Open menu", LOG_LEVEL.INFO);
        if (mcutils.HIGHLIGHT)
            appModel.AdvantageShoppingApplication().ImageViewHamburger().highlight();
        appModel.AdvantageShoppingApplication().ImageViewHamburger().tap();
    }
}
