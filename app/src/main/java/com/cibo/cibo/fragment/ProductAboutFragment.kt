package com.cibo.cibo.fragment


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.cibo.cibo.databinding.ProductAboutFragmentBinding
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.cibo.cibo.R
import com.cibo.cibo.model.Food
import com.cibo.cibo.utils.Constants.LOAD_ATTACH_URL


class ProductAboutFragment : SuperBottomSheetFragment() {

    companion object {
        /*fun newInstance(food: Food): ProductAboutFragment {
            val newFragment = ProductAboutFragment()
            val args = Bundle()
            args.putSerializable("productAbout", food)
            newFragment.arguments = args
            return newFragment
        }*/

        const val REQUEST_KEY = "selectedProduct"
    }

    private var _bn: ProductAboutFragmentBinding? = null
    private val bn get() = _bn!!

    private var productCount = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _bn = ProductAboutFragmentBinding.inflate(inflater, container, false)
        return bn.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.window?.attributes?.windowAnimations = R.style.BottomsheetDialogAnim
    }

    override fun onDestroy() {
        super.onDestroy()
        _bn = null
    }

    private fun initViews() {
        val food: Food = arguments?.getSerializable("productAbout") as Food
        val isAbout: Boolean = arguments?.getBoolean("isAbout") as Boolean
        bn.apply {
            tvProductAbout.text = food.description
            tvProductName.text = food.name
            tvProductPrice.text = food.price.toString().plus(" so'm")
            tvProductCount.text = productCount.toString()
            Glide.with(requireContext()).load(LOAD_ATTACH_URL + food.attachId).into(bn.ivFoodDetail)

            if (isAbout) viewSetParamsFood.visibility = View.GONE

            btnCountPlus.setOnClickListener {
                productCount++
                tvProductCount.text = productCount.toString()
                tvProductPrice.text = (productCount * food.price!!).toInt().toString().plus(" so'm")
            }

            btnCountMinus.setOnClickListener {
                tvProductCount.apply {
                    if (productCount > 1) {
                        productCount--
                        text = productCount.toString()
                    }
                }

                tvProductPrice.text = (productCount * food.price!!).toInt().toString().plus(" so'm")
            }

            btnAddToCart.setOnClickListener {
                val args = Bundle()
                args.putSerializable("food", food)
                args.putInt("foodCount", productCount)
                setFragmentResult(REQUEST_KEY, args)
                findNavController().navigateUp()
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    override fun getBackgroundColor(): Int {
        return android.R.color.transparent
    }

    override fun getPeekHeight(): Int {
        return -1
    }

    override fun animateStatusBar(): Boolean {
        return false
    }

}