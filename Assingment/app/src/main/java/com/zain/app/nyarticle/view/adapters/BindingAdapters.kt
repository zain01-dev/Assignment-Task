package com.zain.app.nyarticle.utils

import android.content.ContextWrapper
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.zain.app.nyarticle.R
import com.zain.app.nyarticle.view.utils.EndlessRecyclerOnScrollListener


@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(
            parentActivity,
            Observer { value -> view.visibility = value ?: View.VISIBLE })
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value ?: "" })
    }
}

private val picasso = Picasso.get()
@BindingAdapter("bind:imageUrl")
fun loadImage(view: ImageView, url: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && url != null) {
        url.observe(parentActivity, Observer { value ->
            picasso
                .load(value)
                .tag(parentActivity.applicationContext)
                .placeholder(R.drawable.image_not_found)
                .into(view)
        })
    } else {
        view.visibility = View.INVISIBLE
    }
}


@BindingAdapter("bind:scrollTo")
fun scrollTo(recyclerView: RecyclerView, position: Int) {
    recyclerView.scrollToPosition(position)
}

@BindingAdapter("bind:addScrollListener")
fun addScrollListener(recyclerView: RecyclerView, onScrollListener: EndlessRecyclerOnScrollListener) {
    recyclerView  .addOnScrollListener(onScrollListener);
}


fun View.getParentActivity(): AppCompatActivity? {
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}
