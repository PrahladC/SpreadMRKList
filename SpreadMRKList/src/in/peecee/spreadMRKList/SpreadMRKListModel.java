package in.peecee.spreadMRKList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.JTableHeader;

public class SpreadMRKListModel {
	
	private int x;
	private String PrinterName, path, search ;
	JTable table;
	public SpreadMRKListView View;
	public void show(String msg) {JOptionPane.showMessageDialog(null, msg);}   ///for debugging
	public void Show(Object object) {JOptionPane.showMessageDialog(null, object);}   ///for debugging	
	public void Show(ArrayList<String> arrayList) {JOptionPane.showMessageDialog(null, arrayList);}   ///for debugging

	public void SetData(Object obj, int row_index, int col_index){View.getTable().getModel().setValueAt(obj,row_index,col_index);  }

	public  ArrayList<String> strArray = new ArrayList<String>();
	public  ArrayList<String> subjectsArray = new ArrayList<String>();

    public SpreadMRKListModel(){
       //  To Do - Constructor
    }    

	public String getJarPath()
	    {
	    	File f = new File(System.getProperty("java.class.path"));
	     	File dir = f.getAbsoluteFile().getParentFile();
	        path=dir.toString();
	     	return  path;
	    }
	
	public void setPrinterName(String printername)
    {
       this.PrinterName = printername;
    }
	  
	public String getSearch(){
	     	return  search;
	    }
		 
	public JTable getTable(){
	     	return  table;
	    }	
	
	public void LoadData(){
	  
			String fyle = "";			
			JFileChooser chooser = new JFileChooser();
	        chooser.setMultiSelectionEnabled(true);
	        FileNameExtensionFilter filter = new FileNameExtensionFilter("Result View", "rlt");
	        chooser.setFileFilter(filter);
	        chooser.setCurrentDirectory(new File("E:/Eclipse/Prahlad/Test Entries"));
	        chooser.setCurrentDirectory(new File("/home/prahallad/Test Entries"));
	        chooser.setCurrentDirectory(new File("/home/student/Test Entries"));   
	       
	        int option = chooser.showOpenDialog(null);
	        
	        if (option == JFileChooser.APPROVE_OPTION)
	         {
	            File[] sf = chooser.getSelectedFiles();
	            String filelist = "nothing";
	            if (sf.length > 0) filelist = sf[0].getName();
	            for (int i = 1; i < sf.length; i++) 
	            {
	            filelist += ", " + sf[i].getName();
	             }
	          fyle=sf[0].getPath();
	         if (!fyle.endsWith(".rlt")) fyle+= ".rlt";			
	         ReadFromDisk(fyle);
	      }  
	  } 
	
