package view;

import java.awt.*;
import javax.swing.*;

/** The main GUI for the meh project.
 *
 * @author Andrew Keesler
 * @date January 4, 2015
 */
public class MehView extends JFrame {

  public final static int WINDOW_WIDTH = 800;
  public final static int WINDOW_HEIGHT = 500;

  private JPanel leftPanel;
  private JLabel graphTitle;
  private JLabel graphOrder;
  private JLabel graphEdges;

  private JPanel rightPanel;
  private JLabel nodeNameLabel;
  private JTextField nodeName;
  private JLabel nodeDegree;

  private GraphView graphView;

  /** Create a new meh view.
   */
  public MehView() {
    super("Meh");

    // Set size, location, layout, visibility, close operation.
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    setLocation(200, 200);
    setLayout(new BorderLayout());
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    // Left panel.
    add((leftPanel = new JPanel(new GridLayout(3,1))), BorderLayout.WEST);
    leftPanel.setBackground(Color.BLUE); // debug
    leftPanel.add((graphTitle = new JLabel("Graph G")));
    leftPanel.add((graphOrder = new JLabel("Order: 0")));
    leftPanel.add((graphEdges = new JLabel("Edges: 0")));

    // Bottom panel.
    add((rightPanel = new JPanel(new FlowLayout())), BorderLayout.SOUTH);
    leftPanel.setBackground(Color.RED); // debug
    rightPanel.add((nodeNameLabel = new JLabel("Node name: ")));
    rightPanel.add((nodeName = new JTextField(50)));
    rightPanel.add((nodeDegree = new JLabel("Node degree: 0")));

    // The graph view (in the middle).
    add((graphView = new GraphView()), BorderLayout.CENTER);

    // Force a layout.
    validate();
  }

  /** Set the title of the graph.
   */
  public void setGraphTitle(String title) {
    graphTitle.setText(title);
  }

  /** Set the order of the graph.
   */
  public void setGraphOrder(int order) {
    graphOrder.setText(String.format("Order: %d", order));
  }

  /** Set the number of edges in the graph.
   */
  public void setGraphEdges(int edges) {
    graphEdges.setText(String.format("Edges: %d", edges));
  }

}