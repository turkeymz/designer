package com.chaimao.designer.service;

import com.chaimao.designer.entity.Art;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: cmxu
 * @Description:
 * @Date： create in 19:50 2018/3/11
 * @Modified By:
 */
public interface ArtService {

    Art createArt(MultipartFile file,String jsonStr) throws Exception;

    String deleteArt(String jsonStr) throws Exception;

    String updateArt(String jsonStr) throws Exception;

    List<Art> getArtByUserno(String jsonStr) throws Exception;

    Art getArtByNo(String jsonStr) throws Exception;
}
