package com.example.scannerqr.popup

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.example.socialmedia.base.utils.click
import com.example.socialmedia.base.utils.dpToPx
import com.scan.scannerqr.R
import com.scan.scannerqr.databinding.PopupMenuLayoutBinding

object CustomPopup {
    fun showPopupMenu(
        context: Context,
        imageView: ImageView,
        anchor: View,
        title1: String,
        image1: Int,
        title2: String,
        image2: Int,
        onClickListener: PopUpOnClickListener
    ) {
        val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupViewBinding: PopupMenuLayoutBinding =
            PopupMenuLayoutBinding.inflate(inflater, null, false)

        val popupWindow = PopupWindow(
            popupViewBinding.root,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT, true
        )
        popupViewBinding.title1.text = title1
        popupViewBinding.title2.text = title2
        popupViewBinding.image1.setImageResource(image1)
        popupViewBinding.image2.setImageResource(image2)
        popupViewBinding.view1.click {
            popupWindow.dismiss()
            onClickListener.onClickItemOne()
        }
        popupViewBinding.view2.click {
            popupWindow.dismiss()
            onClickListener.onClickItemTwo()
        }
        popupWindow.elevation = 5.0f
        val offset = 170.dpToPx(context.resources)
        popupWindow.showAsDropDown(anchor, 0, -offset)
        ImageViewCompat.setImageTintList(
            imageView,
            ColorStateList.valueOf(ContextCompat.getColor(context, R.color.Primary500))
        )

        popupWindow.setOnDismissListener {
            ImageViewCompat.setImageTintList(
                imageView,
                ColorStateList.valueOf(ContextCompat.getColor(context, R.color.black))
            )
        }
    }

}


interface PopUpOnClickListener {
    fun onClickItemOne() {}
    fun onClickItemTwo() {}
}