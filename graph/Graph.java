package graph;

import java.util.*;

/** The graph. A data backing plus fully functional data structure.
 *  These graphs are directed.
 */
public class Graph {

  // Use a linked hash map so we can keep track of the order in which we
  // added the nodes.
  private Map<GraphNode, Set<GraphNode>> adjList
    = new LinkedHashMap<GraphNode, Set<GraphNode>>();

  private String name;

  // Package API for creating and empty graph with default name.
  Graph() {
    this.name = "New Graph";
  }

  /** Create a new graph with a name.
   */
  public Graph(String name) {
    this.name = name;
  }

  /** Get the name of this graph.
   */
  public String name() {
    return name;
  }

  /** What the order of the graph is.
   */
  public int order() { return adjList.keySet().size(); }

  /** How many edges the graph has.
   */
  public int edges() {
    int edges = 0;
    for (GraphNode node : adjList.keySet())
      edges += adjList.get(node).size();
    return edges;
  }

  /** Get the nodes in the graph.
   */
  public GraphNode[] nodes() {
    return adjList.keySet().toArray(new GraphNode[0]);
  }
  
  /** Get the degree of the graph node, or -1 if the graph
   * does not contain the node.
   */
  public int degree(GraphNode node) {
    if (!adjList.keySet().contains(node))
      return -1;
    
    int degree = 0;
    for (GraphNode otherNode : adjList.keySet())
      if (adjList.get(otherNode).contains(node))
        degree ++;
    return degree;
  }
  
  /** Add a graph node that has no neighbors initially.
   *  Returns true iff the node was successfully added.
   */
  public boolean add(GraphNode node) {
    // Don't re-add any nodes that we already have.
    if (adjList.keySet().contains(node))
      return false;

    adjList.put(node, new HashSet<GraphNode>());

    return true;
  }

  /** Add an edge from node1 to node2.
   *  If either of the nodes were not in the graph, it will add them.
   *  Returns true iff the edge was successfully created.
   */
  public boolean add(GraphNode node1, GraphNode node2) {
    // Add the nodes if necessary.
    if (!adjList.keySet().contains(node1)) add(node1);
    if (!adjList.keySet().contains(node2)) add(node2);

    // Create the edge from node1 to node2, if it isn't already created.
    return adjList.get(node1).add(node2);
  }

  /** Remove a node from the graph.
   *  Returns true iff the node was removed.
   */
  public boolean remove(GraphNode node) {
    if (!adjList.keySet().contains(node))
      return false;

    // Remove the node.
    adjList.remove(node);

    // Remove the node from any other neighborhoods.
    for (GraphNode from : adjList.keySet())
      adjList.get(from).remove(node);
    
    return true;
  }

  /** Remove an edge from node1 to node2.
   *  Returns true iff the edge was successfully removed.
   */
  public boolean remove(GraphNode node1, GraphNode node2) {
    if (!adjList.keySet().contains(node1)
        || !adjList.keySet().contains(node2))
      return false;

    // Remove node2 from node1's neighborhood.
    adjList.get(node1).remove(node2);

    return true;
  }

  /** Returns an array of the nodes in the neighborhood of the
   *  passed node. If null is returned, then the node is not in
   *  the graph.
   */
  public GraphNode[] neighborhood(GraphNode node) {
    return (adjList.keySet().contains(node)
            ? adjList.get(node).toArray(new GraphNode[0])
            : null);
  }

  // Helper method for below public API.
  private boolean path(GraphNode node1,
                       GraphNode node2,
                       List<GraphNode> currentPath) {
    // Base case: node1 == node2.
    if (node1 == node2)
      return true;

    // Base case: node2 is in node1's neighborhood.
    if (adjList.get(node1).contains(node2)) {
      currentPath.add(node2);
      return true;
    }

    // Recursion on node1.
    // If the list does not yet contain the nextNode,
    // then see if there is a path from that nextNode to node2.
    for (GraphNode nextNode : adjList.get(node1)) {
      if (!currentPath.contains(nextNode)) {
        currentPath.add(nextNode);
        if (path(nextNode, node2, currentPath)) {
          // We found a path!
          return true;
        } else {
          // We did not find a path with this nextNode.
          currentPath.remove(currentPath.size()-1);
        }
      }
    }

    return false;
  }

  /** Checks to see if there is a path from node1 to node2.
   *  If there is not, this function will return null.
   *  If there is, it will return an array of nodes, including
   *  node1 and node2, that represents the first found path from
   *  node1 to node2.
   */
  public GraphNode[] path(GraphNode node1, GraphNode node2) {
    List<GraphNode> list = new ArrayList<GraphNode>();
    list.add(node1);
    return ((adjList.keySet().contains(node1)
             && adjList.keySet().contains(node2)
             && path(node1, node2, list))
            ? list.toArray(new GraphNode[0])
            : null);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (GraphNode node : adjList.keySet()) {
      builder.append("(node=").append(node).append("):{");
      for (GraphNode neighbor : adjList.get(node))
        builder.append(neighbor).append(",");
      builder.append("}");
    }
    return builder.toString();
  }
}