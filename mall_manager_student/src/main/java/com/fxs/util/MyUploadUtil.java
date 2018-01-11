package com.fxs.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.web.multipart.MultipartFile;

public class MyUploadUtil {

	public static List<String> upload_image(MultipartFile[] files) {
		
		List<String> list_image = new CopyOnWriteArrayList<String>();
		for(int i=0;i<files.length;i++) {
			if(!files[i].isEmpty()) {
				//创建唯一的图片名
				String image_name = System.currentTimeMillis()+files[i].getOriginalFilename();
				try {
					files[i].transferTo(new File(MyPropertyUtil.getSaveImgPath("properties/upload.properties","windowspath_image")+"/"+image_name));
					list_image.add(image_name);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return list_image;
	}
	
}
