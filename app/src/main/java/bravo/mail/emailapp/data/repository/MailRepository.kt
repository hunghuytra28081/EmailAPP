package bravo.mail.emailapp.data.repository

import androidx.lifecycle.LiveData
import bravo.mail.emailapp.data.resource.local.dao.MenuLeftDAO
import bravo.mail.emailapp.data.resource.local.entity.MenuLeftEntity

class MailRepository(private val dao: MenuLeftDAO) {

    val readAllLiveData : LiveData<List<MenuLeftEntity>> = dao.getAll()

    suspend fun insertMenuList(list : List<MenuLeftEntity>) = dao.insertList(list)

    suspend fun deleteMenuLeft(list: MenuLeftEntity) = dao.delete(list)

    suspend fun deleteAll() = dao.deleteAll()

    fun isExists(name: String) = dao.isExists(name) != null
}