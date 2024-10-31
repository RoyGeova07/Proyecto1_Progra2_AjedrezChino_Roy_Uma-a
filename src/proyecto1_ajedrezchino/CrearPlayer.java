/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_ajedrezchino;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.*;

/**
 *
 * @author royum
 */
public class CrearPlayer extends JFrame {

    public static MenuPrincipal menu;
    private JTextField TextoNombre;
    private JPasswordField Textocontrasena;
    private JTextField fechaIngresoField;
    private String UsuarioActual;
    private Calendar fechaActual;  
    private UserManager userManager;  
    private JFrame frame;
    User usuario;

    private GuardarPlayers guardarplayers;

    public CrearPlayer(GuardarPlayers guardarplayers) {
        this.guardarplayers = guardarplayers;

        MenuPrincipal menu;

        setTitle("Crear Jugador");
        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE); 

        
        JPanel PanelMenu = new JPanel();
        PanelMenu.setLayout(null);
        PanelMenu.setBackground(Color.YELLOW);

        
        JLabel Nombre = new JLabel("Ingrese un nombre de usuario");
        Nombre.setBounds(20, 50, 200, 30);
        PanelMenu.add(Nombre);

        TextoNombre = new JTextField(20);
        TextoNombre.setBounds(260, 50, 200, 30);
        PanelMenu.add(TextoNombre);

        
        JLabel Contrasena = new JLabel("Ingrese una contraseña (5 caracteres)");
        Contrasena.setBounds(20, 120, 250, 30);
        PanelMenu.add(Contrasena);

        Textocontrasena = new JPasswordField(20);
        Textocontrasena.setBounds(260, 120, 200, 30);
        PanelMenu.add(Textocontrasena);

    
        JLabel labelFecha = new JLabel("Fecha de registro:");
        labelFecha.setBounds(20, 160, 200, 30);
        PanelMenu.add(labelFecha);

        fechaIngresoField = new JTextField(20);
        fechaIngresoField.setBounds(260, 160, 200, 30);
        fechaIngresoField.setEditable(false);  
        PanelMenu.add(fechaIngresoField);

        JButton GenerarFecha = new JButton("Generar Fecha");
        GenerarFecha.setBackground(Color.cyan);
        GenerarFecha.setBounds(130, 240, 150, 40);
        GenerarFecha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fechaActual = Calendar.getInstance();
                fechaIngresoField.setText(fechaActual.get(Calendar.DAY_OF_MONTH) + "/"
                        + (fechaActual.get(Calendar.MONTH) + 1) + "/"
                        + fechaActual.get(Calendar.YEAR));
            }
        });
        PanelMenu.add(GenerarFecha);

        JButton Crear = new JButton("Crear Player");
        Crear.setBackground(Color.magenta);
        Crear.setBounds(320, 240, 120, 40);
        Crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuarios();
            }
        });
        PanelMenu.add(Crear);

        JButton Regresar = new JButton("Regresar");
        Regresar.setBackground(Color.orange);
        Regresar.setBounds(320, 300, 120, 40);
        Regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuInicio inicio = new MenuInicio();
                dispose();
            }
        });
        PanelMenu.add(Regresar);

        
        add(PanelMenu);
        setVisible(true);

        userManager = new UserManager(); 
    }

    protected void registrarUsuarios() {
        
        
        String nombre = TextoNombre.getText().trim();
        String contrasena = Textocontrasena.getText().trim();

        try{
        
        if (nombre.isEmpty() || contrasena.isEmpty() || fechaActual == null) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben ser completados.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

  
        if (contrasena.length() != 5) {
           JOptionPane.showMessageDialog(null, "La contraseña debe tener exactamente 5 caracteres.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

     
        if (guardarplayers.ExisteUsuario(nombre)) {
            JOptionPane.showMessageDialog(null, "El usuario ya existe. Por favor, elija otro nombre.", "Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }

        
        String fechaIngreso = fechaIngresoField.getText();
        System.out.println("Creando usuario: " + nombre);

        if (guardarplayers.AgregarUsuario(nombre, contrasena, fechaActual)) {
            JOptionPane.showMessageDialog(null, "Usuario " + nombre + " creado exitosamente. Bienvenido", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            User usuarioCreado = guardarplayers.IniciarSesion(nombre, contrasena);
            MenuPrincipal entrar = new MenuPrincipal(usuarioCreado, userManager);
            entrar.setVisible(true);
            dispose();
        } else {
            
            JOptionPane.showMessageDialog(null, "Error al crear el usuario. Inténtalo de nuevo.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

       
        TextoNombre.setText("");
        Textocontrasena.setText("");
        fechaIngresoField.setText("");
        fechaActual = null;
        

        } catch (Exception e ){
             JOptionPane.showMessageDialog(null, "Error inesperado","ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }
}
