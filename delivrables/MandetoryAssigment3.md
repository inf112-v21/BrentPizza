# Mandatory assignment 3

## Retrospective 

Following our previous retrospective, we pointed the following three points to improve during this alteration. 
Points of improvement:
-	Redistribute code work more evenly throughout the members so that we do not fall out of the loop.
-	Work against the high degree of specialization within the group
-	More pair programming.

Therefore, during this iteration, besides focusing on fulfilling the demands for the game, focus has been on working on these points. 
We have actively taken action to redistribute code work more evenly throughout members and have had more pair programming sessions then previous weeks. 
During this iteration, every group member has provided contribution to the code base. Furthermore, group members have also more evenly distributed the “writing work” demanded 
for the assignment then before. In that respect, the goal has been achieved. However, this has not been done as smoothly as desired. 
There still exists some struggles and insecurities within the group members when doing work that is outside of our previous specializations. 
For the next iteration we want therefore to continue these points of improvement to do even more of what we are currently doing and do these with more ease. 
It is evident that an achieved work norm does not alter hastily and easily. 
It, therefore, requires more work from our part on ensuring that in the last iteration we continue and build upon the new work method. 

We have however found a very nice flow in terms of team meetings and routines that was mentioned in the previous retrospective. 
There have been no changes to these meeting routines and roles that we have had previously other than that the lack of specialization naturally leads to less defined roles. 
Furthermore, there has been no changes to the project method that is of interest other then the aforementioned distribution of work. 

## Demands

The demands for this iteration have been unique in terms of that they are not as defined as the previous mandatory assignments. 
Therefore, during our first meeting, before doing anything else, we as a group defined every demand, we believed had to be fulfilled for both this mandatory assignment 
and the next one. We then decided on how many of these we wanted to fulfil during this iteration and which we wanted to spare for the last one. 
We updated and used the project board very actively during this exercise. 
Insight on chosen demands for this iteration can be found both on the project board and in the user stories developed. 


**User story:** As a player I want to see how much damage my robot has taken to know when to repair my robot. 

**Acceptance criteria:** A damage token affects the number of cards the player can pick from. Player loses one life when he has received ten damage tokens.

**Demand:** Show amount of damage tokens the player has.

**User story:** As a player I want to see how many lives my robot has.

**Acceptance criteria:**  The player must begin with three lives. If a player goes outside the map, falls in a hole, or has received ten damage tokens, they lose one life.

**Demand:** Show amount of lives player has.

**User story:** As a player I want to be able to start the game when I am ready.

**Acceptance criteria:** A start screen must be generated, and the game begins when the player has pressed start.

**Work tasks:** A start screen class with appropriate buttons must be generated.

**Demand:** Quality of life improvements for the player.

**User story:** As a player I want to be able to end/restart the game after a session.

**Acceptance criteria:** A end screen must be generated and provide the appropriate options.

**Work tasks:** A end screen class with buttons which initiate the appropriate actions must be generated.

**Demand:** Quality of life improvements for the player.

**User story:** As a player I want to be able to view where I can repair my robot.

**Acceptance criteria:** A robot gets a damage token removed when positioned on repair tiles Amount of damage token removed must coincide with repair tile type. 

**Work tasks:** Create logic which recognizes when robot is on repair tile and remove damage token.

**Demand:** Make map interactive and apply game rules (repair tiles)

**User story:** As a player I want to be able to view where holes are placed.

**Acceptance criteria:** Holes on the map removes a life token from the player. 

**Work tasks:** Create logic which recognizes when robot is placed on hole. (Later also respawn player, this however has not been implemented).

**Demand:** Make map interactive and apply game rules (holes)

**User story:** As a player I want to win the game when my robots visit the flags generated in the appropriate order. 

**Acceptance criteria:** Visiting the flags in the correct order gives the player the win. 

**Work tasks:** Differentiating the flags and ensuring that order of visits counts. 

**Demand:** Provide win condition. 

**User story:** As a player I want the game to be divided into turns and rounds to be able to play the game as intended.

**Acceptance criteria:** Game divided into turn where each player uses action of 1 card. 
Game divided into round where each player has used up program cards. New round is initiated by new cards being handed out. 

**Work tasks:** Ensuring that only one actions is being taken each round. Ensuring that only 5 cards is being played for each round. 

**Demand:** Implementing game logic. 

**User story:** As a player I want the cards to be played according to their priority number. 

**Acceptance criteria:** Higher priority number leads to card being played earlier.

**Work tasks:** Cards are sent to server; server sorts the cards in order and sends the cards to client in correct order. Clients implements card action in the order received by server. 

**Demand:** Implementing game logic


# Meeting summaries

## Meeting summary 08.03 – All attended.
•	During our meeting we decided that Shahez & Rolf would make the presentation on Wednesday. 

•	Tasks until next meeting refactoring, become more used to the code. 

•	Jonas creates more cards. 

•	Rolf starts on creating stages.

## Meeting summary 11.03 – All attended.
•	We used our mildly week session to go through the code so that every member was updated on what had happened with the code. 

•	We did nothing new just went through the code to address some of the weakness with our project structure which was discussed in the mandatory assignment 2 retrospective. 

## Meeting summary 15.03 – All attended.

•	Today’s meeting was quite important. 

•	Created overview of every demand that must be fulfilled before project ending. 

•	Decided on which of these would be done by iteration 3 and which were saved for last iteration.

•	Rolf & Jonas work more on turn system on Wednesday

•	Shahez & Steffen work on edits and implementation of game logic on Tuesday

## Meeting summary 18.03 – All attended.

•	Quick meeting today.

•	Went through developed code and discussed unfinished demands. 

## Meeting summary 22.03 – All attended.

•	Discussed unfinished demands and set up pair programming sessions. 

•	Went through developed code together and group and identified problems and fixed these. 

•	Set up more pair programming sessions.

•	Jonas & Steffen & Shahez – Monday

•	Steffen & Rolf – Tuesday

•	Jonas &Rolf - Wednesday

•	Set goal for being finished with all demands for the next meeting on the 25.3.

## Meeting summary 25.03 – All attended. 

•	Did finishing touches to prepare for hand in.

•	Rolf & Shahez finish up formalities and look over to ensure that everything is handed in.

•	Decided on moving to headless testing mode for the next iteration to avoid having to close the application for every test. 

# Justifications
The primary focus of this iteration has been implementing the turn/round logic. 
This was more complicated and difficult to implement then first expected and therefore took much more time then initially expected. 
Due to this there exists a larger part of code that exists in the pushed work that is yet not actively used on the map. 
They are however used in the tests so that we ensure that they work. 
We believe that this is of no concern due that the logic is developed and is awaiting implementation in the next iteration. 
The code concerning holes and repair sites are not yet used actively in the map, but the logic behind these actions exists in the code. 
The flag logic is implemented and exists within the code yet is not optimized to how the game rules are supposed to work, this will be fixed in the next iteration.

Movement of robot with the keys exists due to testing purposes and will be removed in the next iteration. 
Due to an experienced bug w had to remove textures from card-objects because when used outside of a game class they would crash the application. 
This was fixed by implementing a texture-translator that returns the texture of each card-class-instance. 
We understand that this makes the code less OOP yet decided to do this in order to make the application work. 
