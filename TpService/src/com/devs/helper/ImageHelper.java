package com.devs.helper;

import com.mauroPignatta.Base64Image;
import com.mauroPignatta.Base64ImageConvertor;

import java.io.IOException;

public class ImageHelper {

    public static String saveImage(int userID, Base64Image image){
        String output = "";
        try {
            output = "res/user/user" + userID + "." + image.getExtension();
            Base64ImageConvertor.convertBase64ToImage(image, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

}
