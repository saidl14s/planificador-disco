
package algoritmosplanificaciondisco;

public class Base {
    
    private int cilindros;
    private int[] cola_solicitudes;
    private int pos_inicial;
    
    public Base(){
        this.cilindros = -1;
        this.pos_inicial = -1;
    }

    /**
     * @return the cilindros
     */
    public int getCilindros() {
        return cilindros;
    }

    /**
     * @param cilindros the cilindros to set
     */
    public void setCilindros(int cilindros) {
        this.cilindros = cilindros;
    }

    /**
     * @return the cola_solicitudes
     */
    public int[] getCola_solicitudes() {
        return cola_solicitudes;
    }

    /**
     * @param cola_solicitudes the cola_solicitudes to set
     */
    public void setCola_solicitudes(int[] cola_solicitudes) {
        this.cola_solicitudes = cola_solicitudes;
    }

    /**
     * @return the pos_inicial
     */
    public int getPos_inicial() {
        return pos_inicial;
    }

    /**
     * @param pos_inicial the pos_inicial to set
     */
    public void setPos_inicial(int pos_inicial) {
        this.pos_inicial = pos_inicial;
    }
    
}
