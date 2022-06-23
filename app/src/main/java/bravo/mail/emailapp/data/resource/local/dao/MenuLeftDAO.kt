package bravo.mail.emailapp.data.resource.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import bravo.mail.emailapp.data.resource.local.entity.MenuLeftEntity

@Dao
interface MenuLeftDAO {

    @Query("SELECT * FROM MenuLeftEntity")
    fun getAll(): LiveData<List<MenuLeftEntity>>

    @Insert(onConflict = REPLACE)
    suspend fun insertList(item : List<MenuLeftEntity>)

    suspend fun insertItem(item: MenuLeftEntity)

    @Delete
    suspend fun delete(item: List<MenuLeftEntity>)

    @Query("SELECT * FROM menuLeftEntity WHERE name =:name")
    fun isExists(name: String): MenuLeftEntity?
}
