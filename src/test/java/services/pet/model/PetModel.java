package services.pet.model;

import com.github.javafaker.Faker;
import core.Model;
import lombok.*;
import services.pet.enums.PetStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PetModel extends Model {

    private long id;
    private Category category;
    private String name;
    private ArrayList<String> photoUrls;
    private ArrayList<Tag> tags;
    private PetStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetModel that = (PetModel) o;
        return Objects.equals(category, that.category) && Objects.equals(name, that.name) && Objects.equals(photoUrls, that.photoUrls) && Objects.equals(tags, that.tags) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, name, photoUrls, tags, status);
    }

    public PetModel defaultBuilder(Faker faker){
        return PetModel.builder()
                .id(faker.number().randomNumber())
                .name(faker.dog().name())
                .status(PetStatus.available)
                .category(new Category(101, "dog"))
                .tags(new ArrayList<>(List.of(new Tag(102, faker.dog().breed()))))
                .photoUrls(new ArrayList<>(List.of(faker.internet().image())))
                .build();
    }
}

