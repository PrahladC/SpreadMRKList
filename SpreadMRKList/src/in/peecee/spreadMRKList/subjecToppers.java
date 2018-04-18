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
	public  ArrayList<String> subjectName = new ArrayList<String>();


	public void Show(int num) {JOptionPane.showMessageDialog(null, num);}   ///for debugging
	public void Show(int[] totalMarks) {JOptionPane.showMessageDialog(null, totalMarks);}   ///for debugging
	public void show(ArrayList<String> msg) {JOptionPane.showMessageDialog(null, msg);}   ///for debugging
	public void show(String msg) {JOptionPane.showMessageDialog(null, msg);}   ///for debugging
	public void show1(String[] msg) {JOptionPane.showMessageDialog(null, msg);}   ///for debugging

	
    public void SetData(Object obj, int row_index, int col_index){View.getTable().getModel().setValueAt(obj,row_index,col_index);}
    public void SetData1(Object obj, int row_index, int col_index){Stats.Table1().setValueAt(obj,row_index,col_index);  }
    public Object GetData(JTable table, int row_index, int col_index)  { return View.getTable().getValueAt(row_index, col_index); }
    public String GetData1(JTable table, int row_index, int col_index) { return (String) View.getTable().getValueAt(row_index, col_index); }
	
	public int CengCounter = 0, CmarCounter = 0, ChinCounter = 0, CtamCounter = 0, CiteCounter = 0, CecoCounter = 0, 
		bioCounter = 0,  phyCounter = 0,  CmatCounter = 0, el1Counter = 0,  cs1Counter = 0,  cheCounter = 0,
	    SengCounter = 0, SmarCounter = 0, ShinCounter = 0, StamCounter = 0, SiteCounter = 0, SecoCounter = 0, 
		bkeCounter = 0,  ocmCounter = 0,  SmatCounter = 0, sepCounter = 0;
	
	public void setViewModel(SpreadMRKListView view, SpreadMRKListModel model){
		this.View = view;
		this.Model = model;
	}
	
	public void SubCounters(SpreadMRKListView view, SpreadMRKListModel model){
		View = view;
		Model = model;
		int rows = View.getTable().getRowCount();                     //    100;    
		String RollNo;
		String stream = null;
		ArrayList<String> Subject = null;

        for(int row = 0; row < rows; row++){
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
        }
		
        for(int row = 0; row < rows; row++){
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
			Subject = columnHeaderfinder(RollNo);
			stream = Streamfinder(RollNo); 
							
			if(stream.contains("COMMERCE") && Subject.get(0).contains("ENG")){CengCounter++; }
			if(stream.contains("COMMERCE") && Subject.get(1).contains("MAR")){CmarCounter++; }
			if(stream.contains("COMMERCE") && Subject.get(1).contains("ITE")){CiteCounter++; }
			if(stream.contains("COMMERCE") && Subject.get(1).contains("HIN")){ChinCounter++; }
			if(stream.contains("COMMERCE") && Subject.get(1).contains("TAM")){CtamCounter++; }
			if(stream.contains("COMMERCE") && Subject.get(2).contains("ECO")){CecoCounter++; }
			if(stream.contains("COMMERCE") && Subject.get(3).contains("BKE")){bkeCounter++;  }
			if(stream.contains("COMMERCE") && Subject.get(4).contains("OCM")){ocmCounter++;  }
			if(stream.contains("COMMERCE") && Subject.get(5).contains("SEP")){sepCounter++;  }
			if(stream.contains("COMMERCE") && Subject.get(5).contains("MAT")){CmatCounter++; }
			
			if(stream.contains("SCIENCE") && Subject.get(0).contains("ENG")){SengCounter++; }
			if(stream.contains("SCIENCE") && Subject.get(1).contains("MAR")){SmarCounter++; }
			if(stream.contains("SCIENCE") && Subject.get(1).contains("ITE")){SiteCounter++; }
			if(stream.contains("SCIENCE") && Subject.get(1).contains("HIN")){ShinCounter++; }
			if(stream.contains("SCIENCE") && Subject.get(1).contains("TAM")){StamCounter++; }
			if(stream.contains("SCIENCE") && Subject.get(1).contains("EL1")){el1Counter++;  }
			if(stream.contains("SCIENCE") && Subject.get(1).contains("CS1")){cs1Counter++;  }
			if(stream.contains("SCIENCE") && Subject.get(2).contains("BIO")){bioCounter++;  }
			if(stream.contains("SCIENCE") && Subject.get(2).contains("ECO")){SecoCounter++; }			
			if(stream.contains("SCIENCE") && Subject.get(3).contains("PHY")){phyCounter++;  }
			if(stream.contains("SCIENCE") && Subject.get(4).contains("CHE")){cheCounter++;  }
			if(stream.contains("SCIENCE") && Subject.get(5).contains("MAT")){SmatCounter++; }

        }
/*		show("ENG-C = " + CengCounter + " , MAR-C = "+CmarCounter+ " , ITE-C = "+CiteCounter+ " , HIN-C = "+ChinCounter
				+ " , TAM-C = "+CtamCounter+ " , ECO-C = "+CecoCounter+ " , BKE-C = "+bkeCounter
				+ " , OCM-C = "+ocmCounter+ " , SEP-C = "+sepCounter+ " , MAT-C = "+CmatCounter+"\n\n"
				+"ENG-S = "+SengCounter + " , MAR-S = "+SmarCounter + " , ITE-S = "+SiteCounter + " , HIN-S = "+ShinCounter
				+ " , TAM-S = "+StamCounter+ " , EL1-S = "+el1Counter+ " , CS1-S = "+cs1Counter
				+ " , BIO-S = "+bioCounter+ " , ECO-S = "+SecoCounter+ " , PHY-S = "+phyCounter+" ,"+"\n\n"
				+ "CHE-S = "+cheCounter+ " , MAT-S = "+SmatCounter);                  */
	}

	public void ScEngTopper(SpreadMRKListView view, SpreadMRKListModel model, showstatistics stats){
		View = view;   Model = model;   Stats = stats;
		int rows = View.getTable().getRowCount();                         
		String rollNo  = null, name  = null, div  = null, stream = null;
		int index = 0, maxEng = 0; int engMrk[] = new int [1000];   int rowArray[] = new int[1000];		
		String RollNo;
		ArrayList<String> Subject = null;
        for(int row = 0; row < rows; row++){ 
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
        }        
        for(int row = 0; row < rows; row++){
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
			Subject = columnHeaderfinder(RollNo);
			stream = Streamfinder(RollNo); 							
			if(stream.contains("SCIENCE") && Subject.get(0).contains("ENG")){ 
				engMrk[index] = Sub1(row);
				rowArray[index] = row;
				index++;	
				maxEng = engMrk[0];
				for(int i = 0; i < engMrk.length; i++){
					if(maxEng < engMrk[i]){ maxEng = engMrk[i]; 
					                     rollNo = GetData(View.getTable(), rowArray[i], 1).toString().trim();
				                         name = GetData(View.getTable(), rowArray[i], 3).toString().trim();
				                         div = GetData(View.getTable(), rowArray[i], 2).toString().trim();
				                        }
					      }	
			     }					
        }        
		Stats.SetData1("  "+ name, 11, 1);
        Stats.SetData1("  "+ rollNo, 11, 2);
        Stats.SetData1("  "+ div, 11, 3);
        Stats.SetData1("  "+ maxEng, 11, 4);
//        show("Name is "+name+" Whose Roll no is" +rollNo+" Division is "+div +" Score is "+maxEng +
 //        		" Subject is "+Subject.get(0));		
	}
	
	public void ScIteTopper(SpreadMRKListView view, SpreadMRKListModel model, showstatistics stats){
		View = view;   Model = model;   Stats = stats;
		int rows = View.getTable().getRowCount();                         
		String rollNo  = null, name  = null, div  = null, stream = null;
		int index = 0, maxIte = 0; int iteMrk[] = new int [1000];   int rowArray[] = new int[1000];		
		String RollNo, subject = null;
		ArrayList<String> Subject = null;
        for(int row = 0; row < rows; row++){ 
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
        }        
        for(int row = 0; row < rows; row++){
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
			Subject = columnHeaderfinder(RollNo);
			stream = Streamfinder(RollNo); 							
			if(stream.contains("SCIENCE") && Subject.get(1).contains("ITE")){ 
				iteMrk[index] = Sub2(row);
				rowArray[index] = row;
				index++;	
				maxIte = iteMrk[0];
				for(int i = 0; i < iteMrk.length; i++){
					if(maxIte < iteMrk[i]){ maxIte = iteMrk[i]; 
					                     rollNo = GetData(View.getTable(), rowArray[i], 1).toString().trim();
				                         name = GetData(View.getTable(), rowArray[i], 3).toString().trim();
				                         div = GetData(View.getTable(), rowArray[i], 2).toString().trim();
				                         subject = Subject.get(1);
				                        }
					      }	
			     }					
        }        
		Stats.SetData1("  "+ name, 12, 1);
        Stats.SetData1("  "+ rollNo, 12, 2);
        Stats.SetData1("  "+ div, 12, 3);
        Stats.SetData1("  "+ maxIte, 12, 4);
//        show("Name is "+name+" Whose Roll no is" +rollNo+" Division is "+div +" Score is "+maxIte +
//         		" Subject is "+subject);		
	}

	public void ScHinTopper(SpreadMRKListView view, SpreadMRKListModel model, showstatistics stats){
		View = view;   Model = model;   Stats = stats;
		int rows = View.getTable().getRowCount();                         
		String rollNo  = null, name  = null, div  = null, stream = null;
		int index = 0, maxHin = 0; int hinMrk[] = new int [1000];   int rowArray[] = new int[1000];		
		String RollNo, subject = null;
		ArrayList<String> Subject = null;
        for(int row = 0; row < rows; row++){ 
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
        }        
        for(int row = 0; row < rows; row++){
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
			Subject = columnHeaderfinder(RollNo);
			stream = Streamfinder(RollNo); 							
			if(stream.contains("SCIENCE") && Subject.get(1).contains("HIN")){ 
				hinMrk[index] = Sub2(row);
				rowArray[index] = row;
				index++;	
				maxHin = hinMrk[0];
				for(int i = 0; i < hinMrk.length; i++){
					if(maxHin < hinMrk[i]){ maxHin = hinMrk[i]; 
					                     rollNo = GetData(View.getTable(), rowArray[i], 1).toString().trim();
				                         name = GetData(View.getTable(), rowArray[i], 3).toString().trim();
				                         div = GetData(View.getTable(), rowArray[i], 2).toString().trim();
				                         subject = Subject.get(1);
				                        }
					      }	
			     }					
        }        
		Stats.SetData1("  "+ name, 13, 1);
        Stats.SetData1("  "+ rollNo, 13, 2);
        Stats.SetData1("  "+ div, 13, 3);
        Stats.SetData1("  "+ maxHin, 13, 4);
//        show("Name is "+name+" Whose Roll no is" +rollNo+" Division is "+div +" Score is "+maxHin +
//         		" Subject is "+subject);		
	}

	public void ScMarTopper(SpreadMRKListView view, SpreadMRKListModel model, showstatistics stats){
		View = view;   Model = model;   Stats = stats;
		int rows = View.getTable().getRowCount();                         
		String rollNo  = null, name  = null, div  = null, stream = null;
		int index = 0, maxMar = 0; int marMrk[] = new int [1000];   int rowArray[] = new int[1000];		
		String RollNo, subject = null;
		ArrayList<String> Subject = null;
        for(int row = 0; row < rows; row++){ 
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
        }        
        for(int row = 0; row < rows; row++){
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
			Subject = columnHeaderfinder(RollNo);
			stream = Streamfinder(RollNo); 							
			if(stream.contains("SCIENCE") && Subject.get(1).contains("MAR")){ 
				marMrk[index] = Sub2(row);
				rowArray[index] = row;
				index++;	
				maxMar = marMrk[0];
				for(int i = 0; i < marMrk.length; i++){
					if(maxMar < marMrk[i]){ maxMar = marMrk[i]; 
					                     rollNo = GetData(View.getTable(), rowArray[i], 1).toString().trim();
				                         name = GetData(View.getTable(), rowArray[i], 3).toString().trim();
				                         div = GetData(View.getTable(), rowArray[i], 2).toString().trim();
				                         subject = Subject.get(1);
				                        }
					      }	
			     }					
        }        
		Stats.SetData1("  "+ name, 14, 1);
        Stats.SetData1("  "+ rollNo, 14, 2);
        Stats.SetData1("  "+ div, 14, 3);
        Stats.SetData1("  "+ maxMar, 14, 4);
//        show("Name is "+name+" Whose Roll no is" +rollNo+" Division is "+div +" Score is "+maxMar +
//         		" Subject is "+subject);		
	}

	public void ScTamTopper(SpreadMRKListView view, SpreadMRKListModel model, showstatistics stats){
		View = view;   Model = model;   Stats = stats;
		int rows = View.getTable().getRowCount();                         
		String rollNo  = null, name  = null, div  = null, stream = null;
		int index = 0, maxTam = 0; int tamMrk[] = new int [1000];   int rowArray[] = new int[1000];		
		String RollNo, subject = null;
		ArrayList<String> Subject = null;
        for(int row = 0; row < rows; row++){ 
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
        }        
        for(int row = 0; row < rows; row++){
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
			Subject = columnHeaderfinder(RollNo);
			stream = Streamfinder(RollNo); 							
			if(stream.contains("SCIENCE") && Subject.get(1).contains("TAM")){ 
				tamMrk[index] = Sub2(row);
				rowArray[index] = row;
				index++;	
				maxTam = tamMrk[0];
				for(int i = 0; i < tamMrk.length; i++){
					if(maxTam < tamMrk[i]){ maxTam = tamMrk[i]; 
					                     rollNo = GetData(View.getTable(), rowArray[i], 1).toString().trim();
				                         name = GetData(View.getTable(), rowArray[i], 3).toString().trim();
				                         div = GetData(View.getTable(), rowArray[i], 2).toString().trim();
				                         subject = Subject.get(1);
				                        }
					      }	
			     }					
        }        
		Stats.SetData1("  "+ name, 15, 1);
        Stats.SetData1("  "+ rollNo, 15, 2);
        Stats.SetData1("  "+ div, 15, 3);
        Stats.SetData1("  "+ maxTam, 15, 4);
//        show("Name is "+name+" Whose Roll no is" +rollNo+" Division is "+div +" Score is "+maxTam +
//         		" Subject is "+subject);		
	}
	
	public void ScEcoTopper(SpreadMRKListView view, SpreadMRKListModel model, showstatistics stats){
		View = view;   Model = model;   Stats = stats;
		int rows = View.getTable().getRowCount();                         
		String rollNo  = null, name  = null, div  = null, stream = null;
		int index = 0, maxEco = 0; int ecoMrk[] = new int [1000];   int rowArray[] = new int[1000];		
		String RollNo, subject = null;
		ArrayList<String> Subject = null;
        for(int row = 0; row < rows; row++){ 
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
        }        
        for(int row = 0; row < rows; row++){
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
			Subject = columnHeaderfinder(RollNo);
			stream = Streamfinder(RollNo); 							
			if(stream.contains("SCIENCE") && Subject.get(2).contains("ECO")){ 
				ecoMrk[index] = Sub3(row);
				rowArray[index] = row;
				index++;	
				maxEco = ecoMrk[0];
				for(int i = 0; i < ecoMrk.length; i++){
					if(maxEco < ecoMrk[i]){ maxEco = ecoMrk[i]; 
					                     rollNo = GetData(View.getTable(), rowArray[i], 1).toString().trim();
				                         name = GetData(View.getTable(), rowArray[i], 3).toString().trim();
				                         div = GetData(View.getTable(), rowArray[i], 2).toString().trim();
				                         subject = Subject.get(2);  
				                        }
					      }	
			     }					
        }       
		Stats.SetData1("  "+ name, 16, 1);
        Stats.SetData1("  "+ rollNo, 16, 2);
        Stats.SetData1("  "+ div, 16, 3);
        Stats.SetData1("  "+ maxEco, 16, 4);
//        show("Name is "+name+" Whose Roll no is" +rollNo+" Division is "+div +" Score is "+maxEco +
//         		" Subject is "+subject);		
	}

	public void ScBioTopper(SpreadMRKListView view, SpreadMRKListModel model, showstatistics stats){
		View = view;   Model = model;   Stats = stats;
		int rows = View.getTable().getRowCount();                         
		String rollNo  = null, name  = null, div  = null, stream = null;
		int index = 0, maxBio = 0; int bioMrk[] = new int [1000];   int rowArray[] = new int[1000];		
		String RollNo, subject = null;
		ArrayList<String> Subject = null;
        for(int row = 0; row < rows; row++){ 
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
        }        
        for(int row = 0; row < rows; row++){
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
			Subject = columnHeaderfinder(RollNo);
			stream = Streamfinder(RollNo); 							
			if(stream.contains("SCIENCE") && Subject.get(2).contains("BIO")){ 
				bioMrk[index] = Sub3(row);
				rowArray[index] = row;
				index++;	
				maxBio = bioMrk[0];
				for(int i = 0; i < bioMrk.length; i++){
					if(maxBio < bioMrk[i]){ maxBio = bioMrk[i]; 
					                     rollNo = GetData(View.getTable(), rowArray[i], 1).toString().trim();
				                         name = GetData(View.getTable(), rowArray[i], 3).toString().trim();
				                         div = GetData(View.getTable(), rowArray[i], 2).toString().trim();
				                         subject = Subject.get(2);  
				                        }
					      }	
			     }					
        }       
		Stats.SetData1("  "+ name, 17, 1);
        Stats.SetData1("  "+ rollNo, 17, 2);
        Stats.SetData1("  "+ div, 17, 3);
        Stats.SetData1("  "+ maxBio, 17, 4);
//        show("Name is "+name+" Whose Roll no is" +rollNo+" Division is "+div +" Score is "+maxBio +
//         		" Subject is "+subject);		
	}
	
	public void ScPhyTopper(SpreadMRKListView view, SpreadMRKListModel model, showstatistics stats){
		View = view;   Model = model;   Stats = stats;
		int rows = View.getTable().getRowCount();                         
		String rollNo  = null, name  = null, div  = null, stream = null;
		int index = 0, maxPhy = 0; int phyMrk[] = new int [1000];   int rowArray[] = new int[1000];		
		String RollNo, subject = null;
		ArrayList<String> Subject = null;
        for(int row = 0; row < rows; row++){ 
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
        }        
        for(int row = 0; row < rows; row++){
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
			Subject = columnHeaderfinder(RollNo);
			stream = Streamfinder(RollNo); 							
			if(stream.contains("SCIENCE") && Subject.get(3).contains("PHY")){ 
				phyMrk[index] = Sub4(row);
				rowArray[index] = row;
				index++;	
				maxPhy = phyMrk[0];
				for(int i = 0; i < phyMrk.length; i++){
					if(maxPhy < phyMrk[i]){ maxPhy = phyMrk[i]; 
					                     rollNo = GetData(View.getTable(), rowArray[i], 1).toString().trim();
				                         name = GetData(View.getTable(), rowArray[i], 3).toString().trim();
				                         div = GetData(View.getTable(), rowArray[i], 2).toString().trim();
				                         subject = Subject.get(3);  
				                        }
					      }	
			     }					
        }       
		Stats.SetData1("  "+ name, 18, 1);
        Stats.SetData1("  "+ rollNo, 18, 2);
        Stats.SetData1("  "+ div, 18, 3);
        Stats.SetData1("  "+ maxPhy, 18, 4);
//        show("Name is "+name+" Whose Roll no is" +rollNo+" Division is "+div +" Score is "+maxPhy +
//         		" Subject is "+subject);		
	}

	public void ScCheTopper(SpreadMRKListView view, SpreadMRKListModel model, showstatistics stats){
		View = view;   Model = model;   Stats = stats;
		int rows = View.getTable().getRowCount();                         
		String rollNo  = null, name  = null, div  = null, stream = null;
		int index = 0, maxChe = 0; int cheMrk[] = new int [1000];   int rowArray[] = new int[1000];		
		String RollNo, subject = null;
		ArrayList<String> Subject = null;
        for(int row = 0; row < rows; row++){ 
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
        }        
        for(int row = 0; row < rows; row++){
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
			Subject = columnHeaderfinder(RollNo);
			stream = Streamfinder(RollNo); 							
			if(stream.contains("SCIENCE") && Subject.get(4).contains("CHE")){ 
				cheMrk[index] = Sub5(row);
				rowArray[index] = row;
				index++;	
				maxChe = cheMrk[0];
				for(int i = 0; i < cheMrk.length; i++){
					if(maxChe < cheMrk[i]){ maxChe = cheMrk[i]; 
					                     rollNo = GetData(View.getTable(), rowArray[i], 1).toString().trim();
				                         name = GetData(View.getTable(), rowArray[i], 3).toString().trim();
				                         div = GetData(View.getTable(), rowArray[i], 2).toString().trim();
				                         subject = Subject.get(4);  
				                        }
					      }	
			     }					
        }       
		Stats.SetData1("  "+ name, 19, 1);
        Stats.SetData1("  "+ rollNo, 19, 2);
        Stats.SetData1("  "+ div, 19, 3);
        Stats.SetData1("  "+ maxChe, 19, 4);
// show("Name is "+name+" Whose Roll no is" +rollNo+" Division is "+div +" Score is "+maxChe +" Subject is "+subject);		
	}

	public void ScMatTopper(SpreadMRKListView view, SpreadMRKListModel model, showstatistics stats){
		View = view;   Model = model;   Stats = stats;
		int rows = View.getTable().getRowCount();                         
		String rollNo  = null, name  = null, div  = null, stream = null;
		int index = 0, maxMat = 0; int matMrk[] = new int [1000];   int rowArray[] = new int[1000];		
		String RollNo, subject = null;
		ArrayList<String> Subject = null;
        for(int row = 0; row < rows; row++){ 
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
        }        
        for(int row = 0; row < rows; row++){
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
			Subject = columnHeaderfinder(RollNo);
			stream = Streamfinder(RollNo); 							
			if(stream.contains("SCIENCE") && Subject.get(5).contains("MAT")){ 
				matMrk[index] = Sub6(row);
				rowArray[index] = row;
				index++;	
				maxMat = matMrk[0];
				for(int i = 0; i < matMrk.length; i++){
					if(maxMat < matMrk[i]){ maxMat = matMrk[i]; 
					                     rollNo = GetData(View.getTable(), rowArray[i], 1).toString().trim();
				                         name = GetData(View.getTable(), rowArray[i], 3).toString().trim();
				                         div = GetData(View.getTable(), rowArray[i], 2).toString().trim();
				                         subject = Subject.get(5);  
				                        }
					      }	
			     }					
        }       
		Stats.SetData1("  "+ name, 20, 1);
        Stats.SetData1("  "+ rollNo, 20, 2);
        Stats.SetData1("  "+ div, 20, 3);
        Stats.SetData1("  "+ maxMat, 20, 4);
//   show("Name is "+name+" Whose Roll no is" +rollNo+" Division is "+div +" Score is "+maxMat +" Subject is "+subject);
	}

	
	public void ElectronicsTopper(SpreadMRKListView view, SpreadMRKListModel model, showstatistics stats){
		View = view;   Model = model;   Stats = stats;		
	    int rows = View.getTable().getRowCount(); 
		int rowcount = View.getTable().getRowCount();
		String rollNo  = null, name  = null, div  = null, stream = null;
		int index = 0, maxElectronics = 0; int electronicsMrk[] = new int [1000];   int rowArray[] = new int[1000];		
		String RollNo, subject = null;
		ArrayList<String> Subject = null;

		for(int row = 0; row < rowcount; row++){
		   RollNo = GetData1(View.getTable(), row, 1);
		   subjectName = columnHeaderfinder(RollNo);
		   if(!subjectName.contains("EL1") || !subjectName.contains("EL2")) continue;
		      electronicsMrk[index] = ElectronicSum(row);
			  rowArray[index] = row;
		      index++;	
			  maxElectronics = electronicsMrk[0];
				for(int i = 0; i < electronicsMrk.length; i++){
					if(maxElectronics < electronicsMrk[i]){
						maxElectronics = electronicsMrk[i]; 
						rollNo = GetData(View.getTable(), rowArray[i], 1).toString().trim();
						name = GetData(View.getTable(), rowArray[i], 3).toString().trim();
						div = GetData(View.getTable(), rowArray[i], 2).toString().trim();
//						subject = Subject.get(1);  
					}
				}	
		  }	
		
		Stats.SetData1("  "+ name, 21, 1);
        Stats.SetData1("  "+ rollNo, 21, 2);
        Stats.SetData1("  "+ div, 21, 3);
        Stats.SetData1("  "+ maxElectronics, 21, 4);

//  show("Name is "+name+" Whose Roll no is" +rollNo+" Division is "+div +" Score is "+maxElectronics +" Subject is "+subject);
	}


	public void ComputerTopper(SpreadMRKListView view, SpreadMRKListModel model, showstatistics stats){
		View = view;   Model = model;   Stats = stats;		
	    int rows = View.getTable().getRowCount(); 
		int rowcount = View.getTable().getRowCount();
		String rollNo  = null, name  = null, div  = null, stream = null;
		int index = 0, maxComputer = 0; int computerMrk[] = new int [1000];   int rowArray[] = new int[1000];		
		String RollNo, subject = null;
		ArrayList<String> Subject = null;

		for(int row = 0; row < rowcount; row++){
		   RollNo = GetData1(View.getTable(), row, 1);
		   subjectName = columnHeaderfinder(RollNo);
		   if(!subjectName.contains("CS1") || !subjectName.contains("CS2")) continue;
		      computerMrk[index] = ComputerSum(row);
			  rowArray[index] = row;
		      index++;	
			  maxComputer = computerMrk[0];
				for(int i = 0; i < computerMrk.length; i++){
					if(maxComputer < computerMrk[i]){
						maxComputer = computerMrk[i]; 
						rollNo = GetData(View.getTable(), rowArray[i], 1).toString().trim();
						name = GetData(View.getTable(), rowArray[i], 3).toString().trim();
						div = GetData(View.getTable(), rowArray[i], 2).toString().trim();
//						subject = Subject.get(1);  
					}
				}	
		  }	
		
		Stats.SetData1("  "+ name, 22, 1);
        Stats.SetData1("  "+ rollNo, 22, 2);
        Stats.SetData1("  "+ div, 22, 3);
        Stats.SetData1("  "+ maxComputer, 22, 4);

//  show("Name is "+name+" Whose Roll no is" +rollNo+" Division is "+div +" Score is "+maxComputer +" Subject is "+subject);
	}

	public void ComEngTopper(SpreadMRKListView view, SpreadMRKListModel model, showstatistics stats){
		View = view;   Model = model;   Stats = stats;
		int rows = View.getTable().getRowCount();                         
		String rollNo  = null, name  = null, div  = null, stream = null;
		int index = 0, maxEng = 0; int engMrk[] = new int [1000];   int rowArray[] = new int[1000];		
		String RollNo;
		ArrayList<String> Subject = null;
        for(int row = 0; row < rows; row++){ 
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
        }        
        for(int row = 0; row < rows; row++){
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
			Subject = columnHeaderfinder(RollNo);
			stream = Streamfinder(RollNo); 							
			if(stream.contains("COMMERCE") && Subject.get(0).contains("ENG")){ 
				engMrk[index] = Sub1(row);
				rowArray[index] = row;
				index++;	
				maxEng = engMrk[0];
				for(int i = 0; i < engMrk.length; i++){
					if(maxEng < engMrk[i]){ maxEng = engMrk[i]; 
					                     rollNo = GetData(View.getTable(), rowArray[i], 1).toString().trim();
				                         name = GetData(View.getTable(), rowArray[i], 3).toString().trim();
				                         div = GetData(View.getTable(), rowArray[i], 2).toString().trim();
				                        }
					      }	
			     }					
        }        
		Stats.SetData1("  "+ name, 0, 1);
        Stats.SetData1("  "+ rollNo, 0, 2);
        Stats.SetData1("  "+ div, 0, 3);
        Stats.SetData1("  "+ maxEng, 0, 4);
// show("Name is "+name+" Whose Roll no is" +rollNo+" Division is "+div +" Score is "+maxEng +" Subject is "+Subject.get(0));		
	}

	public void ComIteTopper(SpreadMRKListView view, SpreadMRKListModel model, showstatistics stats){
		View = view;   Model = model;   Stats = stats;
		int rows = View.getTable().getRowCount();                         
		String rollNo  = null, name  = null, div  = null, stream = null;
		int index = 0, maxIte = 0; int iteMrk[] = new int [1000];   int rowArray[] = new int[1000];		
		String RollNo, subject = null;
		ArrayList<String> Subject = null;
        for(int row = 0; row < rows; row++){ 
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
        }        
        for(int row = 0; row < rows; row++){
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
			Subject = columnHeaderfinder(RollNo);
			stream = Streamfinder(RollNo); 							
			if(stream.contains("COMMERCE") && Subject.get(1).contains("ITE")){ 
				iteMrk[index] = Sub2(row);
				rowArray[index] = row;
				index++;	
				maxIte = iteMrk[0];
				for(int i = 0; i < iteMrk.length; i++){
					if(maxIte < iteMrk[i]){ maxIte = iteMrk[i]; 
					                     rollNo = GetData(View.getTable(), rowArray[i], 1).toString().trim();
				                         name = GetData(View.getTable(), rowArray[i], 3).toString().trim();
				                         div = GetData(View.getTable(), rowArray[i], 2).toString().trim();
				                         subject = Subject.get(1);
				                        }
					      }	
			     }					
        }        
		Stats.SetData1("  "+ name, 1, 1);
        Stats.SetData1("  "+ rollNo, 1, 2);
        Stats.SetData1("  "+ div, 1, 3);
        Stats.SetData1("  "+ maxIte, 1, 4);
// show("Name is "+name+" Whose Roll no is" +rollNo+" Division is "+div +" Score is "+maxIte +" Subject is "+subject);		
	}

	
	public void ComHinTopper(SpreadMRKListView view, SpreadMRKListModel model, showstatistics stats){
		View = view;   Model = model;   Stats = stats;
		int rows = View.getTable().getRowCount();                         
		String rollNo  = null, name  = null, div  = null, stream = null;
		int index = 0, maxHin = 0; int hinMrk[] = new int [1000];   int rowArray[] = new int[1000];		
		String RollNo, subject = null;
		ArrayList<String> Subject = null;
        for(int row = 0; row < rows; row++){ 
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
        }        
        for(int row = 0; row < rows; row++){
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
			Subject = columnHeaderfinder(RollNo);
			stream = Streamfinder(RollNo); 							
			if(stream.contains("COMMERCE") && Subject.get(1).contains("HIN")){ 
				hinMrk[index] = Sub2(row);
				rowArray[index] = row;
				index++;	
				maxHin = hinMrk[0];
				for(int i = 0; i < hinMrk.length; i++){
					if(maxHin < hinMrk[i]){ maxHin = hinMrk[i]; 
					                     rollNo = GetData(View.getTable(), rowArray[i], 1).toString().trim();
				                         name = GetData(View.getTable(), rowArray[i], 3).toString().trim();
				                         div = GetData(View.getTable(), rowArray[i], 2).toString().trim();
				                         subject = Subject.get(1);
				                        }
					      }	
			     }					
        }        
		Stats.SetData1("  "+ name, 2, 1);
        Stats.SetData1("  "+ rollNo, 2, 2);
        Stats.SetData1("  "+ div, 2, 3);
        Stats.SetData1("  "+ maxHin, 2, 4);
//  how("Name is "+name+" Whose Roll no is" +rollNo+" Division is "+div +" Score is "+maxHin +" Subject is "+subject);		
	}

	public void ComMarTopper(SpreadMRKListView view, SpreadMRKListModel model, showstatistics stats){
		View = view;   Model = model;   Stats = stats;
		int rows = View.getTable().getRowCount();                         
		String rollNo  = null, name  = null, div  = null, stream = null;
		int index = 0, maxMar = 0; int marMrk[] = new int [1000];   int rowArray[] = new int[1000];		
		String RollNo, subject = null;
		ArrayList<String> Subject = null;
        for(int row = 0; row < rows; row++){ 
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
        }        
        for(int row = 0; row < rows; row++){
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
			Subject = columnHeaderfinder(RollNo);
			stream = Streamfinder(RollNo); 							
			if(stream.contains("COMMERCE") && Subject.get(1).contains("MAR")){ 
				marMrk[index] = Sub2(row);
				rowArray[index] = row;
				index++;	
				maxMar = marMrk[0];
				for(int i = 0; i < marMrk.length; i++){
					if(maxMar < marMrk[i]){ maxMar = marMrk[i]; 
					                     rollNo = GetData(View.getTable(), rowArray[i], 1).toString().trim();
				                         name = GetData(View.getTable(), rowArray[i], 3).toString().trim();
				                         div = GetData(View.getTable(), rowArray[i], 2).toString().trim();
				                         subject = Subject.get(1);
				                        }
					      }	
			     }					
        }        
		Stats.SetData1("  "+ name, 3, 1);
        Stats.SetData1("  "+ rollNo, 3, 2);
        Stats.SetData1("  "+ div, 3, 3);
        Stats.SetData1("  "+ maxMar, 3, 4);
//     show("Name is "+name+" Whose Roll no is" +rollNo+" Division is "+div+" Score is "+maxMar+" Subject is "+subject);		
	}

	public void ComTamTopper(SpreadMRKListView view, SpreadMRKListModel model, showstatistics stats){
		View = view;   Model = model;   Stats = stats;
		int rows = View.getTable().getRowCount();                         
		String rollNo  = null, name  = null, div  = null, stream = null;
		int index = 0, maxTam = 0; int tamMrk[] = new int [1000];   int rowArray[] = new int[1000];		
		String RollNo, subject = null;
		ArrayList<String> Subject = null;
        for(int row = 0; row < rows; row++){ 
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
        }        
        for(int row = 0; row < rows; row++){
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
			Subject = columnHeaderfinder(RollNo);
			stream = Streamfinder(RollNo); 							
			if(stream.contains("COMMERCE") && Subject.get(1).contains("TAM")){ 
				tamMrk[index] = Sub2(row);
				rowArray[index] = row;
				index++;	
				maxTam = tamMrk[0];
				for(int i = 0; i < tamMrk.length; i++){
					if(maxTam < tamMrk[i]){ maxTam = tamMrk[i]; 
					                     rollNo = GetData(View.getTable(), rowArray[i], 1).toString().trim();
				                         name = GetData(View.getTable(), rowArray[i], 3).toString().trim();
				                         div = GetData(View.getTable(), rowArray[i], 2).toString().trim();
				                         subject = Subject.get(1);
				                        }
					      }	
			     }					
        }        
		Stats.SetData1("  "+ name, 4, 1);
        Stats.SetData1("  "+ rollNo, 4, 2);
        Stats.SetData1("  "+ div, 4, 3);
        Stats.SetData1("  "+ maxTam, 4, 4);
//        show("Name is "+name+" Whose Roll no is" +rollNo+" Division is "+div+" Score is "+maxTam+" Subject is "+subject);		
	}

	public void ComEcoTopper(SpreadMRKListView view, SpreadMRKListModel model, showstatistics stats){
		View = view;   Model = model;   Stats = stats;
		int rows = View.getTable().getRowCount();                         
		String rollNo  = null, name  = null, div  = null, stream = null;
		int index = 0, maxEco = 0; int ecoMrk[] = new int [1000];   int rowArray[] = new int[1000];		
		String RollNo, subject = null;
		ArrayList<String> Subject = null;
        for(int row = 0; row < rows; row++){ 
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
        }        
        for(int row = 0; row < rows; row++){
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
			Subject = columnHeaderfinder(RollNo);
			stream = Streamfinder(RollNo); 							
			if(stream.contains("COMMERCE") && Subject.get(2).contains("ECO")){ 
				ecoMrk[index] = Sub3(row);
				rowArray[index] = row;
				index++;	
				maxEco = ecoMrk[0];
				for(int i = 0; i < ecoMrk.length; i++){
					if(maxEco < ecoMrk[i]){ maxEco = ecoMrk[i]; 
					                     rollNo = GetData(View.getTable(), rowArray[i], 1).toString().trim();
				                         name = GetData(View.getTable(), rowArray[i], 3).toString().trim();
				                         div = GetData(View.getTable(), rowArray[i], 2).toString().trim();
				                         subject = Subject.get(2);  
				                        }
					      }	
			     }					
        }       
		Stats.SetData1("  "+ name, 5, 1);
        Stats.SetData1("  "+ rollNo, 5, 2);
        Stats.SetData1("  "+ div, 5, 3);
        Stats.SetData1("  "+ maxEco, 5, 4);
//   show("Name is "+name+" Whose Roll no is" +rollNo+" Division is "+div +" Score is "+maxEco+" Subject is "+subject);		
	}

	public void ComBkeTopper(SpreadMRKListView view, SpreadMRKListModel model, showstatistics stats){
		View = view;   Model = model;   Stats = stats;
		int rows = View.getTable().getRowCount();                         
		String rollNo  = null, name  = null, div  = null, stream = null;
		int index = 0, maxBke = 0; int bkeMrk[] = new int [1000];   int rowArray[] = new int[1000];		
		String RollNo, subject = null;
		ArrayList<String> Subject = null;
        for(int row = 0; row < rows; row++){ 
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
        }        
        for(int row = 0; row < rows; row++){
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
			Subject = columnHeaderfinder(RollNo);
			stream = Streamfinder(RollNo); 							
			if(stream.contains("COMMERCE") && Subject.get(3).contains("BKE")){ 
				bkeMrk[index] = Sub4(row);
				rowArray[index] = row;
				index++;	
				maxBke = bkeMrk[0];
				for(int i = 0; i < bkeMrk.length; i++){
				 if(maxBke < bkeMrk[i]){ maxBke = bkeMrk[i]; 
					                     rollNo = GetData(View.getTable(), rowArray[i], 1).toString().trim();
				                         name = GetData(View.getTable(), rowArray[i], 3).toString().trim();
				                         div = GetData(View.getTable(), rowArray[i], 2).toString().trim();
				                         subject = Subject.get(3);  
				                        }
					      }	
			     }					
        }       
		Stats.SetData1("  "+ name, 6, 1);
        Stats.SetData1("  "+ rollNo, 6, 2);
        Stats.SetData1("  "+ div, 6, 3);
        Stats.SetData1("  "+ maxBke, 6, 4);
//   show("Name is "+name+" Whose Roll no is" +rollNo+" Division is "+div +" Score is "+maxBke +" Subject is "+subject);		
	}

	public void ComOcmTopper(SpreadMRKListView view, SpreadMRKListModel model, showstatistics stats){
		View = view;   Model = model;   Stats = stats;
		int rows = View.getTable().getRowCount();                         
		String rollNo  = null, name  = null, div  = null, stream = null;
		int index = 0, maxOcm = 0; int ocmMrk[] = new int [1000];   int rowArray[] = new int[1000];		
		String RollNo, subject = null;
		ArrayList<String> Subject = null;
        for(int row = 0; row < rows; row++){ 
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
        }        
        for(int row = 0; row < rows; row++){
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
			Subject = columnHeaderfinder(RollNo);
			stream = Streamfinder(RollNo); 							
			if(stream.contains("COMMERCE") && Subject.get(4).contains("OCM")){ 
				ocmMrk[index] = Sub5(row);
				rowArray[index] = row;
				index++;	
				maxOcm = ocmMrk[0];
				for(int i = 0; i < ocmMrk.length; i++){
				 if(maxOcm < ocmMrk[i]){ maxOcm = ocmMrk[i]; 
					                     rollNo = GetData(View.getTable(), rowArray[i], 1).toString().trim();
				                         name = GetData(View.getTable(), rowArray[i], 3).toString().trim();
				                         div = GetData(View.getTable(), rowArray[i], 2).toString().trim();
				                         subject = Subject.get(4);  
				                        }
					      }	
			     }					
        }       
		Stats.SetData1("  "+ name, 7, 1);
        Stats.SetData1("  "+ rollNo, 7, 2);
        Stats.SetData1("  "+ div, 7, 3);
        Stats.SetData1("  "+ maxOcm, 7, 4);
//   show("Name is "+name+" Whose Roll no is" +rollNo+" Division is "+div +" Score is "+maxOcm +" Subject is "+subject);		
	}

	public void ComSepTopper(SpreadMRKListView view, SpreadMRKListModel model, showstatistics stats){
		View = view;   Model = model;   Stats = stats;
		int rows = View.getTable().getRowCount();                         
		String rollNo  = null, name  = null, div  = null, stream = null;
		int index = 0, maxSep = 0; int sepMrk[] = new int [1000];   int rowArray[] = new int[1000];		
		String RollNo, subject = null;
		ArrayList<String> Subject = null;
        for(int row = 0; row < rows; row++){ 
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
        }        
        for(int row = 0; row < rows; row++){
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
			Subject = columnHeaderfinder(RollNo);
			stream = Streamfinder(RollNo); 							
			if(stream.contains("COMMERCE") && Subject.get(5).contains("SEP")){ 
				sepMrk[index] = Sub6(row);
				rowArray[index] = row;
				index++;	
				maxSep = sepMrk[0];
				for(int i = 0; i < sepMrk.length; i++){
					if(maxSep < sepMrk[i]){ maxSep = sepMrk[i]; 
					                     rollNo = GetData(View.getTable(), rowArray[i], 1).toString().trim();
				                         name = GetData(View.getTable(), rowArray[i], 3).toString().trim();
				                         div = GetData(View.getTable(), rowArray[i], 2).toString().trim();
				                         subject = Subject.get(5);  
				                        }
					      }	
			     }					
        }       
		Stats.SetData1("  "+ name, 8, 1);
        Stats.SetData1("  "+ rollNo, 8, 2);
        Stats.SetData1("  "+ div, 8, 3);
        Stats.SetData1("  "+ maxSep, 8, 4);
//  show("Name is "+name+" Whose Roll no is" +rollNo+" Division is "+div +" Score is "+maxSep +" Subject is "+subject);		
	}

	public void ComMatTopper(SpreadMRKListView view, SpreadMRKListModel model, showstatistics stats){
		View = view;   Model = model;   Stats = stats;
		int rows = View.getTable().getRowCount();                         
		String rollNo  = null, name  = null, div  = null, stream = null;
		int index = 0, maxMat = 0; int matMrk[] = new int [1000];   int rowArray[] = new int[1000];		
		String RollNo, subject = null;
		ArrayList<String> Subject = null;
        for(int row = 0; row < rows; row++){ 
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
        }        
        for(int row = 0; row < rows; row++){
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
			Subject = columnHeaderfinder(RollNo);
			stream = Streamfinder(RollNo); 							
			if(stream.contains("COMMERCE") && Subject.get(5).contains("MAT")){ 
				matMrk[index] = Sub6(row);
				rowArray[index] = row;
				index++;	
				maxMat = matMrk[0];
				for(int i = 0; i < matMrk.length; i++){
					if(maxMat < matMrk[i]){ maxMat = matMrk[i]; 
					                     rollNo = GetData(View.getTable(), rowArray[i], 1).toString().trim();
				                         name = GetData(View.getTable(), rowArray[i], 3).toString().trim();
				                         div = GetData(View.getTable(), rowArray[i], 2).toString().trim();
				                         subject = Subject.get(5);  
				                        }
					      }	
			     }					
        }       
		Stats.SetData1("  "+ name, 9, 1);
        Stats.SetData1("  "+ rollNo, 9, 2);
        Stats.SetData1("  "+ div, 9, 3);
        Stats.SetData1("  "+ maxMat, 9, 4);
//   show("Name is "+name+" Whose Roll no is" +rollNo+" Division is "+div +" Score is "+maxMat +" Subject is "+subject);		
	}


	public void MatTopper(SpreadMRKListView view, SpreadMRKListModel model, showstatistics stats, int rows){
		View = view;   Model = model;   Stats = stats;
		rows = View.getTable().getRowCount();                         
		String rollNo  = null, name  = null, div  = null, stream = null;
		int index = 0, maxMat = 0; int matMrk[] = new int [1000];   int rowArray[] = new int[1000];		
		String RollNo, subject = null;
		ArrayList<String> Subject = null;
        for(int row = 0; row < rows; row++){ 
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
        }        
        for(int row = 0; row < rows; row++){
			RollNo = GetData(View.getTable(), row, 1).toString().trim();
			Subject = columnHeaderfinder(RollNo);
			stream = Streamfinder(RollNo); 							
			if(stream.contains("COMMERCE") && Subject.get(5).contains("MAT")){ 
				matMrk[index] = Sub6(row);
				rowArray[index] = row;
				index++;	
				maxMat = matMrk[0];
				for(int i = 0; i < matMrk.length; i++){
					if(maxMat < matMrk[i]){ maxMat = matMrk[i]; 
					                     rollNo = GetData(View.getTable(), rowArray[i], 1).toString().trim();
				                         name = GetData(View.getTable(), rowArray[i], 3).toString().trim();
				                         div = GetData(View.getTable(), rowArray[i], 2).toString().trim();
				                         subject = Subject.get(5);  
				                        }
					      }	
			     }					
        }       
		Stats.SetData1("  "+ name, 9, 1);
        Stats.SetData1("  "+ rollNo, 9, 2);
        Stats.SetData1("  "+ div, 9, 3);
        Stats.SetData1("  "+ maxMat, 9, 4);
   show("Name is "+name+" Whose Roll no is" +rollNo+" Division is "+div +" Score is "+maxMat +" Subject is "+subject);	
   
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
	
	public int Sub2(int row){
//		int row = View.getTable().getSelectedRow();
//		row = pageNum;
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

	public int Sub3(int row){
//		int row = View.getTable().getSelectedRow();
//		row = pageNum;
		int TotalOfEcoBioEL2CS2 = 0, ecobioel2cs2 = 0;	
			for(int j = 12; j < 16; j++ ){
			String EcoBioEl2Cs2Total = GetData1(View.getTable(), row, j);
			
			if(EcoBioEl2Cs2Total == null || EcoBioEl2Cs2Total.isEmpty()){ EcoBioEl2Cs2Total = "00"; }					 
			if(EcoBioEl2Cs2Total.contentEquals("AB") || EcoBioEl2Cs2Total.contentEquals("AB ")){ EcoBioEl2Cs2Total = "00"; }					 
						
			ecobioel2cs2 = 	Integer.parseInt(EcoBioEl2Cs2Total);
			TotalOfEcoBioEL2CS2 = TotalOfEcoBioEL2CS2 + ecobioel2cs2;
		    }
		return TotalOfEcoBioEL2CS2;		
	}

	public int Sub4(int row){
//		int row = View.getTable().getSelectedRow();
//		row = pageNum;
		int TotalOfBKEPHY = 0, bkephy = 0;	
			for(int j = 16; j < 20; j++ ){
			String bkephyTotal = GetData1(View.getTable(), row, j);
			
			if(bkephyTotal == null || bkephyTotal.isEmpty()){ bkephyTotal = "00"; }					 
			if(bkephyTotal.contentEquals("AB") || bkephyTotal.contentEquals("AB ")){ bkephyTotal = "00"; }					 
			
			bkephy = 	Integer.parseInt(bkephyTotal);
			TotalOfBKEPHY = TotalOfBKEPHY + bkephy;
		    }
		return TotalOfBKEPHY;		
	}

	
	public int Sub5(int row){
//		int row = View.getTable().getSelectedRow();
//		row = pageNum;
		int TotalOfOcmChe = 0, ocmche = 0;	
			for(int j = 20; j < 24; j++ ){
			String ocmcheTotal = GetData1(View.getTable(), row, j);
			
			if(ocmcheTotal == null || ocmcheTotal.isEmpty()){ ocmcheTotal = "00"; }					 
			if(ocmcheTotal.contentEquals("AB") || ocmcheTotal.contentEquals("AB ")){ ocmcheTotal = "00"; }					 

			ocmche = 	Integer.parseInt(ocmcheTotal);
			TotalOfOcmChe = TotalOfOcmChe + ocmche;
		    }
		return TotalOfOcmChe;		
	}
	
	public int Sub6(int row){
//		int row = View.getTable().getSelectedRow();
//		row = pageNum;
		int TotalOfMatSep = 0, matsep = 0;	
			for(int j = 24; j < 28; j++ ){
			String MatSepTotal = GetData1(View.getTable(), row, j);
			
			if(MatSepTotal == null || MatSepTotal.isEmpty()){ MatSepTotal = "00"; }					 
			if(MatSepTotal.contentEquals("AB") || MatSepTotal.contentEquals("AB ")){ MatSepTotal = "00"; }					 

			matsep = 	Integer.parseInt(MatSepTotal);
			TotalOfMatSep = TotalOfMatSep + matsep;
		    }
		return TotalOfMatSep;		
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

	
	public int ComputerSum(int row){
		String RollNo = View.getTable().getModel().getValueAt(row, 1).toString(); 
		subjectName = columnHeaderfinder(RollNo);
//		row = View.getTable().getSelectedRow();
	    String marks;
		int Marks = 0, SumComputerMarks = 0;   
		  if(subjectName.contains("CS1") || subjectName.contains("CS2")){
			 for(int j = 0; j < 8; j++){
				marks = GetData1(View.getTable(),row, 8+j);                                   //  show("marks = "+marks);
				if(marks == null || marks.isEmpty()){ marks = "00"; }					 
				if(marks.contentEquals("AB") || marks.contentEquals("AB ")){ marks = "00"; }					 
				Marks = Integer.parseInt(marks);                                              //  show("Marks = "+Marks);
				SumComputerMarks = SumComputerMarks + Marks;                                  //  show(TotalMarks);			 
			  }
		  }
		return SumComputerMarks;							
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
	
	public void Rearrange(SpreadMRKListView view, SpreadMRKListModel model){
		  View = view; Model = model;
		  int[] TotalMarks; int ind = 0;
		  int rowcount = 50;                       //      View.getTable().getRowCount();
		  TotalMarks = new int [rowcount];
		  int length = TotalMarks.length;	  

		  for(int row = 0; row < rowcount; row++){
			  String finalTotal = (String) GetData(View.getTable(), row,30);
			  TotalMarks[ind] = Integer.parseInt(finalTotal);
			  ind++;	    
		  }	
              Show(TotalMarks[9]);
              
             
	}

}
