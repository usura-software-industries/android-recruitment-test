package dog.snow.androidrecruittest.feature

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.common.model.Item
import kotlinx.android.synthetic.main.item.view.*

class ListItem(val item: Item) : AbstractItem<ListItem, ListItem.ViewHolder>() {

    override fun getType() = R.id.constraintLayout
    override fun getLayoutRes() = R.layout.item
    override fun getViewHolder(v: View) = ViewHolder(v)

    class ViewHolder(view: View) : FastAdapter.ViewHolder<ListItem>(view) {

        override fun bindView(item: ListItem, payloads: List<Any>) {
            itemView.apply {
                name_tv.text = item.item.name
                description_tv.text = item.item.description

                Glide.with(this)
                        .load(item.item.icon)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(icon_ic)
            }
        }

        override fun unbindView(item: ListItem) {
        }
    }
}