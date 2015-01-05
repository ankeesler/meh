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
    setBackground(Color.WHITE);

    g.setColor(Color.BLACK);
    g.drawOval(25, 25, 25, 25);
  }

}