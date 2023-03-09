package cs3500.gui.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cs3500.gui.imagecontroller.GuiImageProcessorController;
import cs3500.imagemodel.ImageProcessorModel;

/**
 * The GUI of Operating.
 */
public class MainGui extends JFrame {
  private final int WIDTH = 1500;
  private final int HEIGHT = 800;
  private final String TITLE = "MainGui";
  //private final GuiImageProcessorController guiImageProcessorController;

  /**
   * A public constructor for a MainGui.
   *
   * @param guiImageProcessorController guiImageProcessorController
   */
  public MainGui(GuiImageProcessorController guiImageProcessorController) {
    //this.guiImageProcessorController = guiImageProcessorController;
    setTitle(TITLE);
    setSize(WIDTH, HEIGHT);
    setVisible(true);
    setLocationRelativeTo(null);
    BorderLayout lay = new BorderLayout();
    setLayout(lay);

    // center
    ImagePanel lineStart = new ImagePanel();
    JScrollPane jScrollPane = new JScrollPane(lineStart);
    this.getContentPane().add(jScrollPane, BorderLayout.LINE_START);

    // south
    HistogramPanel histogramPanel = new HistogramPanel();
    this.getContentPane().add(histogramPanel, BorderLayout.LINE_END);

    // north
    JComboBox<String> imageKey = new JComboBox<String>();
    imageKey.setPreferredSize(new Dimension(100, 40));
    imageKey.setToolTipText("list of images' key");
    Set<String> keys = guiImageProcessorController.getKeys();
    for (String key : keys) {
      imageKey.addItem(key);
    }

    JPanel north = new JPanel();
    north.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
    JButton loadBtn = generateLoadButton(guiImageProcessorController, imageKey, lineStart,
            histogramPanel);
    JButton saveBtn = generateSaveButton(guiImageProcessorController, imageKey);
    JButton redCompBtn = generateCompBtn("red-component", "red",
            guiImageProcessorController, imageKey, lineStart, histogramPanel);
    JButton greeCompBtn = generateCompBtn("green-component", "green",
            guiImageProcessorController, imageKey, lineStart, histogramPanel);
    JButton blueCompBtn = generateCompBtn("blue-component", "blue",
            guiImageProcessorController, imageKey, lineStart, histogramPanel);
    JButton valueCompBtn = generateCompBtn("value-component", "value",
            guiImageProcessorController, imageKey, lineStart, histogramPanel);
    JButton lumaCompBtn = generateCompBtn("luma-component", "luma",
            guiImageProcessorController, imageKey, lineStart, histogramPanel);
    JButton intensityCompBtn = generateCompBtn("intensity-component", "intensity",
            guiImageProcessorController, imageKey, lineStart, histogramPanel);
    JButton horiFlipCompBtn = generateCompBtn("horizontal-flip", "hori-flip",
            guiImageProcessorController, imageKey, lineStart, histogramPanel);
    JButton vertFlipCompBtn = generateCompBtn("vertical-flip", "vert-flip",
            guiImageProcessorController, imageKey, lineStart, histogramPanel);
    JButton blurCompBtn = generateCompBtn("blur", "blur", guiImageProcessorController,
            imageKey, lineStart, histogramPanel);
    JButton sharpenCompBtn = generateCompBtn("sharpen", "sharpen",
            guiImageProcessorController, imageKey, lineStart, histogramPanel);
    JButton greyscaleCompBtn = generateCompBtn("greyscale", "greyscale",
            guiImageProcessorController, imageKey, lineStart, histogramPanel);
    JButton sepiaCompBtn = generateCompBtn("sepia", "sepia",
            guiImageProcessorController, imageKey, lineStart, histogramPanel);
    north.add(loadBtn);
    north.add(imageKey);
    north.add(saveBtn);
    north.add(redCompBtn);
    north.add(greeCompBtn);
    north.add(blueCompBtn);
    north.add(valueCompBtn);
    north.add(lumaCompBtn);
    north.add(intensityCompBtn);
    north.add(horiFlipCompBtn);
    north.add(vertFlipCompBtn);
    north.add(blurCompBtn);
    north.add(sharpenCompBtn);
    north.add(greyscaleCompBtn);
    north.add(sepiaCompBtn);
    this.getContentPane().add(north, BorderLayout.PAGE_START);
  }

