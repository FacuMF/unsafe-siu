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

CREATE TABLE Inscripciones (
    ID SERIAL PRIMARY KEY,
    ID_Alumno INTEGER REFERENCES Usuario(ID),
    ID_Materia INTEGER REFERENCES Materia(ID)
);

insert into materia (nombre) values ('Seguridad en Aplicaciones Web');
insert into materia (nombre) values ('Proyecto Final');
insert into materia (nombre) values ('Administracion Gerencial');
insert into materia (nombre) values ('Inteligencia Artificial');

insert into usuario (nombre, apellido, rol, usuario, contrasenia, mail) values ('Facundo', 'Mamani', 'alumno', 'facu-m', 'facu123', 'fmamaniflores@frba.utn.edu.ar');
insert into usuario (nombre, apellido, rol, usuario, contrasenia, mail) values ('Daniel', 'Montesano', 'alumno', 'dani-m', 'dani456', 'dmontesano@frba.utn.edu.ar');
insert into usuario (nombre, apellido, rol, usuario, contrasenia, mail) values ('Mariana', 'Marino', 'alumno', 'maru-m', 'maru789', 'marimarino@frba.utn.edu.ar');
insert into usuario (nombre, apellido, rol, usuario, contrasenia, mail) values ('Juan Cruz', 'Salto', 'alumno', 'juan-s', 'juan123', 'jsaltolastra@frba.utn -edu-ar');
insert into usuario (nombre, apellido, rol, usuario, contrasenia, mail) values ('Gonzalo', 'Vilanova', 'profesor', 'gonzalo-v', 'gonzalo456', 'gvilanova@frba.utn.edu.ar');

insert into inscripcion (id, id_alumno, id_materia) values (1, 1, 1);
insert into inscripcion (id, id_alumno, id_materia) values (2, 1, 2);
insert into inscripcion (id, id_alumno, id_materia) values (3, 1, 3);