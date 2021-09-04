package one.digitalinnovation.todolist.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.main.activity_add_task.*
import one.digitalinnovation.todolist.databinding.ActivityAddTaskBinding
import one.digitalinnovation.todolist.datasource.TaskDataSource
import one.digitalinnovation.todolist.extensions.format
import one.digitalinnovation.todolist.extensions.text
import one.digitalinnovation.todolist.model.Task
import java.util.*

class AddTaskActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        insertListeners()
    }

    private fun insertListeners() {
        binding.textInputData.editText?.setOnClickListener {
           val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener {
               val  timeZone = TimeZone.getDefault()
               val  offset = timeZone.getOffset(Date().time) *-1
                binding.textInputData.text =  Date(it + offset).format()
            }
            datePicker.show(supportFragmentManager, "DATE_PIKER_TAG")
        }

        binding.textInputHora.editText?.setOnClickListener {
            val  timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()
            timePicker.addOnPositiveButtonClickListener {
                val minute = if (timePicker.minute in 0..9)"0${timePicker.minute}" else timePicker.minute
                val hour = if (timePicker.hour in 0..9) "0${timePicker.hour}" else timePicker.hour
                binding.textInputHora.text = "$hour:$minute"

            }
            timePicker.show(supportFragmentManager, "TIME_PIKER_TAG")
        }

        binding.buttonCancel.setOnClickListener {
            finish()
        }

        binding.buttonNew.setOnClickListener {
            val task = Task(
                title = binding.textInputTitle.text,
                description = binding.textInputDescription.text,
                date = binding.textInputData.text,
                hour = binding.textInputHora.text
            )
            TaskDataSource.insertTask(task)
            Log.e("TAG", "insertListeners:  " + TaskDataSource.getList())

        }
    }

}