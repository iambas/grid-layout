package com.suphakrit.gridapplication.adapter

data class MenuModel(
    val type: MenuType,
    val id: String,
    val name: String,
    var index: Int,
    var menuDetails: List<MenuDetailModel>,
    var isSelected: Boolean = false,
)

data class MenuDetailModel(
    val id: String,
    val name: String,
    var isSelected: Boolean = false,
)
