package Interface.GUI;

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

public class ProductPanel extends JPanel {
    private Product product;

    public ProgramWindow getProgramWindow() {
        return programWindow;
    }

    private ProgramWindow programWindow;
    private JTextArea nameArea;
    private JTextArea priceArea;
    private JTextArea numberArea;
    private JTextArea brandArea;
    private JTextArea descriptionArea;

    public JFrame getReduceProductWindow() {
        return reduceProductWindow;
    }

    private JFrame reduceProductWindow;

    public JFrame getAdProductWindow() {
        return adProductWindow;
    }

    private JFrame adProductWindow;

    ProductPanel(Product product, ProgramWindow programWindow) {
        this.product = product;
        this.programWindow = programWindow;
        this.setLayout(new BorderLayout());
        init();
    }

    private void init() {
        setBackground(Colors.first);
        add(northPanel(), BorderLayout.NORTH);
        add(centerPanel(), BorderLayout.CENTER);
        add(southPanel(), BorderLayout.SOUTH);
    }

    private JPanel northPanel() {
        JPanel northPanel = new JPanel(new GridLayout(3, 1));
        northPanel.setBackground(Colors.first);
        northPanel.add(titlePanel());
        return northPanel;
    }

    private JPanel titlePanel() {
        JPanel titlePanel = new JPanel(new
                FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Colors.first);
        JLabel title = new JLabel(product.getName());
        title.setForeground(Colors.fifth);
        title.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
        titlePanel.add(title);
        return titlePanel;
    }

    private JPanel centerPanel() {
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
        infoPanel.add(savePanel(), BorderLayout.SOUTH);
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
        nameArea = new JTextArea("" + product.getName() +
                "");
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
        priceArea = new JTextArea("" + product.getPrice()
                + "");
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
        numberArea = new JTextArea("" +
                product.getNumber() + "");
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
        brandArea = new JTextArea("" + product.getBrand()
                + "");
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
        descriptionArea = new JTextArea("" +
                product.getDescription() + "");
        descriptionArea.setFont(new Font(Font.SERIF, Font.PLAIN,
                18));
        descriptionArea.setBorder(new LineBorder(Colors.fourth));
        descriptionArea.setSize(300, 100);
        descriptionPanel.add(descriptionLabel, BorderLayout.NORTH);
        descriptionPanel.add(descriptionArea, BorderLayout.CENTER);
        return descriptionPanel;
    }

