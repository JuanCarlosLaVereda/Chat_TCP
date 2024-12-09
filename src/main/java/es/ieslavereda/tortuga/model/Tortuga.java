package es.ieslavereda.tortuga.model;

import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;

public class Tortuga {
    private int id;
    private ProgressBar progressBar;
    private Thread thread;

    public Tortuga(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public int getId() {
        return id;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void andar(){
        TaskAndar taskAndar = new TaskAndar();
        thread = new Thread(taskAndar);
        thread.start();
    }
    public class TaskAndar extends Task<Double> {
        public TaskAndar() {

        }

        @Override
        protected Double call() throws Exception {
            while (true){
                synchronized (progressBar){
                    int num = (int) (Math.random() * 100)+1;
                    if (num >=1 && num <= 50){
                        progressBar.setProgress(progressBar.getProgress()+0.03);
                    } else if (num >50 && num <= 70 && !(progressBar.getProgress()<0.06)) {
                        progressBar.setProgress(progressBar.getProgress()-0.06);
                    } else {
                        progressBar.setProgress(progressBar.getProgress()+0.01);
                    }
                }
                Thread.sleep(100);
            }
        }
    }

}
