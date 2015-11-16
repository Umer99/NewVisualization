package experi;

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
import java.util.Comparator;
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

import experi.SquarGrids.SquareRec;


public class SquarGrids extends JPanel implements ActionListener  {
    SquareRx1[][][] squares;
    SquareRec[]sq;
   static SquareRx1 s[]= new SquareRx1[500];
   static int [][]arr = new int[2][4];
   static int [][][]arr1 = new int[50][2][4];
    final int PAD = 5;
    final int ROW = 50;
    final static int ROWS =2;
    final static int COLS = 4;
    private static final String OPEN_CSV_FILE = "Open CSV File";
    private static final String EXIT = "Exit";
    private static final String Column_1="Class";
    static String fileName;
    public static final int PREFERRED_GRID_SIZE_PIXELS = 20;
 
     public SquarGrids() {
    	
    	addMouseListener(ml);
    }
 
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                           RenderingHints.VALUE_ANTIALIAS_ON);
        if(squares == null) {
            initSquares();
        }
        // Draw squares.
        g2.setPaint(Color.blue);
        int t=0;
      for(int k=0;k<ROW;k++){
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 4; j++) {
            	s[t++]= squares[k][i][j];
                //squares[k][i][j].draw(g2);
                //System.out.println("K: "+k);
            }
        }
      }
      System.out.println("T: "+t);
      for (int b=0; b<400;b++)
    	  s[b].draw(g2);
    }
 
    private void initSquares() {
        squares = new SquareRx1[ROW][ROWS][COLS];
       
        double x=0;
        double y=0;
        int w = getWidth();
        int h = getHeight();
        double xInc = (double)(w - 2*PAD)/COLS;
        double yInc =10;// (double)(h - 2*PAD)/ROWS;
        System.out.println("X VA:"+xInc+", Y Va:"+yInc );
       for (int k=0;k<ROW;k++){
    	   //x=PAD+k*80;
          //y=PAD+k*40;
    	   
        for(int i = 0; i < ROWS; i++) {
               //y= PAD + xInc;
        	      //x=PAD;
        	if((i==0)&&((k>0)&&(k%5==0))){
                y=y+PAD+i*yInc;
                x=PAD;
        	}
        	else
        	y=PAD+i*yInc;
            for(int j = 0; j < COLS; j++) {
            	if ((j==0)&&((k==0)||(k%5!=0))){
                	x=x+PAD+j*yInc;
                	y=PAD;
                }
            	else
            	x=PAD+j*yInc;
                Rectangle2D.Double r =
                    new Rectangle2D.Double(x, y, yInc, yInc);
                    squares[k][i][j] = new SquareRx1(i, j, r, arr1[k][i][j]);
                   
               }  
            
        }
       /* if ((k+1)%5!=0){
        	x=x+yInc;
        	y=PAD;
        }
        else if((k>0)&&(k%5==0)){
             y=y+yInc;
             x=PAD;
          }*/
      }

    }
    
    private MouseListener ml = new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
            Point p = e.getPoint();
            if(!isInGrid(p)) return;
            double xInc = (double)(getWidth() - 2*PAD)/COLS;
            double yInc = (double)(getHeight() - 2*PAD)/ROWS;
            int k = (int)((p.y-PAD)/yInc);
            int row = (int)((p.y-PAD)/yInc);
            int col = (int)((p.x-PAD)/xInc);
           System.out.println(squares[k][row][col]);
            boolean isSelected = squares[k][row][col].isSelected();
            squares[k][row][col].setSelected(!isSelected);
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
         item1=new JMenuItem("Type");
         item1.addActionListener(this);
         item1.setAccelerator(KeyStroke.getKeyStroke('2', java.awt.event.InputEvent.ALT_MASK));
          menu1.add(item1);
          item1=new JMenuItem("Package");
          item1.addActionListener(this);
          item1.setAccelerator(KeyStroke.getKeyStroke('3', java.awt.event.InputEvent.ALT_MASK));
           menu1.add(item1);
           item1=new JMenuItem("Thread");
           item1.addActionListener(this);
           item1.setAccelerator(KeyStroke.getKeyStroke('4', java.awt.event.InputEvent.ALT_MASK));
            menu1.add(item1);
            item1=new JMenuItem("Method");
            item1.addActionListener(this);
            item1.setAccelerator(KeyStroke.getKeyStroke('5', java.awt.event.InputEvent.ALT_MASK));
             menu1.add(item1);

        menuBar.add(menu);
        menuBar.add(menu1);
        return menuBar;
    }
    

 
    public static void main(String[] args) {
    	int a= ROWS*PREFERRED_GRID_SIZE_PIXELS;
        int b= COLS*PREFERRED_GRID_SIZE_PIXELS;
    	BuildArray();
    	SquarGrids test = new SquarGrids();
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
    	
	     SquareRec[]sq=new SquareRec[50];
	     String [] s1= new String[8];
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
        //sq[lineNumber-1].= new SquareRec()
        
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
        	{
        		arr1[lineNumber-1][r][c]= arr[r][c];
        	System.out.println(arr[r][c]);
            
        	}
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
 
    public SquareRx1(int ro, int co, Rectangle2D.Double rect, int color) {
       // hr=s;
    	row = ro;
        col = co;
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
        return "SQUARE[ row:" + row + ", col:" + col +
                    ", selected:" + selected + "]";
    }
    
    
        
       }
class SquareRec1{
	
	public final static Comparator<? super SquareRec> typeComparator = null;
	private final String []rr= new String[8];
	/*private final String status;
	private final String thread;
	private final String dtime;
	private final String name;
	private final String type;
	private final String cName;
	private final String mName;
	private final String loc;
	*/
	
	public SquareRec( String[] a /*{String s, String th, String dt, String n,String t,String cN,String mN, String l*/) {
	        
	    	/*status=s;
	    	thread=th;
	    	dtime=dt;
	    	name=n;
	    	type=t;
	    	cName=cN;
	    	mName=mN;
	    	loc=l;
	    	
		
		/*row = ro;
	        col = co;
	        this.rect = rect;
	        bgColor= new Color(color, (color/ 256 )% 256, (color/ 256 / 256)%256);
	        */
	           
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
    