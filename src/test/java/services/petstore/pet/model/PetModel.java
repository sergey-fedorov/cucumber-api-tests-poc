package services.petstore.pet.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Objects;

@Data
@Builder
public class PetModel {

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
}