    private JPanel savePanel() {
        JPanel savePanel = new JPanel(new GridLayout(0, 1));
        savePanel.setBackground(Colors.second);
        JButton saveButton = new JButton("Save");
        saveButton.setBackground(Colors.second);
        saveButton.setFont(new Font(Font.SERIF, Font.PLAIN,
                25));
        saveButton.setForeground(Colors.fifth);
        saveButton.setBorder(new LineBorder(Colors.fourth));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    User.getInstance().getConnection().sendMessage(UserCommand.PRODUCT_UPDATE,
                            new JSONObject()
                                    .put("old name", product.getName())
                                    .put("new name", nameArea.getText())
                                    .put("amount", numberArea.getText())
                                    .put("price", priceArea.getText())
                                    .put("brand", brandArea.getText())
                                    .put("description", descriptionArea.getText()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        savePanel.add(saveButton);
        return savePanel;
    }

    private JPanel falsePanel() {
        JPanel falsePanel = new JPanel(new GridLayout(0, 1));
        falsePanel.setBackground(Colors.second);
        return falsePanel;
    }

    private JPanel southPanel() {
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 0));
        buttonsPanel.setBackground(Colors.second);
        JButton getBackButton = new JButton("Back");
        getBackButton.setBackground(Colors.second);
        getBackButton.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        getBackButton.setForeground(Colors.fifth);
        getBackButton.setBorder(new LineBorder(Colors.fourth));
        getBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                CurrentPanel.getInstance().setPanel(programWindow.getGroupPanel());
                programWindow.openGroupWindow(programWindow.getCurrentGroup());
                try {
                    User.getInstance().getConnection().sendMessage(UserCommand.GROUP_PRODUCT_LIST,
                            new JSONObject().put("group name", programWindow.getCurrentGroup().getName()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        JButton addNumberButton = new JButton("Add");
        addNumberButton.setBackground(Colors.second);
        addNumberButton.setFont(new Font(Font.SERIF, Font.PLAIN,
                16));
        addNumberButton.setForeground(Colors.fifth);
        addNumberButton.setBorder(new LineBorder(Colors.fourth));
        addNumberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame adProductWindow = new JFrame();
                openAddProductWindow();
                revalidate();
            }
        });
        JButton deleteNumberButton = new JButton("Delete number");
        deleteNumberButton.setBackground(Colors.third);
        deleteNumberButton.setFont(new Font(Font.SERIF, Font.PLAIN,
                16));
        deleteNumberButton.setForeground(Colors.fifth);
        deleteNumberButton.setBorder(new
                LineBorder(Colors.fourth));
        deleteNumberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openReduceProductWindow();
                revalidate();
            }
        });
        JButton deleteProductButton = new JButton("Delete");
        deleteProductButton.setBackground(Colors.third);
        deleteProductButton.setFont(new Font(Font.SERIF, Font.PLAIN,
                16));
        deleteProductButton.setForeground(Colors.fifth);
        deleteProductButton.setBorder(new
                LineBorder(Colors.fourth));
        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteProductWindow();
            }
        });
        buttonsPanel.add(getBackButton);
        buttonsPanel.add(addNumberButton);
        buttonsPanel.add(deleteNumberButton);
        buttonsPanel.add(deleteProductButton);
        return buttonsPanel;
    }

    private void openAddProductWindow() {
        adProductWindow = new JFrame();
        adProductWindow.setSize(400, 150);
        adProductWindow.setLocationRelativeTo(null);
        JPanel northPanel = new JPanel();
        northPanel.setBackground(Colors.first);
        JPanel centerPanel = new JPanel(new GridLayout(1, 1));
        centerPanel.setBackground(Colors.first);
        JPanel southPanel = new JPanel(new GridLayout(1, 2));
        southPanel.setBackground(Colors.first);
        adProductWindow.add(northPanel, BorderLayout.NORTH);
        adProductWindow.add(centerPanel, BorderLayout.CENTER);
        adProductWindow.add(southPanel, BorderLayout.EAST);
        JLabel howManyLabel = new JLabel("How many products do you want to add?");
        howManyLabel.setForeground(Colors.fifth);
        howManyLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        JTextField textField = new JTextField();
        textField.setSize(50, 50);
        textField.setFont(new Font(Font.SERIF, Font.PLAIN, 45));
        JButton addProduct = new JButton("Add");
        addProduct.setBackground(Colors.second);
        addProduct.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        addProduct.setForeground(Colors.second);
        addProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                try {
                    User.getInstance().getConnection().sendMessage(UserCommand.PRODUCT_AMOUNT_ADD,
                            new JSONObject().put("product", product.getName())
                                    .put("amount", textField.getText())
                    );
                } catch (IOException exc) {
                    throw new RuntimeException(exc);
                }
            }
        });
        northPanel.add(howManyLabel);
        centerPanel.add(textField);
        southPanel.add(addProduct);
        adProductWindow.setVisible(true);
    }

    private void openReduceProductWindow() {
        reduceProductWindow = new JFrame();
        reduceProductWindow.setSize(400, 150);
        reduceProductWindow.setLocationRelativeTo(null);
        JPanel northPanel = new JPanel();
        northPanel.setBackground(Colors.second);
        JPanel centerPanel = new JPanel(new GridLayout(1, 1));
        centerPanel.setBackground(Colors.first);
        JPanel southPanel = new JPanel(new GridLayout(1, 2));
        southPanel.setBackground(Colors.first);
        reduceProductWindow.add(northPanel, BorderLayout.NORTH);
        reduceProductWindow.add(centerPanel, BorderLayout.CENTER);
        reduceProductWindow.add(southPanel, BorderLayout.EAST);
        JLabel howMany = new JLabel("How many products do you want to delete?");
        howMany.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        JTextField textField = new JTextField();
        textField.setSize(50, 50);
        textField.setFont(new Font(Font.SERIF, Font.PLAIN, 45));
        JButton addProduct = new JButton("Delete");
        addProduct.setBackground(Colors.second);
        addProduct.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        addProduct.setForeground(Colors.fifth);
        addProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                try {
                    User.getInstance().getConnection().sendMessage(UserCommand.PRODUCT_AMOUNT_REDUCE,
                            new JSONObject().put("product", product.getName())
                                    .put("amount", textField.getText())
                    );
                } catch (IOException exc) {
                    throw new RuntimeException(exc);
                }
            }
        });
        northPanel.add(howMany);
        centerPanel.add(textField);
        southPanel.add(addProduct);
        reduceProductWindow.setVisible(true);
    }

    private void deleteProductWindow() {
        JFrame ad1 = new JFrame();
        ad1.setSize(400, 150);
        ad1.setLocationRelativeTo(null);
        JPanel northPanel1 = new JPanel();
        JPanel southPanel1 = new JPanel(new GridLayout(1, 1));
        ad1.add(northPanel1, BorderLayout.CENTER);
        ad1.add(southPanel1, BorderLayout.SOUTH);
        northPanel1.setBackground(Colors.second);
        southPanel1.setBackground(Colors.second);
        JLabel label = new JLabel("Are you sure?");
        label.setForeground(Colors.fifth);
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
                ad1.setVisible(false);
                removeAll();
                try {
                    User.getInstance().getConnection().sendMessage(UserCommand.PRODUCT_DELETE,
                            new JSONObject().put("product", product.getName())
                    );
                } catch (IOException exc) {
                    throw new RuntimeException(exc);
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

    public void showIllegalFormat(String message) {
        JFrame errorMessage = new JFrame();
        errorMessage.setSize(500, 150);
        errorMessage.setLocationRelativeTo(null);
        JPanel northPanel1 = new JPanel();
        JPanel southPanel1 = new JPanel(new GridLayout(1, 1));
        errorMessage.add(northPanel1, BorderLayout.CENTER);
        errorMessage.add(southPanel1, BorderLayout.SOUTH);
        northPanel1.setBackground(Colors.second);
        southPanel1.setBackground(Colors.second);
        JLabel label = new JLabel(message + " -> incorrect query.");
        label.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        JButton yes = new JButton("Submit");
        yes.setBackground(Colors.second);
        yes.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        yes.setForeground(Colors.fifth);
        northPanel1.add(label, BorderLayout.CENTER);
        southPanel1.add(yes, BorderLayout.WEST);
        errorMessage.setVisible(true);
        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                errorMessage.removeAll();
                errorMessage.setVisible(false);
            }
        });
    }
}

