package in.peecee.spreadMRKList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ProgressMonitor;

public class progressBar {
private subjecToppers subtop = new subjecToppers();	
private SpreadMRKListView View;
private SpreadMRKListModel Model;
private showstatistics Stats = new showstatistics();
public void Show(int num) {JOptionPane.showMessageDialog(null, num);}   ///for debugging


	JFrame frame;
  public void progressBar(SpreadMRKListView view){
	  View = view;  
    frame = new JFrame("Swing's ProgressMonitor");
 
    int min = 0;   int max = 100;
    int rows= View.getTable().getRowCount();
    Show(rows);
    String[] message = new String[2];
    message[0] = "Performing Iterations.";
    message[1] = "Wait for completion…….";
    final ProgressMonitor monitor = new ProgressMonitor(frame, message, "Iteration", min, max);
    final Runnable runnable = new Runnable()  
           {
             public void run()   
             {
               int rows= View.getTable().getRowCount();
               int sleepTime = 100;
               for(int i = 1; i < 100; i++)    
               {
                 try  
                 {
                   monitor.setNote("Iteration " + i);
                   monitor.setProgress(i);
 
                   if (monitor.isCanceled())    
                   {
                     monitor.setProgress(100);
                     break;             
                   }
                   Thread.sleep( sleepTime);
                 }
                 catch (InterruptedException e) { }
               }
               monitor.close();
               frame.dispose();
             }

			private void show(int rows) {
				// TODO Auto-generated method stub
				
			}
         } ;
 
    frame.setSize(200,200);
    frame.setLocationRelativeTo(null);
    frame.setVisible(false);
    
    
    Thread thread = new Thread(runnable);
    thread .start();
  }

}
