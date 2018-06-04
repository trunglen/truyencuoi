package offline.truyencuoi.truyencuoi.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.category_item.view.*
import offline.truyencuoi.truyencuoi.R
import offline.truyencuoi.truyencuoi.extensions.inflate
import offline.truyencuoi.truyencuoi.network.models.Category

class CategoryAdapter(val categories: List<Category>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = parent.inflate(R.layout.category_item)
        view.categoryName.text = categories.get(position).name
        return view
    }

    override fun getItem(position: Int): Any {
        return categories.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return categories.size
    }

}