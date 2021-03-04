## Mandatory Assignment 2 - Retrospective
# Project and project structure
Throughout the second iteration of the assignment the group members roles have taken more shape. In our first iteration we did not assign many roles because we did not know how the dynamic within the group would develop and which member would fit each role. We did not see it as something problematic since we managed to finish the assignment and fulfil all the demands that were set for us. As the assignment became more comprehensive however, and we needed more specialization in regards to which tasks that had to be done, more roles took place. 
Shahez was assigned as team leader and continued this role. The team leader role consists of ensuring that we deliver the product that is being asked of us and that this is being done in the best way. Team leader is often involved in coding when new and important additions are being added, yet otherwise has the responsibility to do the administrative work that is being required. Rolf being the best programmer on the group took the role of head programmer and has the primary responsibility towards developing the product. Therefore, he is mostly focused on solving the hard product development problems that the group encounters during development. Primary responsibilities in regard to game logic and how to develop the game to best achieve the desired product is given to Jonas. He and Rolf cooperate a lot as they combine their coding skills and understanding of the developing product to create our product. In terms of developing and writing new code it is Rolf and Jonas that is creating most output. Steffen has the primary responsibility to create the tests and ensure that the program is running as intended. When not focusing on tests he is aiding Rolf & Jonas to further develop the product. 
Our communication is quite good, even though our collaboration is being done throughout Discord we have established a good communicative pattern. We have almost daily contact on our own discord server throughout the working week regarding our project. There are rarely any cases where we talk over each other when discussing problems. We do however at times come across disagreements, but they often solve themselves out naturally after exchange of opinions towards the subject. We have weekly meetings outside of the mandatory meetings on Monday and at times when there is higher pressure to get tasks done, we meet even more frequently. Our meetings are categorized by staying focused on our tasks, yet we keep a friendly tone, often due to the fact that we start the meetings with some socializing. After the initial socializing we often start on going over what we are supposed to do today and keep focused on doing this until the meeting is over. It is the impression of the team members that communication between members is working quite fine as we have found a comfortable and productive balance between friendly chatter and productive discussion. 

# Project method
Our software development methodology is based on the principles of extreme programming. The most important activity that define our development methodology is pair programming and our somewhat daily meetings. The group members work alone on different parts of the project and have differing primary responsibilities, yet there is never long time between our main activities. By having our regular meetings, we minimize the information asymmetry that can occur due to working alone. Due to this, the intention is that we rarely come across the situation where additions have been done to the project that are not being understand by the other members or that the additions are faulty. Furthermore, it has also been throughout these meetings that we do assessments regarding increasing the amount of pair programming activities or other such tasks to minimize the risk of not knowing enough. After a certain addition is being done or a sub-goal is being met, we turn our attention to trying to refactor and improve the quality of the code. At the end of iteration when all the goals have been met, we perform acceptance tests to verify that the most essential part of the project is functioning as intended. Even though we have been quite satisfied with how our project methods work in terms of the meeting structure and the activities, we have several rooms for improvement. We believe that we can become even better at the test part and try to include more of test-driven development in our approach. Furthermore, as the project has become more comprehensive the group has become extra reliant on Rolf and Jonas doing most of the heavy lifting in terms of coding the main part of the project. This development might not be in our favour in the long haul and therefore the rest of the group members must become more pro-active in terms of adding to the code base. Even though they have focused their time and effort on other part of the project, the high degree of specialization might lead to problems down the road. Therefore, we believe that this development must try to be corrected somewhat in the next iteration. 
Points of improvement:
-	Redistribute code work more evenly throughout the members so that we do not fall out of the loop.
-	Work against the high degree of specialization within the group
-	More pair programming.

