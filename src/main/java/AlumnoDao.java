import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.NoArgsConstructor;

import exceptions.DataAccessException;

@NoArgsConstructor
public class AlumnoDao {

    private static final Logger LOGGER = Logger.getLogger(AlumnoDao.class.getName());
    private static Database db = Database.getInstance();
    private int ID = 0;

    public void add(Alumno alumno) throws DataAccessException {
        try {
            db.getTablaAlumno().put(ID, alumno);
            ID++;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error adding Alumno", e);
            throw new DataAccessException("Error adding Alumno", e);
        }
    }

    public Alumno findByAlumnoId(int ID) throws DataAccessException, NoSuchElementException {
        try {
            Alumno alumno = db.getTablaAlumno().get(ID);
            if (alumno == null) {
                throw new NoSuchElementException("No Alumno with ID " + ID);
            }
            return alumno;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error finding Alumno by ID", e);
            throw new DataAccessException("Error finding Alumno by ID", e);
        }
    }

    public HashMap<Integer, Alumno> findAll() throws DataAccessException {
        try {
            return db.getTablaAlumno();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error finding all Alumnos", e);
            throw new DataAccessException("Error finding all Alumnos", e);
        }
    }

    public Alumno update(Alumno alumno, int ID) throws DataAccessException, NoSuchElementException {
        try {
            Alumno oldAlumno = db.getTablaAlumno().put(ID, alumno);
            if (oldAlumno == null) {
                throw new NoSuchElementException("No Alumno with ID " + ID);
            }
            return db.getTablaAlumno().get(ID);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error updating Alumno", e);
            throw new DataAccessException("Error updating Alumno", e);
        }
    }

    public void delete(int ID) throws DataAccessException, NoSuchElementException {
        try {
            Alumno removedAlumno = db.getTablaAlumno().remove(ID);
            if (removedAlumno == null) {
                throw new NoSuchElementException("No Alumno with ID " + ID);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error deleting Alumno", e);
            throw new DataAccessException("Error deleting Alumno", e);
        }
    }
}