package offline.truyencuoi.truyencuoi.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.util.TypedValue
import kotlinx.android.synthetic.main.activity_detail.*
import offline.truyencuoi.truyencuoi.R
import offline.truyencuoi.truyencuoi.common.ApiConstant
import offline.truyencuoi.truyencuoi.extensions.loadImage

class DetailActivity : AppCompatActivity() {
    var defaultTextSize = ApiConstant.DEFAULT_TEXT_SIZE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initView()
    }

    fun initView() {
        var bundle = intent.getBundleExtra("story")
        tvStoryTitle.text = bundle.getString("title")
        ivStoryThumb.loadImage(ApiConstant.STATIC_POST_URL + bundle.getString("id"))
        tvStoryContent.text = Html.fromHtml(bundle.getString("content"))
        btnZoomin.setOnClickListener {
            tvStoryContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, defaultTextSize++.toFloat())
            Log.d("text_size", tvStoryContent.textSize.toString())
        }
        btnZoomout.setOnClickListener {
            tvStoryContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, defaultTextSize--.toFloat())
            Log.d("text_size", tvStoryContent.textSize.toString())
        }
    }
}
