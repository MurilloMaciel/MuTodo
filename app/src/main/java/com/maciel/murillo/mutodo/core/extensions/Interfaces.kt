package com.maciel.murillo.mutodo.core.extensions

import android.text.Editable
import android.text.TextWatcher
import android.view.animation.Animation
import android.widget.SeekBar
import androidx.transition.Transition
import androidx.viewpager.widget.ViewPager

fun newTextWatcher(afterTextChange: ((Editable?) -> Unit)? = null,
                   beforeTextChanged: ((CharSequence?, Int, Int, Int) -> Unit)? = null,
                   onTextChanged: ((CharSequence?, Int, Int, Int) -> Unit)? = null): TextWatcher =
        object : TextWatcher {
            override fun afterTextChanged(e: Editable?) {
                afterTextChange?.invoke(e)
            }

            override fun beforeTextChanged(cs: CharSequence?, start: Int, count: Int, after: Int) {
                beforeTextChanged?.invoke(cs, start, count, after)
            }

            override fun onTextChanged(cs: CharSequence?, start: Int, before: Int, count: Int) {
                onTextChanged?.invoke(cs, start, before, count)
            }
        }

fun newSeekBarChangeListener(onProgressChanged: ((SeekBar?, Int, Boolean) -> Unit)? = null,
                             onStartTrackingTouch: ((SeekBar?) -> Unit)? = null,
                             onStopTrackingTouch: ((SeekBar?) -> Unit)? = null): SeekBar.OnSeekBarChangeListener =
        object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                onProgressChanged?.invoke(seekBar, progress, fromUser)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                onStartTrackingTouch?.invoke(seekBar)
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                onStopTrackingTouch?.invoke(seekBar)
            }
        }

fun newAnimationListener(onAnimationStart: ((Animation?) -> Unit)? = null,
                         onAnimationEnded: ((Animation?) -> Unit)? = null,
                         onAnimationRepeat: ((Animation?) -> Unit)? = null): Animation.AnimationListener =
        object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
                onAnimationRepeat?.invoke(animation)
            }

            override fun onAnimationEnd(animation: Animation?) {
                onAnimationEnded?.invoke(animation)
            }

            override fun onAnimationStart(animation: Animation?) {
                onAnimationStart?.invoke(animation)
            }

        }

fun newTransitionListener(onTransitionEnd: ((Transition?) -> Unit)? = null,
                          onTransitionResume: ((Transition?) -> Unit)? = null,
                          onTransitionPause: ((Transition?) -> Unit)? = null,
                          onTransitionCancel: ((Transition?) -> Unit)? = null,
                          onTransitionStart: ((Transition?) -> Unit)? = null): Transition.TransitionListener =
        object : Transition.TransitionListener {
            override fun onTransitionEnd(transition: Transition) {
                onTransitionEnd?.invoke(transition)
            }

            override fun onTransitionResume(transition: Transition) {
                onTransitionResume?.invoke(transition)
            }

            override fun onTransitionPause(transition: Transition) {
                onTransitionPause?.invoke(transition)
            }

            override fun onTransitionCancel(transition: Transition) {
                onTransitionCancel?.invoke(transition)
            }

            override fun onTransitionStart(transition: Transition) {
                onTransitionStart?.invoke(transition)
            }
        }

fun newOnPageChangeListener(onPageScrollStateChanged: ((p0: Int) -> Unit)? = null,
                            onPageScrolled: ((p0: Int, p1: Float, p2: Int) -> Unit)? = null,
                            onPageSelected: ((p0: Int) -> Unit)? = null): ViewPager.OnPageChangeListener =
        object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
                onPageScrollStateChanged?.invoke(p0)
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
                onPageScrolled?.invoke(p0, p1, p2)
            }

            override fun onPageSelected(p0: Int) {
                onPageSelected?.invoke(p0)
            }
        }