package tn.usousse.eniso.ia1.stage.example.presentation.view;

import tn.usousse.eniso.ia1.stage.example.model.Node;
import tn.usousse.eniso.ia1.stage.example.model.Table;

import javax.swing.*;
import java.awt.*;

public class HashtableDrawComponent extends JComponent {

    private int index;
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


    private void drawMsalha( Graphics2D g2d, int caseSize, int startX, int startY, int i){
        int lineY = startY + caseSize * i - 100;
        int lineX = startX + caseSize * 2 + 100;
        g2d.drawLine(lineX, lineY + 20, lineX, lineY - 20);
        g2d.drawLine(lineX, lineY + 10, lineX + 10, lineY + 20);
        g2d.drawLine(lineX, lineY - 10, lineX + 10, lineY);
    }

    private void drawNode(Node node, Graphics2D g2d, int caseSize, int startX, int startY, int i) {
        int lineY = startY + caseSize * i - 100;
        g2d.setStroke(new BasicStroke(1));
        g2d.drawLine(startX + caseSize, lineY, startX + caseSize + 50, lineY);
        int rectY = lineY + caseSize;
        g2d.drawRect(startX + caseSize + 50, rectY - caseSize - 30, caseSize - 20, caseSize - 40);
        g2d.drawString(node.getValue(), startX + caseSize + 75, lineY);
        g2d.drawLine(startX + caseSize * 2 + 30, lineY, startX + caseSize * 2 + 100, lineY);

    }

    public void setIndex(int index) {
        this.index = index;
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
        int xPos = startX;
        for (int i = 0; i < tableSize; i++) {
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(3));
            int y = startY + i * caseSize;

            g2d.drawRect(xPos, y, caseSize, caseSize);
            Node n = model.getNodes()[i];
            boolean test = false;
            int nodeX = xPos + caseSize + 40;

            while (n != null) {
                test = true;
                int nodeY = y + caseSize - 75;
                g2d.drawLine(nodeX,nodeY+25,nodeX - 40,nodeY+25);
                g2d.drawRect(nodeX, nodeY, caseSize - 20, caseSize - 40);
                g2d.drawString(n.getValue(), nodeX + 25, nodeY + caseSize - 20);
                g2d.drawLine(nodeX+caseSize-17,nodeY+25,nodeX +caseSize +20,nodeY+25);
                nodeX += caseSize + 20;
                n = n.getNext();
            }
            int lineX = xPos + (caseSize + 20) * (i + 1)*2 + 20;
            int lineY = y + caseSize / 2;
            if (i == index && test) {
                g2d.drawLine(lineX, lineY + 20, lineX, lineY - 20);
                g2d.drawLine(lineX, lineY + 10, lineX + 10, lineY + 20);
                g2d.drawLine(lineX, lineY - 10, lineX + 10, lineY);
            }
        }

    }
}
