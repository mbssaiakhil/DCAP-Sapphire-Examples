/*
 * Stub for class console.TodoConsoleManager
 * Generated by Sapphire Compiler (sc).
 */
package console.stubs;


public final class TodoConsoleManager_Stub extends console.TodoConsoleManager implements sapphire.common.AppObjectStub {

    sapphire.policy.SapphirePolicy.SapphireClientPolicy $__client = null;
    boolean $__directInvocation = false;

    public TodoConsoleManager_Stub () {
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



    // Implementation of getTodoList()
    public todolist.TodoList getTodoList() {
        java.lang.Object $__result = null;
        try {
            if ($__directInvocation)
                $__result = super.getTodoList();
            else {
                java.util.ArrayList<Object> $__params = new java.util.ArrayList<Object>();
                String $__method = "public todolist.TodoList console.TodoConsoleManager.getTodoList()";
                $__result = $__client.onRPC($__method, $__params);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ((todolist.TodoList) $__result);
    }
}
