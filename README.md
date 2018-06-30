# steam-avatar-changer
This project uses Selenium to upload avatars from a specified folder to your Steam account.

If you have Steam Guard enabled and have it set to send the code to a Gmail account, the project automatically gets the code from your Gmail. If you have your Steam Guard code sent to another email service, or use mobile authunicator instead, you have two options:
1. Comment out `steamGuardCode.getAuthCode();` in `setSteamAvatar.java` and manually type in the code from either your mobile authunicator or another email service.
2. Rewrite the XPath used in `steamGuardCode.java`to fit the email service you use.

Two modes:
1. Randomly pick a picture from the folder and upload it.
2. Incrementally upload a picture from the folder.

Requirements:
1. ChromeDriver https://sites.google.com/a/chromium.org/chromedriver/
2. The avatar files have to be either PNG or JPG and weigh less than 1024kb.
3. Fill in your user information in `userInformation.java`

For questions email to agentrg.development@gmail.com 
