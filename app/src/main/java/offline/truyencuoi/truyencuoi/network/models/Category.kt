package offline.truyencuoi.truyencuoi.network.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

data class Category(@PrimaryKey val id: String,
                    val name: String) : RealmObject()