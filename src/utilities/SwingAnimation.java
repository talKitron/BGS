package utilities;

import javax.swing.JLabel;

/**
 * The class creates animations for Swing Components
 * @author Tal
 */
public class SwingAnimation {
    
    /**
     * @param start - The pixel value where the component will start the animation from.
     * @param stop - The pixel value where the component will stop moving.
     * @param delay - Delay between each pixel increment.
     * @param increment - The number of pixels to move.
     * @param jLabel - The variable name of the JLabel you want to move.
     */
    public void jLabelYUp(final int start, final int stop, final int delay, final int increment, final JLabel jLabel) {
    if (jLabel.getY() == start) {
      new Thread() {
        @Override
        public void run() {
          while (jLabel.getY() > stop) {
            for (int i = start; i >= stop; i -= increment) {
              try{
                Thread.sleep(delay);
                jLabel.setLocation(jLabel.getX(), i);
              } catch (InterruptedException e) {
                  if (Constants.DEBUG){
                    System.out.println("Error Thread Interrupted: " + e);
                  }
              }
            }
          }
          jLabel.setLocation(jLabel.getX(), stop);
        }
      }.start();
    }
  }
  
  /**
   * @param start - The pixel value where the component will start the animation from.
   * @param stop - The pixel value where the component will stop moving.
   * @param delay - Delay between each pixel increment.
   * @param increment - The number of pixels to move.
   * @param jLabel - The variable name of the JLabel you want to move.
   */
  public void jLabelYDown(final int start, final int stop, final int delay, final int increment, final JLabel jLabel) {
    if (jLabel.getY() == start) {
      new Thread() {
        @Override
        public void run() {
          while (jLabel.getY() <= start) {
            for (int i = start; i <= stop; i += increment) {
              try{
                Thread.sleep(delay);
                jLabel.setLocation(jLabel.getX(), i);
              } catch (InterruptedException e) {
                  if (Constants.DEBUG){
                    System.out.println("Error Thread Interrupted: " + e);
                  }
              }
            }
          }
          jLabel.setLocation(jLabel.getX(), stop);
        }
      }.start();
    }
  }
  
  /**
   * @param start - The pixel value where the component will start the animation from.
   * @param stop - The pixel value where the component will stop moving.
   * @param delay - Delay between each pixel increment.
   * @param increment - The number of pixels to move.
   * @param jLabel - The variable name of the JLabel you want to move. 
   */
  public void jLabelXLeft(final int start, final int stop, final int delay, final int increment, final JLabel jLabel) {
    if (jLabel.getX() == start) {
      new Thread() {
        @Override
        public void run() {
          while (jLabel.getX() > stop) {
            for (int i = start; i >= stop; i -= increment) {
              try{
                Thread.sleep(delay);
                jLabel.setLocation(i, jLabel.getY());
              } catch (InterruptedException e) {
                    if (Constants.DEBUG){
                        System.out.println("Error Thread Interrupted: " + e);
                    }
              }
            }
          }
          jLabel.setLocation(stop, jLabel.getY());
        }
      }.start();
    }
  }
  
  /**
   * @param start - The pixel value where the component will start the animation from.
   * @param stop - The pixel value where the component will stop moving.
   * @param delay - Delay between each pixel increment.
   * @param increment - The number of pixels to move.
   * @param jLabel - The variable name of the JLabel you want to move. 
   */
  public void jLabelXRight(final int start, final int stop, final int delay, final int increment, final JLabel jLabel) {
    if (jLabel.getX() == start) {
      new Thread() {
        @Override
        public void run() {
          while (jLabel.getX() <= start) {
            for (int i = start; i <= stop; i += increment) {
              try{
                Thread.sleep(delay);            
                jLabel.setLocation(i, jLabel.getY());
              } catch (InterruptedException e) {
                  if (Constants.DEBUG){
                    System.out.println("Error Thread Interrupted: " + e);
                  }
              }
            }
          }
          jLabel.setLocation(stop, jLabel.getY());
        }
      }.start();
    }
  }
}
