package bravo.mail.emailapp.data.repository

import androidx.lifecycle.LiveData
import bravo.mail.emailapp.data.resource.local.dao.MenuLeftDAO
import bravo.mail.emailapp.data.resource.local.entity.MenuLeftEntity

class MailRepository(private val dao: MenuLeftDAO) {

    val readAllLiveData : LiveData<List<MenuLeftEntity>> = dao.getAll()

    suspend fun insertMenuList(list : List<MenuLeftEntity>) = dao.insertList(list)

    suspend fun insertMenuItem(item: MenuLeftEntity) = dao.insertItem(item)

    suspend fun deleteMenuLeft(list: List<MenuLeftEntity>) = dao.delete(list)

    fun isExists(name: String) = dao.isExists(name) != null
}