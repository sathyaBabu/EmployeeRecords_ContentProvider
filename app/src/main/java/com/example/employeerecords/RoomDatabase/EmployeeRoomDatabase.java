package com.example.employeerecords.RoomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {EmployeeEntity.class}, version = 1)
public abstract class EmployeeRoomDatabase extends RoomDatabase {
    public abstract EmployeeDao employeeDao();
    private static volatile EmployeeRoomDatabase INSTANCE;

    static EmployeeRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EmployeeRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            EmployeeRoomDatabase.class, "employee_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
