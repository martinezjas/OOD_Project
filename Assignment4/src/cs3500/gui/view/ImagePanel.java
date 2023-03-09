package cs3500.gui.view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * class that shows the image in the Jpanel.
 */
public class ImagePanel extends JPanel {

  /**
   * A public constructor for class ImagePanel.
   */
  public ImagePanel() {
    setPreferredSize(new Dimension(600, 800));
  }

  private ImageIcon imageIcon;

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (imageIcon != null) {
      g.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getIconWidth(),
              imageIcon.getIconHeight(), null);
    }
  }

  public void setImageIcon(ImageIcon imageIcon) {
    this.imageIcon = imageIcon;
  }
}
