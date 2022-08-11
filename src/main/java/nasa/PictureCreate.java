package nasa;

import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PictureCreate {

    public void createNewPicture(String pictureUrl, CloseableHttpResponse response){
        File file = new File(pictureUrl.trim());

        try (FileOutputStream fos = new FileOutputStream(file.getName())) {
            byte[] picture = response.getEntity().getContent().readAllBytes();
            fos.write(picture, 0, picture.length);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Картинка сохранена в " + file.getName());
    }
}
