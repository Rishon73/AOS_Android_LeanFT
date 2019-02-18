package com.mf;

import org.testng.annotations.*;
import com.hp.lft.sdk.*;
import com.hp.lft.sdk.mobile.*;
import com.mf.utils.*;

import unittesting.*;

public class LeanFtTest extends UnitTestClassBase {
    private String name = "";
    private String password = "5954194bd5c6399c6cd6ceb9978eb34043372b53e941331";
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
        mcUtils.setHighlight(true);
        mcUtils.setAppIdentifier("com.Advantage.aShopping");

    }

    @AfterMethod
    public void afterMethod() throws Exception {
    }

    // Runs in parallel when set to true
    // otherwise it runs in serial

    // The 4 parameters are: device ID, app version, is it a packaged version
    // and do we need to install the app
    @DataProvider(name="DevicesList", parallel=true)
    public Object[][] getTestNamesData() {
        return new Object[][]{
                {"ce08171898ee74520c7e", "1.1.7", true, true},
                //{"0a9e0bfe", "1.1.6", false},
                //{"678493775e00c096d7996b4d0192db42358d7087", "1.1.6", false},
                {"samsung SAMSUNG-SM-G930A", "1.1.7", true, true}
        };
    }

    @Test(dataProvider = "DevicesList")
    public void testNativeAOS(String deviceID, String appVersion, Boolean packaged, Boolean installApp) {
        Device device;

        mcUtils.setAppVersion(appVersion);
        mcUtils.setPackaged(packaged);
        mcUtils.setInstallApp(installApp);
        try {
            // lock device (by id/UDID)
            device = lockDeviceByID(deviceID, MobileLabUtils.LabType.MC);

            currentDevice = "'"+ device.getName() + "' (" + device.getId() + "), Model :"
                    + device.getModel() + ", OS: " + device.getOSType() + " version: " + device.getOSVersion()
                    + ", manufacturer: " + device.getManufacturer();

            // has to be Android device for this test
            if(device.getOSType().equals("IOS")){
                Logging.logMessage ("Device ID ["+deviceID+"] is iOS, exiting test...", Logging.LOG_LEVEL.INFO);
                return;
            }

            Logging.logMessage ("["+deviceID+"] running the test... ", Logging.LOG_LEVEL.INFO);

            if (mcUtils.isInstallApp())
                mcUtils.getApp().install();
            else
                mcUtils.getApp().restart();

            doTest(device);

            Logging.logMessage("["+deviceID+"]" +
                    "\n=========== Test completed successfully ===========", Logging.LOG_LEVEL.INFO);

        } catch (InterruptedException iex) {
            Logging.logMessage("Interrupted exception: " + iex.getMessage() + "\nCurrent device: " + currentDevice, Logging.LOG_LEVEL.ERROR);
        } catch (GeneralLeanFtException err) {
            Logging.logMessage("["+deviceID+"] LeanFT/UFTPro exception: " + err.getMessage() + "\nCurrent device: " + currentDevice + "\n\n" +err.getStackTrace(), Logging.LOG_LEVEL.ERROR);
            //Assert.fail("(assertion)");
        } catch (Exception ex) {
            Logging.logMessage ("["+deviceID+"] General error: " + ex.getMessage() + "\nCurrent device: " + currentDevice, Logging.LOG_LEVEL.ERROR);
        } finally {
            Logging.logMessage ("["+deviceID+"] Exit test() \nCurrent device: " + currentDevice, Logging.LOG_LEVEL.INFO);
        }
    }

    // This is the main test funcrion
    // I made it short to save time, but uncommented lines should execute w/o errors
    private void doTest(Device device) throws InterruptedException, GeneralLeanFtException {
        appModel = new AppModelAOS_Android(device);
        String deviceID = device.getId();
        /*
        openMenu();
        // log in/out management
        Logging.logMessage("["+deviceID+"] Check if I'm logged, if not, sign me in", Logging.LOG_LEVEL.INFO);
        if (appModel.AdvantageShoppingApplication().SIGNOUTLabel().exists(5)) {     // is anyone signed in?
            if (appModel.AdvantageShoppingApplication().LoggedInUserNameLabel().getText().compareTo(name) != 0) {    // is current user signed in?
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
        */
        // select Tablets
        Logging.logMessage("["+deviceID+"] Select tablets", Logging.LOG_LEVEL.INFO);
        if (mcUtils.isHighlight())
            appModel.AdvantageShoppingApplication().TABLETSLabel().highlight();
        appModel.AdvantageShoppingApplication().TABLETSLabel().tap();

        //windowSync(2000);
        // pick a tablet
        Logging.logMessage("["+deviceID+"] Pick a tablet", Logging.LOG_LEVEL.INFO);
        if (mcUtils.isHighlight())
            appModel.AdvantageShoppingApplication().ImageViewProductUiObject().highlight();
        appModel.AdvantageShoppingApplication().ImageViewProductUiObject().tap();

        // add to cart
        Logging.logMessage("["+deviceID+"] Click 'Add To Cart'", Logging.LOG_LEVEL.INFO);
        if (mcUtils.isHighlight())
            appModel.AdvantageShoppingApplication().ADDTOCARTButton().highlight();
        appModel.AdvantageShoppingApplication().ADDTOCARTButton().tap();

        windowSync(2000);

/*
        // go to cart
        Logging.logMessage("["+deviceID+"] Click cart icon", Logging.LOG_LEVEL.INFO);
        if (mcUtils.isHighlight())
            appModel.AdvantageShoppingApplication().CartAccessUiObject().highlight();
        appModel.AdvantageShoppingApplication().CartAccessUiObject().tap();

        // check out
        Logging.logMessage("["+deviceID+"] Click 'Checkout' Pay $...", Logging.LOG_LEVEL.INFO);
        if (mcUtils.isHighlight())
            appModel.AdvantageShoppingApplication().CHECKOUTPAYButton().highlight();
        appModel.AdvantageShoppingApplication().CHECKOUTPAYButton().tap();

        windowSync(5000);

        // Pay
        Logging.logMessage("["+deviceID+"] Click 'Pay Now'", Logging.LOG_LEVEL.INFO);
        if (mcUtils.isHighlight())
            appModel.AdvantageShoppingApplication().PAYNOWButton().highlight();
        appModel.AdvantageShoppingApplication().PAYNOWButton().tap();

        Logging.logMessage("["+deviceID+"] Click the 'X' in Order Payment dialog", Logging.LOG_LEVEL.INFO);
        if (mcUtils.isHighlight())
            appModel.AdvantageShoppingApplication().ImageViewCloseDialogUiObject().highlight();
        appModel.AdvantageShoppingApplication().ImageViewCloseDialogUiObject().tap();

        signOut();
*/
    }

    ///////////////////////////////////////////////////////////////
    ///                     Aux functions                       ///
    ///////////////////////////////////////////////////////////////

    private Device lockDeviceByID(String deviceID, MobileLabUtils.LabType labType) throws GeneralLeanFtException {
        Device device = null;

        /*
        DeviceDescription description = new DeviceDescription();
        description.setOsType(OS_TYPE);
        */
        //description.setName("SM-N910C");
        //description.setModel("Sony");

        //mcUtils.lockDevice(description, MobileLabUtils.LabType.SRF);
        mcUtils.lockDeviceById(deviceID, labType);
        mcUtils.setApp();
        device = mcUtils.getDevice();

        return device;
    }

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
        Logging.logMessage("User name is as " + name, Logging.LOG_LEVEL.INFO);
        if (mcUtils.isHighlight())
            appModel.AdvantageShoppingApplication().USERNAMELabel().highlight();
        appModel.AdvantageShoppingApplication().MobileEditEditUserNameField().setText(name);

        // password
        Logging.logMessage("Encrypted password is " + password, Logging.LOG_LEVEL.INFO);
        if (mcUtils.isHighlight())
            appModel.AdvantageShoppingApplication().PASSWORDLabel().highlight();
        appModel.AdvantageShoppingApplication().MobileEditEditPasswordField().setSecure(password);

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
