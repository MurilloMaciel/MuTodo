package com.maciel.murillo.mutodo.modules.tasks.data.local

import androidx.room.*
import com.maciel.murillo.mutodo.modules.tasks.data.model.TaskData

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tasks: List<TaskData>)

    @Update
    suspend fun update(task: TaskData)

    @Query("DELETE FROM TaskData WHERE id = :id")
    suspend fun delete(id: Long)

//    @Delete
//    suspend fun delete(id: Long)

    @Query("SELECT * FROM TaskData")
    suspend fun findAll(): List<TaskData>

    @Query("SELECT * FROM TaskData WHERE id = :id")
    suspend fun findById(id: Long): TaskData
}