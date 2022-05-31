package com.cibo.cibo.fragment


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.cibo.cibo.databinding.ProductAboutFragmentBinding
import com.cibo.cibo.model.Food
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.cibo.cibo.R


class ProductAboutFragment : SuperBottomSheetFragment() {

    companion object {
        fun newInstance(food: Food): ProductAboutFragment {
            val newFragment = ProductAboutFragment()
            val args = Bundle()
            args.putSerializable("productAbout", food)
            newFragment.arguments = args
            return newFragment
        }
    }

    private var _bn: ProductAboutFragmentBinding? = null
    private val bn get() = _bn!!

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
        bn.apply {
            tvProductAbout.text = food.img + "\n\n\n" + food.img
            tvProductName.text = food.content
            tvProductPrice.text = "90 000 so`m"
            tvProductCount.text = "1"
            Glide.with(requireContext()).load(food.img).into(bn.ivFoodDetail)

            btnCountPlus.setOnClickListener {
                tvProductCount.text =
                    (Integer.valueOf(tvProductCount.text.toString()) + 1).toString()
            }

            btnCountMinus.setOnClickListener {
                tvProductCount.apply {
                    if (Integer.valueOf(text.toString()) > 1) {
                        text = (Integer.valueOf(text.toString()) - 1).toString()
                    }
                }
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