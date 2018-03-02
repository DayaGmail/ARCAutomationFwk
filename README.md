# ARCAutomationFwk
This is ARC GMP Automation

Steps : link on how to Run "http://www.screencast.com/t/cOzJPC18"

1. TO Run This for First Time you need to Add Red cross email Id and password in config.properties under resources folder.

            un = (Your Red cross ID)
            #password 
            pass = (Your rescross Password)

2. Install Cucumber Plugin from Market place

            Steps to follow to install plugin
            1) Launch the Eclipse IDE and from Help menu, click “Install New Software”.

            2) You will see a dialog window, click “Add” button.

            3) Type name as you wish, let’s take “Cucumber” and type “http://cucumber.github.com/cucumber-eclipse/update-site” as                       location. Click OK.

            4) You come back to the previous window but this time you must see Cucumber Eclipse Plugin option in the available software                   list. Just Check the box and press “Next” button.

            Note: If running behind a proxy server and you get a ‘HTTP Proxy Authentication Required’ error you may need to contact a                     system administrator to set up your proxy server settings.

            5) Click on Next.

            6) Click “I accept the terms of the license agreement” then click Finish.

            7) Let it install, it will take few seconds to complete.

            8) You may or may not encounter a Security warning, if in case you do just click OK.

            9) You are all done now, just Click Yes.
            
3. Open Feature File -> Right Click -> and Run as-> Cucumber Feature

Note : 
1.It has Code for Diffrent browsers, but all drivers are not added. (IE, Safar, and FF can be added in Driver Folder)

2. For Refrence Eextent-config.xml is placed under Properties folder, please Use it to Customise it to project Specific.

3. You have Runner Class in gmpUtilitis Folder, Kindly update it to run as Tags.(Like smoke test)
