package in.peecee.spreadMRKList;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

public class subjecToppers {
	
	private SpreadMRKListView View;
	private SpreadMRKListModel Model;
	private SpreadMRKListController SMRKC;
	private showstatistics Stats = new showstatistics();
	
	public  ArrayList<String> headerArray = new ArrayList<String>();
	public  ArrayList<String> tempArray = new ArrayList<String>();

	public void Show(int num) {JOptionPane.showMessageDialog(null, num);}   ///for debugging
	public void show(ArrayList<String> msg) {JOptionPane.showMessageDialog(null, msg);}   ///for debugging
	public void show(String msg) {JOptionPane.showMessageDialog(null, msg);}   ///for debugging
	public void show1(String[] msg) {JOptionPane.showMessageDialog(null, msg);}   ///for debugging

	
    public void SetData(Object obj, int row_index, int col_index){View.getTable().getModel().setValueAt(obj,row_index,col_index);}
    public void SetData1(Object obj, int row_index, int col_index){Stats.Table1().setValueAt(obj,row_index,col_index);  }
    public Object GetData(JTable table, int row_index, int col_index)  { return View.getTable().getValueAt(row_index, col_index); }
    public String GetData1(JTable table, int row_index, int col_index) { return (String) View.getTable().getValueAt(row_index, col_index); }
	
	
    
	public int SUB1Topper(SpreadMRKListView view, SpreadMRKListModel model)
	{   
		View = view;
		Model = model;
		int indix = 0, rowcount = 0;
		rowcount = 10;
        int[] sub1Total;
		sub1Total = new int [rowcount];
  	  for(int row = 0; row < rowcount; row++){
		  String RollNo = GetData(View.getTable(), row,1).toString().trim();
	      String Stream = Streamfinder(String.valueOf(RollNo));
	      if(Stream.contentEquals("SCIENCE")) continue;	     
	      sub1Total[indix] = Sub1(row);
		  indix++;
	  }	
		int i;
		int large = 0;  
		int max = 0, index;
		     max = sub1Total[0];
		     index = 0; 
		  for (i = 1; i < sub1Total.length; i++) {
		    if (max < sub1Total[i]) {
		        max = sub1Total[i];
		        index = i;
		       }
		    }
		  
			large = max;
			sub1Total[index] = Integer.MIN_VALUE;
			String RollNo = GetData(View.getTable(), index, 1).toString().trim();
            show("Index is "+index);
//			show("Roll Nimber : "+RollNo+" has got Highest marks "+large);       
//		    Stats.SetData(large, 2, 2);
			return large;
		    
	}
	
	public void SciToppers(SpreadMRKListView view, SpreadMRKListModel model){
		
		View = view;
		Model = model;
		int rows = View.getTable().getRowCount();                     //    100;        
		int engCounter = 0, marCounter = 0, hinCounter = 0, tamCounter = 0, iteCounter = 0,
		    ecoCounter = 0, bioCounter = 0, phyCounter = 0, matCounter = 0, el1Counter = 0,
		    cs1Counter = 0, cheCounter = 0;
		
        for(int i = 0; i < rows; i++){
			String RollNo = GetData(View.getTable(), i, 1).toString().trim();
			ArrayList<String> Subject = columnHeaderfinder(RollNo);
			String stream = Streamfinder(RollNo); 
				
			if(stream.contains("SCIENCE") && Subject.get(0).contains("ENG")){ engCounter++;}
			if(stream.contains("SCIENCE") && Subject.get(1).contains("ITE")){ iteCounter++;}
			if(stream.contains("SCIENCE") && Subject.get(1).contains("MAR")){ marCounter++;}
			if(stream.contains("SCIENCE") && Subject.get(1).contains("HIN")){ hinCounter++;}
			if(stream.contains("SCIENCE") && Subject.get(1).contains("TAM")){ tamCounter++;}
			if(stream.contains("SCIENCE") && Subject.get(2).contains("ECO")){ ecoCounter++;}
			if(Subject.get(2).contains("BIO")){ bioCounter++;}
			if(Subject.get(1).contains("EL1")){ el1Counter++;}
			if(Subject.get(1).contains("CS1")){ cs1Counter++;}			
			if(Subject.get(3).contains("PHY")){ phyCounter++;}
			if(Subject.get(4).contains("CHE")){ cheCounter++;}
			if(stream.contains("SCIENCE") && Subject.get(5).contains("MAT")){ matCounter++;}

        }
        show("SC - ENG : " + engCounter);   show("SC - MAR : " + marCounter);   show("SC - HIN : " + hinCounter);
        show("SC - TAM : " + tamCounter);   show("SC - ITE : " + iteCounter);   show("Sc - ECO : " + ecoCounter);
        show("BIO : " + bioCounter);   show("EL1 : " + el1Counter);   show("CS1 : " + cs1Counter);
        show("PHY : " + phyCounter);   show("CHE : " + cheCounter);   show("MAT : " + matCounter);   
	}

	
	public void ComToppers(SpreadMRKListView view, SpreadMRKListModel model){
		
		View = view;
		Model = model;
		int rows = View.getTable().getRowCount();                     //    100;        
		int engCounter = 0, marCounter = 0, hinCounter = 0, tamCounter = 0, iteCounter = 0,
		    ecoCounter = 0, bkeCounter = 0, ocmCounter = 0, matCounter = 0, sepCounter = 0;
        for(int i = 0; i < rows; i++){
			String RollNo = GetData(View.getTable(), i, 1).toString().trim();
			ArrayList<String> Subject = columnHeaderfinder(RollNo);
			String stream = Streamfinder(RollNo); 
//			show("Roll Number is : "+ RollNo +" Stream is : "+ stream + " and Subjects are " + Subject);
			
			if(stream.contains("COMMERCE") && Subject.get(0).contains("ENG")){ engCounter++;}
			if(stream.contains("COMMERCE") && Subject.get(1).contains("ITE")){ iteCounter++;}
			if(stream.contains("COMMERCE") && Subject.get(1).contains("MAR")){ marCounter++;}
			if(stream.contains("COMMERCE") && Subject.get(1).contains("HIN")){ hinCounter++;}
			if(stream.contains("COMMERCE") && Subject.get(1).contains("TAM")){ tamCounter++;}
			if(stream.contains("COMMERCE") && Subject.get(2).contains("ECO")){ ecoCounter++;}
			if(stream.contains("COMMERCE") && Subject.get(3).contains("BKE")){ bkeCounter++;}
			if(stream.contains("COMMERCE") && Subject.get(4).contains("OCM")){ ocmCounter++;}
			if(stream.contains("COMMERCE") && Subject.get(5).contains("MAT")){ matCounter++;}
			if(stream.contains("COMMERCE") && Subject.get(5).contains("SEP")){ sepCounter++;}

        }
        show("ENG : " + engCounter);   show("MAR : " + marCounter);   show("HIN : " + hinCounter);
        show("TAM : " + tamCounter);   show("ITE : " + iteCounter);   show("ECO : " + ecoCounter);
        show("BKE : " + bkeCounter);   show("OCM : " + ocmCounter);   show("MAT : "+  matCounter);
        show("SEP : " + sepCounter);
        
	}


	public int Sub1(int row) {
//		row = View.getTable().getSelectedRow();
		
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
	
	private String Streamfinder(String Rollno) {
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

}
