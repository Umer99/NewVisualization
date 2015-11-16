package experi;


import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.imageio.stream.FileImageInputStream;
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
import java.util.Arrays;
import java.util.Comparator;



public class SquareObject extends JPanel implements ActionListener  {
	static int siz=37000;
	static int pixel=5;
	static int t=0;
	static SquareRx1 squ[]=new SquareRx1[siz];
	static SquareRec[]sq=new SquareRec[siz];
	SquareRx1[][][] squares;
    static int [] ar=new int [400];
    static SquareRx1 s[]= new SquareRx1[16000];
    static int [][]arr = new int[2][4];
    static int [][][]arr1 = new int[siz][2][4];
    static int []arr11= new int[siz];
    final int PAD = 0;
    final static int ROW = siz;
    final static int ROWS =2;
    final static int COLS = 4;
    private static final String OPEN_CSV_FILE = "Open CSV File";
    private static final String EXIT = "Exit";
    private static final String Column_1="Class";
    static String fileName;
    public static final int PREFERRED_GRID_SIZE_PIXELS = 20;
    
   
 
    public SquareObject() {
    	
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
    /*  for(int k=0;k<ROW;k++){
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 4; j++) {
            	s[t++]= squares[k][i][j];
                //squares[k][i][j].draw(g2);
                //System.out.println("K: "+k);
            }
        }
      }*/
     // System.out.println("T: "+t);
      for (int b=0; b<siz;b++){
      sq[b].draw(g2);
      
      }
     
      
    }
 
    private void initSquares() {
        squares = new SquareRx1[ROW][ROWS][COLS];
        
    /*  for(int h=0;h<50; h++)
        for(int r=0;r<2; r++)
        for(int s=0;s<4;s++)
        	arrr[][]=
        	System.out.println("arr1 h="+h+" Value"+arr1[h][r][c]);
        
        
      
        double x=0;
        double y=0;
        double x1=0;
        int w = getWidth();
        int h = getHeight();
        double xInc = (double)(w - 2*PAD)/COLS;
        double yInc =10;//(double)(h - 2*PAD)/ROWS;
        System.out.println("X VA:"+xInc+", Y Va:"+yInc );
        y=PAD;
        x=PAD;
       for (int k=0;k<ROW;k++){
    	
    	 if (((k+1)%40)==0){
    		 x=PAD+45*(k/40);
    		  
    		  y=PAD;
    	 }*/
    	 
    	   /* if((k%5!=0 )|| (k==0)){
    		  x=PAD+k*45;
              y=PAD;
    		  
    	  }
    	  else {
    		  x=PAD;
              y=PAD+k*45;
    	  }*/
    	/* x1=x;
        for(int i = 0; i < ROWS; i++) {
              
            for(int j = 0; j < COLS; j++) {
            	
                Rectangle2D.Double r =
                    new Rectangle2D.Double(x, y, yInc, yInc);
               // System.out.println("X VA:"+x+", Y Va:"+y );
                    //squares[k][i][j] = new SquareRx1(i, j, r, arr1[k][i][j]);
                    x=x+10;
               }  
            y=y+10;
            x=x1;
            
       	 
     
     
        }*/
       
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
       
        JMenuItem item1 = new JMenuItem("Class");
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
    	int a= ROW*PREFERRED_GRID_SIZE_PIXELS;
        int b= ROW*PREFERRED_GRID_SIZE_PIXELS;
    	BuildArray();
    	SquareObject test = new SquareObject();
    	JPanel panel=new JPanel();

    	//Create a scrollbar using JScrollPane and add panel into it's viewport
    	//Set vertical and horizontal scrollbar always show
    	JScrollPane scrollBar=new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JFrame f = new JFrame();
        f.setTitle("ObjPatVis");
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        f.add(test);
        //setPreferredSize(new Dimension(80, 50));
        f.setSize(a, 800);
       // f.setSize(f.MAXIMIZED_HORIZ, f.MAXIMIZED_VERT);
        Dimension d= f.getMaximumSize();
        //int m=f.MAXIMIZED_BOTH;
        //f.setMaximumSize(d);
        //f.setLocation(100,100);
        
        
        f.setVisible(true);
        f.setJMenuBar(test.addMenu());
        test.addComponentListener(test.cl);
        f.setJMenuBar(test.addMenu());
        f.add(scrollBar);
                  
    }
      
 
    private ComponentListener cl = new ComponentAdapter() {
        public void componentResized(ComponentEvent e) {
            squares = null;
            repaint();
        }
    };
    
public static void BuildArray() {
	double x=0;
    double y=0;
	String temp []=new String[8];
	
         fileName="E:/try13.csv";
         
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
        
       temp[tokenNumber-1]=ss;
       
        
       /* if (tokenNumber<5){    	
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
        	
        }*/
       }
         Rectangle2D.Double re =
            new Rectangle2D.Double(x, y, pixel, pixel);
        sq[lineNumber-1]= new SquareRec(re, temp);
        
        tokenNumber = 0;
        c=0;
        x=x+pixel;
        if (x%1280==0)
        {
        	x=0;
        	y=y+pixel;
        }
        
