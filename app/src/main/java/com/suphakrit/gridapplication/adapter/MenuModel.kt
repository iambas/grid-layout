package com.suphakrit.gridapplication.adapter

data class MenuModel(
    val id: String,
    val type: MenuType,
    val name: String,
    var isSelected: Boolean = false,
    val menuDetailIndex: Int,
    var menuDetails: List<MenuDetailModel>,
)

data class MenuDetailModel(
    val id: String,
    val name: String,
    var isSelected: Boolean = false,
)
