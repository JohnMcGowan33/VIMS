/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DataAccess.InventoryFileReader;
import Model.Car;
import java.awt.Button;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author msalehan
 */
public class InventoryItemPanel extends JPanel{
  
    
     NumberFormat formatter = NumberFormat.getCurrencyInstance();
    JLabel label = new JLabel("Lexus");
    Button button = new Button("View More Details");
    ImageIcon icon;
    Button buy = new Button("BUY NOW");
    
   

    
   
   
    Car car;
    ArrayList<Car> cars;
    
 
    
    
    public InventoryItemPanel(ArrayList<Car> cars, Car car){
        setLayout(new GridLayout(2,1));
        label.setText("<html>"+ car.getYear() +" "+ car.getModel() +" "+ car.getMake() + "<br>"+ car.getMiles() + " Miles" +"</br>" + "<br>" + formatter.format(car.getPrice()) +"</br>"+ "</html>");
       
        buy.setPreferredSize(new Dimension (30,20));
        String userPath = System.getProperty("user.dir");
        
        icon = new ImageIcon(userPath + "/src/Images/"+car.getImage());
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(newimg);  
         JLabel photoLabel = new JLabel(icon1);
//Improve This
          add(label);
        add(button);
        //add(buy);
        add(photoLabel);
       
          
        this.cars = cars;
        this.car = car;
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
            
            });
        
         buy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
            
            });
       
 

    }
    
   
    //ViewCar
     private void jButton1ActionPerformed(ActionEvent evt) {
        ViewCarForm form = new ViewCarForm(cars, car);
        form.setVisible(true);
       
        Object source = evt.getSource();
        if (source instanceof Component) {
            Component c = (Component) source;
            Frame frame = JOptionPane.getFrameForComponent(c);
            
            if(frame != null) {
                frame.dispose();
            }
        }
     }
     
     private void jButton2ActionPerformed(ActionEvent evt) {
         String url = "https://www.gofundme.com/mcgowan-car-dealership";

        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + url);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
     

    public void getCars() {
        
    }
    
}
