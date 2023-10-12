/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author digis
 */
public class Util {
    public static String code64(MultipartFile imageFile){
        try {
            if(!imageFile.isEmpty()){
                byte[] bytes=imageFile.getBytes();
                String imagenBase64= Base64.encodeBase64String(bytes);
                return imagenBase64;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
