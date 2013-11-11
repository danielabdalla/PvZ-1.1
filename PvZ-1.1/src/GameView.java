import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

class GameView extends JFrame implements GameListener{


    private static final String newline = "\n";
    
    // making the menu bar and its items
    private JMenuBar menuBar;

    private JMenu gameMenu;
    private JMenuItem newGame;
    private JMenuItem saveGame;
    private JMenuItem openGame;
    private JMenuItem quitGame;
    
    private JMenu editMenu;
    private JMenuItem undoGame;
    private JMenuItem redoGame;

    private JMenu helpMenu;
    private JMenuItem helpGame;

    
    // making the command box where all the inputs are going to be processed
    private JTextField commandInput;

    private JButton commandButton;
    private JButton commandListButton;
    private JButton [] buttons;
    
    // making the panels where all the components will be placed
    private JPanel mainPanel;
    private JPanel picturePanel;
    private JPanel commandPanel;    
    
    private JTextArea messageDisplayer;
    private JScrollPane scrollPane;
    
    // the model
    private GameSystem game_model;

    public GameSystem getGame_model() {
        return game_model;
    }

    public void setGame_model(GameSystem game_model) {
        this.game_model = game_model;
    }


    
    public GameView(GameSystem g) {
        
        // instanciate the model, and create the 2D and 3D views
        this.game_model = g;

        menuBar = new JMenuBar();
        gameMenu = new JMenu("Game");
        newGame = new JMenuItem("New Game");
        saveGame = new JMenuItem("Save");
        openGame = new JMenuItem("Open");
        quitGame = new JMenuItem("Quit");
        editMenu = new JMenu("Edit");
        undoGame = new JMenuItem("Undo");
        redoGame = new JMenuItem("Redo");
        helpMenu = new JMenu("Help");
        helpGame = new JMenuItem("Detailed Help");
        commandInput = new JTextField(25);
        commandButton = new JButton("Process Command");
        commandListButton = new JButton("Command List");
        messageDisplayer = new JTextArea(8, 30);
        this.construct();
    }
    ////////////////////////////////////////////////////////////////////
    public void construct()
    {
    
        // putting together the menu bar
        gameMenu.add(newGame);
        gameMenu.add(saveGame);
        saveGame.setEnabled(true);
        gameMenu.add(openGame);
        gameMenu.add(quitGame);
        
        editMenu.add(undoGame);
        editMenu.add(redoGame);
        
        helpMenu.add(helpGame);
        
        menuBar.add(gameMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        
        // the main panel contains all the other panels: picture panel 
        mainPanel = new JPanel();
        
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        // setting up the panels
        picturePanel = new JPanel();
        commandPanel = new JPanel();
        
        
        messageDisplayer.setEditable(false);
        messageDisplayer.setBorder(BorderFactory.createEtchedBorder());
        scrollPane = new JScrollPane(messageDisplayer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        
        commandPanel.add(commandListButton);
        commandListButton.setEnabled(false);
        commandPanel.add(new JLabel("Command:"));
        commandPanel.add(commandInput);
        commandInput.setEditable(false);
        commandPanel.add(commandButton);
        commandButton.setEnabled(false);
        
        GridLayout experimentLayout = new GridLayout(5,15);
        picturePanel.setLayout(experimentLayout);
        picturePanel.setSize(new Dimension(500, 200));
       
        buttons = new JButton[75];
  
        if(this.game_model.getGame() != null )
        {
          System.out.println("ok");
        for(int i=0;i<75;i++)
        {
          if(this.game_model.getGame().getField().getEntity( i/15, i % 15) instanceof Zombie)
          {
            buttons[i] = new JButton("Zombie");
            System.out.println("herer");
          }
          else if(this.game_model.getGame().getField().getEntity( i/15, i % 15) instanceof Plant)
          {
            if(((Plant)this.game_model.getGame().getField().getEntity(i/15, i % 15)).getPrice() == Plant.SUNFLOWERPRICE)
              buttons[i] = new JButton("SunFlower");
            else
              buttons[i] = new JButton("Peashooter");
          }
          else if (this.game_model.getGame().getField().getEntity( i/15, i % 15) instanceof Bullet)
            buttons[i]= new JButton("Bullet");
          else
            buttons[i]= new JButton("");
          picturePanel.add(buttons[i]);
        }
        }
        
        mainPanel.add(picturePanel);
        mainPanel.add(scrollPane);
        mainPanel.add(commandPanel);
        disableGameButtons();

        setJMenuBar(menuBar);       
        this.setContentPane(mainPanel);
        this.pack();
        this.setTitle("Zombies");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
        
    // sets the string in the command box
    public void setCommandInput(String s) {
        this.commandInput.setText(s);
    }
    
    // appends a string to the command box
    public void appendCommandInput(String s) {
        this.commandInput.setText(commandInput.getText() + s);
    }
    
    // returns the 3D view
  //  public Drawing3DArea get3DPanel() {
  //      return drawing3D;
  //  }
    
    // gets the user input
    public String getUserInput() {
        return commandInput.getText();
    }
    
    // enable all the items in the command box
    public void enableCommandPanel() {
        commandListButton.setEnabled(true);
        commandInput.setEditable(true);
        commandButton.setEnabled(true);
    }
    
    // enable all the buttons that need to be enabled in the menu, and other game related buttons
    public void enableGameButtons() {
        undoGame.setEnabled(true);
        redoGame.setEnabled(true);
        helpGame.setEnabled(true);
    }
    
    // disable all buttons that require an instance of a game to function
    public void disableGameButtons() {
        undoGame.setEnabled(false);
        redoGame.setEnabled(false);
        helpGame.setEnabled(false);
    }
    
    // disable the command box (no more user inputs)
    public void disableCommandPanel() {
        commandListButton.setEnabled(false);
        commandInput.setEditable(false);
        commandButton.setEnabled(false);
    }
    
    // reset command box
    public void resetUserInput() {
        commandInput.setText("");
    }

    // an error pop up box
    void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }
    
    // The following functions are associating all the buttons/panels with the appropriate listeners
    public void addCommandListener(ActionListener listener) {
        commandButton.addActionListener(listener);
        commandInput.addActionListener(listener);
    }
    
    public void addNewGameListener(ActionListener listener) {
        newGame.addActionListener(listener);
    }
    
    public void addQuitGameListener(ActionListener listener) {
        quitGame.addActionListener(listener);
    }
    
    public void addHelpGameListener(ActionListener listener) {
        helpGame.addActionListener(listener);
    }
    
    public void addSaveGameListener(ActionListener listener) {
        saveGame.addActionListener(listener);
    }
    
    public void addDrawingMouseListener(MouseListener listener) {
     //   drawing3D.addMouseListener(listener);
    }
    
    public void addOpenGameListener(ActionListener listener) {
        openGame.addActionListener(listener);
    }
    
    
    public void addCommandListButtonListener(ActionListener listener) {
        commandListButton.addActionListener(listener);
    }
    
    // display a message to the user
    public void dspMessage(String message) {
        messageDisplayer.append(message + newline);
        messageDisplayer.setCaretPosition(messageDisplayer.getDocument().getLength());      
    }
    
    // disable all buttons
    public void endGame() {
        disableCommandPanel();
        disableGameButtons();
    }

    @Override
    public void commandProcessed(GameEvent e)
    {
      // TODO Auto-generated method stub
      
    }

    
}
