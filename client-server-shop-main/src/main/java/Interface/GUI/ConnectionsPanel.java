package Interface.GUI;

import Interface.Program.Store;
import Structure.Client.ClientTCP;
import Structure.Client.User;
import Structure.Commands.UserCommand;
import Database.Connections;
import Structure.Utility.Cypher;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ConnectionsPanel extends JPanel {
    private Store store;
    private ArrayList<Connections> users;

    public ProgramWindow getProgramWindow() {
        return programWindow;
    }

    private ProgramWindow programWindow;

    public JFrame getPasswordWindow() {
        return passwordWindow;
    }

    private JFrame passwordWindow;

    public JFrame getPasswordUserWindow() {
        return passwordUserWindow;
    }

    private JFrame passwordUserWindow;
    public ConnectionsPanel(Store store, ProgramWindow programWindow) {
        this.store = store;
        this.users = programWindow.getUsers();

        this.programWindow = programWindow;
        this.setLayout(new BorderLayout());
        init();
        setVisible(true);

        CurrentPanel.getInstance().setPanel(this);
    }
    private void init() {
        setBackground(Colors.first);                                               // first
        add(northPanel(), BorderLayout.NORTH);
        add(centerPanel(), BorderLayout.CENTER);
        add(southPanel(), BorderLayout.SOUTH);
    }
    private JPanel northPanel() {
        JPanel northPanel = new JPanel(new GridLayout(3, 1));
        northPanel.add(titlePanel());
        northPanel.add(falseProductPanel());
        northPanel.add(categoriesTitlePanel());
        return northPanel;
    }
    private JPanel titlePanel() {
        JPanel title = new JPanel(new FlowLayout());
        title.setBackground(Colors.second);                                             // second
        JLabel store = new JLabel("CONNECTIONS");
        store.setForeground(Colors.fifth);                                              // fifth
        store.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
        title.add(store);
        return title;
    }
    private JPanel categoriesTitlePanel() {
        JPanel forTitle = new JPanel(new FlowLayout());
        forTitle.setBackground(Colors.first);                                           // first
        JPanel titles = new JPanel(new GridLayout(1, 2));
        titles.setBackground(Colors.first);                                             // first
        JLabel login = new JLabel("Login");
        JLabel address = new JLabel("Address");
        login.setForeground(Colors.fifth);                                              // fifth
        login.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
        address.setForeground(Colors.fifth);                                            // fifth
        address.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
        titles.add(login);
        titles.add(address);
        forTitle.add(titles);
        return forTitle;
    }
    private JPanel centerPanel() {
        JPanel centrePanel = new JPanel(new GridLayout(1, 1));
        centrePanel.setBackground(Colors.first);                    // first
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
        panel.setForeground(Colors.fifth);
        for (int i = 0; i < users.size(); i++) {
            panel.add(oneLinePanel(users.get(i)));
        }
       if (users.size() < 9) {
            for (int n = 0; n < 9 - users.size(); n++)
            {
                panel.add(falseProductPanel());
            }
        }
        JScrollPane scroll = new JScrollPane(panel);
        scroll.setBackground(Colors.first);                                                 // first
        scroll.setPreferredSize(new Dimension(700, 350));
        return scroll;
    }
    private JPanel falseProductPanel() {
        JPanel oneProductPanel = new JPanel(new BorderLayout());
        oneProductPanel.setPreferredSize(new Dimension(100, 40));
        oneProductPanel.setBackground(Colors.first);
        return oneProductPanel;
    }
    public JPanel oneLinePanel(Connections connection) {
        JPanel panel = new JPanel(new GridLayout(1, 2));

        JButton login = new JButton(connection.getLogin());
        JButton address = new JButton(connection.getHost() + ": " + connection.getPort());
        login.setFont(new Font("Century", Font.PLAIN, 18));
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPasswordUserWindow(connection.getLogin());
            }
        });
        login.setPreferredSize(new Dimension(130, 50));
        login.setBackground(Colors.fifth);
        login.setForeground(Colors.first);
        login.setFont(new Font("Arial", Font.PLAIN, 17));
        login.setBorder(new LineBorder(Colors.fourth));

        address.setPreferredSize(new Dimension(130, 50));
        address.setBackground(Colors.fifth);
        address.setForeground(Colors.first);
        address.setFont(new Font("Arial", Font.PLAIN, 17));
        address.setBorder(new LineBorder(Colors.fourth));

        panel.add(login);
        panel.add(address);
        return panel;
    }

    private JPanel southPanel() {
        JPanel southPanel = new JPanel(new GridLayout(1, 0));
        southPanel.setBackground(Colors.first);                                                    // first
        southPanel.setBorder(new LineBorder(Color.WHITE));
        southPanel.setPreferredSize(new Dimension(getWidth(), 33));
        southPanel.add(addCategoryButton());
        return southPanel;
    }
    private JButton addCategoryButton() {
        JButton addCat = new JButton("Add");
        addCat.setPreferredSize(new Dimension(120, 25));
        addCat.setBackground(Colors.second);                                            // second
        addCat.setForeground(Colors.fifth);                                             // fifth
        addCat.setFont(new Font(Font.SERIF, Font.PLAIN, 17));
        addCat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                add(addAddressPanel());
                revalidate();
            }
        });
        return addCat;
    }

    private JTextArea loginArea;
    private JTextArea passwordArea;
    private JTextArea hostnameArea;
    private JTextArea portArea;

    private JPanel addAddressPanel() {
        repaint();
        revalidate();
        JPanel newAddressPanel = new JPanel(new BorderLayout());
        newAddressPanel.setBackground(Colors.first);                                        // first
        newAddressPanel.add(southProductPanel(), BorderLayout.SOUTH);
        newAddressPanel.add(northProductPanel(), BorderLayout.NORTH);
        newAddressPanel.add(centerProductPanel(),
                BorderLayout.CENTER);
        return newAddressPanel;
    }
    private JPanel northProductPanel() {
        JPanel northProductPanel = new JPanel(new GridLayout(3, 1));
        northProductPanel.setBackground(Colors.first);                                      // first
        northProductPanel.add(titleAddressPanel());
        return northProductPanel;
    }
    private JPanel titleAddressPanel() {
        JPanel titlePanel = new JPanel(new
                FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Colors.second);                                            // second
        JLabel title = new JLabel("Add connection");
        title.setForeground(Colors.fifth);
        title.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
        titlePanel.add(title);
        return titlePanel;
    }
    private JPanel centerProductPanel() {
        JPanel centrePanel = new JPanel();
        centrePanel.setBackground(Colors.first);                                            // first
        centrePanel.add(infoPanel());
        return centrePanel;
    }
    private JPanel infoPanel() {
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(Colors.first);                                              // first
        infoPanel.setPreferredSize(new Dimension(700, 350));
        JPanel info = new JPanel(new GridLayout(0, 1));
        info.add(loginPanel());
        info.add(falsePanel());
        info.add(passwordPanel());
        info.add(falsePanel());
        info.add(hostnamePanel());
        info.add(falsePanel());
        info.add(portPanel());
        info.add(falsePanel());
        info.add(falsePanel());
        infoPanel.add(info, BorderLayout.NORTH);
        return infoPanel;
    }
    private JPanel loginPanel() {
        JPanel loginPanel = new JPanel(new GridLayout(1, 0));
        loginPanel.setBackground(Colors.third);                                            // third
        JLabel loginLabel = new JLabel("Login: ");
        loginLabel.setForeground(Colors.fifth);                                             // fifth
        loginLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setBorder(new LineBorder(Colors.fourth));
        loginArea = new JTextArea();
        loginArea.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        loginArea.setBorder(new LineBorder(Colors.fourth));
        loginPanel.add(loginLabel);
        loginPanel.add(loginArea);
        return loginPanel;
    }
    private JPanel passwordPanel() {
        JPanel passwordPanel = new JPanel(new GridLayout(1, 0));
        passwordPanel.setBackground(Colors.third);
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setForeground(Colors.fifth);
        passwordLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordLabel.setBorder(new LineBorder(Colors.fourth));
        passwordArea = new JTextArea();
        passwordArea.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        passwordArea.setBorder(new LineBorder(Colors.fourth));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordArea);
        return passwordPanel;
    }
    private JPanel hostnamePanel() {
        JPanel hostnamePanel = new JPanel(new GridLayout(1, 0));
        hostnamePanel.setBackground(Colors.third);
        JLabel hostnameLabel = new JLabel("Hostname: ");
        hostnameLabel.setForeground(Colors.fifth);
        hostnameLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        hostnameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        hostnameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        hostnameLabel.setBorder(new LineBorder(Colors.fourth));
        hostnameArea = new JTextArea();
        hostnameArea.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        hostnameArea.setBorder(new LineBorder(Colors.fourth));
        hostnamePanel.add(hostnameLabel);
        hostnamePanel.add(hostnameArea);
        return hostnamePanel;
    }
    private JPanel portPanel() {
        JPanel portPanel = new JPanel(new GridLayout(1, 0));
        portPanel.setBackground(Colors.third);
        JLabel portLabel = new JLabel("Port: ");
        portLabel.setForeground(Colors.fifth);
        portLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        portLabel.setHorizontalAlignment(SwingConstants.CENTER);
        portLabel.setHorizontalAlignment(SwingConstants.CENTER);
        portLabel.setBorder(new LineBorder(Colors.fourth));
        portArea = new JTextArea();
        portArea.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        portArea.setBorder(new LineBorder(Colors.fourth));
        portPanel.add(portLabel);
        portPanel.add(portArea);
        return portPanel;
    }

    private JPanel falsePanel() {
        JPanel falsePanel = new JPanel(new GridLayout(0, 1));
        falsePanel.setBackground(Colors.first);                                                     // first
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
                programWindow.openConnectionPanel();
            }
        });
        JButton saveButton = new JButton("Save");
        saveButton.setBackground(Colors.second);
        saveButton.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        saveButton.setForeground(Colors.fifth);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPasswordWindow();
            }
        });
        buttonsPanel.add(getBackButton);
        buttonsPanel.add(saveButton);
        return buttonsPanel;
    }

    private void addAddress() throws NumberFormatException {
        //TODO!!!!!!!!!
    }

    private void showPasswordWindow(){
        passwordWindow = new JFrame();
        passwordWindow.setSize(500, 200);
        passwordWindow.setLocationRelativeTo(null);
        JPanel info = new JPanel(new GridLayout(3, 1));
        info.setBackground(Colors.second);

        JPanel forTitle = new JPanel(new FlowLayout());
        forTitle.setBackground(Colors.third);
        JLabel label = new JLabel("Confirm password");
        label.setForeground(Colors.fifth);
        label.setFont(new Font(Font.SERIF, Font.PLAIN, 25));
        forTitle.add(label);

        JPanel forArea = new JPanel(new FlowLayout());
        JTextArea passwordAreaServer = new JTextArea();
        passwordAreaServer.setFont(new Font(Font.SERIF, Font.PLAIN, 25));
        passwordAreaServer.setBorder(new LineBorder(Colors.fourth));
        forArea.add(passwordAreaServer);

        JPanel forButton = new JPanel(new FlowLayout());
        JButton yes = new JButton("Submit");
        yes.setBackground(Colors.second);
        yes.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        yes.setForeground(Colors.fifth);
        forButton.add(yes);

        info.add(forTitle);
        info.add(passwordAreaServer);
        info.add(yes);

        passwordWindow.add(info);

        passwordWindow.setVisible(true);
        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientTCP clientTCP = new ClientTCP();
                try {
                    clientTCP.startConnection(
                            loginArea.getText(),
                            passwordArea.getText(),
                            hostnameArea.getText(),
                            Integer.parseInt(portArea.getText()),
                            passwordAreaServer.getText());

                    User.getInstance().setConnection(clientTCP);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
    }

    private void showPasswordUserWindow(String login) {
        passwordUserWindow = new JFrame();
        passwordUserWindow.setSize(500, 200);
        passwordUserWindow.setLocationRelativeTo(null);
        JPanel info = new JPanel(new GridLayout(3, 1));


        JPanel forTitle = new JPanel(new FlowLayout());
        forTitle.setBackground(Colors.third);

        JLabel label = new JLabel("Enter connection password: ");
        label.setFont(new Font(Font.SERIF, Font.PLAIN, 25));
        label.setForeground(Colors.fifth);
        forTitle.add(label);

        JPanel forArea = new JPanel(new FlowLayout());
        JTextArea passwordAreaServer = new JTextArea();
        passwordAreaServer.setFont(new Font(Font.SERIF, Font.PLAIN, 25));
        passwordAreaServer.setBorder(new LineBorder(Colors.fourth));
        forArea.add(passwordAreaServer);

        JPanel forButton = new JPanel(new FlowLayout());
        JButton yes = new JButton("Submit");
        yes.setBackground(Colors.second);
        yes.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        yes.setForeground(Colors.fifth);
        forButton.add(yes);

        info.add(forTitle);
        info.add(passwordAreaServer);
        info.add(yes);

        passwordUserWindow.add(info);

        passwordUserWindow.setVisible(true);
        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    User.getInstance().setConnection(ClientTCP.clientMap.get(login));
                    User.getInstance().getConnection().sendMessage(UserCommand.ACCESS_REQUEST,
                            new JSONObject().put("password", Cypher.getEncryptedBytes(passwordAreaServer.getText()))
                                    .put("login", login));
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}