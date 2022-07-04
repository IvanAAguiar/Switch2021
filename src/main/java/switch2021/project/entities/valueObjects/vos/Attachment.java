package switch2021.project.entities.valueObjects.vos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import switch2021.project.utils.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.*;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class Attachment implements ValueObject<Attachment> {

    @Column(name = "attachment")
    private String extension;

    private static final List<String> possibleExtensions = Arrays.asList("pdf", "txt", "jpg", "doc", "docx", "xls", "csv", "xlsb","");

    public Attachment(String attachment) {
        validateExtension(attachment);
        this.extension = attachment;
    }


    private void validateExtension(String fileName) {

        String ext = "";
        if (fileName.contains(".")) {
            ext = Arrays.stream(fileName.split("\\.")).reduce((a, b) -> b).orElse(null);
        }

        if (!possibleExtensions.contains(ext))
            throw new IllegalArgumentException("Invalid format document");
    }

    /**
     * Override Methods
     */
    @Override
    public boolean sameValueAs(final Attachment other) {
        return other != null && this.extension.equals(other.extension);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Attachment that = (Attachment) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return extension.hashCode();
    }

}
