import javax.swing.JFrame;

public class GameFrame extends JFrame
{

    // Constants

    // Variable
    GamePanel panel;


    GameFrame()
    {
        panel = new GamePanel();
        this.add( panel );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setResizable( false );
        this.setTitle( "Snake Game" );
        this.pack();
        this.setVisible( true );
    }
}