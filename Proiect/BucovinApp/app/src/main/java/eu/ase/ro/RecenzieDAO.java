package eu.ase.ro;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RecenzieDAO {

    @Insert
    public void insertRecenzie(Recenzie recenzie);

    @Query("select * from recenzii where userID=:userID;")
    public List<Recenzie> selectToateRecenziile(int userID);

    @Query("select* from recenzii where userID=:userID and rating>4")
    public List<Recenzie> selectRecenziiPozitive(int userID);

    @Query("update recenzii set recenzie=:recenzie where id=:id;")
    public void updateRecenzie(String recenzie, int id);

    @Query("update recenzii set rating=:rating where id=:id;")
    public void updateRecenzieRating(float rating, int id);

    @Query("delete from recenzii where id=:id;")
    public void deleteRecenzie(int id);
}
