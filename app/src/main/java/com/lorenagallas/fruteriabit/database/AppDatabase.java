package com.lorenagallas.fruteriabit.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.lorenagallas.fruteriabit.daos.FrutaDao;
import com.lorenagallas.fruteriabit.entidades.Fruta;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//anotacion indicando q es una base de datos.
//le digo cuales son mis entidades
//le digo q version es
@Database(entities = {Fruta.class}, version =2)
//abstracta, no puedo crear instancias de esta clase
public abstract class AppDatabase extends RoomDatabase {

    public abstract FrutaDao frutaDao(); // aca agregaria todos los daos que yo tenga

    //uso de singletone
    private static volatile AppDatabase instance; //volatile= va a ir a leer el atributo a la memoria principal siempre

    //permite ejecutar en hilos
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);

    public static AppDatabase getInstance( final Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "fruteria")
                    .fallbackToDestructiveMigration() //limpiar base de datos cuando se migra de version
                    .build();
        }
        return instance;
    }




}
