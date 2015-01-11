// Unit test for the graph.

package graph;

import java.util.*;

import mcgoo.*;

public class GraphTest extends McgooTest {
  
  public static void main(String[] args) { new GraphTest(); }

  public GraphTest() {

    add(new McgooCase() {
        public String name() { return "basicTest"; }
        public void run() {
          // Init.
          Graph g = new Graph();
          expect(g.order() == 0);
          expect(g.edges() == 0);

          // Add.
          GraphNode<Integer> node1 = new GraphNode<Integer>(1);
          expect(g.add(node1));
          expect(g.order() == 1);
          expect(g.edges() == 0);

          GraphNode<Integer> node2 = new GraphNode<Integer>(2);
          expect(g.add(node1, node2));
          expect(g.order() == 2);
          expect(g.edges() == 1);

          GraphNode<Integer> node3 = new GraphNode<Integer>(3);
          GraphNode<Integer> node4 = new GraphNode<Integer>(4);
          expect(g.add(node3, node4));
          expect(g.order() == 4);
          expect(g.edges() == 2);
          expect(g.add(node2, node3));
          expect(g.order() == 4);
          expect(g.edges() == 3);

          // Remove.
          expect(g.remove(node4));
          expect(g.order() == 3);
          expect(g.edges() == 2);

          expect(g.remove(node1, node2));
          expect(g.order() == 3);
          expect(g.edges() == 1);

          // Neighboorhood.
          expect(g.neighborhood(node1).length == 0);
          expect(g.neighborhood(node2).length == 1);
          expect(g.neighborhood(node2)[0] == node3);
          expect(g.neighborhood(node3).length == 0);
        }
      });

    add(new McgooCase() {
        public String name() { return "stringTest"; }
        public void run() {
          Graph g = new Graph();

          expect(g.add(new GraphNode<Integer>(3)));
          expect(g.add(new GraphNode<Integer>(1), new GraphNode<Integer>(2)));
          expect(g.add(new GraphNode<Integer>(4), new GraphNode<Integer>(5)));
          note("g", g);
        }
      });

    add(new McgooCase() {
        public String name() { return "pathTest"; }
        public void run() {
          GraphNode[] path, nodes;
          
          // P4.
          Graph p4 = GraphLibrary.P(4);
          nodes = p4.nodes();

          path = p4.path(nodes[0], nodes[0]);
          expect(path.length, 1);
          expect(path[0], nodes[0]);

          path = p4.path(nodes[0], nodes[1]);
          expect(path.length, 2);
          expect(path[0], nodes[0]);
          expect(path[1], nodes[1]);

          path = p4.path(nodes[1], nodes[3]);
          expect(path.length, 3);
          expect(path[0], nodes[1]);
          expect(path[1], nodes[2]);
          expect(path[2], nodes[3]);

          expect(p4.path(nodes[1], nodes[0]) == null);

          // C4.
          Graph c4 = GraphLibrary.C(4);
          nodes = c4.nodes();

          path = c4.path(nodes[0], nodes[0]);
          expect(path.length, 1);
          expect(path[0], nodes[0]);

          path = c4.path(nodes[0], nodes[1]);
          expect(path.length, 2);
          expect(path[0], nodes[0]);
          expect(path[1], nodes[1]);

          path = c4.path(nodes[1], nodes[3]);
          expect(path.length, 3);
          expect(path[0], nodes[1]);
          expect(path[1], nodes[2]);
          expect(path[2], nodes[3]);

          path = c4.path(nodes[1], nodes[0]);
          expect(path.length, 4);
          expect(path[0], nodes[1]);
          expect(path[1], nodes[2]);
          expect(path[2], nodes[3]);
          expect(path[3], nodes[0]);

          // K4.
          Graph k4 = GraphLibrary.K(4);
          nodes = k4.nodes();

          for (GraphNode node1 : nodes) {
            for (GraphNode node2 : nodes) {
              path = k4.path(node1, node2);
              expect((node1 == node2
                      ? path.length == 1
                      : path.length == 2));
            }
          }
        }
      });

    run();

  }

}