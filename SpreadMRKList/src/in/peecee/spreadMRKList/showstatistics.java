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
	public JButton MLSCPrintButton;
	public JButton STPrintButton;
	public JButton FailedButtons;
	int TopLeftX = 50, TopLeftY = 80, Height = 20, Width = 50;
	
    String[] SerialNum = {" ", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10"};
    
    String[] Header1 = {"Rank", "          Name", "Roll", "Div", "Score", "Per",
    		            "Subject", "Commerce", "Science"};
    
    String[] ComSubjects = {"English", "Hindi", "Marathi", "Tamil", "Economics", "Accountancy", "Org. of Commerce",
    		                "Secreterial Practice", "Mathematics", "Info. Technology"};

    String[] SciSubjects = {"English", "Hindi", "Marathi", "Tamil", "Physics", "Chemistry", "Mathematics",
                            "Biology", "Electronics", "Computer Science", "Info. Technology"};
    
    String[] Result = {"Number of Students registered", "Number of students appeared", "Number of Students Passed",
    		           "Number of Students Failed", "Number of Distinctions", "Number of First Class",
    		           "Number of Second  Class", "Number of Pass Class", "Number of Students Promoted",
    		           "Result Pass Percentage"};


    String[] Header2 = {"Subject", "Name", "Roll", "Div", "Score"};
    
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
   public Object GetData1 (JTable table, int row_index, int col_index) {  return table.getValueAt(row_index, col_index); }
   public String GetData(JTable table, int row_index, int col_index) {  return (String) table.getValueAt(row_index, col_index); }
   
   
   public showstatistics(){	   
   	   	 
   	GridBagLayout gridBagLayout = new GridBagLayout();
   	gridBagLayout.columnWidths = new int[]{0, 483, 0};
   	gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
   	gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
   	gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
   	frame.getContentPane().setLayout(gridBagLayout);
   	
   	JLabel lblNewLabel = new JLabel("MERIT LIST - OVER ALL RANKINGS AND SUBJECT TOPPERS");
   	lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
   	GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
   	gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
   	gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
   	gbc_lblNewLabel.gridx = 1;
   	gbc_lblNewLabel.gridy = 0;
   	frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
   	
   	JLabel label = new JLabel(" OVER ALL RANKINGS");
   	label.setFont(new Font("Times New Roman", Font.BOLD, 18));
   	GridBagConstraints gbc_label = new GridBagConstraints();
   	gbc_label.insets = new Insets(0, 0, 5, 5);
   	gbc_label.gridx = 0;
   	gbc_label.gridy = 1;
   	frame.getContentPane().add(label, gbc_label);
   	
   	JScrollPane scrollPane = new JScrollPane();
   	GridBagConstraints gbc_scrollPane = new GridBagConstraints();
   	gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
   	gbc_scrollPane.fill = GridBagConstraints.BOTH;
   	gbc_scrollPane.gridwidth = 2;
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
   	gbc_lblSubjectToppers.gridy = 4;
   	frame.getContentPane().add(lblSubjectToppers, gbc_lblSubjectToppers);
   	
   	JScrollPane scrollPane_1 = new JScrollPane();
   	GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
   	gbc_scrollPane_1.gridheight = 2;
   	gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
   	gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
   	gbc_scrollPane_1.gridwidth = 3;
   	gbc_scrollPane_1.gridx = 0;
   	gbc_scrollPane_1.gridy = 5;
   	frame.getContentPane().add(scrollPane_1, gbc_scrollPane_1);
   	
   	table_1 = new JTable();
   	scrollPane_1.setViewportView(table_1);
   	table_1.setBorder(new LineBorder(new Color(0, 0, 20)));
   	table_1.setModel(new DefaultTableModel(
   		new Object[][] {
   				{"  English (Com)", null, null, null, null},
	    		{"  Hindi (Com)", null, null, null, null},
	    		{"  Marathi (Com)", null, null, null, null},
	    		{"  Tamil (Com)", null, null, null, null},
	    		{"  Economics", null, null, null, null},
	    		{"  Accountancy - BK", null, null, null, null},	    		
	    		{"  Org. of Commerece", null, null, null, null},
	    		{"  Secreterial Practice", null, null, null, null},
	    		{"  Mathematics (Com)", null, null, null, null},
	    		{"  English (Sci)", null, null, null, null},
	    		{"  Hindi (Sci)", null, null, null, null},
	    		{"  Marathi (Sci)", null, null, null, null},
	    		{"  Tamil (Sci)", null, null, null, null},
	    		{"  Physics", null, null, null, null},
	    		{"  Chemistry", null, null, null, null},
	    		{"  Mathematics Sci", null, null, null, null},
	    		{"  Biology", null, null, null, null},
	    		{"  Electronics", null, null, null, null},
	    		{"  Computer Science", null, null, null, null},
	    		{null, null, null, null, null},
	    		{null, null, null, null, null},
	    		{null, null, null, null, null},
	    		{null, null, null, null, null},
	    		{null, null, null, null, null},
	    		{null, null, null, null, null},	},
   		new String[] {
   				"Subject", "Name", "Roll No.", "Division", "Score"
   		}
   	));
   	
    table_1.setRowHeight(25);    
    JTableHeader header2 = table_1.getTableHeader();
    header2.setPreferredSize(new Dimension(0,30));
    header2.setFont(new Font("Dialog", Font.BOLD,16));   
    table_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
    table_1.getColumnModel().getColumn(0).setPreferredWidth(170);      // SUBJECT           
    table_1.getColumnModel().getColumn(1).setPreferredWidth(400);      // NAME
    table_1.getColumnModel().getColumn(2).setPreferredWidth(70);       // ROLL NUMBER  
    table_1.getColumnModel().getColumn(3).setPreferredWidth(30);       // DIVISION
    table_1.getColumnModel().getColumn(4).setPreferredWidth(50);       // SCORE   
    
	MLSCPrintButton = new JButton("Merit List Science / Commerce");       //  MLSC = Merit List Science Commerce
	MLSCPrintButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
	GridBagConstraints gbc_MLSCPrintButton = new GridBagConstraints();
	gbc_MLSCPrintButton.anchor = GridBagConstraints.EAST;
	gbc_MLSCPrintButton.insets = new Insets(10, 20, 5, 5);
	gbc_MLSCPrintButton.gridx = 0;
	gbc_MLSCPrintButton.gridy = 8;
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
				  
			pg.drawString("RESULT VIEW OF BOTH SCIENCE AND COMMERCE", 3*TopLeftX, TopLeftY+17*Height);	

			  for(int i =0; i < 3; i++){
				for(int j = 0; j < 15; j++){
				  if(i == 0){ pg.drawRect(TopLeftX+Width, TopLeftY+j*Height+18*Height, (9*Width)/2, Height); }       
				  if(i == 1) pg.drawRect(TopLeftX+(11*Width)/2, TopLeftY+j*Height+18*Height, 2*Width, Height); 
				  if(i == 2) pg.drawRect(TopLeftX+(15*Width)/2, TopLeftY+j*Height+18*Height, 2*Width, Height);
				  if(i == 0) pg.drawString(Header1[i+6], (15*TopLeftX)/4, TopLeftY+21*Height-45);
				  if(i == 1) pg.drawString(Header1[i+6], (7*TopLeftX)-10, TopLeftY+21*Height-45);
				  if(i == 2) pg.drawString(Header1[i+6], (9*TopLeftX), TopLeftY+21*Height-45);
				}					
			  }
			  
			  for(int j = 0; j < Result.length; j++){
				  pg.drawString(Result[j], TopLeftX+Width+5, (TopLeftY+20*Height-5)+(j*Height));				  				  
			  }
			  			  
			  
			for(int j = 0; j < 4; j++){  
			  for(int i = 0; i < 5; i++){
				  pg.drawString(GetData(table, i, 2), TopLeftX+Width-10, TopLeftY+i*Height+2*Height-5);            //  Names
			      pg.drawString(GetData(table, i, j+3), TopLeftX+6*Width+10+j*Width, TopLeftY+i*Height+2*Height-5); //  Roll No, Div, Score n Percentage
			      pg.drawString(GetData(table, i+11, 2), TopLeftX+Width-10, TopLeftY+i*Height+11*Height-5);            //  Names
			      pg.drawString(GetData(table, i+11, j+3), TopLeftX+6*Width+10+j*Width, TopLeftY+i*Height+11*Height-5);
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

	STPrintButton = new JButton("Subject Toppers");        //  ST = Subject Topper
	STPrintButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
	GridBagConstraints gbc_STPrintButton = new GridBagConstraints();
	gbc_STPrintButton.anchor = GridBagConstraints.WEST;
	gbc_STPrintButton.insets = new Insets(10, 30, 5, 5);
	gbc_STPrintButton.gridx = 1;
	gbc_STPrintButton.gridy = 8;
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

   	
   	JButton btnOK = new JButton("OK");
   	btnOK.addActionListener(new ActionListener() {
   		public void actionPerformed(ActionEvent arg0) {
   			
   		 okButtonAction();
   		}
   	});
   	GridBagConstraints gbc_btnOK = new GridBagConstraints();
   	gbc_btnOK.insets = new Insets(0, 0, 5, 0);
   	gbc_btnOK.anchor = GridBagConstraints.WEST;
   	gbc_btnOK.gridx = 3;
   	gbc_btnOK.gridy = 8;
   	frame.getContentPane().add(btnOK, gbc_btnOK);

   	
/*   	FailedButtons = new JButton("Failure List");
   	btnOK.addActionListener(new ActionListener() {
   		public void actionPerformed(ActionEvent arg0) {
   			
   		
   		}
   	});
   	GridBagConstraints gbc_FailedButtons = new GridBagConstraints();
   	gbc_FailedButtons.insets = new Insets(0, 0, 5, 0);
   	gbc_FailedButtons.anchor = GridBagConstraints.SOUTH;
   	gbc_FailedButtons.gridx = 2;
   	gbc_FailedButtons.gridy = 8;
   	frame.getContentPane().add(FailedButtons, gbc_FailedButtons);

  */ 
	   
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
