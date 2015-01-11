package graph;

/** A node on a graph.
 *
 * It holds a piece of data of type T.
 */
public class GraphNode<T> {
  
  private T data;
  
  /** Create a graph node with data.
    */
  public GraphNode(T data) {
    setData(data);
  }
  
  /** Get the data of this node.
    */
  public T data() { return this.data; }
  
  /** Set the data of this node.
    */
  public void setData(T data) { this.data = data; }
  
  @Override
  public String toString() {
    return (data == null ? "null" : data.toString());
  }
  
  @Override
  public boolean equals(Object other) {
    return (other instanceof GraphNode
            && ((GraphNode)other).data == this.data);
  }
  
  @Override
  public int hashCode() {
    return (data == null ? 33 : data.hashCode());
  }
}