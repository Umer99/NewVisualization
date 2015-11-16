package GradVis;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.JFrame.*;
import javax.swing.filechooser.FileFilter;


public class SquareGrid extends JPanel implements ActionListener  {
    SquareRx1[][] squares;
   static int [][]arr = new int[2][4];
    final int PAD = 10;
    final int ROWS = 2;
    final int COLS = 4;
    private static final String OPEN_CSV_FILE = "Open CSV File";
    private static final String EXIT = "Exit";
    private static final String Column_1="Column";
    static String fileName;
 
    public SquareGrid() {
    	
    	addMouseListener(ml);
    }
 
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
       // g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                           //RenderingHints.VALUE_ANTIALIAS_ON);
        if(squares == null) {
            initSquares();
        }
        // Draw squares.
        g2.setPaint(Color.blue);
        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLS; j++) {
                squares[i][j].draw(g2);
            }
        }
    }
 
    private void initSquares() {
        squares = new SquareRx1[ROWS][COLS];
        int w = getWidth();
        int h = getHeight();
        double xInc = (double)(w - 2*PAD)/COLS;
        double yInc = (double)(h - 2*PAD)/ROWS;
        for(int i = 0; i < ROWS; i++) {
            double y = PAD + i*yInc;
            for(int j = 0; j < COLS; j++) {
                double x = PAD + j*xInc;
                Rectangle2D.Double r =
                    new Rectangle2D.Double(x, y, xInc, yInc);
                   // Color c = new Color(arr[i][j], (arr[i][j]/ 256 )% 256, (arr[i][j]/ 256 / 256)%256);
                squares[i][j] = new SquareRx1(i, j, r, arr[i][j]);
                System.out.println("value"+arr[i][j]);
            }
        }
    }
    
    private MouseListener ml = new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
            Point p = e.getPoint();
            if(!isInGrid(p)) return;
            double xInc = (double)(getWidth() - 2*PAD)/COLS;
            double yInc = (double)(getHeight() - 2*PAD)/ROWS;
            int row = (int)((p.y-PAD)/yInc);
            int col = (int)((p.x-PAD)/xInc);
            System.out.println(squares[row][col]);
            boolean isSelected = squares[row][col].isSelected();
            squares[row][col].setSelected(!isSelected);
            repaint();
        }
    };
 
    private boolean isInGrid(Point p) {
        Rectangle r = getBounds();
        r.grow(-PAD, -PAD);
        return r.contains(p);
    }
    public JMenuBar addMenu() {
        final JMenuBar menuBar = new JMenuBar();
        final JMenu menu = new JMenu("File");
        menu.add(new JSeparator());
        final JMenu menu1 = new JMenu("Sort by");
        menu1.add(new JSeparator());
      
        JMenuItem item = new JMenuItem(OPEN_CSV_FILE);
        item.addActionListener(this);
        item.setAccelerator(KeyStroke.getKeyStroke('O', java.awt.event.InputEvent.ALT_MASK));
        menu.add(item);

        item = new JMenuItem(EXIT);
        item.setAccelerator(KeyStroke.getKeyStroke('X', java.awt.event.InputEvent.ALT_MASK));
        item.addActionListener(this);
        menu.add(item);
       
        JMenuItem item1 = new JMenuItem(Column_1);
        item1.addActionListener(this);
        item1.setAccelerator(KeyStroke.getKeyStroke('1', java.awt.event.InputEvent.ALT_MASK));
         menu1.add(item1);
         item1=new JMenuItem("Column2");
         item1.addActionListener(this);
         item1.setAccelerator(KeyStroke.getKeyStroke('2', java.awt.event.InputEvent.ALT_MASK));
          menu1.add(item1);

        menuBar.add(menu);
        menuBar.add(menu1);
        return menuBar;
    }
    

 
    public static void main(String[] args) {
        BuildArray();
    	SquareGrid test = new SquareGrid();
        JFrame f = new JFrame();
        f.setTitle("ObjectVis");
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        f.add(test);
        f.setSize(800,600);
        f.setLocation(100,100);
        f.setVisible(true);
        f.setJMenuBar(test.addMenu());
        test.addComponentListener(test.cl);
        f.setJMenuBar(test.addMenu());
                    
    }
      
 
    private ComponentListener cl = new ComponentAdapter() {
        public void componentResized(ComponentEvent e) {
            squares = null;
            repaint();
        }
    };
    
