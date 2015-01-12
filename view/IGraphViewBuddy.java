package view;

/** An interface describing how the graph view will tell a controller
 *  what the user does with the graph UI.
 *
 * These methods are highly logical. The graph view is very smart
 * (it went to Davidson College) and it will handle the necessary UI logic.
 *
 * @author Andrew Keesler
 * @date January 11, 2015
 */
public interface IGraphViewBuddy {

  /** Called when someone created a node.
   */
  public void nodeCreated();

  /** Called when someone created an edge.
   */
  public void edgeCreated();
  
  /** Called when a node is touched by a user.
   */
  public void nodeTouched();
}