       /* for(int r=0;r<2; r++)
        	for ( c=0;c<4;c++)
        	{
        		arr1[lineNumber-1][r][c]= arr[r][c];
        	System.out.println(arr[r][c]);
            
        	}*/
        c=0;
        }
        
        
          
        	//sq[i].Display();
        //for (int u=0;u<8;u++)
        	//sq[u].Display();
       // Arrays.sort(sq, 0, 7);
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
   // private final int hr;
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
       //g2.fill(new Rectangle2D.Double(row,col,10,10));
       // g2.fill(new Rectangle2D.Double(rect.x,rect.y,rect.height, rect.width));
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
	if(command.contentEquals("Type")){
		    Arrays.sort(sq, SquareRec.typeComparator); 
	         t=0;
	}
	if(command.contentEquals("Class")){
			Arrays.sort(sq, SquareRec.classComparator); t=1;}
	if(command.contentEquals("Thread")){
			Arrays.sort(sq, SquareRec.threadComparator);t=2;}
	if(command.contentEquals("Method")){
		Arrays.sort(sq, SquareRec.methodComparator);t=3;}
	SquareRec.newArray(sq);
  repaint();
}
 
public static class SquareRec implements Comparable<SquareRec>{
	
	//public final static Comparator<? super SquareRec> typeComparator = null;
	public  String status;
	private  String thread;
	private  String dtime;
	private  String name;
	private  String type;
	private  String cName;
	private  String mName;
	private  String loc;
	Rectangle2D.Double rect;
    Color color = new Color(190,190,190);
    Color bgColor = Color.GRAY;
    Color selColor; 
    private boolean selected = false;
	 
	
	public SquareRec(String s, String th, String dt, String n,String t,String cN,String mN, String l) {
	        
		
	    	status=s;
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
	public SquareRec(Rectangle2D.Double re, String[] temp) {
		
	
		this.status=temp[0];
        this.thread=temp[1];
        this.dtime=temp[2];
        this.name=temp[3];
        this.type=temp[4];
        this.cName=temp[5];
        this.mName=temp[6];
        this.loc=temp[7];
        this.rect = re;
        int color= HashCode(this.type);
        bgColor= new Color(color, (color/ 256 )% 256, (color/ 256 / 256)%256);
        
	}
	public void Display(){
		System.out.println(this.cName);
		
		
	}
	 public int HashCode(String input) {
         int hCode = 0;
         int len = input.length();
         for (int i = 0; i < len; i++) {
             hCode = 31 * hCode + input.charAt(i);
         }
         return Math.abs(hCode%256);
}
	 public void draw(Graphics2D g2) {
	        g2.setPaint(selected ? selColor : bgColor);
	        g2.fill(this.rect);
	       //g2.fill(new Rectangle2D.Double(row,col,10,10));
	       // g2.fill(new Rectangle2D.Double(rect.x,rect.y,rect.height, rect.width));
	        g2.setPaint(color);
	        g2.draw(this.rect);
	        
	        
	    }	
	 
	public static void newArray(SquareRec[] sqq){
		
		double x=0;
		double y=0;
		int c=0;
		
		for(int i=0;i<siz;i++){
			Rectangle2D.Double re =
	            new Rectangle2D.Double(x, y, pixel, pixel);
			sq[i].rect=re;
			x=x+pixel;
	        if (x%1260==0)
	        {
	        	x=0;
	        	y=y+pixel;
	        }	
	       
		if(t==1)
	       c =sq[i].HashCode((sq[i].cName));
		else if(t==2)  
			c =sq[i].HashCode((sq[i].thread));
		else if(t==3)
			c =sq[i].HashCode((sq[i].mName));
		else if (t==0)
			c =sq[i].HashCode((sq[i].type));
		
		sq[i].bgColor= new Color(c, (c/ 256 )% 256, (c/ 256 / 256)%256);
		
		}
		 	
		
		
	}
	
	 public int compareTo(SquareRec compareSqu) {
		 
			/*String compareQuantity = ((SquareRec) compareSqu).type; 
	 
			//ascending order
			return this.type - compareQuantity;
	 
			//descending order*/
			//return compareQuantity - this.quantity;
	     return 1;
		}
	 
		public static Comparator<SquareRec> typeComparator 
	                          = new Comparator<SquareRec>() {
	 
		    public int compare(SquareRec sq1, SquareRec sq2) {
	 
		      String typName1 = sq1.type.toUpperCase();
		      String typName2 = sq2.type.toUpperCase();
		      //ascending order
		      return typName1.compareTo(typName2);
	 
		      //descending order
		      //return fruitName2.compareTo(fruitName1);
		    }
	 
		};
		public static Comparator<SquareRec> classComparator 
                                  = new Comparator<SquareRec>() {

				public int compare(SquareRec sq1, SquareRec sq2) {

				   String ClassName1 = sq1.cName.toUpperCase();
				   String ClassName2 = sq2.cName.toUpperCase();

									//ascending order
				return ClassName1.compareTo(ClassName2);

											//descending order
										//return fruitName2.compareTo(fruitName1);
				}
				};
		
		public static Comparator<SquareRec> threadComparator 
									= new Comparator<SquareRec>() {

				public int compare(SquareRec sq1, SquareRec sq2) {

						String ThreadName1 = sq1.thread.toUpperCase();
						String ThreadName2 = sq2.thread.toUpperCase();

						//ascending order
						return ThreadName1.compareTo(ThreadName2);

						//descending order
						//return fruitName2.compareTo(fruitName1);
		}
		};
		public static Comparator<SquareRec> methodComparator 
							= new Comparator<SquareRec>() {

     public int compare(SquareRec sq1, SquareRec sq2) {

    	 		String mdName1 = sq1.mName.toUpperCase();
    	 		String mName2 = sq2.mName.toUpperCase();

    	 		//ascending order
    	 		return mdName1.compareTo(mName2);

    	 		//descending order
    	 		//return fruitName2.compareTo(fruitName1);
     		}
			};

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


	

    
