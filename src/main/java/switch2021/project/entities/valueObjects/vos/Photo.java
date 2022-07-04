package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;
import switch2021.project.utils.ValueObject;

import java.util.Arrays;
import java.util.List;

@Getter

public class Photo implements ValueObject<Photo> {

    private final String extension;

    private final List<String> possibleExtensions = Arrays.asList("jpg", "png");

    public Photo(String photo) {
        if (photo.isEmpty()) {
            this.extension = "";
        } else {
            validateExtension(photo);
            this.extension = photo;
        }
    }


    private void validateExtension(String photo) {

        String extension = "";
        if (photo.contains(".")) {
            extension = Arrays.stream(photo.split("\\.")).reduce((a, b) -> b).orElse(null);
        }

        if (!possibleExtensions.contains(extension))
            throw new IllegalArgumentException("Invalid photo format.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Photo that = (Photo) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return extension.hashCode();
    }

    @Override
    public boolean sameValueAs(Photo other) {
        return other != null && this.extension.equals(other.extension);
    }
}
