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

	JFrame frame = new JFrame();
	public void show(String msg) {JOptionPane.showMessageDialog(null, msg);}   ///for debugging
	
	private JButton btnCansel;
	private printRoutines prnRoutines = new printRoutines();
	private JButton SSPrintButton;
    public void SetData(Object obj, int row_index, int col_index){table.setValueAt(obj,row_index,col_index);}
    private SpreadMRKListModel Model;

	public JTable table;
	
	public void showScoreButtons(){
		frame.validate();                
	    frame.setTitle("List of Failures");
	    frame.setSize(1290, 700);
//	    frame.setLocationRelativeTo(null);
	    frame.setResizable(false);
//	    frame.getContentPane().setBackground(Color.lightGray);
	    frame.setVisible(true);
		
	}
	
	public FailuresList() {
		
		int n = 200;
//		ResizeTable(table,n);


	    DefaultTableModel model = new DefaultTableModel();
	    model.setDataVector(new Object[][]{
	      {"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""}},
	      
	    new Object[]{"Sr.No","Roll No","Div","Name","U1","T1","U2","T2","U1","T1","U2","T2","U1","T1","U2","T2",
	    		                        "U1","T1","U2","T2","U1","T1","U2","T2","U1","T1","U2","T2","EVS","PTE","Total",""});

	    final String[] HeadereSubjects = {"ENGLISH", "SL / VOC", "ECO/BIO/VOC", "BKE / PHY", "OCM / CHE", "MAT / SEP"};
	    
	      table = new JTable( model ) {
	        protected JTableHeader createDefaultTableHeader() {
	        return new GroupableTableHeader(columnModel);
	      }
	    };
	     
	    TableColumnModel cm = table.getColumnModel();
	    table.setRowHeight(25);
	    table.setFont(new Font("Times New Roman", Font.BOLD, 12));
	    
	    DefaultTableCellRenderer stringRenderer = (DefaultTableCellRenderer)
	    table.getDefaultRenderer(String.class);
	    stringRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	    
	    table.getColumnModel().getColumn(0).setPreferredWidth(110);              //  serial Numbers
	    table.getColumnModel().getColumn(1).setPreferredWidth(115);              //  Roll Numbers
	    table.getColumnModel().getColumn(2).setPreferredWidth(50);               //  Division
	    table.getColumnModel().getColumn(3).setPreferredWidth(850);              //  Names of students
	    table.getColumnModel().getColumn(30).setPreferredWidth(90);
	    table.getColumnModel().getColumn(31).setPreferredWidth(200);
//	    table.getColumnModel().getColumn(31).setHeaderValue("<html>Remarks/<br>Signature");
	    table.getColumnModel().getColumn(31).setHeaderValue("Result");
	    
	    GroupableTableHeader header = (GroupableTableHeader)table.getTableHeader();    
	    JScrollPane scroll = new JScrollPane( table );
	    frame.getContentPane().add( scroll );

	     header.revalidate(); 
	    
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
		
		
		JButton btnHelp = new JButton("HELP");
		btnHelp.setFont(new Font("Times New Roman", Font.BOLD, 14));
		northPanel.add(btnHelp);
	    

	    frame.getContentPane().add(northPanel, BorderLayout.NORTH);
	    
	 //////   S O U T H  P A N E L    
	    
	    JPanel southPanel = new JPanel();  
	    southPanel.setLayout(new GridLayout(1, 8));        
	   
		JButton btnSearch = new JButton("Search");        
		btnSearch.setFont(new Font("Times New Roman", Font.BOLD, 14));
		southPanel.add(btnSearch);
		
		JTextField search = new JTextField("");
		search.setFont(new Font("Times New Roman", Font.BOLD, 14));
		search.setColumns(8);
		southPanel.add(search);
	    
	    JButton btnLoad = new JButton("Load");
	    btnLoad.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    southPanel.add(btnLoad);   
	       
	    JButton btnSave = new JButton("Save");    
	    btnSave.setToolTipText("Save");
	    btnSave.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    southPanel.add(btnSave);    

	    JButton printList = new JButton("Print List");    
	    printList.setToolTipText("Update");
	    printList.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    southPanel.add(printList);
	    
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
	    
	    btnCansel = new JButton("Cancel");
	    btnCansel.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    	      Window win = SwingUtilities.getWindowAncestor(table);
	    	      if (win != null) {
	    	         win.dispose();
	    	      }
	    	}
	    });
	    btnCansel.setFont(new Font("Times New Roman", Font.BOLD, 14));
	  //  btnCansel.setPreferredSize(new Dimension(115, 25));
	    southPanel.add(btnCansel);
	    
	    frame.getContentPane().add(southPanel, BorderLayout.SOUTH);
		
/*		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("CLICK TO ..... ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(10, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		JButton currentPrintButton = new JButton("Print Current Marks Card");
		currentPrintButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				prnRoutines.PrintCurrent();	
				
			}
		});
		currentPrintButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		GridBagConstraints gbc_currentPrintButton = new GridBagConstraints();
		gbc_currentPrintButton.anchor = GridBagConstraints.EAST;
		gbc_currentPrintButton.insets = new Insets(10, 20, 5, 5);
		gbc_currentPrintButton.gridx = 2;
		gbc_currentPrintButton.gridy = 3;
		frame.getContentPane().add(currentPrintButton, gbc_currentPrintButton);
		
		JButton allPrintButton = new JButton("Print All Marks Cards");
		allPrintButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prnRoutines.PrintAllMarksCards();
				
			}
		});
		allPrintButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		GridBagConstraints gbc_allPrintButton = new GridBagConstraints();
		gbc_allPrintButton.insets = new Insets(10, 10, 5, 5);
		gbc_allPrintButton.gridx = 4;
		gbc_allPrintButton.gridy = 3;
		frame.getContentPane().add(allPrintButton, gbc_allPrintButton);
		
		SSPrintButton = new JButton("Print Spread Sheet");
		SSPrintButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		SSPrintButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prnRoutines.SpreadSheet();
				
//				String temp = "I am Fine";
//				show(temp);
//				show("I am Fine, too");
				
			}		
	    });                      
		
		GridBagConstraints gbc_SSPrintButton = new GridBagConstraints();
		gbc_SSPrintButton.anchor = GridBagConstraints.WEST;
		gbc_SSPrintButton.insets = new Insets(10, 45, 5, 5);
		gbc_SSPrintButton.gridx = 2;
		gbc_SSPrintButton.gridy = 5;
		frame.getContentPane().add(SSPrintButton, gbc_SSPrintButton);
		
		final JButton cancellButton = new JButton("Cancell");
		cancellButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Window win = SwingUtilities.getWindowAncestor(cancellButton);
			      if (win != null) {
			         win.dispose();
			}
		  }
			      
        });
		cancellButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		GridBagConstraints gbc_cancellButton = new GridBagConstraints();
		gbc_cancellButton.insets = new Insets(10, 10, 0, 5);
		gbc_cancellButton.gridx = 4;
		gbc_cancellButton.gridy = 5;
		frame.getContentPane().add(cancellButton, gbc_cancellButton);
		// TODO Auto-generated constructor stub
	*/					
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

	  public class GroupableTableHeader extends JTableHeader {
		  /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private static final String uiClassID = "GroupableTableHeaderUI";
		  protected Vector columnGroups = null;

		  public GroupableTableHeader(TableColumnModel model) {
		    super(model);
		    setReorderingAllowed(false);
		  }

	  }
}
