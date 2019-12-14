package eu.ase.ro;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CadouDAO {

    @Insert
    public void insertCadou(Cadou cadou);

    @Query("select * from Cadouri limit 1;")
    public Cadou selectPrimulCadou();

    @Query("select * from Cadouri;")
    public List<Cadou> selectToateCadourile();

    @Query("select * from Cadouri where pret<:pretMaxim;")
    public List<Cadou> selectCadouriIeftine(float pretMaxim);

    @Query("select * from Cadouri where id=:id;")
    public Cadou selectCadouCautat(int id);
}
