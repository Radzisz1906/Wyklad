package com.example.wyklad

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context):SQLiteOpenHelper(context, DATABASE_NAME,
    null, DATABASE_VER
) {
    companion object {
        private val DATABASE_VER = 2
        private val DATABASE_NAME = "Dane.db"
        //Table
        private val TABLE_NAME = "UserData"
        private val COL_ID = "Id"
        private val COL_LOGIN = "Login"
        private val COL_PASSWORD = "Password"
        private val COL_POINTS = "Points"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY = ("CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_LOGIN TEXT, $COL_PASSWORD TEXT, $COL_POINTS NUMBER)")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }

    //CRUD
    val allUsers:List<User>
        get(){
            val listUsers = ArrayList<User>()
            val selectQuery = "SELECT * FROM $TABLE_NAME ORDER BY POINTS DESC"
            val db = this.writableDatabase
            val cursor =  db.rawQuery(selectQuery, null)
            if(cursor.moveToFirst()){
                do {
                    val user = User()
                    user.id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID))
                    user.login = cursor.getString(cursor.getColumnIndexOrThrow(COL_LOGIN))
                    user.password = cursor.getString(cursor.getColumnIndexOrThrow(COL_PASSWORD))
                    user.points = cursor.getInt(cursor.getColumnIndexOrThrow(COL_POINTS))

                    listUsers.add(user)
                } while (cursor.moveToNext())
            }
            db.close()
            return listUsers
        }
    fun UserExists(log:String):Boolean{
        var users = this.allUsers
        users.forEach{
            if (it.login == log){
                return true;
            }
        }
        return false;
    }
    fun AddUser(login:String, haslo:String, punkty:Int):Boolean{
        val userexists = UserExists(login)
        val mydatabase = this.writableDatabase;
        if (!userexists){
            mydatabase.execSQL("INSERT INTO UserData(Login,Password,Points) VALUES(\"$login\",\"$haslo\",$punkty);");
            return true;
        }
        return false;
    }
    fun LoginUser(log:String,pas:String):Boolean{
        val users = this.allUsers;
        users.forEach{
            if (it.login==log && it.password==pas){
                return true;
            }
        }
        return false;
    }

    fun SetRecord(log:String, pts:Int):Boolean{
        val users = this.allUsers;
        val mydatabase = this.writableDatabase;
        users.forEach{
            if (it.login==log){
                if (it.points<pts){

                    mydatabase.execSQL("UPDATE UserData SET Points = $pts WHERE Login is \"$log\"");
                    return true
                }
            }
        }
        return false
    }
    fun FindUser(log:String): User {
        val users=this.allUsers;

        users.forEach{
            if(it.login==log){
                return it;
            }
        }
        return users[0];
    }

}