/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package proyecto1_ajedrezchino;

import java.util.Calendar;

/**
 *
 * @author royum
 */
public interface GuardarPlayers {

    boolean AgregarUsuario(String NombreUsuario, String Contrasena, Calendar FechaIngreso);

    User IniciarSesion(String nombre, String Contrasena);

    boolean ExisteUsuario(String nombre);

}
