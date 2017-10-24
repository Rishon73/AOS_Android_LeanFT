package com.mf;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.hp.lft.sdk.*;
import com.hp.lft.sdk.mobile.*;
import com.hp.lft.verifications.*;

import unittesting.*;

import java.util.Random;

public class LeanFtTest extends UnitTestClassBase {
    private int counter = 0;
    private String[] names = {"Shahar", "Shahar2"};
    private String[] passwords = {"5954194bd5c6399c6cd6ceb9978eb34043372b53e941331", "5954194bd5c6399c6cd6ceb9978eb34043372b53e941331"};
    private boolean INSTALL_APP = false;
    private String OS_TYPE = "Android";
    private boolean HIGHLIGHT = false;
    private String currentDevice;

    @BeforeClass
    public void beforeClass() throws Exception {
    }

    @AfterClass
    public void afterClass() throws Exception {
    }

    @BeforeMethod
    public void beforeMethod() throws Exception {
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
            //description.setName("Nexus 7");
            description.setModel("Sony");
            device = MobileLab.lockDevice(description);
        } catch (GeneralLeanFtException err) {
            System.out.println("[ERR] failed allocating device: " + err.getMessage());
            return;
        }

        ApplicationDescription appDescription = new ApplicationDescription.Builder()
                .identifier("com.Advantage.aShopping").build();
        Application app = device.describe(Application.class, appDescription);

        currentDevice = "\"" + device.getName() + "\", Model:" + device.getModel() + ", OS=" + device.getOSType() + ", Version=" + device.getOSVersion();

        System.out.println("Device in use is " + currentDevice
                + "\nApp in use: \"" + app.getName() + "\", v" + app.getVersion() + "\n***************************\n"
        );

        if (INSTALL_APP) {
            app.install();
            windowSync(1500);
            initAppAfterInstall(device);
        } else
            app.restart();

        windowSync(1000);

        // open menu
        System.out.println("[INFO] open menu");
        openMenu(device);

        // log out if still logged in
        System.out.println("[INFO] check if I'm logged");
        if (device.describe(Application.class, new ApplicationDescription.Builder()
                .identifier("com.Advantage.aShopping").packaged(true).build()).describe(Label.class, new LabelDescription.Builder()
                .text("SIGN OUT").className("Label").resourceId("textViewMenuSignOut").mobileCenterIndex(10).build()).exists()) {
            System.out.println("[INFO] Still logged in... signing out");
            signOut(device);
            openMenu(device);
        }

        // start Login
        System.out.println("[INFO] start logging in");
        if (HIGHLIGHT)
            device.describe(Application.class, new ApplicationDescription.Builder()
                    .identifier("com.Advantage.aShopping").packaged(true).build()).describe(Label.class, new LabelDescription.Builder()
                    .text("LOGIN").className("Label").resourceId("textViewMenuUser").mobileCenterIndex(9).build()).highlight();
        device.describe(Application.class, new ApplicationDescription.Builder()
                .identifier("com.Advantage.aShopping").packaged(true).build()).describe(Label.class, new LabelDescription.Builder()
                .text("LOGIN").className("Label").resourceId("textViewMenuUser").mobileCenterIndex(9).build()).tap();

        // user name
        System.out.println("[INFO] Logging is as " + names[counter]);
        if (HIGHLIGHT)
            device.describe(Application.class, new ApplicationDescription.Builder()
                    .identifier("com.Advantage.aShopping").packaged(true).build()).describe(EditField.class, new EditFieldDescription.Builder()
                    .className("Input").mobileCenterIndex(0).build()).highlight();
        device.describe(Application.class, new ApplicationDescription.Builder()
                .identifier("com.Advantage.aShopping").packaged(true).build()).describe(EditField.class, new EditFieldDescription.Builder()
                .className("Input").mobileCenterIndex(0).build()).setText(names[counter]);

        // password
        System.out.println("[INFO] encrypted password is " + passwords[counter]);
        if (HIGHLIGHT)
            device.describe(Application.class, new ApplicationDescription.Builder()
                    .identifier("com.Advantage.aShopping").packaged(true).build()).describe(EditField.class, new EditFieldDescription.Builder()
                    .className("Input").mobileCenterIndex(1).build()).highlight();
        device.describe(Application.class, new ApplicationDescription.Builder()
                .identifier("com.Advantage.aShopping").packaged(true).build()).describe(EditField.class, new EditFieldDescription.Builder()
                .className("Input").mobileCenterIndex(1).build()).setSecure(passwords[counter++]);

