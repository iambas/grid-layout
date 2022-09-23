package com.suphakrit.gridapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suphakrit.gridapplication.adapter.MenuDetailModel
import com.suphakrit.gridapplication.adapter.MenuModel
import com.suphakrit.gridapplication.adapter.MenuType

class MainViewModel : ViewModel() {

    private val _displayMenu by lazy { MutableLiveData<List<MenuModel>>() }
    val displayMenu: LiveData<List<MenuModel>> get() = _displayMenu

    private val menu = mutableListOf<MenuModel>()

    fun createMenu() {
        menu.clear()
        var indexCounter = 0
        CATEGORIES.forEachIndexed { index, category ->
            menu.add(
                MenuModel(
                    type = MenuType.MAIN,
                    id = category.id,
                    name = category.name,
                    index = indexCounter++,
                    menuDetails = category.subCategories.map { sub ->
                        MenuDetailModel(
                            id = sub.id,
                            name = sub.name,
                        )
                    }
                )
            )

            val isMenuDetailIndex = ((index + 1) % CATEGORY_SPAN == 0)
                    || (index == CATEGORIES.lastIndex && (index + 1) % CATEGORY_SPAN != 0)
            if (isMenuDetailIndex) {
                menu.add(
                    MenuModel(
                        type = MenuType.DETAIL,
                        id = category.id,
                        name = category.name,
                        index = indexCounter++,
                        menuDetails = listOf(),
                    )
                )
            }
        }

        Log.d("MainViewModel", "menu count: ${menu.count()}\n$menu")
    }

    fun displayMenu() {
        _displayMenu.value = menu
    }

    fun onMainMenuClicked(menuItem: MenuModel) {
        Log.d("MainViewModel", "onMainMenuClicked: $menuItem")
        menu.map {
            it.isSelected = it.id == menuItem.id
            if (it.type == MenuType.DETAIL) {
                it.menuDetails = listOf()
            }
            it
        }

        for (index in menu.indices) {
            val item = menu[index]
            if (item.type == MenuType.DETAIL && index > menuItem.index) {
                menuItem.menuDetails.map {
                    it.isSelected = false
                }
                menu[index].menuDetails = menuItem.menuDetails
                break
            }
        }

        displayMenu()
    }

    fun onSubMenuClicked(menuItem: MenuModel) {
        Log.d("MainViewModel", "onSubMenuClicked: $menuItem")
    }
}
