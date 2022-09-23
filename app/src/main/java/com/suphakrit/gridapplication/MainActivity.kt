package com.suphakrit.gridapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.suphakrit.gridapplication.adapter.MenuAdapter
import com.suphakrit.gridapplication.adapter.MenuType
import com.suphakrit.gridapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()
    private val menuAdapter by lazy {
        MenuAdapter(
            onMainItemClicked = viewModel::onMainMenuClicked,
            onSubMenuClicked = viewModel::onSubMenuClicked,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observe()
        setUpView()
        viewModel.createMenu()
        viewModel.displayMenu()
    }

    private fun setUpView() {
        binding.menuRecyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, CATEGORY_SPAN).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when (menuAdapter.getItemViewType(position)) {
                            MenuType.MAIN.ordinal -> 1
                            else -> CATEGORY_SPAN
                        }
                    }
                }
            }
            adapter = menuAdapter
        }
    }

    private fun observe() {
        viewModel.displayMenu.observe(this) {
            menuAdapter.update(it)
        }
    }
}
