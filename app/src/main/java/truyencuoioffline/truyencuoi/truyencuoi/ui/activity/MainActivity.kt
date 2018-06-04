package offline.truyencuoi.truyencuoi.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import offline.truyencuoi.truyencuoi.R
import offline.truyencuoi.truyencuoi.TruyencuoiApplication.Companion.apiService
import offline.truyencuoi.truyencuoi.extensions.getHttpService
import offline.truyencuoi.truyencuoi.extensions.isOnline
import offline.truyencuoi.truyencuoi.extensions.request
import offline.truyencuoi.truyencuoi.network.http.ApiService
import offline.truyencuoi.truyencuoi.network.models.Category
import offline.truyencuoi.truyencuoi.network.models.Story
import offline.truyencuoi.truyencuoi.ui.adapter.CategoryAdapter
import offline.truyencuoi.truyencuoi.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Truyen cuoi hay nhat"
        lvCategory.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _->
            val adapter = lvCategory.adapter as CategoryAdapter
            val intent = Intent(this,ListActivity::class.java)
            val bundle = Bundle()
            val category = adapter.categories.get(position)
            bundle.putString("cat_id",category.id)
            bundle.putString("cat_title",category.name)
            intent.putExtra("category", bundle)
            startActivity(intent)
        }

        if (isOnline(this)) {
            apiService.listCategories().request {
                lvCategory.adapter = CategoryAdapter(it.body())
            }
        } else {
            val categories = realm.where(Category::class.java).findAll().toList()
            lvCategory.adapter = CategoryAdapter(categories)
        }
        val stories = realm.where(Story::class.java).findAll().toList()
    }

}
