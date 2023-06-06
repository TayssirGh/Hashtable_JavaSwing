package tn.usousse.eniso.ia1.stage.example.presentation.view;

import tn.usousse.eniso.ia1.stage.example.presentation.model.Table;
import tn.usousse.eniso.ia1.stage.example.service.Service;

import javax.swing.*;
import java.awt.*;

public class HashtableDrawComponent extends JComponent {
    private int tableSize;
    private String name ="testes";
    public HashtableDrawComponent(){
        tableSize = 0;
    }

    public void setSize(int size) {
        tableSize = size;
    }
    public void setName(String name){
        this.name = name;
    }

    private void drawNode(Graphics2D g2d,int caseSize, int startX, int startY){
        if(name !=null){
            Table table = new Table(tableSize);
            Service service = new Service(table);
            int i = service.hash(name);
            int lineY = startY +caseSize* i+50;
            g2d.drawLine(startX+caseSize, lineY, startX + caseSize+50, lineY);
            int rectY = lineY + caseSize;
            g2d.drawRect(startX+caseSize+50, rectY-caseSize-30, caseSize -20, caseSize -40);
            g2d.drawString(name, startX + caseSize + 75, lineY);
            g2d.drawLine(startX + caseSize*2+30, lineY, startX + caseSize*2+100, lineY);
            int lineX = startX + caseSize*2+100;
            g2d.drawLine(lineX, lineY+20, lineX, lineY-20);
            g2d.drawLine(lineX, lineY+10, lineX +10, lineY+20 );
            g2d.drawLine(lineX, lineY-10, lineX +10, lineY );


        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int caseSize = 100;
        int tableHeight = caseSize*tableSize;

        int startX = (getWidth() - caseSize) / 2;
        int startY = (getHeight() - tableHeight) / 2;

        for (int i = 0; i < tableSize; i++) {
            int y = startY + i * caseSize;
            g2d.drawRect(startX, y, caseSize, caseSize);

        }

        drawNode(g2d,caseSize,startX,startY);
    }
}
