package com.smallkk.qiniu;

import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created By  醉美柳舞之众星捧月
 *
 * @author song
 * @date 2018/2/20 17:00
 */
public class QiNiuFileUploadUtils {
    /**
     * 七牛云存储 上传下载类
     */
    /***七牛keys start****/
    public static final String QINIU_ACCESS_KEY = "rMNLHGzKthiHbpALgbuV1UULI28EDYrJHo8-KE_i";
    public static final String QINIU_SECRET_KEY = "Va_J0CKUFUUPBpXOFjeWvXyUW7MtmUeAKzhIOmBy";
    public static final String QINIU_HEAD_IMG_BUCKET_NAME = "mamabike";

    public static String uploadHeadImg(MultipartFile file) throws IOException {
        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(QINIU_ACCESS_KEY, QINIU_SECRET_KEY);
        String upToken = auth.uploadToken(QINIU_HEAD_IMG_BUCKET_NAME);
        Response response = uploadManager.put(file.getBytes(), null, upToken);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        return putRet.key;
        // key 代表的是文件的名字
        //可以自定义设定名字  /如果设定的话就默认是Hash值

    }
}
