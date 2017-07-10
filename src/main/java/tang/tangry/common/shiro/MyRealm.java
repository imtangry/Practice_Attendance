package tang.tangry.common.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import tang.tangry.user.entity.Permission;
import tang.tangry.user.entity.Role;
import tang.tangry.user.entity.User;
import tang.tangry.user.service.UserService;
import tang.tangry.user.service.UserServiceImpl;

/**
 * Created by tryu on 2017/7/8.
 */

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * Create by tryu 2017/7/9 12:55
     * 授权
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("OK");
        String username = (String) principalCollection.getPrimaryPrincipal();
        User user = userService.findUserByUname(username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : user.getRoles()) {
            authorizationInfo.addRole(role.getRoleName());
            for (Permission permission : role.getPermissionList()) {
                authorizationInfo.addStringPermission(permission.getPermissionOperation());
            }
        }
        return authorizationInfo;
    }


    /**
     * Create by tryu 2017/7/9 12:55
     * 认证登陆
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String userName = usernamePasswordToken.getUsername();
        User user = userService.findUserByUname(userName);
        String a = "aaaaaa";
        if (user == null) {
            return null;
        } else {
            //将数据库查询出的信息加到info，等下到自定义的密码校验器里校验。
            AuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
            SecurityUtils.getSubject().getSession().setAttribute("userInfo", user);
            return info;
        }
    }
}
