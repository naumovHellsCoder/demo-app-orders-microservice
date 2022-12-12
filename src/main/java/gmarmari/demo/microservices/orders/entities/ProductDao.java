package gmarmari.demo.microservices.orders.entities;


import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "products")
public class ProductDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String name = "";
    @NonNull
    private String brand = "";
    @Nullable
    private String description;
    @Nullable
    private String color;
    private int lengthMm;
    private int widthMm;
    private int heightMm;
    private int weightGrams;
    private double prizeEuro;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getBrand() {
        return brand;
    }

    public void setBrand(@NonNull String brand) {
        this.brand = brand;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    @Nullable
    public String getColor() {
        return color;
    }

    public void setColor(@Nullable String color) {
        this.color = color;
    }

    public int getLengthMm() {
        return lengthMm;
    }

    public void setLengthMm(int lengthMm) {
        this.lengthMm = lengthMm;
    }

    public int getWidthMm() {
        return widthMm;
    }

    public void setWidthMm(int widthMm) {
        this.widthMm = widthMm;
    }

    public int getHeightMm() {
        return heightMm;
    }

    public void setHeightMm(int heightMm) {
        this.heightMm = heightMm;
    }

    public int getWeightGrams() {
        return weightGrams;
    }

    public void setWeightGrams(int weightGrams) {
        this.weightGrams = weightGrams;
    }

    public double getPrizeEuro() {
        return prizeEuro;
    }

    public void setPrizeEuro(double prizeEuro) {
        this.prizeEuro = prizeEuro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDao that = (ProductDao) o;
        return id == that.id && lengthMm == that.lengthMm && widthMm == that.widthMm && heightMm == that.heightMm && weightGrams == that.weightGrams && Double.compare(that.prizeEuro, prizeEuro) == 0 && name.equals(that.name) && brand.equals(that.brand) && Objects.equals(description, that.description) && Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand, description, color, lengthMm, widthMm, heightMm, weightGrams, prizeEuro);
    }

    @Override
    public String toString() {
        return "ProductDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", color='" + color + '\'' +
                ", lengthMm=" + lengthMm +
                ", widthMm=" + widthMm +
                ", heightMm=" + heightMm +
                ", weightGrams=" + weightGrams +
                ", prizeEuro=" + prizeEuro +
                '}';
    }
}
