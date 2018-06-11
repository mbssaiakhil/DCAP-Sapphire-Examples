/*
 * Stub for class todolist.TodoList
 * Generated by Sapphire Compiler (sc).
 */
package todolist.stubs;


public final class TodoList_Stub extends todolist.TodoList implements sapphire.common.AppObjectStub {

    sapphire.policy.SapphirePolicy.SapphireClientPolicy $__client = null;
    boolean $__directInvocation = false;

    public TodoList_Stub () {
        super();
    }


    public void $__initialize(sapphire.policy.SapphirePolicy.SapphireClientPolicy client) {
        $__client = client;
    }

    public void $__initialize(boolean directInvocation) {
        $__directInvocation = directInvocation;
    }

    public Object $__clone() throws CloneNotSupportedException {
        return super.clone();
    }



    // Implementation of showAllTodos()
    public void showAllTodos() {
        java.lang.Object $__result = null;
        try {
            if ($__directInvocation)
                super.showAllTodos();
            else {
                java.util.ArrayList<Object> $__params = new java.util.ArrayList<Object>();
                String $__method = "public void todolist.TodoList.showAllTodos()";
                $__result = $__client.onRPC($__method, $__params);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Implementation of migrateObject(InetSocketAddress)
    public void migrateObject(java.net.InetSocketAddress $param_InetSocketAddress_1)
            throws java.lang.Exception {
        java.lang.Object $__result = null;
        try {
            if ($__directInvocation)
                super.migrateObject( $param_InetSocketAddress_1);
            else {
                java.util.ArrayList<Object> $__params = new java.util.ArrayList<Object>();
                String $__method = "public void todolist.TodoList.migrateObject(java.net.InetSocketAddress) throws java.lang.Exception";
                $__params.add($param_InetSocketAddress_1);
                $__result = $__client.onRPC($__method, $__params);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Implementation of addTodo(String)
    public void addTodo(java.lang.String $param_String_1) {
        java.lang.Object $__result = null;
        try {
            if ($__directInvocation)
                super.addTodo( $param_String_1);
            else {
                java.util.ArrayList<Object> $__params = new java.util.ArrayList<Object>();
                String $__method = "public void todolist.TodoList.addTodo(java.lang.String)";
                $__params.add($param_String_1);
                $__result = $__client.onRPC($__method, $__params);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
