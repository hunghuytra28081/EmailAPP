package bravo.mail.emailapp.data.resource.local.entity

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class MenuLeftEntity(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "image")
    val image: Int
) : Parcelable {
    companion object {
        val differCallback = object : DiffUtil.ItemCallback<MenuLeftEntity>() {
            override fun areItemsTheSame(
                oldItem: MenuLeftEntity,
                newItem: MenuLeftEntity
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: MenuLeftEntity,
                newItem: MenuLeftEntity
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}