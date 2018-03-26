package in.peecee.spreadMRKList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class OverAllResult {

	public JTable Table;
	private SpreadMRKListView View;
    public DefaultTableModel model;
    
	private JButton btnCancel;
	private JButton btnPrint;
	JFrame frame = new JFrame();

	public void show(String msg) {JOptionPane.showMessageDialog(null, msg);}   ///for debugging
	
	public void SetData(Object obj, int row_index, int col_index){Table.getModel().setValueAt(obj,row_index,col_index);}
    public String GetData1(JTable table, int row_index, int col_index) { return (String) View.getTable().getValueAt(row_index, col_index); }
    public Object GetData(JTable table, int row_index, int col_index)  { return View.getTable().getValueAt(row_index, col_index); }

    String[] Result = {"Number of students appeared", "Number of Students Passed",   "Number of Students Failed",
    		           "Number of Distinctions",      "Number of First Class",       "Number of Second  Class", 
    		           "Number of Pass Class",        "Number of Students Promoted", "Result Pass Percentage"}; 
		    
    public void overallresult(){
    	
		frame.validate();                
	    frame.setTitle("Over All Result");
	    frame.setSize(800, 550);
	    frame.setLocationRelativeTo(null);
	    frame.setResizable(false);
//	    frame.getContentPane().setBackground(Color.lightGray);
	    frame.setVisible(true);

    }
    
    public OverAllResult(){
    	
       	     	
	//////   N O R T H  P A N E L
    	
    	GridLayout GL = new GridLayout();
	    
	    JPanel northPanel = new JPanel();  
//	    northPanel.setLayout(new FlowLayout());
	    northPanel.setLayout(new GridLayout(5, 2));
        frame.add(northPanel, BorderLayout.NORTH);

	    JLabel lblOverAllResult = new JLabel("   O  V  E  R     A  L  L    R  E  S  U  L   T  -  ");
	    lblOverAllResult.setFont(new Font("Times New Roman", Font.BOLD, 18));
	    northPanel.add(lblOverAllResult);
	    
	    JLabel lblHeader2 = new JLabel(" S C I E N C E   A N D   C O M M E R C E ");
	    lblHeader2.setFont(new Font("Times New Roman", Font.BOLD, 18));
 	    northPanel.add(lblHeader2);
 
 	    JLabel lblCollegeName = new JLabel("   College Name ");
	    lblCollegeName.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    lblCollegeName.setMinimumSize(null);
	    northPanel.add(lblCollegeName);

		JTextField CollName = new JTextField("S.I.W.S. College of Commerce and Economics and Smt. Thirumalai College of Science");
		CollName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		CollName.setPreferredSize(new Dimension(400, 20));
		CollName.setColumns(50);
		northPanel.add(CollName);

	    JLabel lblplace = new JLabel("   Place / Location");
	    lblplace.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    
	    northPanel.add(lblplace);
	    
		JTextField Place = new JTextField("WADALA");
		Place.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Place.setPreferredSize(new Dimension(400, 20));
		Place.setColumns(15);
		northPanel.add(Place);
		
	     btnPrint= new JButton("Print");    
	     btnPrint.setToolTipText("Print Table");
	     btnPrint.setPreferredSize(new Dimension(20, 30));
	     btnPrint.setFont(new Font("Times New Roman", Font.BOLD, 14));
	     btnPrint.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent arg0) {
//	       		System.exit(0);
//	       		Print();
	       		
	       	}
	       });

	     
	     northPanel.add(btnPrint);
	     
	     btnCancel= new JButton("Cancel");    
	     btnCancel.setToolTipText("Cancel");
	     btnCancel.setPreferredSize(new Dimension(20, 30));
	     btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 14));
	     btnCancel.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent arg0) {

	    	      Window win = SwingUtilities.getWindowAncestor(Table);
	    	      if (win != null) {
	    	         win.dispose();
	    	      }
	      	}
	      });
	      

	     northPanel.add(btnCancel);

	    frame.getContentPane().add(northPanel, BorderLayout.NORTH);
       	       	   	
       	JScrollPane scrollPane = new JScrollPane(Table);
       	frame.getContentPane().add(scrollPane);
       	
       	Table = new JTable();
       	String data[][]={ {"   01","", "", "", ""},{"   02","", "", "", ""},{"   03","", "", "", ""},{"   04","", "", "", ""},
       			          {"   05","", "", "", ""},{"   06","", "", "", ""},{"   07","", "", "", ""},{"   08","", "", "", ""},
       			          {"   09","", "", "", ""},{"","", "", "", ""},{"","", "", "", ""},{"","","", "", "", ""}
       	};  
       	
        String column[]={"Sr.No","P A  R T I C U L A R S","COMMERCE","SCIENCE","OVER ALL RESULT"};         
        Table=new JTable(data,column);    
       	scrollPane.setViewportView(Table);
       	Table.setBorder(new LineBorder(new Color(0, 0, 20)));
        Table.setRowHeight(30); 
        Table.getColumnModel().getColumn(0).setPreferredWidth(30);
        Table.getColumnModel().getColumn(1).setPreferredWidth(200);
        JTableHeader header = Table.getTableHeader();
        header.setPreferredSize(new Dimension(0,30));
        header.setFont(new Font("Dialog", Font.BOLD,14));   
        header.revalidate(); 
        
        Table.setFont(new Font("Times New Roman", Font.BOLD, 15));
        
		  for(int j = 0; j < Result.length; j++){
			  SetData("  "+Result[j], j, 1);				  				  
		  }
        }
    	
    }
