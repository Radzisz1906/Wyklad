package com.example.wyklad

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup



/**
 * A simple [Fragment] subclass.
 * Use the [LoadFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoadFragment : Fragment() {
    private var mBackgroundView: View? = null
    private var mNiewiemView: View? = null

    private var startColor = 0
    private var endColor = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_load, container, false)
        mBackgroundView = view.findViewById(R.id.frame)
        mNiewiemView = view.findViewById(R.id.niewiem)

        startColor = resources.getColor(R.color.white)
        endColor = resources.getColor(R.color.black)
        startAnimation()
        return view
    }

    private fun startAnimation() {
        val height: Float = mBackgroundView!!.height.toFloat()
        val rotationAnimator = ObjectAnimator
            .ofFloat(mNiewiemView, "rotation", 0f, 360f)
            .setDuration(1000)
        rotationAnimator.repeatCount = 5
        rotationAnimator.repeatMode = ValueAnimator.RESTART

        val heightAnimator = ObjectAnimator
            .ofFloat(mNiewiemView, "scaleY", 0.5f)
            .setDuration(1000)
        heightAnimator.repeatCount = 4
        heightAnimator.repeatMode = ValueAnimator.REVERSE

        val widthAnimator = ObjectAnimator
            .ofFloat(mNiewiemView, "scaleX", 2f)
            .setDuration(1000)
        widthAnimator.repeatCount = 5
        widthAnimator.repeatMode = ValueAnimator.REVERSE

        val hideAnimator = ObjectAnimator
            .ofFloat(mNiewiemView, "scaleY", 0f)
            .setDuration(1000)

        val colorAnimator = ObjectAnimator
            .ofInt(mBackgroundView, "backgroundColor", startColor, endColor)
            .setDuration(1000)

        colorAnimator.setEvaluator(ArgbEvaluator())

        val animatorSet = AnimatorSet()
        animatorSet
            .play(heightAnimator)
            .with(rotationAnimator)
            .with(widthAnimator)
            .before(hideAnimator)
            .before(colorAnimator)
        animatorSet.start()
    }

    companion object{
        fun newInstance(): LoadFragment {
            return LoadFragment()
        }
    }
}