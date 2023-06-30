package Interface.Program;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Statistics {

    public static void showGroupStatistics(Group group) {
        JTextArea text = new JTextArea();
        text.setCaretPosition(0);
        text.setEditable(false);
        text.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        text.setBackground(new Color(255, 255, 255));

        String s = group.getName() + "\n";
        s += "Description: " + group.getDescription() + "\n";

        long sumMoney = 0;
        int q = 0;

        for (int d = 0; d < group.getProducts().size(); d++) {
            if (group.getProducts() != null) {

                double price = group.getProducts().get(d).getPrice();
                int num = group.getProducts().get(d).getNumber();
                sumMoney += price * num;
                q+=num;
                s = s + "\n" + (d + 1) + ") " + group.getProducts().get(d).getName().toUpperCase() + "\n";
                s = s + "Price: " + group.getProducts().get(d).getPrice() + "\n";
                s = s + "Amount " + Math.round(group.getProducts().get(d).getNumber()) + "\n";
                s = s + "Manufacturer: " + group.getProducts().get(d).getBrand() + "\n";
                s = s + "Description: \n" + group.getProducts().get(d).getDescription() + "\n";

            }
        }


        if (sumMoney > 100000) {
            double sumMoneyInGrands = Math.ceil(sumMoney / 1000);
            s = s + "\n\nTotal products cost: " + sumMoneyInGrands + "k UAH";

        } else if (sumMoney > 100000000) {
            double sumMoneyInGrands = Math.ceil(sumMoney / 1000000);
            s = s + "\n\nTotal products cost: " + sumMoneyInGrands + "m UAH";
        } else {
            s = s + "\n\nStore has products of cost: " + sumMoney + " UAH";
        }
        s = s + "\nProduct amount: " + q;

        s += "Total cost amount of products in group: " + calculateGroupMoney(group) + "\n";

        text.setText(s);


        JScrollPane jp = new JScrollPane(text);
        jp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jp.setPreferredSize(new Dimension(450, 450));

        JFrame frame = new JFrame();
        frame.setSize(600, 500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(450, 450));
        panel.setBackground(new Color(128, 118, 146));
        panel.add(jp);

        frame.add(panel);

    }

    public static void showStatistics(Store store) {

        JTextArea text = new JTextArea();
        text.setCaretPosition(0);
        text.setEditable(false);
        text.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        text.setBackground(new Color(255, 255, 255));

        String s = "Store\n";
        long sumMoney = 0;
        int q = 0;

        for (int i = 0; i < store.getGroups().size(); i++) {
            if (store.getGroups().get(i).getProducts().size() != 0) {

                s = s + "\n\n";
                s = s + store.getGroups().get(i).getName().toUpperCase() + "\n";
                s+= "Description: " + store.getGroups().get(i).getDescription() + "\n";
                s+= "Total cost amount of products in group: " + calculateGroupMoney(store.getGroups().get(i)) + "\n";

                for (int d = 0; d < store.getGroups().get(i).getProducts().size(); d++) {

                    double price = store.getGroups().get(i).getProducts().get(d).getPrice();
                    int num = store.getGroups().get(i).getProducts().get(d).getNumber();
                    sumMoney += price * num;
                    q+=num;
                    s = s + "\n" + store.getGroups().get(i).getProducts().get(d).getName().toUpperCase() + "\n";
                    s = s + "Price: " + store.getGroups().get(i).getProducts().get(d).getPrice() + "\n";
                    s = s + "Amount " + Math.round(store.getGroups().get(i).getProducts().get(d).getNumber()) + "\n";
                    s = s + "Manufacturer: " + store.getGroups().get(i).getProducts().get(d).getBrand() + "\n";
                    s = s + "Description: " + store.getGroups().get(i).getProducts().get(d).getDescription() + "\n";
                }


            } else {
                s = s + "\n" + store.getGroups().get(i).getName().toUpperCase() + "\n this group is empty" + "\n\n";
            }
        }

        if (sumMoney > 100000) {
            double sumMoneyInGrands = Math.ceil(sumMoney / 1000);
            s = s + "\n\nTotal products cost: " + sumMoneyInGrands + "k UAH";

        } else if (sumMoney > 100000000) {
            double sumMoneyInGrands = Math.ceil(sumMoney / 1000000);
            s = s + "\n\nTotal products cost: " + sumMoneyInGrands + "m UAH";
        } else {
            s = s + "\n\nStore has products of cost: " + sumMoney + " UAH";
        }
        s = s + "\nProduct amount: " + q;

        text.setText(s);


        JScrollPane jp = new JScrollPane(text);
        jp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jp.setPreferredSize(new Dimension(450, 450));

        JFrame frame = new JFrame();
        frame.setSize(600, 500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(450, 450));
        panel.setBackground(new Color(128, 118, 146));
        panel.add(jp);

        frame.add(panel);

    }

    public static long calculateMoney(Group group) {
        long sumMoney = 0;
        if (group.getProducts() != null) {
            for (int i = 0; i < group.getProducts().size(); i++)
                sumMoney += (long) (group.getProducts().get(i).getPrice() * group.getProducts().get(i).getNumber());
        } else sumMoney = 0;
        return sumMoney;
    }

    public static int calculateQuantity(Group group) {
        int q = 0; //сума грошей
        if (group.getProducts() != null) {
            for (int i = 0; i < group.getProducts().size(); i++)
                q += group.getProducts().get(i).getNumber();
        } else q = 0;
        return q;
    }

    public static double calculateGroupMoney(Group group){
        double q = 0;
        if(group.getProducts() != null){
            for(int i = 0; i < group.getProducts().size(); i++) {
                q += group.getProducts().get(i).getPrice() * group.getProducts().get(i).getNumber();
            }
        }
        return q;
    }

    public static void addGroupToFile(Group group) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("./" + group.getName() + ".txt"))) {
            bw.write(group.getName().toUpperCase());
            bw.newLine();
            bw.write("Description: " + group.getDescription());
            bw.newLine();
            bw.write("Cost of products in this group: " + calculateGroupMoney(group));
            bw.newLine();
            if (group.getProducts() != null) {
                for (int d = 0; d < group.getProducts().size(); d++) {
                    bw.newLine();
                    bw.write(group.getProducts().get(d).getName().toUpperCase());
                    bw.write(group.getProducts().get(d).getName().toUpperCase());
                    bw.newLine();
                    bw.write("Price: " + group.getProducts().get(d).getPrice());
                    bw.newLine();
                    bw.write("Amount: " + Math.round(group.getProducts().get(d).getNumber()));
                    bw.newLine();
                    bw.write("Manufacturer: " + group.getProducts().get(d).getBrand());
                    bw.newLine();
                    bw.write("Description: " + group.getProducts().get(d).getDescription());
                    bw.newLine();
                }
                bw.newLine();
                bw.write("Total cost: " + calculateMoney(group) + " UAAH");
                bw.newLine();
                bw.write("Amount of products: " + calculateQuantity(group));
            } else bw.write("This group is empty");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void addStoreToFile(Store store) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("./" + "Store.txt"))) {
            String s = "Store ";
            bw.write(s);
            bw.newLine();
            long sumMoney = 0;
            double q = 0;
            for (int i = 0; i < store.getGroups().size(); i++) {
                if (store.getGroups().get(i).getProducts() != null) {
                    bw.newLine();
                    bw.write(store.getGroups().get(i).getName().toUpperCase());
                    bw.write("Description: " + store.getGroups().get(i).getDescription());
                    bw.write("Total cost of products in group: " + calculateGroupMoney(store.getGroups().get(i)));
                    bw.newLine();
                    for (int d = 0; d < store.getGroups().get(i).getProducts().size(); d++) {
                        bw.newLine();
                        bw.write(store.getGroups().get(i).getProducts().get(d).getName().toUpperCase());
                        bw.newLine();
                        bw.write("Price: " + store.getGroups().get(i).getProducts().get(d).getPrice());
                        bw.newLine();
                        bw.write("Amount " + Math.round(store.getGroups().get(i).getProducts().get(d).getNumber()));
                        bw.newLine();
                        bw.write("Manufacturer: " + store.getGroups().get(i).getProducts().get(d).getBrand());
                        bw.newLine();
                        bw.write("Description " + store.getGroups().get(i).getProducts().get(d).getDescription());
                        bw.newLine();
                    }
                    String[] str = s.split("\\s{3}");
                    sumMoney += calculateMoney(store.getGroups().get(i));
                    q += calculateQuantity(store.getGroups().get(i));

                } else {
                    bw.newLine();
                    bw.write(store.getGroups().get(i).getName() + ": this group is empty");
                    bw.newLine();
                }
            }
            bw.newLine();
            bw.write("Store has products with total cost of : " + sumMoney + " UAH");
            bw.newLine();
            bw.write("Products amount: " + q);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}