package com.example.NovoTesteCrud.config.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    // Configuração do Cloudinary
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dcgx5uw86",
                "api_key", "564631686273896",
                "api_secret", "8-eGzYsX0JgRIfCL6W6vIIoYxTs"
        ));

    }
}
