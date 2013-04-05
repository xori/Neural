/*
 * Window.java
 *
 * Created on Mar 24, 2013, 2:17:08 PM
 */
package feature.map.gui;

import feature.map.Epoch;
import feature.map.SOFM;
import feature.map.utilities.DataPreProcess;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.Timer;
import sun.security.jca.JCAUtil;

/**
 *
 * @author verworn
 */
public class Window extends javax.swing.JFrame {

    SOFM som = null;
    File [] files = null;
    
    /** Creates new form Window */
    public Window() {
        initComponents();
        Timer t = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(som!=null && som.isAlive()) {
                    jEpochSlider.setValue(100);
                    jEpochSliderCaretPositionChanged(null);
                }
                canvas.repaint();
            }
        });
        t.start();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        canvas = new feature.map.gui.JCanvas();
        jEpochSlider = new javax.swing.JSlider();
        jEpochLabel = new javax.swing.JLabel();
        jBrowseButton = new javax.swing.JButton();
        jInputLabel = new javax.swing.JLabel();
        jProcessButton = new javax.swing.JButton();
        jProgressBar = new javax.swing.JProgressBar();
        jQMatrixButton = new javax.swing.JToggleButton();
        jErrorLabel = new javax.swing.JLabel();
        jMapDim = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabelNeighbourhood = new javax.swing.JLabel();
        jNeighbourhoodSlider = new javax.swing.JSlider();
        jComboBox1 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        canvas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(213, 213, 213)));
        canvas.setPreferredSize(new java.awt.Dimension(300, 300));

        javax.swing.GroupLayout canvasLayout = new javax.swing.GroupLayout(canvas);
        canvas.setLayout(canvasLayout);
        canvasLayout.setHorizontalGroup(
            canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );
        canvasLayout.setVerticalGroup(
            canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );

        jEpochSlider.setMajorTickSpacing(10);
        jEpochSlider.setPaintTicks(true);
        jEpochSlider.setToolTipText("The epoch to display on the above display");
        jEpochSlider.setFocusable(false);
        jEpochSlider.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                jEpochSliderCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });

        jEpochLabel.setText("epochs");

        jBrowseButton.setText("Browse...");
        jBrowseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBrowseButtonActionPerformed(evt);
            }
        });

        jInputLabel.setText("Input Data");

        jProcessButton.setText("Process Input");
        jProcessButton.setFocusPainted(false);
        jProcessButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jProcessButtonActionPerformed(evt);
            }
        });

        jProgressBar.setToolTipText("");
        jProgressBar.setValue(10);
        jProgressBar.setBorderPainted(false);
        jProgressBar.setFocusable(false);
        jProgressBar.setRequestFocusEnabled(false);
        jProgressBar.setStringPainted(true);

        jQMatrixButton.setText("Q-Matrix");
        jQMatrixButton.setFocusPainted(false);
        jQMatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jQMatrixButtonActionPerformed(evt);
            }
        });

        jErrorLabel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jErrorLabel.setForeground(new java.awt.Color(51, 51, 51));
        jErrorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jErrorLabel.setText("Error: 12%");

        jMapDim.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jMapDim.setText("128");
        jMapDim.setToolTipText("width of map");

        jLabel1.setText("Map Dimensions");

        jLabelNeighbourhood.setText("Starting Neighbourhood Size");

        jNeighbourhoodSlider.setMajorTickSpacing(20);
        jNeighbourhoodSlider.setMaximum(80);
        jNeighbourhoodSlider.setMinorTickSpacing(10);
        jNeighbourhoodSlider.setPaintLabels(true);
        jNeighbourhoodSlider.setPaintTicks(true);
        jNeighbourhoodSlider.setValue(20);
        jNeighbourhoodSlider.setFocusable(false);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jEpochSlider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(canvas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jEpochLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jErrorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jQMatrixButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jInputLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBrowseButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProcessButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(jLabelNeighbourhood, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jNeighbourhoodSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jMapDim))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(canvas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jEpochLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jEpochSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jInputLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBrowseButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jQMatrixButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jErrorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jMapDim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelNeighbourhood)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jNeighbourhoodSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jProcessButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBrowseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBrowseButtonActionPerformed
        
        JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
        jfc.setMultiSelectionEnabled(true);
        int result = jfc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            files = jfc.getSelectedFiles();
            for(File f : jfc.getSelectedFiles()) {
                System.out.println(f);
            }
        }
        
    }//GEN-LAST:event_jBrowseButtonActionPerformed

    private void jProcessButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jProcessButtonActionPerformed
        
        if(som != null && som.isAlive()){
            som.stop();
        }
        
        som = new SOFM(3, Integer.parseInt(jMapDim.getText()), 
                          Integer.parseInt(jMapDim.getText()), 
                0.2, jNeighbourhoodSlider.getValue(), 10000);
        if (files == null)
            som.setData(DataPreProcess.random8Colours());
        som.start();
        
    }//GEN-LAST:event_jProcessButtonActionPerformed

    private void jQMatrixButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jQMatrixButtonActionPerformed
        
        double distance = 0;
        int position = (int) (jEpochSlider.getValue()/100.0*(som.output.size()-1));
        Epoch e = som.output.get(position);
//        for(int y = 1; y < image.getHeight()-1; y++ )
//            for(int x = 1; x < image.getWidth()-1; x++) {
//                for(int jy = -1; jy < 2; jy++)
//                    for(int jx = -1; jx < 2; jx++)
//            }

    }//GEN-LAST:event_jQMatrixButtonActionPerformed

    private void jEpochSliderCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jEpochSliderCaretPositionChanged
        
        if (som.output.isEmpty())
            return;
        
        int position = (int) (jEpochSlider.getValue()/100.0*(som.output.size()-1)); 
        Epoch e = som.output.get(position);
        canvas.setImage(e.image.getScaledInstance(300, 300, Image.SCALE_FAST));
        jErrorLabel.setText("Error: "+ (int) e.error);
        
    }//GEN-LAST:event_jEpochSliderCaretPositionChanged
    
    private double eucDistance(Double [] a, Double [] b){
        double distance = 0;
        
        for(int i = 0; i < a.length; i++) {
            distance += Math.pow(a[i]-b[i],2);
        }
        return Math.sqrt(distance);
    }    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public feature.map.gui.JCanvas canvas;
    public javax.swing.JButton jBrowseButton;
    public javax.swing.JComboBox jComboBox1;
    public javax.swing.JLabel jEpochLabel;
    public javax.swing.JSlider jEpochSlider;
    public javax.swing.JLabel jErrorLabel;
    public javax.swing.JLabel jInputLabel;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabelNeighbourhood;
    public javax.swing.JTextField jMapDim;
    public javax.swing.JSlider jNeighbourhoodSlider;
    public javax.swing.JButton jProcessButton;
    public javax.swing.JProgressBar jProgressBar;
    public javax.swing.JToggleButton jQMatrixButton;
    // End of variables declaration//GEN-END:variables
}
