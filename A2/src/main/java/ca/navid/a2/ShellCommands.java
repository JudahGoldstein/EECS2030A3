package ca.navid.a2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

/**
 * Spring Shell methods goes in this class. This allows you to interact with your application at runtime.
 */
@ShellComponent
public class ShellCommands {

    private static final Logger logger = LoggerFactory.getLogger(ShellCommands.class);

    /**
     * Spring Shell method example. Use this in case you want to invalidate your cache.
     * Try running "cacheflush --forced true"
     * @param forced should we force wipe the cache right away?
     * @return message to echo back to the console
     */
    @ShellMethod("Flush the cache ... ")
    public String cacheflush(@ShellOption(defaultValue = "false") String forced)
    {
        if (Boolean.valueOf(forced))
            return "Forcing flushed ...";
        else
            return "Regular flushed ...";
    }

}
