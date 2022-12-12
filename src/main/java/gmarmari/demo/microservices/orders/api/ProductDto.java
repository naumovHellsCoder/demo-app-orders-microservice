package gmarmari.demo.microservices.orders.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Objects;

@Schema(name = "Product_V1")
public final class ProductDto {

    public final long id;

    @NonNull
    public final String name;
    @NonNull
    public final String brand;
    @Nullable
    public final String description;

    @ApiModelProperty(example = "red")
    @Nullable
    public final String color;

    @ApiModelProperty(example = "120")
    public final int lengthMm;

    @ApiModelProperty(example = "120")
    public final int widthMm;

    @ApiModelProperty(example = "120")
    public final int heightMm;


    @ApiModelProperty(example = "1500")
    public final int weightGrams;


    @ApiModelProperty(example = "8.9")
    public final double prizeEuro;

    @JsonCreator
    public ProductDto(@JsonProperty("id") long id,
                      @NonNull @JsonProperty("name") String name,
                      @NonNull @JsonProperty("brand") String brand,
                      @Nullable @JsonProperty("description") String description,
                      @Nullable @JsonProperty("color") String color,
                      @JsonProperty("lengthMm") int lengthMm,
                      @JsonProperty("widthMm") int widthMm,
                      @JsonProperty("heightMm") int heightMm,
                      @JsonProperty("weightGrams") int weightGrams,
                      @JsonProperty("prizeEuro") double prizeEuro) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.color = color;
        this.lengthMm = lengthMm;
        this.widthMm = widthMm;
        this.heightMm = heightMm;
        this.weightGrams = weightGrams;
        this.prizeEuro = prizeEuro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return id == that.id && lengthMm == that.lengthMm && widthMm == that.widthMm && heightMm == that.heightMm && weightGrams == that.weightGrams && Double.compare(that.prizeEuro, prizeEuro) == 0 && Objects.equals(name, that.name) && Objects.equals(brand, that.brand) && Objects.equals(description, that.description) && Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand, description, color, lengthMm, widthMm, heightMm, weightGrams, prizeEuro);
    }

    @Override
    public String toString() {
        return "ProductDto{" +
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
