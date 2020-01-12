package dam.ase.ro;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDAO {

    @Insert
    public void insertUser(User user);

    @Query("select * from users where id=:id;")
    public User selectSearchUserById(int id);

    @Query("select * from users where username=:username;")
    public User selectSearchUserByUsername(String username);

    @Query("select * from users where parola=:parola;")
    public User selectSearchUserByPassword(String parola);

    @Query("update users set parola=:parola where id=:id;")
    public void updateParola(String parola, int id);

    @Query("delete from users where id=:id;")
    public void deleteUser(int id);

}
