package test1;


import java.io.FileInputStream;
import java.util.Properties;

public abstract class CommandFactory {

	public static AbstractCommand getCommand(RequestContext rc) {
		AbstractCommand command = null;

		Properties prop = new Properties();

		try {
			prop.load(new FileInputStream("C:/eclipse/pleiades/workspace/rjp/src/test1/commands.properties"));

			String name = prop.getProperty(rc.getCommandPath());

			Class c = Class.forName(name);

			command = (AbstractCommand) c.newInstance();

		}catch(Exception e) {
			e.printStackTrace();
		}

		return command;

	}

}
