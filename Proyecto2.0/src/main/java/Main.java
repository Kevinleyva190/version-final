import Vista.Ventana;
import controlador.controladorInstrumento;
public class Main {
    public static void main(String[] args) {
    Ventana ven= new Ventana("Instrumentos");
    controladorInstrumento controller =new controladorInstrumento(ven);
    }
}