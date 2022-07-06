# Olteanu Maria-Teona 321CA

## GENERAL INFO
Application simulates annual deliveries over the course of multiple years.
The children are clients, the gifts are products & the distributor is the user.

## IMPLEMENTATION

# MAIN COMPONENTS
DeliveryData
-> Acts as a database that stores the loaded input data.
It can be accessed from everywhere.
The mentioned database contains:
- the current year of the delivery (first year will be 0)
- santa's budget (the overall budget)
- list of children
- list of gifts
- budget unit
- delivery strategy -> the order in which the gift distribution will be done

DeliveryPlanner
-> Acts as a planner that manages deliveries over the years.
The planner will store:
- the simulation's number of years given by the input
- a sequence of callbacks that ensure all delivery data will be updated anually

DeliveryAction
-> Handles the delivery itself.
The class above contains:
- a sequence of callbacks that set up all data(children's status, averagescore, 
budget unit, children's assigned budgets) neccessary for an annual delivery
- a list of observers (will be notified after every executed delivery)

ScoreStrategy
-> Used to compute a child's average score based on child's status.
Implementations:
- BabyScoreStrategy -> average score = 10
- KidScoreStrategy -> average score = average of previous scores  
- TeenScoreStrategy -> average score = weighted average of previous scores
- YoungAdultStrategy -> average score will not be computed

ScoreStrategyFactory
-> Creates ScoreStrategy objects based on the child's status.

DeliveryStrategy
-> Used to deliver/distribute gifts to all children in acertain order.
Implementation:
- IdDeliveryStrategy -> deliver gifts based on children's ids
- NiceScoreDeliveryStrategy -> deliver gifts based on children's scores
- NiceScoreCityDeliveryStrategy -> deliver gifts based on the cities score ranking

DeliveryStrategyFactory
-> Creates DeliveryStrategy objects based the delivery's strategy type.

Standard delivery details:
Get assigned budget for the child.
For every category in the child's gift preferences get all gifts filtered by 
that category sorted in ascending oreder by price.
Get the cheapest gift in stock from the filtered list of gifts.
If the gift's price is lower than the budget store the gift in the child's 
received gifts list.
Update gift quantity & child' assigned budget.

NiceScore
-> Builds an object used to store a child's average score and its optional param.
Contains:
- nice score value
- bonus value 

# FLOW
Each year we execute the planned delivery. (first year -> round 0)
After a year has passed we update the delivery database for a new delivery. 
If all expected deliveries were executed then simultion is over.

Before we execute a delivery we invoke the set-up callbacks.
Set-up steps:
1) Set the status of every child (e.g. BABY) -> ChildrenStatusHandler
2) Compute & set the average score of every child -> AverageScoreHandler
3) Compute & set the budget unit -> BudgetUnitHandler
4) Compute & set the budget assigned for every child -> ChildBudgetHandler

After all callbacks were called we execute the delivery, which means we 
distribute the gifts to all the children in the delivery database.

To do a delivery we have to determine the gifts ditribution strategy.
Then we execute the delivery strategy.

When the annual delivery is executed the observers will be 
notified (YellowElfObserver & DeliveryDataAggregator).

YellowElfObserver gives a gift to the children that haven't received any after
the lastest finished delivery.

DeliveryDataAggregator stores the report of the just finished delivery in order
to be printed in the output file after the simulation is done.
The delivery report contains a list of DeliveryReportMessages (child data).

After we are done with a delivery we check if the plan of deliveries is over.
If not, after a year has passed we have to update the delivery database.
We invoke all annual updater callbacks.
Update steps:
1) Update (sort) children's id order (ascending)
2) Update the age of every child -> ChildrenAgeUpdater
3) Update the delivery data using the given input changes -> InputDataUpdater 	
4) Update (clear) the received gifts of every child ->ChildrenGiftHistoryEraser
5) Update the current date (year) -> CurrentDateUpdater
Only now, the new delivery can be executed

When the simulation is finished all annual delivery reports will be printed in
the output file.

*NOTE*
Implementation uses a reflection based approach to factory design pattern.
Which is impacted by the checker tool. 
Vmchecker doesn't recognize uninstantiated classes at runtime.
It can't load and find those classes definitions.(ClassNotFoundException)
Therefore, implementation using reflection doesn't work without some additions.

On local machine the code works without the following adaptation.

We added load methods for classes that were uninstatiated before runtime.
This way, the checker tool identifies the reflection retrieved classes.
