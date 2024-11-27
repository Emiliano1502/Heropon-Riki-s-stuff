package Logic;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivoUsuarios {

    private static final String FILE_NAME = "BD.txt";

    // Método para leer los usuarios desde el archivo
    public static List<JSONObject> leerUsuarios() {
        List<JSONObject> usuarios = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            JSONArray jsonArray = new JSONArray(sb.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                usuarios.add(jsonArray.getJSONObject(i));
            }

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado, creando uno nuevo...");
            guardarUsuarios(new JSONArray());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    // Método para guardar los usuarios en el archivo
    public static void guardarUsuarios(JSONArray usuarios) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            bw.write(usuarios.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para agregar un usuario
    public static void guardarUsuario(JSONObject nuevoUsuario) {
        List<JSONObject> usuarios = leerUsuarios();
        usuarios.add(nuevoUsuario);

        JSONArray jsonArray = new JSONArray(usuarios);
        guardarUsuarios(jsonArray);
    }

    // Método para buscar un usuario por ID y contraseña
    public static JSONObject buscarUsuario(String id, String contrasena) {
        List<JSONObject> usuarios = leerUsuarios();

        for (JSONObject usuario : usuarios) {
            if (usuario.getString("id").equals(id) && usuario.getString("contraseña").equals(contrasena)) {
                return usuario;
            }
        }
        return null; // Usuario no encontrado
    }

    public static boolean buscarUsuarioPorCorreo(String correo) {
        try (FileReader reader = new FileReader(FILE_NAME)) {
            // Leer el contenido del archivo JSON
            StringBuilder contenido = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                contenido.append((char) c);
            }

            // Parsear el contenido JSON
            JSONArray listaUsuarios = new JSONArray(contenido.toString());

            // Recorrer la lista para buscar el correo
            for (int i = 0; i < listaUsuarios.length(); i++) {
                JSONObject usuario = listaUsuarios.getJSONObject(i);
                String email = usuario.getString("correo");

                if (email != null && email.equals(correo)) {
                    // Usuario encontrado
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // Usuario no encontrado
    }

    public static boolean actualizarUsuarioPorCorreo(String correo, JSONObject usuarioActualizado) {
        // Paso 1: Crear el archivo temporal "prueba.txt"
        File archivoTemporal = new File("prueba.txt");

        try {
            // Leer los usuarios actuales desde el archivo BD.txt
            List<JSONObject> usuarios = leerUsuarios();

            // Paso 2: Iterar sobre los usuarios existentes
            boolean usuarioEncontrado = false;
            List<JSONObject> usuariosActualizados = new ArrayList<>();

            for (JSONObject usuario : usuarios) {
                // Si el correo coincide, actualiza los datos del usuario
                if (usuario.getString("correo").equals(correo)) {
                    usuariosActualizados.add(usuarioActualizado);
                    usuarioEncontrado = true;
                } else {
                    // Si no, conserva el usuario original
                    usuariosActualizados.add(usuario);
                }
            }

            if (!usuarioEncontrado) {
                System.out.println("Usuario no encontrado.");
                return false; // Si no se encuentra el usuario con ese correo
            }

            // Paso 3: Guardar el arreglo de usuarios actualizados en el archivo temporal
            try (FileWriter writer = new FileWriter(archivoTemporal)) {
                writer.write(usuariosActualizados.toString());
            }

            // Paso 4: Renombrar el archivo "prueba.txt" a "BD.txt"
            File archivoOriginal = new File("BD.txt");
            if (archivoOriginal.exists()) {
                archivoOriginal.delete(); // Elimina el archivo original
            }
            archivoTemporal.renameTo(archivoOriginal); // Renombra el archivo temporal

            return true; // Actualización exitosa
        } catch (IOException e) {
            System.out.println("Error al procesar los archivos: " + e.getMessage());
            return false;
        }
    }
}
