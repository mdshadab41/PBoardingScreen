package com.example.pboardingscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private  lateinit var onboardingItemsAdapter: onboardingItemsAdapter
    private lateinit var indicatorsContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setOnboardngItems()
        setupIndicators()
        setCurrentIndicator(0)

    }

    private fun setOnboardngItems(){
        onboardingItemsAdapter = onboardingItemsAdapter(
            listOf(
                onBoardingItem(
                    onboardingImage = R.drawable.task,
                    title = "Manage Your Task",
                    description = "Organize all your to do's and projects. color tag them to set priorites and categories."
                ),
                onBoardingItem(
                    onboardingImage = R.drawable.time,
                    title = "Work on time",
                    description = "When you're overwhelemd by the amount of work you have on your plate, stop and reflect."
                ),
                onBoardingItem(
                    onboardingImage = R.drawable.reminder,
                    title = "Get reminder on time",
                    description = "When you encounter a small task that takes less than 5 minutes to complete."
                )
            )
        )
        val onboardingViewpager = findViewById<ViewPager2>(R.id.onboardingViewPager)
        onboardingViewpager.adapter = onboardingItemsAdapter
        onboardingViewpager.registerOnPageChangeCallback(object:
            ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
            })
        (onboardingViewpager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER
        findViewById<ImageView>(R.id.imageNext).setOnClickListener {
            if (onboardingViewpager.currentItem + 1 < onboardingItemsAdapter.itemCount) {
                onboardingViewpager.currentItem += 1
            } else {
                naviagteToHomeActivity()
            }
        }
        findViewById<TextView>(R.id.textSkip).setOnClickListener {
            naviagteToHomeActivity()
        }
        findViewById<TextView>(R.id.buttonGetStarted).setOnClickListener {
            naviagteToHomeActivity()
        }

    }

    private fun naviagteToHomeActivity(){
        startActivity(Intent(applicationContext, HomeActivity::class.java))
        finish()
    }

    private fun setupIndicators() {
       //indicatorsContainer = findViewById(R.id.indicatorsContainer)
        val indicators = arrayOfNulls<ImageView>(onboardingItemsAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams = layoutParams
                indicatorsContainer.addView(it)
            }
        }
    }
    private fun setCurrentIndicator(position: Int) {
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount){
            val imageView = indicatorsContainer.getChildAt(i) as ImageView
            if(i == position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_background
                    )
                )
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }
}