package in.peecee.spreadMRKList;


//   import in.peecee.spreadMRKList.SpreadMRKListView.ColumnGroup;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.Dimension;

public class SpreadMRKListController {

	private SpreadMRKListModel Model;
	private SpreadMRKListView View;
	private SpreadMRKListSubMarks SubMarks = new SpreadMRKListSubMarks();
	private showstatistics Stats = new showstatistics();
	private ScoreCardButtons SCButtons = new ScoreCardButtons();
//	private printRoutines prnRoutines = new printRoutines();
	
	public  ArrayList<String> strArray = new ArrayList<String>();
	public  ArrayList<String> TotalMarksArray = new ArrayList<String>();
	public  ArrayList<String> subArray = new ArrayList<String>();
	public  ArrayList<String> subMarksArray = new ArrayList<String>();
	public  ArrayList<String> headerArray = new ArrayList<String>();
	public  ArrayList<String> StuDetailsArray = new ArrayList<String>();
	public  ArrayList<String> subjectName = new ArrayList<String>();
	public  ArrayList<String> Failedin3Subjs = new ArrayList<String>();
	
	
	public void show(float percent) {JOptionPane.showMessageDialog(null, percent);}   ///for debugging
	public void show(int num) {JOptionPane.showMessageDialog(null, num);}   ///for debugging
	public void show(String[] msg) {JOptionPane.showMessageDialog(null, msg);}   ///for debugging
	public void show(String msg) {JOptionPane.showMessageDialog(null, msg);}   ///for debugging
	public void Show(Object object) {JOptionPane.showMessageDialog(null, object);}   ///for debugging	
	public void Show(ArrayList<String> arrayList) {JOptionPane.showMessageDialog(null, arrayList);}   ///for debugging
	
	public int[] GraceMrks = {0,0,0,0,0,0};
	
	public SpreadMRKListController(SpreadMRKListModel model, SpreadMRKListView view){

	        this.Model = model;
	        this.View = view;   
		    System.out.println(model.getJarPath());       ///set JAR path in model variable path;
	    }
	
    String subject[] = { "ENG", "HIN", "MAR", "TAM", "ITE", "EL1", "CS1", "ECO", "BIO", "EL2", "CS2",
    		             "BKE", "PHY", "OCM", "CHE", "MAT", "SEP", "EVS", "PTE" };
    
    String subjectExamType[] = {"U1=ENG","U2=ENG","T1=ENG","T2=ENG","U1=HIN","U2=HIN","T1=HIN","T2=HIN","U1=MAR","U2=MAR","T1=MAR","T2=MAR",
    		                    "U1=TAM","U2=TAM","T1=TAM","T2=TAM","U1=PHY","U2=PHY","T1=PHY","T2=PHY","U1=CHE","U2=CHE","T1=CHE","T2=CHE",
    		                    "U1=BIO","U2=BIO","T1=BIO","T2=BIO","U1=BKE","U2=BKE","T1=BKE","T2=BKE","U1=ECO","U2=ECO","T1=ECO","T2=ECO",
    		                    "U1=OCM","U2=OCM","T1=OCM","T2=OCM","U1=MAT","U2=MAT","T1=MAT","T2=MAT","U1=SEP","U2=SEP","T1=SEP","T2=SEP",
    		                    "U1=CS1","U2=CS1","T1=CS1","T2=CS1","U1=CS2","U2=CS2","T1=CS2","T2=CS2","U1=EL1","U2=EL1","T1=EL1","T2=EL1",
    		                    "U1=EL2","U2=EL2","T1=EL2","T2=EL2","U1=IT1","U2=IT1","T1=IT1","T2=IT1","EVS", "PTE" };
			  
    String subjectOPP[] = { "ENGORA", "HINORA", "MARORA", "TAMORA", "PHYPRA", "CHEPRA", "BIOPRA", "BKEPRO", "ECOPRO", "OCMPRO", "MATPRA",
             "SEPPRO", "CS1PRA", "CS2PRA", "EL1PRA", "EL2PRA", "ITEPRA", "EVSPRO", "PTEPRA"};					  
	
    public void SetData1(JTable table,Object obj, int row_index, int col_index){View.getTable().getModel().setValueAt(obj,row_index,col_index);  }
    public void SetData(Object obj, int row_index, int col_index){View.getTable().getModel().setValueAt(obj,row_index,col_index);  }
    public Object GetData(JTable table, int row_index, int col_index)  { return View.getTable().getValueAt(row_index, col_index); }
    public String GetData1(JTable table, int row_index, int col_index) { return (String) View.getTable().getValueAt(row_index, col_index); }
    public int GetData2(JTable table, int row_index, int col_index) {  return (int) View.getTable().getValueAt(row_index, col_index); }
    
    
    private ActionListener saveListener, loadListener, processListener, searchListener, ResultListener,
	                       setprinterListener, printCurrentListener, printAllListener, canselListener, 
	                       UpdateListener, spreadsheetListener, printConsolidatedListener, failedNumbersListener ;
    
    int TotalMarklists=0;
	
