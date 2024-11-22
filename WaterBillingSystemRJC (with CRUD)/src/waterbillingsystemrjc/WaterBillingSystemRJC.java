//PUSHED IN GITHUB
package waterbillingsystemrjc;

import javax.swing.SwingUtilities;

public class WaterBillingSystemRJC {

  public static void main(String[] args) {
        
        // Ensuring that the GUI is run on the Event Dispatch Thread
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