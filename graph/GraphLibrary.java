
package graph;


/** A bunch of standard graphs.
 */
public class GraphLibrary {

  /** Make an empty graph.
   */
  public static Graph EMPTY() { return new Graph(); }

  /** Make the singleton graph.
   */
  public static Graph SINGLETON() {
    Graph g = new Graph();
    g.add(new GraphNode<Integer>(1));
    return g;
  }

  /** Make a P_n graph, like a path of n nodes.
   */
  public static Graph P(int n) {
    Graph g = new Graph();

    // Corner case: n == 0.
    if (n == 0)
      return EMPTY();
 
    // Corner case: n == 1.
    if (n == 1)
      return SINGLETON();

    for (int i = 0; i < n-1; i ++)
      g.add(new GraphNode<Integer>(i), new GraphNode<Integer>(i+1));

    return g;
  }

  /** Make a C_n graph, like a cycle of n nodes.
   */
  public static Graph C(int n) {
    Graph g;

    // Corner case: n == 0.
    if (n == 0)
      return EMPTY();
 
    // Corner case: n == 1.
    if (n == 1)
      return SINGLETON();

    g = P(n);
    g.add(g.nodes()[n-1], g.nodes()[0]);

    return g;
  }

  /** Make a K_n graph, like a complete graph with n nodes.
   */
  public static Graph K(int n) {
    Graph g;
    
    // Corner case: n == 0.
    if (n == 0)
      return EMPTY();
 
    // Corner case: n == 1.
    if (n == 1)
      return SINGLETON();
    
    // Corner case: n == 2.
    if (n == 2)
      return P(2);

    g = P(n);
    for (GraphNode node1 : g.nodes())
      for (GraphNode node2 : g.nodes())
        g.add(node1, node2);

    return g;
  }
}