package tn.usousse.eniso.ia1.stage.example.presentation;

import tn.usousse.eniso.ia1.stage.example.model.Node;
import tn.usousse.eniso.ia1.stage.example.model.Table;
import tn.usousse.eniso.ia1.stage.example.service.Service;

import javax.swing.*;
import java.util.Scanner;

public class Presentation {
    public Presentation() {
    }

    public  void console(){
        JFrame f = new JFrame();
        JPanel p = new JPanel();
        f.setTitle("SWING");

//        creating the header
        JMenuBar mb=new JMenuBar();
        JMenu menu =new JMenu("file");
        JMenu menu1 = new JMenu("help");
        JMenuItem n=new JMenuItem("size ");
        JMenuItem add=new JMenuItem("add");
        menu.add(n);
        menu.add(add);
        mb.add(menu);
        mb.add(menu1);
        f.setJMenuBar(mb);

//            creating the dialog
        JDialog d = new JDialog(f, "add size");


        f.setSize(500,300);
        f.setVisible(true);

        Scanner sc = new Scanner(System.in);
        System.out.print(">size : ");
        int  size = sc.nextInt();
        sc.nextLine();
        Table table = new Table(size);
        while (true){

        Service service = new Service(table);
        System.out.print(">");
        String c = sc.nextLine();

        if(c.equals("list")){
            Table hashtable = service.list();
            Node[] nodes = hashtable.getNodes();
            for(int i = 0; i<nodes.length; i++){
                Node linkedList = nodes[i];
                System.out.print ("Index " + i + ": ");
                while(linkedList !=null){
                    System.out.print(linkedList.getValue() + "--->" );
                    linkedList = linkedList.getNext();
                }
                System.out.print("null");
                System.out.println();
            }
        }
        else if(c.equals("exit")){
            return;
        }
        else{
            String [] params = c.split(" ");
            String command = params[0];
            String name = params[1];
            switch (command){
                case "add":

                    boolean t1 = service.add(name);
                    System.out.println(t1);
                    break;
                case "remove":

                    t1 = service.remove(name);
                    System.out.println(t1);
                    break;
                case "find":

                    t1 = service.exists(name, table);
                    System.out.println(t1);
                    break;
                case "hash":

                    System.out.println(service.hash(name));
                    break;
                case "break":
                    break;
                default:
                    System.out.println("invalid command");
            }
        }


    }
}}
