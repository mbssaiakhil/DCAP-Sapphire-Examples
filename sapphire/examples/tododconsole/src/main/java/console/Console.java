package console;

import sapphire.kernel.common.GlobalKernelReferences;
import sapphire.kernel.server.KernelServer;
import sapphire.kernel.server.KernelServerImpl;
import sapphire.oms.OMSServer;
import todolist.TodoList;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

public class Console {
    public static  OMSServer server;
    public static Registry registry;
    public static KernelServer nodeServer;
    public static void main(String[] args) {
        TodoConsoleManager todoConsoleManager;
        Scanner sc = new Scanner(System.in);
        String newTodo;
        int cnt = 0;
        todolist.TodoList l = new todolist.TodoList();
        boolean quit = false;
        try {

            // Todo: Remove hardcoded IP Addresses
            String[] hostAddress = new String[4];
            // OMS Host IP
            hostAddress[0] = args[0];
            // OMS Host Port
            hostAddress[1] = args[1];
            // Kernel Server IP
            hostAddress[2] = args[2];
            // Kernel Server Port
            hostAddress[3] = args[3];
            registry = LocateRegistry.getRegistry(hostAddress[0], Integer.parseInt(hostAddress[1]));
            server = (OMSServer) registry.lookup("SapphireOMS");

            nodeServer = new KernelServerImpl(new InetSocketAddress(hostAddress[2], Integer.parseInt(hostAddress[3])), new InetSocketAddress(hostAddress[0], Integer.parseInt(hostAddress[1])));

            todoConsoleManager = (TodoConsoleManager) server.getAppEntryPoint();

            l = todoConsoleManager.getTodoList();

        } catch (Exception e) {
            e.printStackTrace();
        }

        String hostName;
        displayMenu();
        do {
            String choice = sc.next();
            switch (choice) {

                case "add":
                    System.out.println("Please enter your Todo: ");
                    ++cnt;
                    if(cnt%2 == 0) {
                        try {
                            System.out.println("Migrating object with cnt as " + cnt);
                            ArrayList<InetSocketAddress> allKernelServers = server.getServers();
                            System.out.println("All Kernel Servers are " + allKernelServers);
                            InetSocketAddress presentKS = null;
                            KernelServerImpl localKernel = GlobalKernelReferences.nodeServer;
                            presentKS = localKernel.getLocalHost();
                            System.out.println("Present Kernel Servers is " + presentKS);
                            InetAddress hostAddress = allKernelServers.get(1).getAddress();
                            InetSocketAddress destAddr3 = new InetSocketAddress(hostAddress.getHostAddress(), allKernelServers.get(1).getPort());
                            System.out.println("Migrating object with cnt as " + cnt + " to " + destAddr3);
                            ((TodoList)l).migrateObject(destAddr3);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    newTodo = sc.next();
                    l.addTodo(newTodo);
                    displayMenu();
                    break;
                case "ls":
                    l.showAllTodos();
                    displayMenu();
                    break;
                case "exit":
                    quit = true;
                    break;
                default:
                    System.out.println("Please enter only one of the above mentioned command");
                    displayMenu();
                    break;
            }
        } while (!quit);
    }

    static void displayMenu() {
        System.out.println();
        System.out.println("1. Add a new Todo (add)");
        System.out.println("2. Show all Todos (ls)");
        System.out.println("3. exit (Or press escape key to exit) ");
        System.out.println("\nEnter your choice: ");
    }
}
