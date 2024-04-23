# prog2.2024.practico05

Consigna

Ejercicio 1

1. Implementar un proyecto Maven con la siguiente característica: escribir una clase que me permita simular un patrón DAO para acceder a una tabla alumno, la clase se llamará AlumnoDao. La funcionalidad de esa clase me permitirá implementar un CRUD sobre la tabla simulada. La base de datos se simulará con una colección Hash.
2. Usando JUnit5 debe implementarse una batería de pruebas que permita testear el funcionamiento de todos los métodos definidos en la clase AlumnoDao. Hay que tratar de obtener el máximo valor de coverage posible. Se recomienda usar setUp para inicializar el contenido del Hash.
3. Implementar tests para resultados esperados e inesperados.

Ejercicio 2

1. Implementar un proyecto Maven con los siguientes requisitos: escribir una clase que me permita simular un patrón DAO para acceder a una tabla alunno, la clase se llamará AlumnoDao. Los métodos de la clase deben ser al menos findAll(), findByAlumnoId(Integer alumnoId), add(AlumnoDao alumno), update(AlumnoDao alumno, Integer alumnoId), delete(Integer alumnoId).
2. Usando JUnit5 debe implementarse una batería de pruebas que permita testear el funcionamiento de todos los métodos definidos en la clase AlumnoDao.
3. Implementar una clase AlumnoService que permita usar las funcionalidades de la clase AlumnoDao sin accederla directamente, desacoplando de esta forma el trabajo de la "base de datos"
4. Usando Mockito testear toda la funcionalidad de la clase AlumnoService simulando el comportamiento esperado del objeto AlumnoDao.
5. Implementar tests para resultados esperados e inesperados.
