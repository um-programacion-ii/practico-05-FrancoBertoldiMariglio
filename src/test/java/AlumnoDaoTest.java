import exceptions.DataAccessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AlumnoDaoTest {

    private AlumnoDao alumnoDao;
    private Database dbMock;
    private HashMap<Integer, Alumno> tablaAlumnoTest;

    @BeforeEach
    public void setUp() {
        alumnoDao = new AlumnoDao();
    }

    @Test
    public void findAllTest() throws DataAccessException {
        alumnoDao.add(new Alumno("Juan", "Perez", 20));
        alumnoDao.add(new Alumno("Rosa", "Gutierrez", 30));
        dbMock = Mockito.mock(Database.class);
        Database.setInstance(dbMock);
        tablaAlumnoTest = new HashMap<>();
        tablaAlumnoTest.put(0, new Alumno("Juan", "Perez", 20));
        tablaAlumnoTest.put(1, new Alumno("Rosa", "Gutierrez", 30));
        when(dbMock.getTablaAlumno()).thenReturn(tablaAlumnoTest);

        HashMap<Integer, Alumno> test = dbMock.getTablaAlumno();
        HashMap<Integer, Alumno> result = alumnoDao.findAll();

        assertEquals(test, result);
    }

    @Test
    public void findByAlumnoIdTestCorrect() throws DataAccessException {
        dbMock = Mockito.mock(Database.class);
        Database.setInstance(dbMock);
        tablaAlumnoTest = new HashMap<>();
        tablaAlumnoTest.put(0, new Alumno("Juan", "Perez", 20));
        tablaAlumnoTest.put(1, new Alumno("Rosa", "Gutierrez", 30));
        when(dbMock.getTablaAlumno()).thenReturn(tablaAlumnoTest);

        Alumno retrievedAlumno = alumnoDao.findByAlumnoId(0);
        assertEquals(tablaAlumnoTest.get(0), retrievedAlumno);
    }

    @Test
    public void findByAlumnoIdTestWrong() {
        dbMock = Mockito.mock(Database.class);
        Database.setInstance(dbMock);
        tablaAlumnoTest = new HashMap<>();
        tablaAlumnoTest.put(0, new Alumno("Juan", "Perez", 20));
        tablaAlumnoTest.put(1, new Alumno("Rosa", "Gutierrez", 30));
        when(dbMock.getTablaAlumno()).thenReturn(tablaAlumnoTest);

        assertThrows(NoSuchElementException.class, () -> alumnoDao.findByAlumnoId(10));
    }

    @Test
    public void addTest() throws DataAccessException {
        dbMock = Mockito.mock(Database.class);
        Database.setInstance(dbMock);
        tablaAlumnoTest = new HashMap<>();
        tablaAlumnoTest.put(0, new Alumno("Juan", "Perez", 20));
        tablaAlumnoTest.put(1, new Alumno("Rosa", "Gutierrez", 30));
        when(dbMock.getTablaAlumno()).thenReturn(tablaAlumnoTest);

        Alumno newAlumno = new Alumno("Maria", "Gomez", 22);
        alumnoDao.add(newAlumno);
        verify(dbMock.getTablaAlumno(), times(1)).put(anyInt(), eq(newAlumno));
    }

    @Test
    public void deleteTest() throws DataAccessException {
        dbMock = Mockito.mock(Database.class);
        Database.setInstance(dbMock);
        tablaAlumnoTest = new HashMap<>();
        tablaAlumnoTest.put(0, new Alumno("Juan", "Perez", 20));
        tablaAlumnoTest.put(1, new Alumno("Rosa", "Gutierrez", 30));
        when(dbMock.getTablaAlumno()).thenReturn(tablaAlumnoTest);

        alumnoDao.delete(0);
        verify(dbMock.getTablaAlumno(), times(1)).remove(0);
    }

    @Test
    public void updateTest() throws DataAccessException {
        dbMock = Mockito.mock(Database.class);
        Database.setInstance(dbMock);
        tablaAlumnoTest = new HashMap<>();
        tablaAlumnoTest.put(0, new Alumno("Juan", "Perez", 20));
        tablaAlumnoTest.put(1, new Alumno("Rosa", "Gutierrez", 30));
        when(dbMock.getTablaAlumno()).thenReturn(tablaAlumnoTest);

        Alumno updatedAlumno = new Alumno("Maria", "Gomez", 22);
        alumnoDao.update(updatedAlumno, 0);
        verify(dbMock.getTablaAlumno(), times(1)).put(0, updatedAlumno);
    }
}