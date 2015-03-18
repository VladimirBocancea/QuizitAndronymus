package com.andronymus.quizit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by alejandro on 08/02/15.
 */
public class DatabaseManager {
    private DbHelper helper;
    private SQLiteDatabase db;
    /*
     *  Information of all the tables and columns
     */

    //QUESTION
    public static final String TABLE_QUESTION = "QUESTION";
    public static final String COLUMN_QUESTION_ID = "_id";
    public static final String COLUMN_QUESTION_TEXT = "QUESTION_TEXT";
    public static final String COLUMN_QUESTION_FK_CATEGORY = "FK_CATEGORY";
    public static final String COLUMN_QUESTION_DIFFICULTY = "DIFFICULTY";
    //ANSWER
    public static final String TABLE_ANSWER = "ANSWER";
    public static final String COLUMN_ANSWER_ID = "_id";
    public static final String COLUMN_ANSWER_TEXT = "ANSWER_TEXT";
    public static final String COLUMN_ANSWER_FK_QUESTION = "FK_QUESTION";
    public static final String COLUMN_ANSWER_ISTRUE = "IS_TRUE";
    //CATEGORY
    public static final String TABLE_CATEGORIES = "CATEGORY";
    public static final String COLUMN_CATEGORY_ID = "_id";
    public static final String COLUMN_CATEGORY_TEXT = "CATEGORY_TEXT";

    /*
      *Create Tables
      */
    public static final String CREATE_TABLE_QUESTION = "CREATE TABLE " + TABLE_QUESTION +
            "(" + COLUMN_QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_QUESTION_TEXT + " TEXT NOT NULL, "
            + COLUMN_QUESTION_FK_CATEGORY + " INTEGER NOT NULL, "
            + COLUMN_QUESTION_DIFFICULTY + " TEXT NOT NULL, FOREIGN KEY ( "
            + COLUMN_QUESTION_FK_CATEGORY + " ) REFERENCES "
            + TABLE_CATEGORIES + " ( " + COLUMN_CATEGORY_ID + " ) );";

    public static final String CREATE_TABLE_ANSWER = "CREATE TABLE " + TABLE_ANSWER +
            "(" + COLUMN_ANSWER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ANSWER_TEXT + " TEXT NOT NULL, "
            + COLUMN_ANSWER_FK_QUESTION + " INTEGER NOT NULL, "
            + COLUMN_ANSWER_ISTRUE + " INTEGER NOT NULL, FOREIGN KEY ( "
            + COLUMN_ANSWER_FK_QUESTION + ") REFERENCES "
            + TABLE_QUESTION + " ( " + COLUMN_QUESTION_ID + " ) );";

    public static final String CREATE_TABLE_CATEGORY = "CREATE TABLE " + TABLE_CATEGORIES +
            "(" + COLUMN_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CATEGORY_TEXT + " TEXT NOT NULL );";

    // Constructor
    public DatabaseManager(Context context) {

        helper = new DbHelper(context);
        db = helper.getWritableDatabase();
    }

    public SQLiteDatabase openWriteable() {
        return helper.getWritableDatabase();
    }

    public void closeDb(SQLiteDatabase database) {
        database.close();
    }

    public void createQuestion(Question question) {
        SQLiteDatabase database = openWriteable();
        database.beginTransaction();
        ContentValues questionValues = new ContentValues();
        questionValues.put(COLUMN_QUESTION_TEXT, question.getQuestionText());
        questionValues.put(COLUMN_QUESTION_FK_CATEGORY, question.getFkCategory());
        questionValues.put(COLUMN_QUESTION_DIFFICULTY, question.getDifficulty());

        database.insert(TABLE_QUESTION, null, questionValues);
        database.setTransactionSuccessful();
        database.endTransaction();
        closeDb(database);

    }

    public void createAnswer(Answer answer) {
        SQLiteDatabase database = openWriteable();
        database.beginTransaction();
        ContentValues questionValues = new ContentValues();
        questionValues.put(COLUMN_ANSWER_TEXT, answer.getAnswerText());
        questionValues.put(COLUMN_ANSWER_FK_QUESTION, answer.getFkQuestion());
        questionValues.put(COLUMN_ANSWER_ISTRUE, answer.getIsTrue());

        database.insert(TABLE_ANSWER, null, questionValues);
        database.setTransactionSuccessful();
        database.endTransaction();
        closeDb(database);

    }

    public void createCategory(Category category) {
        SQLiteDatabase database = openWriteable();
        database.beginTransaction();
        ContentValues questionValues = new ContentValues();
        questionValues.put(COLUMN_CATEGORY_TEXT, category.getName());

        database.insert(TABLE_CATEGORIES, null, questionValues);
        database.setTransactionSuccessful();
        database.endTransaction();
        closeDb(database);

    }

