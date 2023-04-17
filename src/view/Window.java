package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.Snapshot;

/**
 * The type Window.
 */
public class Window extends JFrame {

  private JPanel snapshotPanel;
  private JButton previousButton;
  private JButton nextButton;
  private JButton jumpButton;
  private JButton quitButton;
  private JComboBox<String> jumpComboBox;
  private JLabel infoLabel;

  private List<Snapshot> snapshots;
  private int currentIndex;

  private void updateInfoLabel(String snapshotID) {
    if (snapshotID == null) {
      throw new IllegalArgumentException("Snapshot ID cannot be null");
    }
    infoLabel.setText("Snapshot " + snapshotID);
  }

  // Constructor

  /**
   * Instantiates a new Window.
   *
   * @param snapshots the snapshots
   * @param xmax      the xmax
   * @param ymax      the ymax
   */
  public Window(List<Snapshot> snapshots, int xmax, int ymax) {
    super("Photo Album");
    this.snapshots = snapshots;
    this.currentIndex = 0;

    //Null check
    if (snapshots == null || xmax < 0 || ymax < 0) {
      throw new IllegalArgumentException("Failed");
    }

    // Set up panel for snapshots
    snapshotPanel = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (snapshots != null && !snapshots.isEmpty()) {
          File imageFile = new File(snapshots.get(currentIndex).getTimestamp() + ".png");
          BufferedImage image = null;
          try {
            image = ImageIO.read(imageFile);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
          if (image != null) {
            int x = (getWidth() - image.getWidth()) / 2;
            int y = (getHeight() - image.getHeight()) / 2;
            g.drawImage(image, x, y, null);
          }
        }
      }
    };
    snapshotPanel.setPreferredSize(new Dimension(xmax, ymax));
    add(snapshotPanel, BorderLayout.CENTER);

    // Set up button panel
    JPanel buttonPanel = new JPanel(new FlowLayout());

    // Set up "previous" button
    previousButton = new JButton("Previous");
    previousButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (snapshots != null && !snapshots.isEmpty()) {
          currentIndex--;
          if (currentIndex < 0) {
            JOptionPane.showMessageDialog(Window.this, "No previous photo!");
            currentIndex = 0;
          } else {
            snapshotPanel.repaint();
            updateInfoLabel(String.valueOf(snapshots.get(currentIndex).getTimestamp()));
          }
        }
      }
    });
    buttonPanel.add(previousButton);

    // Set up "next" button
    nextButton = new JButton("Next");
    nextButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (snapshots != null && !snapshots.isEmpty()) {
          currentIndex++;
          if (currentIndex >= snapshots.size()) {
            JOptionPane.showMessageDialog(Window.this, "No next photo!");
            currentIndex = snapshots.size() - 1;
          } else {
            snapshotPanel.repaint();
            updateInfoLabel(String.valueOf(snapshots.get(currentIndex).getTimestamp()));
          }
        }
      }
    });
    buttonPanel.add(nextButton);

    // Set up "jump" button
    jumpButton = new JButton("Jump");
    jumpButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (snapshots != null && !snapshots.isEmpty()) {
          String selectedId = (String) jumpComboBox.getSelectedItem();
          for (int i = 0; i < snapshots.size(); i++) {
            if (selectedId.equalsIgnoreCase(String.valueOf(snapshots.get(i).getTimestamp()))) {
              currentIndex = i;
              snapshotPanel.repaint();
              updateInfoLabel(String.valueOf(snapshots.get(currentIndex).getTimestamp()));
              break;
            }
          }
        }
      }
    });
    buttonPanel.add(jumpButton);

    // Set up quit button
    quitButton = new JButton("Quit");
    quitButton.addActionListener(new MyCloseListener());
    buttonPanel.add(quitButton);

    // Set up combo box for "jump" functionality
    jumpComboBox = new JComboBox<String>();
    if (snapshots != null && !snapshots.isEmpty()) {
      for (Snapshot snapshot : snapshots) {
        jumpComboBox.addItem(String.valueOf(snapshot.getTimestamp()));
      }
    }
    buttonPanel.add(jumpComboBox);

    // Set up info label
    infoLabel = new JLabel("");
    updateInfoLabel(String.valueOf(snapshots.get(currentIndex).getTimestamp()));
    buttonPanel.add(infoLabel);

    add(buttonPanel, BorderLayout.SOUTH);


    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }
}