public static void BuildArray() {
    	
         fileName="E:/try.csv";
        try {
        BufferedReader br = new BufferedReader( new FileReader(fileName));
        //String strLine = null;
        StringTokenizer st = null;
        int lineNumber = 0, tokenNumber = 0;
      // int [][]arr =new int[2][4];
        int c=0;
        while( (fileName = br.readLine()) != null)
        {
        lineNumber++;
        
        //break comma separated line using ","
        st = new StringTokenizer(fileName, ",");
        while(st.hasMoreTokens())
        {
        
        	//display csv values
       tokenNumber++;
       String ss= st.nextToken();
        System.out.println("Line # " + lineNumber +
        ", Token # " + tokenNumber+"  "+ss);
        if (tokenNumber<5){    	
        	arr[0][c]= HashCode(ss);
        	//System.out.println(ss);
        	++c;
        }
        else
        {
        	if (c>3){
        		c=0;
        	   arr[1][c]= HashCode(ss);
        	//System.out.println(ss);
        	}
        	else
        	{
        	arr[1][c]= HashCode(ss);
        	//System.out.println(ss);
        	}
        	c++;
        	
        }
       }
        tokenNumber = 0;
        c=0;
       
        
        for(int r=0;r<2; r++)
        	for ( c=0;c<4;c++)
        	System.out.println(arr[r][c]);
             c=0;
        }
        }
        catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }
        }
        public static int HashCode(String input) {
            int hCode = 0;
            int len = input.length();
            for (int i = 0; i < len; i++) {
                hCode = 31 * hCode + input.charAt(i);
            }
            return Math.abs(hCode%256);
   
}
 
class SquareRx1 {
    private final int row;
    private final int col;
    Rectangle2D.Double rect;
    Color color = new Color(190,190,190);
    Color bgColor = Color.GRAY;
    Color selColor; 
    private boolean selected = false;
 
    public SquareRx1(int r, int c, Rectangle2D.Double rect, int color) {
        row = r;
        col = c;
        this.rect = rect;
        bgColor= new Color(color, (color/ 256 )% 256, (color/ 256 / 256)%256);
           
    }
 
    public void draw(Graphics2D g2) {
        g2.setPaint(selected ? selColor : bgColor);
        g2.fill(rect);
        g2.setPaint(color);
        g2.draw(rect);
    }
 
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
 
    public boolean isSelected() { return selected; }
 
    public String toString() {
        return "SQUARE[row:" + row + ", col:" + col +
                    ", selected:" + selected + "]";
    }
    
    
        
       }

public void actionPerformed(ActionEvent e) {
	final String command = e.getActionCommand();

    if (OPEN_CSV_FILE.equals(command)) {
        final JFileChooser chooser = new JFileChooser(System.getProperty("user.home"));
        final FileFilter filter = new CVSFileFilter();
        chooser.setFileFilter(filter);
        final int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
           // setCvsFile(chooser.getSelectedFile().getPath());
        }
       
    
    } else if (EXIT.equals(command)) {
       windowClosingEvent(null);
    }
    
	  if (EXIT.equals(command)) 
         windowClosingEvent(null);
	
}
static class CVSFileFilter extends FileFilter {
    // return true if should accept a given file
    @Override
    public boolean accept(final File f) {
        if (f.isDirectory()) {
           return true;
        }
        final String path = f.getPath().toLowerCase(Locale.getDefault());
        if (path.endsWith(".csv")) {
            return true;
        }
        return false;
    }

    // return a description of files
    @Override
    public String getDescription() {
        return "CVS file (*.csv)";
    }
}
protected void windowClosingEvent(final WindowEvent e) {
    System.exit(0);
}
}
    
    
    
    
