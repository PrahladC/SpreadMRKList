package in.peecee.spreadMRKList;


import java.awt.Color;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Vector;

public class FailuresList {
	
	public JTable TABLE;
	private SpreadMRKListView View;
	JFrame frame = new JFrame();
    public DefaultTableModel model;
    public JButton printList;

	public void show(String msg) {JOptionPane.showMessageDialog(null, msg);}   ///for debugging
	
	private JButton btnCansel;
	public void SetData(Object obj, int row_index, int col_index){TABLE.setValueAt(obj,row_index,col_index);}
    public String GetData1(JTable table, int row_index, int col_index) { return (String) View.getTable().getValueAt(row_index, col_index); }
    public Object GetData(JTable table, int row_index, int col_index)  { return View.getTable().getValueAt(row_index, col_index); }

//    private SpreadMRKListModel Model;

	
	
	public void failures(){
		frame.validate();                
	    frame.setTitle("List of Failures");
	    frame.setSize(1290, 700);
//	    frame.setLocationRelativeTo(null);
	    frame.setResizable(false);
//	    frame.getContentPane().setBackground(Color.lightGray);
	    frame.setVisible(true);
		
	}
		
	public FailuresList() {
//	   final String[] HeadereSubjects = {"ENGLISH", "SL / VOC", "ECO/BIO/VOC", "BKE / PHY", "OCM / CHE", "MAT / SEP"};

		int n = 500;

	    DefaultTableModel model = new DefaultTableModel();
	    model.setDataVector(new Object[][]{
	      {"","","","","","","","","","","","",""}},
	      
	    new Object[]{"Sr.No","Roll No","Div","Name","ENG","SL/IT/VOC","ECO/BIO/VOC","BKE/PHY","OCM/CHE","MAT/SEP","EVS","PTE", "Remarks"});
	    
	      TABLE = new JTable( model ) {
	        /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected JTableHeader createDefaultTableHeader() {
	        return new GroupableTableHeader(columnModel);
	      }
	    };                
	     
//	    TableColumnModel cm = TABLE.getColumnModel();
	    TABLE.setRowHeight(25);
	    TABLE.setFont(new Font("Times New Roman", Font.BOLD, 12));
	    
	    DefaultTableCellRenderer stringRenderer = (DefaultTableCellRenderer)
	    TABLE.getDefaultRenderer(String.class);
	    stringRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	    
	    TABLE.getColumnModel().getColumn(0).setPreferredWidth(80);              //  serial Numbers
	    TABLE.getColumnModel().getColumn(1).setPreferredWidth(80);              //  Roll Numbers
	    TABLE.getColumnModel().getColumn(2).setPreferredWidth(50);               //  Division
	    TABLE.getColumnModel().getColumn(3).setPreferredWidth(400);              //  Names of students
	    TABLE.getColumnModel().getColumn(6).setPreferredWidth(100);
	    TABLE.getColumnModel().getColumn(12).setPreferredWidth(300);
	    
	    JScrollPane scroll = new JScrollPane( TABLE );
	    frame.getContentPane().add( scroll );
  
		ResizeTable(TABLE,n);

	    
	//////   N O R T H  P A N E L
	    
	    JPanel northPanel = new JPanel();  
	    northPanel.setLayout(new FlowLayout());
	    
	    JLabel lblCollegeName = new JLabel("College Name ");
	    lblCollegeName.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    northPanel.add(lblCollegeName);

		JTextField CollName = new JTextField("S.I.W.S. College of Commerce and Economics and Smt. Thirumalai College of Science");
		CollName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		CollName.setColumns(50);
		northPanel.add(CollName);

	    JLabel lblplace = new JLabel("Place / Location");
	    lblplace.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    northPanel.add(lblplace);
	    
		JTextField Place = new JTextField("WADALA");
		Place.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Place.setColumns(15);
		northPanel.add(Place);

	    frame.getContentPane().add(northPanel, BorderLayout.NORTH);
	    
	 //////   S O U T H  P A N E L    
	    
	    JPanel southPanel = new JPanel();  
	    southPanel.setLayout(new GridLayout(1, 8));        

/*	    JButton btnLoad = new JButton("Load");
	    btnLoad.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    southPanel.add(btnLoad);   
	       
	    JButton btnSave = new JButton("Save");    
	    btnSave.setToolTipText("Save");
	    btnSave.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    southPanel.add(btnSave);    
	*/    
	    JButton btnSetPrinter = new JButton("Set Printer");
	    btnSetPrinter.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		
	    	}
	    });
	    
	    btnSetPrinter.setFont(new Font("Times New Roman", Font.BOLD, 14));
	  //  btnSetPrinter.setPreferredSize(new Dimension(115, 25));
	    southPanel.add(btnSetPrinter);
	    
	    JLabel lblPrinter = new JLabel("Printer Name ");
	    lblPrinter.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    southPanel.add(lblPrinter);

	    printList = new JButton("Print List");   
	    printList.addActionListener(new ActionListener(){
	       	public void actionPerformed(ActionEvent arg0) {
				  
	 		   int TMrg = 748, BMrg = 8, Center = 0,  x = 7, StringPosition = 0;  // TMrg = Top Margin, BMrg = Bottom Margin.
			   Center = (TMrg - BMrg)/2;
			   final String[] Exams = {"U1", "T1", "U2", "T2"};
			   final String[] HeadereSubjects = {"S1", "S2", "S3", "S4", "S5", "S6", "EV", "PT"};
					
		  try {
			  
			   PrinterJob pjob = PrinterJob.getPrinterJob();
			   pjob.setJobName("Failure List Print");
			   pjob.setCopies(1);
			   pjob.setPrintable(new Printable() {
			   public int print(Graphics pg, PageFormat pf, int pageNum) {
//			   int Rows = View.getTable().getRowCount()-1;   
			   int totalpages = 1;
//			   int TotalPages = Rows/28;
//			   int Remainder = Rows%28;
//			   if(Remainder == 0){ totalpages = TotalPages;}
//			   else totalpages = TotalPages+1;
				if (pageNum < totalpages) 
				{	
				  Font newFont;		          
				  newFont = new Font("Liberation Serif", Font.BOLD, 13);
//				  int TMrg = 748, BMrg = 8, Center = 0,  x = 7, StringPosition = 0;  // TMrg = Top Margin, BMrg = Bottom Margin.
                  int LTopX = 30, LTopY = 50, Width = 50, Height = 20;
				  FontMetrics metrics = pg.getFontMetrics(newFont);

//					pg.drawString("( FOR OFFICE USE ONLY )", 200, 20);
					pg.drawString("L  I  S  T     O  F    F  A  I  L  E  D     S  T  U  D  E  N  T  S", 150, 40); 
//					pg.drawString("( FOR OFFICE USE ONLY )", 200, 785);							
//					pg.drawString("AA",10,10);
//					pg.drawString("AB",580,10);
//					pg.drawString("AC",10,780);
//					pg.drawString("AD",580,780);   
					   Font font = new Font("Liberation Serif", Font.PLAIN, 11); 
					   pg.setFont(font);

					pg.drawString("SR",LTopX+2,LTopY+15);
					pg.drawString("ROLL", LTopX+Width/2+5, LTopY+15);
					pg.drawString("DIV", LTopX+3*Width/2-6, LTopY+15);
					pg.drawString("NAME", LTopX+(7*Width)/2, LTopY+15);
					pg.drawString("REMARK", LTopX+(9*Width), LTopY+15);
					
						pg.drawRect(LTopX, LTopY, Width/2+2, Height);           //   Serial Number
						pg.drawRect(LTopX+Width/2+2, LTopY, Width-10, Height);  //   Roll Number
						pg.drawRect(LTopX+3*Width/2-8, LTopY, Width/2, Height); //   Division
						pg.drawRect(LTopX+2*Width-8, LTopY, 4*Width, Height);   //   Name
						for(int i = 0; i < 8; i++){
						   pg.drawRect((LTopX+6*Width-8)+(i*Width)/3, LTopY, Width/3, Height);   //  Eight Subjects
						   pg.drawString(HeadereSubjects[i], (LTopX+6*Width-8)+(i*Width)/3+1, LTopY+15);
						}									
						pg.drawRect((LTopX+6*Width-8)+(8*Width)/3, LTopY, (5*Width)/2, Height);  //  Remark
										
					return Printable.PAGE_EXISTS;
					}
					
				    else
					{
					 return Printable.NO_SUCH_PAGE;
					}   				
			    }
		   });
				
					if (pjob.printDialog() == false) // choose printer
					return; 
				
					HashPrintRequestAttributeSet pattribs=new HashPrintRequestAttributeSet();
					pattribs.add(new MediaPrintableArea(2, 2, 210, 297, MediaPrintableArea.MM));
					pjob.print(pattribs); 
			}
					catch (PrinterException pe) { pe.printStackTrace(); }                                     				
		}
	         	  
  });
	    
	    
	    printList.setToolTipText("Print List of Failures");
	    printList.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    southPanel.add(printList);
	    
	    btnCansel = new JButton("Cancel");
	    btnCansel.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    	      Window win = SwingUtilities.getWindowAncestor(TABLE);
	    	      if (win != null) {
	    	         win.dispose();
	    	      }
	    	}
	    });
	    btnCansel.setFont(new Font("Times New Roman", Font.BOLD, 14));
	  //  btnCansel.setPreferredSize(new Dimension(115, 25));
	    southPanel.add(btnCansel);
	    
	    frame.getContentPane().add(southPanel, BorderLayout.SOUTH);
	    		
	    }
	
	   public JTable Table(){
	       return TABLE;
	   }


	  public void ResizeTable(JTable tablename,int numberofrows){ 
		  DefaultTableModel model=(DefaultTableModel) tablename.getModel();
	      int totalrows=tablename.getRowCount();
	      int difference=numberofrows-totalrows;
	   if(difference>0){
	        for(int i=0;i<difference;i++) model.addRow(new Object[]{" "});
	      }  
	   if(difference<0){ 
		   difference=-difference;
	       for(int i=0;i<difference;i++) model.removeRow(0);
	    }
	  }


	public class GroupableTableHeader extends JTableHeader {
		private static final long serialVersionUID = 1L;
//		private static final String uiClassID = "GroupableTableHeaderUI";
//		  protected Vector columnGroups = null;

		  public GroupableTableHeader(TableColumnModel model) {
		    super(model);
		    setReorderingAllowed(false);
		  }

	  }
}
