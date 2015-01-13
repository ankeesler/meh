package core;

import graph.*;
import view.*;

import java.util.*;

/** A controller for a meh view.
 *
 * @author Andrew Keesler
 * @date January 11, 2015
 */
public class MehController implements IGraphViewBuddy {

  private Graph graph = new Graph("New Graph");;
  private MehView view = new MehView(this);
  
  private Map<NodeView, GraphNode> viewsToNodes
    = new HashMap<NodeView, GraphNode>();

  /** Create a new controller.
   */
  public MehController() {
    updateView(null);
  }

  private void updateView(NodeView selected) {
    view.setGraphTitle(graph.name());
    view.setGraphOrder(graph.order());
    view.setGraphEdges(graph.edges());
    
    if (selected != null) {
      GraphNode node = viewsToNodes.get(selected);
      view.setNodeName(node.toString());
      view.setNodeDegree(graph.degree(node));
    }
    
    Debug.println(Debug.DebugChannel.CORE, "graph is now: " + graph);
  }

  @Override
  public void nodeCreated(NodeView nodeView) {
    Debug.println(Debug.DebugChannel.CORE, "nodeViewCreated() : " + nodeView);
    
    // Update model.
    GraphNode<String> newNode = new GraphNode<String>("Node " + nodeView.id());
    viewsToNodes.put(nodeView, newNode);
    graph.add(newNode);
    
    // Update view.
    updateView(nodeView);
  }

  @Override
  public void edgeCreated(NodeView from, NodeView to) {
    Debug.println(Debug.DebugChannel.CORE, "edgeCreated()");
    
    graph.add(viewsToNodes.get(from), viewsToNodes.get(to));
    
    updateView(from);
  }
  
  @Override
  public void nodeTouched(NodeView nodeView) {
    Debug.println(Debug.DebugChannel.CORE, "nodeViewTouched() : " + nodeView);
    
    updateView(nodeView);
  }
}
