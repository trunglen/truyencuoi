package truyencuoioffline.truyencuoi.truyencuoi.network.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Story(@PrimaryKey var id: String,
                 var title: String,
                 var content: String,
                 var category:String) : RealmObject() {
    constructor() : this("", "", "","")
}