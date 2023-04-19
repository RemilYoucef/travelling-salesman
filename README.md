# Traveling Salesman Problem

The travelling salesman problem (TSP) is a well-known problem in computer science and optimization theory. The problem can be stated as follows: given a list of cities and the distances between them, find the shortest possible route that visits each city exactly once and returns to the starting city. The TSP has important applications in logistics, transportation planning, and routing optimization, among other fields. Many real-world problems can be formulated as variations of the TSP, and finding good solutions to these problems can lead to significant cost savings and efficiency improvements.


# Nearest Neighbor-based Solution

1. Choose an arbitrary starting city.
2. Mark the starting city as visited.
3. While there are unvisited cities:
    a. Find the nearest unvisited city to the current city.
    b. Add the nearest city to the tour and mark it as visited.
    c. Set the current city to be the nearest city.
4. Return to the starting city to complete the tour.

# Kruskal-based Solution

1. Sort the edges in non-decreasing order of their weights.
2. Initialize an empty graph for the MST.
3. Repeat steps 4-5 until all vertices are in the MST.
4. Select the next smallest edge that does not form a cycle with the edges already in the MST.
5. Add the edge to the MST.

Note: A disjoint-set data structure is commonly used to efficiently implement step 4.


# A JavaFX software tool
<img  alt="" src="./tool.png" style="width: 100%;" />
