package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;

import java.util.*;

import core.Debug;

/** An interactable graph builder 2-D graphics thingy.
 *
 * This class is responsible for translating the UI action into
 * logical dynamic events on a graph and telling a listener.
 *
 * @author Andrew Keesler
 * @date January 4, 2015
 */
public class GraphView
  extends JPanel
  implements MouseListener, MouseWheelListener, MouseMotionListener {

  private Set<NodeView> nodeViews = new HashSet<NodeView>();
  private Set<EdgeView> edgeViews = new HashSet<EdgeView>();

  private IGraphViewBuddy buddy;

  private double defaultRadius = 30;
  
  private Map<EdgeView, NodeView> startingNodeViews
    = new HashMap<EdgeView, NodeView>();
  private Collection<EdgeView> creatingEdges
      = new ArrayList<EdgeView>();

  /** Create a new graph view.
   */
  public GraphView(IGraphViewBuddy buddy) {
    this.buddy = buddy;

    addMouseListener(this);
    addMouseWheelListener(this);
    addMouseMotionListener(this);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    Dimension size = getSize();

    // Set the stroke.
    g2.setStroke(new BasicStroke(5));

    // Clear the background.
    g2.setColor(Color.WHITE);
    g2.drawRect(0, 0, size.width, size.height);
    g2.fillRect(0, 0, size.width, size.height);

    // Draw all of the node views.
    for (NodeView nodeView : nodeViews) {
      g2.setColor(nodeView.color());
      g2.draw(nodeView);
    }
    
    // Draw all of the edge views.
    for (EdgeView ev : creatingEdges) {
      g2.setColor(ev.color());
      g2.draw(ev);
    }
    for (EdgeView ev : edgeViews) {
      g2.setColor(ev.color());
      g2.draw(ev);
    }
  }

  @Override
  public void update(Graphics g) {
    Debug.println("update()");
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    // Is the location within any of the nodes?
    for (NodeView nodeView : nodeViews) {
      if (nodeView.contains(e.getX(), e.getY())) {
        unselectAllNodeViewsBut(nodeView);
        buddy.nodeTouched(nodeView);
        repaint();
        return;
      }
    }

    // Well the user must want to create a node.
    NodeView nv = new NodeView(e.getX(), e.getY(), defaultRadius);
    unselectAllNodeViewsBut(nv);
    nodeViews.add(nv);
    buddy.nodeCreated(nv);
    repaint();
  }

  private void unselectAllNodeViewsBut(NodeView nv) {
    for (NodeView nodeView : nodeViews)
      nodeView.setSelected(false);
    nv.setSelected(true);
  }

  @Override
  public void mouseEntered(MouseEvent e) {}
  
  @Override
  public void mouseExited(MouseEvent e) {}

  @Override
  public void mousePressed(MouseEvent e) {}

  @Override
  public void mouseReleased(MouseEvent e) {
    // If you are on top of a node, make the creatingEdges permanent.
    // Regardless, delete the creating edges.
    if (creatingEdges.size() != 0) {
      for (NodeView nv : nodeViews) {
        if (nv.contains(e.getX(), e.getY())) {
          for (EdgeView ev : creatingEdges) {
            ev.setEnd(nv.center().x, nv.center().y, true); // really the end
            edgeViews.add(ev);
            buddy.edgeCreated(startingNodeViews.get(ev), nv);
          }
        }
      }
    }
    creatingEdges.clear();
    repaint();
  }
  
  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {}

  @Override
  public void mouseDragged(MouseEvent e) {
    if (creatingEdges.size() == 0) {
      // Create the edges.
      for (NodeView nodeView : nodeViews) {
        if (nodeView.isSelected()) {
          EdgeView ev = new EdgeView(nodeView.center().x,
                                         nodeView.center().y,
                                         e.getX(),
                                         e.getY());
          creatingEdges.add(ev);
          startingNodeViews.put(ev, nodeView);
        }
      }
    } else {
      // Update the edges.
      for (EdgeView edgeView : creatingEdges) {
        edgeView.setEnd(e.getX(), e.getY(), false); // not the end
      }
    }
    
    repaint();
  }

  @Override
  public void mouseMoved(MouseEvent e) {}
}