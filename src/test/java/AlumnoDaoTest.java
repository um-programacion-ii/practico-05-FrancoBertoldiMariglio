import exceptions.DataAccessException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AlumnoDaoTest {

    private HashMap<Integer, Alumno> tablaAlumnoTest;
    private Database dbMock;
    private AlumnoDao alumnoDao;

    @BeforeEach
    public void setUp() {
        dbMock = Mockito.mock(Database.class);
        Database.setInstance(dbMock);
        tablaAlumnoTest = new HashMap<>();
        alumnoDao = new AlumnoDao();
    }

//    @AfterEach
//    public void tearDown() {
//        tablaAlumnoTest.clear();
//        Mockito.reset(dbMock);
//    }

    @Test
    public void findAllTest() throws DataAccessException {
        alumnoDao.add(new Alumno("Juan", "Perez", 20));
        alumnoDao.add(new Alumno("Rosa", "Gutierrez", 30));
        tablaAlumnoTest.put(0, new Alumno("Juan", "Perez", 20));
        tablaAlumnoTest.put(1, new Alumno("Rosa", "Gutierrez", 30));
        when(dbMock.getTablaAlumno()).thenReturn(tablaAlumnoTest);

        System.out.println(tablaAlumnoTest);

        HashMap<Integer, Alumno> test = dbMock.getTablaAlumno();
        HashMap<Integer, Alumno> result = alumnoDao.findAll();

        assertEquals(test, result);
    }

    @Test
    public void findByAlumnoIdTestCorrect() throws DataAccessException {
        tablaAlumnoTest.put(0, new Alumno("Juan", "Perez", 20));
        tablaAlumnoTest.put(1, new Alumno("Rosa", "Gutierrez", 30));
        when(dbMock.getTablaAlumno()).thenReturn(tablaAlumnoTest);

        Alumno retrievedAlumno = alumnoDao.findByAlumnoId(0);
        assertEquals(tablaAlumnoTest.get(0), retrievedAlumno);
    }

    @Test
    public void findByAlumnoIdTestWrong() {
        tablaAlumnoTest.put(0, new Alumno("Juan", "Perez", 20));
        tablaAlumnoTest.put(1, new Alumno("Rosa", "Gutierrez", 30));
        when(dbMock.getTablaAlumno()).thenReturn(tablaAlumnoTest);

        assertThrows(NoSuchElementException.class, () -> alumnoDao.findByAlumnoId(20));
    }

    @Test
    public void addTest() throws DataAccessException {
        Alumno newAlumno = new Alumno("Maria", "Gomez", 22);
        HashMap<Integer, Alumno> spyMap = Mockito.spy(new HashMap<>());

        when(dbMock.getTablaAlumno()).thenReturn(spyMap);

        alumnoDao.add(newAlumno);
        verify(spyMap, times(1)).put(anyInt(), eq(newAlumno));
    }

    @Test
    public void deleteTest() throws DataAccessException {
        HashMap<Integer, Alumno> spyMap = Mockito.spy(new HashMap<>());
        spyMap.put(30, new Alumno("Juan", "Perez", 20));

        when(dbMock.getTablaAlumno()).thenReturn(spyMap);

        System.out.println(spyMap);

        alumnoDao.delete(30);
        verify(spyMap, times(1)).remove(30);
    }

    @Test
    public void deleteTestWrong() {
        tablaAlumnoTest.put(0, new Alumno("Juan", "Perez", 20));
        tablaAlumnoTest.put(1, new Alumno("Rosa", "Gutierrez", 30));
        when(dbMock.getTablaAlumno()).thenReturn(tablaAlumnoTest);

        assertThrows(NoSuchElementException.class, () -> alumnoDao.delete(10));
    }

    @Test
    public void updateTest() throws DataAccessException {
        Alumno alumno = new Alumno("Maria", "Gomez", 20);
        Alumno newAlumno = new Alumno("Maria", "Gomez", 22);
        HashMap<Integer, Alumno> spyMap = Mockito.spy(new HashMap<>());

        spyMap.put(0, alumno);

        when(dbMock.getTablaAlumno()).thenReturn(spyMap);
        alumnoDao.update(0, newAlumno);

        verify(spyMap, times(1)).put(anyInt(), eq(newAlumno));
    }

    @Test
    public void updateTestWrong() {
        tablaAlumnoTest.put(0, new Alumno("Juan", "Perez", 20));
        tablaAlumnoTest.put(1, new Alumno("Rosa", "Gutierrez", 30));
        when(dbMock.getTablaAlumno()).thenReturn(tablaAlumnoTest);

        assertThrows(NoSuchElementException.class, () -> alumnoDao.update(10, new Alumno("Maria", "Gomez", 22)));
    }
}
