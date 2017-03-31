/**
 * Created by student on 31.03.2017.
 */
import view.*;
import javax.swing.*;
public class MainClass {
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                                                   public void run() {

                                                       new View();
                                                   }
                                               }
        );
    }
}
