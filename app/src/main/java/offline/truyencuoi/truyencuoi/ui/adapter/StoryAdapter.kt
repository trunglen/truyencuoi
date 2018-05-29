package offline.truyencuoi.truyencuoi.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import offline.truyencuoi.truyencuoi.R
import offline.truyencuoi.truyencuoi.extensions.inflate
import offline.truyencuoi.truyencuoi.network.models.Story

class StoryAdapter(val stories:List<Story>):BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return  parent.inflate(R.layout.story_item)
    }

    override fun getItem(position: Int): Any {
        return stories.get(position)
    }

    override fun getItemId(position: Int): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCount(): Int {
        return stories.size
    }

}