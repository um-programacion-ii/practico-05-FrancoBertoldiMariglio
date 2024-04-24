import java.util.HashMap;

public class Database {

    private HashMap<Integer, Alumno> tablaAlumno = new HashMap<>();

    private static Database instance;

    private Database() {
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public static void setInstance(Database newInstance) {
        instance = newInstance;
    }

    public HashMap<Integer, Alumno> getTablaAlumno() {
        return tablaAlumno;
    }
}
