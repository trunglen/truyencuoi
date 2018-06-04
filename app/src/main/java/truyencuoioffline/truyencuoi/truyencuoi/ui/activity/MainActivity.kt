package truyencuoioffline.truyencuoi.truyencuoi.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_main.*
import truyencuoioffline.truyencuoi.truyencuoi.R
import truyencuoioffline.truyencuoi.truyencuoi.extensions.isOnline
import truyencuoioffline.truyencuoi.truyencuoi.extensions.request
import truyencuoioffline.truyencuoi.truyencuoi.network.models.Category
import truyencuoioffline.truyencuoi.truyencuoi.network.models.Story
import truyencuoioffline.truyencuoi.truyencuoi.ui.adapter.CategoryAdapter
import truyencuoioffline.truyencuoi.truyencuoi.ui.base.BaseActivity

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
        Thread().run {
            requestAds(banner)
        }
    }

}
