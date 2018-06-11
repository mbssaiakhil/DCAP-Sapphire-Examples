package console;

import sapphire.app.SapphireObject;
import todolist.TodoList;

import static sapphire.runtime.Sapphire.new_;

public class TodoConsoleManager implements SapphireObject {
    //private SamplePrint samplePrint;

    private TodoList todoList;

    public TodoConsoleManager() {

        //samplePrint = (SamplePrint) new_(SamplePrint.class);
        todoList = (TodoList) new_(TodoList.class);
    }

    /*public SamplePrint getSamplePrintManager() {
        return samplePrint;
    }*/

    public TodoList getTodoList() { return todoList; }
}
