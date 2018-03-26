package in.peecee.spreadMRKList;


import java.awt.Color;

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
import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

public class FailuresList {
	
	public JTable TABLE;
	private SpreadMRKListView View;
	JFrame frame = new JFrame();
    public DefaultTableModel model;

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
	      {"","","","","","","","","","","","","",""}},
	      
	    new Object[]{"Sr.No","Roll No","Div","Name","ENG","SL/IT/VOC","ECO/BIO/VOC","BKE/PHY","OCM/CHE","MAT/SEP","EVS","PTE","Result","Remarks"});
	    
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
	    TABLE.getColumnModel().getColumn(13).setPreferredWidth(200);
	    
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

	    JButton printList = new JButton("Print List");    
	    printList.setToolTipText("Update");
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
