# INF112 Project BrentPizza 

# RoboRally :robot: :pizza:

## How to run the program

The program is built using Maven. To run the game, run  serverStart.java in the master branch. The server will ask for amount of players in the console. 
2 is recommended, however 11 is maximum. After that run either Main or Main1 ro start the game. To determine the robots action, choose 5 cards from the 9 options that are given and press the ready button. When the games is started, press the **Start**-button on the screen to start the game. If you are playing multi-player, the robots will not move until all players have chosen their cards and pressed ready. Keep eye on console to be properly updated on current game events. Proper GUI messages will be implemtned in the last iteration. 

Recommended screen resolution: 1920x1080

To play **Multiplayer on different computers:** 
Start the server on one computer. Then start the application on both computers. Then press play. After that press connect and input the server IP in the console on both computers (you can use localhost for the hosting computer if you want). Then just press enter and they should both be connected to the same server. The ports used are 7979 and 7878. So these need to be opened in the servers network if you want to play across the global internet. 

To play multiplayer run the **serverStart.java**, **main1.java** and **main.java**. Then press ready on both applications. 

# How to do testing
To run the tests. Just press run on the test you wish to run. Then press connect and play. If the test is automatic you just have to close the application and then the test 
will be done. There are however, two manual tests, in map test where you have to perform the tests manually. It is important that you use the cards to move when performing 
these tests. 

For the laser test you have to move within the proxcimity of the laser to recieve damage using the cards. 

To perform whether the player respawn you have to move the player outside of the map using the cards. 

## Known bugs
- For the game to work properly the generated window can't be altered. Otherwise when you choose cards you will not get the intended card. This is due to the way we set resoulution in the program, this needs to be a global setting instead of the local solution we are using now
