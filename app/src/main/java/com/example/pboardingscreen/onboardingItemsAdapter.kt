package com.example.pboardingscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class onboardingItemsAdapter(private val onboaringitems: List<onBoardingItem>):
RecyclerView.Adapter<onboardingItemsAdapter.OnboardingItemsViewHolder>(){

    inner class OnboardingItemsViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val imageOnboarding = view.findViewById<ImageView>(R.id.imageOnboarding)
        private val textTitle = view.findViewById<TextView>(R.id.textTitle)
        private val textDescription = view.findViewById<TextView>(R.id.textDescription)

        fun bind(onBoardingItem: onBoardingItem) {
            imageOnboarding.setImageResource(onBoardingItem.onboardingImage)
            textTitle.text = onBoardingItem.title
            textDescription.text = onBoardingItem.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemsViewHolder {
        return OnboardingItemsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.onboarding_item_container,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
       return onboaringitems.size
    }

    override fun onBindViewHolder(holder: OnboardingItemsViewHolder, position: Int) {
        holder.bind(onboaringitems[position])
    }
}