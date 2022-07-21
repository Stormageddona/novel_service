package com.novel.service.api;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.novel.service.data.temp.TextDetail;

@RestController
public class FileAPIController {
    @Value("${spring.servlet.multipart.location}") String path;
    @GetMapping("{type}/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String type,@PathVariable String filename, HttpServletRequest request) throws Exception
    {
        Path folderLocation = Paths.get(path+"/"+type) ;
        Path filePath = folderLocation.resolve(filename) ;
        Resource r = null;

        try {
            r = new UrlResource(filePath.toUri()) ;
        } catch (Exception e) { System.out.println("파일을 찾을 수 없거나, 잘못된 파일 경로입니다.");
        return null ;
        }
        
        //파일의 실제 경로에 찾아가서 파일의 유형을 가져옴
        String contentType = null ;
        try {
            request.getServletContext().getMimeType(r.getFile().getAbsolutePath()) ;
            if (contentType == null)
            contentType = "application/octet-stream" ;
        } catch (Exception e) {
            System.out.println("파일을 찾을 수 없거나, 잘못된 파일 경로입니다.");
            return null ;
        }


        return 
        ResponseEntity.ok() //결과로 200ok를 리턴
        // 파일의 타입을 Spring 프레임 워크를 통해 파일 유형을 결정
        .contentType(MediaType.parseMediaType(contentType))
        // 파일 이름의 표시 방법을 설정 (다운로드 되는 파일의 이름 설정)
        .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename*=\""+r.getFilename()+"\"")
        // 실제 리소스를 Body에 포함
        .body(r) ;
    } 

    @PutMapping("/image/upload")
    public Map<String,Object> putImageUpload(@RequestPart MultipartFile file )
    {
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>() ;
        Path forderLocaion = Paths.get(path+"/images") ;
        String fileName = file.getOriginalFilename() ;
        String[] fileNameSplit = fileName.split("\\.") ;
        String ext = fileNameSplit[fileNameSplit.length - 1] ;


        if (!ext.equalsIgnoreCase("jpg") && !ext.equalsIgnoreCase("png")  &&  !ext.equalsIgnoreCase("gif") )
        {            
            resultMap.put("status", false) ;
            resultMap.put("message", "이미지 파일 확장자는 jpg, png, gif만 허용합니다.") ;
            return resultMap ;
        }

        Calendar c = Calendar.getInstance() ;

        String saveFileName = StringUtils.cleanPath("img_" + c.getTimeInMillis() + "." +ext) ;
        Path target =forderLocaion.resolve(saveFileName) ;
        try {
            Files.copy(file.getInputStream(),target,StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            resultMap.put("status", false) ;
            resultMap.put("message", e.getMessage()) ;
            System.out.println(e);
            return resultMap ;
        }
        Long filesize = file.getSize() ;
        resultMap.put("status", true) ;
        resultMap.put("message", "파일 업로드 완료") ;
        resultMap.put("file",saveFileName) ;
        resultMap.put("ext",ext) ;
        resultMap.put("filesize",filesize) ;

        return resultMap ;
    }


    @PutMapping("/text/upload")
    @Transactional
    public  Map<String,Object> putTextFile(@RequestBody TextDetail data) throws IOException
    {
        Map<String,Object> map = new LinkedHashMap<String,Object>() ;
        Path forderLocaion = Paths.get(path+"/text") ;
        Calendar c = Calendar.getInstance() ;
        String saveFileName = StringUtils.cleanPath("text_" + c.getTimeInMillis() + ".txt") ;
        File file = new File(forderLocaion+"/"+saveFileName) ;
        BufferedWriter bw = new BufferedWriter(
            new FileWriter(file,false   // true : 이어쓰기, false : 덮어쓰기
                ))  ;
                
        bw.write(data.getDetail());
        bw.close();
        map.put("file",saveFileName) ;
        if (data.getComment().length() > 0)
        {
            saveFileName = StringUtils.cleanPath("comment_" + c.getTimeInMillis() + ".txt") ;
            file = new File(forderLocaion+"/"+saveFileName) ;
            bw = new BufferedWriter(
                new FileWriter(file,false   // true : 이어쓰기, false : 덮어쓰기
                    ))  ;
            bw.write(data.getComment());
            bw.close();
            map.put("comment",saveFileName) ;
        }
        map.put("status", true) ;
        map.put("message", "파일 업로드 완료") ;
        return map ;
    }


    @DeleteMapping("/image/delete/{filename}")
    public  Map<String,Object> deleteImageFile(@PathVariable String filename)
    {
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>() ;
        String filepath = path+"/images/"+filename ;
        File deleteFile = new File(filepath) ;
        if (deleteFile.exists())
        {
            deleteFile.delete();
        }
        else 
        {
            resultMap.put("status",false);
            resultMap.put("message","파일이 존재하지 않습니다.");
            resultMap.put("path",filepath);
        }
        resultMap.put("status",true);
        resultMap.put("message","파일이 삭제됬습니다.");
        return resultMap ;
    }


}
