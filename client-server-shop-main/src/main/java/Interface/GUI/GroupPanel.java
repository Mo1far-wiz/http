package Interface.GUI;

import Interface.Program.Group;
import Interface.Program.Product;
import Structure.Client.User;
import Structure.Commands.UserCommand;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class GroupPanel extends JPanel {
    private Group group;

    public ProgramWindow getProgramWindow() {
        return programWindow;
    }

    private ProgramWindow programWindow;

    GroupPanel(Group group, ProgramWindow programWindow) {
        this.programWindow = programWindow;
        this.group = group;
        this.setLayout(new BorderLayout());
        init();
    }

    private void init() {
        setBackground(Colors.first);
        add(northPanel(), "North");
        add(centerPanel(), "Center");
        add(southPanel(), "South");
    }

    private JPanel northPanel() {
        JPanel northPanel = new JPanel(new GridLayout(2, 1));
        northPanel.add(titleProductPanel());
        northPanel.add(productsTitle());
        return northPanel;
    }

    private JPanel titleProductPanel() {
        JPanel titlePanel = new JPanel(new
                FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Colors.first);
        JLabel title = new JLabel(group.getName());
        title.setForeground(Colors.fifth);
        title.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
        titlePanel.add(title);
        return titlePanel;
    }

    private JPanel productsTitle() {
        JPanel productsTitle = new JPanel(new FlowLayout());
        productsTitle.setBackground(Colors.first);
        JLabel products = new JLabel("Available products");
        products.setForeground(Colors.fifth);
        products.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
        productsTitle.add(products);
        return productsTitle;
    }

    private JPanel centerPanel() {
        JPanel centrePanel = new JPanel(new
                FlowLayout(FlowLayout.CENTER));
        centrePanel.setBackground(Colors.second);
        centrePanel.add(scrollProductsPanel());
        return centrePanel;
    }

    private JScrollPane scrollProductsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Colors.first);
        panel.setBackground(Color.WHITE);
        panel.add(header(), BorderLayout.NORTH);
        JPanel prodPanel = new JPanel(new GridLayout(0, 1));

        ArrayList<Product> productList = group.getProducts();
        if (productList != null) {
            for (int i = 0; i < productList.size(); i++) {

                prodPanel.add(oneProductPanel(productList.get(i)));
            }
            if (productList.size() < 9) {
                for (int n = 0; n < 9 - productList.size();
                     n++) {
                    prodPanel.add(falseProductPanel());
                }
            }
        }

        panel.add(prodPanel, BorderLayout.CENTER);
        JScrollPane scroll = new JScrollPane(panel);
        scroll.setBackground(Colors.second);
        scroll.setPreferredSize(new Dimension(700, 400));
        return scroll;
    }

    private JPanel falseProductPanel() {
        JPanel oneProductPanel = new JPanel(new BorderLayout());
      //  oneProductPanel.setToolTipText(product.getDescription());
        oneProductPanel.setBorder(new LineBorder(Colors.fourth));
        oneProductPanel.setPreferredSize(new Dimension(100, 40));
        oneProductPanel.setBackground(Colors.second);
        return oneProductPanel;
    }

    private JPanel header() {
        JPanel headerTable = new JPanel(new GridLayout(1, 0));
        headerTable.setPreferredSize(new Dimension(200, 40));
        headerTable.setBackground(Colors.second);
        JLabel productName = new JLabel("Name");
        productName.setHorizontalAlignment(SwingConstants.CENTER);
        productName.setBorder(new LineBorder(Colors.fourth));
        productName.setFont(new Font("Century", Font.PLAIN, 15));
        productName.setForeground(Color.white);
        JLabel productPrice = new JLabel("Price");
        productPrice.setHorizontalAlignment(SwingConstants.CENTER);
        productPrice.setSize(30, 20);
        productPrice.setFont(new Font("Century", Font.PLAIN, 15));
        productPrice.setBorder(new LineBorder(Colors.fourth));
        productPrice.setForeground(Color.white);
        JLabel productNumber = new JLabel("Amount");
        productNumber.setHorizontalAlignment(SwingConstants.CENTER);
        productNumber.setFont(new Font("Century", Font.PLAIN, 15));
        productNumber.setBorder(new LineBorder(Colors.fourth));
        productNumber.setForeground(Color.white);
        JLabel productBrand = new JLabel("Manufacturer");
        productBrand.setHorizontalAlignment(SwingConstants.CENTER);
        productBrand.setBorder(new LineBorder(Colors.fourth));
        productBrand.setFont(new Font("Century", Font.PLAIN, 15));
        productBrand.setForeground(Color.white);
        JButton statisticsButton = new JButton("Information");
        statisticsButton.setBorder(new LineBorder(Colors.fourth));
        statisticsButton.setPreferredSize(new Dimension(120, 15));
        statisticsButton.setBackground(Colors.second);
        statisticsButton.setForeground(Colors.fifth);
        statisticsButton.setFont(new Font("Century", Font.PLAIN,
                15));
        headerTable.add(productName);
        headerTable.add(productPrice);
        headerTable.add(productNumber);
        headerTable.add(productBrand);
        headerTable.add(statisticsButton);
        return headerTable;
    }

    private JPanel fullProductPanel(Product product) {
        JPanel productPanel = new JPanel(new GridLayout(1, 0));
        productPanel.setPreferredSize(new Dimension(200, 20));
        productPanel.setBackground(Colors.fifth);
        JLabel productName = new JLabel(" " + product.getName());
        productName.setBorder(new LineBorder(Colors.fourth));
        productName.setFont(new Font("Century", Font.PLAIN, 16));
        JLabel productPrice = new JLabel("" + product.getPrice() + " грн");
        productPrice.setHorizontalAlignment(SwingConstants.CENTER);
        productPrice.setSize(30, 20);
        productPrice.setFont(new Font("Century", Font.PLAIN, 16));
        productPrice.setBorder(new LineBorder(Colors.fourth));
        productPrice.setBackground(Color.WHITE);
        JLabel productNumber = new JLabel(+product.getNumber() + "");
        productNumber.setHorizontalAlignment(SwingConstants.CENTER);
        productNumber.setFont(new Font("Century", Font.PLAIN, 16));
        productNumber.setBorder(new LineBorder(Colors.fourth));
        JLabel productBrand = new JLabel(product.getBrand());
        productBrand.setHorizontalAlignment(SwingConstants.CENTER);
        productBrand.setBorder(new LineBorder(Colors.fourth));
        productBrand.setFont(new Font("Century", Font.PLAIN, 16));
        JButton button = new JButton("Details");
        button.setBorder(new LineBorder(Colors.fourth));
        button.setPreferredSize(new Dimension(120, 15));
        button.setBackground(Colors.second);
        button.setForeground(Colors.fifth);
        button.setFont(new Font("Century", Font.PLAIN, 12));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GroupPanel.this.removeAll();
                programWindow.openProductWindow(product);
                CurrentPanel.getInstance().setPanel(getProgramWindow().getProductPanel());
            }
        });
        productPanel.add(productName);
        productPanel.add(productPrice);
        productPanel.add(productNumber);
        productPanel.add(productBrand);
        productPanel.add(button);
        return productPanel;
    }

    private JPanel oneProductPanel(Product product) {
        JPanel oneProductPanel = new JPanel(new BorderLayout());
        oneProductPanel.setBorder(new LineBorder(Colors.fifth));
        oneProductPanel.setPreferredSize(new Dimension(100, 40));
        oneProductPanel.setBackground(Colors.third);
        oneProductPanel.add(fullProductPanel(product),
                BorderLayout.CENTER);
        return oneProductPanel;
    }

    private JPanel southPanel() {
        JPanel southPanel = new JPanel(new GridLayout(1, 0));
        southPanel.setBackground(Colors.first);
        southPanel.setPreferredSize(new Dimension(1200, 30));
        southPanel.add(getBackButton());
        southPanel.add(updateButton());
        southPanel.add(addProductButton());
        southPanel.add(deleteCategoryButton());
        return southPanel;
    }

    private JButton getBackButton() {
        JButton returnBack = new JButton("Back");
        returnBack.setPreferredSize(new Dimension(120, 25));
        returnBack.setBackground(Colors.second);
        returnBack.setForeground(Colors.fifth);
        returnBack.setFont(new Font(Font.SERIF, Font.PLAIN, 17));
        returnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                CurrentPanel.getInstance().setPanel(getProgramWindow().getStorePanel());
                getProgramWindow().openStoreWindow();
                revalidate();
            }
        });
        return returnBack;
    }

    private JButton updateButton() {
        JButton saveData = new JButton("Update");
        saveData.setPreferredSize(new Dimension(120, 25));
        saveData.setBackground(Colors.second);
        saveData.setForeground(Colors.fifth);
        saveData.setFont(new Font(Font.SERIF, Font.PLAIN, 17));
        saveData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    User.getInstance().getConnection().sendMessage(UserCommand.GROUP_PRODUCT_LIST,
                            new JSONObject().put("group name", group.getName()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        return saveData;
    }

    private JButton addProductButton() {
        JButton addProduct = new JButton("Add");
        addProduct.setPreferredSize(new Dimension(120, 25));
        addProduct.setBackground(Colors.second);
        addProduct.setForeground(Colors.fifth);
        addProduct.setFont(new Font(Font.SERIF, Font.PLAIN, 17));
        addProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GroupPanel.this.removeAll();
                add(addProductPanel());
            }
        });
        return addProduct;
    }

    private JButton deleteCategoryButton() {
        JButton deleteCategory = new JButton("Delete");
        deleteCategory.setPreferredSize(new Dimension(120, 25));
        deleteCategory.setBackground(Colors.third);
        deleteCategory.setForeground(Colors.fifth);
        deleteCategory.setFont(new Font(Font.SERIF, Font.PLAIN, 17));
        deleteCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame ad1 = new JFrame();
                ad1.setSize(400, 150);
                ad1.setLocationRelativeTo(null);
                JPanel northPanel1 = new JPanel();
                JPanel southPanel1 = new JPanel(new GridLayout(1,
                        1));
                ad1.add(northPanel1, BorderLayout.CENTER);
                ad1.add(southPanel1, BorderLayout.SOUTH);
                northPanel1.setBackground(Colors.second);
                southPanel1.setBackground(Colors.second);
                JLabel label = new JLabel("Are you sure?");
                label.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
                JButton yes = new JButton("Yes");
                JButton no = new JButton("No");
                yes.setBackground(Colors.second);
                yes.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
                yes.setForeground(Colors.fifth);
                northPanel1.add(label, BorderLayout.CENTER);
                southPanel1.add(yes, BorderLayout.WEST);
                southPanel1.add(no, BorderLayout.EAST);
                ad1.setVisible(true);
                yes.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        removeAll();
                        ad1.setVisible(false);
                        try {
                            User.getInstance().getConnection().sendMessage(UserCommand.GROUP_DELETE,
                                    new JSONObject().put("group name", group.getName()));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                no.setBackground(Colors.second);
                no.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
                no.setForeground(Colors.fifth);
                no.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ad1.setVisible(false);
                    }
                });
            }
        });
        return deleteCategory;
    }

    private JTextArea nameArea;
    private JTextArea priceArea;
    private JTextArea numberArea;
    private JTextArea brandArea;
    private JTextArea descriptionArea;

    private JPanel addProductPanel() {
        repaint();
        revalidate();
        JPanel addProductPanel = new JPanel(new BorderLayout());
        addProductPanel.setBackground(Colors.first);
        addProductPanel.add(southProductPanel(), BorderLayout.SOUTH);
        addProductPanel.add(northProductPanel(), BorderLayout.NORTH);
        addProductPanel.add(centerProductPanel(),
                BorderLayout.CENTER);
        return addProductPanel;
    }

    private JPanel northProductPanel() {
        JPanel northProductPanel = new JPanel(new GridLayout(3, 1));
        northProductPanel.setBackground(Colors.first);
        northProductPanel.add(titleProdPanel());
        return northProductPanel;
    }

    private JPanel titleProdPanel() {
        JPanel titlePanel = new JPanel(new
                FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Colors.second);
        JLabel title = new JLabel("Add product");
        title.setForeground(Colors.fifth);
        title.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
        titlePanel.add(title);
        return titlePanel;
    }

    private JPanel centerProductPanel() {
        JPanel centrePanel = new JPanel();
        centrePanel.setBackground(Colors.first);
        centrePanel.add(infoPanel());
        return centrePanel;
    }

    private JPanel infoPanel() {
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setPreferredSize(new Dimension(700, 350));
        JPanel info = new JPanel(new GridLayout(0, 1));
        info.add(namePanel());
        info.add(pricePanel());
        info.add(numberPanel());
        info.add(brandPanel());
        info.add(falsePanel());
        infoPanel.add(info, BorderLayout.NORTH);
        infoPanel.add(descriptionPanel(), BorderLayout.CENTER);
        return infoPanel;
    }

    private JPanel namePanel() {
        JPanel namePanel = new JPanel(new GridLayout(1, 0));
        namePanel.setBackground(Colors.second);
        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setForeground(Colors.fifth);
        nameLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setBorder(new LineBorder(Colors.fourth));
        nameArea = new JTextArea();
        nameArea.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        nameArea.setBorder(new LineBorder(Colors.fourth));
        namePanel.add(nameLabel);
        namePanel.add(nameArea);
        return namePanel;
    }

    private JPanel pricePanel() {
        JPanel pricePanel = new JPanel(new GridLayout(1, 0));
        pricePanel.setBackground(Colors.second);
        JLabel priceLabel = new JLabel("Price: ");
        priceLabel.setForeground(Colors.fifth);
        priceLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        priceLabel.setBorder(new LineBorder(Colors.fourth));
        priceArea = new JTextArea();
        priceArea.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        priceArea.setBorder(new LineBorder(Colors.fourth));
        pricePanel.add(priceLabel);
        pricePanel.add(priceArea);
        return pricePanel;
    }

    private JPanel numberPanel() {
        JPanel numberPanel = new JPanel(new GridLayout(1, 0));
        numberPanel.setBackground(Colors.second);
        JLabel numberLabel = new JLabel("Amount: ");
        numberLabel.setForeground(Colors.fifth);
        numberLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numberLabel.setBorder(new LineBorder(Colors.fourth));
        numberArea = new JTextArea();
        numberArea.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        numberArea.setBorder(new LineBorder(Colors.fourth));
        numberPanel.add(numberLabel);
        numberPanel.add(numberArea);
        return numberPanel;
    }

    private JPanel brandPanel() {
        JPanel brandPanel = new JPanel(new GridLayout(1, 0));
        brandPanel.setBackground(Colors.second);
        JLabel brandLabel = new JLabel("Manufacturer: ");
        brandLabel.setForeground(Colors.fifth);
        brandLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        brandLabel.setHorizontalAlignment(SwingConstants.CENTER);
        brandLabel.setHorizontalAlignment(SwingConstants.CENTER);
        brandLabel.setBorder(new LineBorder(Colors.fourth));
        brandArea = new JTextArea();
        brandArea.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        brandArea.setBorder(new LineBorder(Colors.fourth));
        brandPanel.add(brandLabel);
        brandPanel.add(brandArea);
        return brandPanel;
    }

    private JPanel descriptionPanel() {
        JPanel descriptionPanel = new JPanel(new BorderLayout());
        descriptionPanel.setBackground(Colors.second);
        descriptionPanel.setPreferredSize(new Dimension(500, 100));
        JLabel descriptionLabel = new JLabel("Description: ");
        descriptionLabel.setForeground(Colors.fifth);
        descriptionLabel.setFont(new Font(Font.SERIF, Font.PLAIN,
                20));

        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        descriptionLabel.setBorder(new LineBorder(Colors.fourth));
        descriptionArea = new JTextArea();
        descriptionArea.setFont(new Font(Font.SERIF, Font.PLAIN,
                18));
        descriptionArea.setBorder(new LineBorder(Colors.fourth));
        descriptionArea.setSize(300, 100);
        descriptionPanel.add(descriptionLabel, BorderLayout.NORTH);
        descriptionPanel.add(descriptionArea, BorderLayout.CENTER);
        return descriptionPanel;
    }

    private JPanel falsePanel() {
        JPanel falsePanel = new JPanel(new GridLayout(0, 1));
        falsePanel.setBackground(Colors.second);
        return falsePanel;
    }

    private JPanel southProductPanel() {
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 0));
        buttonsPanel.setBackground(Colors.second);
        JButton getBackButton = new JButton("Back");
        getBackButton.setBackground(Colors.second);
        getBackButton.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        getBackButton.setForeground(Colors.fifth);
        getBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                getProgramWindow().openGroupWindow(group);
                revalidate();
            }
        });
        JButton saveButton = new JButton("Save");
        saveButton.setBackground(Colors.second);
        saveButton.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        saveButton.setForeground(Colors.fifth);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    removeAll();
                    User.getInstance().getConnection().sendMessage(UserCommand.PRODUCT_INSERT,
                            new JSONObject().put("group", group.getName())
                                    .put("name", nameArea.getText())
                                    .put("amount", numberArea.getText())
                                    .put("price", priceArea.getText())
                                    .put("brand", brandArea.getText())
                                    .put("description", descriptionArea.getText())
                    );
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        buttonsPanel.add(getBackButton);
        buttonsPanel.add(saveButton);
        return buttonsPanel;
    }
}