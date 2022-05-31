package com.cibo.cibo.fragment


import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.cibo.cibo.model.Category
import com.cibo.cibo.model.Food

open class BaseFragment : Fragment() {

    fun setTransparentStatusBarColor(
        context: Context,
        textColor: Int?,
        statusColor: Int?,
        lightStatus: Int?
    ) {
        requireActivity().window.decorView.systemUiVisibility =
            ContextCompat.getColor(context, textColor!!) //  set status text dark
        requireActivity().window.statusBarColor = ContextCompat.getColor(
            context,
            statusColor!!
        ) // set status bar color
        requireActivity().window.decorView.systemUiVisibility = lightStatus!!
    }



    //hideKeyboard
    fun hideKeyboard() {
        val manage =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manage.hideSoftInputFromWindow(requireView().windowToken, 0)
    }


    //showKeyboard
    fun showKeyboard(editText: EditText) {
        editText.requestFocus()
        val content =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        content.showSoftInput(editText, 0)
        content.toggleSoftInput(
            InputMethodManager.SHOW_FORCED,
            InputMethodManager.HIDE_IMPLICIT_ONLY
        )
    }


    //Toast
    fun toaster(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }


    val categories = mutableListOf(
        Category(
            "Burgers",
            Food(
                "Big Burger",
                "https://images.unsplash.com/photo-1552526881-721ce8509abb?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=465",
                price = 35000f
            ),
            Food(
                "Medium Burger",
                "https://images.unsplash.com/photo-1594212699903-ec8a3eca50f5?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1171",
                price = 30000f
            ),
            Food(
                "Small Burger",
                "https://images.unsplash.com/photo-1626082892105-1650809e18aa?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170",
                price = 25000f
            ),
        ),
        Category(
            "Pizza",
            Food(
                "Pizza Small",
                "https://images.unsplash.com/photo-1541745537411-b8046dc6d66c?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=388",
                price = 45000f
            ),
            Food(
                "Pizza Medium",
                "https://images.unsplash.com/photo-1590947132387-155cc02f3212?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170",
                price = 55000f
            ),
            Food(
                "Pizza Max",
                "https://images.unsplash.com/photo-1571407970349-bc81e7e96d47?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1025",
                price = 65000f
            ),
        ),
        Category(
            "Drinks",
            Food(
                "Cola",
                "https://images.unsplash.com/photo-1624552184280-9e9631bbeee9?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387",
                price = 10000f
            ),
            Food(
                "Pepsi",
                "https://images.unsplash.com/photo-1629186235045-80d4147d90dc?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=464",
                price = 10000f
            ),
            Food(
                "Fanta",
                "https://images.unsplash.com/photo-1598419161288-9f2f26c85590?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1200",
                price = 10000f
            ),
            Food(
                "Sprite",
                "https://images.unsplash.com/photo-1625772299848-391b6a87d7b3?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387",
                price = 9000f
            ),
        ),
        Category(
            "Burgers",
            Food(
                "Big Burger",
                "https://images.unsplash.com/photo-1552526881-721ce8509abb?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=465",
                price = 35000f
            ),
            Food(
                "Medium Burger",
                "https://images.unsplash.com/photo-1594212699903-ec8a3eca50f5?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1171",
                price = 30000f
            ),
            Food(
                "Small Burger",
                "https://images.unsplash.com/photo-1626082892105-1650809e18aa?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170",
                price = 25000f
            ),
        ),
        Category(
            "Pizza",
            Food(
                "Pizza Small",
                "https://images.unsplash.com/photo-1541745537411-b8046dc6d66c?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=388",
                price = 45000f
            ),
            Food(
                "Pizza Medium",
                "https://images.unsplash.com/photo-1590947132387-155cc02f3212?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170",
                price = 55000f
            ),
            Food(
                "Pizza Max",
                "https://images.unsplash.com/photo-1571407970349-bc81e7e96d47?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1025",
                price = 65000f
            ),
        ),
        Category(
            "Drinks",
            Food(
                "Cola",
                "https://images.unsplash.com/photo-1624552184280-9e9631bbeee9?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387",
                price = 10000f
            ),
            Food(
                "Pepsi",
                "https://images.unsplash.com/photo-1629186235045-80d4147d90dc?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=464",
                price = 10000f
            ),
            Food(
                "Fanta",
                "https://images.unsplash.com/photo-1598419161288-9f2f26c85590?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1200",
                price = 10000f
            ),
            Food(
                "Sprite",
                "https://images.unsplash.com/photo-1625772299848-391b6a87d7b3?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387",
                price = 9000f
            ),
        )
    )
}
