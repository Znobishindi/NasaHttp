package nasa;

import org.apache.http.client.methods.CloseableHttpResponse;
import pictureConverter.BadImageSizeException;
import pictureConverter.Converter;
import pictureConverter.Schema;
import pictureConverter.TextGraphicsConverter;

import java.io.IOException;

public class Main {
    public static final String REMOTE_SERVICE_URI = "https://api.nasa.gov/planetary/apod?api_key=m5B25BPuu1cDelTyEwEzdm6LmpiNlrUsM2HBS5rE";

    public static void main(String[] args) throws IOException, BadImageSizeException {

        Http http = new Http();

        CloseableHttpResponse response = http.getResponseJson(REMOTE_SERVICE_URI, http.setHttpClient());
        JsonMapper jsonMapper = new JsonMapper();

        NASA nasa = jsonMapper.getNASAList(response);

        response.close();

        response = http.getResponseJson(nasa.getUrl(), http.setHttpClient());

        PictureCreate pc = new PictureCreate();
        pc.createNewPicture(nasa.getUrl(), response);

        http.setHttpClient().close();
        response.close();


        // Немного добавил: разложил изображение на пиксели и заменил их символами(первая курсовая работа)))

        TextGraphicsConverter converter = new Converter();
        Schema schema = new Schema();
        converter.setMaxRatio(4.0);
        converter.setMaxWidth(80);
        converter.setMaxHeight(80);
       // schema.setText("@#$%*()-");

        converter.setTextColorSchema(schema);
        String imgTxt = converter.convert(nasa.getUrl());
        System.out.println(imgTxt);
        // Эту картинку, правда, плохо видно....
    }

}



