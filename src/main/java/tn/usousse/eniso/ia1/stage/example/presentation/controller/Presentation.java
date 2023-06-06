package tn.usousse.eniso.ia1.stage.example.presentation.controller;

import tn.usousse.eniso.ia1.stage.example.presentation.model.Node;
import tn.usousse.eniso.ia1.stage.example.presentation.model.Table;
import tn.usousse.eniso.ia1.stage.example.presentation.view.HashtableDrawComponent;
import tn.usousse.eniso.ia1.stage.example.service.Service;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.util.Scanner;

public class Presentation {
    int size;

    public Presentation() {
    }



    public  void console(){
        JFrame f = new JFrame();
        f.setTitle("SWING");

//        creating the header
        JMenuBar mb=new JMenuBar();
        JMenu menu =new JMenu("file");
        JMenu help = new JMenu("help");
        JMenuItem about = new JMenuItem("about");
        JMenuItem n=new JMenuItem("size ");
        JMenuItem addName=new JMenuItem("add");
        menu.add(n);
        menu.add(addName);
        help.add(about);
        mb.add(menu);
        mb.add(help);
        f.setJMenuBar(mb);


//            creating the dialogs
        n.addActionListener(e -> {
            String sizeText = JOptionPane.showInputDialog(f, "Size: ");
            if (sizeText != null) {
                this.size = Integer.parseInt(sizeText);
                HashtableDrawComponent drawComponent = new HashtableDrawComponent();
                drawComponent.setSize(size);
                f.add(drawComponent);
                f.setVisible(true);

            }
        });
        about.addActionListener(e -> {
            JOptionPane.showMessageDialog(f, "Tayssir");

        });
        addName.addActionListener(e ->{
            String name = JOptionPane.showInputDialog(f, "Name");
            HashtableDrawComponent drawComponent = new HashtableDrawComponent();
            drawComponent.setName(name);


        });





        f.setSize(700,500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);


}}
