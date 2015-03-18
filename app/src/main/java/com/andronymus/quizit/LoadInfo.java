package com.andronymus.quizit;

/**
 * Created by alex on 03/03/15.
 *
 * Writes on the database a bunch of categories, answers and questions.
 * This operation is only needed in the first execution of the app.
 */
public class LoadInfo {
    DatabaseManager dbm;
    DbHelper dbHelper;

    public LoadInfo (DatabaseManager dbm){
        this.dbm = dbm;
        dbHelper = dbm.getDeDbHelper();

        load(dbm);
    }

    private void load (DatabaseManager dbm){

        if (dbHelper.getTablesCreated()) {

            dbm.createCategory(new Category("Deporte"));
            dbm.createCategory(new Category("Mundo"));
            dbm.createCategory(new Category("Música"));
            dbm.createCategory(new Category("Literatura"));

            dbm.createQuestion(new Question("¿En que deporte se utiliza tiza?", 1, "Fácil"));
            dbm.createAnswer(new Answer("Futboll", 1, false));
            dbm.createAnswer(new Answer("Golf", 1, false));
            dbm.createAnswer(new Answer("Baloncesto", 1, false));
            dbm.createAnswer(new Answer("Billar", 1, true));


            dbm.createQuestion(new Question("Cuántas manos tiene un caballo", 2, "Fácil"));
            dbm.createAnswer(new Answer("4", 2, false));
            dbm.createAnswer(new Answer("3", 2, false));
            dbm.createAnswer(new Answer("2", 2, true));
            dbm.createAnswer(new Answer("1", 2, false));


            dbm.createQuestion(new Question("¿Qué instrumento tiene nombre y forma geométricos?", 3, "Fácil"));
            dbm.createAnswer(new Answer("Guitarra", 3, false));
            dbm.createAnswer(new Answer("Piano", 3, false));
            dbm.createAnswer(new Answer("Triángulo", 3, true));
            dbm.createAnswer(new Answer("Tambor", 3, false));

            dbm.createQuestion(new Question("¿Cuál es el límite de edad establecido para participar en los juegos Olímpicos?", 1, "Medio"));
            dbm.createAnswer(new Answer("18", 4, false));
            dbm.createAnswer(new Answer("21", 4, false));
            dbm.createAnswer(new Answer("Ninguno", 4, true));
            dbm.createAnswer(new Answer("14", 4, false));


            dbm.createQuestion(new Question("¿Qué lado de los libros suele tener números pares?", 4, "Fácil"));
            dbm.createAnswer(new Answer("Izquierdo", 5, true));
            dbm.createAnswer(new Answer("Derecho", 5, false));
        }
    }
}
