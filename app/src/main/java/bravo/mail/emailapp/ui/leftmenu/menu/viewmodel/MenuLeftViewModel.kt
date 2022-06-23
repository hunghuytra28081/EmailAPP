package bravo.mail.emailapp.ui.leftmenu.menu.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import bravo.mail.emailapp.data.repository.MailRepository
import bravo.mail.emailapp.data.resource.local.database.MenuLeftDatabase
import bravo.mail.emailapp.data.resource.local.entity.MenuLeftEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuLeftViewModel(application: Application) : AndroidViewModel(application) {

    val readAllDataLive: LiveData<List<MenuLeftEntity>>
    private val repository: MailRepository

    init {
        val mailDao = MenuLeftDatabase.getDatabase(application).menuLeftDao()
        repository = MailRepository(mailDao)
        readAllDataLive = repository.readAllLiveData
    }

    fun insertMenuList(list: List<MenuLeftEntity>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMenuList(list)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    fun deleteItemMenu(list: MenuLeftEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMenuLeft(list)
        }
    }

    fun checkIsExists(name: String): Boolean{
        return repository.isExists(name)
    }
}
