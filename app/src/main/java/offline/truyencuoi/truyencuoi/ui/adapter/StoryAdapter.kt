package offline.truyencuoi.truyencuoi.ui.adapter

import android.content.Context
import android.text.Html
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.story_item.view.*
import offline.truyencuoi.truyencuoi.R
import offline.truyencuoi.truyencuoi.common.ApiConstant
import offline.truyencuoi.truyencuoi.extensions.inflate
import offline.truyencuoi.truyencuoi.extensions.loadImage
import offline.truyencuoi.truyencuoi.network.models.Story

class StoryAdapter(var stories: List<Story>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = parent.inflate(R.layout.story_item)
        val story = stories[position]
        view.tvStoryTitle.text = story.title
        view.imvStoryThumb.loadImage(ApiConstant.STATIC_POST_URL + story.id)
        view.tvStoryDescription.text = Html.fromHtml(story.content)
        return view
    }

    override fun getItem(position: Int): Any {
        return stories.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return stories.size
    }
}

