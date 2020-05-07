package com.example.foodawi.common

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.example.foodawi.features.meal_categories.MealsActivity
import com.example.foodawi.features.meal_details.MealDetailActivity

object Navigation {

    fun goToMealActivity(ctx: Context, name: String) {
        val intent = Intent(ctx, MealsActivity::class.java)
        intent.putExtra(Constants.CATEGORY_NAME, name)
        ctx.startActivity(intent)
    }

    fun goToDetailActivity(ctx: Context, id: String) {
        val intent = Intent(ctx, MealDetailActivity::class.java)
        intent.apply {
            putExtra(Constants.MEAL_ID,id)
            ctx.startActivity(this)
        }
    }

}