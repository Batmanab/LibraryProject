import java.nio.file.Path;

public class AddCmd extends LibraryCommand {
    String argument;
    public AddCmd(String argumentInput) {
        super(CommandType.ADD, argumentInput);
    }

    @Override
    public CommandType getType() {
        return super.getType();
    }

    @Override
    public void execute(LibraryData data) {
        System.out.println(argument);
        data.loadData(Path.of(argument));
    }

    @Override
    protected boolean parseArguments(String argumentInput) {
        System.out.println("PLOadX");
        argument = argumentInput;
        // check if csv filename is correct
        return argumentInput.contains("csv");
    }
}
