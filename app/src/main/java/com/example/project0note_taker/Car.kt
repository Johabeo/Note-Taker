package com.example.project0note_taker

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Entity class outlines the database structure
//Annotations create table, each parameter is a field
@Entity(tableName = "car_table")
data class Car(@PrimaryKey @ColumnInfo(name = "car") val car: String)