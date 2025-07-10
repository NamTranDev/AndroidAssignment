package nam.tran.home.assignment.jetpack.compose.ui.feature.onboarding.model

import androidx.annotation.DrawableRes
import nam.tran.home.assignment.jetpack.compose.R

data class PageInfo(
    @DrawableRes val image: Int,
    val title: String,
    val description: String,
){
    companion object{
        val pageInfos = mutableListOf<PageInfo>().apply {
            add(PageInfo(image = R.drawable.image_page_1, title = "Lorem Ipsum is simply dummy", description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry."))
            add(PageInfo(image = R.drawable.image_page_2, title = "Lorem Ipsum is simply dummy", description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry."))
            add(PageInfo(image = R.drawable.image_page_3, title = "Lorem Ipsum is simply dummy", description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry."))
        }
    }
}
