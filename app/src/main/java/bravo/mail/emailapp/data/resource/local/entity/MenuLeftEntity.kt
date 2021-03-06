package bravo.mail.emailapp.data.resource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MenuLeftEntity(
        @PrimaryKey
        @ColumnInfo(name = "name")
        val name: String,
        @ColumnInfo(name = "image")
        val image: Int
)