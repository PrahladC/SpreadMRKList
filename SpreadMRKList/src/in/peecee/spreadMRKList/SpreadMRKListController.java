package in.peecee.spreadMRKList;


//   import in.peecee.spreadMRKList.SpreadMRKListView.ColumnGroup;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Window;
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
import java.util.HashSet;
import java.util.Set;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
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
//	private SpreadMRKListSubMarks SubMarks = new SpreadMRKListSubMarks();
	private showstatistics Stats = new showstatistics();
	private FailuresList fl = new FailuresList();
	private subjecToppers SubTop = new subjecToppers();
	private OverAllResult OAR = new OverAllResult();
	private progressBar pb = new progressBar();
	
//	private printRoutines prnRoutines = new printRoutines();
	
	public  ArrayList<String> strArray = new ArrayList<String>();
	public  ArrayList<String> TotalMarksArray = new ArrayList<String>();
	public  ArrayList<String> subArray = new ArrayList<String>();
	public  ArrayList<String> subMarksArray = new ArrayList<String>();
	public  ArrayList<String> headerArray = new ArrayList<String>();
	public  ArrayList<String> StuDetailsArray = new ArrayList<String>();
	public  ArrayList<String> subjectName = new ArrayList<String>();
	public  ArrayList<String> subjectsArray = new ArrayList<String>();
	public  ArrayList<String> Failedin3Subjs = new ArrayList<String>();
		
	public void show(float percent) {JOptionPane.showMessageDialog(null, percent);}   ///for debugging
	public void show(int num) {JOptionPane.showMessageDialog(null, num);}   ///for debugging
	public void show(String[] msg) {JOptionPane.showMessageDialog(null, msg);}   ///for debugging
	public void show(String msg) {JOptionPane.showMessageDialog(null, msg);}   ///for debugging
	public void Show(Object object) {JOptionPane.showMessageDialog(null, object);}   ///for debugging	
	public void Show(ArrayList<String> arrayList) {JOptionPane.showMessageDialog(null, arrayList);}   ///for debugging
	
	public int[] GraceMrks = {0,0,0,0,0,0};
	int failcounter = 0;
	
	public SpreadMRKListController(SpreadMRKListModel model, SpreadMRKListView view){

	        this.Model = model;
	        this.View = view;   
	        model.View = view;
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
        
    private ActionListener saveListener, loadListener, meritlistListener, searchListener, ResultListener,
	                       setprinterListener, printCurrentListener, printAllListener,
	                       canselListener, overallresultListener,   UpdateListener, spreadsheetListener,
	                       printConsolidatedListener, failedNumbersListener ;
    
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
//     	boolean mouseclicked = true;  
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
	  
	meritlistListener = new ActionListener() {
	public void actionPerformed(ActionEvent actionEvent) {                  
	                BtnMeritList();
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
  
    overallresultListener = new ActionListener() {
	public void actionPerformed(ActionEvent actionEvent) {                  
			BtnOveralllResult();
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
	View.getMeritListButton().addActionListener(meritlistListener);
	View.getFailedNums().addActionListener(failedNumbersListener);
	View.getSearchButton().addActionListener(searchListener);
	View.getSetPrinterButton().addActionListener(setprinterListener);
	View.getPrintCurrentButton().addActionListener(printCurrentListener);
	View.getPrintAllButton().addActionListener(printAllListener);
	View.getCanselButton().addActionListener(canselListener);
	View.getUpdateButton().addActionListener(UpdateListener);
	View.getSpreadSheetButton().addActionListener(spreadsheetListener);
	View.getPrintConsolidatedButton().addActionListener(printConsolidatedListener);
	View.getOverallResultButton().addActionListener(overallresultListener);

	 }	

	   protected void BtnOveralllResult() {
	 int RNum = 26;    //  View.getTable().getRowCount()-1;
	  ListOfSubjects(RNum);
  }
	
	private void BtnMeritList(){

	Stats.ShowStats();
	rankCom();
	rankSci();
	resultCom();
	resultSci();
    OverAllResult();
	SubTop.ComEngTopper(View, Model, Stats);
	SubTop.ComIteTopper(View, Model, Stats);
	SubTop.ComHinTopper(View, Model, Stats);
	SubTop.ComMarTopper(View, Model, Stats);
	SubTop.ComTamTopper(View, Model, Stats);
	SubTop.ComEcoTopper(View, Model, Stats);
	SubTop.ComBkeTopper(View, Model, Stats);
	SubTop.ComOcmTopper(View, Model, Stats);
	SubTop.ComSepTopper(View, Model, Stats);
	SubTop.ComMatTopper(View, Model, Stats);

	SubTop.ScEngTopper(View, Model, Stats);
	SubTop.ScIteTopper(View, Model, Stats);
	SubTop.ScHinTopper(View, Model, Stats);
	SubTop.ScMarTopper(View, Model, Stats);
	SubTop.ScTamTopper(View, Model, Stats);
	SubTop.ScEcoTopper(View, Model, Stats);
	SubTop.ScBioTopper(View, Model, Stats);
	SubTop.ScPhyTopper(View, Model, Stats);
	SubTop.ScCheTopper(View, Model, Stats);
	SubTop.ScMatTopper(View, Model, Stats);
	SubTop.ElectronicsTopper(View, Model, Stats);	  
	SubTop.ComputerTopper(View, Model, Stats);

	}                    

	public void BtnFailedNumbers() {
//		fl.failList(5);

	int row = 0, k = 0, failcounter = 0;
	float sub1 = 0, sub2 = 0, sub3 = 0, sub4 = 0, sub5 = 0, sub6 = 0;
	int evs = 0;  String pte = null;
	int NumofRows = View.getTable().getRowCount()-1;
	fl.failures();        
	String result = null, remark = null;
			
	for(row = 0; row < NumofRows; row++){
//			result = GetData1(View.getTable(),row ,31);	
		result = Mod(row);
		String Roll = GetData1(View.getTable(), row, 1);
    	String Div = GetData1(View.getTable(),  row, 2);
   	    String Name = GetData1(View.getTable(), row, 3);
   	    sub1 = Sub1(row); sub2 = Sub2(row); sub3 = Sub3(row);
   	    sub4 = Sub4(row); sub5 = Sub5(row); sub6 = Sub6(row);
   	    evs = EVSmarks(row);  
   	    pte = GetData1(View.getTable(), row, 29);
		if(result == "Fail" || result.contains("Fail")){
		   failcounter++;	
		   fl.SetData(Roll, k, 1);
		   fl.SetData(Div,  k, 2);
		   fl.SetData(Name, k, 3);	
			   
		   fl.SetData((int) Math.ceil(sub1/2), k, 4);
		   fl.SetData((int) Math.ceil(sub2/2), k, 5);
           fl.SetData((int) Math.ceil(sub3/2), k, 6);
           fl.SetData((int) Math.ceil(sub4/2), k, 7);
           fl.SetData((int) Math.ceil(sub5/2), k, 8);
           fl.SetData((int) Math.ceil(sub6/2), k, 9);
               
           fl.SetData(evs, k, 10);
           fl.SetData(pte, k, 11);
		   if(k < NumofRows) k++;	
			   
/*		        float[] AverageMrks = {Sub1(row), Sub2(row), Sub3(row), Sub4(row), Sub5(row), Sub6(row)};
		        for(int i = 0; i < 6; i++){
		    		fl.SetData(String.valueOf((int) Math.ceil(AverageMrks[i]/2)), k, k+4); // Average marks of each subject

		        }
*/			   
			   
			   
			}
			else continue;
			
		}               
//		show("Fail Counter : " + failcounter );
	    for(int i = 0; i < failcounter; i++){
	       fl.SetData(remark, i, 12);
	       fl.SetData(i+1, i, 0);
	    }		 
	}
	
	protected void BtnPrintConsolidated() {
//		System.exit(0);
		
     final String[] TableItemC1 = {"Exam","Unit I","Term I","Unit II","TermII","Agg","Avg","Grace"};
	 final String[] TableItemC2 = {"Max", "25", "50", "25", "100", "-----", "-----", "15"};
	 final String[] TableItemC3 = {"Min", "-----", "-----", "-----", "-----", "70", "35", "-----"};
	 final String[] RowHeader = {"EVS", "PTE", "    Total"};
	 final String[] StuDetails = {"Roll No:", "Div", ""};
	
	   try {
		  PrinterJob pjob = PrinterJob.getPrinterJob();
		  pjob.setJobName("Consolidated Marks Card");
		  pjob.setPrintable(new Printable() {
		  public int print(Graphics pg, PageFormat pf, int pageNum) {
		  int Rows = View.getTable().getRowCount()-1;    //   show("Number of Rows : "+Rows);
		  int totalpages = 0;
		  int TotalPages = Rows/12;
		  int Remainder = Rows%12;                       //   show("Remainder is : "+Remainder);
			if(Remainder == 0) totalpages = TotalPages;            
			else totalpages = TotalPages + 1;             //   show("TotalNumber of pages : "+totalpages);
			  if (pageNum < totalpages) 
			   {
				Font newFont;		          
				newFont = new Font("Liberation Serif", Font.BOLD, 8);
                pg.setFont(newFont);
                    
//					pg.drawString("AA",10,10);
//					pg.drawString("AB",580,10);
//					pg.drawString("AC",10,780);
//					pg.drawString("AD",580,780);
                    
    pg.drawLine(315, 20, 315, 770);                         //  Vertical Divider LINE
    pg.drawString(String.valueOf(pageNum+1), 580, 780);     //  Page Number at Right Bottom Corner.
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
		   pg.drawString(StuDetails[i],320+i*55, 28+r*JVNGrid);      //  Roll No and Division  Right of Page
	  }
	}
    int m = 0;
	int y = 28;
//        String Roll = GetData1(View.getTable(), i+pageNum*12, 1);          
      for(int j = 0; j < 6; j++){
       	for(int k = 0; k< 2; k++){
       		if(m+pageNum*12 > Rows)continue;
       		String Roll = GetData1(View.getTable(), m+pageNum*12, 1);
       		String Div = GetData1(View.getTable(), m+pageNum*12, 2);
      	    String Name = GetData1(View.getTable(), m+pageNum*12, 3);        		
       		pg.drawString(Roll, 65+k*jump, y);                          //  Printing Roll Numbers
       		pg.drawString(Div, 105+k*jump, y);                          //  Printing Divisions     
       		if(Name == null || Name.isEmpty()){ Name = ""; }
	        if(Name.length() > 32) {pg.drawString(Name.substring(0, 32), 142+k*jump, y);}        //  Printing Names
	            else pg.drawString(Name, 142+k*jump, y);
	            if(m < 12) m++;
       	}
            y = y + JVNGrid;	
      }                       

/////   E V S   M A R K S,  P T E   G R A D E S  and  S U B J E C T    N A M E S 
        
        m = 0;
        int y1 = 39,  y2 = 87;
        for( int j = 0; j < 6; j++){
	        for(int k = 0; k < 2; k++){
	          if(m+pageNum*12 > Rows)continue;
		      String EVS = GetData1(View.getTable(), m+pageNum*12, 28);                          // EVS
//		      if(EVS == null || EVS.isEmpty()){ EVS = "00"; }    show("The page No. is : "+pageNum);
		      String PTE = GetData1(View.getTable(), m+pageNum*12, 29);                          // PTE
		      if(PTE == null || PTE.isEmpty()){ PTE = "00"; }
		      String RollNo = View.getTable().getModel().getValueAt(m+pageNum*12, 1).toString();
		      if(RollNo == null || RollNo.isEmpty()){ continue; }
		      subjectName = columnHeaderfinder(RollNo);   //     Show(subjectName);
				for(int i = 0; i< subjectName.size(); i++){ 
				  pg.drawString(subjectName.get(i), (106+i*20)+k*jump, y1);                   	 // Subjects				
				}
				  for(int t = 0; t < 3; t++){
				    pg.drawString(EVS, 230+k*jump, y2+t*12);   //  pg.drawString(EVS, 230+k*jump, y2+12);       // EVS Marks
				  }    
				 pg.drawString(PTE, 250+k*jump, y2);                                              // PTE Grade
				
				if(m < 12) m++;
	        }   	        
	        y1 = y1 + JVNGrid;
	        y2 = y2 + JVNGrid;
        }
        
 ////   A L L    S U B J E C T    A L L    E X A M    M A R K S   
        
        m = 0;
        int y3 = 51;
      for(int d = 0; d < 6; d++){  
        for(int k = 0; k < 2; k++){
        	if(m+pageNum*12 > Rows)continue;
		  for(int j = 0; j < 6; j++){
			for(int i = 0; i < 4; i++){                                             //  All marks except EVS and PTE
				pg.drawString(GetData1(View.getTable(), m+pageNum*12, 4+i+(4*j)), (110+j*20)+k*jump, y3+i*12);
			}			
          }
		     if(m < 12) m++;
        }
             y3 = y3 + JVNGrid;
      }
     
/////   EX A M W I S E   T O T A L       
      
	  m = 0;	
	 int y4 = 51;
     for( int j = 0; j < 6; j++){
	        for(int k = 0; k < 2; k++){
	        	if(m+pageNum*12 > Rows)continue;
	        	int[] ExamwiseSum = {SumU1Score(m+pageNum*12), SumT1Score(m+pageNum*12), SumU2Score(m+pageNum*12), 
			             SumT2andEVSScore(m+pageNum*12)};
	        	      
				for(int i = 0; i< 4; i++){ 
				pg.drawString(String.valueOf(ExamwiseSum[i]), (276)+k*jump, y4+i*12);      // Sum of Unit and Term Exams				
				}
				if(m < 12) m++;
	        }   	        
              y4 = y4 + JVNGrid;
     }
  
//////    A G G R E G A T E   A N D   A V E R A G E    M A R K S      
     
     m = 0;
     int y5 = 99, y6 = 111;
     for( int j = 0; j < 6; j++){
	   for(int k = 0; k < 2; k++){
		   if(m+pageNum*12 > Rows)continue;
		int[] SubTotal = {Sub1(m+pageNum*12), Sub2(m+pageNum*12), Sub3(m+pageNum*12),
				          Sub4(m+pageNum*12), Sub5(m+pageNum*12), Sub6(m+pageNum*12)};
		float[] AggMrks ={Sub1(m+pageNum*12), Sub2(m+pageNum*12), Sub3(m+pageNum*12),
		                  Sub4(m+pageNum*12), Sub5(m+pageNum*12), Sub6(m+pageNum*12)};	
		String temp = String.valueOf((int) Math.ceil(AggMrks[1]/2));
	      for(int i = 0; i< 6; i++){ 
				pg.drawString(String.valueOf(SubTotal[i]), (110+i*20)+k*jump, y5);    //  Aggregate Marks
				pg.drawString(String.valueOf((int) Math.ceil(AggMrks[i]/2)), (110+i*20)+k*jump, y6); // Average marks of each subject
		  }
				if(m < 12) m++;
	    }   	        
	        y5 = y5 + JVNGrid;
	        y6 = y6 + JVNGrid;
     }
  
//////  A G G R E G A T E    A N D    A V E R A G E    S U M
     
	m = 0;	
	y5 = 99; 	y6 = 111;
    for( int j = 0; j < 6; j++){
	        for(int k = 0; k < 2; k++){
	        	if(m+pageNum*12 > Rows)continue;
	        	int[] ExamwiseSum = {SumU1Score(m+pageNum*12), SumT1Score(m+pageNum*12), SumU2Score(m+pageNum*12), 
			                         SumT2andEVSScore(m+pageNum*12)};
	    		float[] AggMrks ={Sub1(m+pageNum*12), Sub2(m+pageNum*12), Sub3(m+pageNum*12),
		                          Sub4(m+pageNum*12), Sub5(m+pageNum*12), Sub6(m+pageNum*12)};	
				int EVSmrks = EVSmarks(m+pageNum*12);
				int AvgSum = (int) Math.ceil(AggMrks[0]/2)+(int) Math.ceil(AggMrks[1]/2)+(int)Math.ceil(AggMrks[2]/2)+
						  (int) Math.ceil(AggMrks[3]/2)+(int) Math.ceil(AggMrks[4]/2)+(int) Math.ceil(AggMrks[5]/2);
				int AvgTotal = AvgSum+EVSmrks;
	        	int AggSum = 0;
	        	AggSum = ExamwiseSum[0]+ExamwiseSum[1]+ExamwiseSum[2]+ExamwiseSum[3];
				pg.drawString(String.valueOf(AggSum)+"/1250", (270)+k*jump, y5);      // Sum of AGGERATE Marks				
			    pg.drawString(String.valueOf(AvgTotal)+"/650",(270)+k*jump, y6);      // Sum of AVERAGE  Marks				
				if(m < 12) m++;
	        }   	        
             y5 = y5 + JVNGrid;
             y6 = y6 + JVNGrid;
    }
     
//////R E S U L  T    P A R T
    
	m = 0;	
	y6 = 123; int y7 = 135;
    for( int j = 0; j < 6; j++){
	  for(int k = 0; k < 2; k++){	
		if(m+pageNum*12 > Rows)continue;		  
	  	String result = Mod(m+pageNum*12);
	    pg.drawString(result, 66+k*jump, y7);
	    int[] GraceValues = Mod1(m+pageNum*12);
	    int GraceTotal = 0;
	      for(int p = 0; p < 6; p++){GraceTotal += GraceValues[p];}
	    	for(int i = 0; i < 6; i++){    
	    		if(result == "Promoted"){pg.drawString(String.valueOf(GraceValues[i]),(110+i*20)+k*jump, y6);
	                                     pg.drawString(String.valueOf(GraceTotal)+"/15", (270)+k*jump, y6); }
	    		else pg.drawString("", (110+i*20)+k*jump, y6);
	    	}	
	    		String percent = Percent(m+pageNum*12);
	    		if(result == "Promoted" || result == "Fail") pg.drawString(" ", 66+k*jump, y7);
	    		else pg.drawString(percent+" %", 125+k*jump, y7);
	        	
	        	if(m < 12) m++;
	        }   	        
             y6 = y6 + JVNGrid;
             y7 = y7 + JVNGrid;
    }
 		   		
		 return Printable.PAGE_EXISTS;
	} 
		    else { return Printable.NO_SUCH_PAGE; }  
				  
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
		   int TMrg = 748, BMrg = 8, Center = 0,  x = 7, StringPosition = 0;  // TMrg = Top Margin, BMrg = Bottom Margin.
		   Center = (TMrg - BMrg)/2;
		   final String[] Exams = {"U1", "T1", "U2", "T2"};
		   final String[] HeadereSubjects = {"ENGLISH", "SL / VOC", "ECO/BIO/VOC", "BKE / PHY", "OCM / CHE", "MAT / SEP"};
				
	  try {
		  
		   PrinterJob pjob = PrinterJob.getPrinterJob();
		   pjob.setJobName("Spread Sheet Print");
		   pjob.setCopies(1);
		   pjob.setPrintable(new Printable() {
		   public int print(Graphics pg, PageFormat pf, int pageNum) {
		   int Rows = View.getTable().getRowCount()-1;   
		   int totalpages = 0;
		   int TotalPages = Rows/28;
		   int Remainder = Rows%28;
		   if(Remainder == 0){ totalpages = TotalPages;}
		   else totalpages = TotalPages+1;
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
		        pg.drawString(String.valueOf(pageNum+1), 760, 570);
	        
		        for(int i = 0; i < 6; i++){
		           if(i == 0) {pg.drawString(HeadereSubjects[i], 280, 43);}
		           if(i == 1) {pg.drawString(HeadereSubjects[i], 360, 43);}
		           if(i == 2) {pg.drawString(HeadereSubjects[i], 430, 43);}
		           if(i > 2)  {pg.drawString(HeadereSubjects[i], 280+i*80, 43);}
		        }
		        
		        for(int i = 0; i < 6; i++){
		          for(int m = 0; m < 4; m++){
		              pg.drawString(Exams[m], (270+m*20)+i*80, 60);              //  Printing Exam Titles
		          }
		        }
		        		        
		        for( int i = 0; i < 28; i++){
		        	if(i+pageNum*28 > Rows) continue;
		            String Roll = GetData1(View.getTable(), i+pageNum*28, 1);                    //  Printing Roll Numbers
		        	pg.drawString(Roll, 5, 77+(i*17));
		            String Name = GetData1(View.getTable(), i+pageNum*28, 3);                    //  Printing Names  
		            if(Name.length() > 27){ pg.drawString(Name.substring(0, 27), 42, 77+i*17);}
		            else {pg.drawString(Name, 42, 77+i*17);}
		        }

		        for(int i = 0; i < 28; i++){                                     //  Number of Lines in one page = 28
		        	if(i+pageNum*28 > Rows) continue;
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
	          choosertosave.setCurrentDirectory(new File("/home/student/Test Entries"));   
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
        int Rows = View.getTable().getRowCount();
		for(int row = 0; row < Rows-1; row++){
			String result =	Mod(row);
			SetData(result, row, 31);
		}		
	}
	
	public ArrayList<String> ListOfSubjects(int RNum){
//		 RNum = View.getTable().getRowCount()-1;
		 String SubjectNames = null; 
		 int index = 0;
	  	 String plate[];
//	  	      for(int j = 1; j < RNum; j++){ 
			   		plate=Model.strArray.get(RNum).split("#");	
				    subjectsArray.removeAll(subjectsArray);	
					for (int i = 1; i < plate.length; i++) {			   		
						if(plate[1].length() <= 14)
						
						  { 
						    SubjectNames = plate[i].substring(5, 8);	
						    subjectsArray.add(SubjectNames);
				          }
			     	    
						else 
						  {
							  SubjectNames = plate[i].substring(5, 8);
							  subjectsArray.add(SubjectNames);
			     	      }
		        }	  
					Set<String> NewSubjectsArray = new HashSet<>();
					NewSubjectsArray.addAll(subjectsArray);
					subjectsArray.clear();
					subjectsArray.addAll(NewSubjectsArray);
					Show(subjectsArray);
					Show(subjectsArray.get(4));

//	  	      }
			return subjectsArray;
	
	}
	
	public void process(){
	 ClearTable();
	 ResizeTable(View.getTable(),Model.strArray.size());      //   Show(Model.strArray.size());
  	 String plate[];
  	 String rollno, names, div;		   	
		 for(int i=1; i < Model.strArray.size(); i++)                      //  strArray.size()
	     	{ 
		   		 String SrNo=String.format("%d",i);
				 SetData(SrNo,i-1,0);
		   		 plate=Model.strArray.get(i).split("#");
			     rollno = plate[0];
			  if(plate[1].length()<=14){ 
			     rollno = plate[0];         //   show(plate);  show(plate[0]); show(plate[1]);   show(plate[2]);    
			     div = plate[1].substring(0,1);
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
		Model.ENGMarks();
		Model.SecLangMarks();
		Model.VocationalMarks();
		Model.BIOMarks();
		Model.ECOMarks();
		Model.BKEMarks();
		Model.PHYMarks();
		Model.OCMMarks();
		Model.CHEMarks();
		Model.MATMarks();
		Model.SEPMarks();
		Model.EVSMarks();
		Model.PTEGrade();
        TotalScore(); 
  }
	    	    	    
	private void BtnSearch(){
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
			 
			 
			  if(plate[1].length()<=14){names = plate[1].substring(0, 0); }  // For table or .rlt file with out names
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
		  subject = columnHeaderfinder(RollNo);
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
				int totalpages = RowCount;
				  if (pageNum < totalpages) 
				   {
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
				subjectName = columnHeaderfinder(RollNo);
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
 	 	
  public void rankCom(){
	  int[] arrayOfMarks; int ind = 0;
	  int rowcount = View.getTable().getRowCount();
	  arrayOfMarks = new int [rowcount];
	  int length = arrayOfMarks.length;	  
	  
	  for(int row = 0; row < rowcount; row++){
		  String RollNo = GetData(View.getTable(), row,1).toString().trim();
	      String Stream = Streamfinder(String.valueOf(RollNo));
	      if(Stream.contentEquals("SCIENCE")) continue;	     
		  arrayOfMarks[ind] = AverageMarksSum(row);
		  ind++;	    
	  }	
//	  show(ind);
//	    show(arrayOfMarks[1468]);
		int i;
		int large[] = new int[5];
		int max = 0, index;
		for (int j = 0; j < 5; j++) {
		     max = arrayOfMarks[0];
		     index = 0;
		  for (i = 1; i < arrayOfMarks.length; i++) {
		    if (max < arrayOfMarks[i]) {
		        max = arrayOfMarks[i];
		        index = i;
		       }
		    }
		  
		large[j] = max;
		arrayOfMarks[index] = Integer.MIN_VALUE;
		String RollNo = GetData(View.getTable(), index,1).toString().trim();
		String percent = Percent(Integer.valueOf(index));
	    String Div =  GetData(View.getTable(), index,2).toString();
	    String Name = GetData(View.getTable(), index,3).toString();
        if(Name.length() < 1) continue;
	    Stats.SetData(Name.substring(0, 35), j, 2);
		Stats.SetData("    "+RollNo, j, 3);
		Stats.SetData("     "+Div, j, 4);
		Stats.SetData("  "+String.valueOf(large[j]), j, 5);
		Stats.SetData("    "+percent, j, 6);	
		
	    }	  
  }
 	
  public void rankSci(){
	  int[] arrayOfMarks; int ind = 0;
	  int rowcount = View.getTable().getRowCount();
	  arrayOfMarks = new int [rowcount];
	  int length = arrayOfMarks.length;	  
	  int[] arrayOfIndex = new int[rowcount];
	  
	  for(int row = 0; row < rowcount; row++){
		  String RollNo = GetData(View.getTable(), row,1).toString().trim();
	      String Stream = Streamfinder(String.valueOf(RollNo));
	      if(Stream.contentEquals("COMMERCE")) continue;	     
		  arrayOfMarks[ind] = AverageMarksSum(row);
		  arrayOfIndex[ind] = row;
		  ind++;	    
	  }	

	  int i;
		int large[] = new int[5];
		int max = 0, index, temp= 0;
		for (int j = 0; j < 5; j++) {
		     max = arrayOfMarks[0];
		     index = 0;
		  for (i = 1; i < arrayOfMarks.length; i++) {
		    if (max < arrayOfMarks[i]) {
		        max = arrayOfMarks[i];
		        index = i;          
		        temp = arrayOfIndex[i];
		       }
		    }
		  
		large[j] = max;
		arrayOfMarks[index] = Integer.MIN_VALUE;
		String RollNo = GetData(View.getTable(), temp,1).toString().trim();
		String percent = Percent(Integer.valueOf(temp));
	    String Div =  GetData(View.getTable(), temp,2).toString();
	    String Name = GetData(View.getTable(), temp,3).toString();
	    if(Name.length() < 1) continue;
	    Stats.SetData(Name.substring(0, 35), j+5, 2);
		Stats.SetData("    "+RollNo, j+5, 3);
		Stats.SetData("     "+Div, j+5, 4);
		Stats.SetData("  "+String.valueOf(large[j]), j+5, 5);
		Stats.SetData("    "+percent, j+5, 6);	    
	    }	  
  }
 	
	public void resultCom(){
		int[] arrayOfMarks; int ind = 0;
		  int rowcount = View.getTable().getRowCount()-1;
		  arrayOfMarks = new int [rowcount];
		  int[] arrayOfIndexC = new int [rowcount];
		  int Numfailed = 0, Dist = 0, FC = 0,
			  SC = 0, Passclass = 0, Promoted = 0, PassPercent = 0;
		  String Percentage = null;
		  
		  for(int row = 0; row < rowcount; row++){
			  String RollNo = GetData(View.getTable(), row,1).toString().trim();
		      String Stream = Streamfinder(String.valueOf(RollNo));
		      if(Stream.contentEquals("SCIENCE")) continue;	     
			  arrayOfMarks[ind] = AverageMarksSum(row);
			  arrayOfIndexC[ind] = row;			 
//			  show(RollNo + " : " + row);
			  
			  String result = Mod(row);
			  if(result == "Fail") Numfailed++;
			  if(result == "Promoted") Promoted++;
			  if(result == "Pass Class") Passclass++;
			  if(result == "Second Class")  SC++;
			  if(result == "First Class")   FC++;
			  if(result == "Distinction") Dist++;
			  ind++;	    
		  }	  
		  
		  float sum = Promoted+Passclass+SC+FC+Dist, Passpercent, Percent;
		  Passpercent = (sum*100)/ind;
		  Stats.SetData2("     "+ind, 0, 2);
		  Stats.SetData2("     "+(ind-Numfailed), 1, 2);
		  Stats.SetData2("     "+Numfailed, 2, 2);
		  Stats.SetData2("     "+Dist, 3, 2);
		  Stats.SetData2("     "+FC, 4, 2);
		  Stats.SetData2("     "+SC, 5, 2);
		  Stats.SetData2("     "+Passclass, 6, 2);
		  Stats.SetData2("     "+Promoted, 7, 2);
		  Stats.SetData2("     "+Percentage.format("%.2f", Passpercent), 8, 2);		  
	}

	public void resultSci(){
		int[] arrayOfMarks; int ind = 0;
		  int rowcount = View.getTable().getRowCount()-1;
		  arrayOfMarks = new int [rowcount];
		  int length = arrayOfMarks.length;	
		  int[] arrayOfIndexC = new int [rowcount];
		  int Numfailed = 0, Dist = 0, FC = 0,
			  SC = 0, Passclass = 0, Promoted = 0, PassPercent = 0;
		  String Percentage = null;
		  
		  for(int row = 0; row < rowcount; row++){
			  String RollNo = GetData(View.getTable(), row,1).toString().trim();
		      String Stream = Streamfinder(String.valueOf(RollNo));
		      if(Stream.contentEquals("COMMERCE")) continue;	     
			  arrayOfMarks[ind] = AverageMarksSum(row);
			  arrayOfIndexC[ind] = row;
//			  show(RollNo + " : " + row);

				  String result = Mod(row);
				  if(result == "Fail") Numfailed++;
				  if(result == "Promoted") Promoted++;
				  if(result == "Pass Class") Passclass++;
				  if(result == "Second Class")  SC++;
				  if(result == "First Class")   FC++;
				  if(result == "Distinction") Dist++;
				  ind++;	    
		  }	  
		  Show(rowcount);
		  float sum = Promoted+Passclass+SC+FC+Dist, Passpercent, Percent;
		  Passpercent = (sum*100)/ind;
		  Stats.SetData2("     "+ind, 0, 3);
		  Stats.SetData2("     "+(ind-Numfailed), 1, 3);
		  Stats.SetData2("     "+Numfailed, 2, 3);
		  Stats.SetData2("     "+Dist, 3, 3);
		  Stats.SetData2("     "+FC, 4, 3);
		  Stats.SetData2("     "+SC, 5, 3);
		  Stats.SetData2("     "+Passclass, 6, 3);
		  Stats.SetData2("     "+Promoted, 7, 3);
		  Stats.SetData2("     "+Percentage.format("%.2f", Passpercent), 8, 3);		  

	}

	public void OverAllResult(){
		  int[] arrayOfMarks; int ind = 0;
		  int rowcount = View.getTable().getRowCount()-1;
		  arrayOfMarks = new int [rowcount];
		  int length = arrayOfMarks.length;	
		  int[] arrayOfIndexC = new int [rowcount];
		  int Numfailed = 0, Dist = 0, FC = 0,
			  SC = 0, Passclass = 0, Promoted = 0, PassPercent = 0;
		  String Percentage = null;
		  
	   for(int row = 0; row < rowcount; row++){
		 
		  String result = Mod(row);
	      if(result == "Fail" || result.contains("Fail")) Numfailed++;
		  if(result == "Promoted" || result.contains("Promoted")) Promoted++;
		  if(result == "Pass Class" || result.contains("Pass Class")) Passclass++;
		  if(result == "Second Class" || result.contains("Second Class"))  SC++;
	 	  if(result == "First Class" || result.contains("First Class"))   FC++;
		  if(result == "Distinction" || result.contains("Distinction")) Dist++;	    
	  }	  
		  
		  float sum = Promoted+Passclass+SC+FC+Dist, Passpercent;
		  Passpercent = (sum*100)/rowcount;
		  Stats.SetData2("     "+rowcount, 0, 4);
	   	  Stats.SetData2("     "+(rowcount-Numfailed), 1, 4);
		  Stats.SetData2("     "+Numfailed, 2, 4);
		  Stats.SetData2("     "+Dist, 3, 4);
		  Stats.SetData2("     "+FC, 4, 4);
		  Stats.SetData2("     "+SC, 5, 4);
		  Stats.SetData2("     "+Passclass, 6, 4);
		  Stats.SetData2("     "+Promoted, 7, 4);
		  Stats.SetData2("     "+Percentage.format("%.2f", Passpercent), 8, 4);		  		
	}
	
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

//		TotalScore();
		SubTop.Rearrange(View, Model);
//	pb.progressBar(View);	
		
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

	public int ElectronicSum(int row){
		String RollNo = View.getTable().getModel().getValueAt(row, 1).toString(); 
		subjectName = columnHeaderfinder(RollNo);
//		row = View.getTable().getSelectedRow();
	    String marks;
		int Marks = 0, SumElectronicsMarks = 0;   
		  if(subjectName.contains("EL1") || subjectName.contains("EL2")){
			 for(int j = 0; j < 8; j++){
				marks = GetData1(View.getTable(),row, 8+j);                    //  show("marks = "+marks);
				if(marks == null || marks.isEmpty()){ marks = "00"; }					 
				if(marks.contentEquals("AB") || marks.contentEquals("AB ")){ marks = "00"; }					 
				Marks = Integer.parseInt(marks);                               //  show("Marks = "+Marks);
				SumElectronicsMarks = SumElectronicsMarks + Marks;             //  show(TotalMarks);			 
			  }
		  }
		return SumElectronicsMarks;							
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
		double sub1 = 0, sub2 = 0, sub3 = 0, sub4 = 0, sub5 = 0, sub6 = 0, evs= 0;
		String percent = null;
		sub1 = Sub1(row); sub2 = Sub2(row); sub3 = Sub3(row); sub4 = Sub4(row); 
		sub5 = Sub5(row); sub6 = Sub6(row);  evs =  EVSmarks(row);

		int[] AverageMarks = { (int) Math.ceil(sub1/2), (int) Math.ceil(sub2/2), (int) Math.ceil(sub3/2),
				               (int) Math.ceil(sub4/2), (int) Math.ceil(sub5/2), (int) Math.ceil(sub6/2) };
		
		int SumOfAverages = AverageMarks[0] + AverageMarks[1] + AverageMarks[2] 
		          + AverageMarks[3] + AverageMarks[4] + AverageMarks[5];
        double Total = SumOfAverages + evs;  //s  Show(Total);
	    double Class = (Total*100)/650;   

        return percent.format("%.2f", Class);

	   }
	   	
	public String Mod(int row){
		double sub1 = 0, sub2 = 0, sub3 = 0, sub4 = 0, sub5 = 0, sub6 = 0, evs= 0, pte = 0;
		String PTE = null;
		String Result1 = "Fail", Result2 = "Promoted", Result3 = "Pass Class"; 
		String Result4 = "Second Class", Result5 = "First Class", Result6 = "Distinction";
		int GraceCount = 0, GraceTotal = 0, GraceValue = 0; 
		sub1 = Sub1(row); sub2 = Sub2(row); sub3 = Sub3(row); sub4 = Sub4(row); 
		sub5 = Sub5(row); sub6 = Sub6(row);  evs =  EVSmarks(row);
		GraceCount = 0;  GraceTotal = 0; GraceValue = 0;  
		int FCCounter = 0;
		
		PTE = GetData1(View.getTable(), row, 29);
		if(PTE == null || PTE.isEmpty()){ PTE = "00"; }					 
		if(PTE.contentEquals("AB") || PTE.contentEquals("AB ")){ PTE = "00"; }	
		pte = Integer.parseInt(PTE);
		
/*
        for(int i= 0; i < 6; i++)
 	    	float[] AggMrks ={Sub1(m+pageNum*12), Sub2(m+pageNum*12), Sub3(m+pageNum*12),
		                      Sub4(m+pageNum*12), Sub5(m+pageNum*12), Sub6(m+pageNum*12)};	
			int EVSmrks = EVSmarks(m+pageNum*12);
			int AvgSum = (int) Math.ceil(AggMrks[0]/2)+(int) Math.ceil(AggMrks[1]/2)+(int)Math.ceil(AggMrks[2]/2)+
			   		     (int) Math.ceil(AggMrks[3]/2)+(int) Math.ceil(AggMrks[4]/2)+(int) Math.ceil(AggMrks[5]/2);
			int AvgTotal = AvgSum+EVSmrks;
 
  		
 */
		
		int[] AverageMarks = { (int) Math.ceil(sub1/2), (int) Math.ceil(sub2/2), (int) Math.ceil(sub3/2),
				               (int) Math.ceil(sub4/2), (int) Math.ceil(sub5/2), (int) Math.ceil(sub6/2) };
		
		int SumOfAverages = AverageMarks[0] + AverageMarks[1] + AverageMarks[2] 
		          + AverageMarks[3] + AverageMarks[4] + AverageMarks[5];
        int Total = (int) (SumOfAverages + evs);  //   show("AVerages : "+SumOfAverages); show("EVS : "+evs);  show("Total : "+Total);

        
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
		 int Class = (Total*100)/650;  //   show(Class);  Class means First Class, Second Class , Pass Class etc.
		
		 if(GraceValue > 10 || pte < 1 || evs < 18){ return Result1;}
		 else if(GraceCount > 3){ return Result1;}
		 else if(GraceCount < 4 && GraceTotal > 15){return Result1;}
		 else if(GraceCount < 4 && GraceTotal > 0 && GraceTotal < 16){return Result2;}
		 else if(Class >= 50 && Class < 60) { FCCounter++;  /*show(FCCounter);*/ return Result4; }
		 else if(Class >= 60 && Class < 70) { return Result5; }
		 else if(Class >= 70) { return Result6; }    
		 else return Result3;
		
	}            
		
	public int[] Mod1(int row){
		double sub1 = 0, sub2 = 0, sub3 = 0, sub4 = 0, sub5 = 0, sub6 = 0, evs= 0;
		String PTE = null;
		int GraceCount = 0, GraceTotal = 0, GraceValue = 0; 
		sub1 = Sub1(row); sub2 = Sub2(row); sub3 = Sub3(row); sub4 = Sub4(row); 
		sub5 = Sub5(row); sub6 = Sub6(row);  evs =  EVSmarks(row);
		GraceCount = 0;  GraceTotal = 0; GraceValue = 0;  
		
		PTE = GetData1(View.getTable(), row, 29);
		if(PTE == null || PTE.isEmpty()){ PTE = "00"; }					 
		if(PTE.contentEquals("AB") || PTE.contentEquals("AB ")){ PTE = "00"; }	
		int[] AverageMarks = { (int) Math.ceil(sub1/2), (int) Math.ceil(sub2/2), (int) Math.ceil(sub3/2),
				               (int) Math.ceil(sub4/2), (int) Math.ceil(sub5/2), (int) Math.ceil(sub6/2) };
		
		int SumOfAverages = AverageMarks[0] + AverageMarks[1] + AverageMarks[2] 
		          + AverageMarks[3] + AverageMarks[4] + AverageMarks[5];
        int Total = (int) (SumOfAverages + evs);
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

	public int AverageMarksSum(int rownum){
//		int row = View.getTable().getSelectedRow();		
	    int row = rownum, AvgSum = 0;
		float[] AggMrks ={Sub1(row), Sub2(row), Sub3(row),Sub4(row), Sub5(row), Sub6(row)};	
				int EVSmrks = EVSmarks(row);
				AvgSum = (int) Math.ceil(AggMrks[0]/2)+(int) Math.ceil(AggMrks[1]/2)+(int)Math.ceil(AggMrks[2]/2)+
						 (int) Math.ceil(AggMrks[3]/2)+(int) Math.ceil(AggMrks[4]/2)+(int) Math.ceil(AggMrks[5]/2);
				int AvgTotal = AvgSum+EVSmrks;
		return AvgTotal;		
	}
	
	public void ENGMarks(){
		  
		 String[] subwithmarks = null;
    	 String plate[];
		 String ENGmarks = null, ENGTotal = null, line;	
		 int EngT2Marks = 0, ENGTotalT2 = 0;
		 for(int i=1; i < Model.strArray.size() ; i++)                      //  Model.strArray.size()
	     	{
	     	  plate=Model.strArray.get(i).split("#");

	     	   for (int j = 1; j < plate.length; j++){	
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
	

	public ArrayList<String> columnHeaderfinder(String RollNo){
		
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
//        show(View.Sub2.text);        
		if(View.Sub2.text.equals("PHY")) return Science;
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

	 public void ClearFailureTable()
	   {  int Rows = fl.TABLE.getRowCount();  show(Rows);
		  int cols = fl.TABLE.getColumnCount();       show(cols);
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
