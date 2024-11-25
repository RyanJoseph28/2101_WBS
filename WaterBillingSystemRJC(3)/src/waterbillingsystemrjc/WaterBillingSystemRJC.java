// PUSH IN GITHUB 11/22/2024 
package waterbillingsystemrjc;

import javax.swing.SwingUtilities;

public class WaterBillingSystemRJC {

  public static void main(String[] args) {
        
       
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Login LoginFrame = new Login();
                LoginFrame.setVisible(true);
                LoginFrame.pack();
                LoginFrame.setLocationRelativeTo(null);
            }
        });
    }
}