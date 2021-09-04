package one.digitalinnovation.todolist.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import one.digitalinnovation.todolist.databinding.ActivityAddTaskBinding
import one.digitalinnovation.todolist.extensions.format
import one.digitalinnovation.todolist.extensions.text
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

    }

}