  /**
   * generate a red-component button.
   *
   * @param guiImageProcessorController guiIamgeProcessorController
   * @param imageKey                    JComboBox String
   * @param center                      imagePanel
   * @param histogramPanel              HistrogramPanel
   * @return JButton
   */
  private JButton generateCompBtn(String cmd, String btnLbl,
                                  GuiImageProcessorController guiImageProcessorController,
                                  JComboBox<String> imageKey, ImagePanel center,
                                  HistogramPanel histogramPanel) {
    JButton loadBtn = new JButton(btnLbl);
    loadBtn.setToolTipText(cmd);
    loadBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Object selectedItem = imageKey.getSelectedItem();
        if (selectedItem == null) {
          int itemCount = imageKey.getItemCount();
          String msg = "";
          if (itemCount == 0) {
            msg = "Please Load an image first!";
          } else {
            msg = "Please select one image key!";
          }
          JOptionPane.showMessageDialog(null, msg, "Operating Tips",
                  JOptionPane.INFORMATION_MESSAGE);
          return;
        }
        String imagKey = String.valueOf(selectedItem);
        String destImage = String.format("%s_%s", imagKey, cmd);
        guiImageProcessorController.operate(cmd, imagKey, destImage);
        imageKey.addItem(destImage);
        imageKey.setSelectedItem(destImage);
        ImageProcessorModel imageProcessorModel =
                guiImageProcessorController.getImageProcessorModel(destImage);
        String[][] colorInfo = imageProcessorModel.getColorInfo();
        int width = imageProcessorModel.getWidth();
        int height = imageProcessorModel.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < width; x++) {
          for (int y = 0; y < height; y++) {
            String coloInfo = colorInfo[x][y];
            String[] split = coloInfo.split(",");
            int r = Integer.parseInt(split[0]);
            int g = Integer.parseInt(split[1]);
            int b = Integer.parseInt(split[2]);
            Color color = new Color(r, g, b);
            bufferedImage.setRGB(x, y, color.getRGB());
          }
        }
        ImageIcon imageIcon = new ImageIcon(bufferedImage);
        center.setImageIcon(imageIcon);
        center.setSize(width, height);
        center.repaint();

        histogramPanel.setColorInfo(colorInfo);
        histogramPanel.repaint();
      }
    });
    return loadBtn;
  }

  /**
   * generate a save button.
   *
   * @param guiImageProcessorController GuiImageProcessorController
   * @param imageKey                    JComboBox String
   * @return JButton
   */
  private JButton generateSaveButton(GuiImageProcessorController guiImageProcessorController,
                                     JComboBox<String> imageKey) {
    JButton saveBtn = new JButton("save");
    saveBtn.setToolTipText("save an image");
    saveBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Object selectedItem = imageKey.getSelectedItem();
        if (selectedItem == null) {
          int itemCount = imageKey.getItemCount();
          String msg = "";
          if (itemCount == 0) {
            msg = "Please Load an image first!";
          } else {
            msg = "Please select one image key!";
          }
          JOptionPane.showMessageDialog(null, msg, "Operating Tips",
                  JOptionPane.INFORMATION_MESSAGE);
          return;
        }
        String imageKey = String.valueOf(selectedItem);
        ImageProcessorModel imageProcessorModel =
                guiImageProcessorController.getImageProcessorModel(imageKey);
        String fileName = String.format("%s.%s", imageKey, imageProcessorModel.getExtension());
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File(fileName));
        fileChooser.showSaveDialog(null);
        String filePath = fileChooser.getSelectedFile().toString();
        try {
          guiImageProcessorController.operate("save", filePath, imageKey);
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(null, "Picture saving failed",
                  "Operating Tips", JOptionPane.INFORMATION_MESSAGE);
        }
      }
    });
    return saveBtn;
  }

  /**
   * generate a load button.
   *
   * @param guiImageProcessorController GuiImageProcessorController
   * @param imageKey JComboBox String
   * @param center ImagePanel
   * @param histogramPanel HistogramPanel
   * @return JButton
   */
  private JButton generateLoadButton(GuiImageProcessorController guiImageProcessorController,
                                     JComboBox<String> imageKey, ImagePanel center,
                                     HistogramPanel histogramPanel) {
    JButton loadBtn = new JButton("load");
    loadBtn.setToolTipText("load an image");
    loadBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.showDialog(new JLabel(), "select");
        File file = jfc.getSelectedFile();
        String destName = file.getName().split("\\.")[0];
        guiImageProcessorController.operate("load", file.getAbsolutePath(), destName);
        imageKey.addItem(destName);
        ImageProcessorModel imageProcessorModel =
                guiImageProcessorController.getImageProcessorModel(destName);
        String[][] colorInfo = imageProcessorModel.getColorInfo();
        int width = imageProcessorModel.getWidth();
        int height = imageProcessorModel.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < width; x++) {
          for (int y = 0; y < height; y++) {
            String coloInfo = colorInfo[x][y];
            String[] split = coloInfo.split(",");
            int r = Integer.parseInt(split[0]);
            int g = Integer.parseInt(split[1]);
            int b = Integer.parseInt(split[2]);
            Color color = new Color(r, g, b);
            bufferedImage.setRGB(x, y, color.getRGB());
          }
        }
        ImageIcon imageIcon = new ImageIcon(bufferedImage);
        center.setImageIcon(imageIcon);
        center.setSize(width, height);
        center.repaint();

        histogramPanel.setColorInfo(colorInfo);
        histogramPanel.repaint();
      }
    });
    return loadBtn;
  }
}
