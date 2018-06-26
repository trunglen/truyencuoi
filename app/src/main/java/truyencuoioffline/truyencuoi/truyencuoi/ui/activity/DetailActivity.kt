package truyencuoioffline.truyencuoi.truyencuoi.ui.activity

import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_detail.*
import truyencuoioffline.truyencuoi.truyencuoi.R
import truyencuoioffline.truyencuoi.truyencuoi.common.ApiConstant
import truyencuoioffline.truyencuoi.truyencuoi.extensions.loadImage
import truyencuoioffline.truyencuoi.truyencuoi.ui.base.BaseActivity

class DetailActivity : BaseActivity() {
    var defaultTextSize = ApiConstant.DEFAULT_TEXT_SIZE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initView()
        Thread().run {
            Log.d("request_ads","request_ads")
            requestAds(bannerDetail)
        }
    }

    fun initView() {
        var bundle = intent.getBundleExtra("story")
        tvStoryTitle.text = bundle.getString("title")
        ivStoryThumb.loadImage(ApiConstant.STATIC_POST_URL + bundle.getString("id"))
//        tvStoryContent.setHtml(bundle.getString("content"), HtmlHttpImageGetter(tvStoryContent))
        val imgStyle = "<style>img{display: inline;height: auto;max-width: 100%;}</style>"
        wv.loadData(imgStyle +bundle.getString("content"), "text/html", "UTF-8")
        wv.settings.loadsImagesAutomatically = true
//        btnZoomin.setOnClickListener {
//            tvStoryContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, defaultTextSize++.toFloat())
//            Log.d("text_size", tvStoryContent.textSize.toString())
//        }
//        btnZoomout.setOnClickListener {
//            tvStoryContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, defaultTextSize--.toFloat())
//            Log.d("text_size", imgStyle + tvStoryContent.textSize.toString())
//        }
    }
}
