package com.suphakrit.gridapplication

data class CategoryResponse(
    val id: String,
    val name: String,
    val subCategories: List<SubCategoryResponse>,
)

data class SubCategoryResponse(
    val id: String,
    val name: String,
)
