package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type My close listener.
 */
public class MyCloseListener implements ActionListener {

  /**
   * The action exit.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    System.exit(0);
  }
}
