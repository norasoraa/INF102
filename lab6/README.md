# Lab6 - Directed Graphs
In this lab we will be looking at optimizing our flight trip with respect to cost of plane tickets.

## Scenario
Pål is traveling around Norway to see the sights. He is on a strict budget and wants to save as much money as possible on the plane tickets, but at the same time he does not want to spend hours and hours on layovers. He will accept a certain amount of stops between flights, but if there are too many he would rather spend a bit more money on more expensive tickets to get to his destination earlier.

## Task 1 - Construct a directed graph
Given a list of flights construct a weighted graph using `WeightedGraph`. This is the same graph datastructure we have seen in the lecture, but is now directed instead of undirected.

**TODO: Implement the `CheapestFlight::constructGraph`.**

```java
@Override
public WeightedGraph<City, Integer> constructGraph(List<Flight> flights) {
    throw new UnsupportedOperationException();
    // Implement me :)
}
```

✅ Run `CheapestFlightTest` to check your solution. The task is passed if `constructGraph1` and `constructGraph2` pass.


## Task 2 - Find the cheapest flights with `k` stops
Given a starting city and a destination city, find the cheapest set of flights from start to destination, that has a maximum of `k` stops.
For instance, Pål wants to travel from Oslo to Bergen. The flights available are:
 * Oslo --> Trondheim, 100kr
 * Trondheim --> Bergen, 100kr
 * Oslo --> Bergen, 500kr

If Pål allows for 1 stop (`k=1`) then the total price will be 200kr. He then flies Oslo --> Trondheim --> Bergen. If he is in a hurry and does not allow any stops (`k=0`) then the total price will be 500kr. He then flies Oslo --> Bergen.


**TODO: Implement the `CheapestFlight::findCheapestFlights`.**
```java
@Override
public int findCheapestFlights(List<Flight> flights, City start, City destination, int nMaxStops) {
    throw new UnsupportedOperationException();
    // Implement me :)
}
```

✅ Run `CheapestFlightTest` to check your solution. The task, and the lab, is passed if all tests in `CheapestFlightTest` pass.


