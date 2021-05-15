package com.LeoMitinskyi.lab6.Commands;

import com.LeoMitinskyi.lab6.Engine;
import com.LeoMitinskyi.lab6.ServerLoop;
import com.LeoMitinskyi.lab6.types.*;
import java.io.IOException;
import java.time.LocalDate;

import static com.LeoMitinskyi.lab6.types.Friends.friends;

public class AddCommand extends Command{
    public AddCommand(Engine engine,ServerLoop serverLoop) {
        super(engine,serverLoop);
    }

    @Override
    public String execute() throws IOException, ClassNotFoundException {
        long id = System.currentTimeMillis() % Integer.MAX_VALUE;

        String name = ioManager.StringReader("Enter name:");
        Integer height = ioManager.IntegerReader("Enter height(it's should be more than zero):");

        float x = ioManager.FloatReader("Enter x(962 is maximum):", 962f);
        float y = ioManager.FloatReader("Enter y(214 is maximum)", 214f);

        LocalDate creationDate = LocalDate.now();

        Color eyeColor = ioManager.ColorReader("Enter eyeColor: {GREEN, " + "RED, " + "BLACK, " + "YELLOW, " + "BROWN;}");
        Color hairColor = ioManager.ColorReader("Enter eyeColor: {GREEN, " + "RED, " + "BLACK, " + "YELLOW, " + "BROWN;}");
        Country nationality = ioManager.CountryReader("Enter country: {USA," + "SPAIN," + "INDIA," + "ITALY," + "JAPAN;}");

        Long X = ioManager.LongReader("Enter X:");
        double Y = ioManager.DoubleReader("Enter Y");
        Float Z = ioManager.FloatReader("Enter Z");

        Coordinates coordinates = new Coordinates(x,y);
        Location location = new Location(X,Y,Z);

        Friends friends = new Friends();
        String FriendName = "";
        do{
            FriendName = ioManager.FriendReader("Введите имя друга");
            friends.AddFriend(FriendName);
        }while (!FriendName.equals(""));

        Person p = new Person(id,name,coordinates,creationDate,height,eyeColor,hairColor,nationality,location,friends);
        collection.add(p);
        return "Object was added: " + p.toString();
    }

    @Override
    public String getDescription() {
        return ":добавить новый элемент в коллекцию";
    }

    @Override
    public String getName() {
        return "add";
    }
}