package com.aqazadeh.ecommerce.service.imp;

import com.aqazadeh.ecommerce.model.Media;
import com.aqazadeh.ecommerce.model.Product;
import com.aqazadeh.ecommerce.model.enums.MediaType;
import com.aqazadeh.ecommerce.repository.MediaRepository;
import com.aqazadeh.ecommerce.service.MediaService;
import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 17.02.2024
 * Time: 12:26
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MediaServiceImpl implements MediaService {
    private final MediaRepository mediaRepository;
    private final Cloudinary cloudinary;

    @Override
    public Media upload(MultipartFile file, Product product) {
        try {
            HashMap<Object, Object> options = new HashMap<>();
            options.put("folder", "products");
            Map uploadedFile = cloudinary.uploader().upload(file.getBytes(), options);
            String publicId = (String) uploadedFile.get("public_id");
//            String url = cloudinary.url().secure(true).generate(publicId);
            return Media.builder()
                    .mediaType(MediaType.IMAGE)
                    .url(publicId)
                    .product(product)
                    .build();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Long id) {

    }
}
