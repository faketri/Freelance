package com.hivework.domain.controller;


import com.hivework.domain.service.image.ImageService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;

@RestController
@CrossOrigin({"http://localhost:8080", "http://192.168.1.106:8080/"})
@RequestMapping(path = "/api/v1/image", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
public class ImagesController {

    private final ImageService imageService;

    public ImagesController(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * Gets image get id.
     *
     * @param id       the id
     * @param response the response
     * @throws IOException the io exception
     */
    @RequestMapping("/{id}")
    public void getImageGetId(@PathVariable Long id,
                              HttpServletResponse response
    ) throws IOException {
        try {
            final Image image = imageService.findById(id);
            final ClassPathResource classPathResource = new ClassPathResource(image.getPath());
            final InputStream in = classPathResource.exists() ? classPathResource.getInputStream() : new FileInputStream(image.getPath());
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            IOUtils.copy(in, response.getOutputStream());
        } catch (Exception e) {
            final InputStream in = new ClassPathResource("images/NotFound.png").getInputStream();
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            IOUtils.copy(in, response.getOutputStream());
        }
    }
}
