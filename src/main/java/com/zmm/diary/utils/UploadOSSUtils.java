package com.zmm.diary.utils;

import com.aliyun.oss.OSSClient;
import org.springframework.web.multipart.MultipartFile;

public class UploadOSSUtils {
	
	private static String endpoint="oss-cn-shanghai.aliyuncs.com";
	private static String accessKeyId="Nwh1FsDQrstVExU2";
	private static String accessKeySecret="LANDsFPT5fVNMBGdmVJMA1hCnuEQHS";
	private static String bucketName="uog";
	private static String picAddressHead="http://uog.oss-cn-shanghai.aliyuncs.com/pic/";

	/**
	 * 上传单张图片到云端
	 * @param requestFile
	 * @return
	 * @throws Exception
	 */
//	public static String uploadSinglePic(MultipartHttpServletRequest requestFile) throws Exception {
//		String picId = UUID.randomUUID().toString();
//		// 创建OSSClient实例
//		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//		// 上传文件流
//		ossClient.putObject(bucketName, "pic/"+picId+".jpg", requestFile.getFile("uploadFile").getInputStream());
//		// 关闭client
//		ossClient.shutdown();
////		String path = picAddressHead+picId+".jpg";
//		String path = picId+".jpg";
//
//		return path;
//	}

	/**
	 * 上传单张图片到云端
	 * @return
	 * @throws Exception
	 */
	public static String uploadSinglePic(MultipartFile file,String type) throws Exception {
		String picId = KeyUtil.getKeyId();
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		// 上传文件流
		ossClient.putObject(bucketName, "pic/"+picId+type, file.getInputStream());
		// 关闭client
		ossClient.shutdown();
		String path = picId+type;

		return path;
	}

}