	public void contol(){
		
		int n = 2000;
		ResizeTable(View.getTable(),n);

/*		for(int i = 0; i < n ; i++){
			{
				String SrNo=String.format("%d",i+1);
		        SetData(SrNo,i,0);
		    }
		}                               */
		
		 SetPrinter sp=new SetPrinter();
	        String printername=sp.LoadPreferences();
	        Model.setPrinterName(printername);
	        View.setPrinterLabel(printername);
	        
	        
	        View.getTable().addKeyListener(new KeyListener() {				
				@Override
				public void keyTyped(KeyEvent arg0) {
					// TODO Auto-generated method stub					
				}				
				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub	
					  String RowNo = null;
					  int column = 0;
					  int row = View.getTable().getSelectedRow();         // To get the row number of JTable 
					  String RollNo = View.getTable().getModel().getValueAt(row, 1).toString();
					  int keyCode = e.getKeyCode();
					    switch( keyCode ) { 
					        case KeyEvent.VK_UP:				           
					        SearchByRollNo(RollNo);
					        break;
					    
					        case KeyEvent.VK_DOWN:
					        SearchByRollNo(RollNo);
					        break;					       
					     }	
				}				
				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
								
				}
			});	           	
	        	        
	        View.getTable().addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent evt) {
//	            	boolean mouseclicked = true;  
	            	String RollNo = null;
	            	int Rows = View.getTable().getRowCount();
	                int row = View.getTable().rowAtPoint(evt.getPoint());
	                         RollNo = GetData1(View.getTable(), row, 1 );
	                SearchByRollNo(RollNo);                  
	            }	                
	});	        
	        
	canselListener = new ActionListener() {
	public void actionPerformed(ActionEvent actionEvent) {
	                BtnCancel();
	            }
	  }; 
	saveListener = new ActionListener() {
	public void actionPerformed(ActionEvent actionEvent) {                  
	                BtnSave();
	            }
	  }; 
	    	
	loadListener = new ActionListener() {
	public void actionPerformed(ActionEvent actionEvent) {                  
	                BtnLoad();
	            }
	  };
	  
	ResultListener = new ActionListener() {
	public void actionPerformed(ActionEvent actionEvent) {                  
		           btnResult();
			            }
			  };
	  
	processListener = new ActionListener() {
	public void actionPerformed(ActionEvent actionEvent) {                  
	                BtnProcess();
	            }
	  };
	        
	failedNumbersListener = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {                  
            BtnFailedNumbers();
        }
};
	  
	searchListener = new ActionListener() {
	public void actionPerformed(ActionEvent actionEvent) {                  
	                BtnSearch();
	            }
	  };
	
    UpdateListener = new ActionListener() {
    	public void actionPerformed(ActionEvent actionEvent) {                  
            BtnUpdate();
        }
     }; 
	  
	setprinterListener = new ActionListener() {
	public void actionPerformed(ActionEvent actionEvent) {                  
	                BtnSetPrinter();
	            }
	  };
	        
	printCurrentListener = new ActionListener() {
	public void actionPerformed(ActionEvent actionEvent) {                  
	                BtnPrintCurrent();
	            }
	  }; 
	  
	printAllListener = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {                  
			BtnPrintAllMarksCards();
        }
    }; 

    spreadsheetListener = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {                  
			BtnPrintSpreadSheet();
        }
    }; 
    
    printConsolidatedListener = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {                  
			BtnPrintConsolidated();
        }
    }; 
    

	View.getSaveButton().addActionListener(saveListener);
	View.getLoadButton().addActionListener(loadListener);
	View.getResultButton().addActionListener(ResultListener);
	View.getProcessButton().addActionListener(processListener);
	View.getFailedNums().addActionListener(failedNumbersListener);
	View.getSearchButton().addActionListener(searchListener);
	View.getSetPrinterButton().addActionListener(setprinterListener);
	View.getPrintCurrentButton().addActionListener(printCurrentListener);
	View.getPrintAllButton().addActionListener(printAllListener);
	View.getCanselButton().addActionListener(canselListener);
	View.getUpdateButton().addActionListener(UpdateListener);
	View.getSpreadSheetButton().addActionListener(spreadsheetListener);
	View.getPrintConsolidatedButton().addActionListener(printConsolidatedListener);

	 }	

	
	protected void BtnFailedNumbers() {
		// TODO Auto-generated method stub
//	     ClearTable();
//	     ResizeTable(View.getTable(),Model.strArray.size());

		
	}
	protected void BtnPrintConsolidated() {
//		System.exit(0);
		
		  try {
              final String[] TableItemC1 = {"Exam","Unit I","Term I","Unit II","TermII",
            		                        "Agg","Avg","Grace"};
              final String[] TableItemC2 = {"Max", "25", "50", "25", "100", "-----", "-----", "15"};
              final String[] TableItemC3 = {"Min", "-----", "-----", "-----", "-----", "70", "35", "-----"};
              final String[] RowHeader = {"EVS", "PTE", "    Total"};
              final String[] StuDetails = {"Roll No:", "Div", ""};
			  PrinterJob pjob = PrinterJob.getPrinterJob();
			  pjob.setCopies(1);			    
			  pjob.setJobName("Consolidated Marks Card");
			  pjob.setPrintable(new Printable() {
			  public int print(Graphics pg, PageFormat pf, int pageNum) {
				int RowCount = View.getTable().getRowCount();  
				int[] ExamwiseSum = {SumU1Score(pageNum), SumT1Score(pageNum), SumU2Score(pageNum), 
						             SumT2andEVSScore(pageNum)};
				final int[] SubTotal = {Sub1(pageNum), Sub2(pageNum), Sub3(pageNum), 
						                Sub4(pageNum), Sub5(pageNum), Sub6(pageNum)};
				int Rows = View.getTable().getRowCount();
				
				int totalpages = Rows/12;     //   RowCount;
				  if (pageNum < totalpages) 
				   {
					Font newFont;		          
					newFont = new Font("Liberation Serif", Font.BOLD, 9);
                    pg.setFont(newFont);
                    
//					pg.drawString("AA",10,10);
//					pg.drawString("AB",580,10);
//					pg.drawString("AC",10,780);
//					pg.drawString("AD",580,780);
                    
    pg.drawLine(315, 20, 315, 770);                         //  Vertical Divider LINE
    pg.drawString(String.valueOf(pageNum+1), 580, 780);     //  Pane Number at Right Bottom Corner.
    for(int r = 0; r < 6; r++){
       pg.drawLine(35, 140+r*125, 600, 140+r*125);          //  Horizontal Divider LINES
    }
	int LGltx = 35, LGlty = 30, BRWidth = 30, SRWidth = 20, jump = 285 , ht =  12;  //  BRWidth = Big Rectangle width SR = Small Rect
	int YCText = 39, JVNGrid = 125;   //Y Coordinate of Text, Jump to Vertically Next Grid, 	
	for(int r = 0; r < 6; r++){ 
		 for(int i = 0; i < 8; i++){
		   pg.drawRect(LGltx,  (LGlty+r*JVNGrid)+i*ht, BRWidth, ht); // Printing LEFT columns grid  Left of Page
		   pg.drawRect(265, (LGlty+r*JVNGrid)+i*ht, 45, ht);         // Printing RIGHT columns grid   Left of Page
		   pg.drawRect(LGltx+jump, (30+r*JVNGrid)+i*ht, 30, ht);         // Printing LEFT columns grid   Right of Page
		   pg.drawRect(550, (LGlty+r*JVNGrid)+i*ht, 45, ht);         // Printing RIGHT columns grid  Right of Page
		   pg.drawString(TableItemC1[i], 36, (YCText+r*JVNGrid)+i*ht);
		   pg.drawString(TableItemC2[i], 66, (YCText+r*JVNGrid)+i*ht);
		   pg.drawString(TableItemC3[i], 87, (YCText+r*JVNGrid)+i*ht);
           pg.drawString(TableItemC1[i], 321,(YCText+r*JVNGrid)+i*ht);
     	   pg.drawString(TableItemC2[i], 351,(YCText+r*JVNGrid)+i*ht);
           pg.drawString(TableItemC3[i], 372,(YCText+r*JVNGrid)+i*ht);
		 }
		   pg.drawString("Result :",36 ,  135+r*JVNGrid);
		   pg.drawString("Result :",321 , 135+r*JVNGrid);
		   pg.drawString("Name:",115,  28+r*JVNGrid);
		   pg.drawString("Name:",400,  28+r*JVNGrid);
		   
    	}

	for(int r = 0; r < 6; r++){
		   for(int j =0; j < 10; j++){
			 for(int i = 0; i < 8; i++){
			   pg.drawRect(65+j*20,  (30+r*JVNGrid)+i*ht, SRWidth, ht);   // Printing Body of Marks Sheets    Left of Page
			   pg.drawRect(350+j*20, (30+r*JVNGrid)+i*ht, SRWidth, ht);   // Printing Body of Marks Sheets   Righr of page
			 }
		   }		
		}
	
	for(int r = 0; r < 6; r++){	
    	for(int i = 0; i < 3; i++){
		   pg.drawString(RowHeader[i], 226+i*20, 39+r*JVNGrid);      //  PTE and EVS - Left of Page
		   pg.drawString(RowHeader[i], 511+i*20, 39+r*JVNGrid);      //  PTE and EVS - Right of Page
		   pg.drawString(StuDetails[i],35+i*55,  28+r*JVNGrid);      //  Roll No and Division   Left of Page
		   pg.drawString(StuDetails[i],320+i*55, 28+r*JVNGrid);     //  Roll No and Division  Right of Page
	     }
	}
    int i = 0;
	int y = 28;
//        String Roll = GetData1(View.getTable(), i+pageNum*12, 1);          //  Printing Roll Numbers
        for(int j = 0; j < 6; j++){
        	for(int k = 0; k< 2; k++){
        		String Roll = GetData1(View.getTable(), i+pageNum*12, 1);
        		String Div = GetData1(View.getTable(), i+pageNum*12, 2);
        		String Name = GetData1(View.getTable(), i+pageNum*12, 3);
        		pg.drawString(Roll, 70+k*jump, y);
        		pg.drawString(Div, 105+k*jump, y);
        		pg.drawString(Name.substring(0, 28), 142+k*jump, y);
        		if(i < 12)i++;
        	}
            y = y + JVNGrid;	
        }                       
       
        
//	pg.drawString("9999",70,  28);
//	pg.drawString("H",105,  28);
//	pg.drawString("H",392,  28);
//	pg.drawString("Name:",115,  28);
//	pg.drawString("Nagaraj",142,  28);
     
/*				for(int j =0; j < 12; j++){
					 for(int i = 0; i < 8; i++){
					   if(j == 0) { pg.drawRect(60, 300+i*20, 80, 20); }            // Printing LEFT columns grid
					   if(j == 11){ pg.drawRect(490, 300+i*20, 70, 20);}            // Printing RIGHT columns grid
					   if(j>0 &&j<11) {pg.drawRect(105 + j*35, 300+i*20, 35, 20);}  // Printing Body of Marks Sheets
					 }					
				}
				for(int j = 0; j < 3; j++){ if(j == 2) pg.drawRect(384, 460, 55, 20);
					                        else pg.drawRect(210 + j*87, 460, 88, 20); }       // Printing Bottom Rectangles							
				for(int i = 0; i < 8; i++){pg.drawString(TableItemC1[i], 62, 315+i*20);}
				for(int i = 0; i < 8; i++){pg.drawString(TableItemC2[i], 145, 315+i*20);}
				for(int i = 0; i < 8; i++){pg.drawString(TableItemC3[i], 182, 315+i*20);}
				
				String RollNo = View.getTable().getModel().getValueAt(pageNum, 1).toString();
				subjectName = collheaderfinder(RollNo);
				for(int i = 0; i< subjectName.size(); i++){ pg.drawString(subjectName.get(i), 214+i*35, 315);}	 // Subjects

				for(int j = 0; j < 6; j++){
					for(int i = 0; i < 4; i++){                     //  All marks except EVS and PTE
						pg.drawString(GetData1(View.getTable(), pageNum, 4+i+(4*j)), 220+j*35, 335+i*20);
					}
                }
		        pg.drawString(GetData1(View.getTable(), pageNum, 28), 430, 395);     // EVS Marks
		        pg.drawString(GetData1(View.getTable(), pageNum, 29), 465, 395);     // PTE Grade
*/		        
/////   E X A M W I S E   S U M
				
/*				for(int i = 0; i < 4; i++){
					pg.drawString(String.valueOf(ExamwiseSum[i]), 515, 335+i*20);  // Sum of all Unit n Term Exams
				}
/////  A  G  G  R  E  G  A  T  E
				
//				int row = View.getTable().getSelectedRow();  
		        for(int i = 0; i <6; i++){
		        	pg.drawString(String.valueOf(SubTotal[i]), 215+i*35, 415);  // Aggregate marks of each subject
		        }        
		        pg.drawString(GetData1(View.getTable(),pageNum,28), 430, 415);    // Marks of EVS Subject
		        

/////  A  V  E  R  A  G  E  MARKS OF EVERY SUBJECT
		        
		float[] AverageMrks = {Sub1(pageNum), Sub2(pageNum), Sub3(pageNum), 
		   		               Sub4(pageNum), Sub5(pageNum), Sub6(pageNum)};
		for(int i = 0; i < 6; i++){
			pg.drawString(String.valueOf((int) Math.ceil(AverageMrks[i]/2)), 220+i*35, 435); // Average marks of each subject
        }
		pg.drawString(GetData1(View.getTable(), pageNum, 28), 430, 435);    // Marks of EVS Subject		        
		pg.drawString(String.valueOf(SumU1Score(pageNum)+SumT1Score(pageNum)+SumU2Score(pageNum)
		 	                     +SumT2andEVSScore(pageNum)+"/1250" ), 493, 415);  // Sum of all U1, T1, U2, T2
		
        int AvgSum = 0;
        for(int i = 0; i < 6; i++){	AvgSum += (int) Math.ceil(AverageMrks[i]/2); }
        int AvgTotal = AvgSum + EVSmarks(pageNum);
        pg.drawString(String.valueOf(AvgTotal)+"/650",500,435);          // Sum of all Averages

		
//////  R E S U L  T    P A R T   
		
		String result = Mod(pageNum);
		pg.drawString(result, 302, 475);
		int[] GraceValues = Mod1(pageNum);
		int GraceTotal = 0;
		for(int j = 0; j < 6; j++){GraceTotal += GraceValues[j];}
//		show(GraceTotal);
		for(int i = 0; i < 6; i++){    
			if(result == "Promoted"){pg.drawString(String.valueOf(GraceValues[i]), 220+i*35, 455);
                                     pg.drawString(String.valueOf(GraceTotal)+"/15", 505, 455); }
			else pg.drawString("", 220+i*35, 455);
		}
		
		String percent = Percent(pageNum);
		if(result == "Promoted" || result == "Fail") pg.drawString(" ", 387, 475);
		else pg.drawString(percent+" %", 387, 475);


			   	
/////   S T U D E N T  D E T A I L S
		        
		        StuDetailsArray.removeAll(StuDetailsArray);
			   	for(int k = 1; k < 4 ; k++){	   		
			   		StuDetailsArray.add((String) GetData1(View.getTable(),pageNum,k));
			   	}
				
				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
				int YEAR = year-1;

				pg.drawString("Mark Sheet showing the number of marks Obtained by  ", 60, 200);
				pg.drawString(StuDetailsArray.get(2), 60, 220);   //  Name of student 
//				pg.drawString("with Roll No.:"+ StuDetailsArray.get(0)+" of Division : "
//				              +StuDetailsArray.get(1)+", in " + Streamfinder(RollNo)+ " stream", 60, 240);
				pg.drawString("The following table shows each head of passing at FYJC examintion conducted", 60, 260);
				pg.drawString("during the academic year " + YEAR +" - " + year, 60, 280);				
				
/////    F O O T E R - G O V E R N M E N T   C I R C U L A R		
				
				pg.drawString("NOTE  :  This marksheet has been prepared as per the instruction of circular", 60, 520);
				pg.drawString("No 6987,dated 04/11/2009 issued by Secretary, Maharashtra State", 120, 540);
				pg.drawString("Board of Secondary and Higher Secondary Education,Pune 411004", 120, 560);
*/
		   		
		   		
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
		    catch (PrinterException pe) {
		    pe.printStackTrace();
		  }                                     				


	}
	
	
	protected void BtnPrintSpreadSheet() {
		// 	System.exit(0);
	  try {
		  
		   PrinterJob pjob = PrinterJob.getPrinterJob();
		   pjob.setJobName("Spread Sheet Print");
		   pjob.setCopies(1);
		   int TMrg = 748, BMrg = 8, Center = 0,  x = 7, StringPosition = 0;  // TMrg = Top Margin, BMrg = Bottom Margin.
		   Center = (TMrg - BMrg)/2;
		   final String[] Exams = {"U1", "T1", "U2", "T2"};
		   final String[] HeadereSubjects = {"ENGLISH", "SL / VOC", "ECO/BIO/VOC", "BKE / PHY", "OCM / CHE", "MAT / SEP"};
		   pjob.setPrintable(new Printable() {
		   public int print(Graphics pg, PageFormat pf, int pageNum) {
		   int Rows = View.getTable().getRowCount();   
		   
			int totalpages = Rows/28;
			if (pageNum < totalpages) 
			{	
			  Font newFont;		          
			  newFont = new Font("Liberation Serif", Font.BOLD, 13);
			  int TMrg = 748, BMrg = 8, Center = 0,  x = 7, StringPosition = 0;  // TMrg = Top Margin, BMrg = Bottom Margin.

			  FontMetrics metrics = pg.getFontMetrics(newFont);
			  String collegename = View.CollName.getText();
			  String PlaceName = View.Place.getText();
			  Center = (TMrg - BMrg)/2;
			  int StartHeader1 = Center - (int) (metrics.stringWidth(collegename))/2;
			  int StartHeader2 = Center - (int) (metrics.stringWidth(PlaceName))/2;
		      for(int j = 0; j < 6; j++){		  			 
				pg.drawRect(50, 32+j*80, 17, 80);        // Printing Subject Headings
				}      
						          
				for(int j = 0; j < 29; j++){
				  for(int i = 0; i < 24; i++){	
				    pg.drawRect(67 + j*17, 32+i*20, 17, 20);        // Printing Rectangular grid ( Body of Table for Marks )
				    }
				}
								
				for(int j = 0; j < 30; j++){				   			 
				pg.drawRect(50 + j*17, 512, 17, 228);              // Rectangle for Printing Names
				} 								
								
				for(int j = 0; j < 30; j++){
						pg.drawRect(50 + j*17, 740, 17, 35);        //  Rectangle for Printing Serial No and Roll Numbers
					}		          		          

/*				pg.drawString("( FOR OFFICE USE ONLY )", 200, 20);
				pg.drawString("( FOR OFFICE USE ONLY )", 200, 785);							
				pg.drawString("AA",10,10);
				pg.drawString("AB",580,10);
				pg.drawString("AC",10,780);
				pg.drawString("AD",580,780);               */
																
				Graphics2D g2 = (Graphics2D) pg;
				Font font = new Font("Liberation Serif", Font.PLAIN, 12);    
				AffineTransform affineTransform = new AffineTransform();
				affineTransform.rotate(Math.toRadians(270), 400, 380);
				Font rotatedFont = font.deriveFont(affineTransform);
				g2.setFont(rotatedFont);
		        g2.drawString("Roll",10,60);
		        g2.drawString("NAME",125,60);
		        
		        pg.drawString(collegename, StartHeader1, 7);
		        pg.drawString(PlaceName, StartHeader2, 22);
		        
		        for(int i = 0; i < 6; i++){
		           if(i == 0) {pg.drawString(HeadereSubjects[i], 280, 43);}
		           if(i == 1) {pg.drawString(HeadereSubjects[i], 360, 43);}
		           if(i == 2) {pg.drawString(HeadereSubjects[i], 430, 43);}
		           if(i > 2) {pg.drawString(HeadereSubjects[i], 280+i*80, 43);}
		        }
		        
		        for(int i = 0; i < 6; i++){
		          for(int m = 0; m < 4; m++){
		              pg.drawString(Exams[m], (270+m*20)+i*80, 60);              //  Printing Exam Titles
		          }
		        }
		        		        
		        for( int i = 0; i < 28; i++){
		            String Roll = GetData1(View.getTable(), i+pageNum*28, 1);          //  Printing Roll Numbers
		        	pg.drawString(Roll, 5, 77+(i*17));
		        }

		        for( int i = 0; i < 28; i++){
		            String Name = GetData1(View.getTable(), i+pageNum*28, 3);         //  Printing Names  
		            if(Name.length() > 27){ pg.drawString(Name.substring(0, 27), 42, 77+i*17);}
		            else {pg.drawString(Name, 42, 77+i*17);}
		        }

		        for(int i = 0; i < 28; i++){                                     //  Number of Lines in one page = 28
			        for(int j = 4; j < 28; j++){
			        	String Marks = GetData1(View.getTable(), i+pageNum*28, j);
			        	pg.drawString(Marks, 190+j*20, 77+i*17);                     //  Printing ALL MARKS
			        }
		        }
		        
				g2.dispose();
																				
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
	
	
	public void displayAll(){
		String lines = null;
		lines = Model.strArray.get(4);
	}
	
	private void BtnSave(){
//	        System.exit(0);
			String fyle="";
			  JFileChooser choosertosave = new JFileChooser();
			  choosertosave.setMultiSelectionEnabled(true);
	          FileNameExtensionFilter filter = new FileNameExtensionFilter("Result View", "rlt");
	          choosertosave.setFileFilter(filter);
	          choosertosave.setCurrentDirectory(new File("E:/Eclipse/Prahlad/Test Entries"));
	          choosertosave.setCurrentDirectory(new File("/home/prahallad/Test Entries"));
	          choosertosave.setCurrentDirectory(new File("/home/siws/Blank Entries"));   
	          int option = choosertosave.showSaveDialog(choosertosave);

	          if (option == JFileChooser.APPROVE_OPTION)
	           {
	              File[] sf = choosertosave.getSelectedFiles();
	              String filelist = "nothing";
	              if (sf.length > 0) filelist = sf[0].getName();
	              for (int i = 1; i < sf.length; i++) 
	                {
	                  filelist += ", " + sf[i].getName();
	                }
	              fyle=sf[0].getPath();             
	              if (!fyle.endsWith(".rlt")) fyle+= ".rlt";
	              SaveToFile(fyle);                             //Save to File is called here
	              choosertosave.getSelectedFile().getName();
	           }  			        
	    }

	
	private void BtnLoad(){
//	        System.exit(0);
		Model.LoadData();
		process();
		
		
	    }

	private void btnResult(){
         
		for(int row = 0; row < 15; row++){
			String result =	Mod(row);
			SetData(result, row, 31);
		}		
	}
	
	public void process(){
	     ClearTable();
	     ResizeTable(View.getTable(),Model.strArray.size());
  	 String plate[];
  	 String rollno, names, div;		   	
		 for(int i=1; i < Model.strArray.size(); i++)                      //  strArray.size()
	     	{ 
		   		 String SrNo=String.format("%d",i);
				 SetData(SrNo,i-1,0);
		   		 plate=Model.strArray.get(i).split("#");
			     rollno = plate[0];
			     if(plate[1].length()<=12){ 
			     	rollno = plate[0];
			     	div = plate[2].substring(0,1);
			     	SetData(rollno,i-1,1);
				    SetData(div, i-1, 2);
		     	  }
	     	 else {
		     	  names = plate[1].substring(0, 60);
//		     	  str = str.replaceAll("\\s+"," ");                // REGEX replacing method - Removes all white spaces
		     	  names = names.replaceAll("\\s{2}", "").toUpperCase();          // Keeps one white space and removes extra white spaces
		     	  div = plate[2].substring(0,1);
		     	  SetData(rollno,i-1,1);
		     	  SetData(div, i-1, 2);
		     	  SetData(names,i-1,3);
	     	  }
	     	  
      }
		 
//		 displayAll();
//		SubMarks.ENGMarks();
//		Model.ENGMarks();
//	   Model.test();	 
       ENGMarks();
       SecLangMarks();
       VocationalMarks();
       BIOMarks();
       ECOMarks();
       BKEMarks();
       PHYMarks();
       OCMMarks();
       CHEMarks();
       MATMarks();
       SEPMarks();
       EVSMarks();
       PTEGrade();
       TotalScore(); 
 //      Result();
  }


	
	private void BtnProcess(){
//	    	System.exit(0);
		String EngMarks = GetData1(View.getTable(),5,28);
//		show(EngMarks);
		String Eng = GetData1(View.getTable(),7,28);
		Stats.SetData(EngMarks, 1,6);
//		show(Eng);
		Stats.SetData1(Eng, 0, 4);
		Stats.ShowStats();

		
	}                    
	    	    	    
	private void BtnSearch(){
//	        System.exit(0);
		String[] NAMES;
    	String plate[];
    	String subject = null;
    	String rollno, names;
    	boolean foundName = false, required = true , found = false;
    	int dialogButton = 0 ;
		String RollNo = View.Search().getText();
		String Name = View.Search().getText();		
		int Rows = View.getTable().getRowCount();
		if(Name.contentEquals("") || RollNo.contentEquals("") || Name == null || RollNo == null){
		  show("Enter a Valid Roll Number OR Name to search"); return;}
		  for (int row = 0; row < Rows-1; row++) {				
			  plate=Model.strArray.get(row+1).split("#");
			  String SameRoll = GetData1(View.getTable(), row, 1);
			  rollno = plate[0].substring(0);	
			  
			if(RollNo.trim().equals(SameRoll.trim())){		//	show(rollno);		
			found = true;
			// this will automatically set the view of the scroll in the location of the value
			View.getTable().scrollRectToVisible(View.getTable().getCellRect(row, 0, true));
					
            // this will automatically set the focus of the searched/selected row/value
			View.getTable().setRowSelectionInterval(row, row);	
				 for (int i = 0; i <= View.getTable().getColumnCount()-1; i++) {
	
					 View.getTable().getColumnModel().getColumn(i).setCellRenderer(new HighlightRenderer());
	             }					
			}		

			 for (int j = 2; j < plate.length; j++){							 
	     	   subject = plate[j].substring(5, 8);
	     	   rollno = plate[0].substring(0);
	     		  if(RollNo.trim().equals(rollno.trim())){    		    	     		    
	     		    if(subject.contains("ENG")){ 
	     		    	View.Eng.text="ENG"; }
	     		    
	     		if(subject.contains("MAR")||subject.contains("TAM")||subject.contains("HIN")||subject.contains("EL1")
	     				||subject.contains("ITE")||subject.contains("CS1")){ 
	     		    	View.SL.text= subject; }
	     		   
	     		if(subject.contains("ECO")||subject.contains("BIO")
	     				||subject.contains("EL2")||subject.contains("CS2")){ 
	     		    	View.Sub1.text= subject; }
	     		  
	     		if(subject.contains("BKE")||subject.contains("PHY")){ 
		     		    View.Sub2.text= subject; }
	     		 
	     		if(subject.contains("OCM")||subject.contains("CHE")){ 
	     		    	View.Sub3.text= subject; }
	     		 
	     		if(subject.contains("MAT")||subject.contains("SEP")){ 
     		     	    JTableHeader th = View.getTable().getTableHeader();  //  For header changing dynamically
     		   	        th.repaint();      
     		    	    View.Sub4.text= subject; }     		   
	     		  }
	     	   }
			 
			 
			  if(plate[1].length()<=12){names = plate[1].substring(0, 0); }  // For table or .rlt file with out names
			  else names = plate[1].substring(0, 60);
		      NAMES = names.split("\\(");   // Split at Parenthesis  show(NAMES[0].toUpperCase());		    
		      if(NAMES[0].toUpperCase().contains(Name.toUpperCase())){ foundName = true; }			 
			  if(foundName && required){
			    	found = true;
			    	// this will automatically set the view of the scroll in the location of the value
					View.getTable().scrollRectToVisible(View.getTable().getCellRect(row, 0, true));
					
		            // this will automatically set the focus of the searched/selected row/value
					View.getTable().setRowSelectionInterval(row, row);	
			    	
			    	for (int i = 0; i <= View.getTable().getColumnCount()-1; i++) {
							
			    		View.getTable().getColumnModel().getColumn(i).setCellRenderer(new HighlightRenderer());
		            }
					dialogButton = JOptionPane.showConfirmDialog (null, "Found !!  Would you like to continue search","WARNING", JOptionPane.YES_NO_OPTION);
			    	if(dialogButton == JOptionPane.YES_OPTION){  required = true; /* Show(required);*/ } 				    	
			    }	
			  
			    if(foundName){
			    	 for (int j = 2; j < plate.length; j++){							 
				     	   subject = plate[j].substring(5, 8);
//				     	   rollno = plate[0].substring(0);
				     	if(names.contains(Name.toUpperCase())){
	     		       		    
				     		if(subject.contains("ENG")){ 
//				     		     	JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
//				     		   	    th.repaint();      
				     		    	View.Eng.text="ENG"; }
				     		    
				     		if(subject.contains("MAR")||subject.contains("TAM")||subject.contains("HIN")||subject.contains("EL1")
				     				||subject.contains("ITE")||subject.contains("CS1")){ 
//				     		     	JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
//				     		   	    th.repaint();      
				     		    	View.SL.text= subject; }
				     		   
				     		if(subject.contains("ECO")||subject.contains("BIO")
				     				||subject.contains("EL2")||subject.contains("CS2")){ 
//				     		     	JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
//				     		   	    th.repaint();      
				     		    	View.Sub1.text= subject; }
				     		  
				     		if(subject.contains("BKE")||subject.contains("PHY")){ 
//					     		    JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
//					     		   	th.repaint();      
					     		    View.Sub2.text= subject; }
				     		 
				     		if(subject.contains("OCM")||subject.contains("CHE")){ 
//				     		     	JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
//				     		   	    th.repaint();      
				     		    	View.Sub3.text= subject; }
				     		 
				     		if(subject.contains("MAT")||subject.contains("SEP")){ 
			     		     	    JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
			     		   	        th.repaint();      
			     		    	    View.Sub4.text= subject; }
			     		   
				     		  }
				     		   
				     	   }
			    	 if(dialogButton == JOptionPane.NO_OPTION){  required = false; break; }
			    	 foundName = false;
			    }			 		
         }
		  
		if(found == false){ show("No Such Roll Number OR Name Found"); }  
}		
	
	
   private void BtnPrintCurrent(){			

//	   SCButtons.showScoreButtons(); 
	   final ArrayList<String> subject;
	    int row = View.getTable().getSelectedRow();
	    if(row < 0){show("No name or Roll Number is selected "); return;}
	    subMarksArray.removeAll(subMarksArray);   
	   	for(int k = 4; k < 30 ; k++){	   		
	   		subMarksArray.add((String) GetData1(View.getTable(),row,k));
	   	}
	   	
	   	StuDetailsArray.removeAll(StuDetailsArray);
	   	for(int k = 1; k < 4 ; k++){	   		
	   		StuDetailsArray.add((String) GetData1(View.getTable(),row,k));
	   	}
	   	
		  final String RollNo = View.getTable().getModel().getValueAt(row, 1).toString();
//		  ArrayList<String> subject;		  
		  subject = collheaderfinder(RollNo);
		  String EVS = GetData1(View.getTable(),row,28);
		  
	 try {
		 final String[] TableItemC1 = {"Examination","Unit Test I","Terminal I","Unit test II","Terminaal II",
	                "Aggregate","Average","Grace"};
         final String[] TableItemC2 = {"Max", "25", "50", "25", "100", "-----", "-----", "15"};
         final String[] TableItemC3 = {"Min", "-----", "-----", "-----", "-----", "70", "35", "-----"};
         final int[] SubTotal = {Sub1(row),Sub2(row), Sub3(row), Sub4(row), Sub5(row), Sub6(row)};
         final int[] ExamwiseSum = {SumU1Score(row), SumT1Score(row), SumU2Score(row), SumT2andEVSScore(row)};
		 
	      PrinterJob pjob = PrinterJob.getPrinterJob();
		  pjob.setJobName("Current Marks Card Print");
		  pjob.setCopies(1);
		  pjob.setPrintable(new Printable() {
		  public int print(Graphics pg, PageFormat pf, int pageNum) {
		  int totalpages = 0;
		  if (pageNum > totalpages)                                 // we only print one page
		  return Printable.NO_SUCH_PAGE;                            // ie., end of job
		  Font newFont;		          
		  newFont = new Font("Liberation Serif", Font.PLAIN, 13);
		  int LtMrg = 40;       // Left Top x, Left Top y and Left Margin
		  int BtMrg = 750;	        		          		          		
		  pg.drawString("EVS", 425, 315);
		  pg.drawString("PTE", 460, 315);          
		  pg.drawString("Total", 505, 315);
		  pg.drawString("RESULT", 230, 475);

		  for(int j =0; j < 12; j++){
			for(int i = 0; i < 8; i++){
			  if(j == 0) { pg.drawRect(60, 300+i*20, 80, 20); }            // Printing LEFT columns grid
			  if(j == 11){ pg.drawRect(490, 300+i*20, 70, 20);}            // Printing RIGHT columns grid
			  if(j>0 &&j<11) {pg.drawRect(105 + j*35, 300+i*20, 35, 20);}  // Printing Body of Marks Sheets
			}					
		  }
		  for(int j = 0; j < 3; j++){ if(j == 2) pg.drawRect(384, 460, 55, 20);
                                      else pg.drawRect(210 + j*87, 460, 88, 20); }       // Printing Bottom Rectangles							
		  for(int i = 0; i < 8; i++){pg.drawString(TableItemC1[i], 62, 315+i*20);}
		  for(int i = 0; i < 8; i++){pg.drawString(TableItemC2[i], 145, 315+i*20);}
		  for(int i = 0; i < 8; i++){pg.drawString(TableItemC3[i], 182, 315+i*20);}
		  for(int i = 0; i < subject.size(); i++){ pg.drawString(subject.get(i), 214+i*35, 315);}	 // Subjects
	 	  
		pg.drawString("( FOR OFFICE USE ONLY )", 230, LtMrg);
		pg.drawString("( FOR OFFICE USE ONLY )", 230, BtMrg);
		int k = 0;
  	    for(int i= 0; i < 6; i++){
	        for(int j = 0; j < 4; j++){
			 pg.drawString(subMarksArray.get(k), 220+i*35, 335+j*20);   //  All Marks
			 k++;
			}								
		}		
		
///// E X A M W I S E   S U M
		
		pg.drawString(subMarksArray.get(24), 430, 395);               //  EVS marks
		pg.drawString(subMarksArray.get(25), 465, 395);               //  PTE Grade
		for(int i = 0; i < 4; i++){
			pg.drawString(String.valueOf(ExamwiseSum[i]), 515, 335+i*20);  // Sum of all Unit n Term Exams
		}
				
/////  A  G  G  R  E  G  A  T  E
		
		int row = View.getTable().getSelectedRow();  
        for(int i = 0; i <6; i++){
        	pg.drawString(String.valueOf(SubTotal[i]), 215+i*35, 415);  // Aggregate marks of each subject
        }        
        pg.drawString(GetData1(View.getTable(),row,28), 430, 415);    // Marks of EVS Subject

/////  A  V  E  R  A  G  E  MARKS OF EVERY SUBJECT
        
        float[] AverageMrks = {Sub1(row), Sub2(row), Sub3(row), Sub4(row), Sub5(row), Sub6(row)};
        for(int i = 0; i < 6; i++){
    		pg.drawString(String.valueOf((int) Math.ceil(AverageMrks[i]/2)), 220+i*35, 435); // Average marks of each subject

        }
		
        pg.drawString(GetData1(View.getTable(),row,28), 430, 435);    // Marks of EVS Subject
        
        newFont = new Font("Liberation Serif", Font.PLAIN, 9);

		pg.drawString(String.valueOf(SumU1Score(row)+SumT1Score(row)+SumU2Score(row)
				                     +SumT2andEVSScore(row)+"/1250" ), 493, 415);  // Sum of all U1, T1, U2, T2

        int AvgSum = 0;
        for(int i = 0; i < 6; i++){	AvgSum += (int) Math.ceil(AverageMrks[i]/2); }
        int AvgTotal = AvgSum + EVSmarks(row);
        pg.drawString(String.valueOf(AvgTotal)+"/650",500,435);          // Sum of all Averages
        
		
//////  R E S U L  T    P A R T   		

		String result = Mod(row);
		pg.drawString(result, 302, 475);		
		int[] GraceValues = Mod1(row);
		int GraceTotal = 0;
		for(int j = 0; j <6; j++){GraceTotal += GraceValues[j];}
//		show(GraceTotal);
		for(int i = 0; i < 6; i++){    
			if(result == "Promoted"){pg.drawString(String.valueOf(GraceValues[i]), 220+i*35, 455);
			                         pg.drawString(String.valueOf(GraceTotal)+"/15", 505, 455); }
			else pg.drawString("", 220+i*35, 455);
		}
		String percent = Percent(row);
		if(result == "Promoted" || result == "Fail") pg.drawString(" ", 387, 475);
		else pg.drawString(percent+" %", 387, 475);
		
			    				
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int YEAR = year-1;

		newFont = new Font("Liberation Serif", Font.PLAIN, 10);

		pg.drawString("Mark Sheet showing the number of marks Obtained by  ", 60, 200);
		pg.drawString(StuDetailsArray.get(2), 60, 220);   //  Name of student 
		pg.drawString("with Roll No.:"+ StuDetailsArray.get(0)+" of Division : "
		              +StuDetailsArray.get(1)+", in " + Streamfinder(RollNo)+ " stream", 60, 240);
		pg.drawString("The following table shows each head of passing at FYJC examintion conducted", 60, 260);
		pg.drawString("during the academic year " + YEAR +" - " + year, 60, 280);				
		
/////  F O O T E R		
		pg.drawString("NOTE  :  This marksheet has been prepared as per the instruction of circular", 60, 520);
		pg.drawString("No 6987,dated 04/11/2009 issued by Secretary, Maharashtra State", 120, 540);
		pg.drawString("Board of Secondary and Higher Secondary Education,Pune 411004", 120, 560);
		
		
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
   
 	public void BtnPrintAllMarksCards(){
		
//	    int row = View.getTable().getSelectedRow();
//	    if(row < 0){show("No name or Roll Number is selected "); return;}

		  try {
              final String[] TableItemC1 = {"Examination","Unit Test I","Terminal I","Unit test II","Terminaal II",
            		                        "Aggregate","Average","Grace"};
              final String[] TableItemC2 = {"Max", "25", "50", "25", "100", "-----", "-----", "15"};
              final String[] TableItemC3 = {"Min", "-----", "-----", "-----", "-----", "70", "35", "-----"};
			  PrinterJob pjob = PrinterJob.getPrinterJob();
			  pjob.setCopies(1);			    
			  pjob.setJobName("All Marks Card - Print");
			  pjob.setPrintable(new Printable() {
			  public int print(Graphics pg, PageFormat pf, int pageNum) {
				int RowCount = View.getTable().getRowCount();  
				int[] ExamwiseSum = {SumU1Score(pageNum), SumT1Score(pageNum), SumU2Score(pageNum), 
						             SumT2andEVSScore(pageNum)};
				final int[] SubTotal = {Sub1(pageNum), Sub2(pageNum), Sub3(pageNum), 
						                Sub4(pageNum), Sub5(pageNum), Sub6(pageNum)};
				int totalpages = 15;     //   RowCount;
				  if (pageNum < totalpages) 
				   {
					pg.drawString("( FOR OFFICE USE ONLY )", 230, 40);
					pg.drawString("( FOR OFFICE USE ONLY )", 230, 750);
					pg.drawString("EVS", 425, 315);
					pg.drawString("PTE", 460, 315);          
					pg.drawString("Total", 505, 315);
					pg.drawString("RESULT", 230, 475);
				for(int j =0; j < 12; j++){
					 for(int i = 0; i < 8; i++){
					   if(j == 0) { pg.drawRect(60, 300+i*20, 80, 20); }            // Printing LEFT columns grid
					   if(j == 11){ pg.drawRect(490, 300+i*20, 70, 20);}            // Printing RIGHT columns grid
					   if(j>0 &&j<11) {pg.drawRect(105 + j*35, 300+i*20, 35, 20);}  // Printing Body of Marks Sheets
					 }					
				}
				for(int j = 0; j < 3; j++){ if(j == 2) pg.drawRect(384, 460, 55, 20);
					                        else pg.drawRect(210 + j*87, 460, 88, 20); }       // Printing Bottom Rectangles							
				for(int i = 0; i < 8; i++){pg.drawString(TableItemC1[i], 62, 315+i*20);}
				for(int i = 0; i < 8; i++){pg.drawString(TableItemC2[i], 145, 315+i*20);}
				for(int i = 0; i < 8; i++){pg.drawString(TableItemC3[i], 182, 315+i*20);}
				
				String RollNo = View.getTable().getModel().getValueAt(pageNum, 1).toString();
				subjectName = collheaderfinder(RollNo);
				for(int i = 0; i< subjectName.size(); i++){ pg.drawString(subjectName.get(i), 214+i*35, 315);}	 // Subjects

				for(int j = 0; j < 6; j++){
					for(int i = 0; i < 4; i++){                     //  All marks except EVS and PTE
						pg.drawString(GetData1(View.getTable(), pageNum, 4+i+(4*j)), 220+j*35, 335+i*20);
					}
                }
		        pg.drawString(GetData1(View.getTable(), pageNum, 28), 430, 395);     // EVS Marks
		        pg.drawString(GetData1(View.getTable(), pageNum, 29), 465, 395);     // PTE Grade
		        
/////   E X A M W I S E   S U M
				
				for(int i = 0; i < 4; i++){
					pg.drawString(String.valueOf(ExamwiseSum[i]), 515, 335+i*20);  // Sum of all Unit n Term Exams
				}
/////  A  G  G  R  E  G  A  T  E
				
//				int row = View.getTable().getSelectedRow();  
		        for(int i = 0; i <6; i++){
		        	pg.drawString(String.valueOf(SubTotal[i]), 215+i*35, 415);  // Aggregate marks of each subject
		        }        
		        pg.drawString(GetData1(View.getTable(),pageNum,28), 430, 415);    // Marks of EVS Subject
		        

/////  A  V  E  R  A  G  E  MARKS OF EVERY SUBJECT
		        
		float[] AverageMrks = {Sub1(pageNum), Sub2(pageNum), Sub3(pageNum), 
		   		               Sub4(pageNum), Sub5(pageNum), Sub6(pageNum)};
		for(int i = 0; i < 6; i++){
			pg.drawString(String.valueOf((int) Math.ceil(AverageMrks[i]/2)), 220+i*35, 435); // Average marks of each subject
        }
		pg.drawString(GetData1(View.getTable(), pageNum, 28), 430, 435);    // Marks of EVS Subject		        
		pg.drawString(String.valueOf(SumU1Score(pageNum)+SumT1Score(pageNum)+SumU2Score(pageNum)
		 	                     +SumT2andEVSScore(pageNum)+"/1250" ), 493, 415);  // Sum of all U1, T1, U2, T2
		
        int AvgSum = 0;
        for(int i = 0; i < 6; i++){	AvgSum += (int) Math.ceil(AverageMrks[i]/2); }
        int AvgTotal = AvgSum + EVSmarks(pageNum);
        pg.drawString(String.valueOf(AvgTotal)+"/650",500,435);          // Sum of all Averages

		
//////  R E S U L  T    P A R T   
		
		String result = Mod(pageNum);
		pg.drawString(result, 302, 475);
		int[] GraceValues = Mod1(pageNum);
		int GraceTotal = 0;
		for(int j = 0; j < 6; j++){GraceTotal += GraceValues[j];}
//		show(GraceTotal);
		for(int i = 0; i < 6; i++){    
			if(result == "Promoted"){pg.drawString(String.valueOf(GraceValues[i]), 220+i*35, 455);
                                     pg.drawString(String.valueOf(GraceTotal)+"/15", 505, 455); }
			else pg.drawString("", 220+i*35, 455);
		}
		
		String percent = Percent(pageNum);
		if(result == "Promoted" || result == "Fail") pg.drawString(" ", 387, 475);
		else pg.drawString(percent+" %", 387, 475);


			   	
/////   S T U D E N T  D E T A I L S
		        
		        StuDetailsArray.removeAll(StuDetailsArray);
			   	for(int k = 1; k < 4 ; k++){	   		
			   		StuDetailsArray.add((String) GetData1(View.getTable(),pageNum,k));
			   	}
				
				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
				int YEAR = year-1;

				pg.drawString("Mark Sheet showing the number of marks Obtained by  ", 60, 200);
				pg.drawString(StuDetailsArray.get(2), 60, 220);   //  Name of student 
				pg.drawString("with Roll No.:"+ StuDetailsArray.get(0)+" of Division : "
				              +StuDetailsArray.get(1)+", in " + Streamfinder(RollNo)+ " stream", 60, 240);
				pg.drawString("The following table shows each head of passing at FYJC examintion conducted", 60, 260);
				pg.drawString("during the academic year " + YEAR +" - " + year, 60, 280);				
				
/////    F O O T E R - GO V E R N M E N T   C I R C U L A R		
				
				pg.drawString("NOTE  :  This marksheet has been prepared as per the instruction of circular", 60, 520);
				pg.drawString("No 6987,dated 04/11/2009 issued by Secretary, Maharashtra State", 120, 540);
				pg.drawString("Board of Secondary and Higher Secondary Education,Pune 411004", 120, 560);

		   		
		   		
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
		    catch (PrinterException pe) {
		    pe.printStackTrace();
		  }                                     				
	   }	


	   	   //   Swapping Total Scores in decreasing order --- Insertion Sorting 
	   	   
/*	   	   int first = 0, second = 0, max = 0, temp = 0;
	   	   String Fst = null, sec = null, Marks = null,temp1 = null, temp2 = null ;
	   	   
	   	TotalMarksArray.removeAll(TotalMarksArray);   
	   	for(int k = 0; k < Rows ; k++){	   		
	   		TotalMarksArray.add((String) GetData1(View.getTable(),k,28));
	   	}
	   	
	   	   for(int i = 0; i < 10; i++)
	   	   {
	   		  Fst =  GetData(View.getTable(),i,28).toString().trim();
	   		  first = Integer.parseInt(Fst);
	   		    for(int j = i + 1; j < 11; j++)
	   		     {	   			  
		   	       sec = GetData(View.getTable(),j,28).toString().trim();		   		  
		   		   second = Integer.parseInt(sec);
                   String Temp = GetData1(View.getTable(),j,1);
		   		  if(first < second)
		   			    {     				         		   				        
//		   				   SetData(sec,i,28);
//		                   SetData(Fst,j,28);
		   			       temp = first;
		   			       first = second;
		   			       second = temp;		
//		   			       show("First is : " + first); 
		                   SetData(first,i,28);
//		                   show("Second is " + second);
		                   SetData(second,j,28);		                   		                   
		   			     } 
	   		     }
          }		
	   	   for(int i = 0; i < 10; i++){
	   	       temp1 = GetData(View.getTable(),i,28).toString();
	   	       show(temp1);
	   	         {
	   	            for(int j = 0; j < 10; j++){
	   	        	temp2 = TotalMarksArray.get(j);
	   	        	if(temp1.equals(temp2)) SetData(j+1,i,29);
	   	            }
	   	         }
	   	   }
	   	   
	   	   */	   	   	   	   
	 
	private void BtnSetPrinter(){
//	        System.exit(0);
			SetPrinter sp=new SetPrinter();
	        String printername=sp.SelectPrinter();
	        Model.setPrinterName(printername);
	        View.setPrinterLabel(printername);
	    }

	private void BtnCancel(){
	        System.exit(0);
	    }
	    
	private void BtnUpdate(){
		TotalScore();
	}
	
	public void TotalScore(){
	     String marks;
		 int Marks = 0, TotalMarks = 0;
		 for(int i = 0; i < Model.strArray.size()-1; i++){ 	 //   show(strArray.size());
			 for(int j = 4; j < 29; j++){
				marks = GetData1(View.getTable(),i,j);
				if(marks == null || marks.isEmpty()){ marks = "00"; }					 
				if(marks.contentEquals("AB") || marks.contentEquals("AB ")){ marks = "00"; }					 
				Marks = Integer.parseInt(marks);             //  show(Marks);
				TotalMarks = TotalMarks + Marks;             //  show(TotalMarks);			 
			  }
			    String TM = String.format("%d", TotalMarks);
			    SetData(TM,i,30);
			    TotalMarks = 0;			 
		 }							
	}

	public int SumU1Score(int pageNum){
		int row = View.getTable().getSelectedRow();
		row = pageNum;
	     String marks;
		 int Marks = 0, SumOfU1Marks = 0;      
		 for(int j = 0; j < 6; j++){
				marks = GetData1(View.getTable(),row, 4+4*j);   // show("marks = "+marks);
				if(marks == null || marks.isEmpty()){ marks = "00"; }					 
				if(marks.contentEquals("AB") || marks.contentEquals("AB ")){ marks = "00"; }					 
				Marks = Integer.parseInt(marks);            //   show("Marks = "+Marks);
				SumOfU1Marks = SumOfU1Marks + Marks;             //  show(TotalMarks);			 
			  }
		return SumOfU1Marks;							
	}

	public int SumT1Score(int pageNum){
		int row = View.getTable().getSelectedRow();
		row = pageNum;
	     String marks;
		 int Marks = 0, SumOfT1Marks = 0;      
		 for(int j = 0; j < 6; j++){
				marks = GetData1(View.getTable(),row, 5+4*j);   // show("marks = "+marks);
				if(marks == null || marks.isEmpty()){ marks = "00"; }					 
				if(marks.contentEquals("AB") || marks.contentEquals("AB ")){ marks = "00"; }					 
				Marks = Integer.parseInt(marks);            //   show("Marks = "+Marks);
				SumOfT1Marks = SumOfT1Marks + Marks;             //  show(TotalMarks);			 
			  }
		return SumOfT1Marks;							
	}

	public int SumU2Score(int pageNum){
		int row = View.getTable().getSelectedRow();
		row = pageNum;
	     String marks;
		 int Marks = 0, SumOfU2Marks = 0;      
		 for(int j = 0; j < 6; j++){
				marks = GetData1(View.getTable(),row, 6+4*j);   // show("marks = "+marks);
				if(marks == null || marks.isEmpty()){ marks = "00"; }					 
				if(marks.contentEquals("AB") || marks.contentEquals("AB ")){ marks = "00"; }					 
				Marks = Integer.parseInt(marks);            //   show("Marks = "+Marks);
				SumOfU2Marks = SumOfU2Marks + Marks;             //  show(TotalMarks);			 
			  }
		return SumOfU2Marks;							
	}

	public int SumT2andEVSScore(int pageNum){
		int row = View.getTable().getSelectedRow();
		row = pageNum;
	     String marks, EVS;
		 int Marks = 0, SumOfT2Marks = 0, EVSandT2Sum = 0;      
		 for(int j = 0; j < 6; j++){
				marks = GetData1(View.getTable(),row, 7+4*j);   // show("marks = "+marks);
				if(marks == null || marks.isEmpty()){ marks = "00"; }					 
				if(marks.contentEquals("AB") || marks.contentEquals("AB ")){ marks = "00"; }					 
				Marks = Integer.parseInt(marks);               //   show("Marks = "+Marks);
				SumOfT2Marks = SumOfT2Marks + Marks;           //  show(TotalMarks);			 
			  }
		        EVS = GetData1(View.getTable(),row, 28);
		        if(EVS == null || EVS.isEmpty()){ EVS = "00"; }					 
				if(EVS.contentEquals("AB") || EVS.contentEquals("AB ")){ EVS = "00"; }
		        int evs = Integer.parseInt(EVS); 
			    EVSandT2Sum = SumOfT2Marks + evs;
		return EVSandT2Sum;							
	}

	public void SubOfStudent(int roll)
	{ 
		subArray.removeAll(subArray);		
		String SubFromPlate = null;
		String plate[];
		String subfromArray = null ;
	    plate=Model.strArray.get(roll).split("#");
		   for(int j = 0; j < subject.length ; j++)
		   {		                                           //  subject.length = 19 including EVS and PTE 
		     subfromArray= subject[j];
		       for(int k = 2; k < plate.length; k++)
		         {
        		   SubFromPlate = plate[k].substring(5, 8);         // SubFromPlate means Only Three Letter Subject CODE			               
			       if(subfromArray.contains(SubFromPlate))
			         { 		           
			           subArray.add(subfromArray); 
			           break;		        
			         }		        
		         }  
		    }		   		   
	} 		        		
	
	
	public void  SaveToFile(String fylnem){
		
		 FileWriter fw=null;		
		 try {fw = new FileWriter(fylnem); }    catch (IOException e1){e1.printStackTrace();}
		 String newLine = System.getProperty("line.separator");
		 int rows = View.getTable().getRowCount();
		 String Roll=null, Name = null, Div = null, Marks = null, SerialNo = null, Names = null;
		 String EVS =  null, PTE = null, Total = null, Result = null ;
		 int roll =  0, serialNo = 0, EVSMrks;
		 	 
		 try { fw.write(Model.strArray.get(0) + newLine); }  catch (IOException e1) {e1.printStackTrace();}
		 for(int i = 0; i < rows-1; i++){	
			 SerialNo = GetData1(View.getTable(),i,0).trim();
			 serialNo = Integer.parseInt(SerialNo); 
		     SubOfStudent(serialNo) ;			 			 
			 Roll = GetData1(View.getTable(),i,1).trim();
		     roll = Integer.parseInt(Roll); 
			 Div =  GetData(View.getTable(), i,2).toString().trim();
			 Name = GetData(View.getTable(), i,3).toString().trim();
			 Name = Name.replaceAll("\\s{2}", "").toUpperCase(); 
			 Names = String.format("%-60s",Name.trim());
			 try { fw.write(Roll+"#"+Names); }  catch (IOException e1) {e1.printStackTrace();}
			 for(int k = 4; k < 30; k++){ 
			     Marks = GetData(View.getTable(), i,k).toString().trim();
			     if(Marks == null || Marks.isEmpty()){ continue; }
			     if(Marks.contentEquals("AB") || Marks.contentEquals("AB ")){ Marks = "AB"; }
				 String Exam = View.getTable().getColumnName(k);
				 if(k == 28){Exam = "T2";}	
				 if(k == 29){Exam = "T2"; k = 32;}         //   PTE
			 	 try { fw.write("#" + Div + "=" + Exam + "=" + subArray.get(k/4 -1) + ":" + Marks); }  catch (IOException e1) {e1.printStackTrace();}
		     } 

		       
		     try { fw.write(newLine); }  catch (IOException e1) {e1.printStackTrace();}
			 
		 }
			 		 
		 try {fw.close();} catch (IOException e1) {e1.printStackTrace();}  
	}
	
	
   public String Percent(int row){
		int sub1 = 0, sub2 = 0, sub3 = 0, sub4 = 0, sub5 = 0, sub6 = 0, evs= 0;
		String percent = null;
//		row =  View.getTable().getSelectedRow();
		sub1 = Sub1(row); sub2 = Sub2(row); sub3 = Sub3(row); sub4 = Sub4(row); 
		sub5 = Sub5(row); sub6 = Sub6(row);  evs =  EVSmarks(row);

		int[] AverageMarks = { (int) Math.ceil(sub1/2), (int) Math.ceil(sub2/2), (int) Math.ceil(sub3/2),
				               (int) Math.ceil(sub4/2), (int) Math.ceil(sub5/2), (int) Math.ceil(sub6/2), evs };
		
		int SumOfAverages = AverageMarks[0] + AverageMarks[1] + AverageMarks[2] 
		          + AverageMarks[3] + AverageMarks[4] + AverageMarks[5];
        double Total = SumOfAverages + evs;  //s  Show(Total);
	    double Class = (Total*100)/650;   

        return percent.format("%.2f", Class);

	   }
	   
	
	public String Mod(int row){
		int sub1 = 0, sub2 = 0, sub3 = 0, sub4 = 0, sub5 = 0, sub6 = 0, evs= 0, pte = 0;
		String PTE = null;
		String Result1 = "Fail", Result2 = "Promoted", Result3 = "Pass Class"; 
		String Result4 = "Second Class", Result5 = "First Class", Result6 = "Distinction";
//		row =  View.getTable().getSelectedRow();
//		int RowCount = 15;       //    View.getTable().getRowCount();
		int GraceCount = 0, GraceTotal = 0, GraceValue = 0; 
		sub1 = Sub1(row); sub2 = Sub2(row); sub3 = Sub3(row); sub4 = Sub4(row); 
		sub5 = Sub5(row); sub6 = Sub6(row);  evs =  EVSmarks(row);
		GraceCount = 0;  GraceTotal = 0; GraceValue = 0;  
		
		PTE = GetData1(View.getTable(), row, 29);
		if(PTE == null || PTE.isEmpty()){ PTE = "00"; }					 
		if(PTE.contentEquals("AB") || PTE.contentEquals("AB ")){ PTE = "00"; }	
		pte = Integer.parseInt(PTE);

		int[] AverageMarks = { (int) Math.ceil(sub1/2), (int) Math.ceil(sub2/2), (int) Math.ceil(sub3/2),
				               (int) Math.ceil(sub4/2), (int) Math.ceil(sub5/2), (int) Math.ceil(sub6/2), evs };
		
		int SumOfAverages = AverageMarks[0] + AverageMarks[1] + AverageMarks[2] 
		          + AverageMarks[3] + AverageMarks[4] + AverageMarks[5];
        int Total = SumOfAverages + evs;  //   show("AVerages : "+SumOfAverages); show("EVS : "+evs);  show("Total : "+Total);

//        String RollNo = View.getTable().getModel().getValueAt(row, 1).toString();
		int[] GraceMrks = {0,0,0,0,0,0};
		for(int i = 0; i < 6; i++){
			if(AverageMarks[i] < 35 )
				{ GraceMrks[i] = 35 - AverageMarks[i];
				  GraceTotal += GraceMrks[i];
				  GraceCount++;
				}
			
			    else GraceMrks[i] = 0; 	
			
			 if(GraceMrks[i] > 10) {GraceValue = GraceMrks[i];}
		}
		 int Class = (Total*100)/650;  //   show(Class);
		
		 if(GraceValue > 10 || pte < 1 || evs < 18){ return Result1;}
		 else if(GraceCount > 3){ return Result1;}
		 else if(GraceCount < 4 && GraceTotal > 16){return Result1;}
		 else if(GraceCount < 4 && GraceTotal > 0 && GraceTotal < 16){return Result2;}
		 else if(Class >= 50 && Class < 60) { return Result4; }
		 else if(Class >= 60 && Class < 70) { return Result5; }
		 else if(Class >= 75) { return Result6; }
		 else return Result3;
		
	}            
		
	public int[] Mod1(int row){
		int sub1 = 0, sub2 = 0, sub3 = 0, sub4 = 0, sub5 = 0, sub6 = 0, evs= 0, pte = 0;
		String PTE = null;
		String Result1 = "Fail", Result2 = "Promoted", Result4 = "Pass";
//		row =  View.getTable().getSelectedRow();
//		int RowCount = 15;       //    View.getTable().getRowCount();
		int GraceCount = 0, GraceTotal = 0, GraceValue = 0; 
		sub1 = Sub1(row); sub2 = Sub2(row); sub3 = Sub3(row); sub4 = Sub4(row); 
		sub5 = Sub5(row); sub6 = Sub6(row);  evs =  EVSmarks(row);
		GraceCount = 0;  GraceTotal = 0; GraceValue = 0;  
		
		PTE = GetData1(View.getTable(), row, 29);
		if(PTE == null || PTE.isEmpty()){ PTE = "00"; }					 
		if(PTE.contentEquals("AB") || PTE.contentEquals("AB ")){ PTE = "00"; }	
		pte = Integer.parseInt(PTE);
		
		int[] AverageMarks = { (int) Math.ceil(sub1/2), (int) Math.ceil(sub2/2), (int) Math.ceil(sub3/2),
				               (int) Math.ceil(sub4/2), (int) Math.ceil(sub5/2), (int) Math.ceil(sub6/2), evs };
		
		int SumOfAverages = AverageMarks[0] + AverageMarks[1] + AverageMarks[2] 
		          + AverageMarks[3] + AverageMarks[4] + AverageMarks[5];
        int Total = SumOfAverages + evs;

        String RollNo = View.getTable().getModel().getValueAt(row, 1).toString();
		int[] GraceMrks = {0,0,0,0,0,0};
		for(int i = 0; i < 6; i++){
			if(AverageMarks[i] < 35 )
				{ GraceMrks[i] = 35 - AverageMarks[i];
				  GraceTotal += GraceMrks[i];
				  GraceCount++;
				}
			
			    else GraceMrks[i] = 0; 				
		}
		
		return GraceMrks; 
	}            
	
	
	public int Sub1(int pageNum){
		int row = View.getTable().getSelectedRow();
		row = pageNum;
		int TotalOfEng = 0, engtotal = 0;	
			for(int j = 4; j < 8; j++ ){
			String EngTotal = GetData1(View.getTable(), row, j);
			
			if(EngTotal == null || EngTotal.isEmpty()){ EngTotal = "00"; }					 
			if(EngTotal.contentEquals("AB") || EngTotal.contentEquals("AB ")){ EngTotal = "00"; }					 
			
		    engtotal = 	Integer.parseInt(EngTotal);
		    TotalOfEng = TotalOfEng + engtotal;
		    }
		return TotalOfEng;		
	}
	
	public int Sub2(int pageNum){
		int row = View.getTable().getSelectedRow();
		row = pageNum;
		int TotalOfSLITEL1CS1 = 0, slitel1cs1 = 0;	
			for(int j = 8; j < 12; j++ ){
			String SlItEl1Cs1Total = GetData1(View.getTable(), row, j);
			
			if(SlItEl1Cs1Total == null || SlItEl1Cs1Total.isEmpty()){ SlItEl1Cs1Total = "00"; }					 
			if(SlItEl1Cs1Total.contentEquals("AB") || SlItEl1Cs1Total.contentEquals("AB ")){ SlItEl1Cs1Total = "00"; }					 
			
			slitel1cs1 = 	Integer.parseInt(SlItEl1Cs1Total);
		    TotalOfSLITEL1CS1 = TotalOfSLITEL1CS1 + slitel1cs1;
		    }
		return TotalOfSLITEL1CS1;		
	}
	
	public int Sub3(int pageNum){
		int row = View.getTable().getSelectedRow();
		row = pageNum;
		int TotalOfSLITEL2CS2 = 0, slitcl2cs2 = 0;	
			for(int j = 12; j < 16; j++ ){
			String SlItEl2Cs2Total = GetData1(View.getTable(), row, j);
			
			if(SlItEl2Cs2Total == null || SlItEl2Cs2Total.isEmpty()){ SlItEl2Cs2Total = "00"; }					 
			if(SlItEl2Cs2Total.contentEquals("AB") || SlItEl2Cs2Total.contentEquals("AB ")){ SlItEl2Cs2Total = "00"; }					 
						
			slitcl2cs2 = 	Integer.parseInt(SlItEl2Cs2Total);
		    TotalOfSLITEL2CS2 = TotalOfSLITEL2CS2 + slitcl2cs2;
		    }
		return TotalOfSLITEL2CS2;		
	}

	public int Sub4(int pageNum){
		int row = View.getTable().getSelectedRow();
		row = pageNum;
		int TotalOfSub4 = 0, sub4 = 0;	
			for(int j = 16; j < 20; j++ ){
			String Sub4Total = GetData1(View.getTable(), row, j);
			
			if(Sub4Total == null || Sub4Total.isEmpty()){ Sub4Total = "00"; }					 
			if(Sub4Total.contentEquals("AB") || Sub4Total.contentEquals("AB ")){ Sub4Total = "00"; }					 
			
			sub4 = 	Integer.parseInt(Sub4Total);
			TotalOfSub4 = TotalOfSub4 + sub4;
		    }
		return TotalOfSub4;		
	}

	public int Sub5(int pageNum){
		int row = View.getTable().getSelectedRow();
		row = pageNum;
		int TotalOfSub5 = 0, sub5 = 0;	
			for(int j = 20; j < 24; j++ ){
			String Sub5Total = GetData1(View.getTable(), row, j);
			
			if(Sub5Total == null || Sub5Total.isEmpty()){ Sub5Total = "00"; }					 
			if(Sub5Total.contentEquals("AB") || Sub5Total.contentEquals("AB ")){ Sub5Total = "00"; }					 

			sub5 = 	Integer.parseInt(Sub5Total);
			TotalOfSub5 = TotalOfSub5 + sub5;
		    }
		return TotalOfSub5;		
	}
	
	public int Sub6(int pageNum){
		int row = View.getTable().getSelectedRow();
		row = pageNum;
		int TotalOfSub6 = 0, sub6 = 0;	
			for(int j = 24; j < 28; j++ ){
			String Sub6Total = GetData1(View.getTable(), row, j);
			
			if(Sub6Total == null || Sub6Total.isEmpty()){ Sub6Total = "00"; }					 
			if(Sub6Total.contentEquals("AB") || Sub6Total.contentEquals("AB ")){ Sub6Total = "00"; }					 

			sub6 = 	Integer.parseInt(Sub6Total);
			TotalOfSub6 = TotalOfSub6 + sub6;
		    }
		return TotalOfSub6;		
	}

	public int EVSmarks(int pageNum){
		int row = View.getTable().getSelectedRow();
		row = pageNum;
		String evsScore = GetData1(View.getTable(), row, 28);
		if(evsScore == null || evsScore.isEmpty()){ evsScore = "00"; }					 
		if(evsScore.contentEquals("AB") || evsScore.contentEquals("AB ")){ evsScore = "00"; }					 
		int EVSMarks = Integer.parseInt(evsScore);
		return EVSMarks;		
	}

	
	
	public void ENGMarks(){
		  
		 String[] subwithmarks = null;
    	 String plate[];
		 String ENGmarks = null, ENGTotal = null, line;	
		 int EngT2Marks = 0, ENGTotalT2 = 0;
		 for(int i=1; i < Model.strArray.size() ; i++)                      //  Model.strArray.size()
	     	{
	     	  plate=Model.strArray.get(i).split("#");

	     	   for (int j = 2; j < plate.length; j++){	
	     		   line = plate[j];
		     	   ENGmarks = plate[j].substring(2, 8);     //   show( submarksU1);
		     	   subwithmarks = line.split(":");
		     		 if(ENGmarks.contains("U1=ENG")){
		     			ENGmarks = subwithmarks[1];	
		     			SetData(ENGmarks, i-1,4);
		     			}	                         
		     		 
		     		if(ENGmarks.contains("T1=ENG")){
		     		   ENGmarks = subwithmarks[1];
		     		   SetData(subwithmarks[1], i-1,5);
		     		   }	
		     		
		     		if(ENGmarks.contains("U2=ENG")){
		     			ENGmarks = subwithmarks[1];
		     			SetData(subwithmarks[1], i-1,6);
		     			}
		     		
		     		if(ENGmarks.contains("T2=ENG")){
		     			ENGmarks = subwithmarks[1];
		     			if(ENGmarks == null || ENGmarks.isEmpty()){ ENGmarks = "00"; }					 
						if(ENGmarks.contentEquals("AB") || ENGmarks.contentEquals("AB ")){ ENGmarks = "00"; }
		     			EngT2Marks = Integer.parseInt(ENGmarks); //  show(EngT2Marks);	
		     			ENGTotalT2 =  ENGTotalT2 + EngT2Marks;   
		     			ENGTotal = Integer.toString(ENGTotalT2);	
		     			SetData(ENGTotal, i-1,7); 
		     			}			     		
    	         }   		
	     	         ENGTotalT2 = 0;
        }		
     	 JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
    	 th.repaint();                                          
     	 View.Eng.text="ENG";                       
	}     
		
	public void SecLangMarks(){
		  
		 String[] subwithmarks = null;
	   	 String plate[];
		 String SLITmarks = null, MARTotal = null, ITETotal = null, HINTotal = null, TAMTotal = null, line;		   	
		 int MART2marks = 0, MARTotalT2 = 0, ITET2marks = 0, ITETotalT2 = 0, HINT2marks = 0, HINTotalT2 = 0,
		     TAMT2marks = 0, TAMTotalT2 = 0;
		 for(int i=1; i < Model.strArray.size() ; i++)                      //  strArray.size()
	     	{
	     	  plate=Model.strArray.get(i).split("#");
//	     	  show(plate);
	     	   for (int j = 2; j < plate.length; j++){	
	     		    line = plate[j];
	     		    subwithmarks = line.split(":");
			    	SLITmarks = plate[j].substring(2, 8);     //   show( submarksU1); 
		     		 if(SLITmarks.contains("U1=MAR")){
		     			SLITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 8);                 		     			
		     			}	  		     		 		    
		     		 if(SLITmarks.contains("U1=ITE")){
			     			SLITmarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 8);                 		     			
			     			}	
		     		if(SLITmarks.contains("U1=HIN")){
		     			SLITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 8);                 		     			
		     			}	
		     		if(SLITmarks.contains("U1=TAM")){
		     			SLITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 8);                 		     			
		     			}
		     		
		     		if(SLITmarks.contains("T1=MAR")){
		     			SLITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 9);                 		     			
		     			}	  		     		 		    
		     		 if(SLITmarks.contains("T1=ITE")){
			     			SLITmarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 9);                 		     			
			     			}	
		     		if(SLITmarks.contains("T1=HIN")){
		     			SLITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 9);                 		     			
		     			}	
		     		if(SLITmarks.contains("T1=TAM")){
		     			SLITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 9);                 		     			
		     			}	
		     		
		     		if(SLITmarks.contains("U2=MAR")){
		     			SLITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 10);                 		     			
		     			}	  		     		 		    
		     		 if(SLITmarks.contains("U2=ITE")){
			     			SLITmarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 10);                 		     			
			     			}	
		     		if(SLITmarks.contains("U2=HIN")){
		     			SLITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 10);                 		     			
		     			}	
		     		if(SLITmarks.contains("U2=TAM")){
		     			SLITmarks = subwithmarks[1];	
		     			SetData(subwithmarks[1], i-1, 10);                 		     			
		     			}	
		     		if(SLITmarks.contains("T2=MAR")){
		     			SLITmarks = subwithmarks[1];	
		     			if(SLITmarks == null || SLITmarks.isEmpty()){ SLITmarks = "00"; }					 
						if(SLITmarks.contentEquals("AB") || SLITmarks.contentEquals("AB ")){ SLITmarks = "00"; }
						   MART2marks = Integer.parseInt(SLITmarks);  // show(SLITT2marks);	
						   MARTotalT2 =  MARTotalT2 + MART2marks; 
						   MARTotal = Integer.toString(MARTotalT2);
		     			   SetData(MARTotal, i-1, 11);                 		     			
		     			 }		     		
		     		 if(SLITmarks.contains("T2=ITE")){
			     		SLITmarks = subwithmarks[1];	
			     		if(SLITmarks == null || SLITmarks.isEmpty()){ SLITmarks = "00"; }					 
						if(SLITmarks.contentEquals("AB") || SLITmarks.contentEquals("AB ")){ SLITmarks = "00"; }
						ITET2marks = Integer.parseInt(SLITmarks);    //      show(ITET2marks);
			     		ITETotalT2 = ITETotalT2 + ITET2marks; 
			     		ITETotal = Integer.toString(ITETotalT2);			     			     		
			     		SetData(ITETotal, i-1, 11);                 		     			
			     		}	
		     		if(SLITmarks.contains("T2=HIN")){
		     			SLITmarks = subwithmarks[1];	
		     			if(SLITmarks == null || SLITmarks.isEmpty()){ SLITmarks = "00"; }					 
						if(SLITmarks.contentEquals("AB") || SLITmarks.contentEquals("AB ")){ SLITmarks = "00"; }
						HINT2marks = Integer.parseInt(SLITmarks);    //      show(HINT2marks);
						HINTotalT2 = HINTotalT2 + HINT2marks; 
						HINTotal = Integer.toString(HINTotalT2);		     			
		     			SetData(HINTotal, i-1, 11);                 		     			
		     			}	
		     		if(SLITmarks.contains("T2=TAM")){
		     			SLITmarks = subwithmarks[1];	
		     			if(SLITmarks == null || SLITmarks.isEmpty()){ SLITmarks = "00"; }					 
						if(SLITmarks.contentEquals("AB") || SLITmarks.contentEquals("AB ")){ SLITmarks = "00"; }
						TAMT2marks = Integer.parseInt(SLITmarks);   //        show(TAMT2marks);
						TAMTotalT2 = TAMTotalT2 + TAMT2marks; 
						TAMTotal = Integer.toString(TAMTotalT2);			     			
		     			SetData(TAMTotal, i-1, 11);                 		     			
		     			}	
	     	   }
	     	        MARTotalT2 = 0; ITETotalT2 = 0; HINTotalT2 = 0; TAMTotalT2 = 0;
	     	}
   	 JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
	 th.repaint();                                          
 	 View.SL.text = "SL / ITE / EL1 / CS1";
   	    	 
	}
	
	public void VocationalMarks(){
		  
			 String[] subwithmarks = null;
		   	 String plate[];
			 String VocationalMarks = null, EL1Total = null, CS1Total = null, EL2Total = null, CS2Total = null, line;	
			 int EL1T2marks = 0, EL1TotalT2 = 0, CS1T2marks = 0, CS1TotalT2 = 0,
			     EL2T2marks = 0, EL2TotalT2 = 0, CS2T2marks = 0, CS2TotalT2 = 0;
			 for(int i=1; i < Model.strArray.size() ; i++)                      //  strArray.size()
		     	{
				   plate=Model.strArray.get(i).split("#");
				   for (int j = 2; j < plate.length; j++){	
					 
					  line = plate[j];
				      VocationalMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
				      subwithmarks = line.split(":");
					   if(VocationalMarks.contains("U1=EL1")){
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 8);                 		     			
			     			}	
			     		if(VocationalMarks.contains("T1=EL1")){
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 9);                 		     			
			     			}	 
					   if(VocationalMarks.contains("U2=EL1")){
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 10);                 		     			
			     			}	
			     		if(VocationalMarks.contains("T2=EL1")){
			     			VocationalMarks = subwithmarks[1];	
			     			if(VocationalMarks == null || VocationalMarks.isEmpty()){ VocationalMarks = "00"; }					 
							if(VocationalMarks.contentEquals("AB") || VocationalMarks.contentEquals("AB ")){ VocationalMarks = "00"; }
							EL1T2marks = Integer.parseInt(VocationalMarks);  //         show(EL1T2marks);
							EL1TotalT2 = EL1TotalT2 + EL1T2marks; 
							EL1Total = Integer.toString(EL1TotalT2);	
							SetData(EL1Total, i-1, 11);                 		     			
			     			}
			     		if(VocationalMarks.contains("U1=CS1")){
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 8);                 		     			
			     			}	
			     		if(VocationalMarks.contains("T1=CS1")){
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 9);                 		     			
			     			}	 
					   if(VocationalMarks.contains("U2=ECS1")){
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 10);                 		     			
			     			}	
			     		if(VocationalMarks.contains("T2=CS1")){
			     			VocationalMarks = subwithmarks[1];	
			     			if(VocationalMarks == null || VocationalMarks.isEmpty()){ VocationalMarks = "00"; }					 
							if(VocationalMarks.contentEquals("AB") || VocationalMarks.contentEquals("AB ")){ VocationalMarks = "00"; }
							CS1T2marks = Integer.parseInt(VocationalMarks);   //       show(CS1T2marks);
							CS1TotalT2 = CS1TotalT2 + CS1T2marks; 
							CS1Total = Integer.toString(CS1TotalT2);				     						     			
			     			SetData(CS1Total, i-1, 11);                 		     			
			     			}	 
			     		
			     		if(VocationalMarks.contains("U1=EL2")){
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 12);                 		     			
			     			}	
			     		if(VocationalMarks.contains("T1=EL2")){
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 13);                 		     			
			     			}	 
					   if(VocationalMarks.contains("U2=EL2")){
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 14);                 		     			
			     			}	
			     		if(VocationalMarks.contains("T2=EL2")){
			     			VocationalMarks = subwithmarks[1];	
			     			if(VocationalMarks == null || VocationalMarks.isEmpty()){ VocationalMarks = "00"; }					 
							if(VocationalMarks.contentEquals("AB") || VocationalMarks.contentEquals("AB ")){ VocationalMarks = "00"; }
							EL2T2marks = Integer.parseInt(VocationalMarks);   //       show(EL2T2marks);
							EL2TotalT2 = EL2TotalT2 + EL2T2marks; 
							EL2Total = Integer.toString(EL2TotalT2);				     						     						     			
			     			SetData(EL2Total, i-1, 15);                 		     			
			     			}
			     		if(VocationalMarks.contains("U1=CS2")){
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 12);                 		     			
			     			}	
			     		if(VocationalMarks.contains("T1=CS2")){
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 13);                 		     			
			     			}	 
					   if(VocationalMarks.contains("U2=ECS2")){
			     			VocationalMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 14);                 		     			
			     			}	
			     		if(VocationalMarks.contains("T2=CS2")){
			     			VocationalMarks = subwithmarks[1];	
			     			if(VocationalMarks == null || VocationalMarks.isEmpty()){ VocationalMarks = "00"; }					 
							if(VocationalMarks.contentEquals("AB") || VocationalMarks.contentEquals("AB ")){ VocationalMarks = "00"; }
							CS2T2marks = Integer.parseInt(VocationalMarks);   //  show(CS2T2marks);
							CS2TotalT2 = CS2TotalT2 + CS2T2marks; 
							CS2Total = Integer.toString(CS2TotalT2);					     						     			
			     			SetData(CS2Total, i-1, 15);                 		     			
			     			}			     					     		
				   }
				   EL1TotalT2 = 0;	CS1TotalT2 = 0;	EL2TotalT2 = 0;	CS2TotalT2 = 0; 				 
		     }
			 
		   	 JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
			 th.repaint();                                          
	}
	
	public void BIOMarks(){
		 String[] subwithmarks = null;
	   	 String plate[];
		 String BIOMarks = null, BIOTotal = null, line;	
		 int BIOT2marks = 0, BIOTotalT2 = 0;
		 for(int i=1; i < Model.strArray.size() ; i++)                      //  strArray.size()
	     	{
			 plate=Model.strArray.get(i).split("#");
	     	   for (int j = 2; j < plate.length; j++){		     		   
					  line = plate[j];
					  subwithmarks = line.split(":");
				      BIOMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
					   if(BIOMarks.contains("U1=BIO")){
			     			BIOMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 12);                 		     			
			     			}	
			     		if(BIOMarks.contains("T1=BIO")){
			     			BIOMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 13);                 		     			
			     			}	 
					   if(BIOMarks.contains("U2=BIO")){
			     			BIOMarks = subwithmarks[1];	
			     			SetData(subwithmarks[1], i-1, 14);                 		     			
			     			}	
			     		if(BIOMarks.contains("T2=BIO")){
			     			BIOMarks = subwithmarks[1];	
			     			if(BIOMarks == null || BIOMarks.isEmpty()){ BIOMarks = "00"; }					 
							if(BIOMarks.contentEquals("AB") || BIOMarks.contentEquals("AB ")){ BIOMarks = "00"; }
							BIOT2marks = Integer.parseInt(BIOMarks);        //    show(BIOT2marks);
							BIOTotalT2 = BIOTotalT2 + BIOT2marks; 
							BIOTotal = Integer.toString(BIOTotalT2);				     						     			
			     		    SetData(BIOTotal, i-1, 15);
	     	           }
	     	}
	     	  BIOTotalT2 = 0;
	    }		
	   	 JTableHeader th = View.getTable().getTableHeader();     
		 th.repaint();                                          
	}
	
	public void ECOMarks(){
		 String[] subwithmarks = null;
	   	 String plate[];
		 String ECOMarks = null, ECOTotal = null,line;	
		 int ECOT2marks = 0, ECOTotalT2 = 0;
		 for(int i=1; i < Model.strArray.size() ; i++)                      //  Model.strArray.size()
	     	{
			 plate=Model.strArray.get(i).split("#");
	     	   for (int j = 2; j < plate.length; j++){		     		  
				  line = plate[j];
				  subwithmarks = line.split(":");
				  ECOMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
					if(ECOMarks.contains("U1=ECO")){
			     	   ECOMarks = subwithmarks[1];	
			     	   SetData(subwithmarks[1], i-1, 12);                 		     			
			     	 }	
			     	if(ECOMarks.contains("T1=ECO")){
			     	   ECOMarks = subwithmarks[1];	
			     	   SetData(subwithmarks[1], i-1, 13);                 		     			
			     	 }	 
					if(ECOMarks.contains("U2=ECO")){
			     	   ECOMarks = subwithmarks[1];	
			     	   SetData(subwithmarks[1], i-1, 14);                 		     			
			     	 }	
			     	if(ECOMarks.contains("T2=ECO")){
			     	   ECOMarks = subwithmarks[1];	
			     	  if(ECOMarks == null || ECOMarks.isEmpty()){ ECOMarks = "00"; }					 
						if(ECOMarks.contentEquals("AB") || ECOMarks.contentEquals("AB ")){ ECOMarks = "00"; }
						ECOT2marks = Integer.parseInt(ECOMarks);       //   show(ECOT2marks);
						ECOTotalT2 = ECOTotalT2 + ECOT2marks; 
						ECOTotal = Integer.toString(ECOTotalT2);				     	   			     	   
			           SetData(ECOTotal, i-1, 15);
	         	     }
	     	    }
	     	  ECOTotalT2 = 0;
	     	}
	     	   JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
				 th.repaint();                                 
				 View.Sub1.text = "ECO / BIO / EL2 / CS2";
	}
	

	public void BKEMarks(){
		 String[] subwithmarks = null;
	   	 String plate[];
		 String BKEMarks = null, BKETotal = null, line;	
		 int BKET2marks = 0, BKETotalT2 = 0;
		 for(int i=1; i < Model.strArray.size() ; i++)                      //  Model.strArray.size()
	     	{
			 plate=Model.strArray.get(i).split("#");
//	     	  show(plate);
	     	   for (int j = 2; j < plate.length; j++){		     		  
				  line = plate[j];
				  subwithmarks = line.split(":");
				  BKEMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
					if(BKEMarks.contains("U1=BKE")){			     			
			     	   BKEMarks = subwithmarks[1];	
			     	   SetData(subwithmarks[1], i-1, 16);                 		     			
			     	 }	
			     	if(BKEMarks.contains("T1=BKE")){
			     	   BKEMarks = subwithmarks[1];	
			     	   SetData(subwithmarks[1], i-1, 17);                 		     			
			     	 }	 
					if(BKEMarks.contains("U2=BKE")){
  	     			   BKEMarks = subwithmarks[1];	
      	     		   SetData(subwithmarks[1], i-1, 18);                 		     			
			         }	
			     	if(BKEMarks.contains("T2=BKE")){
			     	   BKEMarks = subwithmarks[1];	
			     	if(BKEMarks == null || BKEMarks.isEmpty()){ BKEMarks = "00"; }					 
					if(BKEMarks.contentEquals("AB") || BKEMarks.contentEquals("AB ")){ BKEMarks = "00"; }
					   BKET2marks = Integer.parseInt(BKEMarks);         //   show(BKET2marks);
					   BKETotalT2 = BKETotalT2 + BKET2marks; 
					   BKETotal = Integer.toString(BKETotalT2);				     	   			     	   
			     	   SetData(BKETotal, i-1, 19);
	         	     }
	     	     }
	     	  BKETotalT2 = 0;
	       	}
	   	 JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
		 th.repaint();                                          
//		 View.Sub2.text = "BKE / PHY";
 	}
		 
    public void PHYMarks(){
				 String[] subwithmarks = null;
			   	 String plate[];
				 String PHYMarks = null,PHYTotal = null,line;
				 int PHYT2marks = 0, PHYTotalT2 = 0;
				 for(int i=1; i < Model.strArray.size() ; i++)                      //  Model.strArray.size()
			     	{
					 plate=Model.strArray.get(i).split("#");
//			     	  show(plate);
			     	   for (int j = 2; j < plate.length; j++){				     		   
						 line = plate[j];
						 subwithmarks = line.split(":");
						 PHYMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
						   if(PHYMarks.contains("U1=PHY")){					     			
					     	  PHYMarks = subwithmarks[1];	
					     	  SetData(subwithmarks[1], i-1, 16);                 		     			
					     	}	
					       if(PHYMarks.contains("T1=PHY")){
					          PHYMarks = subwithmarks[1];	
					     	  SetData(subwithmarks[1], i-1, 17);                 		     			
					     	}	 
						   if(PHYMarks.contains("U2=PHY")){
					          PHYMarks = subwithmarks[1];	
					     	  SetData(subwithmarks[1], i-1, 18);                 		     			
					     	}	
					       if(PHYMarks.contains("T2=PHY")){
					     	  PHYMarks = subwithmarks[1];	
					       if(PHYMarks == null || PHYMarks.isEmpty()){ PHYMarks = "00"; }					 
						   if(PHYMarks.contentEquals("AB") || PHYMarks.contentEquals("AB ")){ PHYMarks = "00"; }
							  PHYT2marks = Integer.parseInt(PHYMarks);     //       show(PHYT2marks);
							  PHYTotalT2 = PHYTotalT2 + PHYT2marks; 
							  PHYTotal = Integer.toString(PHYTotalT2);							     	  					     	  
					     	  SetData(PHYTotal, i-1, 19);
			         	    }
		     	      }
			     	  PHYTotalT2 = 0;
   	    }
				 
		 JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
		 th.repaint();                                          
		 View.Sub2.text = "BKE / PHY";
	}

	public void OCMMarks(){
		 String[] subwithmarks = null;
	   	 String plate[];
		 String OCMMarks = null,OCMTotal = null, line;	
		 int OCMT2marks = 0, OCMTotalT2 = 0;
		 for(int i=1; i < Model.strArray.size() ; i++)                      //  Model.strArray.size()
	     	{
			 plate=Model.strArray.get(i).split("#");
//	     	  show(plate);
	     	   for (int j = 2; j < plate.length; j++){		     		   
                 line = plate[j];
                 subwithmarks = line.split(":");
				 OCMMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
				 if(OCMMarks.contains("U1=OCM")){			     		
			     	OCMMarks = subwithmarks[1];	
			     	SetData(subwithmarks[1], i-1, 20);                 		     			
			      }	
			     if(OCMMarks.contains("T1=OCM")){
			     	OCMMarks = subwithmarks[1];	
			     	SetData(subwithmarks[1], i-1, 21);                 		     			
			      }	 
				 if(OCMMarks.contains("U2=OCM")){
			     	OCMMarks = subwithmarks[1];	
			     	SetData(subwithmarks[1], i-1, 22);                 		     			
			      }	
			     if(OCMMarks.contains("T2=OCM")){
			     	OCMMarks = subwithmarks[1];	
			     if(OCMMarks == null || OCMMarks.isEmpty()){ OCMMarks = "00"; }					 
				 if(OCMMarks.contentEquals("AB") || OCMMarks.contentEquals("AB ")){ OCMMarks = "00"; }
					OCMT2marks = Integer.parseInt(OCMMarks);        //    show(OCMT2marks);
					OCMTotalT2 = OCMTotalT2 + OCMT2marks; 
					OCMTotal = Integer.toString(OCMTotalT2);					     				     	
			     	SetData(OCMTotal, i-1, 23);
	         	 }
	   	     }
	     	  OCMTotalT2 = 0;
	 }
		 
	  JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
	  th.repaint();                                          
  	}

	
	public void CHEMarks(){
		 String[] subwithmarks = null;
	   	 String plate[];
		 String CHEMarks = null,CHETotal = null, line;	
		 int CHET2marks = 0, CHETotalT2 = 0;
		 for(int i=1; i < Model.strArray.size() ; i++)                      //  Model.strArray.size()
	       {
			plate=Model.strArray.get(i).split("#");
	     	   for (int j = 2; j < plate.length; j++){		     		   
				line = plate[j];
				subwithmarks = line.split(":");
				CHEMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
				  if(CHEMarks.contains("U1=CHE")){
			     	 CHEMarks = subwithmarks[1];	
			     	 SetData(subwithmarks[1], i-1, 20);                 		     			
			   		}	
			      if(CHEMarks.contains("T1=CHE")){
			     	 CHEMarks = subwithmarks[1];	
			     	 SetData(subwithmarks[1], i-1, 21);                 		     			
			     	}	 
				  if(CHEMarks.contains("U2=CHE")){
			     	 CHEMarks = subwithmarks[1];	
			     	 SetData(subwithmarks[1], i-1, 22);                 		     			
			        }	
			      if(CHEMarks.contains("T2=CHE")){
			     	 CHEMarks = subwithmarks[1];	
			      if(CHEMarks == null || CHEMarks.isEmpty()){ CHEMarks = "00"; }					 
				  if(CHEMarks.contentEquals("AB") || CHEMarks.contentEquals("AB ")){ CHEMarks = "00"; }
				     CHET2marks = Integer.parseInt(CHEMarks);        //    show(CHET2marks);
					 CHETotalT2 = CHETotalT2 + CHET2marks; 
					 CHETotal = Integer.toString(CHETotalT2);		 			     	 			     	 
			     	 SetData(CHETotal, i-1, 23);
	         	   }
	     	   }
	     	        CHETotalT2 = 0;
	       	}
		 
	   	 JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
		 th.repaint();                                          
		 View.Sub3.text = "OCM / CHE";
 	}  
	
	public void MATMarks(){
		 String[] subwithmarks = null;
	   	 String plate[];
		 String MATMarks = null,MATTotal = null, line;
		 int MATT2marks = 0, MATTotalT2 = 0;
		 for(int i=1; i < Model.strArray.size() ; i++)                      //  Model.strArray.size()
	     	{
			 plate=Model.strArray.get(i).split("#");
	     	   for (int j = 2; j < plate.length; j++){	   		   
			     line = plate[j];
			     subwithmarks = line.split(":");
				 MATMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
			   if(MATMarks.contains("U1=MAT")){			        			
			      MATMarks = subwithmarks[1];	
			      SetData(subwithmarks[1], i-1, 24);                 		     			
			     }	
			   if(MATMarks.contains("T1=MAT")){
			      MATMarks = subwithmarks[1];	
			      SetData(subwithmarks[1], i-1, 25);                 		     			
			     }	 
			   if(MATMarks.contains("U2=MAT")){
			      MATMarks = subwithmarks[1];	
			      SetData(subwithmarks[1], i-1, 26);                 		     			
			     }	
			   if(MATMarks.contains("T2=MAT")){
			      MATMarks = subwithmarks[1];
			   if(MATMarks == null || MATMarks.isEmpty()){ MATMarks = "00"; }					 
			   if(MATMarks.contentEquals("AB") || MATMarks.contentEquals("AB ")){ MATMarks = "00"; }
				  MATT2marks = Integer.parseInt(MATMarks);     //       show(MATT2marks);
				  MATTotalT2 = MATTotalT2 + MATT2marks; 
				  MATTotal = Integer.toString(MATTotalT2);				      			      			      
			      SetData(MATTotal, i-1, 27);
	         	 }
	       }
	     	  MATTotalT2 = 0;
	    }
	   
		 JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
		 th.repaint();                                          

 	}

	public void SEPMarks(){
		 String[] subwithmarks = null;
	   	 String plate[];
		 String SEPMarks = null,SEPTotal = null, line;	
		 int SEPT2marks = 0, SEPTotalT2 = 0;
		 for(int i=1; i < Model.strArray.size() ; i++)                      //  Model.strArray.size()
	     	{
			 plate=Model.strArray.get(i).split("#");
	     	  for (int j = 2; j < plate.length; j++){		     		   
				  line = plate[j];
				  subwithmarks = line.split(":");
				  SEPMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
			  if(SEPMarks.contains("U1=SEP")){
			     SEPMarks = subwithmarks[1];	
			     SetData(subwithmarks[1], i-1, 24);                 		     			
			    }	
			  if(SEPMarks.contains("T1=SEP")){
			     SEPMarks = subwithmarks[1];	
			     SetData(subwithmarks[1], i-1, 25);                 		     			
			    }	 
			 if(SEPMarks.contains("U2=SEP")){
			    SEPMarks = subwithmarks[1];	
			    SetData(subwithmarks[1], i-1, 26);                 		     			
			   }	
			 if(SEPMarks.contains("T2=SEP")){
			    SEPMarks = subwithmarks[1];	
			 if(SEPMarks == null || SEPMarks.isEmpty()){ SEPMarks = "00"; }					 
			 if(SEPMarks.contentEquals("AB") || SEPMarks.contentEquals("AB ")){ SEPMarks = "00"; }
				SEPT2marks = Integer.parseInt(SEPMarks);      //      show(SEPT2marks);
				SEPTotalT2 = SEPTotalT2 + SEPT2marks; 
				SEPTotal = Integer.toString(SEPTotalT2);	    			    			    			    
			    SetData(SEPTotal, i-1, 27);
	           }
	       }
	     	 SEPTotalT2 = 0;
	   }
		 
	 JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
	 th.repaint();                                           
	 View.Sub4.text = "MAT / SEP";
    }

	public void EVSMarks(){
		 String[] subwithmarks = null;
	   	 String plate[];
		 String EVSMarks = null,line;	
		 for(int i=1; i < Model.strArray.size() ; i++)                      //  Model.strArray.size()
	     	{
			 plate=Model.strArray.get(i).split("#");
	     	  for (int j = 2; j < plate.length; j++){		     		   
				  line = plate[j];
				  subwithmarks = line.split(":");
				  EVSMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
			 if(EVSMarks.contains("T2=EVS")){
				 EVSMarks = subwithmarks[1];	
			 if(EVSMarks == null || EVSMarks.isEmpty()){ EVSMarks = "00"; }					 
			 if(EVSMarks.contentEquals("AB") || EVSMarks.contentEquals("AB ")){ EVSMarks = "00"; }
			 SetData(EVSMarks, i-1, 28);
	           }
	       }
	    }
     }

	public void PTEGrade(){
		 String[] subwithmarks = null;
	   	 String plate[];
		 String PTEGrade = null,line;	
		 for(int i=1; i < Model.strArray.size() ; i++)                      //  Model.strArray.size()
	     	{
			 plate=Model.strArray.get(i).split("#");
	     	  for (int j = 2; j < plate.length; j++){		     		   
				  line = plate[j];
				  subwithmarks = line.split(":");
				  PTEGrade = plate[j].substring(2, 8);     //   show( submarksU1); 
			 if(PTEGrade.contains("T2=PTE")){
				 PTEGrade = subwithmarks[1];	
			 if(PTEGrade == null || PTEGrade.isEmpty()){ PTEGrade = "00"; }					 
			 if(PTEGrade.contentEquals("AB") || PTEGrade.contentEquals("AB ")){ PTEGrade = "00"; }
			    SetData(PTEGrade, i-1, 29);
	           }
	       }
	    }
     }

	public ArrayList<String> collheaderfinder(String RollNo){
		
		String plate[];
      	String rollno;
      	boolean found = false;
//		String RollNo = View.Search().getText();
		int Rows = View.getTable().getRowCount();
		String subject = null;
				
		 for (int row = 0; row < Rows-1; row++) {				
			  plate=Model.strArray.get(row+1).split("#");
			  String SameRoll = GetData1(View.getTable(), row, 1);
			  rollno = plate[0].substring(0);		    

			for (int j = 2; j < plate.length; j++){							 
	     	   subject = plate[j].substring(5, 8);
	     	   rollno = plate[0].substring(0);
	     		  if(RollNo.trim().equals(rollno.trim())){    		    
	     		    
	     		    if(subject.contains("ENG")){ 
	     		    	View.Eng.text="ENG"; }
	     		    
	     		if(subject.contains("MAR")||subject.contains("TAM")||subject.contains("HIN")||subject.contains("EL1")
	     				||subject.contains("ITE")||subject.contains("CS1")){ 
	     		    	View.SL.text= subject; }
	     		   
	     		if(subject.contains("ECO")||subject.contains("BIO")
	     				||subject.contains("EL2")||subject.contains("CS2")){ 
	     		    	View.Sub1.text= subject; }
	     		  
	     		if(subject.contains("BKE")||subject.contains("PHY")){ 
		     		    View.Sub2.text= subject; }
	     		 
	     		if(subject.contains("OCM")||subject.contains("CHE")){ 
	     		    	View.Sub3.text= subject; }
	     		 
	     		if(subject.contains("MAT")||subject.contains("SEP")){ 
    		     	    JTableHeader th = View.getTable().getTableHeader();  //  For header changing dynamically
    		   	        th.repaint();      
    		    	    View.Sub4.text= subject; }     		   
	     		  }
	     	   }
		 }
		 headerArray.removeAll(headerArray);
		 headerArray.add(View.Eng.text);headerArray.add(View.SL.text);headerArray.add(View.Sub1.text);
		 headerArray.add(View.Sub2.text);headerArray.add(View.Sub3.text);headerArray.add(View.Sub4.text);
		return headerArray;

		
	}
	
	public String Streamfinder(String Rollno){		
		String Science = "SCIENCE", Commerce = "COMMERCE";
		String plate[];
      	String rollno;
      	int Rows = View.getTable().getRowCount();
		String subject = null;
		 for (int row = 0; row < Rows-1; row++) {				
			  plate=Model.strArray.get(row+1).split("#");
 			for (int j = 2; j < plate.length; j++){							 
	     	   subject = plate[j].substring(5, 8);
	     	   rollno = plate[0].substring(0);
	     		  if(Rollno.trim().equals(rollno.trim())){    		    	     		    	     		  
	     		    if(subject.contains("BKE")||subject.contains("PHY")){ 
		     		    View.Sub2.text= subject; }
	     		  }
	     	   }
		 }
 //       show(View.Sub2.text);        
		if(View.Sub2.text.equals("PHY")) {return Science;}
		else return Commerce;		
	}
	
	
	public void SearchByRollNo(String RollNo){
		
		String plate[];
      	String rollno;
      	boolean found = false;
//		String RollNo = View.Search().getText();
		int Rows = View.getTable().getRowCount();
		String subject = null;
				
		 for (int row = 0; row < Rows-1; row++) {				
			  plate=Model.strArray.get(row+1).split("#");
			  String SameRoll = GetData1(View.getTable(), row, 1);
			  rollno = plate[0].substring(0);		    
			  
			if(RollNo.trim().equals(SameRoll.trim())){		//	show(rollno);		
			found = true;
			// this will automatically set the view of the scroll in the location of the value
			View.getTable().scrollRectToVisible(View.getTable().getCellRect(row, 0, true));
					
           // this will automatically set the focus of the searched/selected row/value
			View.getTable().setRowSelectionInterval(row, row);	
				 for (int i = 0; i <= View.getTable().getColumnCount()-1; i++) {
	
					 View.getTable().getColumnModel().getColumn(i).setCellRenderer(new HighlightRenderer());
	             }					
			}		

			for (int j = 2; j < plate.length; j++){							 
	     	   subject = plate[j].substring(5, 8);
	     	   rollno = plate[0].substring(0);
	     		  if(RollNo.trim().equals(rollno.trim())){    		    
	     		    
	     		    if(subject.contains("ENG")){ 
	     		    	View.Eng.text="ENG"; }
	     		    
	     		if(subject.contains("MAR")||subject.contains("TAM")||subject.contains("HIN")||subject.contains("EL1")
	     				||subject.contains("ITE")||subject.contains("CS1")){ 
	     		    	View.SL.text= subject; }
	     		   
	     		if(subject.contains("ECO")||subject.contains("BIO")
	     				||subject.contains("EL2")||subject.contains("CS2")){ 
	     		    	View.Sub1.text= subject; }
	     		  
	     		if(subject.contains("BKE")||subject.contains("PHY")){ 
		     		    View.Sub2.text= subject; }
	     		 
	     		if(subject.contains("OCM")||subject.contains("CHE")){ 
	     		    	View.Sub3.text= subject; }
	     		 
	     		if(subject.contains("MAT")||subject.contains("SEP")){ 
    		     	    JTableHeader th = View.getTable().getTableHeader();  //  For header changing dynamically
    		   	        th.repaint();      
    		    	    View.Sub4.text= subject; }     		   
	     		  }
	     	   }
		 }
	}
	
	private class HighlightRenderer extends DefaultTableCellRenderer {

	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	    	table = View.getTable();
	        // everything as usual
	        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

	        // added behavior
	        if(row == table.getSelectedRow()) {

	            // this will customize that kind of border that will be use to highlight a row
	            setBorder(BorderFactory.createMatteBorder(2, 1, 2, 1, Color.BLUE));
	        }

	        return this;
	    }
	}
	
	 public void ClearTable()
	   {  int Rows = View.getTable().getRowCount();
		  int cols = View.getTable().getColumnCount();
		 for (int i = 0; i < Rows; i++)
		  {
		    for (int j = 1; j < cols; j++)
		      {
				SetData("",i,j);
			  }
		  }        
	   }
	
	public void ResizeTable(JTable tablename,int numberofrows)
		   { DefaultTableModel model=(DefaultTableModel) tablename.getModel();
	      int totalrows=tablename.getRowCount();
	      int difference=numberofrows-totalrows;
	      if(difference>0)
	       {
	          for(int i=0;i<difference;i++) model.addRow(new Object[]{" "});
	       }  
	 if(difference<0)
		   { difference=-difference;
	      for(int i=0;i<difference;i++) model.removeRow(0);	      
		   }
		 }
}
