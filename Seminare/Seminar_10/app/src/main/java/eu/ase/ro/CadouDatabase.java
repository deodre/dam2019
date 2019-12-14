package eu.ase.ro;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Cadou.class}, version = 1)
public abstract class CadouDatabase extends RoomDatabase {
    public abstract CadouDAO getCadouDAO();
}
