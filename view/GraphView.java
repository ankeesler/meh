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

  // The node object that the graph view will draw.
  private static class NodeView extends Ellipse2D.Double {
    private Color color;

    private static final Color DEFAULT_COLOR = Color.BLACK;
    private static final Color SELECTED_COLOR = Color.RED;
    
    // Create a node view with a center and a radius.
    public NodeView(double centerX, double centerY, double radius) {
      super(centerX - radius, centerY - radius, radius*2, radius*2);
      this.color = DEFAULT_COLOR;
    }

    // Set whether or not the node is selected.
    public void setSelected(boolean yes) {
      color = (yes ? SELECTED_COLOR : DEFAULT_COLOR);
    }

    // Get the color.
    public Color color() { return color; }
  }

  private Set<NodeView> nodeViews = new HashSet<NodeView>();

  private IGraphViewBuddy buddy;

  private double defaultRadius = 30;

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

    // Clear the background.
    g2.setColor(Color.WHITE);
    g2.drawRect(0, 0, size.width, size.height);
    g2.fillRect(0, 0, size.width, size.height);

    // Draw all of the node views.
    for (NodeView nodeView : nodeViews) {
      g2.setColor(nodeView.color);
      g2.draw(nodeView);
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
        buddy.nodeTouched();
        repaint();
        return;
      }
    }

    // Well the user must want to create a node.
    NodeView nv = new NodeView(e.getX(), e.getY(), defaultRadius);
    unselectAllNodeViewsBut(nv);
    nodeViews.add(nv);
    buddy.nodeCreated();
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
  public void mouseReleased(MouseEvent e) {}
  
  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {}

  @Override
  public void mouseDragged(MouseEvent e) {}

  @Override
  public void mouseMoved(MouseEvent e) {}
}