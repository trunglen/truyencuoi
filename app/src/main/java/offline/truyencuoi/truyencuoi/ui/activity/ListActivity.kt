package offline.truyencuoi.truyencuoi.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_list.*
import offline.truyencuoi.truyencuoi.R
import offline.truyencuoi.truyencuoi.extensions.isOnline
import offline.truyencuoi.truyencuoi.extensions.request
import offline.truyencuoi.truyencuoi.network.models.Story
import offline.truyencuoi.truyencuoi.ui.adapter.StoryAdapter
import offline.truyencuoi.truyencuoi.ui.base.BaseActivity

class ListActivity : BaseActivity(), SwipeRefreshLayout.OnRefreshListener {

    var page = 1
    var storyAdapter = StoryAdapter(ArrayList<Story>())
    lateinit var catID:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        initView()
    }

    fun navigateToDetail(story: Story) {
        val intent = Intent(this, DetailActivity::class.java)
        val bundle = Bundle()
        bundle.putString("id", story.id)
        bundle.putString("title", story.title)
        bundle.putString("content", story.content)
        intent.putExtra("story", bundle)
        startActivity(intent)
    }

    fun initView() {
        val bundle = intent.getBundleExtra("category")
        title = bundle.getString("cat_title")
        catID = bundle.getString("cat_id")
        lvPosts.adapter = storyAdapter
        lvPosts.setOnItemClickListener { _, _, position, _ ->
            val storyAdapter = (lvPosts.adapter as StoryAdapter)
            val story = storyAdapter.stories[position]
            navigateToDetail(story)
        }
        fetchStory(catID)
        btnLoadMore.setOnClickListener {
            page++
            fetchStory(bundle.getString("cat_id"))
        }
        swipeLayout.setOnRefreshListener(this)
        swipeLayout.post(Runnable {
            if (page > 1) {
                page--
                fetchStory(bundle.getString("cat_id"))
            }
            swipeLayout.isRefreshing = false
        })
    }

    fun fetchStory(catID: String) {
        if (isOnline(this)) {
            apiService.listPosts(catID, page).request {
                val response = if (it.body() != null) it.body() else emptyList()
                if (response.size<10) {
                    hideLoadMore()
                } else{
                    showLoadMore()
                }
                storyAdapter.stories = response
                storyAdapter.notifyDataSetChanged()
                lvPosts.setSelectionAfterHeaderView()
                swipeLayout.isRefreshing = false
            }
        } else {
            swipeLayout.isRefreshing = false
            val stories = realm.where(Story::class.java).equalTo("category", catID).findAll().toList()
            storyAdapter.stories = stories
            storyAdapter.notifyDataSetChanged()
            hideLoadMore()
        }
    }

    override fun onRefresh() {
        Log.d("swipe_layout", "swipere")
        if (page > 1) {
            page--
            fetchStory(catID)
        }
        swipeLayout.isRefreshing = false
    }

    fun hideLoadMore() {
        btnLoadMore.visibility = View.GONE
    }

    fun showLoadMore() {
        btnLoadMore.visibility = View.VISIBLE
    }

}
