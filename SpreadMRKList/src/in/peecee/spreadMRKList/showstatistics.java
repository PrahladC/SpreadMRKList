package in.peecee.spreadMRKList;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Window;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class showstatistics {
	
    JFrame frame = new JFrame();
    public JTable table;
    private JTable table_1;
//    private MeritListButtons MLButtons = new MeritListButtons();
    private SpreadMRKListController SMRKLC;
    private OverAllResult OAR;
    
	public JButton MLSCPrintButton;
	public JButton STPrintButton;
	public JButton FailedButtons;
	int TopLeftX = 50, TopLeftY = 80, Height = 20, Width = 50;
	
    String[] SerialNum = {" ", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10"};
    
    String[] Header1 = {"Rank", "          Name", "Roll", "Div", "Score", "Per",
    		            "Subject", "Commerce", "Science", "Over All"};
    
    String[] ComSubjects = {"English", "Hindi", "Marathi", "Tamil", "Economics", "Accountancy", "Org. of Commerce",
    		                "Secreterial Practice", "Mathematics", "Info. Technology"};

    String[] SciSubjects = {"English", "Hindi", "Marathi", "Tamil", "Physics", "Chemistry", "Mathematics",
                            "Biology", "Electronics", "Computer Science", "Info. Technology"};
    
    String[] Result = {"Number of students appeared", "Number of Students Passed",
    		           "Number of Students Failed", "Number of Distinctions", "Number of First Class",
    		           "Number of Second  Class", "Number of Pass Class", "Number of Students Promoted",
    		           "Result Pass Percentage"};


    String[] Header2 = {"Subject", "Name", "Roll", "Div", "Score"};
    private JTable table_2;
    
    public void show(String msg) {JOptionPane.showMessageDialog(null, msg);}   ///for debugging
   
    
    public void ShowStats(){
		 
	    frame.validate();                
	    frame.setTitle("MERIT LIST - OVER ALL RANKINGS AND SUBJECT TOPPERS");
	    frame.setSize(1290, 700);
//	    frame.setLocationRelativeTo(null);
	    frame.setResizable(false);
	    frame.getContentPane().setBackground(Color.lightGray);
	    frame.setVisible(true);
	}

   public void SetData(Object obj, int row_index, int col_index){table.getModel().setValueAt(obj,row_index,col_index);  }
   public void SetData1(Object obj, int row_index, int col_index){table_1.getModel().setValueAt(obj,row_index,col_index);  }
   public void SetData2(Object obj, int row_index, int col_index){table_2.getModel().setValueAt(obj,row_index,col_index);  }   
   public Object GetData1 (JTable table, int row_index, int col_index) {  return table.getValueAt(row_index, col_index); }
   public String GetData(JTable table, int row_index, int col_index) {  return (String) table.getValueAt(row_index, col_index); }
   
   
   public showstatistics(){	   
   	   	 
   	GridBagLayout gridBagLayout = new GridBagLayout();
   	gridBagLayout.columnWidths = new int[]{0, 483, 0};
   	gridBagLayout.rowHeights = new int[]{0, 0, 112, 0, 14, 3, 86, 0, 0, 106, 0, 0, 0};
   	gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
   	gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
   	frame.getContentPane().setLayout(gridBagLayout);
   	
   	JLabel label = new JLabel(" OVER ALL RANKINGS");
   	label.setFont(new Font("Times New Roman", Font.BOLD, 18));
   	GridBagConstraints gbc_label = new GridBagConstraints();
   	gbc_label.insets = new Insets(0, 0, 5, 5);
   	gbc_label.gridx = 0;
   	gbc_label.gridy = 0;
   	frame.getContentPane().add(label, gbc_label);
   	
   	JScrollPane scrollPane = new JScrollPane();
   	GridBagConstraints gbc_scrollPane = new GridBagConstraints();
   	gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
   	gbc_scrollPane.fill = GridBagConstraints.BOTH;
   	gbc_scrollPane.gridwidth = 3;
   	gbc_scrollPane.gridx = 0;
   	gbc_scrollPane.gridy = 2;
   	frame.getContentPane().add(scrollPane, gbc_scrollPane);
   	
   	table = new JTable();
   	scrollPane.setViewportView(table);
   	table.setBorder(new LineBorder(new Color(0, 0, 20)));
   	table.setModel(new DefaultTableModel(
   		new Object[][] {
   				{" Commerce", "1", null, null, null, null, null},
	    		{null, "2", null, null, null, null, null},
	    		{null, "3", null, null, null, null, null},
	    		{null, "4", null, null, null, null, null},
	    		{null, "5", null, null, null, null, null},
	    		{null, "6", null, null, null, null, null},
	    		{null, "7", null, null, null, null, null},
	    		{null, "8", null, null, null, null, null},
	    		{null, "9", null, null, null, null, null},
	    		{null, "10", null, null, null, null, null},
	    		{null, null, null, null, null, null, null},
	    		{" Science", "1", null, null, null, null, null},
	    		{null, "2", null, null, null, null, null},
	    		{null, "3", null, null, null, null, null},
	    		{null, "4", null, null, null, null, null},
	    		{null, "5", null, null, null, null, null},
	    		{null, "6", null, null, null, null, null},
	    		{null, "7", null, null, null, null, null},
	    		{null, "8", null, null, null, null, null},
	    		{null, "9", null, null, null, null, null},
	    		{null, "10", null, null, null, null, null},
	    		{null, null, null, null, null, null, null},
   		},
   		new String[] {
   				"Stream", "Rank", "Name", "Roll No.", "Division", "Score", "Percent"
   		}
   	));
    table.setRowHeight(25); 
    JTableHeader header1 = table.getTableHeader();
    header1.setPreferredSize(new Dimension(0,30));
    header1.setFont(new Font("Dialog", Font.BOLD,16));   
    table.setBorder(new LineBorder(new Color(0, 0, 0)));
    table.setFont(new Font("Times New Roman", Font.BOLD, 14));
    table.getColumnModel().getColumn(0).setPreferredWidth(100);       // Stream           
    table.getColumnModel().getColumn(1).setPreferredWidth(30);       // Rank
    table.getColumnModel().getColumn(2).setPreferredWidth(410);      // Name  
    table.getColumnModel().getColumn(3).setPreferredWidth(65);       // Roll Number
    table.getColumnModel().getColumn(4).setPreferredWidth(40);       // Division
    table.getColumnModel().getColumn(5).setPreferredWidth(50);       // Total Score
    table.getColumnModel().getColumn(6).setPreferredWidth(70);       // Percent
    
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
    table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
   	
   	JLabel lblSubjectToppers = new JLabel("SUBJECT TOPPERS");
   	lblSubjectToppers.setFont(new Font("Times New Roman", Font.BOLD, 18));
   	GridBagConstraints gbc_lblSubjectToppers = new GridBagConstraints();
   	gbc_lblSubjectToppers.insets = new Insets(0, 0, 5, 5);
   	gbc_lblSubjectToppers.gridx = 0;
   	gbc_lblSubjectToppers.gridy = 3;
   	frame.getContentPane().add(lblSubjectToppers, gbc_lblSubjectToppers);
   	
   	JScrollPane scrollPane_1 = new JScrollPane();
   	GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
   	gbc_scrollPane_1.gridheight = 4;
   	gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
   	gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
   	gbc_scrollPane_1.gridwidth = 3;
   	gbc_scrollPane_1.gridx = 0;
   	gbc_scrollPane_1.gridy = 4;
   	frame.getContentPane().add(scrollPane_1, gbc_scrollPane_1);
   	
   	table_1 = new JTable();
   	scrollPane_1.setViewportView(table_1);
   	table_1.setBorder(new LineBorder(new Color(0, 0, 20)));
   	table_1.setModel(new DefaultTableModel(
   		new Object[][] {
   				{"(01) English (Com)", null, null, null, null},
   				{"(02) Information Technology (Com)", null, null, null, null},
	    		{"(03) Hindi (Com)", null, null, null, null},
	    		{"(04) Marathi (Com)", null, null, null, null},
	    		{"(05) Tamil (Com)", null, null, null, null},
	    		{"(06) Economics", null, null, null, null},
	    		{"(07) Accountancy - BK", null, null, null, null},	    		
	    		{"(08) Organization of Commerece", null, null, null, null},
	    		{"(09) Secreterial Practice", null, null, null, null},
	    		{"(10) Mathematics (Com)", null, null, null, null},
	    		{null, null, null, null, null},
	    		{"(01) English (Sci)", null, null, null, null},
	    		{"(02) Information Tecnology (Sci)", null, null, null, null},
	    		{"(03) Hindi (Sci)", null, null, null, null},
	    		{"(04) Marathi (Sci)", null, null, null, null},
	    		{"(05) Tamil (Sci)", null, null, null, null},
	    		{"(06) Economics (Sci)", null, null, null, null},
	    		{"(07) Biology", null, null, null, null},	    		
	    		{"(08) Physics", null, null, null, null},
	    		{"(09) Chemistry", null, null, null, null},
	    		{"(10) Mathematics Sci", null, null, null, null},
	    		{"(11) Electronics", null, null, null, null},
	    		{"(12) Computer Science", null, null, null, null},
	    		{null, null, null, null, null},
	    		{null, null, null, null, null}},
	    		
   		new String[] {
   				"Subject", "Name", "Roll No.", "Division", "Score"
   		}
   	));
   	
    table_1.setRowHeight(25);    
    JTableHeader header2 = table_1.getTableHeader();
    header2.setPreferredSize(new Dimension(0,30));
    header2.setFont(new Font("Dialog", Font.BOLD,14));   
    table_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
    table_1.getColumnModel().getColumn(0).setPreferredWidth(190);      // SUBJECT           
    table_1.getColumnModel().getColumn(1).setPreferredWidth(400);      // NAME
    table_1.getColumnModel().getColumn(2).setPreferredWidth(70);       // ROLL NUMBER  
    table_1.getColumnModel().getColumn(3).setPreferredWidth(30);       // DIVISION
    table_1.getColumnModel().getColumn(4).setPreferredWidth(50);       // SCORE   
   	
   	JLabel lblOverAllResult = new JLabel("OVER ALL RESULT");
   	lblOverAllResult.setFont(new Font("Times New Roman", Font.BOLD, 18));
   	GridBagConstraints gbc_lblOverAllResult = new GridBagConstraints();
   	gbc_lblOverAllResult.insets = new Insets(0, 0, 5, 5);
   	gbc_lblOverAllResult.gridx = 0;
   	gbc_lblOverAllResult.gridy = 8;
   	frame.getContentPane().add(lblOverAllResult, gbc_lblOverAllResult);
   	
   	
   	JScrollPane scrollPane_2 = new JScrollPane();
   	GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
   	gbc_scrollPane_2.gridheight = 4;
   	gbc_scrollPane_2.insets = new Insets(0, 0, 5, 0);
   	gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
   	gbc_scrollPane_2.gridwidth = 3;
   	gbc_scrollPane_2.gridx = 0;
   	gbc_scrollPane_2.gridy = 9;
   	frame.getContentPane().add(scrollPane_2, gbc_scrollPane_2);
   	
   	table_2 = new JTable();
   	scrollPane_2.setViewportView(table_2);
   	table_2.setBorder(new LineBorder(new Color(0, 0, 20)));
   	table_2.setModel(new DefaultTableModel(
   		new Object[][] {
   				{"(01)", null, null, null, null},
   				{"(02)", null, null, null, null},
	    		{"(03)", null, null, null, null},
	    		{"(04)", null, null, null, null},
	    		{"(05)", null, null, null, null},
	    		{"(06)", null, null, null, null},
	    		{"(07)", null, null, null, null},	    		
	    		{"(08)", null, null, null, null},
	    		{"(09)", null, null, null, null},
	    		{"(10)", null, null, null, null},
	    		{null,null, null, null, null}},
	    		
   		new String[] {
   				"Sr,No.", "PARTICULARS", "COMMERCE", "SCIENCE", "OVER ALL RESULT"
   		}
   	));
   	
    table_2.setRowHeight(25);    
    JTableHeader header3 = table_2.getTableHeader();
    header3.setPreferredSize(new Dimension(0,30));
    header3.setFont(new Font("Dialog", Font.BOLD,14));   
    table_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
    table_2.getColumnModel().getColumn(0).setPreferredWidth(20);        // Serial Nuber           
    table_2.getColumnModel().getColumn(1).setPreferredWidth(300);       // Particulars
    table_2.getColumnModel().getColumn(2).setPreferredWidth(200);       // Commerce   
    table_2.getColumnModel().getColumn(3).setPreferredWidth(200);       // Science
    table_2.getColumnModel().getColumn(4).setPreferredWidth(200);       // Over all Result
    
	  for(int j = 0; j < Result.length; j++){
		  SetData2("  "+Result[j], j, 1);				  				  
	  }
   		
   		MLSCPrintButton = new JButton("Merit List Science / Commerce");       //  MLSC = Merit List Science Commerce
   		MLSCPrintButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
   		GridBagConstraints gbc_MLSCPrintButton = new GridBagConstraints();
   		gbc_MLSCPrintButton.anchor = GridBagConstraints.NORTHEAST;
   		gbc_MLSCPrintButton.insets = new Insets(10, 20, 5, 5);
   		gbc_MLSCPrintButton.gridx = 1;
   		gbc_MLSCPrintButton.gridy = 0;
   		frame.getContentPane().add(MLSCPrintButton, gbc_MLSCPrintButton);
   		MLSCPrintButton.addActionListener(new ActionListener() {
   		public void actionPerformed(ActionEvent arg0) {
        				 					  
   			try {
   					 
   			  PrinterJob pjob = PrinterJob.getPrinterJob();
   			  pjob.setJobName("MERRIT LIST");
   			  pjob.setCopies(1);
   			  pjob.setPrintable(new Printable() {
   			  public int print(Graphics pg, PageFormat pf, int pageNum) {
   			    int totalpages = 0;
   				if (pageNum > totalpages)                                 // we only print one page
   				return Printable.NO_SUCH_PAGE;                            // ie., end of job
   				
   				
   				
   				pg.drawString("S T R E A M W I S E   R A N K S", 230, 40);	  
   				
   				pg.drawString("C O M M E R C E", TopLeftX, TopLeftY-Height);
   				  for(int i =0; i < 6; i++){
   					for(int j = 0; j < 6; j++){
   					  pg.drawString(SerialNum[j], TopLeftX+5, TopLeftY+j*Height+15);	
   					  if(i == 0){ pg.drawRect(TopLeftX+i*Width, TopLeftY+j*Height, 3*Width/4, Height); }       
   					  if(i == 1){ pg.drawRect(TopLeftX+i*Width-Width/4, TopLeftY+j*Height, 11*Width/2, Height); }
//				  pg.drawString("KHATRI MOINUDDIN mOHMD AREEF QAMAR J", TopLeftX+28, TopLeftY+15);
   					  if(i > 1) { pg.drawRect(TopLeftX+i*Width+(9*Width/2)-Width/4, TopLeftY+j*Height, Width, Height); } 
   					  if(i < 2 )pg.drawString(Header1[i], (4+TopLeftX)+i*100, TopLeftY+15);
   					  if(i > 1 )pg.drawString(Header1[i], (11*TopLeftX/2)+i*Width, TopLeftY+15);
   					}					
   				  }
   				for(int i = 0; i < 5; i++){     //  COMMERCE
               pg.drawString(GetData(table, i, 6), TopLeftX+(9*Width), TopLeftY+(7*Height)/4+(i*Height));    // Percentage
               pg.drawString(GetData(table, i, 5), TopLeftX+(25*Width)/3, TopLeftY+(7*Height)/4+(i*Height)); // Score
               pg.drawString(GetData(table, i, 4), TopLeftX+(22*Width)/3, TopLeftY+(7*Height)/4+(i*Height)); // Division
               pg.drawString(GetData(table, i, 3), TopLeftX+(19*Width)/3, TopLeftY+(7*Height)/4+(i*Height)); // Roll No.
               pg.drawString(GetData(table, i, 2), TopLeftX+Width-5,      TopLeftY+(7*Height)/4+(i*Height));      // Name              
   				}
   				
   				for(int i = 0; i < 5; i++){    //   SCIENCE
   		           pg.drawString(GetData(table, i+11, 6), TopLeftX+(9*Width), TopLeftY+(43*Height)/4+(i*Height));// Percentage
               pg.drawString(GetData(table, i+11, 5), TopLeftX+(25*Width)/3, TopLeftY+(43*Height)/4+(i*Height)); // Score
               pg.drawString(GetData(table, i+11, 4), TopLeftX+(22*Width)/3, TopLeftY+(43*Height)/4+(i*Height)); // Division
               pg.drawString(GetData(table, i+11, 3), TopLeftX+(19*Width)/3-10, TopLeftY+(43*Height)/4+(i*Height)); // Roll No.
               pg.drawString(GetData(table, i+11, 2), TopLeftX+Width-5,      TopLeftY+(43*Height)/4+(i*Height)); // Name              

   				}
   				
   				pg.drawString("S C I E N C E", TopLeftX, TopLeftY+8*Height);					  
   				  for(int i =0; i < 6; i++){
   					for(int j = 0; j < 6; j++){
   					  pg.drawString(SerialNum[j], TopLeftX+5, TopLeftY+j*Height+9*Height+15);	
   					  if(i == 0){ pg.drawRect(TopLeftX+i*Width, TopLeftY+j*Height+9*Height, 3*Width/4, Height); }       
   					  if(i == 1) pg.drawRect(TopLeftX+i*Width-Width/4, TopLeftY+j*Height+9*Height, 11*Width/2, Height);
   					  if(i > 1) pg.drawRect(TopLeftX+i*Width+(9*Width/2)-Width/4, TopLeftY+j*Height+9*Height, Width, Height);
   					  if(i < 2 )pg.drawString(Header1[i], (4+TopLeftX)+(i*100), TopLeftY+11*Height-25);
   					  if(i > 1 )pg.drawString(Header1[i], (11*TopLeftX/2)+i*Width, TopLeftY+11*Height-25);

   					}					
   				  }
   					  
   				pg.drawString("RESULT VIEW OF BOTH SCIENCE AND COMMERCE", TopLeftX, TopLeftY+17*Height);	

   				  for(int i =0; i < 4; i++){
   					for(int j = 0; j < 11; j++){
   					  if(i == 0){ pg.drawRect(TopLeftX, TopLeftY+j*Height+18*Height, (9*Width)/2, Height); }       
                  if(i == 1) pg.drawRect(TopLeftX+(9*Width)/2, TopLeftY+j*Height+18*Height, 2*Width, Height); 
   					  if(i == 2) pg.drawRect(TopLeftX+(13*Width)/2, TopLeftY+j*Height+18*Height, 2*Width, Height);
   					  if(i == 3) pg.drawRect(TopLeftX+(17*Width)/2, TopLeftY+j*Height+18*Height, 2*Width, Height);
   					  if(i == 0) pg.drawString("Particulars", (11*TopLeftX)/4, TopLeftY+21*Height-45);
   					  if(i == 1) pg.drawString(Header1[i+6], (6*TopLeftX)-10, TopLeftY+21*Height-45);
   					  if(i == 2) pg.drawString(Header1[i+6], (8*TopLeftX), TopLeftY+21*Height-45);
   					  if(i == 3) pg.drawString(Header1[i+6], (10*TopLeftX), TopLeftY+21*Height-45);
   					}					
   				  }
   				  
   				  for(int j = 0; j < Result.length; j++){
   					  pg.drawString(Result[j], TopLeftX+5, (TopLeftY+20*Height-5)+(j*Height));				  				  
   				  }
   				  			  
             int k =  0, m = 0;
   				for(int j = 2; j < 5; j++){  
   				  for(int i = 23; i < 32; i++){
//			      pg.drawString(GetData(table_1, i, j), (2*m*Width)+(23*TopLeftX)/4, TopLeftY+(k*Height+20*Height-5));
   					  k++; 
   					  if(i == 31){ m++; k=0;}
   					  }
   				    }  
   															    				
   				return Printable.PAGE_EXISTS;
   			}
   		});

   				if (pjob.printDialog() == false) // choose printer
   				return; 
   					     
   				HashPrintRequestAttributeSet pattribs=new HashPrintRequestAttributeSet();
   				pattribs.add(new MediaPrintableArea(2, 2, 210, 297, MediaPrintableArea.MM));
   				pjob.print(pattribs); 
   			}
   				catch (PrinterException pe) {
   				pe.printStackTrace();
   			  }                                     						
   			
   		}
   		      
   });
   		
   			STPrintButton = new JButton("Print Subject Toppers");        //  ST = Subject Topper
   			STPrintButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
   			GridBagConstraints gbc_STPrintButton = new GridBagConstraints();
   			gbc_STPrintButton.anchor = GridBagConstraints.NORTHWEST;
   			gbc_STPrintButton.insets = new Insets(10, 30, 5, 5);
   			gbc_STPrintButton.gridx = 2;
   			gbc_STPrintButton.gridy = 0;
   			frame.getContentPane().add(STPrintButton, gbc_STPrintButton);
   			STPrintButton.addActionListener(new ActionListener() {
   				public void actionPerformed(ActionEvent arg0) {
   					 try {
   						 
   					      PrinterJob pjob = PrinterJob.getPrinterJob();
   						  pjob.setJobName("SUBJECT TOPPERS");
   						  pjob.setCopies(1);
   						  pjob.setPrintable(new Printable() {
   						  public int print(Graphics pg, PageFormat pf, int pageNum) {
   						  int totalpages = 0;
   						  if (pageNum > totalpages)                                 // we only print one page
   						  return Printable.NO_SUCH_PAGE;                            // ie., end of job
//				  Font newFont;		          
//				  newFont = new Font("Liberation Serif", Font.PLAIN, 13);

   						  pg.drawString("SUBJECT TOPPERS FROM BOTH THE STREAMS", 150, 30);
   						  
   						  pg.drawString("C O M M E R C E", TopLeftX, TopLeftY-Height);
   						  for(int i =0; i < 5; i++){
   							for(int j = 0; j < 11; j++){
   							  if(i == 0){ pg.drawRect(TopLeftX, TopLeftY+j*Height, (5*Width)/2, Height); } //  Subject
   							  if(i == 1) pg.drawRect(3*TopLeftX+Width/2, TopLeftY+j*Height, (9*Width)/2, Height);   //  Name
   							  if(j < 10)pg.drawString(ComSubjects[j], TopLeftX+5, TopLeftY+j*Height+35 );
//					  pg.drawString("KHATRI MOINUDDIN MOHMD AREEF", 178, TopLeftY+15);
   							  if(i > 1) pg.drawRect(6*TopLeftX+i*Width, TopLeftY+j*Height, Width, Height); 
   							}					
   						  }

   						 for(int i = 0 ; i < 5; i++){
   							 if(i == 0){ pg.drawString(Header2[i], TopLeftX+Width, TopLeftY+15);
   							             pg.drawString(Header2[i], TopLeftX+Width, TopLeftY+335); }
   							 if(i == 1){ pg.drawString(Header2[i], 5*TopLeftX+(Width/2), TopLeftY+15);
   				                         pg.drawString(Header2[i], 5*TopLeftX+(Width/2), TopLeftY+335); }
   							 if(i > 1) { pg.drawString(Header2[i], TopLeftX+5*Width+i*Width+10, TopLeftY+15);
   							             pg.drawString(Header2[i], TopLeftX+5*Width+i*Width+10, TopLeftY+335); }
   						 }
   						  
   						  pg.drawString("S C I E N C E", TopLeftX, TopLeftY+15*Height);	
   						  
   						  for(int i =0; i < 5; i++){
   							for(int j = 0; j < 11; j++){
   							  if(i == 0){ pg.drawRect(TopLeftX, TopLeftY+j*Height+16*Height, (5*Width)/2, Height); } //  Subject
   							  if(i == 1) pg.drawRect(3*TopLeftX+Width/2, TopLeftY+j*Height+16*Height, (9*Width)/2, Height);   //  Name
   							  if(j < 10)pg.drawString(SciSubjects[j], TopLeftX+5, TopLeftY+j*Height+355 );
//					  pg.drawString("KHATRI MOINUDDIN MOHMD AREEF", 178, TopLeftY+15);
   							  if(i > 1) pg.drawRect(6*TopLeftX+i*Width, TopLeftY+j*Height+16*Height, Width, Height); 
   							}					
   						  }
   						  
   						  
//				pg.drawString("( FOR OFFICE USE ONLY )", 230, 750);
   						
   						
   							    				
   						return Printable.PAGE_EXISTS;
   					  }
   				   });

   						if (pjob.printDialog() == false) // choose printer
   						return; 
   								     
   						HashPrintRequestAttributeSet pattribs=new HashPrintRequestAttributeSet();
   						pattribs.add(new MediaPrintableArea(2, 2, 210, 297, MediaPrintableArea.MM));
   						pjob.print(pattribs); 
   					}
   						catch (PrinterException pe) {
   						pe.printStackTrace();
   					  }                                     								
   				}
   				      
    });
   		
   		JButton btnOK = new JButton("EXIT");
   		btnOK.addActionListener(new ActionListener() {
   			public void actionPerformed(ActionEvent arg0) {
   				
   			 okButtonAction();
   			}
   		});
   		GridBagConstraints gbc_btnOK = new GridBagConstraints();
   		gbc_btnOK.insets = new Insets(0, 0, 5, 0);
   		gbc_btnOK.anchor = GridBagConstraints.NORTHWEST;
   		gbc_btnOK.gridx = 4;
   		gbc_btnOK.gridy = 10;
   		frame.getContentPane().add(btnOK, gbc_btnOK);

   }
   
   public JTable Table(){
       return table;
   }
   
   public JTable Table1(){
       return table_1;
   }
   
   // This button's action is simply to dispose of the JDialog.
   private void okButtonAction() {
      // win is here the JDialog that holds this JPanel, but it could be a JFrame or 
      // any other top-level container that is holding this JPanel
      Window win = SwingUtilities.getWindowAncestor(table);
      if (win != null) {
         win.dispose();
      }
   }

   
}