	public  void ReadFromDisk(String fnem)
    {   strArray.removeAll(strArray);
    	BufferedReader reader=null;
		try { 
			reader = new BufferedReader(new FileReader(fnem));
		} catch (FileNotFoundException e1) 
		{		
			e1.printStackTrace();
		}
 		
		String line = null;
    	try { while ((line = reader.readLine()) != null) 
			{
			 strArray.add(line);
			}
		} catch (IOException e) { e.printStackTrace(); } 
     }    	

	
	public void ENGMarks(){
		  
		 String[] subwithmarks = null;
  	     String plate[];
		 String ENGmarks = null, ENGTotal = null, line;	
		 int EngT2Marks = 0, ENGTotalT2 = 0;
		 for(int i=1; i < strArray.size() ; i++)                      //  strArray.size()
	     	{ 
//			 Show(strArray.size());
	     	  plate = strArray.get(i).split("#");
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
		     		   SetData(ENGmarks, i-1,5);
		     		   }	
		     		
		     		if(ENGmarks.contains("U2=ENG")){
		     			ENGmarks = subwithmarks[1];
		     			SetData(ENGmarks, i-1,6);
		     			}
		     		
		     		if(ENGmarks.contains("T2=ENG")){
		     			ENGmarks = subwithmarks[1].trim();
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
		 for(int i=1; i < strArray.size() ; i++)                      //  strArray.size()
	     	{
	     	  plate = strArray.get(i).split("#");
//	     	  show(plate);
	     	   for (int j = 1; j < plate.length; j++){	
	     		    line = plate[j];
	     		    subwithmarks = line.split(":");
			    	SLITmarks = plate[j].substring(2, 8);     //   show( submarksU1); 
		     		 if(SLITmarks.contains("U1=MAR")){
		     			SLITmarks = subwithmarks[1];	
		     			SetData(SLITmarks, i-1, 8);                 		     			
		     			}	  		     		 		    
		     		 if(SLITmarks.contains("U1=ITE")){
			     			SLITmarks = subwithmarks[1];	
			     			SetData(SLITmarks, i-1, 8);                 		     			
			     			}	
		     		if(SLITmarks.contains("U1=HIN")){
		     			SLITmarks = subwithmarks[1];	
		     			SetData(SLITmarks, i-1, 8);                 		     			
		     			}	
		     		if(SLITmarks.contains("U1=TAM")){
		     			SLITmarks = subwithmarks[1];	
		     			SetData(SLITmarks, i-1, 8);                 		     			
		     			}
		     		
		     		if(SLITmarks.contains("T1=MAR")){
		     			SLITmarks = subwithmarks[1];	
		     			SetData(SLITmarks, i-1, 9);                 		     			
		     			}	  		     		 		    
		     		 if(SLITmarks.contains("T1=ITE")){
			     			SLITmarks = subwithmarks[1];	
			     			SetData(SLITmarks, i-1, 9);                 		     			
			     			}	
		     		if(SLITmarks.contains("T1=HIN")){
		     			SLITmarks = subwithmarks[1];	
		     			SetData(SLITmarks, i-1, 9);                 		     			
		     			}	
		     		if(SLITmarks.contains("T1=TAM")){
		     			SLITmarks = subwithmarks[1];	
		     			SetData(SLITmarks, i-1, 9);                 		     			
		     			}	
		     		
		     		if(SLITmarks.contains("U2=MAR")){
		     			SLITmarks = subwithmarks[1];	
		     			SetData(SLITmarks, i-1, 10);                 		     			
		     			}	  		     		 		    
		     		 if(SLITmarks.contains("U2=ITE")){
			     			SLITmarks = subwithmarks[1];	
			     			SetData(SLITmarks, i-1, 10);                 		     			
			     			}	
		     		if(SLITmarks.contains("U2=HIN")){
		     			SLITmarks = subwithmarks[1];	
		     			SetData(SLITmarks, i-1, 10);                 		     			
		     			}	
		     		if(SLITmarks.contains("U2=TAM")){
		     			SLITmarks = subwithmarks[1];	
		     			SetData(SLITmarks, i-1, 10);                 		     			
		     			}	
		     		if(SLITmarks.contains("T2=MAR")){
		     			SLITmarks = subwithmarks[1].trim();	
		     			if(SLITmarks == null || SLITmarks.isEmpty()){ SLITmarks = "00"; }					 
						if(SLITmarks.contentEquals("AB") || SLITmarks.contentEquals("AB ")){ SLITmarks = "00"; }
						   MART2marks = Integer.parseInt(SLITmarks);  // show(SLITT2marks);	
						   MARTotalT2 =  MARTotalT2 + MART2marks; 
						   MARTotal = Integer.toString(MARTotalT2);
		     			   SetData(MARTotal, i-1, 11);                 		     			
		     			 }		     		
		     		 if(SLITmarks.contains("T2=ITE")){
			     		SLITmarks = subwithmarks[1].trim();	
			     		if(SLITmarks == null || SLITmarks.isEmpty()){ SLITmarks = "00"; }					 
						if(SLITmarks.contentEquals("AB") || SLITmarks.contentEquals("AB ")){ SLITmarks = "00"; }
						ITET2marks = Integer.parseInt(SLITmarks);    //      show(ITET2marks);
			     		ITETotalT2 = ITETotalT2 + ITET2marks; 
			     		ITETotal = Integer.toString(ITETotalT2);			     			     		
			     		SetData(ITETotal, i-1, 11);                 		     			
			     		}	
		     		if(SLITmarks.contains("T2=HIN")){
		     			SLITmarks = subwithmarks[1].trim();	
		     			if(SLITmarks == null || SLITmarks.isEmpty()){ SLITmarks = "00"; }					 
						if(SLITmarks.contentEquals("AB") || SLITmarks.contentEquals("AB ")){ SLITmarks = "00"; }
						HINT2marks = Integer.parseInt(SLITmarks);    //      show(HINT2marks);
						HINTotalT2 = HINTotalT2 + HINT2marks; 
						HINTotal = Integer.toString(HINTotalT2);		     			
		     			SetData(HINTotal, i-1, 11);                 		     			
		     			}	
		     		if(SLITmarks.contains("T2=TAM")){
		     			SLITmarks = subwithmarks[1].trim();	
		     			if(SLITmarks == null || SLITmarks.isEmpty()){ SLITmarks = "00"; }					 
						if(SLITmarks.contentEquals("AB") || SLITmarks.contentEquals("AB ")){ SLITmarks = "00"; }
						TAMT2marks = Integer.parseInt(SLITmarks);    //      show(TAMT2marks);
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
		 for(int i=1; i < strArray.size() ; i++)                      //  strArray.size()
	     	{
			   plate = strArray.get(i).split("#");
			   for (int j = 1; j < plate.length; j++){	
				 
				  line = plate[j];
			      VocationalMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
			      subwithmarks = line.split(":");
				   if(VocationalMarks.contains("U1=EL1")){
		     			VocationalMarks = subwithmarks[1];	
		     			SetData(VocationalMarks, i-1, 8);                 		     			
		     			}	
		     	   if(VocationalMarks.contains("T1=EL1")){
		     			VocationalMarks = subwithmarks[1];	
		     			SetData(VocationalMarks, i-1, 9);                 		     			
		     			}	 
				   if(VocationalMarks.contains("U2=EL1")){
		     			VocationalMarks = subwithmarks[1];	
		     			SetData(VocationalMarks, i-1, 10);                 		     			
		     			}	
		     		if(VocationalMarks.contains("T2=EL1")){
		     			VocationalMarks = subwithmarks[1].trim();	
		     			if(VocationalMarks == null || VocationalMarks.isEmpty()){ VocationalMarks = "00"; }					 
						if(VocationalMarks.contentEquals("AB") || VocationalMarks.contentEquals("AB ")){ VocationalMarks = "00"; }
						EL1T2marks = Integer.parseInt(VocationalMarks);  //         show(EL1T2marks);
						EL1TotalT2 = EL1TotalT2 + EL1T2marks; 
						EL1Total = Integer.toString(EL1TotalT2);	
						SetData(EL1Total, i-1, 11);                 		     			
		     			}
		     		if(VocationalMarks.contains("U1=CS1")){
		     			VocationalMarks = subwithmarks[1];	
		     			SetData(VocationalMarks, i-1, 8);                 		     			
		     			}	
		     		if(VocationalMarks.contains("T1=CS1")){
		     			VocationalMarks = subwithmarks[1];	
		     			SetData(VocationalMarks, i-1, 9);                 		     			
		     			}	 
				   if(VocationalMarks.contains("U2=CS1")){
		     			VocationalMarks = subwithmarks[1];	
		     			SetData(VocationalMarks, i-1, 10);                 		     			
		     			}	
		     		if(VocationalMarks.contains("T2=CS1")){
		     			VocationalMarks = subwithmarks[1].trim();	
		     			if(VocationalMarks == null || VocationalMarks.isEmpty()){ VocationalMarks = "00"; }					 
						if(VocationalMarks.contentEquals("AB") || VocationalMarks.contentEquals("AB ")){ VocationalMarks = "00"; }
						CS1T2marks = Integer.parseInt(VocationalMarks);   //       show(CS1T2marks);
						CS1TotalT2 = CS1TotalT2 + CS1T2marks; 
						CS1Total = Integer.toString(CS1TotalT2);				     						     			
		     			SetData(CS1Total, i-1, 11);                 		     			
		     			}	 
		     		
		     		if(VocationalMarks.contains("U1=EL2")){
		     			VocationalMarks = subwithmarks[1];	
		     			SetData(VocationalMarks, i-1, 12);                 		     			
		     			}	
		     		if(VocationalMarks.contains("T1=EL2")){
		     			VocationalMarks = subwithmarks[1];	
		     			SetData(VocationalMarks, i-1, 13);                 		     			
		     			}	 
				   if(VocationalMarks.contains("U2=EL2")){
		     			VocationalMarks = subwithmarks[1];	
		     			SetData(VocationalMarks, i-1, 14);                 		     			
		     			}	
		     		if(VocationalMarks.contains("T2=EL2")){
		     			VocationalMarks = subwithmarks[1].trim();	
		     			if(VocationalMarks == null || VocationalMarks.isEmpty()){ VocationalMarks = "00"; }					 
						if(VocationalMarks.contentEquals("AB") || VocationalMarks.contentEquals("AB ")){ VocationalMarks = "00"; }
						EL2T2marks = Integer.parseInt(VocationalMarks);   //       show(EL2T2marks);
						EL2TotalT2 = EL2TotalT2 + EL2T2marks; 
						EL2Total = Integer.toString(EL2TotalT2);				     						     						     			
		     			SetData(EL2Total, i-1, 15);                 		     			
		     			}
		     		if(VocationalMarks.contains("U1=CS2")){
		     			VocationalMarks = subwithmarks[1];	
		     			SetData(VocationalMarks, i-1, 12);                 		     			
		     			}	
		     		if(VocationalMarks.contains("T1=CS2")){
		     			VocationalMarks = subwithmarks[1];	
		     			SetData(VocationalMarks, i-1, 13);                 		     			
		     			}	 
				   if(VocationalMarks.contains("U2=CS2")){
		     			VocationalMarks = subwithmarks[1];	
		     			SetData(VocationalMarks, i-1, 14);                 		     			
		     			}	
		     		if(VocationalMarks.contains("T2=CS2")){
		     			VocationalMarks = subwithmarks[1].trim();	
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
		 View.SL.text = "SL / ITE / EL1 / CS1";
    }

	public void BIOMarks(){
		 String[] subwithmarks = null;
	   	 String plate[];
		 String BIOMarks = null, BIOTotal = null, line;
		 String EcoMrk = null, BioMrk = null, lastTwoT2BIO = null, lastTwoT2BIOOPP = null;
		 ArrayList<String> subjects;
		 subjects = ListOfSubjects();
		 int BIOT2marks = 0, BIOTotalT2 = 0, BIOT2OPP = 0;
		 for(int i=1; i < strArray.size() ; i++)                      //  strArray.size()
	     	{
			 plate = strArray.get(i).split("#");
	     	   for (int j = 1; j < plate.length; j++){		     		   
				 line = plate[j];
				 subwithmarks = line.split(":");    //  Show(subwithmarks);
				 BIOMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
				 if(subwithmarks[0].contains("ECO")){ 
					for(int k = 0; k < plate.length; k++){
				  		BIOMarks = plate[k].substring(0);
				   		if(BIOMarks.contains("U1=BIO")){ SetData(BIOMarks.substring(BIOMarks.length()-2), i-1, 24);}
				   		if(BIOMarks.contains("T1=BIO")){ SetData(BIOMarks.substring(BIOMarks.length()-2), i-1, 25);}
				   		if(BIOMarks.contains("U2=BIO")){ SetData(BIOMarks.substring(BIOMarks.length()-2), i-1, 26);}
				   		if(BIOMarks.contains("T2=BIO")){ 
				   			lastTwoT2BIO = BIOMarks.substring(BIOMarks.length()-2);
			     			if(lastTwoT2BIO == null || lastTwoT2BIO.isEmpty()){ lastTwoT2BIO = "00"; }					 
							if(lastTwoT2BIO.contentEquals("AB") || lastTwoT2BIO.contentEquals("AB ")){ lastTwoT2BIO = "00"; }
				   			BIOT2marks = Integer.parseInt(lastTwoT2BIO);
				   			BIOTotalT2 = BIOTotalT2 + BIOT2marks;
	//			   			show("Marks of Bio T2 is : " + BIOTotalT2);
				    	}
				   		SetData(String.valueOf(BIOTotalT2), i-1, 27);
					  } 
					break;
				   }
				      
					   if(BIOMarks.contains("U1=BIO")){
			     			BIOMarks = subwithmarks[1];	
//			     			show(BIOMarks);
			     			SetData(BIOMarks, i-1, 12);                 		     			
			     			}
 					   
			     		if(BIOMarks.contains("T1=BIO")){
			     			BIOMarks = subwithmarks[1];	
			     			SetData(BIOMarks, i-1, 13);                 		     			
			     			}	 
					   if(BIOMarks.contains("U2=BIO")){
			     			BIOMarks = subwithmarks[1];	
			     			SetData(BIOMarks, i-1, 14);                 		     			
			     			}	
			     		if(BIOMarks.contains("T2=BIO")){
			     			BIOMarks = subwithmarks[1].trim();	
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
		 View.Sub1.text = "ECO / BIO / EL2 / CS2";
	}

	public void ECOMarks(){
		 String[] subwithmarks = null;
	   	 String plate[];
		 String ECOMarks = null, ECOTotal = null,line;	
		 int ECOT2marks = 0, ECOTotalT2 = 0;
		 for(int i=1; i < strArray.size() ; i++)                      //  Model.strArray.size()
	     	{
			 plate = strArray.get(i).split("#");
	     	   for (int j = 1; j < plate.length; j++){		     		  
				  line = plate[j];
				  subwithmarks = line.split(":");
				  ECOMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
					if(ECOMarks.contains("U1=ECO")){
			     	   ECOMarks = subwithmarks[1];	
			     	   SetData(ECOMarks, i-1, 12);                 		     			
			     	 }	
			     	if(ECOMarks.contains("T1=ECO")){
			     	   ECOMarks = subwithmarks[1];	
			     	   SetData(ECOMarks, i-1, 13);                 		     			
			     	 }	 
					if(ECOMarks.contains("U2=ECO")){
			     	   ECOMarks = subwithmarks[1];	
			     	   SetData(ECOMarks, i-1, 14);                 		     			
			     	 }	
			     	if(ECOMarks.contains("T2=ECO")){
			     	   ECOMarks = subwithmarks[1].trim();	
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

	
	public void BIO_ECOCombo(String Bio, String Eco){
		 String[] biomarks = null, ecomarks = null ;
	   	 String plate[];
		 String ECOMarks = null, BIOMarks = null, ECOTotal = null,line;	
		 int ECOT2marks = 0, ECOTotalT2 = 0;
		 for(int i=1; i < strArray.size() ; i++)                      //  Model.strArray.size()
	     	{
			 plate = strArray.get(i).split("#");
	     	   for (int j = 1; j < plate.length; j++){		     		  
				  line = plate[j];
				  biomarks = line.split(":");
				  ecomarks = line.split(":");
				  ECOMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
				  BIOMarks = plate[j].substring(2, 8);
 				  if(BIOMarks.contains("U1=BIO")){BIOMarks = biomarks[1];  Bio = BIOMarks;}
 				  if(ECOMarks.contains("U1=ECO")){ECOMarks = ecomarks[1];  Eco = ECOMarks;}
 				  
// 				  if(BIOMarks.contains("T1=BIO")){BIOMarks = biomarks[1];  Bio = BIOMarks;}
// 				  if(ECOMarks.contains("T1=ECO")){ECOMarks = ecomarks[1]; Eco = ECOMarks;}

			 
	     	   }
	  		 show("Roll No : " + i + " has Biology Marks of U1 as : " + Bio);
			 show("Roll No : " + i + " has Economics Marks of U1 as : " + Eco);
//	     	   SetData(Bio, i-1, 24);

			 Bio = null;   Eco = null;
	     	}
                 
		
	}
	
	public void BKEMarks(){
		 String[] subwithmarks = null;
	   	 String plate[];
		 String BKEMarks = null, BKETotal = null, line;	
		 int BKET2marks = 0, BKETotalT2 = 0;
		 for(int i=1; i < strArray.size() ; i++)                      //  Model.strArray.size()
	     	{
			 plate = strArray.get(i).split("#");
//	     	  show(plate);
	     	   for (int j = 1; j < plate.length; j++){		     		  
				  line = plate[j];
				  subwithmarks = line.split(":");
				  BKEMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
					if(BKEMarks.contains("U1=BKE")){			     			
			     	   BKEMarks = subwithmarks[1];	
			     	   SetData(BKEMarks, i-1, 16);                 		     			
			     	 }	
			     	if(BKEMarks.contains("T1=BKE")){
			     	   BKEMarks = subwithmarks[1];	
			     	   SetData(BKEMarks, i-1, 17);                 		     			
			     	 }	 
					if(BKEMarks.contains("U2=BKE")){
 	     			   BKEMarks = subwithmarks[1];	
     	     		   SetData(BKEMarks, i-1, 18);                 		     			
			         }	
			     	if(BKEMarks.contains("T2=BKE")){
			     	   BKEMarks = subwithmarks[1].trim();	
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
		 for(int i=1; i < strArray.size() ; i++)                      //  Model.strArray.size()
	     	{
			 plate = strArray.get(i).split("#");
//	     	  show(plate);
	     	   for (int j = 1; j < plate.length; j++){				     		   
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
			     	  PHYMarks = subwithmarks[1].trim();	
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
		 for(int i=1; i < strArray.size() ; i++)                      //  Model.strArray.size()
	     	{
			 plate = strArray.get(i).split("#");
//	     	  show(plate);
	     	   for (int j = 1; j < plate.length; j++){		     		   
                line = plate[j];
                subwithmarks = line.split(":");
				 OCMMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
				 if(OCMMarks.contains("U1=OCM")){			     		
			     	OCMMarks = subwithmarks[1];	
			     	SetData(OCMMarks, i-1, 20);                 		     			
			      }	
			     if(OCMMarks.contains("T1=OCM")){
			     	OCMMarks = subwithmarks[1];	
			     	SetData(OCMMarks, i-1, 21);                 		     			
			      }	 
				 if(OCMMarks.contains("U2=OCM")){
			     	OCMMarks = subwithmarks[1];	
			     	SetData(OCMMarks, i-1, 22);                 		     			
			      }	
			     if(OCMMarks.contains("T2=OCM")){
			     	OCMMarks = subwithmarks[1].trim();	
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
		 for(int i=1; i < strArray.size() ; i++)                      //  Model.strArray.size()
	       {
			plate = strArray.get(i).split("#");
	     	   for (int j = 1; j < plate.length; j++){		     		   
				line = plate[j];
				subwithmarks = line.split(":");
				CHEMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
				  if(CHEMarks.contains("U1=CHE")){
			     	 CHEMarks = subwithmarks[1];	
			     	 SetData(CHEMarks, i-1, 20);                 		     			
			   		}	
			      if(CHEMarks.contains("T1=CHE")){
			     	 CHEMarks = subwithmarks[1];	
			     	 SetData(CHEMarks, i-1, 21);                 		     			
			     	}	 
				  if(CHEMarks.contains("U2=CHE")){
			     	 CHEMarks = subwithmarks[1];	
			     	 SetData(CHEMarks, i-1, 22);                 		     			
			        }	
			      if(CHEMarks.contains("T2=CHE")){
			     	 CHEMarks = subwithmarks[1].trim();	
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
		 for(int i=1; i < strArray.size() ; i++)                      //  Model.strArray.size()
	     	{
			 plate = strArray.get(i).split("#");
	     	   for (int j = 1; j < plate.length; j++){	   		   
			     line = plate[j];
			     subwithmarks = line.split(":");
				 MATMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
			   if(MATMarks.contains("U1=MAT")){			        			
			      MATMarks = subwithmarks[1];	
			      SetData(MATMarks, i-1, 24);                 		     			
			     }	
			   if(MATMarks.contains("T1=MAT")){
			      MATMarks = subwithmarks[1];	
			      SetData(MATMarks, i-1, 25);                 		     			
			     }	 
			   if(MATMarks.contains("U2=MAT")){
			      MATMarks = subwithmarks[1];	
			      SetData(MATMarks, i-1, 26);                 		     			
			     }	
			   if(MATMarks.contains("T2=MAT")){
			      MATMarks = subwithmarks[1].trim();
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
		 for(int i=1; i < strArray.size() ; i++)                      //  Model.strArray.size()
	     	{
			 plate = strArray.get(i).split("#");
	     	  for (int j = 1; j < plate.length; j++){		     		   
				  line = plate[j];
				  subwithmarks = line.split(":");
				  SEPMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
			  if(SEPMarks.contains("U1=SEP")){
			     SEPMarks = subwithmarks[1];	
			     SetData(SEPMarks, i-1, 24);                 		     			
			    }	
			  if(SEPMarks.contains("T1=SEP")){
			     SEPMarks = subwithmarks[1];	
			     SetData(SEPMarks, i-1, 25);                 		     			
			    }	 
			 if(SEPMarks.contains("U2=SEP")){
			    SEPMarks = subwithmarks[1];	
			    SetData(SEPMarks, i-1, 26);                 		     			
			   }	
			 if(SEPMarks.contains("T2=SEP")){
			    SEPMarks = subwithmarks[1].trim();	
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
		 for(int i=1; i < strArray.size() ; i++)                      //  Model.strArray.size()
	     	{
			 plate = strArray.get(i).split("#");
	     	  for (int j = 1; j < plate.length; j++){		     		   
				  line = plate[j];
				  subwithmarks = line.split(":");
				  EVSMarks = plate[j].substring(2, 8);     //   show( submarksU1); 
			 if(EVSMarks.contains("T2=EVS")){
				 EVSMarks = subwithmarks[1].trim();	
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
		 for(int i=1; i < strArray.size() ; i++)                      //  Model.strArray.size()
	     	{
			 plate = strArray.get(i).split("#");
	     	  for (int j = 1; j < plate.length; j++){		     		   
				  line = plate[j];
				  subwithmarks = line.split(":");
				  PTEGrade = plate[j].substring(2, 8);     //   show( submarksU1); 
			 if(PTEGrade.contains("T2=PTE")){
				 PTEGrade = subwithmarks[1].trim();	
			 if(PTEGrade == null || PTEGrade.isEmpty()){ PTEGrade = "00"; }					 
			 if(PTEGrade.contentEquals("AB") || PTEGrade.contentEquals("AB ")){ PTEGrade = "00"; }
			    SetData(PTEGrade, i-1, 29);
	           }
	       }
	    }
     }
	
	public ArrayList<String> ListOfSubjects(){
//		 RNum = View.getTable().getRowCount()-1;
	     int row = View.getTable().getSelectedRow();
   	     if(row < 0){ row = 0; }

		 String RowNo = (String) View.getTable().getModel().getValueAt(row, 0);
		 int rowNo = Integer.parseInt(RowNo.trim());
		 String SubjectNames = null; 
		 String plate[];
		 plate = strArray.get(rowNo).split("#");	
		 subjectsArray.removeAll(subjectsArray);	
		   for (int i = 2; i < plate.length; i++) {			   		
			 if(plate[1].length() <= 14){ 
				SubjectNames = plate[i-1].substring(5, 8);	// plate(0) = Roll No, plate(1) = subjects string. 
				subjectsArray.add(SubjectNames);
			 }			     	    
			  else {
		  	    SubjectNames = plate[i].substring(5, 8);   	// plate(0) = Roll No, plate(1) = Name so i started with 2
				subjectsArray.add(SubjectNames);
			  }
		    }	  
		  Set<String> NewSubjectsArray = new HashSet<>();
		  NewSubjectsArray.addAll(subjectsArray);
		  subjectsArray.clear();
		  subjectsArray.addAll(NewSubjectsArray); 
//		  Show(subjectsArray);
//		  Show(subjectsArray.get(4));
		  		  
		  return subjectsArray;
	
	}
	
	public void marksEntry(String subject){
	     int row = View.getTable().getSelectedRow();
   	     if(row < 0){ row = 0; }
		 ArrayList<String> subjects;
		 subjects = ListOfSubjects();
//         Show(subjects);
//         if(subjects.contains(subject)){
//        	 show("U1="+subject);
//         }
//         else show("There is no such subject");         
//         #I=U1=ENG:17
  	     String plate[]; 
  	     for(int i = row; i < 5+row; i++){
	    	 plate = strArray.get(i).split("#");
	    	 Show(plate);
  	     }
	}

	public void marksFill(String subject){
		 String[] subwithmarks = null;
 	     String plate[];
		 String subjectmarks = null, subjectMarksTotal = null, line;	
		 int subject_T2Marks = 0, subjectTotalT2 = 0, k = 0;
		 if(subject.contains("ENG"))k = 0;

		 if(subject.contains("HIN"))k = 4;
		 if(subject.contains("MAR"))k = 4;
		 if(subject.contains("TAM"))k = 4;
		 if(subject.contains("ITE"))k = 4;
		 if(subject.contains("EL1"))k = 4;
		 if(subject.contains("CS1"))k = 4;

		 if(subject.contains("BIO"))k = 8;
		 if(subject.contains("ECO"))k = 8;
		 
		 if(subject.contains("PHY"))k = 12;
		 if(subject.contains("BKE"))k = 12;
		 
		 if(subject.contains("OCM"))k = 16;
		 if(subject.contains("CHE"))k = 16;
		 
		 if(subject.contains("SEP"))k = 20;
		 if(subject.contains("MAT"))k = 20;

		 
		 for(int i=1; i < strArray.size() ; i++)                      //  strArray.size()
	     	{ 
//			 Show(strArray.size());
	     	  plate = strArray.get(i).split("#");
	     	   for (int j = 2; j < plate.length; j++){	
	     		   line = plate[j];
		     	   subjectmarks = plate[j].substring(2, 8);     //   show( submarksU1);
		     	   subwithmarks = line.split(":");
		     		 if(subjectmarks.contains("U1="+subject)){
		     			subjectmarks = subwithmarks[1];	
		     			SetData(subjectmarks, i-1, k+4);
		     			}	                         
		     		 
		     		if(subjectmarks.contains("T1="+subject)){
		     		   subjectmarks = subwithmarks[1];
		     		   SetData(subjectmarks, i-1,k+5);
		     		   }	
		     		
		     		if(subjectmarks.contains("U2="+subject)){
		     			subjectmarks = subwithmarks[1];
		     			SetData(subjectmarks, i-1,k+6);
		     			}
		     		
		     		if(subjectmarks.contains("T2="+subject)){
		     			subjectmarks = subwithmarks[1].trim();
		     			if(subjectmarks == null || subjectmarks.isEmpty()){ subjectmarks = "00"; }					 
						if(subjectmarks.contentEquals("AB") || subjectmarks.contentEquals("AB ")){ subjectmarks = "00"; }
		     			subject_T2Marks = Integer.parseInt(subjectmarks); //  show(EngT2Marks);	
		     			subjectTotalT2 =  subjectTotalT2 + subject_T2Marks;   
		     			subjectMarksTotal = Integer.toString(subjectTotalT2);	
		     			SetData(subjectMarksTotal, i-1,k+7);
		     			}			     		
 	         }   		
	     	         subjectTotalT2 = 0;
     }		
  	     JTableHeader th = View.getTable().getTableHeader();          //  For header changing dynamically
 	     th.repaint();                                          
 	     View.Eng.text="ENG";                       
	}

	
  }