package es.ieslavereda.tortuga.model;

import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;

public class Liebre {
    private int id;
    private ProgressBar progressBar;
    private Thread thread;

    public Liebre(ProgressBar progressBar) {
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
                    if (num >=1 && num <= 20){
                        Thread.sleep(100);
                    } else if (num >20 && num <= 40) {
                            progressBar.setProgress(progressBar.getProgress()+0.09);
                    } else if (num >40 && num <= 50 && (progressBar.getProgress()<0.12)) {
                        progressBar.setProgress(progressBar.getProgress()-0.12);
                    } else if (num >50 && num <= 80) {
                        progressBar.setProgress(progressBar.getProgress()+0.01);
                    } else if (!(progressBar.getProgress() < 0.02)){
                        progressBar.setProgress(progressBar.getProgress()-0.02);
                    }
                }
                Thread.sleep(100);
            }
        }
    }
}
