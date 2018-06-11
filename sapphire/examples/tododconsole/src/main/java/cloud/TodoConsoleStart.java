package cloud;

import console.TodoConsoleManager;
import sapphire.app.AppEntryPoint;
import sapphire.app.AppObjectNotCreatedException;
import sapphire.common.AppObjectStub;

import static sapphire.runtime.Sapphire.new_;

public class TodoConsoleStart implements AppEntryPoint {
    @Override
    public AppObjectStub start() throws AppObjectNotCreatedException {
        return (AppObjectStub) new_(TodoConsoleManager.class);
    }
}