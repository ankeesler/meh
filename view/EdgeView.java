package view;

import java.awt.*;
import java.awt.geom.*;

/** How a view looks on the screen. */
public class EdgeView extends Line2D.Double {
  
  public static final Color DEFAULT_COLOR = Color.BLACK;
  public static final Color CREATION_COLOR = Color.GRAY;

  private Color color = CREATION_COLOR;
  
  /** Create an edge view with a start and an end. */
  public EdgeView(double startX, double startY, double endX, double endY) {
    super(startX, startY, endX, endY);
  }
  
  /** Get the color. */
  public Color color() {
    return color;
  }
  
  /** Mark the end of the edge and whether or not this is really the end. */
  public void setEnd(double endX, double endY, boolean reallyEnd) {
    this.x2 = endX;
    this.y2 = endY;
    if (reallyEnd)
      color = DEFAULT_COLOR;
  }
}