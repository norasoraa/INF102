# Answer File - Semester 2
# Description of each Implementation
Briefly describe your implementation of the different methods. What was your idea and how did you execute it? If there were any problems and/or failed implementations please add a description.

## Task 1 - mst
My idea for this task was to use Prim's algorithm. I implemented a help method that generates the minimum spanning tree from a given start node. I used a priority queue to sort the edges based on the weight of each edge.  Start with the first node in the graph and find all of the edges from this node and put them in the toSearch set. Implemented a help method for this operation to avoid duplicate code of adding neighbour edges. Inside the while-loop, remove the smallest edge in the set and check if the edge will create a cycle. If not, determine what node from the edge you should use and find the edges from this node and add them to the set. Add the edge to the ArrayList. After the while-loop is done, return the ArrayList with all the edges in the minimum spanning tree.

## Task 2 - lca
My idea for this task was to use a depth first search to find the path to the nodes u and v and then compare the nodes i found in search of the lowest common ancestor. I startet to check if either of the nodes were the root of the tree, in which case it will be the lca. Then performing a depth first search for both of the nodes and stored the paths in stacks. A help method performs a depth first search recursively, where it visits each node and loop trough it's neighbours. If the target node is found, it returns true, if not it goes back one node and continue the search from there. Then after finding the paths to both u and v, for each of the nodes in the path to u, check if the node is in the path to v, and update the lca for each iteration. If the node is not in the path to v, break the loop and return the previous node which is the lca.  

## Task 3 - addRedundant
My idea for this task was to first find the largest subtree and the second largest subtree from the root. Then I wanted to focus on only these two subtrees. First starting with the biggest subtree. I wanted to find the child of the root that had the most children. Then I wanted to go to this node and find the child of this node that had the most children. I wanted to continue this until I reach the bottom of the tree, where the current node did not have any children. Then i wanted to save this node and use it in the edge. I would do exactly the same for the second largest subtree. Finding the node that had the most children for each child in the tree. Then I would end up with two nodes, that should be the last nodes in the subtrees with the most nodes. Then i would create an edge with these two nodes and return it.  


# Runtime Analysis
For each method of the different strategies give a runtime analysis in Big-O notation and a description of why it has this runtime.

**If you have implemented any helper methods you must add these as well.**

* ``mst(WeightedGraph<T, E> g)``: O(mlogn)
    * Getting the first node in the graph is O(1). Then it calls for the mstGenerator() method, which has a runtime of O(mlogn). Therefore the runtime is O(mlogn). 
* ``mstGenerator(WeightedGraph<V, E> g, V startNode)``: O(mlogn)
    * The initialization of the PriorityQueue toSearch, ArrayList mst and HashSet found is O(1). To add an element to the HashSet found is O(1). The call for addEdges() where all the edges from a node is added to toSearch is O(degree*logm).
    Inside the while-loop: Removing the smallest element from the toSearch is O(logm). Checking if the edge creates a cycle is O(1). Finding the next node and adding it to found is O(1). Then looping trough all of the edges from this node and adding them to toSearch by the call for addEdges() is O(degree*logm), followed by O(1) for adding the edge to the mst ArrayList.
    The while loop could have 2m iterations, because it could at maximum visit each edge twice for all of the edges in the graph.  The if sentence will not be executed for every edge and we have that the total time for adding edges is the sum of all the degrees * logm. If we count every edge twice for each node, then the sum of all the degrees will be 2m. Therefore we have O(m+2m*logm) for the while loop. Removing the constants, we have that the total runtime is O(mlogm). If all edges in the graph are connected then we could have O(n^2) edges. Hence we have that m <= n^2 and hence logm <= log(n^2) = 2logn. Therefore we have that the total runtime is O(mlogn).
* ``addEdges(WeightedGraph<V, E> g, V node, PriorityQueue<Edge<V>> toSearch)``: O(degree*logm)
    * Looping trough the edges from a given node and adding them to toSearch is O(degree*logm). Degree is the degree number of the node, how many neighbours the node has. And for each iteration the add() method for a PriorityQueue is O(logm).

* ``lca(Graph<T> g, T root, T u, T v)``: O(n)
    * To check if either of the nodes were the root of the tree, in which case it will be the lca is O(1). Then it calls for the dfs() method for each of the nodes u and v, and stores the paths to them in stacks. This is O(n) for each of the calls, because dfs() is O(n). 
    Inside the for-loop, checking if the node is in the path to v is O(1), and updating the lca is also O(1). The for-loop could have at most n iterations for all of the nodes in the graph. Since both calls for the dfs() method and the iteration trough the path to u is not nested, the total runtime will be the largest runtime of these three. Therefore the total runtime is O(n).
* ``dfs(Graph<V> g, V root, V target)``: O(n)
    * The initialization of the Set visited and Stack path is both O(1). Then it calls for the dfsRecursive() method, which has a runtime of O(n). Therefore the runtime is O(n).
* ``dfsRecursive(Graph<V> g, V root, V target, Set<V> visited, Stack<V> path)``: O(n)
    * Adding a node to the visited set and the path stack is both O(1). Checking if the node is the target node is O(1). Then for each neighbour of the node, we have O(1) for checking if the node is already visited or not, and then the method recursively calls on itself. For each node it calls recursively on itself to check if the neighbour is part of the path to the target node. The for-loop could have m iterations for the number of edges from this node, in this case the neighbour nodes. In worst case it will visit all the nodes in the graph which is n. This gives the for-loop a runtime of O(n+m). If the target was not found within the for-loop, the last node in the path is removed which is O(1).
    Since we can have at most n-1 edges in the graph, we have that n+m = n+n-1 = 2n-1. After removing the constants we have that the total runtime is O(n).

* ``addRedundant(Graph<T> g, T root)``: O(?)
    * *Insert description of why the method has the given runtime*

