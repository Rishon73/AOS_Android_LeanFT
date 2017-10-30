<?xml version="1.0" encoding="utf-8" standalone="yes"?>
<applicationModel>
    <name>Application Model</name>
    <className>AppModelAOS_Android</className>
    <objects>
        <object type="Application" technology="Mobile">
            <name>Advantage Shopping</name>
            <codeName>AdvantageShoppingApplication</codeName>
            <description></description>
            <identification>
                <properties>
                    <property name="Identifier" type="string" isRegExp="false">com.Advantage.aShopping</property>
                    <property name="IsPackaged" type="boolean" isRegExp="false">false</property>
                </properties>
            </identification>
            <children>
                <object type="Label" technology="Mobile">
                    <name>TABLETS</name>
                    <codeName>TABLETSLabel</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">2</property>
                            <property name="ResourceId" type="string" isRegExp="false">com.Advantage.aShopping:id/textViewCategory</property>
                            <property name="Text" type="string" isRegExp="false">TABLETS</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>LAPTOPS</name>
                    <codeName>LAPTOPSLabel</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                            <property name="ResourceId" type="string" isRegExp="false">com.Advantage.aShopping:id/textViewCategory</property>
                            <property name="Text" type="string" isRegExp="false">LAPTOPS</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>imageViewMenu</name>
                    <codeName>ImageViewHamburger</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                            <property name="ResourceId" type="string" isRegExp="false">com.Advantage.aShopping:id/imageViewMenu</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>LOGIN</name>
                    <codeName>LOGINLabel</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">6</property>
                            <property name="ResourceId" type="string" isRegExp="false">com.Advantage.aShopping:id/textViewMenuUser</property>
                            <property name="Text" type="string" isRegExp="false">LOGIN</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>USER NAME</name>
                    <codeName>USERNAMELabel</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">3</property>
                            <property name="Text" type="string" isRegExp="false">USER NAME</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>PASSWORD</name>
                    <codeName>PASSWORDLabel</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">4</property>
                            <property name="Text" type="string" isRegExp="false">PASSWORD</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>LOGIN</name>
                    <codeName>LOGINButton</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                            <property name="ResourceId" type="string" isRegExp="false">com.Advantage.aShopping:id/buttonLogin</property>
                            <property name="Text" type="string" isRegExp="false">LOGIN</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>imageViewProduct</name>
                    <codeName>ImageViewProductUiObject</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">4</property>
                            <property name="ResourceId" type="string" isRegExp="false">com.Advantage.aShopping:id/imageViewProduct</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>ADD TO CART</name>
                    <codeName>ADDTOCARTButton</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                            <property name="ResourceId" type="string" isRegExp="false">com.Advantage.aShopping:id/buttonProductAddToCart</property>
                            <property name="Text" type="string" isRegExp="false">ADD TO CART</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>CHECKOUT (PAY $1,279.00)</name>
                    <codeName>CHECKOUTPAYButton</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                            <property name="ResourceId" type="string" isRegExp="false">com.Advantage.aShopping:id/buttonCheckOut</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>PAY NOW</name>
                    <codeName>PAYNOWButton</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                            <property name="ResourceId" type="string" isRegExp="false">com.Advantage.aShopping:id/buttonPayNow</property>
                            <property name="Text" type="string" isRegExp="false">PAY NOW</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>imageViewCloseDialog</name>
                    <codeName>ImageViewCloseDialogUiObject</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                            <property name="ResourceId" type="string" isRegExp="false">com.Advantage.aShopping:id/imageViewCloseDialog</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>SIGN OUT</name>
                    <codeName>SIGNOUTLabel</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">7</property>
                            <property name="ResourceId" type="string" isRegExp="false">com.Advantage.aShopping:id/textViewMenuSignOut</property>
                            <property name="Text" type="string" isRegExp="false">SIGN OUT</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>YES</name>
                    <codeName>YESButton</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                            <property name="ResourceId" type="string" isRegExp="false">android:id/button2</property>
                            <property name="Text" type="string" isRegExp="false">YES</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>MobileEditUserName</name>
                    <codeName>MobileEditEditUserNameField</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>MobileEditPassword</name>
                    <codeName>MobileEditEditPasswordField</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>Cart access</name>
                    <codeName>CartAccessUiObject</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">Cart access</property>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                            <property name="ResourceId" type="string" isRegExp="false">com.Advantage.aShopping:id/imageViewCart</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>UserNameLabel</name>
                    <codeName>LoggedInUserNameLabel</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">6</property>
                            <property name="ResourceId" type="string" isRegExp="false">com.Advantage.aShopping:id/textViewMenuUser</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>TABLETSFromMenu</name>
                    <codeName>TABLETSLabelFromMenu</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">12</property>
                            <property name="ResourceId" type="string" isRegExp="false">com.Advantage.aShopping:id/textViewMenuTablets</property>
                            <property name="Text" type="string" isRegExp="false">TABLETS</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>HOME</name>
                    <codeName>HOMELabel</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">8</property>
                            <property name="ResourceId" type="string" isRegExp="false">com.Advantage.aShopping:id/textViewMenuHome</property>
                            <property name="Text" type="string" isRegExp="false">HOME</property>
                        </properties>
                    </identification>
                </object>
            </children>
        </object>
    </objects>
</applicationModel>
