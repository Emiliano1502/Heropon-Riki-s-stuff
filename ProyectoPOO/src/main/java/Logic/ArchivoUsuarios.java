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
}

