package es.ieslavereda.tortuga;

import es.ieslavereda.tortuga.model.Animal;
import es.ieslavereda.tortuga.model.Liebre;
import es.ieslavereda.tortuga.model.Tortuga;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

public class HelloController {
    @FXML
    private Button btnStart;
    @FXML
    private ProgressBar pbTortuga;
    @FXML
    private ProgressBar pbLiebre;
    @FXML
    protected void onHelloButtonClick() {
        pbTortuga.setProgress(0);
        pbLiebre.setProgress(0);
        Animal tortuga = new Animal(pbTortuga, pbLiebre, "tortuga", pbTortuga);
        Animal liebre = new Animal(pbTortuga, pbLiebre, "liebre", pbLiebre);
        tortuga.andar();
        liebre.andar();
    }


}