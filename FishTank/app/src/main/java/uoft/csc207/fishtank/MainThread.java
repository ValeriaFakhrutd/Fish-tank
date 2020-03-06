package uoft.csc207.fishtank;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Hacky way to manage threading and updates.
 */
public class MainThread extends Thread {

    /**
     * Where the fish tank items are drawn.
     */
    private FishTankView fishTankView;
    /**
     * The canvas container.
     */
    private SurfaceHolder surfaceHolder;
    /**
     * Whether the thread is running.
     */
    private boolean isRunning;
    /**
     * The canvas on which to draw the fish tank.
     */
    public static Canvas canvas;

    /**
     * Construct the thread.
     *
     * @param surfaceHolder the canvas container.
     * @param view          where the fish tank items are drawn.
     */
    public MainThread(SurfaceHolder surfaceHolder, FishTankView view) {
        this.surfaceHolder = surfaceHolder;
        this.fishTankView = view;
    }

    @Override
    public void run() {
        while (isRunning) {
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.fishTankView.update();
                    this.fishTankView.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            try {
                this.sleep(300);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
}
