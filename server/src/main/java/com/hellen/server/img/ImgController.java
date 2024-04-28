package com.hellen.server.img;

import com.hellen.entity.client.AnimalImg;
import com.hellen.result.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("img")
@CrossOrigin
public class ImgController {


    @Value("${file.upload.dir}")
    private String uploadDir;

    @PostMapping("upload")
    public Result upload(MultipartFile file) throws FileNotFoundException {
        if (file.isEmpty()) {
            return Result.fail("图片不能为空！");
        }

        String uniqueFileName = FileNameGenerator.generateUniqueFileName(file.getOriginalFilename());
        // 生成保存图片的路径
        String filePatch = ResourceUtils.getURL("classpath:").getPath() + "/static" + "/image/";
        if (filePatch.substring(0,1).equals("/")) {
            filePatch = filePatch.replaceFirst("/", "");
        }
        Path destinationFile = Paths.get(filePatch, uniqueFileName);

        // 确保目录存在
        try {
            Files.createDirectories(destinationFile.getParent());
        } catch (IOException e) {
            return Result.fail("上传失败！");
        }

        // 保存图片
        try {
            file.transferTo(destinationFile);
        } catch (IOException e) {
            return Result.fail("写入图片失败！");
        }

        // 构建响应
        String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/image/")
                .path(uniqueFileName)
                .toUriString();

        AnimalImg animalImg = new AnimalImg().setUrl(imageUrl);
        return Result.success().setData(animalImg);
    }


    /**
     * 生成随机文件名字
     */

    public class FileNameGenerator {

        private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        private static final String FILE_NAME_SEPARATOR = "-";
        private static final String FILE_EXTENSION_SEPARATOR = ".";
        private static final int RANDOM_SUFFIX_LENGTH = 5;
        private static final String[] ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".split("");

        public static String generateUniqueFileName(String originalFilename) {
            String extension = getExtension(originalFilename);

            LocalDateTime now = LocalDateTime.now();
            String timestampPart = now.format(TIMESTAMP_FORMATTER);
            String randomPart = generateRandomSuffix(RANDOM_SUFFIX_LENGTH);

            return timestampPart + FILE_NAME_SEPARATOR + randomPart + FILE_EXTENSION_SEPARATOR + extension;
        }

        private static String getExtension(String originalFilename) {
            int index = originalFilename.lastIndexOf(FILE_EXTENSION_SEPARATOR);
            return index != -1 ? originalFilename.substring(index + 1) : "";
        }

        private static String generateRandomSuffix(int length) {
            Random random = new Random();
            return random.ints(0, ALLOWED_CHARACTERS.length)
                    .mapToObj(i -> ALLOWED_CHARACTERS[i])
                    .limit(length)
                    .collect(Collectors.joining());
        }
    }
}
