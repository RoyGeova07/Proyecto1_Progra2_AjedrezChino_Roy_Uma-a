/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_ajedrezchino;

/**
 *
 * @author royum
 */
public abstract class Pieza {

    protected Bando bando;

    public Pieza(Bando bando) {
        this.bando = bando;
    }

    
    public abstract boolean MovimientoPiezas(int filaOrigen, int columOrigen, int filaDestino, int columDestino);

    public Bando getBando() {
        return bando;
    }

    
}
