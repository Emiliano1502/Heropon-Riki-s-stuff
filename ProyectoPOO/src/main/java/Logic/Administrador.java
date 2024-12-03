/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;
public class Administrador extends Usuario {

    private Usuario.Materia especialidad;

    public Administrador(String id, String nombre, String correo, String contraseña, String fechaNacimiento, Usuario.Sexo sexo) {
        super(id, nombre, correo, contraseña, fechaNacimiento, sexo, Usuario.Usuarios.Administrador);
        this.especialidad = Usuario.Materia.Na;
    }

    public static boolean validarContraseñaAdmin(String contraseñaAdmin) {
        return "admin123".equals(contraseñaAdmin);
    }

    public void verEstadisticas() {
    }
}
