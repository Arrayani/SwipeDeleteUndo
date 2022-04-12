package com.gamecodeschool.swipedeleteundo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gamecodeschool.swipedeleteundo.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var itemAdapter: ItemAdapter
    private val mList = arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        itemAdapter = ItemAdapter()
        mData()
        swipeToDelete()
    }

    private fun mData(){
        mList.add("Subscribe")
        mList.add("Subscribe")
        mList.add("Subscribe")
        mList.add("Subscribe")
        mList.add("Subscribe")
        mList.add("Subscribe")
        mList.add("Subscribe")
        mList.add("Subscribe")
        mList.add("Subscribe")
        mList.add("Subscribe")
        mList.add("Subscribe")
        mList.add("Subscribe")
        mList.add("Subscribe")
        mList.add("Subscribe")
        mList.add("Subscribe")
        mList.add("Subscribe")
        mList.add("Subscribe")
        mList.add("Subscribe")
        mList.add("Subscribe")

        itemAdapter.differ.submitList(mList)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = itemAdapter
            setHasFixedSize(true)
        }
    }

    private fun swipeToDelete(){
        ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val item = itemAdapter.differ.currentList[position]

                mList.removeAt(position)
                itemAdapter.notifyItemRemoved(position)
                Snackbar.make(
                    binding.rootView,
                    "Item '$item $position' deleted",
                    Snackbar.LENGTH_SHORT
                ).apply {
                    setAction("Undo"){
                        mList.add(item)
                    }
                    show()
                }
            }


        }).attachToRecyclerView(binding.recyclerView)
    }
}