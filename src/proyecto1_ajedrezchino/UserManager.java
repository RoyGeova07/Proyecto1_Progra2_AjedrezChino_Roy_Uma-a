/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_ajedrezchino;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 *
 * @author royum
 */
public final class UserManager implements GuardarPlayers {

    public static int contador = 0;
    private final static User[] usuarios = new User[50];

    @Override
    public final boolean AgregarUsuario(String NombreUsuario, String Contrasena, Calendar FechaIngreso) {

        if (contador >= 50) {
            return false;
        }

        for (int i = 0; i < contador; i++) {
            if (usuarios[i] != null && usuarios[i].getNombre().equals(NombreUsuario)) {
                return false;
            }
        }

        usuarios[contador] = new User(NombreUsuario, Contrasena, 0, FechaIngreso);
        contador++;

        return true;

    }

    @Override
    public User IniciarSesion(String nombre, String Contrasena) {
        for (int aceptar = 0; aceptar < contador; aceptar++) {

            if (usuarios[aceptar].getNombre().equals(nombre) && usuarios[aceptar].getContrasena().equals(Contrasena)) {
                return usuarios[aceptar];
            }

        }
        return null;
    }

    @Override
    public boolean ExisteUsuario(String Nombre) {

        for (int exis = 0; exis < contador; exis++) {
            if (usuarios[exis].getNombre().equals(Nombre)) {
                return true;
            }
        }
        return false;

    }

    public User[] getUsuarios() {
        return usuarios;
    }

    /*
    1. Recursion
    Como funciona la recursion de Eliminar Usuario?
    
    esta funciona tiene como parametro User y index, index que es el indice actual desde que comenzamos con 
    la busqueda
    
    el caso base ocurre cuando hemos revisado todos los elementos, por decir asi index>=contador,
    y si no enontramos el usuario, se retorna false
    
    si encontramos al usuario, llamamos a la funcion de moverUsuario para desplazar los elementos y luego
    eliminamos el ultimo elemento del arreglo
    
     */
    // 1. funcion recursiva Eliminar Usuario
    public final boolean EliminarUsuario(User usuario, int index) {

        // aqui caso base : si se llega al final del arreglo sin encontrar el usuario
        if (index >= contador) {
            return false;
        }

        // si encontramos el usuario a eliminar
        if (usuarios[index] != null && usuarios[index].equals(usuario)) {

            // aqui para mover todos los elementos a la izquierda recursividad
            MoverUsuarios(index);
            usuarios[contador - 1] = null; // aqui se elimina la ultima referencia
            contador--; // se reduce
            return true;

        }

        // caso recursivo se continua buscando el siguiente indice
        return EliminarUsuario(usuario, index + 1);

    }

    /*
    
    2. Recursion
    
    esta funcion es recursiva tambien, su proposito es mover todos los elementos del arreglo
    a la izquierda despues de que se encuentren al usuario a eliminar
    
    el caso base para MoverUsuarios es cuando alcanzamos el final del arreglo donde no queda nada mas que mover.
    
     */
    // 2. funcion recursiva funcion para mover a los usuarios
    public void MoverUsuarios(int index) {

        //aqui caso base
        if (index < contador - 1) {

            // aqui se mueve el siguiente usuario hacia el indice actual
            usuarios[index] = usuarios[index + 1];
            // con metodo recursivo mover los siguiente usuarios
            MoverUsuarios(index + 1);

        }

    }
    
    public final static User[] RankingUsuarios(){
        
        User[] usuariosOrdenados=Arrays.copyOf(usuarios, contador);
        
        Arrays.sort(usuariosOrdenados, (u1, u2) -> Integer.compare(u2.getPuntos(), u1.getPuntos()));
        
        return usuariosOrdenados;
        
    }

}