## Meeting summaries
# Meeting summary – 15.2 – All present
The first meeting after the first product was delivered was used to firstly create an overview of what we had done and what we are supposed to do until the next delivery. We dedicated much time to discuss what we thought of the process, which lead to the previous product and its positives and negatives. After discussing the teamwork and organizing associated with the product we went on to discuss the more technical parts of the product. We underlined the parts of the product which needed improvement and how we should approach the new features we wanted to add. This led us to updating the project board according to our agreed plan of action and dividing up these tasks among ourselves. 
# Meeting summary – 18.2 – All present
On our second meeting on the second iteration of the product we started of with going through what work had been done since the last meeting. After updating each other on our progress we decided that we would not write much more code until the next meeting. We would rather focus more on quality rather than quantity, therefore until the next meeting the focus would be on improving existing code. However, we wanted to create more tests and therefore we assigned one group member to do this. We adjusted the project board accordingly and made plans for the upcoming week and what we wanted to achieve. 
# Meeting summary – 22.2 – All present
The first meeting of the week started of with going through the server code which had been developed. The card class as in focus during this meeting and therefore we spent most of the time discussing how it should be developed. We started with the development of the class and divided up some further tasks that we wanted to finish throughout the week. 
# Meeting summary - 25.02 – All present
We continued the talk on how best developing the card class and worked on this. Furthermore, we started the work on refactoring our code. It was during this meeting that we put effort into understanding what feedback we had gotten in regards to the first mandatory assignment and adjust our code accordingly. 
# Meeting summary – 01.03 – All present 
Continued developing the card class and went into further deep dive regarding the class diagram. Much of the meeting was dedicated to pair programming the card class to fulfil the demands that were set by MVP for the second iteration. Set up a new meeting for tomorrow to check up on current tasks. 
# Meeting summary - 02.03 – All present
During this meeting Jonas & Rolf worked on refactoring the code to make it clearer and tidier according to the feedback that we received form the first iteration. Steffen started to work more on developing further tests for the code. Shahez worked on the reports regarding project structure. Furthermore, we set plans on finishing the card class until next meeting and delve into how to best illustrate the cards. However, how this should be done would be decided on the new meeting on 4.3 in two days.
# Meeting summary – 04.03 – All present
Discussed the retrospective and talked about what we should try to do better for the next iteration. Rest of the meeting was being spent on how to figure out how to present the cards in the best way for the player and finish up the project and prepare to hand in the assignment. 


## User stories:
For the final part of the MVP, we must fulfil the following demands. 
1.	Spille fra flere maskiner (vise brikker for alle spillere, flytte brikker for alle spillere)
2.	Dele ut kort
3.	Velge ut 5 kort
4.	Bevege robot ut fra valgte kort


To achieve this, we have conducted the following user stories.

**User story:** As a player I want to be able to play against other human players.

**Acceptance criteria:** All players sprites that play must show on the map. If a player moves, that movement must be visible by other players. After a turn, the game state must be equal for all players. 

**Work tasks:** Set up a server client that connects different players. Set up so that the necessary packets and information is being sent so that the game state is constantly updated.  

**Demand:** Play from different machines (game state must be updated and equal for both players)

**User story:** As a player I want to be given cards to view my possible actions. 

**Acceptance criteria:** All players must be given the right number of cards.

**Work tasks:** We will firstly create some card classes that consist of the different types of actions that a given card will take. These will be based on single responsibility, therefore, there might be many card classes. We believe however that this is positive since it will give an easy overview of the code. These cards must then be given to the players; therefore, the player must be able to have a way to “hold” the cards. This will be done in the form of a list where the cards are stored. 

**Demand:** Hand out cards

**User story:** As a player I want to choose cards to program my robot to take actions. 

**Acceptance criteria:** Players must pick 5 cards from the set of cards they hold. The chosen cards must be the ones that are added to the “hand.

**Work tasks:** From the set of cards available the player must be able to pick 5 cards. This will be done by having a GUI where the player character can pick 5 of the 9 cards that is available to him.

**Demand:** Pick 5 cards from the set of cards available

**User story:** As a player I need to be able to view what actions different cards will cause.

**Acceptance criteria:** Player must be able to distinguish cards that are different. 

**Work tasks:** The cards and their description must be presented to the player. 

**Demand:** Pick 5 cards from the set of cards available

**User story:** As a player I want my robot to take actions according to the given card.

**Acceptance criteria:** The robot’s actions coincide with the card’s description. The robot’s action must match the set of cards chosen. 

**Work tasks:** Loop through the players hand and call the actions that are described by each card.  

**Demand:** Move the robot according to the chosen cards. 
