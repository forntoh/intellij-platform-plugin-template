package com.forntoh.mvvmtemplates.recipes.database.src

fun baseDao(
    packageName: String
) = """package $packageName

import androidx.room.*
import java.util.*

@Dao
abstract class BaseDao<T> {
    /**
     * Insert an object in the database.
     *
     * @param entity the object to be inserted.
     * @return The SQLite row id
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(entity: T): Long

    /**
     * Insert an array of objects in the database.
     *
     * @param entity the objects to be inserted.
     * @return The SQLite row ids
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(entity: List<T>): List<Long>

    /**
     * Update an object from the database.
     *
     * @param entity the object to be updated
     */
    @Update
    abstract suspend fun update(entity: T)

    /**
     * Update an array of objects from the database.
     *
     * @param entity the object to be updated
     */
    @Update
    abstract suspend fun update(entity: List<T>)

    /**
     * Delete an object from the database
     *
     * @param entity the object to be deleted
     */
    @Delete
    abstract suspend fun delete(entity: T)

    @Transaction
    open suspend fun upsert(entity: T) {
        val id = insert(entity)
        if (id == -1L) update(entity)
    }

    @Transaction
    open suspend fun upsert(entities: List<T>) {
        val insertResult = insert(entities)
        val updateList: MutableList<T> = ArrayList()
        for (i in insertResult.indices) if (insertResult[i] == -1L) updateList.add(entities[i])
        if (updateList.isNotEmpty()) update(updateList)
    }
}
"""