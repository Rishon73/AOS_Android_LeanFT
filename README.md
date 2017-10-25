# AOS_Android_UFTPro

UFTPro (LeanFT) script with TestNG framework. Created with Eclipse.

For parallel executions use the @Test annotaion:

@Test (threadPoolSize = 3, invocationCount = 1) invocationCount dictates the number of devices to run the script.

#There's a problem with "'Checkout' Pay $..." step: if the amount isn't $849.99, this step will fail - need to be able to click that button no matter what's the $ amount on it...
