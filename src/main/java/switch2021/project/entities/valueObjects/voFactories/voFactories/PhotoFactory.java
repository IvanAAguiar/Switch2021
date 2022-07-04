package switch2021.project.entities.valueObjects.voFactories.voFactories;

import org.springframework.stereotype.Component;
import switch2021.project.entities.valueObjects.voFactories.voInterfaces.IPhotoFactory;
import switch2021.project.entities.valueObjects.vos.Photo;

@Component
public class PhotoFactory implements IPhotoFactory {
    @Override
    public Photo createPhoto(String photo) {
        return new Photo(photo);
    }
}