    //    public void deleteQuestion(int questionId) {
//        SQLiteDatabase database = openWriteable();
//        database.beginTransaction();
//        database.delete(TABLE_ANSWER,
//                String.format("%s=%d", COLUMN_ANSWER_FK_QUESTION, questionId),
//                null);
//        database.delete(TABLE_QUESTION,
//                String.format("%s=%d", COLUMN_QUESTION_ID, questionId),
//                null);
//
//        database.setTransactionSuccessful();
//        database.endTransaction();
//        closeDb(database);
//    }
//
//    public void deleteAnswer(int answerId) {
//        SQLiteDatabase database = openWriteable();
//        database.beginTransaction();
//        database.delete(TABLE_QUESTION,
//                String.format("%s=%d", COLUMN_ANSWER_FK_QUESTION, answerId),
//                null);
//        database.delete(TABLE_ANSWER,
//                String.format("%s=%d", COLUMN_ANSWER_ID, answerId),
//                null);
//        database.setTransactionSuccessful();
//        database.endTransaction();
//        closeDb(database);
//    }
//
//    public void deleteCategory(int categoryId) {
//        SQLiteDatabase database = openWriteable();
//        database.beginTransaction();
//        database.delete(TABLE_QUESTION,
//                String.format("%s=%d", COLUMN_QUESTION_FK_CATEGORY, categoryId),
//                null);
//        database.delete(TABLE_ANSWER,
//                String.format("%s=%d", COLUMN_ANSWER_FK_QUESTION, categoryId),
//                null);
//        database.delete(TABLE_CATEGORIES,
//                String.format("%s=%d", COLUMN_CATEGORY_ID, categoryId),
//                null);
//        database.setTransactionSuccessful();
//        database.endTransaction();
//        closeDb(database);
//    }
//
    public Question getQuestion(int id){

        SQLiteDatabase db = openWriteable();

        Cursor cursor =
                db.query(TABLE_QUESTION,
                        new String[] {COLUMN_QUESTION_ID, COLUMN_QUESTION_TEXT, COLUMN_QUESTION_FK_CATEGORY, COLUMN_QUESTION_DIFFICULTY},
                        " _id = ?",
                        new String[] { String.valueOf(id) },
                        null,
                        null,
                        null,
                        null);


        if (cursor != null)
            cursor.moveToFirst();

        Question question = new Question();
        question.setId(Integer.parseInt(cursor.getString(0)));
        question.setQuestionText(cursor.getString(1));
        question.setFkCategory(Integer.parseInt(cursor.getString(2)));
        question.setDifficulty(cursor.getString(3));


        Log.d("getQuestion(" + id + ")", question.toString());
        closeDb(db);
        return question;
    }

    public Answer getAnswerByIdAnswer(int idAnswer){

        SQLiteDatabase db = openWriteable();

        Cursor cursor =
                db.query(TABLE_ANSWER,
                        new String[] {COLUMN_ANSWER_ID, COLUMN_ANSWER_TEXT, COLUMN_ANSWER_FK_QUESTION, COLUMN_ANSWER_ISTRUE},
                        " _id = ?",
                        new String[] { String.valueOf(idAnswer) },
                        null,
                        null,
                        null,
                        null);


        if (cursor != null)
            cursor.moveToFirst();

        Answer answer = new Answer();
        answer.setId(Integer.parseInt(cursor.getString(0)));
        answer.setAnswerText(cursor.getString(1));
        answer.setFkQuestion(Integer.parseInt(cursor.getString(2)));
        answer.setIsTrue(Boolean.parseBoolean(cursor.getString(3)));


        Log.d("getAnswer(" + idAnswer + ")", answer.toString());
        closeDb(db);
        return answer;
    }

    public Category getCategory(int id){

        SQLiteDatabase db = openWriteable();

        Cursor cursor =
                db.query(TABLE_QUESTION,
                        new String[] {COLUMN_QUESTION_ID, COLUMN_QUESTION_TEXT},
                        " _id = ?",
                        new String[] { String.valueOf(id) },
                        null,
                        null,
                        null,
                        null);


        if (cursor != null)
            cursor.moveToFirst();

        Category category = new Category();
        category.setId(Integer.parseInt(cursor.getString(0)));
        category.setName(cursor.getString(1));

        Log.d("getCategory(" + id + ")", category.toString());
        closeDb(db);
        return category;
    }

    public DbHelper getDeDbHelper (){
        return helper;
    }






    public int getMaxNumQuestion(){
        SQLiteDatabase db = openWriteable();
        String countQuery = "SELECT _id FROM " + TABLE_QUESTION;

        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;

    }

    public ArrayList <Answer> getAnswer (int idQuestion){
        boolean isTrue = false;
        SQLiteDatabase db = openWriteable();
        ArrayList <Answer> answers = new ArrayList<Answer>();

        Cursor cursor =
                db.query(TABLE_ANSWER,
                        new String[] {COLUMN_ANSWER_ID, COLUMN_ANSWER_TEXT, COLUMN_ANSWER_FK_QUESTION, COLUMN_ANSWER_ISTRUE},
                        COLUMN_ANSWER_FK_QUESTION + " = ?",
                        new String[] { String.valueOf(idQuestion) },
                        null,
                        null,
                        null,
                        null);

        if (cursor.moveToFirst()){
            do{
                Answer answer = new Answer(
                        getStringFromColumnName(cursor, COLUMN_ANSWER_TEXT),
                        getIntFromColumnName(cursor,COLUMN_ANSWER_FK_QUESTION),
                        isTrue);
                if (cursor.getInt(3) == 1){
                    isTrue = true;
                } else {
                    isTrue = false;
                }
                answer.setId(Integer.parseInt(cursor.getString(0)));
                answer.setIsTrue(isTrue);



                answers.add(answer);

                Log.d("getAnswer(" + idQuestion + ")", answer.toString());

            } while (cursor.moveToNext());
        }



        closeDb(db);
        return answers;
    }

    private int getIntFromColumnName (Cursor cursor, String columnName){
        int columnIndex = cursor.getColumnIndex(columnName);
        return cursor.getInt(columnIndex);
    }

    private String getStringFromColumnName (Cursor cursor, String columnName){
        int columnIndex = cursor.getColumnIndex(columnName);
        return cursor.getString(columnIndex);
    }




}//end class

