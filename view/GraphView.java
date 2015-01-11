package view;

import java.awt.*;
import javax.swing.*;

/** An interactable graph builder 2-D graphics thingy.
 *
 * @author Andrew Keesler
 * @date January 4, 2015
 */
public class GraphView extends JPanel {

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    Dimension size = getSize();

    g2.setColor(Color.WHITE);
    g2.drawRect(0, 0, size.width, size.height);
    g2.fillRect(0, 0, size.width, size.height);
  }

}