package todolist;

import sapphire.app.SapphireObject;
import sapphire.policy.mobility.explicitmigration.ExplicitMigrationPolicy;

import java.net.InetSocketAddress;
import java.util.ArrayList;

public class TodoList implements SapphireObject<ExplicitMigrationPolicy> {
    private ArrayList<String> todoList;
    private static int cnt = 0;

    public TodoList() {
        todoList = new ArrayList<String>();
    }

    public void addTodo(String todo) {
        todoList.add(todo);
    }

    public void migrateObject(InetSocketAddress destAddr) throws Exception {
        System.out.println("Inside the migrateObject of app directly with count as " + cnt++ + " with move to " + destAddr);
    }

    public void showAllTodos() {
        for (int i = 0; i < todoList.size(); i++) {
            System.out.println(todoList.get(i));
        }
    }
}