        // log-in
        System.out.println("[INFO] Click 'LOGIN'");
        if (HIGHLIGHT)
            device.describe(Application.class, new ApplicationDescription.Builder()
                    .identifier("com.Advantage.aShopping").packaged(true).build()).describe(Button.class, new ButtonDescription.Builder()
                    .text("LOGIN").className("Button").resourceId("buttonLogin").mobileCenterIndex(0).build()).highlight();

        device.describe(Application.class, new ApplicationDescription.Builder()
                .identifier("com.Advantage.aShopping").packaged(true).build()).describe(Button.class, new ButtonDescription.Builder()
                .text("LOGIN").className("Button").resourceId("buttonLogin").mobileCenterIndex(0).build()).tap();
        // end login

        // select laptops
        System.out.println("[INFO] select laptops");
        if (HIGHLIGHT)
            device.describe(Application.class, new ApplicationDescription.Builder()
                    .identifier("com.Advantage.aShopping").packaged(true).build()).describe(Label.class, new LabelDescription.Builder()
                    .text("LAPTOPS").className("Label").resourceId("textViewCategory").mobileCenterIndex(2).build()).highlight();
        device.describe(Application.class, new ApplicationDescription.Builder()
                .identifier("com.Advantage.aShopping").packaged(true).build()).describe(Label.class, new LabelDescription.Builder()
                .text("LAPTOPS").className("Label").resourceId("textViewCategory").mobileCenterIndex(2).build()).tap();

        // pick a laptop
        System.out.println("[INFO] pick a laptop");
        if (HIGHLIGHT)
            device.describe(Application.class, new ApplicationDescription.Builder()
                    .identifier("com.Advantage.aShopping").packaged(true).build()).describe(UiObject.class, new UiObjectDescription.Builder()
                    .className("ImageView").container("Table[0][2][0]").resourceId("imageViewProduct").mobileCenterIndex(0).build()).highlight();
        device.describe(Application.class, new ApplicationDescription.Builder()
                .identifier("com.Advantage.aShopping").packaged(true).build()).describe(UiObject.class, new UiObjectDescription.Builder()
                .className("ImageView").container("Table[0][2][0]").resourceId("imageViewProduct").mobileCenterIndex(0).build()).tap();

        // add to cart
        System.out.println("[INFO] click 'Add To Cart'");
        if (HIGHLIGHT)
            device.describe(Application.class, new ApplicationDescription.Builder()
                    .identifier("com.Advantage.aShopping").packaged(true).build()).describe(Button.class, new ButtonDescription.Builder()
                    .text("ADD TO CART").className("Button").resourceId("buttonProductAddToCart").mobileCenterIndex(0).build()).highlight();
        device.describe(Application.class, new ApplicationDescription.Builder()
                .identifier("com.Advantage.aShopping").packaged(true).build()).describe(Button.class, new ButtonDescription.Builder()
                .text("ADD TO CART").className("Button").resourceId("buttonProductAddToCart").mobileCenterIndex(0).build()).tap();

        // go to cart
        System.out.println("[INFO] go back");
        if (HIGHLIGHT)
            device.describe(Application.class, new ApplicationDescription.Builder()
                    .identifier("com.Advantage.aShopping").packaged(true).build()).describe(UiObject.class, new UiObjectDescription.Builder()
                    .className("ImageView").resourceId("imageViewBack").mobileCenterIndex(1).build()).highlight();
        device.describe(Application.class, new ApplicationDescription.Builder()
                .identifier("com.Advantage.aShopping").packaged(true).build()).describe(UiObject.class, new UiObjectDescription.Builder()
                .className("ImageView").resourceId("imageViewBack").mobileCenterIndex(1).build()).tap();

        System.out.println("[INFO] open menu");
        openMenu(device);

