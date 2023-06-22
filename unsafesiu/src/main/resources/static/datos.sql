CREATE TABLE Usuario (
    ID SERIAL PRIMARY KEY,
    Nombre VARCHAR,
    Apellido VARCHAR,
    Rol VARCHAR,
    Usuario VARCHAR,
    Contrasenia VARCHAR,
    Mail VARCHAR
);

CREATE TABLE Materia (
    ID SERIAL PRIMARY KEY,
    NOMBRE VARCHAR
);

CREATE TABLE Inscripcion (
    ID SERIAL PRIMARY KEY,
    ID_Alumno INTEGER REFERENCES Usuario(ID),
    ID_Materia INTEGER REFERENCES Materia(ID)
);

CREATE TABLE Curso (
    ID SERIAL PRIMARY KEY,
    ID_Profesor INTEGER REFERENCES Usuario(ID),
    ID_Materia INTEGER REFERENCES Materia(ID)
);

CREATE TABLE Calificacion (
    ID SERIAL PRIMARY KEY,
    ID_Curso INTEGER REFERENCES Curso(ID),
	ID_Alumno INTEGER REFERENCES Usuario(ID),
    Calificacion DECIMAL,
    Descripcion_Examen VARCHAR
);

insert into materia (nombre) values ('Seguridad en Aplicaciones Web');
insert into materia (nombre) values ('Proyecto Final');
insert into materia (nombre) values ('Administracion Gerencial');
insert into materia (nombre) values ('Inteligencia Artificial');

insert into usuario (nombre, apellido, rol, usuario, contrasenia, mail) values ('Facundo', 'Mamani', 'ALUMNO', 'facu-m', 'facu123', 'fmamaniflores@frba.utn.edu.ar');
insert into usuario (nombre, apellido, rol, usuario, contrasenia, mail) values ('Daniel', 'Montesano', 'ALUMNO', 'dani-m', 'dani456', 'dmontesano@frba.utn.edu.ar');
insert into usuario (nombre, apellido, rol, usuario, contrasenia, mail) values ('Mariana', 'Marino', 'ALUMNO', 'maru-m', 'maru789', 'marimarino@frba.utn.edu.ar');
insert into usuario (nombre, apellido, rol, usuario, contrasenia, mail) values ('Juan Cruz', 'Salto', 'ALUMNO', 'juan-s', 'juan123', 'jsaltolastra@frba.utn.edu.ar');
insert into usuario (nombre, apellido, rol, usuario, contrasenia, mail) values ('Gonzalo', 'Vilanova', 'PROFESOR', 'gonzalo-v', 'gonzalo456', 'gvilanova@frba.utn.edu.ar');
insert into usuario (nombre, apellido, rol, usuario, contrasenia, mail) values ('Ruben', 'Soro', 'RECTOR', 'ruben-s', 'ruben789', 'rsoro@frba.utn.edu.ar');
insert into usuario (nombre, apellido, rol, usuario, contrasenia, mail) values ('Juan', 'Perez', 'PROFESOR', 'juan-p', 'juan123', 'jperez@frba.utn.edu.ar');

insert into inscripcion (id_alumno, id_materia) values (1, 1);
insert into inscripcion (id_alumno, id_materia) values (1, 4);
insert into inscripcion (id_alumno, id_materia) values (2, 1);
insert into inscripcion (id_alumno, id_materia) values (3, 1);
insert into inscripcion (id_alumno, id_materia) values (4, 1);

insert into curso (id_profesor, id_materia) values (5, 1);
insert into curso (id_profesor, id_materia) values (6, 4);

insert into calificacion (id_curso, id_alumno, calificacion, descripcion_examen) values (1, 1, 2, 'Primer parcial');
insert into calificacion (id_curso, id_alumno, calificacion, descripcion_examen) values (1, 1, 8, 'Segundo Parcial');
insert into calificacion (id_curso, id_alumno, calificacion, descripcion_examen) values (1, 1, 8, 'Primer Recuperatorio - Primer Parcial');
insert into calificacion (id_curso, id_alumno, calificacion, descripcion_examen) values (1, 1, 2, 'Primer parcial');
insert into calificacion (id_curso, id_alumno, calificacion, descripcion_examen) values (1, 1, 8, 'Segundo Parcial');
insert into calificacion (id_curso, id_alumno, calificacion, descripcion_examen) values (1, 1, 8, 'Primer Recuperatorio - Primer Parcial');
insert into calificacion (id_curso, id_alumno, calificacion, descripcion_examen) values (1, 2, 3, 'Primer parcial');
insert into calificacion (id_curso, id_alumno, calificacion, descripcion_examen) values (1, 3, 1, 'Primer parcial');
insert into calificacion (id_curso, id_alumno, calificacion, descripcion_examen) values (1, 4, 5.5, 'Primer parcial');

