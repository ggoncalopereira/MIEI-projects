/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftpclient;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.ClientFTP;
import static model.ClientFTP.datadin;
import static model.ClientFTP.dataout;
import static model.ClientFTP.datasoc;
import static model.ClientFTP.din;
import static model.ClientFTP.dout;

/**
 *
 * @author dhanush
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    String Host,Port,pwd;
    String Comand;
    ClientFTP obj_client = new ClientFTP();
    public MainFrame() {
        initComponents();
    
    }
    public MainFrame(String[] args) throws Exception {
        Host=args[0];
        Port=args[1];
        System.out.println("in func0");
        initComponents();
        System.out.println("in func1");
        myinitComponents();
        System.out.println("in func");
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {System.out.println("Closed");
                try {
                    obj_client.disconnect();
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Closed");
                e.getWindow().dispose();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    private void myinitComponents() throws Exception{
    jLabel1.setText("Host: "+Host);
    //jLabel1.setAlignment(L);
    jLabel2.setText("Port: "+Port);
    System.out.println("in inner func1");
    pwd=obj_client.getPWD();
    System.out.println("in inner func2");
    jLabel3.setText("PWD: "+pwd);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();

        jFileChooser1.setDialogTitle("Choose File");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 300));

        jLabel1.setText("Host");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Port");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Pwd");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setText("Disconnect");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jToggleButton1.setText("help?");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Commands");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton2.setText("Execute");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ftpclient/FTPImage2.jpeg"))); // NOI18N

        jProgressBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jProgressBar1.setStringPainted(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(50, 50, 50)
                                        .addComponent(jButton2)))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jToggleButton1)
                                .addGap(22, 22, 22)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jButton2))
                    .addComponent(jToggleButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
            // TODO add your handling code here:
            Thread queryThread = new Thread() {
            public void run() {
            jButton2.setEnabled(false);
                try {
                    executeCommands();
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            };
            queryThread.start();
            //executeCommands();
       
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
        if(jToggleButton1.isSelected()){
             
            JOptionPane.showMessageDialog(this,"send (Sends a file)\nreceive (Receives a file)\nlist (Lists all files in current working directiry)/"
                    + "\ncd (change working directory)\nmkdir (creates a new directory)\nrmdir (deletes a directory if it's empty)\ndelete (delete's a file)" , "List of Comments", JOptionPane.INFORMATION_MESSAGE);
           
           jToggleButton1.doClick(); 
        }
       
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            obj_client.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] args={"as"};
        ClientUI.main(args);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    public String getChoice(){
        //Object[] options = {"Yes","No"};
     String choice = null;
     int n;
        n = JOptionPane.showConfirmDialog(this,"Do you want to overwrite","File Already Exists",
                JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
       // System.out.println(n);
    if (n==0){
    //return "Y";
    choice="Y";
    }
    else if (n==1){
    choice="N";
    }
    return choice;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //obj.setValues(args);
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    //System.out.println("asdada");
                    new MainFrame(args).setVisible(true);
                    //System.out.println("asdasdasdadada");
                } catch (Exception ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
private void executeCommands() throws IOException{
 Comand=jTextField1.getText();
        Comand=Comand.toLowerCase();
        //ClientFTP.datasoc=new Socket(Host,20);
        if (Comand.compareTo("send")==0){
          
              int returnVal = jFileChooser1.showOpenDialog(this);
        if (returnVal == jFileChooser1.APPROVE_OPTION) {
        File file = jFileChooser1.getSelectedFile();
        
           ClientFTP.dout.writeUTF("SEND");
             datasoc=new Socket(Host,ClientFTP.dataPort);
    datadin=new DataInputStream(datasoc.getInputStream());
    dataout=new DataOutputStream(datasoc.getOutputStream());
        String filename=file.getName();
        //System.out.print("Enter File Name :");
        //filename=br.readLine();
        //System.out.println(file);
        //System.out.println(file.getName());
        /*File f=new File(filename);
        if(!f.exists())
        {
            System.out.println("File not Exists...");
            dout.writeUTF("File not found");
            return;
        }*/
        
        ClientFTP.dout.writeUTF(filename);
        
        String msgFromServer=ClientFTP.din.readUTF();
        if(msgFromServer.compareTo("File Already Exists")==0)
        {
            String Option;
            //System.out.println("File Already Exists. Want to OverWrite (Y/N) ?");
            //Option=br.readLine();   
            MainFrame obj_main=new MainFrame();
            Option=obj_main.getChoice();
            if(Option=="Y")    
            {
                ClientFTP.dout.writeUTF("Y");
            }
            else
            {
                ClientFTP.dout.writeUTF("N");
                return;
            }
        }
        
        System.out.println("Sending File ...");
        FileInputStream fin=new FileInputStream(file);
        double filelength=file.length();
        double updatelength=filelength/1000;
        //MainFrame obj=new MainFrame();
        int ch,count=0;
        do
        {
            if (count > updatelength){
                //System.out.println("in client" + updatelength);
               
                 double progValue=((updatelength/filelength)*100);
                 System.out.println("size" + (int) progValue);
                 updatelength+=updatelength;
                 jProgressBar1.setValue((int) progValue);
                 jProgressBar1.update(jProgressBar1.getGraphics());
                            
            }
            count++;
            ch=fin.read();
            //System.out.println(ch);
            ClientFTP.dataout.writeUTF(String.valueOf(ch));
        }
        while(ch!=-1);
        fin.close();
        System.out.println(ClientFTP.din.readUTF());
        jProgressBar1.setValue(100);
        //return true;
    }     
        
                 
     else {
        System.out.println("File access cancelled by user.");
    }
       
        }   
        else if (Comand.compareTo("receive")==0){
           
            ArrayList<String> fileList= new ArrayList<String>();
            try {
                fileList=obj_client.getFiles();
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            //String fileList[] = new String[30];
            String[] inputlist = new String[fileList.size()];
            inputlist = fileList.toArray(inputlist);
            System.out.println("In receive before pane");
        String fileName = (String) JOptionPane.showInputDialog(this, "Choose a File to Download/Receive", "Input", JOptionPane.QUESTION_MESSAGE,
        null, inputlist, "Titan");
        System.out.println("In receive after pane");
        System.out.println(fileName);
           ClientFTP.dout.writeUTF("GET");
     datasoc=new Socket(Host,ClientFTP.dataPort);
    datadin=new DataInputStream(datasoc.getInputStream());
    dataout=new DataOutputStream(datasoc.getOutputStream());
        dout.writeUTF(fileName);
        String msgFromServer=din.readUTF();
        
        if(msgFromServer.compareTo("File Not Found")==0)
        {
            System.out.println("File not found on Server ...");
            return;
        }
        else if(msgFromServer.compareTo("READY")==0)
        {
            System.out.println("Receiving File ...");
            File f=new File(fileName);
            if(f.exists())
            {
                String Option;
                System.out.println("File Already Exists. Want to OverWrite (Y/N) ?");
                //Option=br.readLine();  
                MainFrame obj_main=new MainFrame();
                Option=obj_main.getChoice();
                if(Option=="N")    
                {
                    dout.flush();
                    return;    
                }                
            }
            FileOutputStream fout=new FileOutputStream(f);
            int ch;
            String temp;
            int count=0;
            double receivelength,filelength;
            filelength=din.readDouble();
            receivelength=filelength/1000;
            
            do
            {
                          if (count > receivelength){
                //System.out.println("in client" + updatelength);
               
                 double progValue=((receivelength/filelength)*100);
                 System.out.println("size" + (int) progValue);
                 receivelength+=receivelength;
                 jProgressBar1.setValue((int) progValue);
                 jProgressBar1.update(jProgressBar1.getGraphics());
                            
            }
            count++;
                temp=datadin.readUTF();
                ch=Integer.parseInt(temp);
                if(ch!=-1)
                {
                    fout.write(ch);                    
                }
            }while(ch!=-1);
            fout.close();
            System.out.println(din.readUTF());
            jProgressBar1.setValue(100);
                
        }
       }
        else if(Comand.compareTo("list")==0){
            ArrayList<String> fileList= new ArrayList<String>();
            try {
                fileList= obj_client.getList();
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
           String[] inputlist = new String[fileList.size()];
            inputlist = fileList.toArray(inputlist);
         
        JOptionPane.showMessageDialog(this, inputlist, "List of Files/Folders", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (Comand.compareTo("cd")==0){
        String status="false";
        String selection = (String) JOptionPane.showInputDialog(this, "Enter a Path:", "Input", JOptionPane.QUESTION_MESSAGE,
        null, null,null);
        System.out.println(selection);
        if (selection.isEmpty()){
            JOptionPane.showMessageDialog(this,"You didn't enter any path" , "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
        
            try {
               status= obj_client.setCD(selection);
                myinitComponents();
                 if (status.compareTo("false")==0){
                     JOptionPane.showMessageDialog(this,"Directory not Exist" , "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
        else if (Comand.compareTo("delete")==0){
              ArrayList<String> fileList= new ArrayList<String>();
            try {
                fileList=obj_client.getFiles();
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            //String fileList[] = new String[30];
            String[] inputlist = new String[fileList.size()];
            inputlist = fileList.toArray(inputlist);
         
        String selection = (String) JOptionPane.showInputDialog(this, "Choose a File to delete", "Input", JOptionPane.QUESTION_MESSAGE,
        null, inputlist, "Titan");
        System.out.println(selection);
            try {
                obj_client.deleteFile(selection);
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (Comand.compareTo("mkdir")==0){
         String status="false";
            String selection = (String) JOptionPane.showInputDialog(this, "Enter a Directory name:", "Input", JOptionPane.QUESTION_MESSAGE,
        null, null,null);
        System.out.println(selection);
        if (selection.isEmpty()){
            JOptionPane.showMessageDialog(this,"You didn't enter any input" , "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
        
            try {
                status=obj_client.setNewDir(selection);
                if (status.compareTo("false")==0){
                    JOptionPane.showMessageDialog(this,"Directory not created" , "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
        else if (Comand.compareTo("rmdir")==0){
            String status="false";
         ArrayList<String> fileList= new ArrayList<String>();
            try {
                fileList=obj_client.getDir();
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            //String fileList[] = new String[30];
            String[] inputlist = new String[fileList.size()];
            inputlist = fileList.toArray(inputlist);
         
        String selection = (String) JOptionPane.showInputDialog(this, "Choose a Folder to delete", "Input", JOptionPane.QUESTION_MESSAGE,
        null, inputlist, "Titan");
        System.out.println(selection);
            try {
                status=obj_client.deleteFolder(selection);
                if (status.compareTo("false")==0){
                     JOptionPane.showMessageDialog(this,"Directory contains Files!!!!" , "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            JOptionPane.showMessageDialog(this,"Invalid Command Click Help Button" , "Error", JOptionPane.ERROR_MESSAGE);
        }
        jTextField1.setText("");
        jButton2.setEnabled(true);
        jProgressBar1.setValue(0);
        
}
 /*  public void updateProgress(double length,double filelength) {
       //MainFrame obj=new MainFrame();
    //System.out.println("in progress" + length);
    double progValue=((length/filelength)*100);
    //System.out.println("in progress "+ progValue);
    //Thread.sleep(100);
    System.out.println(SwingUtilities.isEventDispatchThread());
    jProgressBar1.setValue((int)progValue);
  
   }*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables

 

}
