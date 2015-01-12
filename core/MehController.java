package core;

import graph.*;
import view.*;

/** A controller for a meh view.
 *
 * @author Andrew Keesler
 * @date January 11, 2015
 */
public class MehController implements IGraphViewBuddy {

  private Graph graph;
  private MehView view;

  /** Create a new controller.
   */
  public MehController() {
    graph = new Graph("New Graph");
    view = new MehView(this);
    updateView();
  }

  private void updateView() {
    view.setGraphTitle(graph.name());
    view.setGraphOrder(graph.order());
    view.setGraphEdges(graph.edges());
  }

  @Override
  public void nodeCreated() {
    Debug.println("nodeCreated()");
  }

  @Override
  public void edgeCreated() {
    Debug.println("edgeCreated()");
  }
  
  @Override
  public void nodeTouched() {
    Debug.println("nodeTouched()");
  }
}
