import java.awt.event.*;


public class GameController{
    
    protected GameSystem model;
    protected GameView view;
    
    
    public GameController() {
    }
    
    GameController(GameView v, GameSystem g) {
        model = g;
        view = v;
            
        view.addQuitGameListener(new QuitGameListener());
        view.addNewGameListener(new NewGameListener());
        view.addCommandListener(new CommandListener());
        view.addHelpGameListener(new HelpGameListener());

    }

     
    //Checks to see if the user has entered a game command, then sends it to the game to process     
    class CommandListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String userInput = view.getUserInput();
            if (userInput.length()>0) {
                view.dspMessage(dspUserInput(userInput));
                String s = model.getGame().next(userInput);
                view.dspMessage(s);
                if(s.contains("You have choosen to continue"))
                {
                  view.dspMessage( model.getGame().play());
                  view.construct();
                }
                view.resetUserInput();
                view.enableCommandPanel();
                view.enableGameButtons();
            } else {
                view.showError("You did not enter a command!");
            }
        }
        private String dspUserInput(String input) {
            return "\n** You typed '" + input + "'";
        }
    }
    
    /*
     * Creates a new game and tells the view to enable its buttons
     */
    class NewGameListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String s = model.newGame();
            view.dspMessage(s);
            view.construct();
            view.enableCommandPanel();
            view.enableGameButtons();
        }
    }
    
    
    //Shut down everything
    class QuitGameListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            System.exit(1);
        }
    }
    
    /*
     * Does the same thing as the help command when you type "help" into command box
     */
    class HelpGameListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            view.dspMessage("\n** You clicked 'help'");
            
        }
    }
   
    
}
