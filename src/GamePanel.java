import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener
{
    // Constants
    private static final int PANEL_WIDTH = 600;
    private static final int PANEL_HEIGHT = 600;
    private static final int UNIT_SIZE = 25;
    private static final int GAME_UNITS = (PANEL_HEIGHT * PANEL_WIDTH) / UNIT_SIZE;
    private static final int DELAY = 75;
    private final int x[] = new int[GAME_UNITS];
    private final int y[] = new int[GAME_UNITS];
    
    // Variables
    private int bodyParts;
    private int applesEaten;
    private int appleX;
    private int appleY;
    private char direction;
    private boolean running;
    private Timer timer;
    private Random random;
    private static boolean gameOn = false; 

    // Program Code

    GamePanel()
    {
        bodyParts = 6;
        running = false;
        direction = 'W';
        random = new Random();
        this.setPreferredSize( new Dimension( PANEL_WIDTH, PANEL_HEIGHT ) );
        this.setBackground( Color.black );
        this.setFocusable( true );
        this.addKeyListener( new MyKeyAdapter() );
        startGame();
    }

    // Methods

    public void startGame()
    {
        applesEaten = 0;
        spawnApple();
        running = true;
        timer = new Timer( DELAY, this );
        timer.start();
    }

    public void pause()
    {
		GamePanel.gameOn = true;
		timer.stop();
	}

    public void resume()
    {
		GamePanel.gameOn = false;
		timer.start();
	}

    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        draw( g );

    }

    public void draw( Graphics g )
    { 
        if ( running )
        {
            //for ( int i = 0; i < PANEL_HEIGHT / UNIT_SIZE; i++ )
            {
                //g.drawLine( i * UNIT_SIZE, 0, i * UNIT_SIZE, PANEL_HEIGHT );
                //g.drawLine( 0, i * UNIT_SIZE, PANEL_WIDTH, i * UNIT_SIZE );
            }
        

            g.setColor( Color.red );
            g.fillOval( appleX, appleY, UNIT_SIZE, UNIT_SIZE );

            for ( int i = 0; i < bodyParts; i++ )
            {
                if ( i == 0 )
                {
                    g.setColor( Color.red );
                }

                else
                {
                    g.setColor( Color.green );
                }

                g.fillRect( x[i], y[i], UNIT_SIZE, UNIT_SIZE );
            }
        }
        else
        {
            gameOver( g );
        }
    }
    public void spawnApple()
    {
        appleX = random.nextInt( ( int ) ( PANEL_WIDTH / UNIT_SIZE ) ) * UNIT_SIZE;
        appleY = random.nextInt( ( int ) ( PANEL_HEIGHT / UNIT_SIZE ) ) * UNIT_SIZE;
    }

    public void move()
    {
        for ( int i = bodyParts; i > 0; i-- )
        {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch ( direction )
        {
            case 'N':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'S':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'E':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'W':
                x[0] = x[0] + UNIT_SIZE;
                break; 
        }
        
    }

    public void checkApple()
    {
        if ( x[0] == appleX && y[0] == appleY )
        {
            bodyParts++;
            applesEaten++;
            spawnApple();
        }
    }

    //public void checkHitWall()
    //{
    //    if (x[0] == PANEL_WIDTH || x[0] == 0) 
    //    {
    //        for (int i = 0; i < bodyParts; i++) 
    //        {
    //            x[i] = -x[i];
    //        }
    //    }
    //    if (y[0] == PANEL_HEIGHT || y[0] == 0)
    //    {
    //        for (int i = 0; i < bodyParts; i++) 
    //        {
    //            y[i] = -y[i];
    //        }
    //    }
    //}

    public void checkCollisions()
    {
        for ( int i = bodyParts; i > 0; i-- )
        {
            if ( x[0] == x[i] && y[0] == y[i] )
            {
                running = false;
            }
        }

        if ( x[0] < 0 )
        {
            running = false;
        }

        if ( x[0] > PANEL_WIDTH )
        {
            running = false;
        }

        if ( y[0] < 0 )
        {
            running = false;
        }

        if ( y[0] > PANEL_HEIGHT )
        {
            running = false;
        }

        if ( !running )
        {
            timer.stop();
        }
    }

    public void gameOver( Graphics g )
    {
        g.setColor( Color.red );
        g.setFont( new Font ( "SansSerif", Font.BOLD, 75 ) );
        g.drawString( "Game Over", 100, 300);
        g.setFont( new Font( "SansSerif", Font.BOLD, 30 ) );
        g.drawString( "You have eaten " + applesEaten + " apple(s)", 110, 400 );
    }

    @Override
    public void actionPerformed( ActionEvent e ) 
    {
        if ( running )
        {
            move();
            checkApple();
            checkCollisions();
            //checkHitWall();
            repaint();
        }
    }

    public class MyKeyAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed( KeyEvent e )
        {
            switch ( e.getKeyCode() )
            {
                case KeyEvent.VK_LEFT:
                    if ( direction != 'W' )
                    {
                        direction = 'E';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if ( direction != 'E' )
                    {
                        direction = 'W';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if ( direction != 'S' )
                    {
                        direction = 'N';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if ( direction != 'N' )
                    {
                        direction = 'S';
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    if( GamePanel.gameOn ) 
                    {
                        resume();
                    } 
                    else 
                    {
                        pause();
                    }
                    break;
            }
        }
    }
    
}
