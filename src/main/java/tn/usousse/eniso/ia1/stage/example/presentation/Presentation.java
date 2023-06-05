package tn.usousse.eniso.ia1.stage.example.presentation;

import tn.usousse.eniso.ia1.stage.example.model.Node;
import tn.usousse.eniso.ia1.stage.example.model.Table;
import tn.usousse.eniso.ia1.stage.example.service.Service;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.util.Scanner;

public class Presentation {
    int size;

    public Presentation() {
    }
    private JScrollPane drawTable(int size ){

        // Data to be displayed in the JTable
        String[][] data = new String[size][1];

        // Column Names
        String[] columnNames = { "Name" };

        // Initializing the JTable
        JTable j = new JTable(data, columnNames);
        TableColumn column = j.getColumnModel().getColumn(0);
        column.setPreferredWidth(2);

        return new JScrollPane(j);
    }

    public  void console(){
        JFrame f = new JFrame();
        JPanel p = new JPanel();
        f.setTitle("SWING");

//        creating the header
        JMenuBar mb=new JMenuBar();
        JMenu menu =new JMenu("file");
        JMenu help = new JMenu("help");
        JMenuItem about = new JMenuItem("about");
        JMenuItem n=new JMenuItem("size ");
        JMenuItem add=new JMenuItem("add");
        menu.add(n);
        menu.add(add);
        help.add(about);
        mb.add(menu);
        mb.add(help);
        f.setJMenuBar(mb);


//            creating the size dialog
        n.addActionListener(e -> {
            String sizeText = JOptionPane.showInputDialog(f, "");
            if (sizeText != null) {
                this.size = Integer.parseInt(sizeText);
                JScrollPane sp = drawTable(this.size);
                p.add(sp);

                f.getContentPane().add(p);
                // Frame Visible = true
                f.setVisible(true);
            }
        });
        about.addActionListener(e -> {
            JOptionPane.showMessageDialog(f, "Enter the size:");

        });




        f.setSize(500,300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

                default:
                    System.out.println("invalid command");
            }
        }


    }
}}
