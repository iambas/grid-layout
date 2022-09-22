package com.suphakrit.gridapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suphakrit.gridapplication.adapter.MenuDetailModel
import com.suphakrit.gridapplication.adapter.MenuModel
import com.suphakrit.gridapplication.adapter.MenuType

class MainViewModel : ViewModel() {

    private val _displayMenu by lazy { MutableLiveData<List<MenuModel>>() }
    val displayMenu: LiveData<List<MenuModel>> get() = _displayMenu

    val menu = listOf(
        MenuModel(
            id = "1",
            type = MenuType.MAIN,
            name = "Menu 1",
            menuDetailIndex = 3,
            menuDetails = listOf(
                MenuDetailModel(
                    id = "1-1",
                    name = "Menu 1-1",
                ),
                MenuDetailModel(
                    id = "1-2",
                    name = "Menu 1-2",
                ),
                MenuDetailModel(
                    id = "1-3",
                    name = "Menu 1-3",
                ),
                MenuDetailModel(
                    id = "1-4",
                    name = "Menu 1-4",
                ),
            ),
        ),
        MenuModel(
            id = "2",
            type = MenuType.MAIN,
            name = "Menu 2",
            menuDetailIndex = 3,
            menuDetails = listOf(
                MenuDetailModel(
                    id = "2-1",
                    name = "Menu 2-1",
                ),
                MenuDetailModel(
                    id = "2-2",
                    name = "Menu 2-2",
                ),
                MenuDetailModel(
                    id = "2-3",
                    name = "Menu 2-3",
                ),
                MenuDetailModel(
                    id = "2-4",
                    name = "Menu 2-4",
                ),
            ),
        ),
        MenuModel(
            id = "3",
            type = MenuType.MAIN,
            name = "Menu 3",
            menuDetailIndex = 3,
            menuDetails = listOf(
                MenuDetailModel(
                    id = "3-1",
                    name = "Menu 3-1",
                ),
                MenuDetailModel(
                    id = "3-2",
                    name = "Menu 3-2",
                ),
                MenuDetailModel(
                    id = "3-3",
                    name = "Menu 3-3",
                ),
                MenuDetailModel(
                    id = "3-4",
                    name = "Menu 3-4",
                ),
            ),
        ),
        MenuModel(
            id = "4",
            type = MenuType.DETAIL,
            name = "Menu 4",
            menuDetailIndex = 3,
            menuDetails = listOf(),
        ),

        MenuModel(
            id = "5",
            type = MenuType.MAIN,
            name = "Menu 5",
            menuDetailIndex = 7,
            menuDetails = listOf(
                MenuDetailModel(
                    id = "5-1",
                    name = "Menu 5-1",
                ),
                MenuDetailModel(
                    id = "5-2",
                    name = "Menu 5-2",
                ),
                MenuDetailModel(
                    id = "5-3",
                    name = "Menu 5-3",
                ),
                MenuDetailModel(
                    id = "5-4",
                    name = "Menu 5-4",
                ),
            ),
        ),
        MenuModel(
            id = "6",
            type = MenuType.MAIN,
            name = "Menu 6",
            menuDetailIndex = 7,
            menuDetails = listOf(
                MenuDetailModel(
                    id = "6-1",
                    name = "Menu 6-1",
                ),
                MenuDetailModel(
                    id = "6-6",
                    name = "Menu 6-2",
                ),
                MenuDetailModel(
                    id = "6-3",
                    name = "Menu 6-3",
                ),
                MenuDetailModel(
                    id = "6-4",
                    name = "Menu 6-4",
                ),
            ),
        ),
        MenuModel(
            id = "7",
            type = MenuType.MAIN,
            name = "Menu 7",
            menuDetailIndex = 7,
            menuDetails = listOf(
                MenuDetailModel(
                    id = "7-1",
                    name = "Menu 7-1",
                ),
                MenuDetailModel(
                    id = "7-2",
                    name = "Menu 7-2",
                ),
                MenuDetailModel(
                    id = "7-3",
                    name = "Menu 7-3",
                ),
                MenuDetailModel(
                    id = "7-4",
                    name = "Menu 7-4",
                ),
            ),
        ),
        MenuModel(
            id = "8",
            type = MenuType.DETAIL,
            name = "Menu 8",
            menuDetailIndex = 7,
            menuDetails = listOf(),
        ),
    )

    fun displayMenu() {
        _displayMenu.value = menu
    }

    fun onMainMenuClicked(menuItem: MenuModel) {
        menu.map {
            it.isSelected = it.id == menuItem.id
            if (it.type == MenuType.DETAIL) {
                it.menuDetails = listOf()
            }
            it
        }
        menu[menuItem.menuDetailIndex].menuDetails = menuItem.menuDetails
        displayMenu()
    }
}
