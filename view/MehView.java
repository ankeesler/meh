package view;

import java.awt.*;
import javax.swing.*;

/** The main GUI for the meh project.
 *
 * @author Andrew Keesler
 * @date January 4, 2015
 */
public class MehView extends JFrame {

  public final static int WINDOW_WIDTH = 400;
  public final static int WINDOW_HEIGHT = 400;

  private JPanel leftPanel;
  private JLabel graphTitle;
  private JLabel graphOrder;
  private JLabel graphEdges;

  private JPanel rightPanel;
  private JLabel nodeNameLabel;
  private JTextField nodeName;
  private JLabel nodeDegree;

  private GraphView graphView;

  public MehView() {
    super("Meh");

    // Set size, layout, visibility, close operation.
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    setLayout(new BorderLayout());
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    // Left panel.
    add((leftPanel = new JPanel(new GridLayout(3,1))), BorderLayout.LINE_START);
    leftPanel.setBackground(Color.BLUE); // debug
    leftPanel.add((graphTitle = new JLabel("Graph G")));
    leftPanel.add((graphOrder = new JLabel("Order: 0")));
    leftPanel.add((graphEdges = new JLabel("Edges: 0")));

    // Right panel.
    add((rightPanel = new JPanel(new FlowLayout())), BorderLayout.LINE_END);
    leftPanel.setBackground(Color.RED); // debug
    rightPanel.add((nodeNameLabel = new JLabel("Node name: ")));
    rightPanel.add((nodeName = new JTextField(50)));
    rightPanel.add((nodeDegree = new JLabel("Node degree: 0")));

    // The graph view (in the middle).
    add((graphView = new GraphView()), BorderLayout.CENTER);
  }

}