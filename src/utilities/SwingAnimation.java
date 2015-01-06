package utilities;

import java.util.ArrayList;
import javax.swing.JLabel;

/**
 * The class creates animations for Swing Components
 *
 * @author Tal
 */
public class SwingAnimation {

    /**
     * @param start - The pixel value where the component will start the animation from.
     * @param stop - The pixel value where the component will stop moving.
     * @param delay - Delay between each pixel increment.
     * @param increment - The number of pixels to move.
     * @param label - The variable name of the JLabel you want to move.
     */
    public void jLabelYUp(final int start, final int stop, final int delay, final int increment, final JLabel label) {
        JLabelYUp jxf = new JLabelYUp(start, stop, delay, increment, label);
        jxf.start();
    }

    /**
     * @param start - The pixel value where the component will start the animation from.
     * @param stop - The pixel value where the component will stop moving.
     * @param delay - Delay between each pixel increment.
     * @param increment - The number of pixels to move.
     * @param label - The variable name of the JLabel you want to move.
     */
    public void jLabelYDown(final int start, final int stop, final int delay, final int increment, final JLabel label) {
        JLabelYDown jxf = new JLabelYDown(start, stop, delay, increment, label);
        jxf.start();
    }

    /**
     * @param start - The pixel value where the component will start the animation from.
     * @param stop - The pixel value where the component will stop moving.
     * @param delay - Delay between each pixel increment.
     * @param increment - The number of pixels to move.
     * @param label - The variable name of the JLabel you want to move.
     */
    public void jLabelXLeft(int start, int stop, int delay, int increment, JLabel label) {
        JLabelXLeft jxf = new JLabelXLeft(start, stop, delay, increment, label);
        jxf.start();
    }

    /**
     * @param start - The pixel value where the component will start the animation from.
     * @param stop - The pixel value where the component will stop moving.
     * @param delay - Delay between each pixel increment.
     * @param increment - The number of pixels to move.
     * @param label - The variable name of the JLabel you want to move.
     */
    public void jLabelXRight(final int start, final int stop, final int delay, final int increment, final JLabel label) {
        JLabelXRight jxf = new JLabelXRight(start, stop, delay, increment, label);
        jxf.start();
    }

    public class JLabelYUp extends Thread implements Runnable {

        private int start;
        private int stop;
        private int delay;
        private int increment;
        private JLabel label;

        public JLabelYUp(int start, int stop, int delay, int increment, JLabel label) {
            this.start = start;
            this.stop = stop;
            this.delay = delay;
            this.increment = increment;
            this.label = label;
        }

        @Override
        public void run() {
            super.run();
            if (label.getY() == start) {
                for (int i = start; i >= stop; i -= increment) {
                    try {
                        label.setLocation(label.getX(), i);
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        if (Constants.DEBUG) {
                            System.out.println("Error Thread Interrupted: " + e);
                        }
                    }
                }
            }
        }

    }

    public class JLabelYDown extends Thread implements Runnable {

        private int start;
        private int stop;
        private int delay;
        private int increment;
        private JLabel label;

        public JLabelYDown(int start, int stop, int delay, int increment, JLabel label) {
            this.start = start;
            this.stop = stop;
            this.delay = delay;
            this.increment = increment;
            this.label = label;
        }

        @Override
        public void run() {

            if (label.getY() == start) {
                for (int i = start; i <= stop; i += increment) {
                    try {
                        label.setLocation(label.getX(), i);
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        if (Constants.DEBUG) {
                            System.out.println("Error Thread Interrupted: " + e);
                        }
                    }
                }
            }
        }
    }

    public class JLabelXLeft extends Thread implements Runnable {

        private int start;
        private int stop;
        private int delay;
        private int increment;
        private JLabel label;

        public JLabelXLeft(int start, int stop, int delay, int increment, JLabel label) {
            this.start = start;
            this.stop = stop;
            this.delay = delay;
            this.increment = increment;
            this.label = label;
        }

        @Override
        public void run() {
            super.run();
            if (label.getX() == start) {
                for (int i = start; i >= stop; i -= increment) {
                    try {
                        label.setLocation(i, label.getY());
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        if (Constants.DEBUG) {
                            System.out.println("Error Thread Interrupted: " + e);
                        }
                    }
                }
            }
        }

    }

    public class JLabelXRight extends Thread implements Runnable {

        private int start;
        private int stop;
        private int delay;
        private int increment;
        private JLabel label;

        public JLabelXRight(int start, int stop, int delay, int increment, JLabel label) {
            this.start = start;
            this.stop = stop;
            this.delay = delay;
            this.increment = increment;
            this.label = label;
        }

        @Override
        public void run() {
            super.run();
            if (label.getX() == start) {
                for (int i = start; i <= stop; i += increment) {
                    try {
                        label.setLocation(i, label.getY());
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        if (Constants.DEBUG) {
                            System.out.println("Error Thread Interrupted: " + e);
                        }
                    }
                }
            }
        }
    }

    public void jLabelXSideAndReturn(int stop, int delay, int increment, ArrayList< JLabel> cardsArray, boolean startToRight) {
        new JLabelXSideAndReturn(stop, delay, increment, startToRight, cardsArray).start();
    }

    public class JLabelXSideAndReturn extends Thread implements Runnable {

        private int stop;
        private int delay;
        private int increment;
        private ArrayList< JLabel> cardsArray;
        private boolean startToRight;

        public JLabelXSideAndReturn(int stop, int delay, int increment, boolean startToRight, ArrayList< JLabel> label) {
            this.stop = stop;
            this.delay = delay;
            this.increment = increment;
            this.startToRight = startToRight;
            this.cardsArray = label;
        }

        @Override
        public void run() {
            super.run();
            if (startToRight) {
                for (JLabel jl : cardsArray) {
                    new JLabelXRight(jl.getLocation().x, jl.getLocation().x + stop, delay, increment, jl).start();
                }
                try {
                    Thread.sleep(stop * delay * cardsArray.size() / 2);
                } catch (InterruptedException ex) {
                    if (Constants.DEBUG) {
                        System.out.println(ex.getMessage());
                    }
                }
                for (JLabel jl : cardsArray) {
                    new JLabelXLeft(jl.getLocation().x, jl.getLocation().x - stop, delay, increment, jl).start();
                }
            } else {
                for (JLabel jl : cardsArray) {
                    new JLabelXLeft(jl.getLocation().x, jl.getLocation().x - stop, delay, increment, jl).start();
                }
                try {
                    Thread.sleep(stop * delay * cardsArray.size() / 2);
                } catch (InterruptedException ex) {
                    if (Constants.DEBUG) {
                        System.out.println(ex.getMessage());
                    }
                }
                for (JLabel jl : cardsArray) {
                    new JLabelXRight(jl.getLocation().x, jl.getLocation().x + stop, delay, increment, jl).start();
                }
            }
        }
    }
}
