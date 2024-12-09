package es.ieslavereda.tortuga.model;

import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;

public class Animal {
    ProgressBar tortuga;
    ProgressBar liebre;
    String nombre;
    ProgressBar sulbl;
    String ganador;
    public Animal(ProgressBar tortuga, ProgressBar liebre, String nombre, ProgressBar sulbl) {
        this.tortuga = tortuga;
        this.liebre = liebre;
        this.nombre = nombre;
        this.sulbl = sulbl;
    }

    public ProgressBar getTortuga() {
        return tortuga;
    }

    public ProgressBar getLiebre() {
        return liebre;
    }

    public void andar(){
        Thread thread = new Thread(new TaskAndar());
        thread.start();
    }

    public class TaskAndar extends Task<Double> {
        public TaskAndar() {

        }

        @Override
        protected Double call() throws Exception {
            while (true){
                if (nombre == "tortuga"){
                    synchronized (sulbl){
                        int num = (int) (Math.random() * 100)+1;
                        if (num >=1 && num <= 50){
                            sulbl.setProgress(sulbl.getProgress()+0.03);
                        } else if (num >50 && num <= 70 && !(sulbl.getProgress()<0.06)) {
                            sulbl.setProgress(sulbl.getProgress()-0.06);
                        } else {
                            sulbl.setProgress(sulbl.getProgress()+0.01);
                        }
                        if (tortuga.getProgress()>=1){
                            ganador = "tortuga";
                            System.out.println("El ganador es: " + ganador);
                            break;
                        } else if (liebre.getProgress()>=1) {
                            ganador = "liebre";
                            break;
                        }
                        Thread.sleep(100);
                    }
                } else {
                    synchronized (sulbl){
                        int num = (int) (Math.random() * 100)+1;
                        if (num >=1 && num <= 20){
                            Thread.sleep(100);
                        } else if (num >20 && num <= 40) {
                            sulbl.setProgress(sulbl.getProgress()+0.09);
                        } else if (num >40 && num <= 50 && !(sulbl.getProgress()<0.12)) {
                            sulbl.setProgress(sulbl.getProgress()-0.12);
                        } else if (num >50 && num <= 80) {
                            sulbl.setProgress(sulbl.getProgress()+0.01);
                        } else if (!(sulbl.getProgress() < 0.02)){
                            sulbl.setProgress(sulbl.getProgress()-0.02);
                        }
                        if (tortuga.getProgress()>=1){
                            ganador = "tortuga";
                            break;
                        } else if (liebre.getProgress()>=1) {
                            ganador = "liebre";
                            System.out.println("El ganador es: " + ganador);
                            break;
                        }
                        Thread.sleep(100);
                    }
                }
            }
            return null;
        }
    }
}
