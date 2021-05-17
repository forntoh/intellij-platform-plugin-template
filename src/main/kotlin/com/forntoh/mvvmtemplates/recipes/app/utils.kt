package com.forntoh.mvvmtemplates.recipes.app

fun coilHelper(packageName: String) = """package $packageName

import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.ImageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest

fun ImageView.load(imageLoader: ImageLoader, request: ImageRequest.Builder, url: String) {
    imageLoader.enqueue(
        request.data(url).memoryCachePolicy(CachePolicy.DISABLED).target(this).build()
    )
}

fun ImageView.load(imageLoader: ImageLoader, @DrawableRes res: Int) {
    imageLoader.enqueue(
        ImageRequest.Builder(context)
            .data(res)
            .target(this)
            .build()
    )
}"""

fun contextExtensions(packageName: String) = """package $packageName

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.StyleRes
import androidx.core.content.res.use

/**
 * Retrieve a color from the current [android.content.res.Resources.Theme].
 */
@ColorInt
@SuppressLint("Recycle")
fun Context.themeColor(
    @AttrRes themeAttrId: Int
): Int {
    return obtainStyledAttributes(
        intArrayOf(themeAttrId)
    ).use {
        it.getColor(0, Color.MAGENTA)
    }
}

/**
 * Retrieve a style from the current [android.content.res.Resources.Theme].
 */
@StyleRes
fun Context.themeStyle(@AttrRes attr: Int): Int {
    val tv = TypedValue()
    theme.resolveAttribute(attr, tv, true)
    return tv.data
}

/**
 * Retrieve a style from the current [android.content.res.Resources.Theme].
 */
fun Context.resolveBool(@AttrRes attr: Int): Boolean {
    val tv = TypedValue()
    theme.resolveAttribute(attr, tv, true)
    return tv.data != 0
}

@SuppressLint("Recycle")
fun Context.themeInterpolator(@AttrRes attr: Int): Interpolator {
    return AnimationUtils.loadInterpolator(
        this,
        obtainStyledAttributes(intArrayOf(attr)).use {
            it.getResourceId(0, android.R.interpolator.fast_out_slow_in)
        }
    )
}"""

fun customViewBinding(packageName: String) = """package $packageName

import android.animation.ObjectAnimator
import android.text.format.DateUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import $packageName.R
import $packageName.internal.view.InsetDecoration
import coil.ImageLoader
import coil.request.ImageRequest
import java.util.*


@BindingAdapter(value = ["setAdapter"])
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) = this.run {
    this.setHasFixedSize(true)
    this.adapter = adapter
}

@BindingAdapter(value = ["insetPadding"])
fun RecyclerView.bindRecyclerViewInsetDecor(padding: Int = 0) = this.run {
    this.addItemDecoration(InsetDecoration(padding))
}

@BindingAdapter(value = ["setImage", "setLoader"])
fun ImageView.bindImage(icon: Int, imageLoader: ImageLoader) = this.run {
    load(imageLoader, icon)
}

@BindingAdapter(value = ["setImage", "setLoader", "setRequestBuilder"])
fun ImageView.bindImageWithHeaders(
    icon: String?,
    imageLoader: ImageLoader,
    request: ImageRequest.Builder?
) = this.run {
    if (icon != null && request != null) {
        load(imageLoader, request, icon)
    }
}"""

fun displayMetricHelper(packageName: String) = """package $packageName

import android.app.Activity
import android.content.res.Resources
import android.util.TypedValue

val Int.inPx: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Int.inSp: Float get() = (this * Resources.getSystem().displayMetrics.scaledDensity)

val screenWidth: Int get() = Resources.getSystem().displayMetrics.widthPixels

val Activity.statusBarHeight: Int
    get() {
        var result = 0
        val resourceId: Int = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) result = resources.getDimensionPixelSize(resourceId)
        return result
    }

val Activity.toolbarHeight: Int
    get() {
        val tv = TypedValue()
        return if (theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
        } else 56.inPx
    }"""

fun viewHelper(packageName: String) = """package $packageName

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

fun View.hide(animate: Boolean = false) {
    if (animate) {
        alpha = 1f
        animate()
            .alpha(0f)
            .setDuration(resources.getInteger(android.R.integer.config_shortAnimTime).toLong())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    visibility = View.GONE
                }
            })
    } else visibility = View.GONE
}

fun BottomAppBar.hide() {
    performHide()
    alpha = 0f
}

fun BottomAppBar.show() {
    alpha = 1f
    performShow()
}

fun View.show(animate: Boolean = false) {
    if (animate) {
        alpha = 0f
        visibility = View.VISIBLE
        animate()
            .alpha(1f)
            .setListener(null)
            .duration = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()
    } else visibility = View.VISIBLE
}

fun View.showSnackBar(
    message: String,
    duration: Int = Snackbar.LENGTH_SHORT,
    actionTitle: String = "",
    action: (() -> Unit)? = null
) {
    val snackBar = Snackbar.make(this, message, duration)
    snackBar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).maxLines =
        3
    if (action != null) snackBar.setAction(actionTitle) { action.invoke() }.show()
    else snackBar.show()
}

fun View.showError(message: String?, shouldFocus: Boolean) {
    if (this is TextInputLayout) error = message
    else if (this is EditText) error = message
    if (shouldFocus) requestFocus()
}

fun View.clipAllParents() {
    var view = this
    while (view.parent != null && view.parent is ViewGroup) {
        val viewGroup = view.parent as ViewGroup
        viewGroup.clipToPadding = false
        viewGroup.clipChildren = false
        view = viewGroup
    }
}

fun View.translateY(distance: Int, goUp: Boolean) {
    val outAnim: ObjectAnimator = ObjectAnimator.ofFloat(
        this,
        View.TRANSLATION_Y,
        if (goUp) distance.inPx.toFloat() else 0f,
        if (goUp) 0f else distance.inPx.toFloat(),
    ).apply {
        duration = resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()
    }

    if (translationY == if (goUp) distance.inPx.toFloat() else 0f)
        outAnim.start()
}

fun ObjectAnimator.onCompleted(done: () -> Unit) {
    addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(anim: Animator) = Unit

        override fun onAnimationCancel(anim: Animator) = Unit

        override fun onAnimationEnd(anim: Animator) {
            done()
        }
    })
}"""

fun windowHelper(packageName: String) = """package $packageName

import android.app.Activity
import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import $packageName.R
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView

fun Activity.hideKeyboard() {
    var view = currentFocus
    if (view == null) view = View(this)
    (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
        view.windowToken,
        0
    )
}

@Suppress("DEPRECATION")
fun Context.getLoadingDialog() = MaterialDialog(this).show {
    customView(view = ProgressBar(context).apply {
        val typedValue = TypedValue()
        theme.resolveAttribute(R.attr.colorAccent, typedValue, true)
        val color = typedValue.data

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            indeterminateDrawable.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
        else
            indeterminateDrawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
    })
    cancelOnTouchOutside(false)
    this.view.background = ColorDrawable(Color.TRANSPARENT)
}

inline fun Activity.observeKeyboardState(crossinline action: (isOpen: Boolean) -> Unit) {
    var previousHeight = 0
    val content: View = findViewById(android.R.id.content)

    window.decorView.viewTreeObserver.addOnPreDrawListener {

        val contentHeight = content.height
        if (previousHeight == 0) previousHeight = contentHeight

        // Keyboard Expanded
        if (contentHeight > previousHeight) {
            action(false)
            previousHeight = contentHeight
        } else if (contentHeight < previousHeight) {
            action(true)
            previousHeight = contentHeight
        }

        return@addOnPreDrawListener true
    }
}"""