package tn.usousse.eniso.ia1.stage.example.presentation.view;

import tn.usousse.eniso.ia1.stage.example.model.Node;
import tn.usousse.eniso.ia1.stage.example.model.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HashtableDrawComponent extends JComponent {
    private String delName;
    int finalNodeX;
    int nodeY;
    int xMax;
    int xMin;
    int yMax;
    int yMin;

    public int getxMax() {
        return xMax;
    }

    public int getxMin() {
        return xMin;
    }

    public int getyMax() {
        return yMax;
    }

    public int getyMin() {
        return yMin;
    }

    public String getDelName() {
        return delName;
    }

    private Table model = new Table(10);

    public HashtableDrawComponent() {
    }

    public Table getModel() {

        return model;
    }

    public void setModel(Table model) {
        this.model = model;
        //SwingUtilities.invokeLater(()->repaint());
        SwingUtilities.invokeLater(this::repaint);
    }




    public void setIndex(int index) {

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);
        g2d.setPaint(Color.WHITE);
        g2d.fill(getBounds());

        int tableSize = model.getNodes().length;
        System.out.println("table size : " + tableSize);

        int caseSize = 100;
        int tableHeight = caseSize * tableSize;


        int startX = (getWidth() - caseSize*2-100) / 2;
        int startY = (getHeight() - tableHeight) / 2;
        for (int i = 0; i < tableSize; i++) {
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(3));
            int y = startY + i * caseSize;

            g2d.drawRect(startX, y, caseSize, caseSize);
            Node n = model.getNodes()[i];
            boolean test = false;
            int nodeX = startX + caseSize + 40;

            while (n != null) {
                test = true;
                 nodeY = y + caseSize - 75;
                g2d.drawLine(nodeX,nodeY+25,nodeX - 40,nodeY+25);
                g2d.drawRect(nodeX, nodeY, caseSize - 20, caseSize - 40);
                g2d.drawString(n.getValue(), nodeX + 25, nodeY + caseSize - 20);
                g2d.drawLine(nodeX+caseSize-17,nodeY+25,nodeX +caseSize +20,nodeY+25);
                nodeX += caseSize + 20;

                 finalNodeX = nodeX;
                Node node = n;
                delName = node.getValue();
                xMin = finalNodeX -caseSize;
                xMax = finalNodeX  - 20  ;
                yMax = nodeY + caseSize - 40;
                yMin = nodeY;
                this.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getX() >= finalNodeX -caseSize && e.getX() <= finalNodeX  - 20 &&
                                e.getY() >= nodeY && e.getY() <= nodeY + caseSize - 40) {
                            int confirm = JOptionPane.showConfirmDialog(null, "Do you want to remove this node?");
                            if (confirm == JOptionPane.YES_OPTION) {
                                delName = node.getValue();
                                repaint();
                            }
                        }
                    }
                });
                n = n.getNext();
            }

            if (test){
                int lineY = y + caseSize / 2;
                g2d.drawLine(nodeX, lineY + 20, nodeX, lineY - 20);
                g2d.drawLine(nodeX, lineY + 10, nodeX + 10, lineY + 20);
                g2d.drawLine(nodeX, lineY - 10, nodeX+ 10, lineY);
            }


        }

    }
}
