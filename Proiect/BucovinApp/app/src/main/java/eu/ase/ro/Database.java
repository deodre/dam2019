package dam.ase.ro;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {User.class, Recenzie.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract UserDAO getUserDAO();
    public abstract RecenzieDAO getRecenzieDAO();
}
