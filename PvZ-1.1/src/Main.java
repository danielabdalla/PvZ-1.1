
public class Main
{
  public static void main(String args[])
  {
    GameSystem s = new GameSystem();
    GameView v = new GameView(s);
    s.addGameListener(v);
    GameController c = new GameController(v, s);
    
    v.setVisible(true);
    v.setLocationRelativeTo(null);
  }
}
