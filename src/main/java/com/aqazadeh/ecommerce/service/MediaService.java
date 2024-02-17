package com.aqazadeh.ecommerce.service;

import com.aqazadeh.ecommerce.model.Media;
import com.aqazadeh.ecommerce.model.Product;
import org.springframework.web.multipart.MultipartFile;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 17.02.2024
 * Time: 12:25
 */

public interface MediaService {
    Media upload(MultipartFile file, Product product);

    void delete(Long id);
}
