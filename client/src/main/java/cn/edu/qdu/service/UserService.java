package cn.edu.qdu.service;

import cn.edu.qdu.common.Constants;
import cn.edu.qdu.model.Picture;
import cn.edu.qdu.model.Product;
import cn.edu.qdu.model.User;
import cn.edu.qdu.util.Image;
import cn.edu.qdu.util.SendMailUtil;
import cn.edu.qdu.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;


@Service
@Transactional
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	ProductService productService;

	@Autowired
	PictureService pictureService;

	@Autowired
	private UserRepository userDao;

	public boolean checkLogin(User user) {
		user = userDao.findByUsernameAndPassword(user.getUsername(),
				user.getPassword());
		return user != null;
	}
	
	public User findByUsernameAndPassword(String username,String password){
		User user = userDao.findByUsernameAndPassword(username, password);
		return user;
	}

    public User findByUsername(String username){
        return userDao.findByUsername(username);
    }
	
	public void save(User user) {
		userDao.save(user);
	}

	public User findOne(Integer id) {
		return userDao.findOne(id);
	}

	public void uploadImage(Product product, HttpSession session, MultipartFile file) {
		String fileName = generateFileName();
		String path = generateFilePath(session);
		String serverFile = path + "/" + fileName;
		Picture picture = uploadAndSaveImg(session, file, fileName, path, serverFile);
		product.setMasterPic(picture);
	}

	private String generateFilePath(HttpSession session) {
		return session.getServletContext().getRealPath("/upload");
	}

	private String generateFileName() {
		return new Date().getTime() + ".jpg";
	}

	private Picture uploadAndSaveImg(HttpSession session, MultipartFile file, String fileName, String path, String serverFile) {
		Picture picture = new Picture();
		try {
			logger.info(path);
			if (!new File(path).exists()) {
				new File(path).mkdirs();
			}
			if (!new File(serverFile).exists()) {
				new File(serverFile).createNewFile();
			}
			byte[] bytes = file.getBytes();
			BufferedOutputStream stream =
					new BufferedOutputStream(new FileOutputStream(new File(serverFile)));
			stream.write(bytes);
			stream.close();
			//缩放处理
			Image image = new Image(serverFile);
			image.resize(Constants.IMG_WIDTH,Constants.IMG_HEIGHT);
			image.save();
		} catch (Exception e) {
			e.printStackTrace();
		}
		picture.setMemo("商品上传");
		picture.setTitle("商品上传");
		picture.setUpdateTime(new Date());
		picture.setUrl("/upload/" + fileName);
		pictureService.save(picture);
		return picture;
	}

	public void uploadFile(Product product, HttpSession session, MultipartFile file) {

		String fileName = new Date().getTime()+getpostfix(file.getOriginalFilename());
		String path = generateFilePath(session);
		String serverFile = path + "/" + fileName;
		BufferedOutputStream stream = null;
		try {
			logger.info(path);
			if (!new File(path).exists()) {
				new File(path).mkdirs();
			}
			if (!new File(serverFile).exists()) {
				new File(serverFile).createNewFile();
			}
			byte[] bytes = file.getBytes();
			stream = new BufferedOutputStream(new FileOutputStream(new File(serverFile)));
			stream.write(bytes);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try {
				product.setUrl(fileName);
				product.setPostfix(getpostfix(fileName));
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getpostfix (String fname){
		String postfix=null;
		if (fname==null)
			return "";
		if(fname.indexOf(".")!=-1){
			postfix=fname.substring(fname.indexOf("."));
		}else{
			return "非法文件名";
		}
		return postfix;
	}

    public void insert(User user) throws MessagingException {
		String activation_code = UUID.randomUUID().toString().replaceAll("-", "");
		user.setState(0);
		user.setActivation_code(activation_code);
		user.setCreateTime(new Date());
		user.setPoint(100l);
		String context = "请点击该网址进行账号激活\n <a href='http://localhost:8080/user/activation?username="+user.getUsername()+"&activationCode="+activation_code+"'>点这里</a>";
		SendMailUtil.sendEmail(user.getEmail(),context);
		userDao.save(user);
    }

	public User queryByUsername(String username) {
		return userDao.findByUsername(username);
	}

	public void updateStatus(Integer id) {
		userDao.updateStatusById(id);
	}

	public void updatePwdByEmail(User user) throws MessagingException {
		User u = userDao.findByUsername(user.getUsername());
		u.setPassword(user.getPassword());
		u.setState(0);
		String activation_code = UUID.randomUUID().toString().replaceAll("-", "");
		u.setActivation_code(activation_code);
		String context = "请点击该网址进行账号激活\n <a href='http://localhost:8080/user/activation?username="+user.getUsername()+"&activationCode="+activation_code+"'>点这里</a>";
		SendMailUtil.sendEmail(user.getEmail(),context);
		userDao.save(u);
	}

	public void updatePwdByTel(User user) {
		User u = userDao.findByUsername(user.getUsername());
		u.setPassword(user.getPassword());
		userDao.save(u);
	}
}
