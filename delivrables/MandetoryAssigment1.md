## Retrospective summary 
During our retrospective discussions we found out that there were many aspects of the collaboration that had gone well. 
Among these were the fact that we had throughout the weeks found time to meet up when we could and that those meets ups were productive. 
Furthermore, team leader has seen that all members have been dutiful about their tasks and the assignments have been done to the proposed time. 
We believe that much of this success was due to productive talks in the start of the collaboration. 
We talked much of our abilities and weaknesses and therefore had a good understanding of which roles would be best assigned by to who. 
Due to these positive experiences, we feel that we have reached the expected progress at this stage of the assignment. 
The product fulfils the demands that were set for this iteration. We believe that in the next iteration we will include the rest of the points on the MVP list. 
However, some improvements can be made on how we apply OOP principles, and we wish therefore to be better at this in the iteration. 
Furthermore, we did not experience code with me as satisfactory and would therefore look for other pair programming tools. 
Another change we wish to do in the developing of the next iteration is making it so that every member must accept a certain change to the repository. 
We wish to do this to encourage that everyone updates themselves regularly on the code that is being written and therefore 
have better overview of the project and its development. 

# Competencies and Roles 
Shahez – Team leader – Primary responsibility ensure that the work is being done and fix the administrative tasks around the task. Competencies: Teamwork, leadership.

Jonas. Competencies:  Naturally inclined towards coding, Java, Python, good communication skills. 

Steffen. Competencies:  Computer security, python. 

Rolf. Competencies: Proficient in several types of languages including python and Java. Good understanding of the practical side of servers. 

In regard to the roles, we wanted to become better known with each other before assigning any specific practical roles in regard to the coding. Therefore, everyone has some responsibility for everything. During the first iteration this has worked well but will be put up for revaluation for the next iteration. 

# Meeting summary – 08.02.2021
In this meeting we firstly talked about what code we were going to finish and push in the first obligatory assignment. We addressed which tasks and which goals we were supposed to have reached during this week. After the goals were identified we informed our other members of what we had been doing since our last meeting on Thursday 4.02.2021 and the progress. Therefore, we had an overview of what we had been doing, what was done, and what we must do to achieve the demands set by the obligatory assignment 1. We updated the project board on GitHub in according to our current progress in the project. 
Beyond the first obligatory assignment, we agreed upon how we wanted to approach the multiplayer problem in the future. Even though we had some code setup for this already, we agreed on not pushing this code to Github during this interval. We chose to rather focus more on the tasks at hand ensure that we have solved these optimally before moving forward. 

# Meeting summary – 11.02.2021
In our end of the week meeting, we firstly created an overview over our progression. We mapped what had been done, what needed to be done, and updated the project board accordingly. After updating each other on what had been done and coming on board about our current situation we started on this meetings agenda. During our meeting we talked about the retrospective on our assignment, what had been gone well, and what had not been going as well. Furthermore, we touched upon the next assignment and what our expectations and thoughts were in regard to that. 


## User stories
The application has the purpose of recreating the boardgame Robot Rally in a digital form. Robot Rally is a board game where several players are in a race where the win condition is visiting certain flag points on the map. To achieve this, the players have to “program” their robots to take certain actions with the help of cards which they are dealt during the start of every round. The application will recreate these aspects of the game to provide the players with the same joy as the physical version.

To achieve these goals, we have derived the following user stories.

**User stories:** As a player I need to be able to have a map to play on:

**Acceptance criteria:** The game map must be generated. 

**Work tasks:** An appropriate game map must be generated using Tiled.

**Demand:** The user story fulfils the demand to view the game board. 

**User stories:** As a player I need to be able to view my, and the opponent’s robots on the game board:

**Acceptance criteria:** The robot’s position on the map must be clear to see.

**Work tasks:** Robots must be added to the map and be easy to distinguish. 

**Demand:** The user story fulfils the demand to view the robots on the game board. 

**User stories:** As a player I need to be able to move the robots:

**Acceptance criteria:** The robot must move as a response to a command.

**Work tasks:** Make the robots change position as a response to key commands. 

**Demand:** The user story fulfils the demand to move the robots for testing purposes. 

**User stories:** As a player I need to make progress towards win condition when visiting flags:

**Acceptance criteria:** The players progression towards winning increases as a flag is visited in the right order. 

**Work tasks:** Create flag points which are distinguished and affect the players status when visited in the right order. Make sure that flag points that are visited in wrong order does not affect progression. 

**Demand:** The user story fulfils the demand for robots being able to visit flags. 

**User stories:** As a player I need to win the game when visiting all the flags on the map in the correct order: 

**Acceptance criteria:** The player wins the game if last flag is visited.  

**Work tasks:** Specify the win condition and make the flag being able to visit only when all the other flags are already visited. 

**Demand:** The user story fulfils the demand for robot wining when visiting the flag. 
