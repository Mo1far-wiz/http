package Interface.GUI;

import Interface.Program.Group;
import Interface.Program.Store;
import Structure.Client.User;
import Structure.Commands.UserCommand;
import Structure.Utility.Cypher;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class StorePanel extends JPanel {

    private Store store;

    public ProgramWindow getProgramWindow() {
        return programWindow;
    }

    private ProgramWindow programWindow;
    public StorePanel(Store store, ProgramWindow programWindow) {
        this.store = store;
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
        northPanel.add(titlePanel());
        northPanel.add(searchPanel());
        northPanel.add(categoriesTitlePanel());
        return northPanel;
    }
    private JPanel titlePanel() {
        JPanel title = new JPanel(new FlowLayout());
        title.setBackground(Colors.second);
        JLabel store = new JLabel("Store");
        store.setForeground(Colors.fifth);
        store.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
        title.add(store);
        return title;
    }
    private JPanel searchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBackground(Colors.first);
        JTextArea searchText = new JTextArea(1, 30);
        searchText.setFont(new Font(Font.SERIF, Font.PLAIN, 25));
        searchText.setBorder(new LineBorder(Colors.fourth));
        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(110, 33));
        searchButton.setBackground(Colors.second);
        searchButton.setForeground(Colors.fifth);
        searchButton.setFont(new Font(Font.SERIF, Font.PLAIN, 17));
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                try {
                    User.getInstance().getConnection().sendMessage(UserCommand.PRODUCT_FIND,
                            new JSONObject().put("searchText", searchText.getText()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        searchPanel.add(searchText);
        searchPanel.add(searchButton);
        return searchPanel;
    }
    private JPanel categoriesTitlePanel() {
        JPanel categoriesTitle = new JPanel(new FlowLayout());
        categoriesTitle.setBackground(Colors.second);
        JLabel types = new JLabel("All categories");
        types.setForeground(Colors.fifth);
        types.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
        categoriesTitle.add(types);
        return categoriesTitle;
    }
    private JPanel centerPanel() {
        JPanel centrePanel = new JPanel(new GridLayout(1, 1));
        centrePanel.setBackground(Colors.first);
        centrePanel.add(categoriesPanel());
        return centrePanel;
    }
    private JPanel categoriesPanel() {
        JPanel categories = new JPanel(new
                FlowLayout(FlowLayout.CENTER));
        categories.setBackground(Colors.first);
        categories.add(scroll());
        return categories;
    }
    private JScrollPane scroll() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setForeground(Colors.first);
        for (int i = 0; i < store.getGroups().size(); i++) {
            panel.add(oneCategoryPanel(store.getGroups().get(i)));
        }
        if (store.getGroups().size() < 9) {
            for (int n = 0; n < 9 - store.getGroups().size(); n++)
            {
                panel.add(falseProductPanel());
            }
        }
        JScrollPane scroll = new JScrollPane(panel);
        scroll.setBackground(Colors.first);
        scroll.setPreferredSize(new Dimension(700, 350));
        return scroll;
    }
    private JPanel falseProductPanel() {
        JPanel oneProductPanel = new JPanel(new BorderLayout());
// oneProductPanel.setToolTipText(product.getDescription());
        oneProductPanel.setBorder(new LineBorder(Colors.fourth));
        oneProductPanel.setPreferredSize(new Dimension(100, 40));
        oneProductPanel.setBackground(Colors.first);
        return oneProductPanel;
    }
    private JButton oneCategoryPanel(Group group) {
        JButton button = new JButton(group.getName());
        button.setFont(new Font("Century", Font.PLAIN, 18));
        button.setToolTipText(group.getDescription());

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                getProgramWindow().openGroupWindow(group);
                CurrentPanel.getInstance().setPanel(getProgramWindow().getGroupPanel());
                try {
                    User.getInstance().getConnection().sendMessage(UserCommand.GROUP_PRODUCT_LIST,
                            new JSONObject().put("group name", group.getName()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        button.setPreferredSize(new Dimension(130, 50));
        button.setBackground(Colors.second);
        button.setForeground(Colors.fifth);
        button.setFont(new Font("Arial", Font.PLAIN, 17));
        button.setBorder(new LineBorder(Colors.fourth));
        return button;
    }
    private JPanel southPanel() {
        JPanel southPanel = new JPanel(new GridLayout(1, 0));
        southPanel.setBackground(Colors.second);
        southPanel.setBorder(new LineBorder(Colors.fourth));
        southPanel.setPreferredSize(new Dimension(getWidth(), 33));
        southPanel.add(cancelButton());
        southPanel.add(addCategoryButton());
        southPanel.add(updateButton());
        southPanel.add(statisticsButton());
        return southPanel;
    }
    private JButton addCategoryButton() {
        JButton addCat = new JButton("Add");
        addCat.setPreferredSize(new Dimension(120, 25));
        addCat.setBackground(Colors.second);
        addCat.setForeground(Colors.fifth);
        addCat.setFont(new Font(Font.SERIF, Font.PLAIN, 17));
        addCat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                add(addCategoryPanel());
                revalidate();
            }
        });
        return addCat;
    }

    private JButton cancelButton() {
        JButton addCat = new JButton("Back");
        addCat.setPreferredSize(new Dimension(120, 25));
        addCat.setBackground(Colors.second);
        addCat.setForeground(Colors.fifth);
        addCat.setFont(new Font(Font.SERIF, Font.PLAIN, 17));
        addCat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                getProgramWindow().openConnectionPanel();
                revalidate();
            }
        });
        return addCat;
    }
    private JButton statisticsButton() {
        JButton addCat = new JButton("Statistics");
        addCat.setPreferredSize(new Dimension(120, 25));
        addCat.setBackground(Colors.second);
        addCat.setForeground(Colors.fifth);
        addCat.setFont(new Font(Font.SERIF, Font.PLAIN, 17));
        addCat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    User.getInstance().getConnection().sendMessage(UserCommand.SHOP_UPDATE,
                            new JSONObject());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        return addCat;
    }
    private JButton updateButton() {
        JButton addCat = new JButton("Update");
        addCat.setPreferredSize(new Dimension(120, 25));
        addCat.setBackground(Colors.second);
        addCat.setForeground(Colors.fifth);
        addCat.setFont(new Font(Font.SERIF, Font.PLAIN, 17));
        addCat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                try {
                    User.getInstance().getConnection().sendMessage(UserCommand.GROUP_LIST, new JSONObject().put(" ", " "));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        return addCat;
    }

    private JPanel addCategoryPanel() {
        JPanel addCategoryPanel = new JPanel(new BorderLayout());
        addCategoryPanel.setBackground(Colors.first);
        addCategoryPanel.add(addCatNorthPanel(), BorderLayout.NORTH);
        addCategoryPanel.add(addCatMenu(), BorderLayout.CENTER);
        addCategoryPanel.add(buttonsPanel(), BorderLayout.SOUTH);
        return addCategoryPanel;
    }
    private JPanel addCatNorthPanel() {
        JPanel addCatNorthPanel = new JPanel(new BorderLayout());
        addCatNorthPanel.setBackground(Colors.second);
        addCatNorthPanel.setPreferredSize(new Dimension(200, 70));
        JLabel store = new JLabel("Add category");
        store.setForeground(Colors.fifth);
        store.setBorder(new LineBorder(Colors.fourth));
        store.setHorizontalAlignment(SwingConstants.CENTER);
        store.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
        addCatNorthPanel.add(store, BorderLayout.CENTER);
        return addCatNorthPanel;
    }
    private JPanel addCatMenu() {
        JPanel menu = new JPanel();
        menu.setBackground(Colors.second);
        menu.add(addCategoryName());
        menu.add(addCategoryDetails());
        return menu;
    }
    JTextArea newCategory;
    private JPanel addCategoryName() {
        JPanel namePanel = new JPanel(new GridLayout(0, 1));
        namePanel.setPreferredSize(new Dimension(700, 150));
        namePanel.setBackground(Colors.second);
        JLabel addCategory = new JLabel("Enter name");
        addCategory.setHorizontalAlignment(SwingConstants.CENTER);
        addCategory.setVerticalAlignment(SwingConstants.CENTER);
        addCategory.setForeground(Colors.fifth);
        addCategory.setFont(new Font(Font.SERIF, Font.PLAIN, 22));
        newCategory = new JTextArea(1, 20);
        newCategory.setBorder(new LineBorder(Colors.fourth));
        newCategory.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        namePanel.add(addCategory, BorderLayout.NORTH);
        namePanel.add(newCategory, BorderLayout.SOUTH);
        return namePanel;
    }
    JTextArea newDetails;
    private JPanel addCategoryDetails() {
        JPanel namePanel = new JPanel(new GridLayout(0, 1));
        namePanel.setPreferredSize(new Dimension(700, 150));
        namePanel.setBackground(Colors.second);
        JLabel addCategory = new JLabel("Enter description");
        addCategory.setBackground(Colors.fifth);
        addCategory.setHorizontalAlignment(SwingConstants.CENTER);
        addCategory.setForeground(Color.BLACK);
        addCategory.setFont(new Font(Font.SERIF, Font.PLAIN, 22));
        newDetails = new JTextArea(3, 20);
        newDetails.setBorder(new LineBorder(Colors.fourth));
        newDetails.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        JScrollPane scroll = new JScrollPane(newDetails);
        scroll.setBackground(Colors.first);
        scroll.setPreferredSize(new Dimension(700, 250));
        namePanel.add(addCategory, BorderLayout.NORTH);
        namePanel.add(scroll, BorderLayout.SOUTH);
        return namePanel;
    }
    private JPanel buttonsPanel() {
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 0));
        buttonsPanel.setPreferredSize(new Dimension(getWidth(), 30));
        buttonsPanel.add(getBackButton());
        buttonsPanel.add(saveNewCategoryButton());
        return buttonsPanel;
    }
    private JButton getBackButton() {
        JButton getBack = new JButton("Back");
        getBack.setBackground(Colors.second);
        getBack.setForeground(Colors.fifth);
        getBack.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        getBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                programWindow.openStoreWindow();
                revalidate();
            }
        });
        return getBack;
    }


    private JButton saveNewCategoryButton() {
        JButton saveCategory = new JButton("Save");
        saveCategory.setBackground(Colors.second);
        saveCategory.setForeground(Colors.fifth);
        saveCategory.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        saveCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                try {
                    User.getInstance().getConnection().sendMessage(UserCommand.GROUP_INSERT,
                            new JSONObject().put("name", newCategory.getText())
                                    .put("description", newDetails.getText()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                };
            }
        });
        return saveCategory;
    }
    private void showEmptyAreaError(){
        JFrame errorMessage = new JFrame();
        errorMessage.setSize(500, 150);
        errorMessage.setLocationRelativeTo(null);
        JPanel northPanel1 = new JPanel();
        JPanel southPanel1 = new JPanel(new GridLayout(1, 1));
        errorMessage.add(northPanel1, BorderLayout.CENTER);
        errorMessage.add(southPanel1, BorderLayout.SOUTH);
        northPanel1.setBackground(Colors.first);
        southPanel1.setBackground(Colors.first);
        JLabel label = new JLabel("Enter name and description.");
        label.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
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
                newCategory.setText("");
            }
        });
    }
    private void showUnicNameError() {
        JFrame errorMessage = new JFrame();
        errorMessage.setSize(500, 150);
        errorMessage.setLocationRelativeTo(null);
        JPanel northPanel1 = new JPanel();
        JPanel southPanel1 = new JPanel(new GridLayout(1, 1));
        errorMessage.add(northPanel1, BorderLayout.CENTER);
        errorMessage.add(southPanel1, BorderLayout.SOUTH);
        northPanel1.setBackground(Colors.first);
        southPanel1.setBackground(Colors.first);
        JLabel label = new JLabel("Category with this name already exists.");
                label.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
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
                newCategory.setText("");
            }
        });
    }
}