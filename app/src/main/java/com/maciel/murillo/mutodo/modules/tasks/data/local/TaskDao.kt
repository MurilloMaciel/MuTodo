package com.maciel.murillo.mutodo.modules.tasks.data.local

import androidx.room.*
import com.maciel.murillo.mutodo.modules.tasks.data.model.TaskData

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskData): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tasks: List<TaskData>)

    @Update
    suspend fun update(task: TaskData)

    @Delete
    suspend fun delete(task: TaskData)

    @Query("SELECT * FROM TaskData ORDER BY id ASC")
    suspend fun findAll(): List<TaskData>

    @Query("SELECT * FROM TaskData WHERE TaskData.categoryType = :categoryType ORDER BY id ASC")
    suspend fun findAllByCategory(categoryType: String): List<TaskData>

    @Query("SELECT * FROM TaskData WHERE id = :id")
    suspend fun findById(id: Long): TaskData
}