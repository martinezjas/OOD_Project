package cs3500.gui.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Image Histograms class that extends JPanel.
 */
public class HistogramPanel extends JPanel {
  private static final int COLOR_MAX = 256;
  private String[][] colorInfo;

  /**
   * A public constructor for class HistogramPanel.
   */
  public HistogramPanel() {
    setBackground(Color.BLACK);
    setPreferredSize(new Dimension(1000, 800));
  }

  @Override
  protected void paintComponent(Graphics g) {
    int frameHeight = getHeight();
    int frameWidth = getWidth();
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, frameWidth, frameHeight);
    g.setColor(Color.black);
    Font font = g.getFont();
    int fontSize = font.getSize();
    g.drawString("Image Histogram", frameWidth / 2 - fontSize * 3, 30);
    int startX = frameWidth / 2 - 390;
    int startY = 50;
    g.drawLine(startX, startY, startX, frameHeight - startY);//Y-axis
    int yX2 = frameWidth - 10;
    g.drawLine(startX, frameHeight - startY, yX2, frameHeight - startY);//X-axis
    g.drawString("0", startX - 4, frameHeight - startY + 15);
    g.drawString("255", frameWidth - 135, frameHeight - startY + 15);
    int rectWidth = 3;
    int width = (yX2 - startX - COLOR_MAX * rectWidth) / (COLOR_MAX - 1);
    int height = 10000;
    int initHeight = frameHeight - startY;

    if (colorInfo != null && colorInfo.length > 0) {
      java.util.List<Integer> stat = new ArrayList<>(256);
      for (int l = 0; l <= 255; l++) {
        stat.add(0);
      }
      for (int i = 0; i < colorInfo.length; i++) {
        for (int j = 0; j < colorInfo[i].length; j++) {
          String coloInfo = colorInfo[i][j];
          String[] split = coloInfo.split(",");
          int r = Integer.parseInt(split[0]);
          int gj = Integer.parseInt(split[1]);
          int b = Integer.parseInt(split[2]);
          Integer cntOfR = stat.get(r);
          stat.set(r, cntOfR + 1);
          Integer cntOfG = stat.get(gj);
          stat.set(gj, cntOfG + 1);
          Integer cntOfB = stat.get(gj);
          stat.set(b, cntOfB + 1);
        }
      }
      int sum = stat.stream().mapToInt(Integer::intValue).sum();
      for (int i = 0; i < COLOR_MAX; i++) {
        g.setColor(Color.BLUE);
        int x = startX + i * (width + rectWidth);
        int cout = stat.get(i);
        int heght = (int) ((Double.valueOf(cout) / Double.valueOf(sum)) * Double.valueOf(height));
        int y = initHeight - heght;
        g.fillRect(x, y, rectWidth, heght);
      }
    }
  }

  public void setColorInfo(String[][] colorInfo) {
    this.colorInfo = colorInfo;
  }
}