        System.out.println("[INFO] click 'Cart'");
        if (HIGHLIGHT)
            device.describe(Application.class, new ApplicationDescription.Builder()
                    .identifier("com.Advantage.aShopping").packaged(true).build()).describe(Label.class, new LabelDescription.Builder()
                    .text("CART").className("Label").resourceId("textViewMenuCart").mobileCenterIndex(34).build()).highlight();
        device.describe(Application.class, new ApplicationDescription.Builder()
                .identifier("com.Advantage.aShopping").packaged(true).build()).describe(Label.class, new LabelDescription.Builder()
                .text("CART").className("Label").resourceId("textViewMenuCart").mobileCenterIndex(34).build()).tap();

        // check out
        try {
            System.out.println("[INFO] click 'Checkout' Pay $...");
            if (HIGHLIGHT)
                device.describe(Application.class, new ApplicationDescription.Builder()
                        .identifier("com.Advantage.aShopping").packaged(true).build()).describe(Button.class, new ButtonDescription.Builder()
                        .text("CHECKOUT (PAY $849.99)").className("Button").resourceId("buttonCheckOut").mobileCenterIndex(0).build()).highlight();
            device.describe(Application.class, new ApplicationDescription.Builder()
                    .identifier("com.Advantage.aShopping").packaged(true).build()).describe(Button.class, new ButtonDescription.Builder()
                    .text("CHECKOUT (PAY $849.99)").className("Button").resourceId("buttonCheckOut").mobileCenterIndex(0).build()).tap();
        } catch (ReplayObjectNotFoundException err) {
            System.out.println("[ERR] (ReplayObjectNotFoundException) click 'click 'Checkout' Pay $...: " + err.getMessage() + "\nDevice is: " + currentDevice);
        }
        /*

        // Pay
        try {
            System.out.println("[INFO] click 'Pay Now'");
            if (HIGHLIGHT)
                device.describe(Application.class, new ApplicationDescription.Builder()
                        .identifier("com.Advantage.aShopping").packaged(true).build()).describe(Button.class, new ButtonDescription.Builder()
                        .text("PAY NOW").className("Button").resourceId("buttonPayNow").mobileCenterIndex(0).build()).highlight();
            device.describe(Application.class, new ApplicationDescription.Builder()
                    .identifier("com.Advantage.aShopping").packaged(true).build()).describe(Button.class, new ButtonDescription.Builder()
                    .text("PAY NOW").className("Button").resourceId("buttonPayNow").mobileCenterIndex(0).build()).tap();
        } catch (ReplayObjectNotFoundException err) {
            System.out.println("[ERR] (ReplayObjectNotFoundException) click 'Pay Now': " + err.getMessage() + "\nDevice is: " + currentDevice);
        } catch (GeneralLeanFtException err2) {
            System.out.println("[ERR] (GeneralLeanFtException) click 'Pay Now': " + err2.getMessage() + "\nDevice is: " + currentDevice);
        }

        windowSync(1000);

        try {
            System.out.println("[INFO] Click the 'X' in Order Payment dialog");
            if (HIGHLIGHT)
                device.describe(Application.class, new ApplicationDescription.Builder()
                        .identifier("com.Advantage.aShopping").packaged(true).build()).describe(UiObject.class, new UiObjectDescription.Builder()
                        .className("ImageView").resourceId("imageViewCloseDialog").mobileCenterIndex(1).build()).highlight();
            device.describe(Application.class, new ApplicationDescription.Builder()
                    .identifier("com.Advantage.aShopping").packaged(true).build()).describe(UiObject.class, new UiObjectDescription.Builder()
                    .className("ImageView").resourceId("imageViewCloseDialog").mobileCenterIndex(1).build()).tap();
        } catch (ReplayObjectNotFoundException err) {
            System.out.println("[ERR] Click the 'X' in Order Payment dialog: " + err.getMessage() + "\nDevice is: " + currentDevice);
        }

        //signOut(device);
        */
    }

    ///////////////////////////////////////////////////////////////
    ///                     Aux functions                       ///
    ///////////////////////////////////////////////////////////////

    private void windowSync(int milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }

    private void initAppAfterInstall(Device device) throws GeneralLeanFtException {
        try {
            // Set correct Server
            if (HIGHLIGHT)
                device.describe(Application.class, new ApplicationDescription.Builder()
                        .identifier("com.Advantage.aShopping").packaged(true).build()).describe(Button.class, new ButtonDescription.Builder()
                        .text("OK").className("Button").resourceId("button1").mobileCenterIndex(2).build()).highlight();
            device.describe(Application.class, new ApplicationDescription.Builder()
                    .identifier("com.Advantage.aShopping").packaged(true).build()).describe(Button.class, new ButtonDescription.Builder()
                    .text("OK").className("Button").resourceId("button1").mobileCenterIndex(2).build()).tap();

            if (HIGHLIGHT)
                device.describe(Application.class, new ApplicationDescription.Builder()
                        .identifier("com.Advantage.aShopping").packaged(true).build()).describe(EditField.class, new EditFieldDescription.Builder()
                        .className("Input").resourceId("editTextServer").mobileCenterIndex(1).build()).highlight();
            device.describe(Application.class, new ApplicationDescription.Builder()
                    .identifier("com.Advantage.aShopping").packaged(true).build()).describe(EditField.class, new EditFieldDescription.Builder()
                    .className("Input").resourceId("editTextServer").mobileCenterIndex(1).build()).setText("www.advantageonlineshopping.com");

            if (HIGHLIGHT)
                device.describe(Application.class, new ApplicationDescription.Builder()
                        .identifier("com.Advantage.aShopping").packaged(true).build()).describe(Button.class, new ButtonDescription.Builder()
                        .text("Connect").className("Button").resourceId("buttonConnect").mobileCenterIndex(0).build()).highlight();
            device.describe(Application.class, new ApplicationDescription.Builder()
                    .identifier("com.Advantage.aShopping").packaged(true).build()).describe(Button.class, new ButtonDescription.Builder()
                    .text("Connect").className("Button").resourceId("buttonConnect").mobileCenterIndex(0).build()).tap();

            if (HIGHLIGHT)
                device.describe(Application.class, new ApplicationDescription.Builder()
                        .identifier("com.Advantage.aShopping").packaged(true).build()).describe(Button.class, new ButtonDescription.Builder()
                        .text("OK").className("Button").resourceId("button1").mobileCenterIndex(2).build()).highlight();
            device.describe(Application.class, new ApplicationDescription.Builder()
                    .identifier("com.Advantage.aShopping").packaged(true).build()).describe(Button.class, new ButtonDescription.Builder()
                    .text("OK").className("Button").resourceId("button1").mobileCenterIndex(2).build()).tap();
        } catch (GeneralLeanFtException err) {
            System.out.println("[ERR] error in initAppAfterInstall(): " + err.getMessage() + "\nDevice is: " + currentDevice);
        }
    }

    private void signOut(Device device) {
        try {
            device.describe(Application.class, new ApplicationDescription.Builder()
                    .identifier("com.Advantage.aShopping").packaged(true).build()).describe(Label.class, new LabelDescription.Builder()
                    .text("SIGN OUT").className("Label").resourceId("textViewMenuSignOut").mobileCenterIndex(10).build()).tap();
            device.describe(Application.class, new ApplicationDescription.Builder()
                    .identifier("com.Advantage.aShopping").packaged(true).build()).describe(Button.class, new ButtonDescription.Builder()
                    .text("YES").className("Button").resourceId("button2").mobileCenterIndex(1).build()).tap();
        } catch (GeneralLeanFtException err) {
            System.out.println("[ERR] error in signOut(): " + err.getMessage() + "\nDevice is: " + currentDevice);
        }
    }

    private void openMenu(Device device) {
        try {
            if (HIGHLIGHT)
                device.describe(Application.class, new ApplicationDescription.Builder()
                        .identifier("com.Advantage.aShopping").packaged(true).build()).describe(UiObject.class, new UiObjectDescription.Builder()
                        .className("ImageView").resourceId("imageViewMenu").mobileCenterIndex(0).build()).highlight();
            device.describe(Application.class, new ApplicationDescription.Builder()
                    .identifier("com.Advantage.aShopping").packaged(true).build()).describe(UiObject.class, new UiObjectDescription.Builder()
                    .className("ImageView").resourceId("imageViewMenu").mobileCenterIndex(0).build()).tap();
        } catch (GeneralLeanFtException err) {
            System.out.println("[ERR] error in openMenu(): " + err.getMessage() + "\nDevice is: " + currentDevice);
        }
    }
}
