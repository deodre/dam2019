package eu.ase.ro;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "recenzii", foreignKeys = @ForeignKey(entity = User.class, parentColumns = "ID", childColumns = "userID", onDelete = ForeignKey.CASCADE))
public class Recenzie implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private int id;
    private String recenzie;
    private float rating;
    private int userID;

    public Recenzie(String recenzie, float rating, int userID) {
        this.id = 0;
        this.recenzie = recenzie;
        this.rating = rating;
        this.userID = userID;
    }

    protected Recenzie(Parcel in) {
        id = in.readInt();
        recenzie = in.readString();
        rating = in.readFloat();
        userID = in.readInt();
    }

    public static final Creator<Recenzie> CREATOR = new Creator<Recenzie>() {
        @Override
        public Recenzie createFromParcel(Parcel in) {
            return new Recenzie(in);
        }

        @Override
        public Recenzie[] newArray(int size) {
            return new Recenzie[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecenzie() {
        return recenzie;
    }

    public void setRecenzie(String recenzie) {
        this.recenzie = recenzie;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Recenzie{" +
                "id=" + id +
                ", recenzie='" + recenzie + '\'' +
                ", rating=" + rating +
                ", userID=" + userID +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(recenzie);
        dest.writeFloat(rating);
        dest.writeInt(userID);
    }
}
