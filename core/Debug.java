package core;

/** An internal debug class for the meh project.
 *
 * @author Andrew Keesler
 * @date January 11, 2015
 */
public class Debug {
  
  /** An enum for which 'channel' or module the debug help is for. */
  public enum DebugChannel {
    DEFAULT(true), /* the always on debug channel */
      
    CORE(true),    /* the core debug channel */
    VIEW(true),    /* the view debug channel */
    GRAPH(true);   /* the graph debug channel */
      
    boolean isOn;
    
    DebugChannel(boolean isOn) { this.isOn = isOn; }
  }
  
  public static void println(String stuff) {
    println(DebugChannel.DEFAULT, stuff);
  }
  
  public static void println(Debug.DebugChannel channel, String stuff) {
    if (channel.isOn)
      System.out.println(stuff);
  }

}