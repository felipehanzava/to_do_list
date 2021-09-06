package one.digitalinnovation.todolist.datasource

import one.digitalinnovation.todolist.model.Task

object TaskDataSource {
    private val list = arrayListOf<Task>()

    fun getList() = list.toList()

    fun insertTask(task: Task){
        if (task.id == 0){
            list.add(task.copy(id = list.size +1))
        }else{
            list.remove(task)
            list.add(task)
        }

    }

    fun findByID(taskID: Int) = list.find {it.id == taskID }
    fun deleteTask(task: Task) {
        list.remove(task)
    }

}