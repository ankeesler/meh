package view;

import java.awt.*;
import java.awt.geom.*;

/** A view of a simple node.
 *  Provides an id for itself to make it easier to track.
 *
 * @author Andrew Keesler
 * @date Jaunary 12, 2015
 */
public class NodeView extends Ellipse2D.Double {
  private static int NEXT_ID = 0;
  
  public static final Color DEFAULT_COLOR = Color.BLACK;
  public static final Color SELECTED_COLOR = Color.RED;
  
  private Color color = DEFAULT_COLOR;
  private int id = ++NEXT_ID;
  
  /** Create a new node view centered at a point with a radius. */
  public NodeView(double centerX, double centerY, double radius) {
    super(centerX-radius, centerY-radius, radius*2, radius*2);
  }

  /** Set whether or not the node is selected. */
  public void setSelected(boolean yes) {
    color = (yes ? SELECTED_COLOR : DEFAULT_COLOR);
  }
  
  /** Returns whether or not the node view is selected. */
  public boolean isSelected() {
    return color == SELECTED_COLOR;
  }

  /** Get the color. */
  public Color color() {
    return color;
  }
  
  /** Get the id. */
  public int id() {
    return id;
  }
  
  /** Get the point at the center of the node view. */
  public Point2D.Double center() {
    return new Point2D.Double(this.x+(this.width/2), this.y+(this.width/2));
  }
  
  @Override
  public String toString() {
    return "NodeView(" + id + ")";
  }
  
  @Override
  public boolean equals(Object other) {
    return (other instanceof NodeView
            ? ((NodeView)other).id == this.id
            : false);
  }
  
  @Override
  public int hashCode() {
    return id;
  }
}
