package truyencuoioffline.truyencuoi.truyencuoi.ui.adapter

import android.content.Context
import android.text.Html
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.story_item.view.*
import truyencuoioffline.truyencuoi.truyencuoi.R
import truyencuoioffline.truyencuoi.truyencuoi.common.ApiConstant
import truyencuoioffline.truyencuoi.truyencuoi.extensions.inflate
import truyencuoioffline.truyencuoi.truyencuoi.extensions.loadImage
import truyencuoioffline.truyencuoi.truyencuoi.network.models.Story

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

