package core;

import graph.*;
import view.*;

/** A controller for a meh view.
 *
 * @author Andrew Keesler
 * @date January 11, 2015
 */
public class MehController {

  private Graph graph;
  private MehView view;

  /** Create a new controller.
   */
  public MehController() {
    graph = new Graph("New Graph");
    view = new MehView();
    updateView();
  }

  private void updateView() {
    view.setGraphTitle(graph.name());
    view.setGraphOrder(graph.order());
    view.setGraphEdges(graph.edges());
  }
}
