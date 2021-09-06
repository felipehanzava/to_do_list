package one.digitalinnovation.todolist.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import one.digitalinnovation.todolist.databinding.ActivityMainBinding
import one.digitalinnovation.todolist.datasource.TaskDataSource


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy {TaskListAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTask.adapter = adapter
        updateList()


        insertListeners()
    }

    private fun insertListeners() {
        binding.buttonAdd.setOnClickListener {
            startActivityForResult(Intent(this, AddTaskActivity::class.java), CREAT_NEW_TASK)
        }

        adapter.listenerEdit = {
            val intent = Intent( this, AddTaskActivity::class.java)
            intent.putExtra(AddTaskActivity.TASK_ID, it.id)
            startActivityForResult(intent, CREAT_NEW_TASK)
        }

        adapter.listenerDelete = {
            TaskDataSource.deleteTask(it)
            updateList()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREAT_NEW_TASK && resultCode == Activity.RESULT_OK) updateList()
    }

    private fun updateList(){
        val list = TaskDataSource.getList()
        binding.includeEmpty.emptyState.visibility  =  if(list.isEmpty()){
            View.VISIBLE
        }else{
            View.GONE
        }
        adapter.submitList(list)
    }

    companion object {
        private const val CREAT_NEW_TASK = 1000
    }

}