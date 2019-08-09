package nju.software.ssm.service.impl;

import nju.software.ssm.dao.IUserDao;
import nju.software.ssm.domain.Role;
import nju.software.ssm.domain.UserInfo;
import nju.software.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo=null;
        try{
            userInfo=userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将自己的用户对象封装成UserDetails
        //User user=new User(userInfo.getUsername(),"{noop}"+userInfo.getpsword(),this.getAuthority(userInfo.getRoles()));
        User user=new User(userInfo.getUsername(),userInfo.getpsword(), userInfo.getStatus()==1?true:false,
                true,true, true,
                this.getAuthority(userInfo.getRoles()));
        return user;
    }

    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list=new ArrayList<>();
        for(Role role:roles){
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }
    @Override
    public List<UserInfo> findAll() throws Exception{
        return userDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) throws Exception{
        //进行密码加密，然后再传回数据库
        userInfo.setPsword(bCryptPasswordEncoder.encode(userInfo.getpsword()));
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(Integer id) throws Exception {
        return userDao.findById(id);
    }
}
